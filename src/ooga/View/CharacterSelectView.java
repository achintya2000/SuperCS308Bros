package ooga.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

public class CharacterSelectView extends Application implements ViewInternal {

  private Scene currentScene;
  private BorderPane VB;
  private HBox HB1;
  private HBox HB2;
  private boolean p1IsReady = false;
  private boolean p2IsReady = false;
  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {

  }

  public void p1Ready()
  {
    checkIfReady();
    p1IsReady = true;
    ImageView p1ReadyOverlay = new ImageView();
    p1ReadyOverlay.setImage(new Image("ReadyLeft.png",600,150,false,true));
    HB1.getChildren().add(p1ReadyOverlay);

    System.out.println(p1ReadyOverlay.getX() + " "+  p1ReadyOverlay.getY());
  }

  public void p2Ready()
  {
    checkIfReady();
    p2IsReady = true;
    ImageView p2ReadyOverlay = new ImageView();
    p2ReadyOverlay.setX(800);
    p2ReadyOverlay.setY(100);
    p2ReadyOverlay.setImage(new Image("ReadyRight.png",600,150,false,true));
    HB2.getChildren().add(p2ReadyOverlay);
    System.out.println("ASDhD");
  }

  public void checkIfReady()
  {
    if(p1IsReady && p2IsReady)
    {
      ImageView readyOverlay = new ImageView();
      readyOverlay.setImage(new Image("ReadyToFight.png",1200,800,false,true));
    }
  }

  private BorderPane makeBorderPane() throws IOException {
    BorderPane myGP = new BorderPane();
    VBox myBox = new VBox();
    VB = myGP;
    BackgroundImage homeScreen = new BackgroundImage(new Image("CharacterSelectScreen.png",1245,763,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    myGP.setBackground(new Background(homeScreen));
    HashMap<String, String> buttonMap = new HashMap<>();
    Properties props = new Properties();
    props.load(Home.class.getResourceAsStream("charSelect_buttons.properties"));
    for(String s : props.stringPropertyNames()){
      buttonMap.put(s, props.getProperty(s));
    }
    Class<?> thisSelectScreen = CharacterSelectView.class;
    for(String key : buttonMap.keySet()){
      Button b = new Button(key);
      for(Method m : thisSelectScreen.getDeclaredMethods()){
        if(buttonMap.get(key).equals(m.getName())){
          b.setOnAction(e -> {
            try{
              m.setAccessible(true);
              m.invoke(this);
            } catch (IllegalAccessException ex) {
              ex.printStackTrace();
            } catch (InvocationTargetException ex) {
              ex.printStackTrace();
            }
          });
        }
      }
      myBox.getChildren().add(b);
    }
    myGP.setLeft(myBox);
    HB1 = new HBox();
    HB2 = new HBox();
    HB1.setAlignment(Pos.BOTTOM_LEFT);
    HB2.setAlignment(Pos.BOTTOM_RIGHT);
    HBox bottomOverlays = new HBox();
    bottomOverlays.getChildren().addAll(HB1,HB2);
    myGP.setBottom(bottomOverlays);
    return myGP;
  }

  @Override
  public void start(Stage primaryStage) {
    try{
      Scene selectScene = new Scene(makeBorderPane());
      currentScene = selectScene;
      primaryStage.setScene(selectScene);
      primaryStage.setHeight(800);
      primaryStage.setWidth(1200);
      primaryStage.show();
    } catch (IOException e){
      System.out.println(e.getLocalizedMessage());
    }
  }
}
