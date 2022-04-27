package view;

import JDBC.User;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class LevelSelection {

    private ArrayList<String> levels = new ArrayList<>();
    public static LevelSelectionItem[] lvlSelectionItems;
    private BufferedImage img ;
    private ImageLoader il;
    private User user;
    public static int indexSelection=0;
    public static int mapNum;

    public static int getMapNum() {
        return mapNum;
    }

    public LevelSelection(int mapNum){
        getMaps();
        il=new ImageLoader();
        img = il.loadImage("selectLvl-screen.png");
        this.mapNum=mapNum;
        this.lvlSelectionItems = createItems(this.levels);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawImage(img,0,0,null);
        if(lvlSelectionItems == null){
            System.out.println(1);
            return;
        }
        String title = "Select a Level";
        int x_location = (1280 - g.getFontMetrics().stringWidth(title))/2;
        g.setColor(Color.WHITE);
        g.drawString(title, x_location, 100);

        g.setColor(Color.WHITE);

        for(LevelSelectionItem item : lvlSelectionItems){

            int width = g.getFontMetrics().stringWidth(item.getName().split("[.]")[0]);
            int height = g.getFontMetrics().getHeight();
            item.setDimension( new Dimension(width, height));
            item.setLocation( new Point((1280-width)/2, item.getLocation().y));
            g.drawString(item.getName().split("[.]")[0], item.getLocation().x, item.getLocation().y-60);
        }

    }


    private void getMaps(){
        //TODO: read from file
        levels.add("Lvl 1.png");
        levels.add("Lvl 2.png");
        levels.add("Lvl 3.png");
        levels.add("Lvl 4.png");
        levels.add("Lvl 5.png");
    }


    public int changeSelectedMap(int index, boolean up) {
        if(up){
            if(index <= 0)
                return lvlSelectionItems.length - 1;
            else
                return index - 1;
        }
        else{
            if(index >= lvlSelectionItems.length - 1)
                return 0;
            else
                return index + 1;
        }
    }
    private LevelSelectionItem[] createItems(ArrayList<String> lvls){
        if(lvls == null)
            return null;
        int defaultGridSize = 100;
        LevelSelectionItem[] items = new LevelSelectionItem[lvls.size()];
        for (int i = 0; i < items.length; i++) {
            Point location = new Point(0, (i+1)*defaultGridSize+200);
            items[i] = new LevelSelectionItem(String.valueOf(mapNum+1),lvls.get(i),location);
        }

        return items;
    }

    public static void moveUp(){
        indexSelection = (indexSelection-1)%5;
    }
    public static void moveDown(){
        indexSelection=(indexSelection+1)%5;
    }

    public int getIndexSelection() {
        return indexSelection;
    }
}
