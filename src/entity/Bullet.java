package entity;

import main.GamePanel;
import main.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    Player player;
    GamePanel gp;
    MouseHandler mh;
    BufferedImage image;

    public Bullet(Player player, GamePanel gp, MouseHandler mh){
        this.gp=gp;
        this.player=player;
        this.mh=mh;


    }
    public void draw(Graphics2D g2){
         int x=player.screenX;
        int y=player.screenY;


//        worldX =(mh.X/ gp.maxWorldCol)* gp.tileSize;
//        worldY=(mh.Y/ gp.maxWorldRow)*gp.tileSize;
//        int screenX= worldX - gp.player.worldX + gp.player.screenX;
//        int screenY= worldY - gp.player.worldY + gp.player.screenY;
//        System.out.println(worldX+" "+ worldY);
            while (mh.X!=x && mh.Y!=y){
                if (mh.X>x){
                    x++;
                }
                else
                    x--;
                if (mh.Y>y)
                    y++;
                else y--;
                g2.fillOval(x, y, 20,20);

            }
//            if (mh.X + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                    mh.Y + gp.tileSize > gp.player.worldY - gp.player.screenY
//            ) {
//                g2.drawImage(tile[tileNum].image, screenX, screenY,null);



            }
        }



