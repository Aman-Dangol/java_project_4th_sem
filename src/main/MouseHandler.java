package main;

import entity.Bullet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public boolean mousePressed;
    public  int X ,Y;
    Bullet[] bullet;
    GamePanel gp ;
    public MouseHandler(Bullet[] bullet,GamePanel gp){
        this.bullet = bullet;
        this.gp = gp;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gp.gameState== gp.playState){
            X = e.getX();
            Y = e.getY();
            if (gp.player.hasGun){
                if (gp.bulletIndex < bullet.length) {
                    bullet[gp.bulletIndex].onAir = true;
                    bullet[gp.bulletIndex].destination(X, Y);
                    gp.bulletIndex++;
                    gp.soundSE(1);
                } else {
                    gp.ui.showMessage("reload [R]");
                }
            }
            else {
                gp.ui.showMessage("pickUp gun [E]");


            }
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        X=e.getX();
        Y=e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) { X=e.getX();
        Y=e.getY();
    }
}
