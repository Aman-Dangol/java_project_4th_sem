
package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


    public class Background{
        BufferedImage background;
        GamePanel gp;
        public Background(GamePanel gp) throws IOException {
            this.gp=gp;
            background= ImageIO.read(getClass().getResourceAsStream("/background/background1.png"));
        }
        public void draw(Graphics g2){
            g2.drawImage(background,0,0,gp.screenWidth,gp.screenHeight,null);
        }
    }

