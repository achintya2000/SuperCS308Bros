package ooga.Model.Characters;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.Model.GameEngine.SpriteAnimation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractCharacter {

  private int myStocks;
  private int myStamina;
  private int health = 100;
  private String name;
  private Group charGroup = new Group();

  protected int centerX;
  protected int centerY;
  protected int xSpeed = 10;

  /* Image files for character */
  protected Image IDLE_IMAGE_RIGHT;
  protected Image IDLE_IMAGE_LEFT;
  protected Image RUN_IMAGE_RIGHT;
  protected Image RUN_IMAGE_LEFT;
  protected Image ATTACK_IMAGE_RIGHT;
  protected Image ATTACK_IMAGE_LEFT;
  protected Image JUMP_IMAGE_RIGHT;
  protected Image JUMP_IMAGE_LEFT;

  protected boolean facingRight = true;

  protected ImageView spriteImageView;
  protected SpriteAnimation spriteAnimation;
  protected Pane root;
  protected Circle hitBox;
  protected Rectangle hurtBox;
  protected Rectangle dummy;
  protected BooleanProperty RIGHT_COLLIDE = new SimpleBooleanProperty();
  protected BooleanProperty LEFT_COLLIDE = new SimpleBooleanProperty();
  protected BooleanProperty INTERSECTS = new SimpleBooleanProperty();
  protected SimpleDoubleProperty HEALTH = new SimpleDoubleProperty();
  private BooleanProperty HOLLOW_COLLIDE = new SimpleBooleanProperty();


  public AbstractCharacter(String name) {
    this.name = name;
    System.out.println(name);
    System.out.println(this.name);
    HEALTH.set(100);
    RIGHT_COLLIDE.set(false);
    LEFT_COLLIDE.set(false);
    INTERSECTS.set(false);
    HOLLOW_COLLIDE.set(false);
  }

  public boolean getHOLLOW_COLLIDE(){
    return HOLLOW_COLLIDE.get();
  }

  public void setHOLLOW_COLLIDE(boolean flag){
    HOLLOW_COLLIDE.set(flag);
  }

  public boolean getINTERSECTS(){
    return INTERSECTS.get();
  }

  public SimpleDoubleProperty healthProperty(){
    return HEALTH;
  }

  public void setHEALTH(double new_health){
    HEALTH.set(new_health);
  }
  public double getHEALTH(){
    return HEALTH.get();
  }

  public void setINTERSECTS(boolean flag){
    INTERSECTS.set(flag);
  }

  public boolean getRIGHT_COLLIDE(){
    return RIGHT_COLLIDE.get();
  }

  public boolean getLEFT_COLLIDE(){
    return LEFT_COLLIDE.get();
  }

  public void setRIGHT_COLLIDE(boolean flag){
    RIGHT_COLLIDE.set(flag);
  }

  public void setLEFT_COLLIDE(boolean flag){
    LEFT_COLLIDE.set(flag);
  }

  /**
   * Getter for myStamina
   *
   * @return
   */
  public int getStamina() {
    return myStamina;
  }

  /**
   * Setter for myStamina
   *
   * @param newStamina the new amount of stocks
   */
  public void setStamina(int newStamina) {
    myStamina = newStamina;
  }


  /**
   * Getter for myStocks
   *
   * @return
   */
  public int getStocks() {
    return myStocks;
  }

  /**
   * Setter for myStocks
   *
   * @param newStock the new amount of stocks
   */
  public void setStocks(int newStock) {
    myStocks = newStock;
  }

  public ImageView getCharacterImage() {
    return spriteImageView;
  }

  public void printHealth() {
    System.out.println(health);
  }

  public String getName() {
    return name;
  }


  public Circle getHitBox() {
    return hitBox;
  }

  public Rectangle getHurtBox() {
    return hurtBox;
  }

  public Group getGroup() {
    return charGroup;
  }

  public abstract void moveLeft();

  public abstract void moveRight();

  public abstract void moveDown();

  public abstract void attack();

  public abstract void jump();

  public abstract void idle();

  public abstract void special();

  public abstract void setImageFiles() throws FileNotFoundException;

  public abstract int getCenterY();

  public abstract void setCenterY(double centerY);

}
