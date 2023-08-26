package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    String prevDirection="left";
    public Player(GamePanel gp,KeyHandler keyH){

        this.gamePanel=gp;
        this.keyH=keyH;

        screenX= gp.screenWidth/2;
        screenY=gp.screenHeight/2;

        solidArea = new Rectangle(0,0,gp.tileSize,gp.tileSize);

        setDefaultValues();
        getPlayerImage();
        direction="down";


    }
    public void setDefaultValues(){
       worldX =gamePanel.tileSize*23;
        worldY =gamePanel.tileSize*9;
        speed=4;

    }
    void getPlayerImage(){
        try {
            upLeft1= ImageIO.read(getClass().getResourceAsStream("/player/up-left-1.png"));
            upLeft2= ImageIO.read(getClass().getResourceAsStream("/player/up-left-2.png"));
            upRight1= ImageIO.read(getClass().getResourceAsStream("/player/up-right-1.png"));
            upRight2= ImageIO.read(getClass().getResourceAsStream("/player/up-right-2.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/right-1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/right-3.png"));
            right3= ImageIO.read(getClass().getResourceAsStream("/player/right-2.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/left-3.png"));
            left3= ImageIO.read(getClass().getResourceAsStream("/player/left-2.png"));
            fly=ImageIO.read(getClass().getResourceAsStream("/player/fly.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update()  {
        if (keyH.upPressed == true || keyH.downPressed == true   ||keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";

            }
            if (keyH.downPressed == true) {
                direction = "down";

            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
                //direction is for changing img according to the arrow
                //helps to increase the x-axis if right key is pressed
            }
            collisionOn=false;
            gamePanel.collisionChecker.checkTile(this);

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;

                }
            }
        }



            //60FPS,so every 11 frame,it changes the image
            spriteCounter++;
            //if spriteCounter or every 11 frame >11, spriteCounter=0 and count again ;
        if (keyH.downPressed || keyH.rightPressed || keyH.leftPressed || keyH.upPressed){
            if (spriteCounter > 12) {
                if (spriteNum == 1) {//default spriteNum is 1
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 2;
                }
                spriteCounter = 0;
            }
        }
        }


    void jump()  {
        fallingSpeed=jump;
        worldY +=fallingSpeed;
    }
    public void falling()  {
            if (collisionOn==false){
                worldY += fallingSpeed;
                fallingSpeed++;
            }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;
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
                if (!keyH.upPressed){
                    if(spriteNum==1){
                        image = left1;
                    }
                    if(spriteNum==2){
                        image = left2;
                    }
                    if(spriteNum==3){
                        image=left3;
                    }
                }
                else {
                    image=left1;
                }
                break;
            case "right":
                if (keyH.upPressed ==false){
                    if(spriteNum==1){
                        image = right1;
                    }
                    if(spriteNum==2){
                        image = right2;
                    }
                    if(spriteNum==3){
                        image=right3;
                    }
                }
                else {
                    image=right1;
                }
                break;
        }
        g2d.drawImage(image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize, null);
        if (keyH.upPressed ==true){
            g2d.drawImage(fly,screenX,screenY+48,gamePanel.tileSize, gamePanel.tileSize, null);

        }
    }


}
