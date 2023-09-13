package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    public GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,pick,checkDrawTime=false;

    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gp.gameState==gp.playState){
            if(e.getKeyCode()==KeyEvent.VK_W) {
                upPressed = true;
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
                System.out.println("Reload");
                gp.bulletIndex=0;
            }

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
        else if (gp.gameState == gp.pauseState){

            if(e.getKeyCode()==KeyEvent.VK_P){
                gp.gameState= gp.playState;
            }

        }
        //dialogue state
        else  if (gp.gameState == gp.dialogueState){

            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W) {
            upPressed = false;
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

