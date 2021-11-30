import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    
    public static void playSound(String audio) throws UnsupportedAudioFileException, IOException, LineUnavailableException{

        File audioFile = new File(audio);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }

    public static void stopSound(){
        clip.close();

    }
}
