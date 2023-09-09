package main;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Weapon {
    public BufferedImage gun;

    Player player;
    public Weapon(Player player){
        this.player=player;
    }
    public void drawWeapon(Graphics2D g2d , BufferedImage weapon){
        g2d.drawImage(weapon,player.screenX,player.screenY+9, player.gamePanel.tileSize,player.gamePanel.tileSize ,null);
    }
}
