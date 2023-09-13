package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed=5;
    public int fallingSpeed = 1;
    public int jump=-4;
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    String prevDirection="left";
    public int actionLockCounter;


    public String name="";

    public  int maxLife;
    public  int life;

    public BufferedImage fly;
    public BufferedImage upLeft1;
    public BufferedImage upLeft2;
    public BufferedImage upLeft3;
    public BufferedImage upRight1;
    public BufferedImage upRight2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage left3;
    public BufferedImage right1;
    public BufferedImage right2;
    public BufferedImage right3;
    public Rectangle solidArea= new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=false;
    GamePanel gp;
    public Entity(GamePanel gp){
        this.gp =gp;
    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image=null;
        try {
            image= ImageIO.read(getClass().getResourceAsStream(imageName+".png"));
            image=uTool.scaledImage(image, gp.tileSize,gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

         int screenX= worldX - gp.player.worldX + gp.player.screenX;
         int screenY= worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
        ) {
            switch (direction){
                case "up":
//                    if(prevDirection=="left") {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left1;
                        }
                        if (spriteNum == 3) {
                            image = left1;
                        }
//                    } else if (prevDirection=="right") {
//                        if (spriteNum == 1) {
//                            image = right1;
//                        }
//                        if (spriteNum == 2) {
//                            image = right1;
//                        }
//                        if (spriteNum == 3) {
//                            image = right1;
//                        }
//                    }
                    break;
                case "down":
                    image = right1;
                    break;
                case "left":
                        if(spriteNum==1){
                            image = left1;
                        }
                        if(spriteNum==2){
                            image = left2;
                        }
                        if(spriteNum==3){
                            image=left3;
                        }

                    break;
                case "right":
                        if(spriteNum==1){
                            image = right1;
                        }
                        if(spriteNum==2){
                            image = right2;
                        }
                        if(spriteNum==3){
                            image=right3;
                        }
                    break;
            }
            g2.drawImage(image,screenX,screenY, null);
        }

    }

    public void setAction(){

    }
    public void update(){
        setAction();
        collisionOn=false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this,false);
        gp.collisionChecker.checkEntity(this,gp.enemy);
        gp.collisionChecker.checkPlayer(this);
        if (collisionOn==false){

            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left" :
                    worldX -= speed;
                    break;
                case "right":
                    worldX+= speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {//player image changes every 10 frames
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;

            }
            spriteCounter = 0;
        }

    }


}
