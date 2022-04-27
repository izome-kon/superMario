package model.enemy;

import manager.Camera;
import view.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import view.ImageStore;

public class KoopaTroopa extends Enemy {
    private boolean alive,startMove ;
    private Animation animation ;
    private int speed ;
    private BufferedImage [] arr;
    private statu st ;
    private boolean rm;
    public KoopaTroopa(double x, double y, statu status,int type) {
        super(x, y);
       setToRight(true);
       rm=false;
        alive = true ;
        if(type == 1){
        arr = ImageStore.KOOPA;
        }else if(type == 2){
        arr = ImageStore.KOOPA2;
        }else
            arr = ImageStore.UnderKOOPA;
       animation = new Animation(Arrays.copyOfRange(arr, 0,2));
         st = status;
         speed = 2;
        }

    public void setRm(boolean rm) {
        this.rm = rm;
    }

    public boolean isRm() {
        return rm;
    }


    public void draw (Graphics g)
    {
        setFalling(true);
        update();
        updateLocation();
        super.setStyle(animation.animate(8));
        super.draw(g);
        if(getDieT()>200){
            setEdie(true);
        }
    }
    
    public void update ()
    {
        if (st == st.FLY)
        {
            animation.setAnimation(Arrays.copyOfRange(arr, 2, 4));
            setFalling(false);
        }
        else if (st == st.RUN)
        {
            setFalling(true);
            animation.setAnimation(Arrays.copyOfRange(arr, 0, 2));
        }
        else if (st == st.ROLL)
        {
            animation.setAnimation(Arrays.copyOfRange(arr, 4, 5));
        }
        else if (st == st.THROW)
        {
            //3and Amr
        }
        
    }
    public void turnStatu(){
    if(st == statu.FLY)
        st = statu.RUN;
    else if(st == statu.RUN){
        st = statu.ROLL;
        setR(true);
    }
    else if(!rm){
        speed*=2;
        rm = true;
        setR(false);
    }
    else
        setEdie(true);
    }
    
     public void updateLocation(){
        if(getX()<= Camera.x+1268+48)
            startMove=true;
       if(startMove){
        if(isToRight()){
            setVelX(-speed);
        }else{
            setVelX(+speed);
        }
        if(isFalling()){
            setY(getY() + getVelY()) ;
            setVelY(getVelY() + getGravityAcc());
        }
        if(st!=statu.ROLL)
        setX(getX()+getVelX());
        else if(rm){
        setX(getX()+getVelX());
            setDieT(getDieT()+1);
        }
       }
    }

    
}
