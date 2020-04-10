package ooga.Model.GameEngine;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ooga.Model.GameEngine.SpriteAnimation;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;

public class SpriteTester extends Application {

    private static final int COLUMNS  =   10;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =  0;
    private static final int OFFSET_Y =  0;
    private static final int WIDTH    = 366;
    private static final int HEIGHT   = 461;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creating an image
        Image IMAGE = new Image(new FileInputStream("src/ooga/Model/GameEngine/spritesheet.png"));

        //Setting the image view
        ImageView imageView = new ImageView(IMAGE);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

        imageView.preserveRatioProperty();
        imageView.setFitHeight(100);
        imageView.setFitWidth(120);

        final Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );

        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        Group root = new Group(imageView);

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
                imageView.setX(imageView.getX() + 30);
            }
        });
    }

    public static void main(String args[]) {
        launch(args);
    }
}
