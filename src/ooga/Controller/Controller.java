package ooga.Controller;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Controller.ControllerInternal;
import ooga.Controller.GameMode;
import ooga.Controller.MusicManager;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.AbstractCharacter;
import ooga.Model.Player;
import ooga.Model.StageClasses.Platform;
import ooga.View.GameOver;
import ooga.View.GameView;

public class Controller extends AnimationTimer implements ControllerInternal {

  private static final double GRAVITY = 1.8;
  private GameView gv;
  private AbstractCharacter player1;
  private AbstractCharacter player2;
  private ArrayList<Player> playerList;
  private Stage mainStage;
  private ArrayList<Platform> platforms;
  private String gameMode;

  public Controller(GameView gv, ArrayList<Player> playerList, ArrayList<Platform> platformList,
                    Stage gameViewStage, String gameMode){
    super();
    this.gv = gv;
    this.playerList = playerList;
    this.gameMode = gameMode;
    platforms = platformList;
    player1 = playerList.get(0).getMyCharacter();
    player2 = playerList.get(1).getMyCharacter();
    mainStage = gameViewStage;
  }
  @Override
  public void handle(long now) {
    for(Player player : playerList) {
      isGameOver(gv.getIsLocal());
      AbstractCharacter character = player.getMyCharacter();
      if (!character.getINTERSECTS() || character.getRIGHT_COLLIDE() || character.getLEFT_COLLIDE() || character.getBOTTOM_COLLIDE()) {
        character.setCenterY(character.getHurtBox().getY() + GRAVITY);
      }

      character.setINTERSECTS(false);
      character.setRIGHT_COLLIDE(false);
      character.setLEFT_COLLIDE(false);
      character.setHOLLOW_COLLIDE(true);
      character.setBOTTOM_COLLIDE(false);

      for (Platform platform : platforms) {

        if (character.getHurtBox().getBoundsInParent().intersects(platform.getBoundsInParent())) {
          character.setINTERSECTS(true);
          character.setHOLLOW_COLLIDE(platform.getHollow());



          if(character.getHurtBox().getBoundsInParent().getMaxY() > platform.getBoundsInParent().getMaxY()){
            if(character.getHurtBox().getBoundsInParent().getMinY() <  platform.getBoundsInParent().getMaxY())
            {
              if(!platform.getHollow()){
                character.setBOTTOM_COLLIDE(true);
              }
            }
          }

          if(character.getHurtBox().getBoundsInParent().getMaxY() < platform.getBoundsInParent().getMaxY()){
            if(character.getHurtBox().getBoundsInParent().getMinY() >  platform.getBoundsInParent().getMinY())
            {
              if(!platform.getHollow()){
                character.setBOTTOM_COLLIDE(true);
              }
            }
          }


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
    if (gv.getPlayer1JumpProp().get() && !player1.getBOTTOM_COLLIDE()){
      player1.jump();
    }
    if (gv.getPlayer1RightProp().get() && !player1.getLEFT_COLLIDE()){
      player1.moveRight();
    }
    if (gv.getPlayer1LeftProp().get() && !player1.getRIGHT_COLLIDE()) {
      player1.moveLeft();
    }
    if (gv.getPlayer2LeftProp().get() && !player2.getRIGHT_COLLIDE()) {
      player2.moveLeft();
    }
    if (gv.getPlayer2RightProp().get() && !player2.getLEFT_COLLIDE()) {
      player2.moveRight();
    }
    if (gv.getPlayer1FallProp().get() && player1.getHOLLOW_COLLIDE()) {
      player1.setCenterY(player1.getHurtBox().getY() + 15);
    }
    if (gv.getPlayer2FallProp().get() && player2.getHOLLOW_COLLIDE()) {
      player2.setCenterY(player2.getHurtBox().getY() + 15);
    }
    if (gv.getPlayer1AttackProp().get()) {
      player1.attack();
      if (player1.getHitBox().getBoundsInParent()
              .intersects(player2.getHurtBox().getBoundsInParent())) {
        player2.getHurtBox().setStroke(Color.RED);
        player2.setHEALTH(player2.getHEALTH() - 10);
        MusicManager.playHitSound();
      }
    }
    if (gv.getPlayer2JumpProp().get()) {
      player2.jump();
    }
    if (gv.getPlayer2AttackProp().get()) {
      player2.attack();
      if (player2.getHitBox().getBoundsInParent()
              .intersects(player1.getHurtBox().getBoundsInParent())) {
        player1.getHurtBox().setStroke(Color.RED);
        player1.setHEALTH(player1.getHEALTH() - 10);
        MusicManager.playHitSound();
      }
    }
  }

  @Override
  public void checkWithinBoundries() {

  }

  @Override
  public void isGameOver(boolean isLocal) {
    GameOver go = null;
    switch (gameMode){
      case "LIVES":
        try {
          if(player1.healthProperty().get() == 0) {
            player1.STONKSProperty().set(player1.STONKSProperty().get() - 1);
            player1.healthProperty().set(100);
          }
          if(player2.healthProperty().get() == 0) {
            player2.STONKSProperty().set(player2.STONKSProperty().get() - 1);
            player2.healthProperty().set(100);
          }
          if (player1.STONKSProperty().get() == 0) {
            go = new GameOver(player2.getName(), (int) player2.healthProperty().get(), isLocal);
            mainStage.close();
            this.stop();
            go.start(new Stage());
          }
          else if (player2.STONKSProperty().get() == 0) {
            go = new GameOver(player1.getName(), (int) player1.healthProperty().get(), isLocal);
            mainStage.close();
            this.stop();
            go.start(new Stage());
          }
        } catch (Exception e){
          new ExceptionHelper(e);
        }
        break;
      case "HEALTH":
        try {
          if (player1.healthProperty().get() == 0) {
            go = new GameOver(player2.getName(), (int) player2.healthProperty().get(),isLocal);
            mainStage.close();
            this.stop();
            go.start(new Stage());
          }
          else if (player2.healthProperty().get() == 0) {
            go = new GameOver(player1.getName(), (int) player1.healthProperty().get(), isLocal);
            mainStage.close();
            this.stop();
            go.start(new Stage());
          }
        } catch (Exception e){
          new ExceptionHelper(e);
        }
        break;
    }
  }
}
