package manager;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.*;

import JDBC.User;
import model.GameObject;
import model.hero.Fireball;
import model.hero.Mario;
import view.*;
import view.UIManager;

public class GameEngine implements Runnable {

    private final static int WIDTH = 1268, HEIGHT = 708;
    public static Point tmpMarioLocations;
    public static int numOfDies=0;
    private MapManager mapManager;
    private UIManager uiManager;
    private SoundManager soundManager;
    private GameStatus gameStatus;
    private boolean isRunning;
    private Camera camera;
    private ImageLoader imageLoader;
    private Thread thread;
    private StartScreenSelection startScreenSelection = StartScreenSelection.START_GAME;
    private int selectedMap = 0;
    private User user ;
    private int winPage=0;
    private GameEngine() {
        init();
    }

    private void init() {
        imageLoader = new ImageLoader();
        InputManager inputManager = new InputManager(this);
        gameStatus = GameStatus.START_SCREEN;
        camera = new Camera();
        uiManager = new UIManager(this, WIDTH, HEIGHT);
        soundManager = new SoundManager();
        mapManager = new MapManager();

        JFrame frame = new JFrame("Super Mario");
        frame.add(uiManager);
        frame.addKeyListener(inputManager);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        start();
    }

    private synchronized void start() {
        if (isRunning)
            return;

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    private void reset(){
        //reset game
        resetCamera();
        setGameStatus(GameStatus.START_SCREEN);
    }

    public void resetCamera(){
       // reset camera to 0 , 0 ;
        camera = new Camera();
        soundManager.restartBackground();
    }


    public void selectMapViaKeyboard(){
        String path = uiManager.selectMapViaKeyboard(selectedMap);
        if (path != null) {
           //createMap(path);
            gameStatus=GameStatus.LVL_SELECTION;
            System.out.println(selectedMap+"= selectedMap");
            uiManager.lvlSelection=new LevelSelection(selectedMap);
        }
    }
    String path;
    public void selectLvlViaKeyboard(){
        path = LevelSelection.lvlSelectionItems[LevelSelection.indexSelection].getLvlPath();
        System.out.println(path);
        if (path != null) {
            createMap(path);
        }
    }
    public String getPath() {
        return path;
    }

    public void changeSelectedMap(boolean up){
        selectedMap = uiManager.changeSelectedMap(selectedMap, up);
    }

    private void createMap(String path) {
         boolean loaded = mapManager.createMap(imageLoader, path);
        if(loaded){
            setGameStatus(GameStatus.RUNNING);
            soundManager.restartBackground();
        }

        else
            setGameStatus(GameStatus.START_SCREEN);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning && !thread.isInterrupted()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (gameStatus == GameStatus.RUNNING) {
                    gameLoop();
                }
                delta--;
            }
            render();
            try {
                    Thread.sleep(15);
                } catch (InterruptedException ex) {
                }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                mapManager.updateTime();
            }
        }
    }

    private void render() {
        uiManager.repaint();
    }

    private void gameLoop() {
        updateLocations();
        checkCollisions();
        updateCamera();

        if (isGameOver()) {
            setGameStatus(GameStatus.GAME_OVER);
            soundManager.playGameOver();
        }
        if (!mapManager.getMario().isCc() && mapManager.getMario().getY() >= 1200) {
            numOfDies++;
            Mario tmpMario = new Mario(tmpMarioLocations.x, tmpMarioLocations.y, numOfDies);
            mapManager.resetCurrentMap(this, tmpMario);
        }
        if (mapManager.getMario().getX() >= mapManager.EndPoint()&&gameStatus!=GameStatus.MISSION_PASSED) {
            setGameStatus(GameStatus.MISSION_PASSED);
            System.out.println("Win");
            System.out.println(LevelSelection.lvlSelectionItems[LevelSelection.indexSelection].getLvlPath());
            LevelSelection.indexSelection += 1;
            if(LevelSelection.indexSelection>=5){
                LevelSelection.indexSelection =0;
                selectedMap+=1;
                uiManager.lvlSelection=new LevelSelection(selectedMap);
            }
            selectLvlViaKeyboard();
            resetCamera();
            gameStatus = GameStatus.RUNNING;
        }
        if (gameStatus == GameStatus.MISSION_PASSED) {

        }
    }

    private void updateCamera() {
      Mario mario = mapManager.getMario();
        double marioVelX = mario.getVelX();
        double move = 0;
        
        if(marioVelX>0&&mario.getX()>=camera.getX()+WIDTH/4){
            move = marioVelX;
        }
        camera.moveCam(move, 0);
    }

    private void updateLocations() {
   
       if(mapManager.getMario().getX()-(mapManager.getMario().getWIDTH())<=camera.getX()&&mapManager.getMario().getVelX()<0)
           mapManager.getMario().setEndCam(true);
    }
    

    private void checkCollisions() {
     
    }

    public void receiveInput(KeyEvent e){
        if(gameStatus == GameStatus.LOGIN){
           uiManager.getLogin().write(e.getKeyChar());
        }
        if(gameStatus == GameStatus.SIGNUP){
            uiManager.getSignUp().write(e.getKeyChar());
        }
    }
    public void receiveInput(ButtonAction input) {

        if (gameStatus == GameStatus.START_SCREEN) {
            if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.START_GAME) {
                startGame();
            } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.VIEW_ABOUT) {
                setGameStatus(GameStatus.ABOUT_SCREEN);
            } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.VIEW_HELP) {
                setGameStatus(GameStatus.HELP_SCREEN);
            } else if (input == ButtonAction.GO_UP) {
                selectOption(true);
            } else if (input == ButtonAction.GO_DOWN) {
                selectOption(false);
            }
        }
        else if(gameStatus == GameStatus.MAP_SELECTION){
            if(input == ButtonAction.SELECT){
                //selectMapViaKeyboard();
                gameStatus=GameStatus.LVL_SELECTION;
                uiManager.lvlSelection=new LevelSelection(selectedMap);
            }
            else if(input == ButtonAction.GO_UP){
                changeSelectedMap(true);
            }
            else if(input == ButtonAction.GO_DOWN){
                changeSelectedMap(false);
            }

        }
        else if(gameStatus == GameStatus.LVL_SELECTION) {
            System.out.println(input);
            if (input == ButtonAction.SELECT) {
                selectLvlViaKeyboard();
                gameStatus = GameStatus.RUNNING;
            } else if (input == ButtonAction.GO_UP) {

                LevelSelection.moveUp();
            } else if (input == ButtonAction.GO_DOWN) {

                LevelSelection.moveDown();
            }
        }
        else if(gameStatus == GameStatus.ACCOUNT){
             if(input == ButtonAction.M_RIGHT||input == ButtonAction.M_LEFT)
                 uiManager.getAccount().change();
            if(input==ButtonAction.SELECT&&uiManager.getAccount().getSelect()==Account.Select.LOGIN)
                gameStatus=GameStatus.LOGIN;
            else if(input==ButtonAction.SELECT&&uiManager.getAccount().getSelect()==Account.Select.SIGNUP)
                gameStatus=GameStatus.SIGNUP;

        }else if(gameStatus == GameStatus.LOGIN){
            if(input == ButtonAction.SELECT){
                if(uiManager.getLogin().getSelect()==Login.Select.btn){
                    if(Login(Login.getUserName(),Login.getPassword())                    ) {
                        uiManager.setMapSelection(new MapSelection());
                        uiManager.getMapSelection().setUser(user);
                        gameStatus = GameStatus.MAP_SELECTION;
                    }
                    else
                        JOptionPane.showMessageDialog(null,"your user or password is wrong","Error!",JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(input == ButtonAction.GO_UP){
               uiManager.getLogin().changeUP();
            }
            else if(input == ButtonAction.GO_DOWN){
                uiManager.getLogin().changeDown();
            }else if(input == ButtonAction.BACKSPACE){
                uiManager.getLogin().remove();
            }
        }else if(gameStatus == GameStatus.SIGNUP){
            if(input == ButtonAction.SELECT){
                if(uiManager.getSignUp().getSelect()==SignUp.Select.btn){

                    User newUser = new User(uiManager.getSignUp().getUserName(),uiManager.getSignUp().getPassword());
                    newUser.createNewUser();
                    JOptionPane.showMessageDialog(null,"Done ! now Login by your new user and password","Done!",JOptionPane.WARNING_MESSAGE);
                    gameStatus=GameStatus.LOGIN;
                    }
            }
            else if(input == ButtonAction.GO_UP){
                uiManager.getSignUp().changeUP();
            }
            else if(input == ButtonAction.GO_DOWN){
                uiManager.getSignUp().changeDown();
            }else if(input == ButtonAction.BACKSPACE){
                uiManager.getSignUp().remove();
            }
        }else if (gameStatus == GameStatus.RUNNING) {
            if(input == ButtonAction.M_RIGHT){
              if(!mapManager.getMario().isToRight()){
            
            mapManager.getMario().setToRight(true);
              }
              mapManager.getMario().setRight(true);
            }
            if(input == ButtonAction.M_LEFT){
                
            mapManager.getMario().setLeft(true);
               if(mapManager.getMario().isToRight())
            mapManager.getMario().setToRight(false);
            }
            if(input == ButtonAction.JUMP){
                if(!mapManager.getMario().isJumping()&&!mapManager.getMario().isFalling()){
                mapManager.getMario().setMaxHiegt(mapManager.getMario().getY()-(63));
                }
                mapManager.getMario().setUp(true);
                playJump();
            }
            if (input == ButtonAction.FIRE) {
                mapManager.getMario().setX(7500);
                camera.setX(7500);
            }
            if (input == ButtonAction.FIRE&&mapManager.getMario().isfire()) {
                Fireball f;

                if(mapManager.getMario().isToRight())
                f = new Fireball(mapManager.getMario().getX()+mapManager.getMario().getWIDTH(), mapManager.getMario().getY());
                else{
                    f = new Fireball(mapManager.getMario().getX(), mapManager.getMario().getY());
                    f.setToRight(false);
                }
                playFireball();
                mapManager.addfire(f);
            }
            if(input == ButtonAction.ACTION_COMPLETED){
                mapManager.getMario().setRight(false);
                mapManager.getMario().setLeft(false);
                mapManager.getMario().setUp(false);
                mapManager.getMario().setVelY(0);
            }
            if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }
                
        } else if (gameStatus == GameStatus.PAUSED) {
            if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }
        } else if(gameStatus == GameStatus.GAME_OVER && input == ButtonAction.GO_TO_START_SCREEN){
            reset();
        }

        if(input == ButtonAction.GO_TO_START_SCREEN){
            setGameStatus(GameStatus.START_SCREEN);
        }
        System.out.println(input);
    }

    private void selectOption(boolean selectUp) {
        startScreenSelection = startScreenSelection.select(selectUp);
    }

    private void startGame() {
        if (gameStatus != GameStatus.GAME_OVER) {
           setGameStatus(GameStatus.ACCOUNT);
        }
    }

    private void pauseGame() {
        if (gameStatus == GameStatus.RUNNING) {
            setGameStatus(GameStatus.PAUSED);
            soundManager.pauseBackground();
        } else if (gameStatus == GameStatus.PAUSED) {
            setGameStatus(GameStatus.RUNNING);
            soundManager.resumeBackground();
        }
    }

    private boolean isGameOver() {
        if(gameStatus == GameStatus.RUNNING)
            return mapManager.isGameOver();
        return false;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public StartScreenSelection getStartScreenSelection() {
        return startScreenSelection;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getScore() {
        //from MapManagers
        return mapManager.getScore();
        
    }

    public int getRemainingLives() {
        //from MapManager
        return mapManager.getRemainingLives();
    }

    public int getCoins() {
        //from Map Manager
        return mapManager.getCoins();
    }

    public int getSelectedMap() {
        return selectedMap;
    }

    public void drawMap(Graphics2D g2) {
        mapManager.drawMap(g2);
        mapManager.setCameraLocation(getCameraLocation());
    }

    public Point getCameraLocation() {
        return new Point((int)camera.getX(), (int)camera.getY());
    } 

   

    public void playCoin() {
        soundManager.playCoin();
    }

    public void playOneUp() {
        soundManager.playOneUp();
    }

    public void playSuperMushroom() {
        soundManager.playSuperMushroom();
    }

    public void playMarioDies() {
        soundManager.playMarioDies();
    }

    public void playJump() {
        soundManager.playJump();
    }

    public void playFireFlower() {
        soundManager.playFireFlower();
    }

    public void playFireball() {
        soundManager.playFireball();
    }

    
    public void playStomp() {
        soundManager.playStomp();
    }

    public MapManager getMapManager() {
        return mapManager;
    }
    
    public static void main(String[] args) {
        new GameEngine();
    }

    public int getRemainingTime() {
        //from map manager
        return mapManager.getRemainingTime();
    }

    public boolean Login(String user,String password) {
        User u = new User(user,password);
        if(u.load()){
            this.user=u;
            return true;
        }else
            return false;
    }
}
