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
public class WaterBrick extends Brick{

    public WaterBrick(double x, double y) {
        super(x, y,false);
    }
 public void draw(Graphics  g)
    {
        setStyle(ImageStore.BRICKS[9]);
        super.draw(g);
    }

     
}
