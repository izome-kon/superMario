package model.brick;

import model.GameObject;
import model.Map;
import view.Animation;
import view.ImageStore;
public abstract class Brick extends GameObject{
private boolean under ; 
    private boolean   empty ;
    private Animation brickAnimation ; 
    private double oldY , newY ;
    private boolean   breakable ;

    private boolean First;
    public Brick(double x, double y,boolean under)       
    {
    
        super(x, y);
        this.under = under;
        breakable = false;
        oldY = getY();
        newY = oldY-20 ;
        brickAnimation = new Animation(ImageStore.BRICKS);
        First =true ;
    }

 
    public void reveal (Map gameMap)
    {
         if(breakable)
         {
       gameMap.getBricks().remove(this);
         }
         
    }

    public void setUnder(boolean under) {
        this.under = under;
    }

    public boolean isUnder() {
        return under;
    }
    
    @Override
    public void updateLocation()
    {
        // lw awl mara ytla3 
        if (getY()>=newY&&First)
        {
            setY(getY()-getGravityAcc()*5);
       
        }   
        else if (getY()<=oldY)
        {
            setY(getY()+getGravityAcc()*5);
            if(getY()>oldY)
                setY(oldY);
             First = false ;
        }

        
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public boolean isBreakable() {
        return breakable;
    }
    
     public void setFirst(boolean First) {
        this.First = First;
    }
}
