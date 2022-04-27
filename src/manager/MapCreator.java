package manager;

import model.EndPoint;
import model.brick.*;
import model.prize.*;
import view.ImageLoader;
import model.Map;
import model.enemy.Enemy;
import model.enemy.Goomba;
import model.enemy.KoopaTroopa;
import model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;
import model.enemy.statu;

public class MapCreator {

    private ImageLoader imageLoader;

    private BufferedImage backgroundImage;

     MapCreator(ImageLoader imageLoader,int BGnum) {
        this.imageLoader = imageLoader;
        if(BGnum==1)
        this.backgroundImage = imageLoader.loadImage("/background.png");
        else if(BGnum==2)
        this.backgroundImage = imageLoader.loadImage("/backgroundUnder.png");
        else if(BGnum==3)
             this.backgroundImage = imageLoader.loadImage("/background-sky.png");
    }

    Map createMap(String mapPath, double timeLimit) {
         System.err.println(mapPath);
         BufferedImage mapImage = imageLoader.loadImage(mapPath);
        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }

        Map createdMap = new Map(timeLimit, backgroundImage);
        String[] paths = mapPath.split("/");
        createdMap.setPath(paths[paths.length-1]);
        System.out.println( paths[paths.length-1]);
        System.out.println(paths);
        int pixelMultiplier = 48;


        int mario = new Color(173,173 ,173 ).getRGB();
        int ordinaryBrick = new Color(129, 53, 255).getRGB();
        
        int CoinBrick = new Color(255, 238, 53).getRGB();
        int Coin = new Color(255, 159, 0).getRGB();
        int SuperMushroomBrick = new Color(255, 53, 139).getRGB();
        int OneUpMushroomBrick = new Color(53, 243, 255).getRGB();
        int fireFlowerBrick = new Color(184, 255, 145).getRGB();
        
        int groundBrick = new Color(154, 72, 29).getRGB();
        int pipe = new Color(75, 157, 91).getRGB();
        int block = new Color(94,39,11).getRGB();
        int pipe2 = new Color(54,150,97).getRGB();
        int pipe3 = new Color(57,183,2).getRGB();
        int goomba = new Color(255, 53, 53).getRGB();
        int koopa = new Color(10, 97, 30).getRGB();
        int brownBrick = new Color(208, 76, 31).getRGB();
        int RGreenBrick = new Color(12, 182, 20).getRGB();
        int CGreenBrick = new Color(20, 230, 30).getRGB();
        int LGreenBrick = new Color(11, 169, 19).getRGB();
        int bridge = new Color(79, 65, 48).getRGB();
        int whiteBrick = new Color(203, 203, 203).getRGB();
        int fire = new Color(193, 5, 5).getRGB();
        int upfire = new Color(182, 77, 77).getRGB();
        ///->under<-///
        int underBlock = new Color(2,12,60).getRGB();
        int underKoopa = new Color(154, 18, 18).getRGB();
        int underGoomba = new Color(255, 0, 0).getRGB();
        int underGroundBrick=new Color(3, 18, 95).getRGB();
        int underOrdinaryBrick = new Color(0, 26, 155).getRGB();
        int elevatorV =  new Color(167, 145, 56).getRGB();
        int elevatorH =  new Color(228, 230, 65).getRGB();
        int end = new Color(224, 53, 255).getRGB();
        
        
          for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {

                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x*pixelMultiplier;
                int yLocation = y*pixelMultiplier;
             if (currentPixel == ordinaryBrick) {
                    Brick brick = new OrdinaryBrick(xLocation, yLocation,false);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == CoinBrick) {
                    Prize prize = new Coin(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation,prize,false);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == SuperMushroomBrick) {
                    Prize prize = (Prize) new SuperMushroom(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation,prize,false);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == OneUpMushroomBrick) {
                    Prize prize = (Prize) new OneUpMushroom(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation,prize,false);
                    createdMap.addBrick(brick);
                }
                 else if (currentPixel == fireFlowerBrick) {
                    Prize prize = (Prize) new FireFlower(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation,prize,false);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == pipe) {
                   Pipe p = new Pipe(xLocation, yLocation-48,false);
                   createdMap.addBrick(p);
                }
                
                else if (currentPixel == pipe2) {
                   Pipe2 p2 = new Pipe2(xLocation, yLocation,false);
                   PipeCont pc = new PipeCont(xLocation, yLocation,false);
                   createdMap.addBrick(p2);
                   createdMap.addBrick(pc);
                }
                
                else if (currentPixel == pipe3) {
                   Pipe3 p3 = new Pipe3(xLocation, yLocation,false);
                   PipeCont pc = new PipeCont(xLocation, yLocation-48,false);
                   PipeCont pc1 = new PipeCont(xLocation, yLocation,false);
                   createdMap.addBrick(p3);
                   createdMap.addBrick(pc);
                   createdMap.addBrick(pc1);
                }
                else if (currentPixel == groundBrick) {
                 Brick brick = new GroundBrick(xLocation, yLocation,false);
                    createdMap.addBrick(brick);

                }
                
                else if (currentPixel == block) {
                    Brick brick = new Block(xLocation, yLocation,false);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == goomba) {
                    Enemy enemy = new Goomba(xLocation, yLocation,false);
                   
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == koopa) {
                    Enemy enemy = new KoopaTroopa(xLocation, yLocation,statu.RUN,1);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == mario) {
                    Mario marioObject = new Mario(xLocation, yLocation,0);
                    if(GameEngine.tmpMarioLocations==null)
                        GameEngine.tmpMarioLocations= new Point(xLocation,yLocation);
                    createdMap.setMario(marioObject);
                }
                
                else if (currentPixel == underGoomba) {
                    Enemy enemy = new Goomba(xLocation, yLocation,true);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == underGroundBrick) {
                     Brick brick = new GroundBrick(xLocation, yLocation,true);
                     createdMap.addBrick(brick);
                }
                else if (currentPixel == underOrdinaryBrick) {
                     Brick brick = new OrdinaryBrick(xLocation, yLocation,true);
                     createdMap.addBrick(brick);
                }
                 else if (currentPixel == underBlock) {
                     Brick brick = new Block(xLocation, yLocation,true);
                     createdMap.addBrick(brick);
                }
                 else if (currentPixel == elevatorV) {
                     Brick brick = new ElevatorV(xLocation, yLocation,false);
                     createdMap.addBrick(brick);
                }
             else if (currentPixel == elevatorH) {
                 Brick brick = new ElevatorH(xLocation, yLocation,4*48,4*48,2,true);
                 createdMap.addBrick(brick);
             }
                 else if (currentPixel == Coin) {
                     Prize coin = new Coin(xLocation, yLocation);
                     createdMap.addRevealedPrize(coin);
                }
             else if (currentPixel == brownBrick) {
                 Brick brown= new BrownBrick(xLocation, yLocation);
                 createdMap.addForStyle(brown);
             }
             else if (currentPixel == RGreenBrick) {
                 Brick RGBrick= new GreenBrick(xLocation, yLocation,1);
                 createdMap.addBrick(RGBrick);
             }
             else if (currentPixel == CGreenBrick) {
                 Brick CGBrick= new GreenBrick(xLocation, yLocation,2);
                 createdMap.addBrick(CGBrick);
             }
             else if (currentPixel == LGreenBrick) {
                 Brick LGBrick= new GreenBrick(xLocation, yLocation,3);
                 createdMap.addBrick(LGBrick);
             }
             else if (currentPixel == upfire) {
                 Brick brick= new Fire(xLocation, yLocation,true);
                 createdMap.addBrick(brick);
             }
             else if (currentPixel == fire) {
                 Brick brick= new Fire(xLocation, yLocation,false);
                 createdMap.addBrick(brick);
             }
             else if (currentPixel == bridge) {
                 Brick brick= new Bridge(xLocation, yLocation);
                 createdMap.addBrick(brick);
             }
             else if (currentPixel == whiteBrick) {
                 Brick brick= new WhiteBrick(xLocation, yLocation);
                 createdMap.addBrick(brick);
             }

             else if(currentPixel == end){
                    EndPoint endPoint= new EndPoint(xLocation+24, yLocation);
                    createdMap.setEndPoint(endPoint);
                }
            }
            
        }

        System.out.println("Map is created..");
        return createdMap;
    }


}
