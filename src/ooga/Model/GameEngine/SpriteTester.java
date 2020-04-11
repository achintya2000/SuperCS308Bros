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
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Model.Characters.Character1;
import ooga.Model.GameEngine.SpriteAnimation;

import java.io.FileInputStream;

public class SpriteTester extends Application {

//    private static final int COLUMNS  =   10;
//    private static final int COUNT    =  10;
//    private static final int OFFSET_X =  0;
//    private static final int OFFSET_Y =  0;
//    private static final int WIDTH    = 366;
//    private static final int HEIGHT   = 461;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Character1 ninja = new Character1();

//        //Creating an image
//        Image RUN_IMAGE = new Image(new FileInputStream("resources/spritesheets/chracter1/run.png"));
//        Image IDLE_IMAGE = new Image(new FileInputStream("resources/spritesheets/chracter1/idle.png"));
//        Image ATTACK_IMAGE = new Image(new FileInputStream("resources/spritesheets/chracter1/attack.png"));
//
//        //Setting the image view
//        ImageView spriteImageView = new ImageView(IDLE_IMAGE);
//        spriteImageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        //spriteImageView.setPreserveRatio(true);
        //spriteImageView.setFitHeight(100);
        //imageView.setFitWidth(100);

//        SpriteAnimation spriteAnimation = new SpriteAnimation(
//                spriteImageView,
//                Duration.millis(1000),
//                COUNT, COLUMNS,
//                OFFSET_X, OFFSET_Y,
//                234, 442
//        );
//        spriteAnimation.setCycleCount(Animation.INDEFINITE);
//        spriteAnimation.play();

        Group root = new Group(ninja.getCharacterImage());

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
//                spriteImageView.setImage(RUN_IMAGE);
//                spriteAnimation.setAnimation(spriteImageView, Duration.millis(1000),
//                        COUNT, COLUMNS,
//                        OFFSET_X, OFFSET_Y,
//                        WIDTH, HEIGHT);
//                spriteAnimation.play();
//                spriteImageView.setX(spriteImageView.getX() + 30);
            }
            if (e.getCode() == KeyCode.T) {
//                spriteAnimation.stop();
//                spriteImageView.setImage(ATTACK_IMAGE);
//                spriteAnimation.setAnimation(spriteImageView, Duration.millis(1000),
//                        COUNT, COLUMNS,
//                        OFFSET_X, OFFSET_Y,
//                        536, 495);
//                spriteAnimation.setCycleCount(1);
//                spriteAnimation.play();
//
//                spriteAnimation.setOnFinished(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        spriteAnimation.stop();
//                        spriteImageView.setImage(IDLE_IMAGE);
//                        spriteAnimation.setAnimation(spriteImageView, Duration.millis(1000),
//                                COUNT, COLUMNS,
//                                OFFSET_X, OFFSET_Y,
//                                234, 442);
//                        spriteAnimation.setCycleCount(Animation.INDEFINITE);
//                        spriteAnimation.play();
//                    }
//                });
            }
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.D) {
//                spriteImageView.setImage(IDLE_IMAGE);
//                spriteAnimation.setAnimation(spriteImageView, Duration.millis(1000),
//                        COUNT, COLUMNS,
//                        OFFSET_X, OFFSET_Y,
//                        234, 442);
//                spriteAnimation.play();
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
