package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_key extends SuperObject{
    public Obj_key(){
        name="key";
        try {
            image= ImageIO.read(getClass().getResourceAsStream("/Objects/gun_left.PNG"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
