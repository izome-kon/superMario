/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import manager.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Just Smile
 */
public class Login {

    public enum Select{userName,password,btn}
     Select select  =Select.userName;

     private static String userName,password,passwordstr;

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public Login(){
         userName="";
         password="";
         passwordstr="";

     }
    public Select getSelect() {
        return select;
    }
    
   
    public void draw(Graphics2D g2){
        if(select==Select.userName){
         String title = "USERNAME : ";
        int x_location = (398);
        g2.setColor(new Color(223, 73, 56));
        g2.drawString(title, x_location, 408);
        g2.drawLine(600, 408, 830, 408);
         g2.setColor(Color.BLACK);
         title = "PASSWORD :";
         x_location = (398);
        g2.drawString(title, x_location, 500);
        
        g2.drawLine(600, 500, 830, 500);
        if(userName!=null)
        g2.drawString(userName, 600, 405);
        if(passwordstr!=null)
        g2.drawString(passwordstr, 600, 500);
        }
        else if(select==Select.password){
         String title = "USERNAME : ";
        int x_location = (398);
        g2.setColor(Color.BLACK);
        g2.drawString(title, x_location, 408);
        g2.drawLine(600, 408, 830, 408);
          g2.setColor(new Color(223, 73, 56));
         title = "PASSWORD :";
         x_location = (398);
        g2.drawString(title, x_location, 500);
        g2.drawLine(600, 500, 830, 500);
        g2.setColor(Color.BLACK);
        if(userName!=null)
        g2.drawString(userName, 600, 405);
        if(passwordstr!=null)
        g2.drawString(passwordstr, 600, 500);
        }else {
                 String title = "USERNAME : ";
        int x_location = (398);
        g2.setColor(Color.BLACK);
        g2.drawString(title, x_location, 408);
        g2.drawLine(600, 408, 830, 408);
          g2.setColor(Color.BLACK);
         title = "PASSWORD :";
         x_location = (398);
        g2.drawString(title, x_location, 500);
        g2.drawLine(600, 500, 830, 500);
        g2.setColor(Color.BLACK);
        if(userName!=null)
        g2.drawString(userName, 600, 405);
        if(passwordstr!=null)
        g2.drawString(passwordstr, 600, 500);
        g2.setColor(new Color(223, 73, 56));
       
        
        }
         g2.drawString("LOGIN", 580, 600);
    }
    
    public void changeDown(){
        if(select == Select.userName)
            select = Select.password;
        else if(select == Select.password)
            select = Select.btn;
        System.err.println(select);
      
    }
    public void changeUP(){
        if(select == Select.btn)
            select = Select.password;
        else if(select == Select.password)
            select = Select.userName;
        System.err.println(select);
    }
    
    public void write(char s){
        if(select==Select.userName&&userName.length()<=11){
            userName+=s;
        }else if(select==Select.password&&password.length()<=11){
            password+=s;
            passwordstr+="*";
        }
    }
    public void remove(){
        if(select==Select.userName&&userName.length()!=0){
         userName = userName.substring(0, userName.length()-1);
        }else if(select==Select.password&&password.length()!=0){
           password = password.substring(0, password.length()-1);
           passwordstr = passwordstr.substring(0,passwordstr.length()-1);
        }
    }
}
