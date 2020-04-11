package ooga.Model.Stages;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.scene.image.Image;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public abstract class Stage {
  protected ImageView background;
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
}
