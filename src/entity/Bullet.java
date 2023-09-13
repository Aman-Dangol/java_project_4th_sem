package entity;

import main.GamePanel;
import main.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet extends Entity {
    Player player;
    public GamePanel gp;

    BufferedImage image;
    public Boolean onAir=false;
    int i=0;
    int mouseX,mouseY;
    int pX ,pY;
    public int bulletX;
    public int bulletY;
    double angle;
    int bulletRange=300;

    public Bullet(Player player, GamePanel gp)  {
        super(gp);
       super.speed = 8;

        this.gp=gp;
        this.player=player;

//        solidArea = new Rectangle(20,20,4,5);
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/bullet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void draw(Graphics2D g2){
        if (onAir) {
            moveBullet();
            if (i!=bulletRange){
                gp.collisionChecker.checkBulletCollision(this);
                if (collisionOn){

                }
                else
                    g2.drawImage(image,bulletX-24,bulletY-24,gp.tileSize,gp.tileSize,null);
                i++;
            }
            else {
                onAir=false;
                i=0;
                collisionOn=false;
            }


    }
    }
         public void destination(int x,int y){
        mouseX=(x/speed)*speed;
        mouseY=(y/speed)*speed;
        bulletX=player.weapon.centerX;
        bulletY= player.weapon.centerY;
         }


         public void moveBullet(){
             angle = Math.atan2(mouseY - bulletY, mouseX - bulletX);
             bulletX += ((int) (speed * Math.cos(angle)));
             bulletY += (int) (speed * Math.sin(angle));
             worldX = bulletX+gp.player.worldX - gp.player.screenX;
             worldY=bulletY+gp.player.worldY - gp.player.screenY;
             if ((bulletX/speed)*speed == mouseX || (bulletX/speed)*speed == mouseX -speed && (bulletY/speed)*speed == mouseY ||  (bulletY/speed)*speed == mouseY- speed){
                 i=bulletRange;
             }
             if (collisionOn){
                 i=bulletRange;
             }
         }


}




