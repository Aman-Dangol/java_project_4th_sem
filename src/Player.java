import java.awt.*;
import java.awt.event.KeyListener;

public class Player extends Entity{
    GamePanel gamePanel;
    Player(GamePanel gp){
        x=100;
        y=100;
        gamePanel=gp;
    }
    void update(String direction){
        if(direction=="up"){
            y-=speed;
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

    }
    void falling(){
        if(y<gamePanel.height-gamePanel.tileSize){
            y += 2;
        }
        else
            y= gamePanel.height-gamePanel.tileSize;
        System.out.println(y);

    }
    void draw(Graphics2D g2d){
        g2d.fillRect(x,y, gamePanel.tileSize, gamePanel.tileSize);
        g2d.dispose();
    }

}
