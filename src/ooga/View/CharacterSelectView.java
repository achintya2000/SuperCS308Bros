package ooga.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.Bunny;
import ooga.Model.Characters.Ghost;
import ooga.Model.Characters.AbstractCharacter;
import ooga.Model.Player;

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
//  private boolean p1IsReady = false;
//  private boolean p2IsReady = false;
  private Stage currentStage;
  private ArrayList<AbstractCharacter> characterList = new ArrayList<>();
  private ArrayList<Button> buttonList = new ArrayList<>();
  private Player player1;
  private Player player2;
  private ArrayList<Player> playerList = new ArrayList<>();
  private int currentPlayer = 1;

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
    Bunny bunny = new Bunny("bunny", 200, 200);
    Bunny bunny2 = new Bunny("bunny2", 200, 200);
    Ghost ghost = new Ghost("ghost", 400, 200);

    GridPane charGrid = new GridPane();
    BP.setCenter(charGrid);
    characterList.add(bunny);
    characterList.add(bunny2);
    characterList.add(ghost);
    int colCount = 0;
    int rowCount = 0;
    int colThresh = 2;
    for(AbstractCharacter character : characterList)
    {
      Button button = new Button();
      button.setOnMouseClicked((e) -> {
        try
        {
          Class<?> cls = Class.forName(character.getClass().getName());
          AbstractCharacter newCharacter = (AbstractCharacter) cls.getDeclaredConstructors()[0].newInstance(character.getName(), 200, 400);
          playerList.get(currentPlayer-1).setMyCharacter(newCharacter);
          playerList.get(currentPlayer-1).setHasChosenChar(true);
          System.out.println("Player " + currentPlayer + "  character: " + playerList.get(currentPlayer-1).getMyCharacter().getName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException ex)
        {
          new ExceptionHelper(ex);
        }
      });
      button.setDisable(true);
      button.setGraphic(character.getCharacterImage());
      charGrid.add(button, colCount,rowCount);
      colCount++;
      if(colCount >= colThresh)
      {
        colCount = 0;
        rowCount++;
      }
      buttonList.add(button);
    }
  }


  public void p1Ready() {
    for(Button button : buttonList)
    {
      button.setDisable(false);
    }
    currentPlayer = 1;
//    ImageView p1ReadyOverlay = new ImageView();
//    p1ReadyOverlay.setImage(new Image("ReadyLeft.png",600,150,false,true));
//    HB1.getChildren().add(p1ReadyOverlay);
//    for(Button button : buttonList)
//    {
//      button.setDisable(false);
//    }
//    checkIfReady();
//    System.out.println(p1ReadyOverlay.getX() + " "+  p1ReadyOverlay.getY());
  }

  public void p2Ready(){
    for(Button button : buttonList)
    {
      button.setDisable(false);
    }
    currentPlayer = 2;
//    ImageView p2ReadyOverlay = new ImageView();
//    p2ReadyOverlay.setImage(new Image("ReadyRight.png",600,150,false,true));
//    HB2.getChildren().add(p2ReadyOverlay);
//    for(Button button : buttonList)
//    {
//      button.setDisable(false);
//    }
//    checkIfReady();
  }

  public void createGame()
  {
    if(!checkAllPlayersChosen())
    {
      System.out.println("NOT ALL PLAYERS HAVE CHOSEN");
      return;
    }
    System.out.println("Creating Game ... ");
    currentStage.hide();
    GameView game = new GameView(playerList);
    game.start(new Stage());
  }

  public boolean checkAllPlayersChosen()
  {
    boolean allTrue = true;
    for(Player player :playerList)
    {
      if(!player.getHasChosenChar())
      {
        allTrue = false;
      }
    }
    return allTrue;

    //Ready to fight picture
//      ImageView readyOverlay = new ImageView();
//      readyOverlay.setImage(new Image("ReadyToFight.png",1200,200,false,true));
//      HBox readyBox = new HBox();
//      readyBox.setAlignment(Pos.CENTER_LEFT);
//      readyBox.getChildren().add(readyOverlay);
//      BP.setCenter(readyBox);
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
      player1 = new Player();
      player2 = new Player();
      playerList.add(player1);
      playerList.add(player2);
      initCharacters();
      primaryStage.show();
    } catch (IOException e){
      System.out.println(e.getLocalizedMessage());
    }
  }
}
