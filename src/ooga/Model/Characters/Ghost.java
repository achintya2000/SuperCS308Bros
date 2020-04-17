package ooga.Model.Characters;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ooga.Model.GameEngine.SpriteAnimation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ghost extends AbstractCharacter {

    private static final int COLUMNS  =  10;
    private static final int COUNT    =  10;
    private static final int OFFSET_X =   0;
    private static final int OFFSET_Y =   0;
    private static final int WIDTH    = 100;
    private static final int HEIGHT   = 100;

    private boolean attackFinish;

    public Ghost(String name, int x, int y) throws FileNotFoundException {
        super(name);
        setImageFiles();
        spriteImageView = new ImageView(IDLE_IMAGE_LEFT);
        this.centerX = x + 50;
        this.centerY = y + 50;
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

        hurtBox = makeHurtBox(centerX, centerY);

        hitBox = makeHitBox();

        getGroup().getChildren().addAll(hurtBox,getCharacterImage());
    }

    private Circle makeHitBox(){
        double x;
        if(facingRight){
            x = spriteImageView.getBoundsInParent().getMaxX();
        } else {
            x = spriteImageView.getBoundsInParent().getMinX();
        }
        double y = (spriteImageView.getBoundsInParent().getMaxY() + spriteImageView.getBoundsInParent().getMinY())/2;
        double height = spriteImageView.getImage().getHeight();
        double radius = 20;
        Circle hitbox = new Circle(x, y, radius);
        hitbox.setStroke(Color.RED);
        hitbox.setFill(Color.rgb(200, 200, 200, 0.5));
        return hitbox;
    }

    private Rectangle makeHurtBox(int x , int y){
        int width = 50;
        int newX = x + (100 - width)/2;
        Rectangle hurtbox = new Rectangle(newX, y, width, 100);
        hurtbox.setStroke(Color.YELLOW);
        hurtbox.setFill(Color.rgb(200, 200, 200, 0.5));
        return hurtbox;

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
        double x = hurtBox.getX() - xSpeed;
        hurtBox.setX(x);
    }

    @Override
    public void moveRight() {
        facingRight = true;
        playRunRightAnimation();
        spriteImageView.setX(centerX += xSpeed);
        double x = hurtBox.getX() + xSpeed;
        hurtBox.setX(x);

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void jump() {
        TranslateTransition jump = new TranslateTransition(Duration.millis(500), spriteImageView);
        jump.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        jump.setByY(-150);
        jump.setAutoReverse(true);
        jump.setCycleCount(2);
        jump.play();

        playJumpAnimation();
    }

    private void jumpTransition(Node jumpNode) {
        TranslateTransition jump = new TranslateTransition(Duration.millis(500), jumpNode);
        jump.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        jump.setByY(-150);
        jump.setAutoReverse(true);
        jump.setCycleCount(2);
        jump.play();
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

        super.getGroup().getChildren().remove(hitBox);
        hitBox = makeHitBox();
        super.getGroup().getChildren().add(hitBox);

        spriteAnimation.setOnFinished(event -> {
            super.getGroup().getChildren().remove(hitBox);
            spriteAnimation.stop();
            playIdleAnimation();
        });
    }

    private void playJumpAnimation() {
        spriteAnimation.stop();
        if (facingRight){
            spriteImageView.setImage(JUMP_IMAGE_RIGHT);
        } else {
            spriteImageView.setImage(JUMP_IMAGE_LEFT);
        }
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

    public Pane getRoot(){ return root; }

    public ImageView getCharacterImage(){
        return spriteImageView;
    }


    public int getCenterY() {
        return (int) (spriteImageView.getBoundsInParent().getMaxY() + spriteImageView.getBoundsInParent().getMinY())/2;
    }

    public void setCenterY(int centerY) {
        spriteImageView.setY(centerY);
        hurtBox.setY(spriteImageView.getBoundsInParent().getMinY());

    }

    public Circle getHitBox(){
        return hitBox;
    }

    public Rectangle getHurtBox(){
        return hurtBox;
    }

    @Override
    public void setImageFiles() throws FileNotFoundException {
        IDLE_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-idle-right.png"));
        IDLE_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-idle-left.png"));

        RUN_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-run-right.png"));
        RUN_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-run-left.png"));

        ATTACK_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-attack-right.png"));
        ATTACK_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-attack-left.png"));

        JUMP_IMAGE_RIGHT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-jump-right.png"));
        JUMP_IMAGE_LEFT = new Image(new FileInputStream("data/spritesheets/ghost/ghost-jump-left.png"));
    }

}
