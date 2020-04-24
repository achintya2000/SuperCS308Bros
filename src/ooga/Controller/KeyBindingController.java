package ooga.Controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.AbstractCharacter;
import ooga.View.GameView;


public class KeyBindingController {
  private Scene scene;
  private GameView gv;
  private AbstractCharacter player1;
  private AbstractCharacter player2;

  /**
   * Constructor for the KeyBindingController. Simply calling the constructor is sufficient to set
   * up bindings as the method for setting them up is private and called in the controller
   * @param gv where the bindings need to be setup
   * @param scene the scene where the characters are being rendered
   * @param player1 the character of player 1
   * @param player2 the character of player 2
   */
  public KeyBindingController(GameView gv, Scene scene, AbstractCharacter player1, AbstractCharacter player2){
    this.gv = gv;
    this.scene = scene;
    this.player1 = player1;
    this.player2 = player2;
    setKeyBinds();
  }

  private void setKeyBinds() {
    KeyBindManager keyBindManager = new KeyBindManager();

    scene.setOnKeyPressed(e -> {
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1RightKey()).get(null)) {
          gv.d_PRESSEDProperty().set(true);
          player2.getHurtBox().setStroke(Color.YELLOW);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1LeftKey()).get(null)) {
          gv.a_PRESSEDProperty().set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1JumpKey()).get(null)) {
          //player1.jump();
          gv.W_PRESSEDProperty().set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1FallKey()).get(null)) {
          gv.s_PRESSEDProperty().set(true);
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
          gv.LEFT_PRESSEDProperty().set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2RightKey()).get(null)) {
         gv.RIGHT_PRESSEDProperty().set(true);
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2FallKey()).get(null)) {
          gv.DOWN_PRESSEDProperty().set(true);
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
          gv.a_PRESSEDProperty().set(false);
          player1.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1RightKey()).get(null)) {
          gv.d_PRESSEDProperty().set(false);
          player1.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1FallKey()).get(null)) {
          gv.s_PRESSEDProperty().set(false);
          player1.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2FallKey()).get(null)) {
          gv.DOWN_PRESSEDProperty().set(false);
          player2.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2LeftKey()).get(null)) {
          gv.LEFT_PRESSEDProperty().set(false);
          player2.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2RightKey()).get(null)) {
          gv.RIGHT_PRESSEDProperty().set(false);
          player2.idle();
        }
      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1JumpKey()).get(null)) {
          gv.W_PRESSEDProperty().set(false);
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
