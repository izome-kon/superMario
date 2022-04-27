/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Reem
 */
public class Points {
     private String score;
     private Point position;
     private Font points;
     private int count = 0;
     public Points(String score,int x,int y){
         this.score = score;
         position = new Point();
         
         position.x = x;
         position.y = y;
         Font();
     }
     public void Font(){
      try{
            InputStream in = getClass().getResourceAsStream("/media/font/mario-font.ttf");
            points = Font.createFont(Font.TRUETYPE_FONT, in);
        }catch(FontFormatException | IOException e)
        {
            points = new Font("Verdana",Font.PLAIN,12);
            e.printStackTrace(); 
        }

  }
     public void draw(Graphics g)
     {   if(count<20)
        {
         g.setFont(points.deriveFont(25f));
         g.setColor(Color.white);
         g.drawString(score, (int)position.x-10, (int)position.y-16);
        }  
       count++;
     }
     
}
