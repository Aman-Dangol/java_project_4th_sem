package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public GamePanel gp;
    int code;
    public boolean upPressed, downPressed, leftPressed, rightPressed,pick;

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
         code = e.getKeyCode();
        if (gp.gameState== gp.titleState){
            drawTileState();
        }else
        if (gp.gameState==gp.playState){
            drawPlayState();

        }else
        if (gp.gameState == gp.pauseState){
            drawPauseState();
        }else
        if (gp.gameState == gp.gameOverState){
            gameOver();
        }

    }

    public void drawPauseState() {
        if (code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if (code==KeyEvent.VK_W){
        gp.ui.commandNum--;
        if (gp.ui.commandNum<0){
            gp.ui.commandNum=1;
        }

    }
        if (code==KeyEvent.VK_S){
            gp.ui.commandNum++;
            if (gp.ui.commandNum>1){
                gp.ui.commandNum=0;
            }
        }
        if (code==KeyEvent.VK_ENTER){
            if (gp.ui.commandNum==0){
                gp.gameState=gp.playState;
            }
            if (gp.ui.commandNum==1){
                gp.gameState = gp.titleState;
            }
        }
    }

    public void drawPlayState() {

        if(code==KeyEvent.VK_W) {
            upPressed = true;
            downPressed=false;
        }
        if(code==KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code==KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code==KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code ==KeyEvent.VK_E){
            pick=true;
        }
        if(code ==KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }
        if(code==KeyEvent.VK_R) {
            gp.bulletIndex=0;
        }
    }

    public void drawTileState() {

        if (code==KeyEvent.VK_W){
            gp.ui.commandNum--;
            if (gp.ui.commandNum<0){
                gp.ui.commandNum=1;
            }

        }
        if (code==KeyEvent.VK_S){
            gp.ui.commandNum++;
            if (gp.ui.commandNum>1){
                gp.ui.commandNum=0;
            }

        }
        if (code==KeyEvent.VK_ENTER){
            if (gp.ui.commandNum==0){
//                gp.reset();
                gp.gameState=gp.playState;
//                    gp.playMusic(0);
            }
            if (gp.ui.commandNum==1){
                System.exit(0);
            }
        }
    }

    public void gameOver() {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
        }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if (gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    gp.reset();
                }
               else if (gp.ui.commandNum == 1){
                   gp.gameState = gp.titleState;
                }
            }


    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W) {
            upPressed = false;
            downPressed=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_S) {
            downPressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(e.getKeyCode()==KeyEvent.VK_D) {
            rightPressed = false;
        }if(e.getKeyCode()==KeyEvent.VK_E) {
            pick = false;
        }


    }
}

