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
        Media music = new Media(new File("/Users/achintya20/CS308/final_team11/data/sound/Battlefield.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setAutoPlay(true);
    }

}
