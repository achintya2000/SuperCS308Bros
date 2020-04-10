package ooga.Model;

public interface Character {

  /**
   * Choose sprite sheet image
   */
  public void setSpriteSheet();

  /**
   * Move character left
   */
  public void left();

  /**
   * Move character right
   */
  public void right();

  /**
   * Move character down
   */
  public void down();

  /**
   * Has the character jump
   */
  public void jump();

  public abstract void special();

}
