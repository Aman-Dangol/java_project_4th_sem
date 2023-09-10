package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial_40 = new Font("Arial",Font.PLAIN,20);
    public  UI(GamePanel gp){
        this.gp=gp;
    }
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.drawString("health : "+gp.player.health ,50,50);
        g2.drawString("stamina : "+gp.player.stamina ,50,70);
    }

}
