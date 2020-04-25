package ooga.Controller;

import ooga.Exceptions.ExceptionHelper;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class MusicManager {

  public static final String MAIN_MENU_MUSIC = "./data/sound/MainMenu.wav";
  public static final String BATTLEFIELD_MUSIC = "./data/sound/Battlefield.wav";
  public static final String FINAL_DESTINATION_MUSIC = "./data/sound/FinalDestination.wav";
  public static final String HIT_CLIP = "./data/sound/hit.wav";
  private static Clip clip = null;

  public static void playMainMenuMusic() {
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File(MAIN_MENU_MUSIC)));
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      new ExceptionHelper(e);
    }
  }

  public static void playBattlefieldMusic() {
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File(BATTLEFIELD_MUSIC)));
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      new ExceptionHelper(e);
    }
  }

  public static void playFinalDestinationMusic() {
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File(FINAL_DESTINATION_MUSIC)));
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      new ExceptionHelper(e);
    }
  }

  public static void playHitSound() {
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(new File(HIT_CLIP)));
      clip.start();
    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
      new ExceptionHelper(e);
    }
  }

  public static void clearMusic() {
    clip.close();
  }

}
