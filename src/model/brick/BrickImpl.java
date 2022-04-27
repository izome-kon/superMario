/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.Graphics;
import view.ImageStore;

public  class BrickImpl extends Brick {
   
 
    public BrickImpl(double x, double y,boolean under) {
        super(x, y, under);
 
    }
    
     
    public void draw(Graphics  g)
    {
        setStyle(ImageStore.BRICKS[6]);
      //  setDimension(70,70);
        super.draw(g);
    }


   
    
}
