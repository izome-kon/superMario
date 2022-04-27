package model.prize;

import java.awt.Graphics;

import view.Animation;
import view.Points;
import view.ImageStore;
public class FireFlower extends BoostItem {
  
  private Animation A;
  private Points points ;
  
    public FireFlower(double x, double y) {
        super(x, y);
        setPoint("200");
        A = new Animation(ImageStore.FLOWER);
        points = new Points(getPoint(),(int)x,(int)y);
        
        
      
    }
  
    public void draw(Graphics g)
    {  
       if(!isRevealed()){
           
             setStyle(A.animate(5));
             super.draw(g);  
             updateLocation();
             
       }
       else 
           points.draw(g);
            
         }

    
    public void onTouch(){
        setRevealed(true);   
    }
    public void updateLocation(){
        if(getY()>getUpTo())
            setY(getY()-1);
    }
    

}
