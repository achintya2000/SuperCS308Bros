package ooga.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ooga.Model.Character;
import ooga.Model.Characters.Character1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class CharacterSelectView extends Application implements ViewInternal {

  private Scene currentScene;
  private BorderPane BP;
  private HBox HB1;
  private HBox HB2;
  private boolean p1IsReady = false;
  private boolean p2IsReady = false;
  private Stage currentStage;
  private ArrayList<Character1> characterList = new ArrayList<>();

  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {

  }

  public void initCharacters() throws FileNotFoundException {
    Character1 ninja = new Character1();
    Character1 ninja2 = new Character1();
    Character1 ninja3 = new Character1();
    GridPane charGrid = new GridPane();
    BP.setCenter(charGrid);
    characterList.add(ninja);
    characterList.add(ninja2);
    characterList.add(ninja3);
    int colCount = 0;
    int rowCount = 0;
    int colThresh = 2;
    for(Character1 character : characterList)
    {
      charGrid.add(character.getCharacterImage(), colCount,rowCount);
      colCount++;
      if(colCount >= colThresh)
      {
        colCount = 0;
        rowCount++;
      }
    }
  }

  public void p1Ready() throws InterruptedException {
    p1IsReady = true;
    ImageView p1ReadyOverlay = new ImageView();
    p1ReadyOverlay.setImage(new Image("ReadyLeft.png",600,150,false,true));
    HB1.getChildren().add(p1ReadyOverlay);
    checkIfReady();
    System.out.println(p1ReadyOverlay.getX() + " "+  p1ReadyOverlay.getY());
  }

  public void p2Ready() throws InterruptedException {
    p2IsReady = true;
    ImageView p2ReadyOverlay = new ImageView();
    p2ReadyOverlay.setImage(new Image("ReadyRight.png",600,150,false,true));
    HB2.getChildren().add(p2ReadyOverlay);
    checkIfReady();
  }

  public void checkIfReady() throws InterruptedException
  {
    if(p1IsReady && p2IsReady)
    {
      ImageView readyOverlay = new ImageView();
      readyOverlay.setImage(new Image("ReadyToFight.png",1200,200,false,true));
      HBox readyBox = new HBox();
      readyBox.setAlignment(Pos.CENTER_LEFT);
      readyBox.getChildren().add(readyOverlay);
      BP.setCenter(readyBox);
      System.out.println("Creating Game ... ");
      Thread.sleep(2000);
      currentStage.hide();
      new GameView().start(new Stage());
    }
  }

  private BorderPane makeBorderPane() throws IOException {
    BorderPane myBP = new BorderPane();
    VBox myBox = new VBox();
    BP = myBP;
//    BackgroundImage homeScreen = new BackgroundImage(new Image("CharacterSelectScreen.png",1245,763,false,true),
//            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//            BackgroundSize.DEFAULT);
//    myBP.setBackground(new Background(homeScreen));
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
    myBP.setLeft(myBox);
    HB1 = new HBox();
    HB1.setMaxWidth(600);
    HB2 = new HBox();
    HB1.setAlignment(Pos.BOTTOM_LEFT);
    HB2.setAlignment(Pos.BOTTOM_RIGHT);
    HBox bottomOverlays = new HBox();
    bottomOverlays.getChildren().addAll(HB1,HB2);
    myBP.setBottom(bottomOverlays);
    return myBP;
  }

  @Override
  public void start(Stage primaryStage) {
    try{
      Scene selectScene = new Scene(makeBorderPane());
      currentScene = selectScene;
      currentStage = primaryStage;
      primaryStage.setScene(selectScene);
      primaryStage.setHeight(800);
      primaryStage.setWidth(1200);
      initCharacters();
      primaryStage.show();
    } catch (IOException e){
      System.out.println(e.getLocalizedMessage());
    }
  }
}
