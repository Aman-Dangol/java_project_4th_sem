
package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


    public class Background{
        BufferedImage background,titleBackground;

        GamePanel gp;
        public Background(GamePanel gp) throws IOException {
            this.gp=gp;
            background= ImageIO.read(getClass().getResourceAsStream("/background/background1.png"));
            titleBackground=ImageIO.read(getClass().getResourceAsStream("/background/titlebg.png"));
        }
        public void draw(Graphics g2){
            if (gp.gameState==gp.titleState){
                g2.drawImage(titleBackground,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            else
                g2.drawImage(background,0,0,gp.screenWidth,gp.screenHeight,null);
        }
    }

