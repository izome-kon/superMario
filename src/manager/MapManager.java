package manager;

import model.GameObject;
import model.Map;
import model.brick.Brick;
import model.brick.OrdinaryBrick;
import model.enemy.Enemy;
import model.hero.Fireball;
import model.hero.Mario;
import model.prize.BoostItem;
import model.prize.Coin;
import model.prize.Prize;
import view.ImageLoader;
import view.LevelSelection;

import java.awt.*;
import java.util.ArrayList;

public class MapManager {

    private Map map;
    private Point camLocation;

    public MapManager() {}

    public void addfire(Fireball f){
    map.addFireball(f);
    }
    
    public void resetCurrentMap(GameEngine engine,Mario mario) {
        engine.resetCamera();
        createMap(engine.getImageLoader(), engine.getPath()
        );
        map.setMario(mario);
    }

   public boolean createMap(ImageLoader loader, String path) {
        MapCreator mapCreator =null;
        if(LevelSelection.getMapNum()==1){
            mapCreator = new MapCreator(loader,2);
        }else if(LevelSelection.getMapNum()==0){
        mapCreator = new MapCreator(loader,1);
        }else
        mapCreator = new MapCreator(loader,3);

        map = mapCreator.createMap("/maps/"+path, 100);

        return map != null;
    }

    public Mario getMario() {
        return map.getMario();
    }


    public boolean isGameOver() {
        return getMario().getRemainingLives() == 0 || map.isTimeOver();
    }

   public int getScore() {
        return map.getMario().getPoints();
    }

    public int EndPoint(){
        return (int)map.getEndPoint().getX();
    }

    public int getRemainingLives() {
        return map.getMario().getRemainingLives();
    }

    public int getCoins() {
        return map.getMario().getCoins();
    }

    public void drawMap(Graphics2D g2) {
        map.drawMap(g2);
    }

    public boolean endLevel(){
        return true;
    }

    public void updateTime(){
        if(map != null)
            map.updateTime(1);
    }

    public int getRemainingTime() {
        return (int)map.getRemainingTime();
    }
    public void setCameraLocation(Point p){
        this.camLocation = p ;
    }
}
