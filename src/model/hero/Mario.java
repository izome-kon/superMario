package model.hero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.text.html.CSS;
import model.GameObject;


public class Mario extends GameObject{
    private MarioForm myForm ;
    private boolean toRight;
    private int coins ;
    private int points;
    private int remainingLives;
    private boolean Right,Left,Up,Down,D5ra,U5ra,R5ra,L5ra,endCam;
    private double maxHiegt;
    
    
    public Mario(double x, double y,int numOfDies) {
        super(x, y);
        myForm = new MarioForm();
        setStyle(myForm.getStyle());
        toRight = true;
        coins = 0;
        points = 0;
        remainingLives = 3-numOfDies;

        Up = false;
        Down = false;
        Right = false;
        Left = false;
        maxHiegt = 0;
        setFalling(false);
        setJumping(false);
        myForm.setIsSuper(false);
        endCam = false;

    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }
    
    public void setEndCam(boolean endCam) {
        this.endCam = endCam;
    }
    
    public boolean isfire(){
    return myForm.isIsFire();
    }
    
    public boolean issuper(){
    return myForm.isIsSuper();
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
    
    public void setD5ra(boolean b5ra) {
        this.D5ra = b5ra;
    }
    
   
    public void setMaxHiegt(int maxHiegt) {
        this.maxHiegt = maxHiegt;
    }

    public void draw(Graphics g){
        
        update();
        super.draw(g);
    }

    
      public int getCoins() {
        return coins;
    }

    public int getPoints() {
        return points;
    }

    public int getRemainingLives() {
        return remainingLives;
    }


    public boolean isD5ra() {
        return D5ra;
    }
    
    public void Die(){
    if(myForm.isIsSuper()){
    return;
    }else {
        if(myForm.getForm()==MarioForm.Forms.SUPER){
           myForm.setForm(MarioForm.Forms.MARIO);
        }else{
            myForm.setRange(MarioForm.movePic.DIE);
        }
    }
    }
    
    @Override
    public void update() {
        if(myForm.getForm()==MarioForm.Forms.MARIO){
        setDimension(16*3,16*3);
        }else 
            setDimension(16*3,32*3);
        if(getX()<=550){
            setFalling(false);
        }
        Move();
        myForm.checkFire();
        myForm.checkSuper();
        myForm.updateForm();
        myForm.updatePicRange();
        setStyle(myForm.getStyle());
    }
    

    public void Move() {
        setVels();
        setMT();
        if(endCam)
            setVelX(0); 
        setX(getX()+getVelX());
        setY(getY()+getVelY());
    }

    public void setMaxHiegt(double maxHiegt) {
        this.maxHiegt = maxHiegt;
    }



    private void setVels() {
        if(Right){
            endCam = false;
            if(R5ra){setVelX(0);}
            else{
            if(getVelX()<7){
            if(getVelX()<=0)
                setVelX(getVelX()+2);
            else 
                setVelX(getVelX()+1);
            }}
        }else if(Left){
            if(L5ra){setVelX(0);}
            else{
            if(getVelX()>-7){
            if(getVelX()>=0)
                setVelX(getVelX()-2);
            else 
                setVelX(getVelX()-1);
            }
            }
            
        }else if(!Left&&!Right){
            if(getVelX()>0&&!R5ra){
                setVelX(getVelX()-1);
            }else if(getVelX()<0&&!L5ra){
                setVelX(getVelX()+1);
            } 
        }
        if(Up&&getY()>=maxHiegt&&maxHiegt!=0){
            setJumping(true);
        }
        else{
            maxHiegt=0;
            setJumping(false);
        }
        if(!Up){
        setJumping(false);
        }
        if(isJumping()||D5ra){
            setFalling(false);
        }else 
            setFalling(true);
        if(isFalling()){
            
            setVelY(getVelY()+getGravityAcc());
        }
        else if(isJumping()){
            D5ra=false;
            if(!U5ra){
            if(getVelY()>=-20){
                setVelY(getVelY()-2);
            }}else{
                setVelY(0);
            }
            
        }else{
        setVelY(0);
        }
    }

    public void setU5ra(boolean U5ra) {
        this.U5ra = U5ra;
    }

    public void setR5ra(boolean R5ra) {
        this.R5ra = R5ra;
    }

    public void setL5ra(boolean L5ra) {
        this.L5ra = L5ra;
    }

    public double getMaxHiegt() {
        return maxHiegt;
    }

    public boolean isUp() {
        return Up;
    }

    public boolean isDown() {
        return Down;
    }

    

    public void setUp(boolean Up) {
        this.Up = Up;
    }

    public void setRight(boolean Right) {
        this.Right = Right;
    }

    public void setLeft(boolean Left) {
        this.Left = Left;
    }

    public void setDown(boolean Down) {
        this.Down = Down;
    }

    private void setMT() {
        if(myForm.getRange()!=MarioForm.movePic.DIE){
        if(isJumping()||isFalling()){
                    myForm.setRange(MarioForm.movePic.JUMP);
        }else if(Right){
            if(getVelX()>0)
                    myForm.setRange(MarioForm.movePic.RUN);
            else
                    myForm.setRange(MarioForm.movePic.BACK);
        }else if(Left){
            if(getVelX()<0)
                    myForm.setRange(MarioForm.movePic.RUN);
            else
                    myForm.setRange(MarioForm.movePic.BACK);
        }else 
                    myForm.setRange(MarioForm.movePic.STAND);
    }
    }

    public MarioForm getMyForm() {
        return myForm;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    }
