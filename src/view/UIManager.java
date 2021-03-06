package view;

import manager.GameEngine;
import manager.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import manager.SoundManager;
import model.brick.Brick;
import model.brick.GroundBrick;
import model.prize.Coin;

public class UIManager extends JPanel{

    private GameEngine engine;
    private Font gameFont;
    private BufferedImage startScreenImage,accountScreenImage,loginScreenImage,signUpScreenImage, aboutScreenImage, helpScreenImage, gameOverScreen;
    private BufferedImage heartIcon;
    private BufferedImage coinIcon;
    private BufferedImage selectIcon;
    private MapSelection mapSelection;
    public LevelSelection lvlSelection;
    private Account account ;
    private Login login;
    private SignUp signUp = new SignUp();

    public void setMapSelection(MapSelection mapSelection) {
        this.mapSelection = mapSelection;

    }

    public MapSelection getMapSelection() {
        return mapSelection;
    }

    public UIManager(GameEngine engine, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        account = new Account();
        login = new Login();
        this.engine = engine;
        ImageLoader loader = engine.getImageLoader();

        coinIcon=loader.loadImage("/Prize-forms.png");
        this.coinIcon = loader.getSubImage(coinIcon,0,6*16, 16, 16);
        this.heartIcon = loader.loadImage("/heart-icon.png");
        
        this.selectIcon = loader.loadImage("/select-icon.png");
        this.startScreenImage = loader.loadImage("/start-screen.png");
        this.helpScreenImage = loader.loadImage("/help-screen.png");
        this.aboutScreenImage = loader.loadImage("/about-screen.png");
        this.gameOverScreen = loader.loadImage("/game-over.png");
        this.accountScreenImage=loader.loadImage("/account-screen.png");
        this.loginScreenImage=loader.loadImage("/login-screen.png");
        
        try {
            InputStream in = getClass().getResourceAsStream("/media/font/mario-font.ttf");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (FontFormatException | IOException e) {
            gameFont = new Font("Verdana", Font.PLAIN, 12);
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g.create();
        GameStatus gameStatus = engine.getGameStatus();
       
        if(gameStatus == GameStatus.START_SCREEN){
            drawStartScreen(g2);
        }else if(gameStatus == GameStatus.ACCOUNT){
            drawAccountScreen(g2);
        }else if(gameStatus == GameStatus.LOGIN){
            drawLoginScreen(g2);
        }
        else if(gameStatus == GameStatus.SIGNUP){
            drawSignUpScreen(g2);
        }
        else if(gameStatus == GameStatus.MAP_SELECTION){
            drawMapSelectionScreen(g2);
        }
        else if(gameStatus == GameStatus.ABOUT_SCREEN){
            drawAboutScreen(g2);
        }
        else if(gameStatus == GameStatus.HELP_SCREEN){
            drawHelpScreen(g2);
        }
        else if(gameStatus == GameStatus.GAME_OVER){
            drawGameOverScreen(g2);
        }
        else if(gameStatus == GameStatus.LVL_SELECTION){
            drawLvlSelectionScreen(g2);
        }
        else {


            if(gameStatus == GameStatus.PAUSED){
                drawPauseScreen(g2);
            }
            else if(gameStatus == GameStatus.MISSION_PASSED){
                drawVictoryScreen(g2);
            }else{
                Point camLocation = engine.getCameraLocation();
                g2.translate(-camLocation.x, -camLocation.y);
                engine.drawMap(g2);
                g2.translate(camLocation.x, camLocation.y);
                drawPoints(g2);
                drawRemainingLives(g2);
                drawAcquiredCoins(g2);
                drawRemainingTime(g2);
            }
        }
         
        g2.dispose();
    }

    private void drawLvlSelectionScreen(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        lvlSelection.draw(g2);
        int row = lvlSelection.getIndexSelection();
        int y_location = row*100+300-selectIcon.getHeight();
        g2.drawImage(selectIcon, 410, y_location-60, null);
    }

    private void drawRemainingTime(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "TIME: " + engine.getRemainingTime();
        g2.drawString(displayedStr, 750, 50);
    }
    boolean first;
    int s;
    private void drawVictoryScreen(Graphics2D g2) {

        g2.fillRect(0,0,2000,1000);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        String displayedStr = "YOU WON this Level !";


        if(!first) {
             s = engine.getRemainingTime()*3+engine.getScore();
            first=true;
        }
        String score = "Scour :" + String.valueOf(s);
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);
        int stringLength2 = g2.getFontMetrics().stringWidth(score);

        g2.drawString(displayedStr, (getWidth()-stringLength)/2, getHeight()/2);
        g2.setColor(Color.ORANGE);
        g2.drawString(score, (getWidth()-stringLength2)/2, getHeight()/2+100);

    }

    private void drawHelpScreen(Graphics2D g2) {
        g2.drawImage(helpScreenImage, 0, 0, null);
    }

    private void drawAboutScreen(Graphics2D g2) {
        g2.drawImage(aboutScreenImage, 0, 0, null);
    }

    public Login getLogin() {
        return login;
    }
    public SignUp getSignUp() {
        return signUp;
    }

    private void drawGameOverScreen(Graphics2D g2) {
        g2.drawImage(gameOverScreen, 0, 0, null);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        String acquiredPoints = "Score: " + engine.getScore();
        int stringLength = g2.getFontMetrics().stringWidth(acquiredPoints);
        int stringHeight = g2.getFontMetrics().getHeight();
        g2.drawString(acquiredPoints, (getWidth()-stringLength)/2, getHeight()-stringHeight*2);
    }

    private void drawAccountScreen(Graphics2D g2){
         g2.drawImage(accountScreenImage, 0, 0, null);
        g2.setFont(gameFont.deriveFont(50f));
         account.draw(g2);
    }
    
    private void drawLoginScreen(Graphics2D g2){
         g2.drawImage(loginScreenImage, 0, 0, null);
         g2.setFont(gameFont.deriveFont(20f));
         login.draw(g2);
    }
    private void drawSignUpScreen(Graphics2D g2){
        g2.drawImage(loginScreenImage, 0, 0, null);
        g2.setFont(gameFont.deriveFont(20f));
        signUp.draw(g2);
    }

    private void drawPauseScreen(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,2000,1000);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        String displayedStr = "PAUSED";
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);
        g2.drawString(displayedStr, (getWidth()-stringLength)/2, getHeight()/2);
    }

    private void drawAcquiredCoins(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(30f));
        g2.setColor(Color.WHITE);
        String displayedStr = "" + engine.getCoins();
        g2.drawImage(coinIcon, getWidth()-150, 10,40,40, null);
        g2.drawString(displayedStr, getWidth()-100, 50);
    }

    private void drawRemainingLives(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(30f));
        g2.setColor(Color.WHITE);
        String displayedStr = "" + engine.getRemainingLives();
        g2.drawImage(heartIcon, 50, 10, null);
        g2.drawString(displayedStr, 100, 50);
    }

    private void drawPoints(Graphics2D g2){
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "Points: " + engine.getScore();
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);
        g2.drawString(displayedStr, 300, 50);
    }

    private void drawStartScreen(Graphics2D g2){
        int row = engine.getStartScreenSelection().getLineNumber();
        g2.drawImage(startScreenImage, 0, 0, null);
        g2.drawImage(selectIcon, 375, row * 70 + 440, null);
    }

    private void drawMapSelectionScreen(Graphics2D g2){
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        mapSelection.draw(g2);
        int row = engine.getSelectedMap();
        int y_location = row*100+300-selectIcon.getHeight();
        g2.drawImage(selectIcon, 763, y_location, null);
    }

    public Account getAccount() {
        return account;
    }



    public String selectMapViaKeyboard(int index){
        return mapSelection.selectMap(index);
    }

    public int changeSelectedMap(int index, boolean up){
        return mapSelection.changeSelectedMap(index, up);
    }
    public int changeSelectedLvl(int index, boolean up){
        return lvlSelection.changeSelectedMap(index, up);
    }
}