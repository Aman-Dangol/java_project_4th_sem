package main;

import entity.Player;
import tile.Background;
import tile.TileManager;
import Object.SuperObject;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    public int tileSize = 48;//for creating a tiles
    int column = 20;

    int rows = 12;

   public int screenWidth = column * tileSize;

    public int screenHeight = rows * tileSize;

   public final int maxWorldCol =50;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*column;
    public final int worldHeight=tileSize*rows;
    int FPS = 60;
    TileManager tileM;
    Background background=new Background(this);

    KeyHandler keyH=keyH=new KeyHandler();
    public Player player = new Player(this,keyH);

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    AssetSetter aSetter= new AssetSetter(this);

    SuperObject obj[]= new SuperObject[10];
    Thread thread;




    GamePanel() throws IOException {



        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        addKeyListener(keyH);
        setFocusable(true);
        tileM=new TileManager(this,keyH);
    }

    public void setupGame(){
        aSetter.setObject();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        background.draw(g2d);
        tileM.draw(g2d);
        for (int i=0;i< obj.length;i++){
            if (obj[i]!=null){
                obj[i].draw(g2d,this);
            }
        }
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

    void update()  {
        player.update();
        player.falling();

    }
    public void startingThread(){
        thread=new Thread(this);
        thread.start();

    }
}


