package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed=5;
    public int fallingSpeed = 10;
    public int jump =-3;
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
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
    public Rectangle solidArea;
    public boolean collisionOn=false;


}
