package ooga.Model.Stages;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Stage {
  protected javafx.scene.image.ImageView background;
  protected List<Rectangle> platforms;

  public Stage() throws FileNotFoundException {


  }

  /**
   * Getter for background ImageView
   * @return background
   */
  public ImageView getBackground() {
    return background;
  }

  protected abstract ArrayList<javafx.scene.shape.Rectangle> makePlatforms();

  public abstract ArrayList<javafx.scene.shape.Rectangle> getPlatforms();
}
