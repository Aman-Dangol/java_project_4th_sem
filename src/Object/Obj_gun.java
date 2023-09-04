package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_gun extends SuperObject{
    public Obj_gun(){           
        name="gun";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/Objects/gun_left.PNG"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
