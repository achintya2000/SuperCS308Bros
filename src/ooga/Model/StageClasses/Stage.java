package ooga.Model.StageClasses;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public abstract class Stage {

  protected String name;
  protected Image background;
  protected List<Platform> platforms;
  protected List<List<Integer>> spawnCoordinates;

  /**
   * Getter for background ImageView
   *
   * @return background
   */
  public Image getBackground() {
    return background;
  }

  /**
   * Method for making the platforms for the private collection field 'platforms'.
   *
   * @return the rectangles to be stored in the class
   */
  protected abstract ArrayList<Platform> makePlatforms();

  /**
   * Method for the GameView to access the Rectangle collections for rendering
   *
   * @return platforms from the Stage class
   */
  public abstract ArrayList<Platform> getPlatforms();

  public abstract List<List<Integer>> getSpawnCoordinates();

  public abstract String getName();
}
