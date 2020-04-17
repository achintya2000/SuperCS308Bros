package ooga.Model.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Model.Characters.Bunny;
import ooga.Model.Characters.Ghost;

public class SpriteTester extends Application {

    int y;
    int y2;

    private BooleanProperty A_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty D_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty LEFT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty RIGHT_PRESSED = new SimpleBooleanProperty();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Bunny bunny = new Bunny("bunny", 100, 400);
        Bunny bunny2 = new Bunny("bunny2", 400, 500);

        Ghost ghost = new Ghost("ghost", 300, 400);

        Rectangle stage = new Rectangle(100, 800, 500, 100);

        Pane root = bunny.getRoot();
        root.getChildren().add((bunny2.getRoot()));
        root.getChildren().add(ghost.getRoot());
        root.getChildren().add(stage);
        root.getChildren().add(bunny2.getRoot());

        //Creating a scene object
        Scene scene = new Scene(root, 1000, 1000);

        //Setting title to the Stage
        primaryStage.setTitle("Sprite Animation");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();

        y = bunny.getCenterY();
        y2 = bunny2.getCenterY();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D) {
                D_PRESSED.set(true);
            }
            if (e.getCode() == KeyCode.T) {
                bunny.attack();
                if(bunny.getHitBox().getBoundsInParent().intersects(bunny2.getHurtBox().getBoundsInParent())){
                    bunny2.getHurtBox().setStroke(Color.GREEN);
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
            if (e.getCode() == KeyCode.UP) {
                bunny2.jump();
            }
            if (e.getCode() == KeyCode.L) {
                bunny2.attack();
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.A){
                A_PRESSED.set(false);
            }
            if (e.getCode() == KeyCode.D){
                D_PRESSED.set(false);
            }
            if (e.getCode() == KeyCode.LEFT){
                LEFT_PRESSED.set(false);
            }
            if (e.getCode() == KeyCode.RIGHT){
                RIGHT_PRESSED.set(false);
            }
//            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.A) {
//                bunny.idle();
//            }
//            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT){
//                bunny2.idle();
//            }
    });

    // Main game loop
    AnimationTimer animationTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {

        if (!bunny.getHurtBox().getBoundsInParent().intersects(stage.getBoundsInParent())) {
          y += 3;
          bunny.setCenterY(y);
        }
        if (!bunny2.getHurtBox().getBoundsInParent().intersects(stage.getBoundsInParent())) {
          y2 += 3;
          bunny2.setCenterY(y);
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

//                scene.setOnKeyPressed(e -> {
//                    if (e.getCode() == KeyCode.D) {
//                        bunny.moveRight();
//                    }
//                    if (e.getCode() == KeyCode.T) {
//                        bunny.attack();
//                        if(bunny.getHitBox().getBoundsInParent().intersects(bunny2.getHurtBox().getBoundsInParent())){
//                            bunny2.getHurtBox().setStroke(Color.GREEN);
//                        }
//                    }
//                    if (e.getCode() == KeyCode.W) {
//                        bunny.jump();
//                    }
//                    if (e.getCode() == KeyCode.A) {
//                        bunny.moveLeft();
//                    }
//                    if (e.getCode() == KeyCode.LEFT) {
//                        bunny2.moveLeft();
//                    }
//                    if (e.getCode() == KeyCode.RIGHT) {
//                        bunny2.moveRight();
//                    }
//                    if (e.getCode() == KeyCode.UP) {
//                        bunny2.jump();
//                    }
//                    if (e.getCode() == KeyCode.L) {
//                        bunny2.attack();
//                    }
//                });
//
//                scene.setOnKeyReleased(e -> {
//                    if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.A) {
//                        bunny.idle();
//                    }
//                    if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT){
//                        bunny2.idle();
//                    }
//                });

      }
    };
    animationTimer.start();
  }

  public static void main(String args[]) {
    launch(args);
  }
}
