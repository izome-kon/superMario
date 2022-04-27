package view;

import JDBC.User;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MapSelection {

    private ArrayList<String> maps = new ArrayList<>();
    public static MapSelectionItem[] mapSelectionItems;
    private BufferedImage img ;
    private ImageLoader il;
    private User user;
    private Font font ;
    public MapSelection(){
        getMaps();
        Font();
        il=new ImageLoader();
        img = il.loadImage("selectMap-screen.png");
       // this.mapSelectionItems = createItems(this.maps);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawImage(img,0,0,null);
        if(mapSelectionItems == null){
            System.out.println(1);
            return;
        }
        String title = "Select a Map";
        int x_location = (1280 - g.getFontMetrics().stringWidth(title))/2;
        g.setColor(new Color(147, 124, 19));
        g.drawString(title, x_location+240, 150);

        g.setColor(Color.WHITE);

        for(MapSelectionItem item : mapSelectionItems){

            int width = g.getFontMetrics().stringWidth(item.getName().split("[.]")[0]);
            int height = g.getFontMetrics().getHeight();
            item.setDimension( new Dimension(width, height));
            item.setLocation( new Point((1280-width)/2, item.getLocation().y));
            g.drawString(item.getName().split("[.]")[0], item.getLocation().x+300, item.getLocation().y);
        }
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(18F);
        g.setFont(newFont);
        g.drawString(user.getUserName(),263,100);
        g.drawString(user.getScore(),341,147);
    }

    public void Font(){
        try{
            InputStream in = getClass().getResourceAsStream("/media/font/mario-font.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, in);
        }catch(FontFormatException | IOException e)
        {
            font = new Font("Verdana",Font.PLAIN,12);
            e.printStackTrace();
        }

    }

    private void getMaps(){
        //TODO: read from file
        maps.add("Map 1.png");
        maps.add("Map 2.png");
        maps.add("Map 3.png");
        maps.add("Map 4.png");
    }


    public String selectMap(int index){
        if(index < mapSelectionItems.length && index > -1)
            return mapSelectionItems[index].getName();
        return null;
    }

    public int changeSelectedMap(int index, boolean up) {
        if(up){
            if(index <= 0)
                return mapSelectionItems.length - 1;
            else
                return index - 1;
        }
        else{
            if(index >= mapSelectionItems.length - 1)
                return 0;
            else
                return index + 1;
        }
    }
}
