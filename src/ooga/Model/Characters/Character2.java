package ooga.Model.Characters;

import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ooga.Model.Character;
import ooga.Model.GameEngine.SpriteAnimation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Character2 implements Character {

    private static final int COLUMNS  =  10;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =   0;
    private static final int OFFSET_Y =   0;
    private static final int WIDTH    = 100;
    private static final int HEIGHT   = 100;

    private int centerX = 400;
    private int centerY = 200;
    private int xSpeed = 25;

    Image IDLE_IMAGE = new Image(new FileInputStream("data/spritesheets/bunny/bunny-idle.png"));
    Image RUN_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-run-right.png"));
    Image RUN_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-run-left.png"));
    //Image ATTACK_IMAGE = new Image(new FileInputStream(""));
    //Image JUMP_IMAGE = new Image(new FileInputStream(""));

    ImageView spriteImageView;
    SpriteAnimation spriteAnimation;

    public Character2() throws FileNotFoundException {
        spriteImageView = new ImageView(IDLE_IMAGE);
        spriteImageView.setX(centerX);
        spriteImageView.setY(centerY);

        spriteImageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        spriteAnimation = new SpriteAnimation(
                spriteImageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );

        spriteAnimation.setCycleCount(Animation.INDEFINITE);
        spriteAnimation.play();
    }

    @Override
    public void setSpriteSheet() {

    }

    @Override
    public void idle() {
        playIdleAnimation();
    }

    @Override
    public void moveLeft() {
        playRunLeftAnimation();
        spriteImageView.setX(centerX -= xSpeed);
    }

    @Override
    public void moveRight() {
        playRunRightAnimation();
        spriteImageView.setX(centerX += xSpeed);
    }

    @Override
    public void moveDown() {

    }

    @Override
    public void jump() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void special() {

    }

    private void playIdleAnimation() {
//        spriteImageView.setImage(IDLE_IMAGE);
//        spriteAnimation.setAnimation(
//                spriteImageView,
//                Duration.millis(1000),
//                COUNT, COLUMNS,
//                OFFSET_X, OFFSET_Y,
//                WIDTH, HEIGHT
//        );
//        spriteAnimation.setCycleCount(Animation.INDEFINITE);
//        spriteAnimation.play();
    }

    private void playRunRightAnimation() {
        spriteImageView.setImage(RUN_IMAGE_RIGHT);
        spriteAnimation.setAnimation(
                spriteImageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT);
        spriteAnimation.play();
    }

    private void playRunLeftAnimation() {
        spriteImageView.setImage(RUN_IMAGE_LEFT);
        spriteAnimation.setAnimation(
                spriteImageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT);
        spriteAnimation.play();
    }

    public ImageView getCharacterImage(){
        return spriteImageView;
    }
}
