import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

//not completed
public class Sound {
    Clip clip  ;
    URL soundURL;
    public Sound(){
        soundURL=getClass().getResource("/sound/splash.wav");
        // soundURL[1]=getClass().getResource("/sound/intro.mp3");


    }
    public void setFile(URL url) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip=AudioSystem.getClip();
            clip.open(ais);


        }
        catch (Exception e) {
            e.printStackTrace();

        }


    }
    public void playLoop() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }




    public void playSound(){

    }
}
