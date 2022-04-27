package model.brick;

import java.awt.Graphics;
import view.ImageStore;

public class Pipe extends Brick{

   
    public Pipe(double x, double y,boolean b) {
        super(x, y,b);
       
    }
  
    public void draw(Graphics  g)
    {
        setStyle(ImageStore.PIPES[0]);
        super.draw(g);
    }


   

  
}
