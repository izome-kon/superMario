package model.prize;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import model.GameObject;

public class BoostItem extends GameObject implements Prize{
    private boolean revealed = true ;
    private String point;
    private double upTo;
    private boolean uped , goDown;

    public void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    public boolean isGoDown() {
        return goDown;
    }

    public boolean isUped() {
        return uped;
    }

    public double getUpTo() {
        return upTo;
    }

    public void setUped(boolean uped) {
        this.uped = uped;
    }
    
    public void setPoint(String point) {
        this.point = point;
    }
    public BoostItem(double x, double y) {
        super(x, y);
         upTo=y-47;
    }
    public void onTouch(){}
    //public void updateLocation(){}
    public void draw(Graphics g){
        super.draw(g);
    }
    public void reveal(){}

    @Override
    public String getPoint() {
        return point;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }



    @Override
    public boolean CollisionPrize(Rectangle r) { 
    return r.intersects(getRec());
    }

    @Override
    public void setPoints(String s) {
    point=s;  
    }
    
    public void remove(){
        setRec(new Rectangle(0,0,0,0));
        
    }
}
