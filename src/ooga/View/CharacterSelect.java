package ooga.View;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ooga.Controller.KeyBindManager;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.AbstractCharacter;
import ooga.Model.Characters.Bunny;
import ooga.Model.Characters.Ghost;
import ooga.Model.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.*;

public class CharacterSelect extends AbstractSelectScreen{

  private Scene currentScene;
  private BorderPane borderPane = new BorderPane();
  private BorderPane playerViewBox1;
  private BorderPane playerViewBox2;

  private Label char1NameText;
  private Label char2NameText;

  private Label player1ViewBoxPic;
  private Label player2ViewBoxPic;

  private VBox picAndName1;
  private VBox picAndName2;

  private Button player1Text;
  private Button player2Text;

  private BorderPane centerElements;
  private Insets inset = new Insets(10);
  private Stage currentStage;

  private ArrayList<AbstractCharacter> characterList = new ArrayList<>();
  private ArrayList<Button> buttonList = new ArrayList<>();
  private Player player1;
  private Player player2;
  private ArrayList<Player> playerList = new ArrayList<>();
  private int currentPlayer = 1;
  private Group root = new Group();
  private ooga.Model.StageClasses.Stage chosenStage;

  private boolean joiningMatch = false;

  public CharacterSelect() throws IOException {
    super();
    joiningMatch = true;
  }

  public CharacterSelect(ooga.Model.StageClasses.Stage chosenStage) throws IOException {
    super();
    this.chosenStage = chosenStage;
  }

  public void initCharacters() throws FileNotFoundException {
    ArrayList<Label> playerViewList = new ArrayList<>();
    playerViewList.add(player1ViewBoxPic);
    playerViewList.add(player2ViewBoxPic);

    ArrayList<Label> charNameLabelList = new ArrayList<>();
    charNameLabelList.add(char1NameText);
    charNameLabelList.add(char2NameText);

    Bunny bunny = new Bunny("bunny", 200, 100);
    Ghost ghost = new Ghost("ghost", 400, 200);

    GridPane charGrid = new GridPane();
    charGrid.setStyle("-fx-background-color: rgba(0,0,0, 1)");
    charGrid.setGridLinesVisible(true);
    charGrid.setMaxHeight(300);
    charGrid.setMaxWidth(800);
    borderPane.setStyle("-fx-background-color: rgba(200, 200, 240, 0.5)");
    borderPane.setCenter(charGrid);
    characterList.add(bunny);
    characterList.add(ghost);
    int colCount = 0;
    int rowCount = 0;
    int colThresh = 8;
    boolean a = (1 == 2);
    for (AbstractCharacter character : characterList) {
      Button button = new Button();
      button.setOnMouseClicked((e) -> {
        try {
          Class<?> cls = Class.forName(character.getClass().getName());
          AbstractCharacter imageCharacter = (AbstractCharacter) cls.getDeclaredConstructors()[0]
              .newInstance(character.getName(), 200, 400);
          AbstractCharacter newCharacter = (AbstractCharacter) cls.getDeclaredConstructors()[0]
              .newInstance(character.getName(), 200, 400);

          playerList.get(currentPlayer - 1).setMyCharacter(newCharacter);
          playerList.get(currentPlayer - 1).setHasChosenChar(true);
          playerViewList.get(currentPlayer - 1).setGraphic(imageCharacter.getCharacterImage());
          charNameLabelList.get(currentPlayer - 1).setText(character.getName());
          charNameLabelList.get(currentPlayer - 1)
              .setStyle(prop.getProperty("characterText"));
          System.out.println(
              "Player " + currentPlayer + "  character: " + playerList.get(currentPlayer - 1)
                  .getMyCharacter().getName());
          checkAllPlayersChosen();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
          new ExceptionHelper(ex);
        }
      });
      button.setDisable(true);
      button.setGraphic(character.getCharacterImage());
      charGrid.add(button, colCount, rowCount);
      colCount++;
      if (colCount >= colThresh) {
        colCount = 0;
        rowCount++;
      }
      buttonList.add(button);
    }
  }


  public void p1Ready() {
    for (Button button : buttonList) {
      button.setDisable(false);
    }
    currentPlayer = 1;
    //player1.setHasChosenChar(true);
  }

  public void p2Ready() {
    for (Button button : buttonList) {
      button.setDisable(false);
    }
    currentPlayer = 2;
    //player2.setHasChosenChar(true);
  }

  public void createGame() {

    System.out.println("Creating Game ... ");
    currentStage.hide();
    root.getChildren().clear();
    for (Player player : playerList) {
      root.getChildren().add(player.getMyCharacter().getGroup());
    }
    Pane newRoot = new Pane(root);
    GameView game = new GameView(playerList, newRoot, chosenStage, joiningMatch);
    game.start(new Stage());
  }

  public void checkAllPlayersChosen() {
    for (Player player : playerList) {
      if (!player.getHasChosenChar()) {

        System.out.println("NOT ALL PLAYERS HAVE CHOSEN");
        return;
      }
    }
    System.out.println("CREATING READY TO FIGHT BUTTON");
    createReadyToFightButton();
  }

  private void createReadyToFightButton() {
    Button readyToFight = new Button();
    BackgroundImage backgroundImage = new BackgroundImage(
        new Image(getClass().getResource("/ReadytoFightButton.png").toExternalForm()),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background readyToFightImage = new Background(backgroundImage);
    readyToFight.setBackground(readyToFightImage);
    readyToFight.setAlignment(BOTTOM_CENTER);
    centerElements.setTop(readyToFight);
    readyToFight.setMinWidth(1185);
    readyToFight.setMinHeight(100);
    readyToFight.setOnMouseClicked((e) -> {
      createGame();
    });
  }

  private BorderPane makeBorderPane() throws IOException {
    VBox header = createToolbar();
    borderPane.setTop(header);

    createViewBox1();
    createViewBox2();

    setupBorderPane();
    return borderPane;
  }

  private void createViewBox2() {
    char2NameText = new Label();
    picAndName2 = new VBox(10);
    player2ViewBoxPic = new Label();
    picAndName2.getChildren().addAll(player2ViewBoxPic, char2NameText);
    picAndName2.setAlignment(CENTER);
    playerViewBox2 = new BorderPane();
    playerViewBox2.setMinWidth(300);
    playerViewBox2.setMinHeight(300);
    player2Text = new Button("PLAYER 2");
    player2Text.setMinWidth(300);
    player2Text.setMinHeight(100);
    player2Text.setStyle(prop.getProperty("playerText"));
    player2Text.setOnMouseClicked((e) -> {
      p2Ready();
    });
    playerViewBox2.setStyle("-fx-background-color: rgba(0, 0, 230, 0.7)");
    playerViewBox2.setCenter(picAndName2);
    playerViewBox2.setBottom(player2Text);
  }

  private void createViewBox1() {
    char1NameText = new Label();
    picAndName1 = new VBox(10);
    player1ViewBoxPic = new Label();
    picAndName1.getChildren().addAll(player1ViewBoxPic, char1NameText);
    picAndName1.setAlignment(CENTER);
    playerViewBox1 = new BorderPane();
    playerViewBox1.setMinWidth(300);
    playerViewBox1.setMinHeight(300);
    player1Text = new Button("PLAYER 1");
    player1Text.setStyle(prop.getProperty("playerText"));
    player1Text.setMinWidth(300);
    player1Text.setMinHeight(100);
    player1Text.setOnMouseClicked((e) -> {
      p1Ready();
    });
    playerViewBox1.setStyle("-fx-background-color: rgba(230, 0, 0, 0.7)");
    playerViewBox1.setCenter(picAndName1);
    playerViewBox1.setBottom(player1Text);
  }

  private void setupBorderPane() {
    centerElements = new BorderPane();
    HBox bottomOverlays = new HBox();
    bottomOverlays.setAlignment(CENTER);
    centerElements.setBottom(bottomOverlays);
    centerElements.setMargin(bottomOverlays, inset);
    bottomOverlays.getChildren().addAll(playerViewBox1, playerViewBox2);
    borderPane.setBottom(centerElements);
  }

  @Override
  public void start(Stage primaryStage) {
    try {

      KeyBindManager keyBindManager = new KeyBindManager();
      //keyBindManager.setPlayer1KeyBinds("Z", "C", "S", "X", "F");

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
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
