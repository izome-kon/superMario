package model.enemy;

import model.GameObject;
import model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Enemy extends GameObject {
    private BufferedImage style ;
    private Rectangle interSctionRect;
    private boolean Edie,r;
    private boolean tauchBrick;
    private int dieT;
    public Enemy(double x, double y) {
        super(x, y);
        dieT = 0;
        r = false;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean isR() {
        return r;
    }

    public boolean isTauchBrick() {
        return tauchBrick;
    }

    public void setTauchBrick(boolean tauchBrick) {
        this.tauchBrick = tauchBrick;
    }
  
    public String checkHits(Rectangle r,Rectangle r2){
        String s = "no";
        Rectangle r3 = r2.intersection(r);
        interSctionRect=r3;
         if(r2.intersects(r)){
             if(r3.getX()==r.x&&r3.height>r3.width){
            s="left";
         }
         if(r3.getX()+r3.width==r.getX()+r.width&&r3.height>r3.width){
              s="right";
         }
         if(r3.getY()==r.y&&r3.height<r3.width){
             s="up";
         }
         if(r3.getY()+r3.getHeight()==r.getY()+r.getHeight()&&r3.height<r3.width){
             s="down";
         }
         }
         return s;
    }
  
    public void InTouch (Mario mario)
    {
        
    }
    @Override
    public Rectangle getTopBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getBottomBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getLeftBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getRightBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Rectangle getInterSctionRect() {
        return interSctionRect;
    }
        public boolean isEdie() {
        return Edie;
    }

    public void setDieT(int dieT) {
        this.dieT = dieT;
    }

    public int getDieT() {
        return dieT;
    }

    public void setEdie(boolean Edie) {
        this.Edie = Edie;
    }

}