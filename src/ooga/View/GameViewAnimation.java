package ooga.View;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Controller.ControllerInternal;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.AbstractCharacter;
import ooga.Model.Player;
import ooga.Model.Stages.Platform;

public class GameViewAnimation extends AnimationTimer implements ControllerInternal {

  private static final double GRAVITY = 1.5;
  private GameView gv;
  private AbstractCharacter player1;
  private AbstractCharacter player2;
  private ArrayList<Player> playerList;
  private Stage mainStage;
  private ArrayList<Platform> platforms;
  public GameViewAnimation(GameView gv, ArrayList<Player> playerList, ArrayList<Platform> platformList,
      Stage gameViewStage){
    super();
    this.gv = gv;
    this.playerList = playerList;
    platforms = platformList;
    player1 = playerList.get(0).getMyCharacter();
    player2 = playerList.get(1).getMyCharacter();
    mainStage = gameViewStage;
  }
  @Override
  public void handle(long now) {
    for(Player player : playerList) {
      isGameOver();
      AbstractCharacter character = player.getMyCharacter();
      if (!character.getINTERSECTS() || character.getRIGHT_COLLIDE() || character.getLEFT_COLLIDE()) {
        character.setCenterY(character.getHurtBox().getY() + GRAVITY);
      }

      character.setINTERSECTS(false);
      character.setRIGHT_COLLIDE(false);
      character.setLEFT_COLLIDE(false);

      for (Rectangle platform : platforms) {

        if (character.getHurtBox().getBoundsInParent().intersects(platform.getBoundsInParent())) {
          character.setINTERSECTS(true);
        }

        if (character.getHurtBox().getBoundsInParent().intersects(platform.getBoundsInParent())) {
          if (character.getHurtBox().getBoundsInParent().getMaxY() > platform.getBoundsInParent().getMinY() + 5) {
            if (character.getHurtBox().getBoundsInParent().getMaxX() > platform.getBoundsInParent().getMaxX()) {
              if (character.getHurtBox().getBoundsInParent().getMinX() < platform.getBoundsInParent().getMaxX()) {
                character.setRIGHT_COLLIDE(true);
              }
            }

            if (character.getHurtBox().getBoundsInParent().getMinX() < platform.getBoundsInParent().getMinX()) {
              if (character.getHurtBox().getBoundsInParent().getMaxX() > platform.getBoundsInParent().getMinX()) {
                character.setLEFT_COLLIDE(true);
              }
            }
          }
        }
      }
    }

    checkKeys();
  }

  private void checkKeys() {
    if (gv.d_PRESSEDProperty().get() && !player1.getLEFT_COLLIDE()){
      player1.moveRight();
    }
    if (gv.a_PRESSEDProperty().get() && !player1.getRIGHT_COLLIDE()) {
      player1.moveLeft();
    }
    if (gv.LEFT_PRESSEDProperty().get() && !player2.getRIGHT_COLLIDE()) {
      player2.moveLeft();
    }
    if (gv.RIGHT_PRESSEDProperty().get() && !player2.getLEFT_COLLIDE()) {
      player2.moveRight();
    }
//    if (T_PRESSED.get()) {
//      bunny.attack();
//    }
//    if (L_PRESSED.get()) {
//      bunny2.attack();
//    }
    if (gv.s_PRESSEDProperty().get()) {
      player1.setCenterY(player1.getHurtBox().getY() + 3);
    }
    if (gv.DOWN_PRESSEDProperty().get()) {
      player2.setCenterY(player2.getHurtBox().getY() + 3);
    }
    if (gv.W_PRESSEDProperty().get()) {
      player1.jump();
    }
    if (gv.T_PRESSEDProperty().get()) {
      player1.attack();
      if (player1.getHitBox().getBoundsInParent()
              .intersects(player2.getHurtBox().getBoundsInParent())) {
        player2.getHurtBox().setStroke(Color.RED);
        player2.setHEALTH(player2.getHEALTH() - 10);
      }
    }
  }

  @Override
  public void checkWithinBoundries() {

  }

  @Override
  public void isGameOver() {
    GameOver go = null;
    try {
      if (player1.healthProperty().get() == 0) {
        go = new GameOver(player2.getName(), (int) player2.healthProperty().get());
        mainStage.close();
        this.stop();
        go.start(new Stage());
      }
      else if (player2.healthProperty().get() == 0) {
        go = new GameOver(player1.getName(), (int) player1.healthProperty().get());
        mainStage.close();
        this.stop();
        go.start(new Stage());
      }
    } catch (Exception e){
      new ExceptionHelper(e);
    }
  }
}
