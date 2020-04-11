package ooga.Model.Stages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public abstract class Stage {
  ImageView background;
  Image IMAGE = new Image(new FileInputStream("data/1200px-SSBU-Battlefield.png"));


  public Stage() throws FileNotFoundException {
    background = new ImageView((Element) IMAGE);

  }

  /**
   * Getter for background ImageView
   * @return background
   */
  public ImageView getBackground() {
    return background;
  }
}
