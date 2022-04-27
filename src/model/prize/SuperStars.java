package model.prize;

import java.awt.Graphics;

import view.Animation;
import view.Points;
import view.ImageStore;

public class SuperStars extends BoostItem{
    Animation A;
    private Points points ;

    public SuperStars(double x, double y) {
        super(x, y);
        A = new Animation(ImageStore.STAR);
        setPoint("400");
        points = new Points(getPoint(),(int)x,(int)y);
        setVelX(5);
        setVelY(5);
    }
    
    public void draw(Graphics g){
         if(!isRevealed()){
          setStyle(A.animate(5));
          super.draw(g);
        }
        else 
         points.draw(g);
            
        updateLocation();
    }
    public void updateLocation(){
     
     if(isToRight())  
         setX(getX()+getVelX());
     else 
         setX(getX()-getVelX());
     if(isFalling())
         setY(getY()+getVelY());
     if(isJumping())
         setY(getY()-getVelY());
         
         
         
         
 }
   
    
    public void onTouch(){}

}
