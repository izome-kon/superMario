/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.Graphics;

/**
 *
 * @author Shrouk
 */
import view.ImageStore;
public class Breaking extends Brick{
    
    public Breaking(double x, double y) {
        super(x, y,false);
        setStyle(ImageStore.BREAK_BRICKS);
    }
 
     public void draw(Graphics  g)
    {
       super.draw(g);
    
       
      }

    public void DropRightUp()
    {
         setX(getX()+getGravityAcc()*5);
         setY(getY()-getGravityAcc()*9);
         
    }
    public void DropLeftUp()
    {
         setX(getX()-getGravityAcc()*5);
         setY(getY()-getGravityAcc()*9);
    }
public void DropRightDown()
    {
         setX(getX()+getGravityAcc()*5);
         setY(getY()+getGravityAcc()*9);
    }
    public void DropLeftDown()
    {
         setX(getX()-getGravityAcc()*5);
         setY(getY()+getGravityAcc()*9);
    }
}
