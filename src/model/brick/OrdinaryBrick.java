package model.brick;

import java.awt.Graphics;

import model.hero.MarioForm;

import view.ImageStore;
public class OrdinaryBrick extends Brick {
    private int ShockTime ;
    private int BreakTime ;
    private boolean shocked ;
    private boolean Break ;
    private Breaking B1,B2,B3,B4 ;
    public OrdinaryBrick(double x, double y,boolean b) {
        
          super(x, y,b);
        ShockTime =0 ; 
        shocked= false ;
        Break= false;
        B1 = new  Breaking(x, y);
        B2 = new  Breaking(x+24, y);
        B3 = new  Breaking(x, y+24);
        B4 = new  Breaking(x+24, y+24);
        
    }

    public void draw(Graphics  g)
    {
         if (ShockTime==22)
        {
          ShockTime=0;
          shocked=false;
          setFirst(true);
        }
        else if (shocked)
        {
            updateLocation();
            ShockTime++;
        }
        
        if (BreakTime==30)
         {
            BreakTime=0;
            Break=false;
            setBreakable(true);
        }
        else if (Break)
       {
        B1.draw(g);
        B2.draw(g);
        B3.draw(g);
        B4.draw(g);
        B1.DropLeftUp();
        B2.DropRightUp();
        B3.DropLeftDown();
        B4.DropRightDown();
        
        BreakTime++;

        }
   
      
        else
       {
            if(!isUnder())
            setStyle(ImageStore.BRICKS[2]);
            else
            setStyle(ImageStore.UnderBRICKS[2]);
        super.draw(g);
       }
    }
    

 public void HIT(MarioForm.Forms f) {
        if (f==MarioForm.Forms.SUPER)
        {
            Break= true;

        }
        
        else if (f==MarioForm.Forms.MARIO)
        { 
            
            shocked=true ;

        }
       }   
}
