/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.image.BufferedImage;


/**
 *
 * @author user
 */
public class ImageStore {
    //////////////--> up <--//////////////
    public static BufferedImage [] GOMBA;
    public static BufferedImage [] KOOPA;
    public static BufferedImage [] KOOPA2;
    public static BufferedImage [] MASHRUM;
    public static BufferedImage [] COIN;
    public static BufferedImage [] STAR;
    public static BufferedImage [] FLOWER;
    public static BufferedImage [] BRICKS;
    public static BufferedImage [] PIPES;
    public static BufferedImage P ;
    public static BufferedImage End;
    public static BufferedImage  BREAK_BRICKS;
    public static BufferedImage [][] MARIO;
    public static BufferedImage [][] FIREMARIO;
    public static BufferedImage [][] SUPERMARIO;
    public static BufferedImage [] FIREBALL;
    public static BufferedImage [] GreenBrick;    
    public static BufferedImage  CORAL;
    public static BufferedImage BRIDGE;
    public static BufferedImage Fire[];
     //////////////--> under <--//////////////
    public static BufferedImage [] UnderGOMBA;
    public static BufferedImage [] UnderKOOPA;
    public static BufferedImage [] UnderBRICKS;
    
     //////////////--> other <--//////////////
     public static BufferedImage elevator;
    public ImageStore() {
        GOMBA = new BufferedImage[3];
        KOOPA = new BufferedImage[6];
        KOOPA2 = new BufferedImage[6];
        MASHRUM = new BufferedImage[6];
        COIN = new BufferedImage[2];
        STAR = new BufferedImage[4];
        FLOWER = new BufferedImage[4];
        PIPES = new BufferedImage[2];
        BRICKS = new BufferedImage[10];
        
        FIREMARIO = new BufferedImage[2][7];
        MARIO = new BufferedImage[2][7];
        SUPERMARIO = new BufferedImage[2][14];
         FIREBALL = new BufferedImage[7];
          GreenBrick = new BufferedImage[3];

        Fire = new BufferedImage[2];
        //under
        UnderBRICKS = new BufferedImage[7];
        UnderKOOPA = new BufferedImage[6];
        UnderGOMBA= new BufferedImage[3];
    }
    public static void load(){
    ImageLoader il = new ImageLoader();
    BufferedImage all = il.loadImage("enimy-forms.png") ;
    for(int a=0;a<3;a++){
    GOMBA[a]= il.getSubImage(all, a*16, 0, 16, 24);
    }
    
    for(int a=0;a<3;a++){
    UnderGOMBA[a]= il.getSubImage(all, a*16, 24, 16, 24);
    }
    
    for(int a=6;a<12;a++){
    KOOPA[a-6]= il.getSubImage(all, a*16, 0, 16, 24);
    }
    for(int a=6;a<12;a++){
    KOOPA2[a-6]= il.getSubImage(all, a*16, 24*2, 16, 24);
    }
     for(int a=6;a<12;a++){
    UnderKOOPA[a-6]= il.getSubImage(all, a*16, 24, 16, 24);
    }
    
    all = il.loadImage("Prize-forms.png");
    for(int a=0;a<3;a++){
    MASHRUM[a]= il.getSubImage(all, a*16, 0, 16, 16);
    MASHRUM[a+3]= il.getSubImage(all, a*16, 16, 16, 16);
    }
    for(int a=0;a<2;a++){
    COIN[a]= il.getSubImage(all, 0, 16*(a+6), 15, 15);
    }
    for(int a=0;a<4;a++){
    STAR[a]= il.getSubImage(all, a*16, 16*(3), 16, 16);
    }
    for(int a=0;a<4;a++){
    FLOWER[a]= il.getSubImage(all, a*16, 16*(2), 16, 16);
    }
    all = il.loadImage("brick-forms.png");
    for(int a=0;a<7;a++){
    BRICKS[a]= il.getSubImage(all, a*16, 0, 16, 16);
    }
    BREAK_BRICKS= il.getSubImage(all,32,0 , 8, 8);  
    all = il.loadImage("brick-forms-under.png");
    for(int a=0;a<7;a++){
        UnderBRICKS[a]= il.getSubImage(all, a*16, 0, 16, 16);
    }
           
    all = il.loadImage("pipe.png");
    for(int a=0;a<2;a++){
    PIPES[a]= il.getSubImage(all, a*32, 0, 32, 32);
    }
    all = il .loadImage("mario-forms.png");
    for(int a=0;a<7;a++){
    MARIO[0][a]= il.getSubImage(all, a*16, 2*16, 16, 16);
    MARIO[1][a]= il.getSubImage(all, a*16, 8*16, 16, 16);
    }
    for(int a=0;a<7;a++){
    FIREMARIO[0][a]= il.getSubImage(all, a*16, 0, 16, 32);
    FIREMARIO[1][a]= il.getSubImage(all, a*16, 6*16, 16, 32);
    }
    int b = 0;
    for(int a=0;a<14;a+=2){
    SUPERMARIO[0][a]=MARIO[0][b] ;
    SUPERMARIO[0][a+1]= MARIO[1][b];
    SUPERMARIO[1][a]= FIREMARIO[0][b];
    SUPERMARIO[1][a+1]= FIREMARIO[1][b];
    b++;
    }
    //other 
    
     all = il .loadImage("evelator.png");
     elevator = all;
     
     all = il .loadImage("fireBall.png");
    FIREBALL[0]= il.getSubImage(all, 0, 0, 8, 8);
    FIREBALL[1]= il.getSubImage(all, 8, 0, 8, 8);
    FIREBALL[2]= il.getSubImage(all, 0, 8, 8, 8);
    FIREBALL[3]= il.getSubImage(all, 8, 8, 8, 8);
    all = il .loadImage("pew.png");
    FIREBALL[4]= il.getSubImage(all, 0, 4, 8, 8);
    FIREBALL[5]= il.getSubImage(all, 13, 2, 14, 12);
    FIREBALL[6]= il.getSubImage(all, 28, 0, 16, 16);
    
    all= il.loadImage("brick-forms-more.png");
     for (int i=0;i<3;i++)
     {
      GreenBrick[i]= il.getSubImage(all, i*16, 8*16, 16, 16);
     }
     
    BRICKS[7] = il.getSubImage(all, 0, 16, 16, 16);
    BRICKS[8] = il.getSubImage(all, 13*16, 4*16, 16, 16);
    
    CORAL = il.getSubImage(all, 6*16, 18*16, 16, 16);
     all= il.loadImage("briks.png");
    
    BRICKS[9] = il.getSubImage(all, 1*16, 7*16, 16, 16);
    BRIDGE = il.getSubImage(all,4*16,24*16,16,16);
    Fire[0] = il.getSubImage(all,3*16,24*16,16,16);
    Fire[1] = il.getSubImage(all,3*16,25*16,16,16);

    End = il.loadImage("end.png");

    }

}
