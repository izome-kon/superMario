package JDBC;

import view.MapSelection;
import view.MapSelectionItem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class User {
    private static String userName;
    private static String score;
    private String level;
    private String password;
    private String lastMap;

    public static String getUserName() {
        return userName;
    }

    public static String getScore() {
        return score;
    }

    public static ArrayList<String> maps = new ArrayList<>();

    JDBC jdbc = new JDBC();
    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
        jdbc.putData("CREATE TABLE IF NOT EXISTS \"users\" (\n" +
                "\t\"ID\"\tINTEGER,\n" +
                "\t\"UserName\"\tTEXT,\n" +
                "\t\"Password\"\tTEXT,\n" +
                "\t\"Score\"\tTEXT,\n" +
                "\t\"Level\"\tTEXT,\n" +
                "\t\"Map\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"ID\")\n" +
                ");");
    }

    public boolean load(){
        boolean found = false;
        try {
            ResultSet rs = jdbc.getData("select * from users");
            String username,password;
            while (rs.next()){
                username=rs.getString("UserName");
                password=rs.getString("Password");

                if(username.equals(this.userName)&&password.equals(this.password))
                {   found = true;
                    this.score=rs.getString("Score");
                    this.level=rs.getString("Level");
                    this.lastMap=rs.getString("Map");
                    System.out.println("lastMap"+lastMap);
                    loadMaps();
                    MapSelection.mapSelectionItems=createItems(maps);
                    break;
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    public void printData(){
        System.out.println("userName : "+userName);
        System.out.println("Password : "+password);
        System.out.println("Level : "+level);
        System.out.println("Score : "+score);
    }

    public static ArrayList<String> getMaps() {
        return maps;
    }

    public void Save(){
        try {

            Connection con = jdbc.getConnection();
            PreparedStatement ps =con.prepareStatement("update users set Score='"+score+"',Level='"+level+"' where UserName='"+userName+"'");

            ps.execute();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMaps(){
        for(int i = 1 ; i <= Integer.parseInt(lastMap);i++)
            maps.add("Map "+i+".png");
    }

    private MapSelectionItem[] createItems(ArrayList<String> maps){
        if(maps == null)
            return null;
        int defaultGridSize = 100;
        MapSelectionItem[] items = new MapSelectionItem[maps.size()];
        for (int i = 0; i < items.length; i++) {
            Point location = new Point(0, (i+1)*defaultGridSize+200);
            items[i] = new MapSelectionItem(maps.get(i), location);
        }

        return items;
    }

    public void createNewUser(){
        jdbc.putData("insert into users (UserName,Password,Score,Level,Map)values('"+userName+"','"+password+"','0','1','1')");
    }
}
