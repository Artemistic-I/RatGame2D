import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Class of Audio player
 * 
 * @author Alex Gingureanu
 * @version 1.0.0
 */

public class AudioManager{

    private static File directory;
    private static File[] files;

    private static ArrayList<File> music;

    private static int musicNumber;

    private static Media media;
    private static MediaPlayer mediaPlayer;


    /**
    * Method to create the playlist with the music in the directory
    */
    public static void createPlaylist(){
        
        music = new ArrayList<File>();

        directory = new File("audio");

        files = directory.listFiles();

        if(files != null){
            for(File file: files){
                music.add(file);
                
            }
        }
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        
    }

    /**
    * Method to play the menu music
    */
    public static void playMenuMusic(){
        mediaPlayer.stop();
        musicNumber = 3;

        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    /**
    * Method to play the game music
    */
    public static void playGameMusic(){
        mediaPlayer.stop();
        musicNumber = 2;
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    /**
    * Method to play the winning screen music
    */
    public static void playWinMusic(){
        mediaPlayer.stop();
        musicNumber = 4;
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    /**
    * Method to play the losing screen music
    */
    public static void playLoseMusic(){
        mediaPlayer.stop();
        musicNumber = 1;
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    /**
    * Pause the current music
    */
    public static void pauseMusic(){
        mediaPlayer.pause();
    }

    /**
    * Resume the current music
    */
    public static void resumeMusic(){
        mediaPlayer.play();
    }

    
    /** 
     * Set the volume of the media player
     * @param vol the value from the settings slider
     */
    public static void setVol(double vol){
        mediaPlayer.setVolume(vol);
    }
    
}
