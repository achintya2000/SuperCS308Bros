package ooga.Model.Stages;

import java.io.*;
import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ooga.Exceptions.ExceptionHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StageBuilder extends Stage {

  public StageBuilder(String propertiesPath) throws FileNotFoundException {
    JSONParser parser = new JSONParser();
    JSONObject stageProperties;
    JSONArray rectangles;
    JSONArray spawns;

    platforms = new ArrayList<>();
    spawnCoordinates = new ArrayList<>();
//    Properties props = new Properties();
    try {
      stageProperties = (JSONObject) parser.parse(new FileReader(propertiesPath));

      background = new Image(new FileInputStream(stageProperties.get("gameImage").toString()));
      //background = new Image(new FileInputStream((File) stageProperties.get("gameImage")));

      System.out.println(stageProperties.get("rectangles"));
      rectangles = (JSONArray) stageProperties.get("rectangles");
      spawns = (JSONArray) stageProperties.get("spawns");

      rectangles.forEach(item -> {
        JSONObject rect = (JSONObject) item;
        int x = Integer.parseInt(rect.get("xPos").toString());
        int y = Integer.parseInt(rect.get("yPos").toString());
        int width = Integer.parseInt(rect.get("width").toString());
        int height = Integer.parseInt(rect.get("height").toString());
        boolean hollow = Boolean.parseBoolean(rect.get("hollow").toString());
        platforms.add(new Platform(x, y, width, height, hollow));
      });

      spawns.forEach(item -> {
        List<Integer> currentSpawnPoint = new ArrayList<>();
        JSONObject spawn = (JSONObject) item;
        currentSpawnPoint.add(Integer.parseInt(spawn.get("xPos").toString()));
        currentSpawnPoint.add(Integer.parseInt(spawn.get("yPos").toString()));
        spawnCoordinates.add(currentSpawnPoint);
      });

    } catch (IOException | ParseException e) {
      new ExceptionHelper(e);
    }

//    HashMap<String, String[]> propsMap = new HashMap<>();
//    for (String s : props.stringPropertyNames()) {
//      propsMap.put(s, props.getProperty(s).split(","));
//    }
//    boolean hollow = false;
//    for (String key : propsMap.keySet()) {
//      if (key.equals("image")) {
//        background = new Image(new FileInputStream(propsMap.get(key)[0]));
//      }
//      else if (key.equals("hollow")){
//        hollow = true;
//      } else if (key.contains("rec")) {
//        int x = Integer.valueOf(propsMap.get(key)[0]);
//        int y = Integer.valueOf(propsMap.get(key)[1]);
//        int width = Integer.valueOf(propsMap.get(key)[2]);
//        int height = Integer.valueOf(propsMap.get(key)[3]);
//        platforms.add(new Platform(x, y, width, height, hollow));
//      } else if (key.contains("spawn")) {
//        List<Integer> currentSpawnPoint = new ArrayList<>();
//        currentSpawnPoint.add(Integer.valueOf(propsMap.get(key)[0]));
//        currentSpawnPoint.add(Integer.valueOf(propsMap.get(key)[1]));
//        spawnCoordinates.add(currentSpawnPoint);
//      }
//    }
  }

  @Override
  protected ArrayList<Platform> makePlatforms() {
    return null;
  }


  @Override
  public ArrayList<Platform> getPlatforms() {
    return (ArrayList<Platform>)platforms;
  }

  @Override
  public List<List<Integer>> getSpawnCoordinates() { return spawnCoordinates; }
}
