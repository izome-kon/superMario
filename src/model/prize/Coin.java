package model.prize;

import java.awt.Graphics;
import java.awt.Rectangle;

import model.GameObject;
import view.Animation;
import view.Points;
import view.ImageStore;

public class Coin extends GameObject implements Prize{
    private String point;
    private Points points;
    private boolean revealed;
    private int revealBoundry;
    private Animation A;
    private boolean show = true;
    private int count ;
    public boolean gets=false;
   
    
    public Coin(double x, double y) {
        super(x, y);
        point = "100";
        revealed = false;
        revealBoundry = 0;
        A = new Animation(ImageStore.COIN);
        points = new Points(point,(int)x,(int)y);
        count = 0;
    }

    public void reveal(){}
    public void updateLocation(){}
    public void draw(Graphics g)
    {  
         if(!show){

             if(count<=30) 
             {
             setStyle(A.animate(5));
             super.draw(g); 
            
                 setY(getY()-count/2);
             
             }
             count++;
             points.draw(g);
         }else{
       setStyle(A.animate(5));
        super.draw(g);
         }
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    @Override
    public String getPoint() {
    return point;
    }

    @Override
    public boolean CollisionPrize(Rectangle r) { 
    return r.intersects(getRec());
    }

    @Override
    public void setPoints(String s) {
        point=s;
    }
}
