package entity;

import entity.Entity;
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

        screenX=gamePanel.width/2-(gp.tileSize/2);
        screenY=(gamePanel.height/2-(gp.tileSize/2))+90;


        setDefaultValues();
        getPlayerImage();
        direction="left";


    }
    public void setDefaultValues(){
        x=gamePanel.tileSize*23;
        y=gamePanel.tileSize*14;
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() throws InterruptedException {
            if (keyH.up == true) {
                direction="up";
                jump();
            }
            if (keyH.up==false){
                direction=prevDirection;
            }
            if (keyH.down == true) {
                direction = "down";
                y+= speed;
            }
            if (keyH.left == true) {
                direction = "left";
                prevDirection="left";
                x -= speed;
            }
            if (keyH.right == true) {
                //direction is for changing img according to the arrow
                direction = "right";
                prevDirection="right";
                //helps to increase the x-axis if right key is pressed
                x+= speed;
            }
            //60FPS,so every 11 frame,it changes the image
            spriteCounter++;
            //if spriteCounter or every 11 frame >11, spriteCounter=0 and count again ;
        if (keyH.down|| keyH.right|| keyH.left|| keyH.right){
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


    void jump() throws InterruptedException {
        fallingSpeed=jump;
        y+=fallingSpeed;

    }
    public void falling() throws InterruptedException {
        if (y < gamePanel.height - gamePanel.tileSize) {
            y += fallingSpeed;
            fallingSpeed++;

    }
        else{
                y = gamePanel.height - gamePanel.tileSize;
            }

        }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(prevDirection=="left") {
                    if (spriteNum == 1) {
                        image = upLeft1;
                    }
                    if (spriteNum == 2) {
                        image = upLeft2;
                    }
                    if (spriteNum == 3) {
                        image = upLeft2;
                    }
                } else if (prevDirection=="right") {
                        if (spriteNum == 1) {
                            image = upRight1;
                        }
                        if (spriteNum == 2) {
                            image = upRight2;
                        }
                        if (spriteNum == 3) {
                            image = upRight2;
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
        g2d.drawImage(image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
