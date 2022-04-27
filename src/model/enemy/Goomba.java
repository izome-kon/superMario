package model.enemy;

import manager.Camera;
import view.Animation;

import java.awt.*;
import java.util.Arrays;
import view.ImageStore;

public class Goomba extends Enemy {
    private boolean alive,startMove ;
    private Animation animation ;
    private final int speed;
    private boolean under ;
    private int maxH; 
    enum die {
    THROW,
    COMPRESS
    }
    die die;
    public Goomba (double x, double y,boolean under) {
        super(x, y);
        this.under=under;
        setToRight(false);
        alive = true ;
        if(!under)
        animation = new Animation(Arrays.copyOfRange(ImageStore.GOMBA, 0, 2));
        else
        animation = new Animation(Arrays.copyOfRange(ImageStore.UnderGOMBA, 0, 2));
        die = null;
        speed = 2;
        startMove=false;
        setMaxH(5000);
        setVelY(0);
        
    }

    public void draw (Graphics g)
    {
        update();
        super.setStyle(animation.animate(5));
        if(alive||die==die.THROW){
        setFalling(true);
        updateLocation();
        }else{
        setFalling(false);
        }
        if(!alive)
            setDieT(getDieT()+1);
        if(getDieT()>20){
            setEdie(true);
        }
        super.draw(g);
        if(!isCc())
            setDieT(getDieT()+1);
//        g.setColor(Color.red);
//        g.fillRect(getRec().x, getRec().y, getRec().width, getRec().height);
    }
    
    @Override
    public void updateLocation(){
        if(getX()<= Camera.x+1268+48)
            startMove=true;
       if(startMove){
        if(isToRight()){
            setVelX(speed);
        }else{
            setVelX(-speed);
        }
        if(isFalling()){
            setY(getY() + getVelY()) ;
            setVelY(getVelY() + getGravityAcc());
        }
        
        setX(getX()+getVelX());
       }
    }
    @Override
   public void update ()
    {
        if (alive)
        {   if(!under)
            animation.setAnimation(Arrays.copyOfRange(ImageStore.GOMBA, 0, 2));
            else
            animation.setAnimation(Arrays.copyOfRange(ImageStore.UnderGOMBA, 0, 2));
        }
        else if(this.die == die.THROW) 
        {
            //3nd 3mr
        }
        else if(this.die == die.COMPRESS){
           if(!under)
            animation.setAnimation(Arrays.copyOfRange(ImageStore.GOMBA, 2, 3));
            else
            animation.setAnimation(Arrays.copyOfRange(ImageStore.UnderGOMBA, 2, 3));
        }
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setMaxH(int maxH) {
        this.maxH = maxH;
    }
    
     public void firedie(){
       setAlive(false);
       die = die.THROW;
       
       setMaxH((int)getY()-100);
       setY(getY()+getHIEGHT());
       setVelY(-3);
   }
   public void mariodie(){
       setAlive(false);
       die = die.COMPRESS;
   }
    
}