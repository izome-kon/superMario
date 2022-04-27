package view;

import java.awt.image.BufferedImage;

public class Animation {

    private int index, count , speed ;
    private BufferedImage[] Frames;
    private BufferedImage currentFrame = null;

    public Animation( BufferedImage[] Frames){
        this.Frames = Frames;
      
        index = 0 ; 
        speed = 0 ;
        if(this.Frames!=null)
        count = Frames.length;
        
    }
    public void setAnimation(BufferedImage[] an){
    this.Frames = an;
    count = Frames.length;
    }

    public BufferedImage animate(int speed){
        this.speed++;
        if (this.speed>speed)
        {
         this.speed =0;
        index = (index+1)%count;
        currentFrame = Frames[index];
        }
        return currentFrame;
    }

    public BufferedImage[] getFrames() {
        return Frames;
    }

   

}
