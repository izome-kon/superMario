package model;

import manager.SoundManager;
import model.brick.*;
import model.enemy.Enemy;
import model.hero.Fireball;
import model.hero.Mario;
import model.prize.Coin;
import model.prize.Prize;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import model.hero.MarioForm;
import model.prize.FireFlower;
import model.prize.OneUpMushroom;
import model.prize.SuperMushroom;
import model.prize.SuperStars;
import view.ImageStore;
public class Map {

    private double remainingTime;
    private Mario mario;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private ArrayList<Brick> forStyle = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Prize> revealedPrizes = new ArrayList<>();
    private ArrayList<Fireball> fireballs = new ArrayList<>();
    private EndPoint endPoint;
    private BufferedImage backgroundImage;

    private String path;
    ImageStore is ;
   SoundManager sounds = new SoundManager();

    public Map(double remainingTime, BufferedImage backgroundImage) {
        is = new ImageStore();
        is.load();
        this.backgroundImage = backgroundImage;
        this.remainingTime = remainingTime;

        }
    public Mario getMario() {
        return mario;
    }


    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void addBrick(Brick brick) {
        this.bricks.add(brick);
    }
    public void setEndPoint(EndPoint e){this.endPoint=e;}
    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void addForStyle(Brick brick) {
        this.forStyle.add(brick);
    }

    public void drawMap(Graphics2D g2){
        drawBackground(g2);
        drawForStyle(g2);
        drawPrizes(g2);
        drawEnemies(g2);
        drawFireballs(g2);
        drawMario(g2);
        drawBricks(g2);
        endPoint.drawJust(g2);
    }

    public EndPoint getEndPoint() {
        return endPoint;
    }

    private void drawForStyle(Graphics2D g2) {
        for(Brick b :forStyle)
            b.draw(g2);
    }

    private void drawFireballs(Graphics2D g2) {
        //drawFireBall
        for(Fireball f : fireballs){
            if(!f.isDie()){
                f.draw(g2);
            }else
                removeFireball(f);
            break;
        }
    }

    private void drawPrizes(Graphics2D g2) {

       for(Prize prize:revealedPrizes)
       {
         if(prize instanceof Coin){
             ((Coin)prize).draw(g2);
             CollisionCoins(prize);
         }
         if(prize instanceof SuperMushroom){
             CollisionSuperMushroom(prize);
         }
         if(prize instanceof OneUpMushroom){
             CollisionOneUpMushroom(prize);
         }
         if(prize instanceof FireFlower){
             CollisionFireFlower(prize);
         }
         //prize.updateLocation();
         CollisionPrize(prize);
       }

    }
    private void CollisionPrize(Prize e){

            if(e instanceof SuperMushroom)
        {

            boolean tuch = false;

            for(Brick b : bricks){

            if(((SuperMushroom)e).checkHits(b.getRec(),((SuperMushroom)e).getRec()).equals("up")){
                if(((SuperMushroom)e).getInterSctionRect().height>2)
                            ((SuperMushroom)e).setY(((SuperMushroom)e).getY()-((SuperMushroom)e).getInterSctionRect().height);
                    ((SuperMushroom)e).setVelY(0);
                    ((SuperMushroom)e).setFalling(false);
            }


              if(((SuperMushroom)e).checkHits(b.getRec(),((SuperMushroom)e).getRec()).equals("left")||((SuperMushroom)e).checkHits(b.getRec(),((SuperMushroom)e).getRec()).equals("right")){
                    tuch=true;
                   }
            }
            if(tuch){
                if(((SuperMushroom)e).isToRight())
                ((SuperMushroom)e).setToRight(false);
                else
                    ((SuperMushroom)e).setToRight(true);
            }


   }else if(e instanceof SuperStars)
        {

          boolean tuch = false;

            for(Brick b : bricks){

            if(((SuperStars)e).checkHits(b.getRec(),((SuperStars)e).getRec()).equals("up")){
                if(((SuperStars)e).getInterSctionRect().height>2)
                            ((SuperStars)e).setY(((SuperStars)e).getY()-((SuperStars)e).getInterSctionRect().height);
                    ((SuperStars)e).setVelY(0);
                    ((SuperStars)e).setFalling(false);
            }


              if(((SuperStars)e).checkHits(b.getRec(),((SuperStars)e).getRec()).equals("left")||((SuperStars)e).checkHits(b.getRec(),((SuperStars)e).getRec()).equals("right")){
                    tuch=true;
                   }
            }
            if(tuch){
                if(((SuperStars)e).isToRight())
                ((SuperStars)e).setToRight(false);
                else
                    ((SuperStars)e).setToRight(true);
            }


        }
            else  if(e instanceof OneUpMushroom)
        {
           boolean tuch = false;

            for(Brick b : bricks){

            if(((OneUpMushroom)e).checkHits(b.getRec(),((OneUpMushroom)e).getRec()).equals("up")){
                if(((OneUpMushroom)e).getInterSctionRect().height>2)
                            ((OneUpMushroom)e).setY(((OneUpMushroom)e).getY()-((OneUpMushroom)e).getInterSctionRect().height);
                    ((OneUpMushroom)e).setVelY(0);
                    ((OneUpMushroom)e).setFalling(false);
            }


              if(((OneUpMushroom)e).checkHits(b.getRec(),((OneUpMushroom)e).getRec()).equals("left")||((OneUpMushroom)e).checkHits(b.getRec(),((OneUpMushroom)e).getRec()).equals("right")){
                    tuch=true;
                   }
            }
            if(tuch){
                if(((OneUpMushroom)e).isToRight())
                ((OneUpMushroom)e).setToRight(false);
                else
                    ((OneUpMushroom)e).setToRight(true);
            }


        }
   }
    private void CollisionCoins(Prize p){

       if(((Coin)p).CollisionPrize(mario.getRec()))
       {
           if(((Coin)p).isShow())
               sounds.playCoin();
           ((Coin)p).setShow(false);
           mario.setPoints(mario.getPoints()+Integer.valueOf(((Coin)p).getPoint()));
           ((Coin)p).setPoints("0");
           if(!((Coin)p).gets){
           mario.setCoins(mario.getCoins()+1);
           ((Coin)p).gets=true;
           }

       }
   }
    private void CollisionSuperMushroom(Prize prize) {

       if(((SuperMushroom)prize).CollisionPrize(mario.getRec()))
       {
           sounds.playSuperMushroom();
           ((SuperMushroom)prize).setRevealed(true);
           mario.setPoints(mario.getPoints()+Integer.valueOf(((SuperMushroom)prize).getPoint()));
           ((SuperMushroom)prize).setPoints("0");
            ((SuperMushroom)prize).remove();
           mario.getMyForm().setForm(MarioForm.Forms.SUPER);
           mario.setY(mario.getY()-48);
       }
    }
    private void CollisionFireFlower(Prize prize) {
     if(((FireFlower)prize).CollisionPrize(mario.getRec()))
       {
           sounds.playFireFlower();
           ((FireFlower)prize).setRevealed(true);
           mario.setPoints(mario.getPoints()+Integer.valueOf(((FireFlower)prize).getPoint()));
           ((FireFlower)prize).setPoints("0");
            ((FireFlower)prize).remove();
           mario.getMyForm().setIsFire(true);
       }
    }
    private void CollisionOneUpMushroom(Prize prize) {

          if(((OneUpMushroom)prize).CollisionPrize(mario.getRec()))
       {    sounds.playOneUp();
           ((OneUpMushroom)prize).setRevealed(true);
           mario.setPoints(mario.getPoints()+Integer.valueOf(((OneUpMushroom)prize).getPoint()));
           ((OneUpMushroom)prize).setPoints("0");
            ((OneUpMushroom)prize).remove();
         if(!((OneUpMushroom)prize).gets){
           mario.setRemainingLives(mario.getRemainingLives()+1);
           ((OneUpMushroom)prize).gets=true;
       }
    }
    }
    private void drawBackground(Graphics2D g2){
        g2.drawImage(backgroundImage, 0, 0, null);
    }
    private void drawBricks(Graphics2D g2) {
       boolean u = true,d=true,l=true,r = true;

       for(Brick b : bricks){
       b.reveal(this);
       if(b!=null)
           b.draw(g2);
       collisionBrick(b, mario);
       if(mario.checkHits(b.getRec(),mario.getRec2()).equals("down")||mario.checkHits(b.getRec(),mario.getRec()).equals("down")){
          d = false;
        }
       if(mario.checkHits(b.getRec(),mario.getRec2()).equals("up")||mario.checkHits(b.getRec(),mario.getRec()).equals("up")){
          u = false;
        }
       if(mario.checkHits(b.getRec(),mario.getRec2()).equals("left")||mario.checkHits(b.getRec(),mario.getRec()).equals("left")){
          r = false;
        }
       if(mario.checkHits(b.getRec(),mario.getRec2()).equals("rigth")||mario.checkHits(b.getRec(),mario.getRec()).equals("right")){
          l = false;
        }
       }
       if(u){
       mario.setD5ra(false);
       }
       if(d){
       mario.setU5ra(false);
       }
       if(r){
       mario.setR5ra(false);
       }
       if(l){
       mario.setL5ra(false);
       }

    }
    private void drawEnemies(Graphics2D g2) {
        //drawEnemies
        for(Enemy e : enemies){
            e.draw(g2);
            fireDie(e);
            marioDie(e);
            if(e.isEdie()){
                enemies.remove(e);
                break;}
        }
        collisionEnemy();
    }
    private void drawMario(Graphics2D g2) {
       //drawMario
       mario.draw(g2);
       if(mario.getY()>=708&&mario.isCc()){
           mario.Die();
           mario.setX(mario.getX() + mario.getInterSctionRect().width);
           mario.setCc(false);
           mario.getRec().setLocation(0, 0);
           mario.setUp(true);
           mario.setMaxHiegt(mario.getY() - (63));
           mario.setVelY(-3);
           sounds.playMarioDies();
       }
    }

    public void addRevealedPrize(Prize prize) {
        revealedPrizes.add(prize);
    }
    public void addFireball(Fireball fireball) {
        fireballs.add(fireball);
    }

    public void removeFireball(Fireball object) {
        fireballs.remove(object);
    }

    public void removeEnemy(Enemy object) {
        enemies.remove(object);
    }

    public void removePrize(Prize object) {
        revealedPrizes.remove(object);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void updateTime(double passed){
        remainingTime = remainingTime - passed;
    }

    public boolean isTimeOver(){
        return remainingTime <= 0;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public void collisionEnemy(){
        for(Enemy e : enemies){
            if(e instanceof KoopaTroopa){
                KoopaTroopa k = (KoopaTroopa)e;
                if(k.isRm()){
                    for(Enemy s : enemies)
                    {
                        if(k.checkHits(s.getRec(), k.getRec()).equals("left")||k.checkHits(s.getRec(), k.getRec()).equals("right")){
                            e.getRec().setLocation(0, 0);
                            e.setCc(false);
                            break;
                        }
                    }
                }
            }
            boolean tuch = false;

            for(Brick b : bricks){
                if(e.checkHits(b.getRec(),e.getRec()).equals("up")){
                    if(e.getInterSctionRect().height>2)
                        e.setY(e.getY()-e.getInterSctionRect().height);
                    e.setVelY(0);
                    e.setFalling(false);
                }
                if(e.checkHits(b.getRec(),e.getRec()).equals("left")||e.checkHits(b.getRec(),e.getRec()).equals("right")){
                    tuch=true;
                }
            }
            if(tuch){
                if(e.isToRight())
                    e.setToRight(false);
                else
                    e.setToRight(true);
            }

        }
    }
    public void collisionBrick(Brick b,Mario mario){
        for(Fireball f : fireballs){
          if(f.checkHits(b.getRec(),f.getRec()).equals("left")||f.checkHits(b.getRec(),f.getRec()).equals("right")){
        f.setLive(500);
        }
            if(f.checkHits(b.getRec(),f.getRec()).equals("up")){
          f.setHit(true);
          f.setMaxHieght(f.getY()-30);
          f.setY(f.getY()-f.getInterSctionRect().height);
        }
       }
        if(mario.checkHits(b.getRec(),mario.getRec()).equals("up")){
            if(b instanceof Fire){
                mario.Die();
                mario.setCc(false);
                mario.getRec().setLocation(0, 0);
                mario.setUp(true);
                mario.setMaxHiegt(mario.getY() - (63));
                mario.setVelY(-3);
                sounds.playMarioDies();
            }else {
                mario.setD5ra(true);
                mario.setY(mario.getY() - mario.getInterSctionRect().height);
                mario.setJumping(false);
                mario.setFalling(false);
                mario.setVelY(0);
            }
        }

        if(mario.checkHits(b.getRec(),mario.getRec()).equals("down")){
          mario.setU5ra(true);
          mario.setY(mario.getY()+mario.getInterSctionRect().height);
          mario.setVelY(0);
          if (b instanceof OrdinaryBrick  )
          {
              ((OrdinaryBrick)b).HIT(mario.getMyForm().getForm());
          }else if (b instanceof SurpriseBrick ){
              SurpriseBrick brick = (SurpriseBrick)b ;
              brick.HIT(MarioForm.Forms.MARIO,mario);
              revealedPrizes.add(brick.getPrize());
              if(brick.getPrize()instanceof Coin)
                  sounds.playCoin();
          }
        }
          if(mario.checkHits(b.getRec(),mario.getRec()).equals("left")){
          mario.setR5ra(true);
          mario.setX(mario.getX()-mario.getInterSctionRect().width);
        }

          if(mario.checkHits(b.getRec(),mario.getRec()).equals("right")){
          mario.setL5ra(true);
          mario.setX(mario.getX()+mario.getInterSctionRect().width);
        }

    }
    public void marioDie(Enemy e) {
        if (mario.getRec().intersects(e.getRec()) && mario.issuper()) {
            e.getRec().setLocation(0, 0);
            e.setCc(false);
            if (e instanceof Goomba) {
                Goomba g = (Goomba) e;
                g.firedie();
            } else if (e instanceof KoopaTroopa) {
                KoopaTroopa k = (KoopaTroopa) e;

            }
        } else {
            if (mario.checkHits(e.getRec(), mario.getRec()).equals("up")) {
                mario.setY(mario.getY() - mario.getRec().intersection(e.getRec()).height-3);
                mario.setMaxHiegt(mario.getY() - (60));
                mario.setJumping(true);
                mario.setVelY(-10);
                if (e instanceof Goomba) {
                    e.setCc(false);
                    e.getRec().setLocation(0, 0);
                    Goomba g = (Goomba) e;
                    g.mariodie();
                }else {
                    KoopaTroopa k = (KoopaTroopa) e;
                    k.turnStatu();
                }
            }else if(e.isR()&&mario.getRec().intersects(e.getRec())){
                mario.setY(mario.getY() - mario.getRec().intersection(e.getRec()).height-3);
                mario.setMaxHiegt(mario.getY() - (60));
                mario.setJumping(true);
                mario.setVelY(-10);
                KoopaTroopa k = (KoopaTroopa) e;
                k.turnStatu();
            }
            else if (mario.checkHits(e.getRec(), mario.getRec()).equals("down")&&mario.getMyForm().getForm()==MarioForm.Forms.MARIO) {
                mario.setY(mario.getY() + mario.getInterSctionRect().height);
                mario.Die();
                mario.setCc(false);
                mario.getRec().setLocation(0, 0);
                mario.setUp(true);
                mario.setMaxHiegt(mario.getY() - (63));
                mario.setVelY(-3);
            } else if (mario.checkHits(e.getRec(), mario.getRec()).equals("left")&&mario.getMyForm().getForm()==MarioForm.Forms.MARIO) {
                mario.Die();
                mario.setX(mario.getX() - mario.getInterSctionRect().width);
                mario.setCc(false);
                mario.getRec().setLocation(0, 0);
                mario.setUp(true);
                mario.setMaxHiegt(mario.getY() - (63));
                mario.setVelY(-3);
            } else if (mario.checkHits(e.getRec(), mario.getRec()).equals("right")&&mario.getMyForm().getForm()==MarioForm.Forms.MARIO) {
                mario.Die();
                mario.setX(mario.getX() + mario.getInterSctionRect().width);
                mario.setCc(false);
                mario.getRec().setLocation(0, 0);
                mario.setUp(true);
                mario.setMaxHiegt(mario.getY() - (63));
                mario.setVelY(-3);
            }else if(mario.getRec().intersects(e.getRec())){
                e.setCc(false);
                e.getRec().setLocation(0, 0);
                if (e instanceof Goomba) {
                    Goomba g = (Goomba) e;
                    g.mariodie();
                }
                mario.getMyForm().setForm(MarioForm.Forms.MARIO);
            }
        }
    }
    public void fireDie(Enemy e){
        for(Fireball f : fireballs){
            if(f.getRec().intersects(e.getRec())){
                if(e.isEdie())
                    enemies.remove(e);
                f.setLive(500);
                e.getRec().setLocation(0, 0);
                e.setCc(false);
                if(e instanceof Goomba){
                    Goomba g =(Goomba)e;
                    g.firedie();
                }
            }
        }
    }


}
