package ooga.Model.Characters;

import javafx.scene.image.ImageView;
import ooga.Model.GameEngine.SpriteAnimation;

public abstract class AbstractCharacter {

  private int myStocks;
  private int myStamina;
  private int health = 100;
  private String name;

  protected int centerX;
  protected int centerY;
  protected int xSpeed = 10;

  ImageView spriteImageView;
  SpriteAnimation spriteAnimation;

  public AbstractCharacter(String name) {
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

  public abstract void moveLeft();
  public abstract void moveRight();
  public abstract void moveDown();
  public abstract void attack();
  public abstract void jump();
  public abstract void idle();
  public abstract void special();
}
