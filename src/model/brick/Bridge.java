/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.*;

/**
 *
 * @author Shrouk
 */
import view.ImageStore;
public class Bridge extends Brick{

    public Bridge(double x, double y) {
        super(x, y,false);
        setStyle(ImageStore.BRIDGE);
    }
    public void draw(Graphics  g)
    {
        super.draw(g);
    }

    
}
