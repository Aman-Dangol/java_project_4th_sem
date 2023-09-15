package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    public GamePanel gp;
    int code;
    public boolean upPressed, downPressed, leftPressed, rightPressed,pick,checkDrawTime=false;

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
                    gp.reset();
                    gp.gameState=gp.playState;
//                    gp.playMusic(0);
                }
                if (gp.ui.commandNum==1){
                    System.exit(0);
                }
            }

        }

        if (gp.gameState==gp.playState){
            if(e.getKeyCode()==KeyEvent.VK_W) {
                upPressed = true;
                downPressed=false;
            }
            if(e.getKeyCode()==KeyEvent.VK_S) {
                downPressed = true;
            }
            if(e.getKeyCode()==KeyEvent.VK_A) {
                leftPressed = true;
            }
            if(e.getKeyCode()==KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (e.getKeyCode()==KeyEvent.VK_E){
                pick=true;
            }
            if(e.getKeyCode()==KeyEvent.VK_P) {
                    gp.gameState= gp.pauseState;
            }
            if(e.getKeyCode()==KeyEvent.VK_R) {
                gp.bulletIndex=0;
            }

        }else if (gp.gameState == gp.pauseState){

            if(e.getKeyCode()==KeyEvent.VK_P){
                gp.gameState= gp.playState;
            }
        }else if (gp.gameState == gp.gameOverState){
            gameOver();
        }
        if (e.getKeyCode()==KeyEvent.VK_T){
            if (checkDrawTime==false){
                checkDrawTime=true;
            }
            else if (checkDrawTime==true){
                checkDrawTime=false;
            }
        }

        //pause state

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

