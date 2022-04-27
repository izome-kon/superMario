/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.brick;

import java.awt.Graphics;

import view.ImageStore;
public class GreenBrick extends Brick {

    private int num ;
    public GreenBrick(double x, double y , int num) {
        super(x, y,false);
        this.num = num ;
    }
   
    public void draw(Graphics  g)
    {
        if (num==1)
        setStyle(ImageStore.GreenBrick[0]);
        else if (num==2)
            setStyle(ImageStore.GreenBrick[1]);
        else if (num==3)
            setStyle(ImageStore.GreenBrick[2]);
     
        super.draw(g);
        
     }

    
}
