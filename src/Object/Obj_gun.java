package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_gun extends SuperObject{
    public Obj_gun(){           
        name="gun";
        try {
            leftImage = ImageIO.read(getClass().getResourceAsStream("/Objects/leftGun.png"));
            rightImage=ImageIO.read(getClass().getResourceAsStream("/Objects/rightGun.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
