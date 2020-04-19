package ooga.Controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicManager {

    public void playMainMenuMusic() {
        Media music = new Media(getClass().getResourceAsStream("Battlefield.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setAutoPlay(true);
    }

    public void playBattlefieldMusic() {
        Media music = new Media(getClass().getClassLoader().getResource("sound/Battlefield.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

}
