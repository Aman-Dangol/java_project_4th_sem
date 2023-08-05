package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable {
    public int tileSize = 48;//for creating a tiles
    int column = 16;

    int rows = 12;

   public int width = column * tileSize;

    public int height = rows * tileSize;

   // int x = 100;
    //int y = height - tileSize;
    int FPS = 60;
    TileManager tileM;

    KeyHandler keyH=new KeyHandler();
    public Player player = new Player(this,keyH);

    Thread thread;


    GamePanel() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        addKeyListener(keyH);
        setFocusable(true);
        tileM=new TileManager(this,keyH);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileM.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
    @Override
    public void run() {
        double drawInterval = 1000 / FPS;//draw screen avery 0.01666 times==draw screen 60 times per second
        double nextDrawTime = System.currentTimeMillis() + drawInterval;//upper limit
        while (true) {//as long as gameThread obj exists it repeats the process,core component go game
            //update character position and drawing with updated information
            update();
          //  falling();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                nextDrawTime += drawInterval;
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void update() {player.update();}
//    void falling() {
//        player.falling();
//    }
    public void startingThread(){
        thread=new Thread(this);
        thread.start();

    }
}


