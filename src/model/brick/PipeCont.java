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
public class PipeCont extends Brick{
    
    public PipeCont(double x, double y,boolean b) {
        super(x, y,b);
        setStyle(ImageStore.PIPES[0].getSubimage(0, 16, 32, 16));
    }
    public void draw(Graphics g){
        super.draw(g);
//        g.setColor(Color.PINK);
//        g.fillRect(getRec().x, getRec().y, getRec().width, getRec().height);
  }


    
}
