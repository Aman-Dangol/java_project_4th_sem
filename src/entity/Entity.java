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

    BufferedImage fly;
    BufferedImage upLeft1;
    BufferedImage upLeft2;
    BufferedImage upLeft3;
    BufferedImage upRight1;
    BufferedImage upRight2;
    protected BufferedImage left1;
    public BufferedImage left2;
    BufferedImage left3;
    public BufferedImage right1;
    public BufferedImage right2;
    BufferedImage right3;
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

                    if(prevDirection=="left") {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left1;
                        }
                        if (spriteNum == 3) {
                            image = left1;
                        }
                    } else if (prevDirection=="right") {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right1;
                        }
                        if (spriteNum == 3) {
                            image = right1;
                        }
                    }
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


    }


}
