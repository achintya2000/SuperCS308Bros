package ooga.View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ooga.Model.StageClasses.StageBuilder;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.*;

public class StageSelect{

  public Properties prop;
  private Scene currentScene;
  private BorderPane borderPane = new BorderPane();
  private Button go;
  private Stage currentStage;
  private ooga.Model.StageClasses.Stage chosenStage;

  private ArrayList<ooga.Model.StageClasses.Stage> stageList = new ArrayList<>();
  private ArrayList<Button> buttonList = new ArrayList<>();
  private Group root = new Group();
  private int colThresh = 8;

  public void settings()
  {
    new SettingsPopUp();
  }

  public void initStages() throws FileNotFoundException {

    File dir = new File("src/ooga/Model/StageClasses/Stages");
    System.out.println(dir.getAbsolutePath());
    File[] directoryListing = dir.listFiles();
    if (directoryListing != null) {
      for (File child : directoryListing) {
        System.out.println(child.getName());
        StageBuilder stage = new StageBuilder("Stages/"+child.getName());
        stageList.add(stage);
      }
    }
    GridPane charGrid = setupStageGrid();
    createStageButtons(charGrid);
  }

  private void createStageButtons(GridPane charGrid) {
    int colCount = 0;
    int rowCount = 0;
    for (ooga.Model.StageClasses.Stage stage : stageList) {
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

  private GridPane setupStageGrid() {
    GridPane charGrid = new GridPane();
    charGrid.setStyle("-fx-background-color: rgba(0,0,0, 1)");
    charGrid.setGridLinesVisible(true);
    charGrid.setMaxHeight(300);
    charGrid.setMaxWidth(800);
    borderPane.setStyle("-fx-background-color: rgba(200, 200, 240, 0.5)");
    borderPane.setCenter(charGrid);
    return charGrid;
  }


  public void goToSelectScreen() {
    System.out.println("Going to Select Screen ... ");
    currentStage.hide();
    CharacterSelect characterSelect = new CharacterSelect(chosenStage);
    characterSelect.start(new Stage());
  }


  private BorderPane makeBorderPane() throws IOException {
    VBox header = new VBox();
    header.setAlignment(CENTER);
    HBox toolbar = new HBox();
    toolbar.setSpacing(10);
    toolbar.setAlignment(TOP_CENTER);
    header.getChildren().add(toolbar);

    HashMap<String, String> buttonMap = new HashMap<>();
    Properties props = new Properties();
    props.load(new FileReader("data/buttons/toolbar.properties"));
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
    setupGoButton();
    return setupBorderPane(header);
  }

  private BorderPane setupBorderPane(VBox header) {
    borderPane.setTop(header);
    VBox bottomElements = new VBox();
    bottomElements.getChildren().add(go);
    bottomElements.setAlignment(TOP_CENTER);
    borderPane.setBottom(bottomElements);
    return borderPane;
  }

  private void setupGoButton() {
    go = new Button("GO!");
    go.setStyle(prop.getProperty("playerText"));
    go.setMinWidth(300);
    go.setMinHeight(100);
    go.setOnMouseClicked((e) -> {
      goToSelectScreen();
    });
    go.setDisable(true);
    go.setId("#GoButton");
  }

  public void start(Stage primaryStage) throws IOException {
    prop = new Properties();
    prop.load(new FileReader("data/stylesheets/buttonStyle.properties"));

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

  public Button getGo() {
    return go;
  }
}
