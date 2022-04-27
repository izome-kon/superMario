package manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class InputManager implements KeyListener, MouseListener{

    private GameEngine engine;
    
    InputManager(GameEngine engine) {
        this.engine = engine; }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        GameStatus status = engine.getGameStatus();
        ButtonAction currentAction = ButtonAction.NO_ACTION;
        //System.err.println(keyCode);
        if(keyCode>=65&&keyCode<=90||keyCode>=48&&keyCode<=57)
        {
            engine.receiveInput(event);
            return;
        }
        
        if (keyCode == KeyEvent.VK_UP) {
            if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION||status == GameStatus.LOGIN||status==GameStatus.LVL_SELECTION||status==GameStatus.SIGNUP)
                currentAction = ButtonAction.GO_UP;
            else
                currentAction = ButtonAction.JUMP;
        }
         if(keyCode == KeyEvent.VK_DOWN){
            if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION||status == GameStatus.LOGIN||status==GameStatus.LVL_SELECTION||status==GameStatus.SIGNUP)
                currentAction = ButtonAction.GO_DOWN;
        }
         if (keyCode == KeyEvent.VK_RIGHT) {
            currentAction = ButtonAction.M_RIGHT;
        }
         if (keyCode == KeyEvent.VK_LEFT) {
            currentAction = ButtonAction.M_LEFT;
        }
         if (keyCode == KeyEvent.VK_ENTER) {
            currentAction = ButtonAction.SELECT;
        }
         if (keyCode == KeyEvent.VK_ESCAPE) {
            if(status == GameStatus.RUNNING || status == GameStatus.PAUSED )
                currentAction = ButtonAction.PAUSE_RESUME;
            else
                currentAction = ButtonAction.GO_TO_START_SCREEN;

        }
         if (keyCode == KeyEvent.VK_SPACE){
            currentAction = ButtonAction.FIRE;
        }
         if(keyCode==KeyEvent.VK_BACK_SPACE)
             currentAction= ButtonAction.BACKSPACE;

        notifyInput(currentAction);
    }

    @Override
    public void mousePressed(MouseEvent e) {
     
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_LEFT)
            notifyInput(ButtonAction.ACTION_COMPLETED);
    }

    private void notifyInput(ButtonAction action) {
        if(action != ButtonAction.NO_ACTION)
            engine.receiveInput(action);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
