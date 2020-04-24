package ooga.Controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Player;

public class KeyBindingController {
  private Scene scene;
  private Player player1;
  private Player player2;

  public KeyBindingController(Scene scene, Player player1, Player player){
    
  }
  private void setKeyBinds() {
    KeyBindManager keyBindManager = new KeyBindManager();

    scene.setOnKeyPressed(e -> {
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1RightKey()).get(null)) {
          D_PRESSED.set(true);
          player2.getHurtBox().setStroke(Color.YELLOW);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1LeftKey()).get(null)) {
          A_PRESSED.set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1JumpKey()).get(null)) {
          player1.jump();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1FallKey()).get(null)) {
          S_PRESSED.set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1AttackKey()).get(null)) {
          player1.attack();
          if (player1.getHitBox().getBoundsInParent()
              .intersects(player2.getHurtBox().getBoundsInParent())) {
            player2.getHurtBox().setStroke(Color.RED);
            player2.setHEALTH(player2.getHEALTH() - 10);
          }
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      //~~~~~~~~~~~~~~~ Player 2 ~~~~~~~~~~~~~~//
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2LeftKey()).get(null)) {
          LEFT_PRESSED.set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2RightKey()).get(null)) {
          RIGHT_PRESSED.set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2FallKey()).get(null)) {
          DOWN_PRESSED.set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2JumpKey()).get(null)) {
          player2.jump();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2AttackKey()).get(null)) {
          player2.attack();
          if (player2.getHitBox().getBoundsInParent()
              .intersects(player1.getHurtBox().getBoundsInParent())) {
            player1.getHurtBox().setStroke(Color.RED);
            player1.setHEALTH(player1.getHEALTH() - 10);
          }
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
    });

    scene.setOnKeyReleased(e -> {
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1LeftKey()).get(null)) {
          A_PRESSED.set(false);
          player1.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1RightKey()).get(null)) {
          D_PRESSED.set(false);
          player1.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1FallKey()).get(null)) {
          S_PRESSED.set(false);
          player1.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2FallKey()).get(null)) {
          DOWN_PRESSED.set(false);
          player2.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2LeftKey()).get(null)) {
          LEFT_PRESSED.set(false);
          player2.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2RightKey()).get(null)) {
          RIGHT_PRESSED.set(false);
          player2.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
//        if (e.getCode() == KeyCode.T) {
//          T_PRESSED.set(false);
//        }
//        if (e.getCode() == KeyCode.L) {
//          L_PRESSED.set(false);
//        }
//        bunny.idle();
//        bunny2.idle();
    });
  }

}
