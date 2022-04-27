/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Just Smile
 */
public class Account {
    
    public enum Select{LOGIN,SIGNUP}
     Select select  =Select.LOGIN;

    public Select getSelect() {
        return select;
    }
   
    public void draw(Graphics2D g2){
        if(select==Select.LOGIN){
         String title = "LOGIN";
        int x_location = (300);
        g2.setColor(Color.YELLOW);
        g2.drawString(title, x_location, 634);
         g2.setColor(Color.white);
         title = "SIGNUP";
         x_location = (350+g2.getFontMetrics().stringWidth(title));
        g2.drawString(title, x_location, 634);
        }else
        {
        
            String title = "LOGIN";
        int x_location = (300);
        g2.setColor(Color.white);
        g2.drawString(title, x_location, 634);
         g2.setColor(Color.YELLOW);
         title = "SIGNUP";
         x_location = (350+g2.getFontMetrics().stringWidth(title));
        g2.drawString(title, x_location, 634);
        }
        
    }
    
    public void change(){
        if(select == Select.LOGIN)
            select = Select.SIGNUP;
        else
            select = Select.LOGIN;
    }
}
