package ooga.Model.Stages;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
  private Rectangle rectangle;
  private boolean hollow;

  /**
   * Constructor for creating a platform for a stage
   * @param x cord of platform
   * @param y cord of platform
   * @param width of platform
   * @param height of platform
   * @param isHollow whether player can fall through the platform
   */
  public Platform(int x, int y, int width, int height, boolean isHollow){
    rectangle = new Rectangle(x, y, width, height);
  }

  /**
   * Getter for whether players can fall through the platform
   * @return whether platform is hollow
   */
  public boolean getHollow(){
    return hollow;
  }
}
