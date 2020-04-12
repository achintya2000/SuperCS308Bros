package ooga.Model.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Model.Characters.Character1;
import ooga.Model.Characters.Character2;
import ooga.Model.Characters.Character3;

public class SpriteTester extends Application {

    int y;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Character2 bunny = new Character2(100, 400);
        Character2 bunny2 = new Character2(400, 500);

        Character3 ghost = new Character3(300, 400);

        Rectangle stage = new Rectangle(100, 800, 500, 100);

        Pane root = bunny.getRoot();
        root.getChildren().add((bunny2.getRoot()));
        root.getChildren().add(ghost.getRoot());
        root.getChildren().add(stage);

        //Creating a scene object
        Scene scene = new Scene(root, 1000, 1000);

        //Setting title to the Stage
        primaryStage.setTitle("Sprite Animation");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();

        y = bunny.getCenterY();

        // Main game loop
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (!bunny.getHurtBox().getBoundsInParent().intersects(stage.getBoundsInParent()))
                {
                    y += 10;
                    bunny.setCenterY(y);
                    bunny2.setCenterY(y);
                }

                //Update and render

                scene.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.D) {
                        bunny.moveRight();
                        bunny2.getHurtBox().setStroke(Color.YELLOW);
                    }
                    if (e.getCode() == KeyCode.T) {
                        bunny.attack();
                        if(bunny.getHitBox().intersects(bunny2.getHurtBox().getBoundsInParent())) {
                            System.out.println("HIT");
                            bunny2.getHurtBox().setStroke(Color.GREEN);
                        }
                    }
                    if (e.getCode() == KeyCode.W) {
                        bunny.jump();
                    }
                    if (e.getCode() == KeyCode.A) {
                        bunny.moveLeft();
                    }
                    if (e.getCode() == KeyCode.LEFT) {
                        ghost.moveLeft();
                    }
                    if (e.getCode() == KeyCode.RIGHT) {
                        ghost.moveRight();
                    }
                    if (e.getCode() == KeyCode.UP) {
                        ghost.jump();
                    }
                    if (e.getCode() == KeyCode.L) {
                        ghost.attack();
                    }
                });

                scene.setOnKeyReleased(e -> {
                    if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.A) {
                        bunny.idle();
                    }
                    if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT){
                        ghost.idle();
                    }
                });

            }
        };
        animationTimer.start();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
