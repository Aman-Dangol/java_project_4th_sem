package Object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_gun extends SuperObject{
    GamePanel gp;
    public Obj_gun(GamePanel gp){
        this.gp=gp;
        name="gun";
        try {
            leftImage = ImageIO.read(getClass().getResourceAsStream("/Objects/leftGun.png"));
            utilityTool.scaledImage(leftImage,gp.tileSize,gp.tileSize);
            rightImage=ImageIO.read(getClass().getResourceAsStream("/Objects/rightGun.png"));
            utilityTool.scaledImage(rightImage,gp.tileSize,gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
