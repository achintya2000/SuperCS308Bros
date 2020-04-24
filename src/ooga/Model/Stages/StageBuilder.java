package ooga.Model.Stages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ooga.Exceptions.ExceptionHelper;

public class StageBuilder extends Stage {

  public StageBuilder(String propertiesPath) throws FileNotFoundException {
    platforms = new ArrayList<>();
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
      System.out.println(key);
      if (key.equals("image")) {
        background = new Image(new FileInputStream(propsMap.get(key)[0]));
      } else {
        int x = Integer.valueOf(propsMap.get(key)[0]);
        int y = Integer.valueOf(propsMap.get(key)[1]);
        int width = Integer.valueOf(propsMap.get(key)[2]);
        int height = Integer.valueOf(propsMap.get(key)[3]);
        int hollow = Integer.valueOf(propsMap.get(key)[4]);
        //System.out.println(hollow);
        platforms.add(new Platform(x, y, width, height, hollow));
      }
    }
  }

  @Override
  protected ArrayList<Platform> makePlatforms() {
    return null;
  }


  @Override
  public ArrayList<Platform> getPlatforms() {
    return (ArrayList<Platform>)platforms;
  }
}
