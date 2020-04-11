package ooga.Model.GameEngine;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Model.Characters.Character1;
import ooga.Model.GameEngine.SpriteAnimation;

import java.io.FileInputStream;

public class SpriteTester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Character1 ninja = new Character1();

        Pane root = ninja.getRoot();

        //Creating a scene object
        Scene scene = new Scene(root, 1000, 1000);

        //Setting title to the Stage
        primaryStage.setTitle("Sprite Animation");

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D) {
                ninja.moveRight();
            }
            if (e.getCode() == KeyCode.T) {
                ninja.attack();
            }
            if (e.getCode() == KeyCode.W) {
                ninja.jump();
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.D) {
                ninja.idle();
            }
            if (e.getCode() == KeyCode.T){
                //if (spriteAnimation.)
            }
        });
    }

    public static void main(String args[]) {
        launch(args);
    }
}
