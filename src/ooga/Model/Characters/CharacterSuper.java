package ooga.Model.Characters;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.Model.Character;
import ooga.Model.GameEngine.SpriteAnimation;

public abstract class CharacterSuper {

  private int myStocks;
  private int myStamina;
  private int health = 100;
  private String name;
  private Circle hitBox;
  private Rectangle hurtBox;
  private Group charGroup = new Group();

  ImageView spriteImageView;
  SpriteAnimation spriteAnimation;

  public CharacterSuper(String name) {
    this.name = name;
    System.out.println(name);
    System.out.println(this.name);
  }

  /**
   * Getter for myStamina
   * @return
   */
  public int getStamina() {
    return myStamina;
  }

  /**
   * Setter for myStamina
   * @param newStamina the new amount of stocks
   */
  public void setStamina(int newStamina) {
    myStamina = newStamina;
  }

  /**
   * Getter for myStocks
   * @return
   */
  public int getStocks() {
    return myStocks;
  }

  /**
   * Setter for myStocks
   * @param newStock the new amount of stocks
   */
  public void setStocks(int newStock) {
    myStocks = newStock;
  }

  public ImageView getCharacterImage(){
    return spriteImageView;
  }

  public void printHealth() {
    System.out.println(health);
  }

  public String getName()
  {
    return name;
  }


  public Circle getHitBox(){
    return hitBox;
  }

  public Rectangle getHurtBox(){
    return hurtBox;
  }

  public Group getGroup()
  {
    return charGroup;
  }

  public abstract void moveLeft();
  public abstract void moveRight();
  public abstract void moveDown();
  public abstract void attack();
  public abstract void jump();
  public abstract void idle();

  public abstract int getCenterY();

  public abstract void setCenterY(int y);
}
