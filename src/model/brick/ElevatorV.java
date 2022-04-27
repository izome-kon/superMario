/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.Graphics;

/**
 *
 * @author Just Smile
 */
import view.ImageStore;
public class ElevatorV extends Brick{
    private boolean up ;
    public ElevatorV(double x, double y, boolean up) {
        super(x, y, false);
        this.up= up;
        setStyle(ImageStore.elevator);
    }
    
    
    @Override
    public void draw(Graphics g){
        super.draw(g);
        updateLocation();
    }
    
    @Override
    public void updateLocation(){
        if(up)
            setVelY(3);
        else
            setVelY(-3);
        if(getY()<=0-getHIEGHT()-5)
            setY(708+getHIEGHT());
        else if(getY()>=708+getHIEGHT()+5)
            setY(-getHIEGHT());
        setY(getY()+getVelY());
    }


}
