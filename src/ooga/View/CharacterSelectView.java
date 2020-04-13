package ooga.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.Character2;
import ooga.Model.Characters.Character3;
import ooga.Model.Characters.CharacterSuper;
import ooga.Model.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.*;

public class CharacterSelectView extends Application implements ViewInternal {

  public static final ResourceBundle buttonStyles = ResourceBundle.getBundle("ooga.Resources.stylesheets.buttonStyle");
  private Scene currentScene;
  private BorderPane BP;
  private VBox VB1;
  private VBox VB2;
//  private boolean p1IsReady = false;
//  private boolean p2IsReady = false;
  private Stage currentStage;
  private ArrayList<CharacterSuper> characterList = new ArrayList<>();
  private ArrayList<Button> buttonList = new ArrayList<>();
  private Player player1;
  private Player player2;
  private ArrayList<Player> playerList = new ArrayList<>();
  private int currentPlayer = 1;
  private Group root = new Group();

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
    Character2 bunny = new Character2("bunny", root, 200, 100);
    Character3 ghost = new Character3("ghost", root,400, 200);

    GridPane charGrid = new GridPane();
    charGrid.setStyle("-fx-background-color: rgba(0,0,0, 1)");
    charGrid.setGridLinesVisible(true);
    charGrid.setMaxHeight(300);
    charGrid.setMaxWidth(800);
    BP.setStyle("-fx-background-color: rgba(200, 200, 240, 0.5)");
    BP.setCenter(charGrid);
    characterList.add(bunny);
    characterList.add(ghost);
    int colCount = 0;
    int rowCount = 0;
    int colThresh = 2;
    for(CharacterSuper character : characterList)
    {
      Button button = new Button();
      button.setOnMouseClicked((e) -> {
        try
        {
          Class<?> cls = Class.forName(character.getClass().getName());
          CharacterSuper newCharacter = (CharacterSuper) cls.getDeclaredConstructors()[0].newInstance(character.getName(), root, 200, 400);
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
    GameView game = new GameView(playerList, root);
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
    VBox header = new VBox();
    header.setAlignment(CENTER);
    HBox toolbar = new HBox();
    toolbar.setSpacing(10);
    toolbar.setAlignment(TOP_CENTER);
    header.getChildren().add(toolbar);
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
      toolbar.getChildren().add(b);
    }
    BP.setTop(header);
    VB1 = new VBox();
    VB2 = new VBox();
    VB1.setMinWidth(300);
    VB2.setMinWidth(300);
    VB1.setMinHeight(300);
    VB2.setMinHeight(300);
    Button player1Text = new Button("PLAYER 1");
    player1Text.setAlignment(BOTTOM_CENTER);
    player1Text.setStyle(buttonStyles.getString("playerText"));
    player1Text.setMinWidth(300);
    player1Text.setOnMouseClicked((e) -> {p1Ready();});

    Button player2Text = new Button("PLAYER 2");
    player2Text.setOnMouseClicked((e) -> {p2Ready();});
    player2Text.setMinWidth(300);
    player2Text.setStyle(buttonStyles.getString("playerText"));
    player2Text.setAlignment(BOTTOM_CENTER);
    VB1.setStyle("-fx-background-color: rgba(230, 0, 0, 0.7)");
    VB2.setStyle("-fx-background-color: rgba(0, 0, 230, 0.7)");
    VB1.setAlignment(Pos.CENTER_LEFT);
    VB2.setAlignment(Pos.CENTER_RIGHT);
    VB1.getChildren().add(player1Text);
    VB2.getChildren().add(player2Text);
    HBox bottomOverlays = new HBox();
    bottomOverlays.setAlignment(Pos.CENTER);
    bottomOverlays.getChildren().addAll(VB1,VB2);
    BP.setBottom(bottomOverlays);
    return BP;
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
