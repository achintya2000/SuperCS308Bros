package ooga.Model.Stages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import ooga.Exceptions.ExceptionHelper;

public class StageBuilder extends Stage {

  public StageBuilder(String propertiesPath) throws FileNotFoundException {
    platforms = new ArrayList<Rectangle>();
    Properties props = new Properties();
    try {
      props.load(StageBuilder.class.getResourceAsStream(propertiesPath));
    } catch (IOException e) {
      new ExceptionHelper(e);
    }

    HashMap<String, String[]> propsMap = new HashMap<>();
    for (String s : props.stringPropertyNames()) {
      propsMap.put(s, props.getProperty(s).split(","));
    }
    for (String key : propsMap.keySet()) {
      if (key.equals("image"))
        background = new ImageView(new Image(new FileInputStream(propsMap.get(key)[0])));
      else {
        int x = Integer.valueOf(propsMap.get(key)[1]);
        int y = Integer.valueOf(propsMap.get(key)[1]);
        int width = Integer.valueOf(propsMap.get(key)[1]);
        int height = Integer.valueOf(propsMap.get(key)[1]);
        platforms.add(new Rectangle(x, y, width, height));
      }
    }
  }

  @Override
  protected ArrayList<Rectangle> makePlatforms() {
    return null;
  }

  @Override
  public ArrayList<Rectangle> getPlatforms() {
    return (ArrayList<Rectangle>) platforms;
  }
}
