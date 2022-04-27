package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public ImageLoader(){
  
    }

    public BufferedImage loadImage(String path){
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(getClass().getResource("/media/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public BufferedImage loadImage(File file){
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public BufferedImage getSubImage(BufferedImage image, int col, int row, int w, int h){
        return image.getSubimage(col, row, w, h);
    }

    

    

}
