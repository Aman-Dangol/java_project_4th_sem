import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;

    String prevDirection="right";

    Player(GamePanel gp){
        x=100;
        y=100;
        gamePanel=gp;
        getPlayerImage();
        direction="right";
    }
    void getPlayerImage(){
        try {
            left1= ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/player/left-2.png"));;
            right2=ImageIO.read(getClass().getResourceAsStream("/player/right-2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/player/right-1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void update(String direction){
        this.direction=direction;
        if(direction=="up"){
            jump();
        }
        if(direction=="down"){
            y+=speed;
        }
        if(direction=="left"){
            x-=speed;
        }
        if(direction=="right"){
            x+=speed;
        }
        spriteCounter++;
        if (spriteCounter>12){
            if (spriteNum==1){
                spriteNum=2;
            }else {
                spriteNum=1;
            }
            spriteCounter=0;
        }

    }
    void jump(){
        fallingSpeed=jump;
        y+=fallingSpeed;
    }
    void falling(){
        if(y<gamePanel.height-gamePanel.tileSize) {
            y += fallingSpeed;
            fallingSpeed++;

        }
        else {
            y = gamePanel.height - gamePanel.tileSize;
        }

    }
    void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (prevDirection=="left"){
                    image=left1;
                }else if (prevDirection=="right"){
                    image=right2;
                }

                break;
            case "left":
                if (spriteNum==1){
                    image=left1;
                }else {
                    image=left2;
                }
                prevDirection="left";
                break;
            case "right":
                if (spriteNum==1){
                    image=right1;
                }
                if (spriteNum==2){
                    image=right2;

                }
                prevDirection="right";
                break;

        }
        g2d.drawImage(image,x,y,gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
