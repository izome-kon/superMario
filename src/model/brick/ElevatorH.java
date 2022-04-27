/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.*;

/**
 *
 * @author Just Smile
 */
import view.ImageStore;
public class ElevatorH extends Brick{
    private int max[] ;
    private boolean goToRight;
    public ElevatorH(double x, double y,int maxRight,int maxLeft,double velX,boolean goToRight) {
        super(x, y, false);
        max=new int[2];
        max[0]=(int)x+maxRight;
        max[1]=(int)x-maxLeft;
        setStyle(ImageStore.elevator);
        setVelX(velX);
        this.goToRight=goToRight;
    }
    
    
    @Override
    public void draw(Graphics g){
        super.draw(g);
        updateLocation();
    }
    
    @Override
    public void updateLocation(){
        if(goToRight){
            setX(getX()+getVelX());
        }else
            setX(getX()-getVelX());

        if(getX()>=max[0])
            goToRight=false;
        else if(getX()<=max[1])
            goToRight=true;
    }


}
