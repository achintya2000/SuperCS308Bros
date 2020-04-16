package ooga.View;

import java.io.FileNotFoundException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.CharacterSuper;
import ooga.Model.Player;

import java.util.ArrayList;
import ooga.Model.Stages.StageBuilder;

public class GameView extends Application implements ViewInternal {

  private final int GRAVITY = 1;
  private final int RECTANGLE_OFFSET = 2;

  private ArrayList<Player> playerList;
  private Scene scene;
  private Pane root;

  private BooleanProperty A_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty D_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty LEFT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty RIGHT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty S_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty DOWN_PRESSED = new SimpleBooleanProperty();


  CharacterSuper bunny;
  CharacterSuper bunny2;
  int y;
  int y2;

  ArrayList<Rectangle> platforms;

  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {
  }


  public void setKeyBinds() {
    // Main game loop
    AnimationTimer animationTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        for (Rectangle platform : platforms) {
          if (bunny.getHurtBox().getBoundsInParent().intersects(platform.getBoundsInParent())) {
            y -= RECTANGLE_OFFSET;
            bunny.setCenterY(y);
          }
          if (!bunny.getHurtBox().getBoundsInParent().intersects(platform.getBoundsInParent())) {
            y += GRAVITY;
            bunny.setCenterY(y);
          }
          if (bunny2.getHurtBox().getBoundsInParent().intersects(platform.getBoundsInParent())) {
            y2 -= RECTANGLE_OFFSET;
            bunny2.setCenterY(y2);
          }
          if (!bunny2.getHurtBox().getBoundsInParent().intersects(platform.getBoundsInParent())) {
            y2 += GRAVITY;
            bunny2.setCenterY(y2);
          }
        }

        //Update and render
        if (D_PRESSED.get()) {
          bunny.moveRight();
        }
        if (A_PRESSED.get()) {
          bunny.moveLeft();
        }
        if (LEFT_PRESSED.get()) {
          bunny2.moveLeft();
        }
        if (RIGHT_PRESSED.get()) {
          bunny2.moveRight();
        }
        if (S_PRESSED.get()) {
          y += 3;
          bunny.setCenterY(y);
        }
        if (DOWN_PRESSED.get()) {
          y2 += 3;
          bunny2.setCenterY(y2);
        }
      }
    };
    animationTimer.start();
  }

  public GameView(ArrayList playerlist, Pane root, ooga.Model.Stages.Stage chosenStage) {
    this.playerList = playerlist;
    this.root = root;
    bunny = playerList.get(0).getMyCharacter();
    bunny2 = playerList.get(1).getMyCharacter();
    y = bunny.getCenterY();
    y2 = bunny2.getCenterY();
    platforms = chosenStage.getPlatforms();
    BackgroundImage stageBackground = new BackgroundImage(chosenStage.getBackground(),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    root.setBackground(new Background(stageBackground));
    root.getChildren().addAll(platforms);
  }

  @Override
  public void start(Stage primaryStage) {
    scene = new Scene(root, 1200, 800);
    scene.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.D) {
        D_PRESSED.set(true);
        bunny2.getHurtBox().setStroke(Color.YELLOW);
      }
      if (e.getCode() == KeyCode.S) {
        S_PRESSED.set(true);
      }
      if (e.getCode() == KeyCode.T) {
        bunny.attack();
        if (bunny.getHitBox().getBoundsInParent()
            .intersects(bunny2.getHurtBox().getBoundsInParent())) {
          bunny2.getHurtBox().setStroke(Color.RED);
        }

      }
      if (e.getCode() == KeyCode.W) {
        bunny.jump();
      }
      if (e.getCode() == KeyCode.A) {
        A_PRESSED.set(true);
      }
      if (e.getCode() == KeyCode.LEFT) {
        LEFT_PRESSED.set(true);
      }
      if (e.getCode() == KeyCode.RIGHT) {
        RIGHT_PRESSED.set(true);
      }
      if (e.getCode() == KeyCode.DOWN) {
        DOWN_PRESSED.set(true);
      }
      if (e.getCode() == KeyCode.UP) {
        bunny2.jump();
      }
      if (e.getCode() == KeyCode.L) {
        bunny2.attack();
      }
    });

    scene.setOnKeyReleased(e -> {
      if (e.getCode() == KeyCode.A) {
        A_PRESSED.set(false);
      }
      if (e.getCode() == KeyCode.D) {
        D_PRESSED.set(false);
      }
      if (e.getCode() == KeyCode.S) {
        S_PRESSED.set(false);
      }
      if (e.getCode() == KeyCode.DOWN) {
        DOWN_PRESSED.set(false);
      }
      if (e.getCode() == KeyCode.LEFT) {
        LEFT_PRESSED.set(false);
      }
      if (e.getCode() == KeyCode.RIGHT) {
        RIGHT_PRESSED.set(false);
      }

    });

    primaryStage.setTitle("FIGHT!");
    primaryStage.setScene(scene);
    primaryStage.show();
    setKeyBinds();

  }
}