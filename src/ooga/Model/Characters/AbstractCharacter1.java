//package ooga.Model.Characters;
//
//import javafx.animation.Animation;
//import javafx.animation.Interpolator;
//import javafx.animation.TranslateTransition;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.paint.Color;
//import javafx.util.Duration;
//import ooga.Model.Character;
//import ooga.Model.GameEngine.SpriteAnimation;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//
//public class AbstractCharacter1 extends AbstractCharacter implements Character {
//
//    private static final int COLUMNS  =   10;
//    private static final int COUNT    =  10;
//    private static final int OFFSET_X =  0;
//    private static final int OFFSET_Y =  0;
//    private static final int WIDTH    = 366;
//    private static final int HEIGHT   = 461;
//
//
//    private int centerX = 100;
//    private int centerY = 200;
//    private int xSpeed = 25;
//
//    private int health = 100;
//
//    Image RUN_LEFT_IMAGE = new Image(new FileInputStream("data/spritesheets/chracter1/runLeft.png"));
//    Image RUN_RIGHT_IMAGE = new Image(new FileInputStream("data/spritesheets/chracter1/runRight.png"));
//    Image IDLE_IMAGE = new Image(new FileInputStream("data/spritesheets/chracter1/idle.png"));
//    Image ATTACK_IMAGE = new Image(new FileInputStream("data/spritesheets/chracter1/attack.png"));
//    Image JUMP_IMAGE = new Image(new FileInputStream("data/spritesheets/chracter1/jump.png"));
//
//    ImageView spriteImageView;
//    SpriteAnimation spriteAnimation;
//    Pane root;
//    Circle hitBox;
//    Rectangle dummy;
//    boolean attackFinish;
//
//
//
//    public AbstractCharacter1(String name) throws FileNotFoundException {
//        super(name);
//        spriteImageView = new ImageView(IDLE_IMAGE);
//        spriteImageView.setX(centerX);
//        spriteImageView.setY(centerY);
//
//        spriteImageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
//        spriteAnimation = new SpriteAnimation(
//                spriteImageView,
//                Duration.millis(1000),
//                COUNT, COLUMNS,
//                OFFSET_X, OFFSET_Y,
//                234, 442);
//
//        spriteAnimation.setCycleCount(Animation.INDEFINITE);
//        spriteAnimation.play();
//        root = new Pane(spriteImageView);
//        dummy = getDummy();
//        root.getChildren().add(dummy);
//        attackFinish = true;
//    }
//
//    public void idle(){
//        playIdleAnimation();
//    }
//
//    @Override
//    public void setSpriteSheet() {
//
//    }
//
//    @Override
//    public void moveLeft() {
//        playRunAnimation(RUN_LEFT_IMAGE);
//        spriteImageView.setX(spriteImageView.getX() - 30);
//    }
//
//    public void moveRight() {
//        playRunAnimation(RUN_RIGHT_IMAGE);
//        spriteImageView.setX(centerX += xSpeed);
//    }
//
//    @Override
//    public void moveDown() {
//
//    }
//
//    @Override
//    public void jump() {
//        TranslateTransition jump = new TranslateTransition(Duration.millis(500), spriteImageView);
//        jump.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
//        jump.setByY(-50);
//        jump.setAutoReverse(true);
//        jump.setCycleCount(2);
//        jump.play();
//
//        playJumpAnimation();
//    }
//
//    @Override
//    public void special() {
//
//    }
//
//    public void attack() {
//        playAttackAnimation();
//    }
//
//    private void playIdleAnimation() {
//        spriteImageView.setImage(IDLE_IMAGE);
//        spriteAnimation.setAnimation(
//                spriteImageView,
//                Duration.millis(1000),
//                COUNT, COLUMNS,
//                OFFSET_X, OFFSET_Y,
//                234, 441
//        );
//        spriteAnimation.setCycleCount(Animation.INDEFINITE);
//        spriteAnimation.play();
//    }
//
//    private void playRunAnimation(Image runImage) {
//        spriteImageView.setImage(runImage);
//        spriteAnimation.setAnimation(
//                spriteImageView,
//                Duration.millis(1000),
//                10, 20,
//                OFFSET_X, OFFSET_Y,
//                WIDTH, HEIGHT);
//        spriteAnimation.play();
//    }
//
//    private void playAttackAnimation() {
//        spriteAnimation.stop();
//        spriteImageView.setImage(ATTACK_IMAGE);
//        spriteAnimation.setAnimation(
//                spriteImageView,
//                Duration.millis(1000),
//                COUNT, COLUMNS,
//                OFFSET_X, OFFSET_Y,
//                536, 495);
//        spriteAnimation.setCycleCount(1);
//        spriteAnimation.play();
//        root.getChildren().remove(hitBox);
//        hitBox = getHitBox();
//        root.getChildren().add(hitBox);
//
//        if(hitBox.getBoundsInParent().intersects(dummy.getBoundsInParent())){
//            dummy.setFill(Color.GREEN);
//        }
//        spriteAnimation.setOnFinished(event -> {
//            spriteAnimation.stop();
//            root.getChildren().remove(hitBox);
//            dummy.setFill(Color.YELLOW);
//            playIdleAnimation();
//        });
//    }
//
//    private Rectangle getDummy(){
//        double x = 500;
//        double y = 0;
//        double height = spriteImageView.getImage().getHeight();
//        double width = 200;
//        Rectangle dummy = new Rectangle(x, y, width, height);
//        dummy.setFill(Color.YELLOW);
//        return dummy;
//    }
//
//    private Circle getHitBox(){
//        double x = spriteImageView.getBoundsInParent().getMaxX() + 120;
//        double y = spriteImageView.getBoundsInParent().getMaxY()/2;
//        double radius = 100;
//        Circle hurtBox = new Circle(x, y, radius);
//        hurtBox.setFill(Color.RED);
//        return hurtBox;
//    }
//
//    private void playJumpAnimation() {
//        spriteAnimation.stop();
//        spriteImageView.setImage(JUMP_IMAGE);
//        spriteAnimation.setAnimation(
//                spriteImageView,
//                Duration.millis(1000),
//                COUNT, COLUMNS,
//                OFFSET_X, OFFSET_Y,
//                365, 486);
//        spriteAnimation.setCycleCount(1);
//        spriteAnimation.play();
//
//        spriteAnimation.setOnFinished(event -> {
//            spriteAnimation.stop();
//            playIdleAnimation();
//        });
//    }
//
//    public ImageView getCharacterImage(){
//        return spriteImageView;
//    }
//
//    public Pane getRoot(){ return root; }
//
//    public void printHealth() {
//        System.out.println(health);
//    }
//
//
//
//}
