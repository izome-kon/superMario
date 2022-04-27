/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.brick.Brick;
import view.ImageStore;

import java.awt.*;

public class EndPoint extends GameObject{

    public EndPoint(double x, double y) {
        super(x, y);
    }
     public void drawJust(Graphics  g)
    {
        setStyle(ImageStore.End);
        g.drawImage(getStyle(),(int)getX(),(int)getY(),null);
    }
}
