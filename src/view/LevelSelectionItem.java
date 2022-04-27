package view;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelSelectionItem {

    private BufferedImage image;
    private String name;
    private Point location;
    private Dimension dimension;
    private String lvlPath;
    public static int numFolderMap;
    public LevelSelectionItem(String map,String lvl, Point location){
        this.location = location;
        this.name = lvl;
        numFolderMap=Integer.parseInt(map);

        ImageLoader loader = new ImageLoader();
        lvlPath="Map "+numFolderMap+"/"+ lvl;
        this.image = loader.loadImage("/maps/Map "+map+"/"+ lvl);

        this.dimension = new Dimension();
    }

    public String getLvlPath() {
        return lvlPath;
    }

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
