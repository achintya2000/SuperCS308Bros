package ooga.Model.Stages;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle {
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
  public Platform(int x, int y, int width, int height, int isHollow){
    super(x, y, width, height);
    setHollow(isHollow == 1);
    //this.setFill(Color.TRANSPARENT);
  }

  /**
   * Getter for whether players can fall through the platform
   * @return whether platform is hollow
   */
  public boolean getHollow(){
    return hollow;
  }

  /**
   * Method for setting whether players can fall through a platform
   * @param isHollow whether players can fall through the platform
   */
  public void setHollow(boolean isHollow){
    hollow = isHollow;
  }
}
