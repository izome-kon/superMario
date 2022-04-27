package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    private double x, y;
    private double velX, velY;
    private Rectangle interSctionRect;
    private boolean cc; 

    private BufferedImage style;

    private double gravityAcc;

    private boolean falling, jumping;
    
    private boolean toRight;
    private Rectangle rec,rec2;
    
      private  int WIDTH;
     private  int HIEGHT;


    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    public Rectangle getRec() {
        return rec;
    }
    
    public Rectangle getRec2() {
        return rec2;
    }
 

    public boolean isToRight() {
        return toRight;
    }

    public Rectangle getInterSctionRect() {
        return interSctionRect;
    }
    
    public GameObject(double x, double y){
        setLocation(x, y);
        setVelX(0);
        setVelY(0);
        setGravityAcc(0.9);
        jumping = false;
        falling = true;
        toRight = true;
        rec = new Rectangle();
        rec2=new Rectangle();
        cc = true;
    }

    public void setCc(boolean cc) {
        this.cc = cc;
    }

    public void setToRight(boolean toRight) {
        if(toRight)
            RefR();
        else
            RefL();
        this.toRight = toRight;
    }

    public void draw(Graphics g) {
        BufferedImage style = getStyle();
        
        if(style != null){
            WIDTH=style.getWidth()*3;
            HIEGHT=style.getHeight()*3;
            
            if(toRight){
          
            g.drawImage(style, (int)x ,(int)y, WIDTH, HIEGHT,null);
            if(cc)
            rec.setBounds((int)x ,(int)y, WIDTH, HIEGHT);
            rec2.setBounds((int)x-2 ,(int)y-4, (WIDTH)+4, (HIEGHT)+6);
            }
            else{
         
            g.drawImage(style, (int)x ,(int)y, -WIDTH, HIEGHT,null);
            if(cc)
            rec.setBounds((int)x-WIDTH,(int)y, WIDTH, HIEGHT);
            rec2.setBounds((int)x-2-WIDTH ,(int)y-4, (WIDTH)+4, (HIEGHT)+6);
            }
            
        }
    }
    
     public void RefR(){
        setX(getX()-48);
    }
    public void RefL(){
        setX(getX()+48);
    }
    public String checkHits(Rectangle r,Rectangle r2){
        String s = "no";
        Rectangle r3 = r2.intersection(r);
        interSctionRect=r3;
         if(r2.intersects(r)){
             if(r3.getX()==r.x&&r3.height>r3.width){
            s="left";
         }
         if(r3.getX()+r3.width==r.getX()+r.width&&r3.height>r3.width){
              s="right";
         }
         if(r3.getY()==r.y&&r3.height<r3.width){
             s="up";
         }
         if(r3.getY()+r3.getHeight()==r.getY()+r.getHeight()&&r3.height<r3.width){
             s="down";
         }
         }
         return s;
    }
    public void updateLocation() {
        if(jumping && velY <= 0){
            jumping = false;
            falling = true;
        }
        else if(jumping){
            velY = velY - gravityAcc;
            y = y - velY;
        }

        if(falling){
            y = y + velY;
            velY = velY + gravityAcc;
        }

        x = x + velX;
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isCc() {
        return cc;
    }

    public void setDimension(int width, int height){ this.HIEGHT=height;WIDTH=width; }

    public BufferedImage getStyle() {
        return style;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public void setHIEGHT(int HIEGHT) {
        this.HIEGHT = HIEGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHIEGHT() {
        return HIEGHT;
    }

    public void setStyle(BufferedImage style) {
        this.style = style;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getGravityAcc() {
        return gravityAcc;
    }

    public void setGravityAcc(double gravityAcc) {
        this.gravityAcc = gravityAcc;
    }
    

   
         
    public Rectangle getTopBounds() {
     return new Rectangle((int)getX()+(WIDTH/4), (int)getY(), WIDTH/2, HIEGHT/5);
    }

    
    public Rectangle getBottomBounds() {
       return new Rectangle((int)getX()+(WIDTH/4),  (int)getY()+((4*HIEGHT)/5), WIDTH/2, HIEGHT/5);
    
    }

    
    public Rectangle getLeftBounds() {
            return new Rectangle((int)getX(), (int)getY()+(HIEGHT/4), WIDTH/5, HIEGHT/2);
    }

    
    public Rectangle getRightBounds() {
       return new Rectangle((int)getX()+((4*WIDTH)/5), (int) getY()+(HIEGHT/4), WIDTH/5, HIEGHT/2);
    }

    
    public Rectangle getBounds() {
        return new Rectangle((int)getX(),(int)getY(),WIDTH,HIEGHT);
    }

    public void update(){}

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
