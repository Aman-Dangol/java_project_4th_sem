package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    public boolean mousePressed;
    public  int X ,Y;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed=true;
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
    public void mouseMoved(MouseEvent e) {
        X=e.getX();
        Y=e.getY();
    }
}
