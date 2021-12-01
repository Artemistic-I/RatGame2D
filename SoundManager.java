import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
	 * The Sound Manager {@link javafx.scene.Scene}
	 *
	 * @author Alex Gingureanu
	 * @version 1.0
	 */

public class SoundManager {

    static Clip clip;
    static FloatControl volume;
    
    public static void playSound(String audio) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        File audioFile = new File(audio);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();

    }

    public static void pauseSound(){
        clip.stop();

    }

    public static void resumeSound(){
        clip.start();

    }


    public static void stopSound(){
        clip.close();

    }

    public static void setVolume(double percent) {
        volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);   
        float dB = (float) (Math.log(percent) / Math.log(10.0) * 20.0);
        volume.setValue(dB);
    }
}
