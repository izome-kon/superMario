package model.brick;

import java.awt.Graphics;
import java.util.Arrays;
import model.Map;
import model.hero.Mario;
import model.hero.MarioForm;
import model.prize.Coin;
import model.prize.FireFlower;
import model.prize.OneUpMushroom;
import model.prize.Prize;
import model.prize.SuperMushroom;
import model.prize.SuperStars;
import view.Animation;
import view.ImageStore;
public class SurpriseBrick extends Brick{
    
    private Prize prize ; 
    private Animation animation ;
    private boolean draw = false;
    private Coin coin ;
    private OneUpMushroom OneUpMushroom;
    private SuperMushroom SuperMushroom;
    private FireFlower FireFlower;
    private SuperStars SuperStars;
    private boolean hited;
    
    public SurpriseBrick(double x, double y, Prize prize,boolean under) {
        super(x, y,under);
       
        animation = new Animation(Arrays.copyOfRange(ImageStore.BRICKS, 4,6));
        this.prize = prize ;
        if(prize instanceof Coin)
        coin = (Coin)this.prize;
        else if(prize instanceof FireFlower)
           FireFlower  = (FireFlower)this.prize;
        else if(prize instanceof OneUpMushroom)
           OneUpMushroom  = (OneUpMushroom)this.prize;
        else if(prize instanceof SuperMushroom)
           SuperMushroom  = (SuperMushroom)this.prize;
        else if(prize instanceof SuperStars)
           SuperStars  = (SuperStars)this.prize;


    }
     
    @Override
    public void draw(Graphics  g)
    {
        if(hited)
             setStyle(ImageStore.BRICKS[3]);
        else
        setStyle(animation.animate(15));
        super.draw(g);
        if(draw)
            coin.draw(g);
        else if (FireFlower!=null&&!FireFlower.isRevealed()){
          FireFlower.draw(g);
      
            }
        else if (OneUpMushroom!=null&&!OneUpMushroom.isRevealed()){
          OneUpMushroom.draw(g);
            OneUpMushroom.setFalling(true);
        }
         else if(SuperMushroom!=null&&!SuperMushroom.isRevealed()){
           SuperMushroom.draw(g);
           SuperMushroom.setFalling(true);
            }
        else if(SuperStars!=null&&!SuperStars.isRevealed()){
           SuperStars.draw(g);
            SuperStars.setFalling(true);
         }
    }
    
     
    @Override
    public void reveal (Map gameMap)
    {
     //yt7awel l Impbrick   
    }
    
    
  
    public Prize getPrize()
    {
        return prize ;
    }

  
      public void HIT(MarioForm.Forms f,Mario mario) {

            if (prize instanceof Coin) {
                coin.setShow(false);
                draw = true;
                mario.setPoints(mario.getPoints() + Integer.valueOf(coin.getPoint()));
                coin.setPoints("0");
                if (!coin.gets) {
                    mario.setCoins(mario.getCoins() + 1);
                    coin.gets = true;
                }
            }
            if (prize instanceof FireFlower) {
                FireFlower.setRevealed(false);
            }
            if (prize instanceof OneUpMushroom) {
                OneUpMushroom.setRevealed(false);
            }
            if (prize instanceof SuperMushroom) {
                SuperMushroom.setRevealed(false);
            }
            if (prize instanceof SuperStars) {
                SuperStars.setRevealed(false);
            }


            hited=true;
         
         
      }
   

   
}
