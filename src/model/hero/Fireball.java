package model.hero;

import java.awt.Graphics;
import java.util.Arrays;
import model.GameObject;
import view.Animation;
import view.ImageStore;

public class Fireball extends GameObject{
    private Animation animation;
    private boolean hit,die;
    private double maxHieght,live,dieT;
    public Fireball(double x, double y) {
        super(x, y);
        animation = new Animation(Arrays.copyOfRange(ImageStore.FIREBALL, 0, 4));
        super.setStyle(ImageStore.FIREBALL[0]);
        hit = false;
        setToRight(true);
        setFalling(true);
        setJumping(false);
        maxHieght=0;
        die = false;
        live = 0;
        dieT = 0;
    }

    public void setLive(double live) {
        this.live = live;
    }
    public void draw(Graphics g){
        if(dieT>=8){
        die = true;
        }
        setStyle(animation.animate(2));
        update();
        super.draw(g);
    }

    public boolean isDie() {
        return die;
    }
    public void update(){
        if(!die)
        if(live<=200){
        if(hit){
            setFalling(false);
            setJumping(true);
            hit = false;
        }
        updatelocation();
        live++;
        }else{
            animation.setAnimation(Arrays.copyOfRange(ImageStore.FIREBALL, 4, 7));
            dieT++;
        }
    }
    public void updatelocation(){
        if(isToRight()){
            setX(getX()+8);
        }else{
            setX(getX()-8);
        }
        if(getY()<=maxHieght){
            setFalling(true);
            setJumping(false);
        }
        if(isFalling()){
            setY(getY()+3);
        }
        if(isJumping())
        setY(getY()-3);
        
    }

    public void setMaxHieght(double maxHieght) {
        this.maxHieght = maxHieght;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
