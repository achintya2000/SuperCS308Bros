package ooga.Model.Characters;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ooga.Model.Character;
import ooga.Model.GameEngine.SpriteAnimation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Character2 extends CharacterSuper implements Character {

    private static final int COLUMNS  =  10;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =   0;
    private static final int OFFSET_Y =   0;
    private static final int WIDTH    = 100;
    private static final int HEIGHT   = 100;

    private int centerX = 400;
    private int centerY = 200;
    private int xSpeed = 25;

    private boolean facingRight = true;
    private int health = 100;

    Image IDLE_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-idle-right.png"));
    Image IDLE_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-idle-left.png"));

    Image RUN_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-run-right.png"));
    Image RUN_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-run-left.png"));

    Image ATTACK_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-attack-right.png"));
    Image ATTACK_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/bunny/bunny-attack-left.png"));

    Image JUMP_IMAGE = new Image(new FileInputStream("data/spritesheets/bunny/bunny-jump.png"));

    ImageView spriteImageView;
    SpriteAnimation spriteAnimation;
    Pane root;
    Circle hitBox;
    Rectangle dummy;
    boolean attackFinish;

    public Character2(int x, int y) throws FileNotFoundException {
        spriteImageView = new ImageView(IDLE_IMAGE_RIGHT);
        this.centerX = x;
        this.centerY = y;
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

        root = new Pane(spriteImageView);
        dummy = getDummy();
        root.getChildren().add(dummy);
        attackFinish = true;
    }

    private Rectangle getDummy(){
        double x = 500;
        double y = 0;
        double height = spriteImageView.getImage().getHeight();
        double width = 200;
        Rectangle dummy = new Rectangle(x, y, width, height);
        dummy.setFill(Color.YELLOW);
        return dummy;
    }

    private Circle getHitBox(){
        double x = spriteImageView.getBoundsInParent().getMaxX() + 120;
        double y = spriteImageView.getBoundsInParent().getMaxY()/2;
        double height = spriteImageView.getImage().getHeight();
        double radius = 100;
        Circle hurtBox = new Circle(x, y, radius);
        hurtBox.setFill(Color.RED);
        return hurtBox;
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
        facingRight = false;
        playRunLeftAnimation();
        spriteImageView.setX(centerX -= xSpeed);
    }

    @Override
    public void moveRight() {
        facingRight = true;
        playRunRightAnimation();
        spriteImageView.setX(centerX += xSpeed);
    }

    @Override
    public void moveDown() {

    }

    @Override
    public void jump() {
        TranslateTransition jump = new TranslateTransition(Duration.millis(500), spriteImageView);
        jump.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        jump.setByY(-25);
        jump.setAutoReverse(true);
        jump.setCycleCount(2);
        jump.play();

        playJumpAnimation();
    }

    @Override
    public void attack() {
        playAttackAnimation();
    }

    @Override
    public void special() {

    }

    private void playIdleAnimation() {
        if (facingRight) {
            spriteImageView.setImage(IDLE_IMAGE_RIGHT);
        } else {
            spriteImageView.setImage(IDLE_IMAGE_LEFT);
        }
        spriteAnimation.setAnimation(
                spriteImageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        spriteAnimation.setCycleCount(Animation.INDEFINITE);
        spriteAnimation.play();

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

    private void playAttackAnimation() {
        spriteAnimation.stop();
        if (facingRight) {
            spriteImageView.setImage(ATTACK_IMAGE_RIGHT);

        } else {
            spriteImageView.setImage(ATTACK_IMAGE_LEFT);
        }
        spriteAnimation.setAnimation(
                spriteImageView,
                Duration.millis(1000),
                6, 6,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT);
        spriteAnimation.setCycleCount(1);
        spriteAnimation.play();

        spriteAnimation.setOnFinished(event -> {
            spriteAnimation.stop();
            playIdleAnimation();
        });

    }

    private void playJumpAnimation() {
        spriteAnimation.stop();
        spriteImageView.setImage(JUMP_IMAGE);
        spriteAnimation.setAnimation(
                spriteImageView,
                Duration.millis(1000),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT);
        spriteAnimation.setCycleCount(1);
        spriteAnimation.play();

        spriteAnimation.setOnFinished(event -> {
            spriteAnimation.stop();
            playIdleAnimation();
        });
    }

    public ImageView getCharacterImage(){
        return spriteImageView;
    }

    public Pane getRoot(){ return root; }

}
