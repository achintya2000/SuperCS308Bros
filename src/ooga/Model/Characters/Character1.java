package ooga.Model.Characters;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ooga.Model.GameEngine.SpriteAnimation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Character1 {

    private static final int COLUMNS  =   10;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =  0;
    private static final int OFFSET_Y =  0;
    private static final int WIDTH    = 366;
    private static final int HEIGHT   = 461;

    Image RUN_IMAGE = new Image(new FileInputStream("resources/spritesheets/chracter1/run.png"));
    Image IDLE_IMAGE = new Image(new FileInputStream("resources/spritesheets/chracter1/idle.png"));
    Image ATTACK_IMAGE = new Image(new FileInputStream("resources/spritesheets/chracter1/attack.png"));

    ImageView spriteImageView;
    SpriteAnimation spriteAnimation;

    public Character1() throws FileNotFoundException {
        spriteImageView = new ImageView(IDLE_IMAGE);
        spriteImageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        spriteAnimation = new SpriteAnimation(
                spriteImageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                234, 442);
        spriteAnimation.setCycleCount(Animation.INDEFINITE);
        spriteAnimation.play();
    }

    public void idle(){
        playIdleAnimation();
    }

    public void moveRight() {
        playRunRightAnimation();
        spriteImageView.setX(spriteImageView.getX() + 30);
    }

    public ImageView getCharacterImage(){
        return spriteImageView;
    }

    private void playIdleAnimation() {
        spriteImageView.setImage(IDLE_IMAGE);
        spriteAnimation.setAnimation(
                spriteImageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                234, 441
        );
        spriteAnimation.play();
    }

    private void playRunRightAnimation() {
        spriteImageView.setImage(RUN_IMAGE);
        spriteAnimation.setAnimation(spriteImageView, Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT);
        spriteAnimation.play();
    }


}
