package model.brick;


import java.awt.Graphics;

import view.ImageStore;
public class GroundBrick extends Brick{

  
    public GroundBrick(double x, double y,boolean under) {
        super(x, y,under);
    }

     
    public void draw(Graphics  g)
    {
        if(!isUnder())
        setStyle(ImageStore.BRICKS[1]);
        else
        setStyle(ImageStore.UnderBRICKS[1]);
       // setDimension(70,70);
        super.draw(g);
        
//                g.setColor(Color.GREEN);
//        g.fillRect(getRec().x,getRec().y,getRec().width,getRec().height);
    }


}

