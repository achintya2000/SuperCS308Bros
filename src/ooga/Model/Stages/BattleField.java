package ooga.Model.Stages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javax.swing.text.Element;

public class BattleField extends Stage {
  Image IMAGE = new Image(new FileInputStream("data/1200px-SSBU-Battlefield.png"));
  private ArrayList<Rectangle> platforms;

  public BattleField() throws FileNotFoundException {
    super();
    background = new ImageView(IMAGE);
    platforms = makePlatforms();
  }

  @Override
  protected ArrayList<Rectangle> makePlatforms() {
    ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
    recs.add(new Rectangle(100, 100, 100, 100));
    recs.add(new Rectangle(200, 700, 1000, 100));
    return recs;
  }

  @Override
  public ArrayList<Rectangle> getPlatforms() {
    return platforms;
  }

}
