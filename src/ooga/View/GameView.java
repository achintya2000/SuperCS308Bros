package ooga.View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Controller.KeyBindManager;
import ooga.Controller.KeyBindingController;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.AbstractCharacter;
import ooga.Model.Player;

import java.util.ArrayList;
import ooga.Model.Stages.Platform;

public class GameView extends Application implements ViewInternal {

  private final double GRAVITY = 1.5;

  private Stage mainStage;
  private ArrayList<Player> playerList;
  private Scene scene;
  private Pane root;

  private BooleanProperty A_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty D_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty LEFT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty RIGHT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty S_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty DOWN_PRESSED = new SimpleBooleanProperty();

  public BooleanProperty a_PRESSEDProperty() {
    return A_PRESSED;
  }

  public BooleanProperty d_PRESSEDProperty() {
    return D_PRESSED;
  }

  public BooleanProperty LEFT_PRESSEDProperty() {
    return LEFT_PRESSED;
  }

  public BooleanProperty RIGHT_PRESSEDProperty() {
    return RIGHT_PRESSED;
  }

  public BooleanProperty s_PRESSEDProperty() {
    return S_PRESSED;
  }

  public BooleanProperty DOWN_PRESSEDProperty() {
    return DOWN_PRESSED;
  }


  //private BooleanProperty W_PRESSED = new SimpleBooleanProperty();
  //private BooleanProperty UP_PRESSED = new SimpleBooleanProperty();

  //private BooleanProperty T_PRESSED = new SimpleBooleanProperty();
  //private BooleanProperty L_PRESSED = new SimpleBooleanProperty();

  AbstractCharacter player1;
  AbstractCharacter player2;

  int y;
  int y2;

  ArrayList<Platform> platforms;

  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {
  }


  public GameView(ArrayList playerlist, Pane root, ooga.Model.Stages.Stage chosenStage) {
    this.playerList = playerlist;
    this.root = root;
    player1 = playerList.get(0).getMyCharacter();
    player2 = playerList.get(1).getMyCharacter();
    platforms = chosenStage.getPlatforms();
    BackgroundImage stageBackground = new BackgroundImage(chosenStage.getBackground(),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    root.setBackground(new Background(stageBackground));
    root.getChildren().addAll(platforms);
    Rectangle healthBar1 = new Rectangle(100, 100, 1000, 50);
    Rectangle healthBar2 = new Rectangle(900, 100, 1000, 50);
    healthBar1.setFill(Color.GREEN);
    healthBar2.setFill(Color.GREEN);
    healthBar1.widthProperty().bind(player1.healthProperty());
    healthBar2.widthProperty().bind(player2.healthProperty());
    root.getChildren().add(healthBar1);
    root.getChildren().add(healthBar2);

  }

  @Override
  public void start(Stage primaryStage) {
    mainStage = primaryStage;
    scene = new Scene(root, 1200, 800);

    new KeyBindingController(this, scene, player1, player2);

    primaryStage.setTitle("FIGHT!");
    primaryStage.setScene(scene);
    primaryStage.show();

    AnimationTimer animationTimer = new GameViewAnimation(this, playerList, platforms, mainStage);
    animationTimer.start();

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