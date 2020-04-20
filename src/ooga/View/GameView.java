package ooga.View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Controller.KeyBindManager;
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

  //private BooleanProperty W_PRESSED = new SimpleBooleanProperty();
  //private BooleanProperty UP_PRESSED = new SimpleBooleanProperty();

  //private BooleanProperty T_PRESSED = new SimpleBooleanProperty();
  //private BooleanProperty L_PRESSED = new SimpleBooleanProperty();

  AbstractCharacter bunny;
  AbstractCharacter bunny2;

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
    bunny = playerList.get(0).getMyCharacter();
    bunny2 = playerList.get(1).getMyCharacter();
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
    healthBar1.widthProperty().bind(bunny.healthProperty());
    healthBar2.widthProperty().bind(bunny2.healthProperty());
    root.getChildren().add(healthBar1);
    root.getChildren().add(healthBar2);

  }

  @Override
  public void start(Stage primaryStage) {
    mainStage = primaryStage;
    scene = new Scene(root, 1200, 800);

    setKeyBinds();

    primaryStage.setTitle("FIGHT!");
    primaryStage.setScene(scene);
    primaryStage.show();

    AnimationTimer animationTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {
//        System.out.println(bunny.getLEFT_COLLIDE() + " " + bunny.getRIGHT_COLLIDE() + " " +
//                bunny.getINTERSECTS());
        // Update and render
        for(Player player : playerList) {
          GameOver go = null;
          try {
            if (bunny.healthProperty().get() == 0) {
              go = new GameOver(bunny2.getName(), (int) bunny2.healthProperty().get());
              mainStage.close();
              this.stop();
              go.start(new Stage());
            }
            else if (bunny2.healthProperty().get() == 0) {
              go = new GameOver(bunny.getName(), (int) bunny.healthProperty().get());
              mainStage.close();
              this.stop();
              go.start(new Stage());
            }
          } catch (Exception e){
            new ExceptionHelper(e);
          }
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
    };
    animationTimer.start();

  }

  private void checkKeys() {
    if (D_PRESSED.get() && !bunny.getLEFT_COLLIDE()){
      bunny.moveRight();
    }
    if (A_PRESSED.get() && !bunny.getRIGHT_COLLIDE()) {
      bunny.moveLeft();
    }
    if (LEFT_PRESSED.get() && !bunny2.getRIGHT_COLLIDE()) {
      bunny2.moveLeft();
    }
    if (RIGHT_PRESSED.get() && !bunny2.getLEFT_COLLIDE()) {
      bunny2.moveRight();
    }
//    if (T_PRESSED.get()) {
//      bunny.attack();
//    }
//    if (L_PRESSED.get()) {
//      bunny2.attack();
//    }
    if (S_PRESSED.get()) {
      bunny.setCenterY(bunny.getHurtBox().getY() + 3);
    }
    if (DOWN_PRESSED.get()) {
      bunny2.setCenterY(bunny2.getHurtBox().getY() + 3);
    }
  }

  private void setKeyBinds() {
    KeyBindManager keyBindManager = new KeyBindManager();

      scene.setOnKeyPressed(e -> {
        try {
          if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1LRightKey()).get(null)) {
            D_PRESSED.set(true);
            bunny2.getHurtBox().setStroke(Color.YELLOW);
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
            bunny.jump();
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
            bunny.attack();
            if (bunny.getHitBox().getBoundsInParent()
                    .intersects(bunny2.getHurtBox().getBoundsInParent())) {
              bunny2.getHurtBox().setStroke(Color.RED);
              bunny2.setHEALTH(bunny2.getHEALTH() - 10);
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
            bunny2.jump();
          }
        } catch (IllegalAccessException | NoSuchFieldException ex) {
          new ExceptionHelper(ex);
        }
        try {
          if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2AttackKey()).get(null)) {
            bunny2.attack();
            if (bunny2.getHitBox().getBoundsInParent()
                    .intersects(bunny.getHurtBox().getBoundsInParent())) {
              bunny.getHurtBox().setStroke(Color.RED);
              bunny.setHEALTH(bunny.getHEALTH() - 10);
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
            bunny.idle();
          }
        } catch (IllegalAccessException | NoSuchFieldException ex) {
          new ExceptionHelper(ex);
        }
        try {
          if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1LRightKey()).get(null)) {
            D_PRESSED.set(false);
            bunny.idle();
          }
        } catch (IllegalAccessException | NoSuchFieldException ex) {
          new ExceptionHelper(ex);
        }
        try {
          if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer1FallKey()).get(null)) {
            S_PRESSED.set(false);
            bunny.idle();
          }
        } catch (IllegalAccessException | NoSuchFieldException ex) {
          new ExceptionHelper(ex);
        }
        try {
          if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2FallKey()).get(null)) {
            DOWN_PRESSED.set(false);
            bunny2.idle();
          }
        } catch (IllegalAccessException | NoSuchFieldException ex) {
          new ExceptionHelper(ex);
        }
        try {
          if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2LeftKey()).get(null)) {
            LEFT_PRESSED.set(false);
            bunny2.idle();
          }
        } catch (IllegalAccessException | NoSuchFieldException ex) {
          new ExceptionHelper(ex);
        }
        try {
          if (e.getCode() == KeyCode.class.getDeclaredField(keyBindManager.getPlayer2RightKey()).get(null)) {
            RIGHT_PRESSED.set(false);
            bunny2.idle();
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