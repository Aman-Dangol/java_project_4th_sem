package main;

import entity.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Weapon {
    public BufferedImage gunRight;
    public BufferedImage gunLeft;
    public int centerX,centerY;

    Player player;
    public Weapon(Player player){
        this.player=player;
    }
    public void drawWeapon(Graphics2D g2d,GamePanel gamePanel){
        centerX=player.screenX+( gamePanel.tileSize/2);
        centerY=player.screenY+( gamePanel.tileSize/2);

        AffineTransform originalTransform = g2d.getTransform();

        g2d.rotate(player.angle,centerX,centerY);

        if (gamePanel.mouseH.X>=500){
            g2d.drawImage(gunRight,player.screenX,player.screenY+9 ,null);

        }
        else {
            g2d.drawImage(gunLeft,player.screenX+gamePanel.tileSize+4,player.screenY-9, -gamePanel.tileSize, gamePanel.tileSize ,null);
        }

        g2d.setTransform(originalTransform);

    }

}
