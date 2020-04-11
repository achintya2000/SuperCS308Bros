package ooga.Model.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Model.Characters.Character1;
import ooga.Model.Characters.Character2;

public class SpriteTester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Character1 ninja = new Character1();
        Character2 bunny = new Character2();

        Pane root = ninja.getRoot();


        //Creating a scene object
        Scene scene = new Scene(root, 1000, 1000);

        //Setting title to the Stage
        primaryStage.setTitle("Sprite Animation");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();

        // Main game loop
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //Update and render
                scene.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.D) {
                        ninja.moveRight();
                    }
                    if (e.getCode() == KeyCode.T) {
                        ninja.attack();
                    }
                    if (e.getCode() == KeyCode.W) {
                        bunny.jump();
                    }
                    if (e.getCode() == KeyCode.A) {
                        bunny.moveLeft();
                    }
                });

                scene.setOnKeyReleased(e -> {
                    if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.A) {
                        bunny.idle();
                    }
                    if (e.getCode() == KeyCode.T){
                        //if (spriteAnimation.)
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
