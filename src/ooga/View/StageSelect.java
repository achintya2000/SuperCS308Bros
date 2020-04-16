package ooga.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.Character2;
import ooga.Model.Characters.Character3;
import ooga.Model.Characters.CharacterSuper;
import ooga.Model.Player;
import ooga.Model.Stages.StageBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.*;

public class StageSelect extends Application implements ViewInternal {

  public static final ResourceBundle buttonStyles = ResourceBundle
      .getBundle("ooga.Resources.stylesheets.buttonStyle");
  private Scene currentScene;
  private BorderPane borderPane;
  private Button go;
  private Stage currentStage;
  private ooga.Model.Stages.Stage chosenStage;

  private ArrayList<ooga.Model.Stages.Stage> stageList = new ArrayList<>();
  private ArrayList<Button> buttonList = new ArrayList<>();
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

  public void initStages() throws FileNotFoundException {
    StageBuilder battlefield = new StageBuilder("battlefield.properties");
    StageBuilder fd = new StageBuilder("finalDestination.properties");
    StageBuilder bridge = new StageBuilder("bridgeOfEldin.properties");

    stageList.add(battlefield);
    stageList.add(fd);
    stageList.add(bridge);
    GridPane charGrid = new GridPane();
    charGrid.setStyle("-fx-background-color: rgba(0,0,0, 1)");
    charGrid.setGridLinesVisible(true);
    charGrid.setMaxHeight(300);
    charGrid.setMaxWidth(800);
    borderPane.setStyle("-fx-background-color: rgba(200, 200, 240, 0.5)");
    borderPane.setCenter(charGrid);

    int colCount = 0;
    int rowCount = 0;
    int colThresh = 8;
    for (ooga.Model.Stages.Stage stage : stageList) {
      Button button = new Button();
      button.setOnMouseClicked((e) -> {
        chosenStage = stage;
        go.setDisable(false);
      });
      ImageView stageBackground = new ImageView(stage.getBackground());
      stageBackground.setFitHeight(100);
      stageBackground.setFitWidth(100);
      button.setGraphic(stageBackground);
      charGrid.add(button, colCount, rowCount);
      colCount++;
      if (colCount >= colThresh) {
        colCount = 0;
        rowCount++;
      }
    }
  }


  public void goToSelectScreen() {
    System.out.println("Going to Select Screen ... ");
    currentStage.hide();
    CharacterSelect characterSelect = new CharacterSelect(chosenStage);
    characterSelect.start(new Stage());
  }


  private BorderPane makeBorderPane() throws IOException {
    BorderPane myborderPane = new BorderPane();
    VBox header = new VBox();
    header.setAlignment(CENTER);
    HBox toolbar = new HBox();
    toolbar.setSpacing(10);
    toolbar.setAlignment(TOP_CENTER);
    header.getChildren().add(toolbar);
    borderPane = myborderPane;

    HashMap<String, String> buttonMap = new HashMap<>();
    Properties props = new Properties();
    props.load(Home.class.getResourceAsStream("charSelect_buttons.properties"));
    for (String s : props.stringPropertyNames()) {
      buttonMap.put(s, props.getProperty(s));
    }
    Class<?> thisSelectScreen = StageSelect.class;
    for (String key : buttonMap.keySet()) {
      Button b = new Button(key);
      for (Method m : thisSelectScreen.getDeclaredMethods()) {
        if (buttonMap.get(key).equals(m.getName())) {
          b.setOnAction(e -> {
            try {
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

    borderPane.setTop(header);

    go = new Button("GO!");
    go.setStyle(buttonStyles.getString("playerText"));
    go.setMinWidth(300);
    go.setMinHeight(100);
    go.setOnMouseClicked((e) -> {
      goToSelectScreen();
    });
    go.setDisable(true);
    VBox bottomElements = new VBox();
    bottomElements.getChildren().add(go);
    bottomElements.setAlignment(TOP_CENTER);
    borderPane.setBottom(bottomElements);
    return borderPane;
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      Scene selectScene = new Scene(makeBorderPane());
      currentScene = selectScene;
      currentStage = primaryStage;
      primaryStage.setScene(selectScene);
      primaryStage.setHeight(800);
      primaryStage.setWidth(1200);
      initStages();
      primaryStage.show();
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
