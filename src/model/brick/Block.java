/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.Graphics;
import view.ImageStore;
/**
 *
 * @author Shrouk
 */
public class Block extends Brick{

    public Block(double x, double y,boolean under) {
        super(x, y,under);
        setDimension(16*3,16*3);
        if(isUnder())
            setStyle(ImageStore.UnderBRICKS[0]);
        else
            setStyle(ImageStore.BRICKS[0]);
        
    }
    
    public void draw(Graphics  g)
    {
        super.draw(g);
    }

    
}
