package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_gun extends SuperObject{
    public Obj_gun(){           
        name="gun";
        try {
            leftImage = ImageIO.read(getClass().getResourceAsStream("/Objects/gun_left1.PNG"));
            rightImage=ImageIO.read(getClass().getResourceAsStream("/Objects/right_gun.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
