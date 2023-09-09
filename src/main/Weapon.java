package main;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Weapon {
    public BufferedImage gunRight;
    public BufferedImage gunLeft;
    public int centerX,centerY;

    Player player;
    public Weapon(Player player){
        this.player=player;
    }
    public void drawWeapon(Graphics2D g2d){
        centerX=player.screenX+( player.gamePanel.tileSize/2);
        centerY=player.screenY+( player.gamePanel.tileSize/2);
        g2d.rotate(player.angle,centerX,centerY);

        if (player.gamePanel.mouseH.X>=500){
            g2d.drawImage(gunRight,player.screenX,player.screenY+9, player.gamePanel.tileSize,player.gamePanel.tileSize ,null);

        }
        else {
            g2d.drawImage(gunLeft,player.screenX+player.gamePanel.tileSize+4,player.screenY-9, -player.gamePanel.tileSize, player.gamePanel.tileSize ,null);
        }


    }
}
