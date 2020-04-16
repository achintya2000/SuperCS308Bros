package ooga.Model.Stages;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Stage {
  protected javafx.scene.image.ImageView background;
  protected List<Rectangle> platforms;

  /**
   * Getter for background ImageView
   * @return background
   */
  public ImageView getBackground() {
    return background;
  }

  /**
   * Method for making the platforms for the private collection
   * field 'platforms'.
   * @return the rectangles to be stored in the class
   */
  protected abstract ArrayList<javafx.scene.shape.Rectangle> makePlatforms();

  /**
   * Method for the GameView to access the Rectangle collections for rendering
   * @return platforms from the Stage class
   */
  public abstract ArrayList<javafx.scene.shape.Rectangle> getPlatforms();
}
