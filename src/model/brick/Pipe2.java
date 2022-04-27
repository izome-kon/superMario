/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.Graphics;

/**
 *
 * @author Just Smile
 */
import view.ImageStore;
public class Pipe2 extends Brick {

      public Pipe2(double x, double y,boolean b) {
        super(x, y-96,b);

      }
      
 
    public void draw(Graphics  g)
    {

        setStyle(ImageStore.PIPES[0].getSubimage(0, 0, 32, 32));
        super.draw(g);
    }

     
}
