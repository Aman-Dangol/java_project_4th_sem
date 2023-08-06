package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x ;
    public int y;
    public int speed=5;
    public int fallingSpeed = 10;
    public int jump =-3;
    public String direction;


    public int spriteCounter=0;
    public int spriteNum=1;
    BufferedImage up1;
    BufferedImage up2;
    BufferedImage up3;
    protected BufferedImage left1;
    public BufferedImage left2;
    BufferedImage left3;
    public BufferedImage right1;
    public BufferedImage right2;
    BufferedImage right3;
    BufferedImage down;

}
