package model.prize;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import view.Animation;
import view.Points;
import view.ImageStore;
public class OneUpMushroom extends BoostItem{
    private Animation A;
    private BufferedImage [] OneUpMushroom;
    private Points points ;
    public boolean gets = false;
    public OneUpMushroom(double x, double y) {
        super(x, y);
        OneUpMushroom= Arrays.copyOfRange(ImageStore.MASHRUM,0,2);
        A = new Animation(OneUpMushroom);
        setPoint("300");
        points = new Points(getPoint(),(int)x,(int)y);
        setVelX(2);
        
    }
     
    public void draw(Graphics g){
          if(!isRevealed()){
          setStyle(A.animate(10));
            
          if(getY()<getUpTo()){
              setUped(true);
         
          }else if(!isUped())
                 setY(getY()-1);
           
          updateLocation();
          super.draw(g);
         }
        else 
         points.draw(g);
        
    }
   
 public void onTouch(){
      setRevealed(true);
 }
 public void updateLocation(){
   if(isUped()){
     if(isToRight())  
         setX(getX()+getVelX());
     else 
         setX(getX()-getVelX());
    if(isFalling()){
            setVelY(getVelY() + getGravityAcc());
        }else
            setVelY(0);
    }
     setY(getY()+getVelY());  
 }
}
