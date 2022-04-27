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
public class Fire extends Brick{

    public Fire(double x, double y,boolean upFire) {
        super(x, y,false);
        if(upFire)
        setStyle(ImageStore.Fire[0]);
        else
        setStyle(ImageStore.Fire[1]);
    }
    public void draw(Graphics  g)
    {
        super.draw(g);
    }

    public void updateLocation(){

    }
    
}
