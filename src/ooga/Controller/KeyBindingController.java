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
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1LeftKey()).get(null)) {
          gv.getPlayer1LeftProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1RightKey()).get(null)) {
          gv.getPlayer1RightProp().set(true);
          player2.getHurtBox().setStroke(Color.YELLOW);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1FallKey()).get(null)) {
          gv.getPlayer1FallProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1JumpKey()).get(null)) {
            gv.getPlayer1JumpProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1AttackKey()).get(null)) {
          gv.getPlayer1AttackProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2LeftKey()).get(null)) {
          gv.getPlayer2LeftProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2RightKey()).get(null)) {
          gv.getPlayer2RightProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2FallKey()).get(null)) {
          gv.getPlayer2FallProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2JumpKey()).get(null)) {
          gv.getPlayer2JumpProp().set(true);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2AttackKey()).get(null)) {
          gv.getPlayer2AttackProp().set(true);
        }

      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
    });

    scene.setOnKeyReleased(e -> {
      try {
        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1LeftKey()).get(null)) {
          gv.getPlayer1LeftProp().set(false);
          player1.idle();
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1RightKey()).get(null)) {
          gv.getPlayer1RightProp().set(false);
          player1.idle();
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1FallKey()).get(null)) {
          gv.getPlayer1FallProp().set(false);
          player1.idle();
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1JumpKey()).get(null)) {
          gv.getPlayer1JumpProp().set(false);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1AttackKey()).get(null)) {
          gv.getPlayer1AttackProp().set(false);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2LeftKey()).get(null)) {
          gv.getPlayer2LeftProp().set(false);
          player2.idle();
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2RightKey()).get(null)) {
          gv.getPlayer2RightProp().set(false);
          player2.idle();
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2FallKey()).get(null)) {
          gv.getPlayer2FallProp().set(false);
          player2.idle();
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2JumpKey()).get(null)) {
          gv.getPlayer2JumpProp().set(false);
        }

        if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2AttackKey()).get(null)) {
          gv.getPlayer2AttackProp().set(false);
        }

      } catch (IllegalAccessException | NoSuchFieldException ex) {
        new ExceptionHelper(ex);
      }
    });
  }

}
