package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundUrl[]=new URL[30];

    Sound(){
        soundUrl[0]=getClass().getResource("/sound/background_music.wav");
        soundUrl[1]=getClass().getResource("/sound/gun_shot.wav");
        soundUrl[2]=getClass().getResource("/sound/intro.wav");
        soundUrl[3]=getClass().getResource("/sound/mini-militia1.wav");
        soundUrl[4]=getClass().getResource("/sound/splash.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){
            e.printStackTrace();

        }

    }
    public  void play(){

        clip.start();

    }
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);

    }
    public void stop(){
        clip.stop();

    }

}

