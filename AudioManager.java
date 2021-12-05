import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager{

    private static File directory;
    private static File[] files;

    private static ArrayList<File> music;

    private static int musicNumber;

    private static Media media;
    private static MediaPlayer mediaPlayer;



    public static void createPlaylist(){
        
        music = new ArrayList<File>();

        directory = new File("audio");

        files = directory.listFiles();

        if(files != null){
            for(File file: files){
                music.add(file);
                System.out.println(file);
            }
        }
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        
    }

    public static void playMenuMusic(){
        mediaPlayer.stop();
        musicNumber = 3;

        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    public static void playGameMusic(){
        mediaPlayer.stop();
        musicNumber = 2;
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    public static void playWinMusic(){
        mediaPlayer.stop();
        musicNumber = 4;
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    public static void playLoseMusic(){
        mediaPlayer.stop();
        musicNumber = 1;
        
        media = new Media(music.get(musicNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    public static void pauseMusic(){
        mediaPlayer.pause();
    }

    public static void resumeMusic(){
        mediaPlayer.play();
    }
    
}
