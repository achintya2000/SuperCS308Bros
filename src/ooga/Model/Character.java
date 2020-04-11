package ooga.Model;

public interface Character {

  /**
   * Choose sprite sheet image
   */
  public void setSpriteSheet();

  /**
   * Set idle info
   */
  public void idle();

  /**
   * Move character left
   */
  public void moveLeft();

  /**
   * Move character right
   */
  public void moveRight();

  /**
   * Move character down
   */
  public void moveDown();

  /**
   * Has the character jump
   */
  public void jump();

  /**
   * Basic attack
   */
  public void attack();

  public abstract void special();

}
