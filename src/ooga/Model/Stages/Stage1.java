package ooga.Model.Stages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class Stage1 extends Stage {
  Image IMAGE = new Image(new FileInputStream("data/1200px-SSBU-Battlefield.png"));


  public Stage1() throws FileNotFoundException {
    super();
    background = new ImageView((Element) IMAGE);
    platforms = new ArrayList<>();
  }
}
