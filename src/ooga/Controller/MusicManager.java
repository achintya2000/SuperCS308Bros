package ooga.Controller;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import ooga.Exceptions.ExceptionHelper;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicManager {

  private Clip clip = null;

  public void playMainMenuMusic() {
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File("./data/sound/MainMenu.wav")));
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      new ExceptionHelper(e);
    }
  }

  public void playBattlefieldMusic() {
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File("./data/sound/Battlefield.wav")));
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      new ExceptionHelper(e);
    }
  }

  public void playFinalDestinationMusic() {
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File("./data/sound/FinalDestination.wav")));
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      new ExceptionHelper(e);
    }
  }

}
