package ooga.View;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ooga.Model.StageClasses.StageBuilder;
import java.io.*;
import java.util.ArrayList;


import static javafx.geometry.Pos.*;

public class StageSelect extends AbstractSelectScreen{
  private BorderPane borderPane= new BorderPane();
  private Button go;
  private Stage currentStage;
  private ooga.Model.StageClasses.Stage chosenStage;

  private ArrayList<ooga.Model.StageClasses.Stage> stageList= new ArrayList<>();
  private ArrayList<Button> buttonList= new ArrayList<>();
  private Group root= new Group();
  private int colThresh= 8;
  private boolean isLocal;
  private GridPane charGrid;
  private ArrayList<String> stageNames = new ArrayList<>();

  public StageSelect(boolean isLocal) throws IOException {
    super();
    this.isLocal = isLocal;
  }

  public String initStages() throws FileNotFoundException {
    File dir = new File("data/stages/stagedata");
    File[] directoryListing = dir.listFiles();
    if (directoryListing != null) {
      for (File child : directoryListing) {
        StageBuilder stage = new StageBuilder("data/stages/stagedata/"+child.getName());
        stageList.add(stage);
        stageNames.add(child.getName());
      }
    }
    charGrid = setupStageGrid();
    createStageButtons(charGrid);
    return dir.getName();
  }

  public ArrayList<String> getStageNames()
  {
    return stageNames;
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


  public void goToSelectScreen() throws IOException {
    System.out.println("Going to Select Screen ... ");
    currentStage.hide();
    CharacterSelect characterSelect = new CharacterSelect(chosenStage, isLocal);
    characterSelect.start(new Stage());
  }


  private BorderPane integrateBorderPane() throws IOException {
    VBox header = createToolbar();
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
      try {
        goToSelectScreen();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
    go.setDisable(true);
    go.setId("#GoButton");
  }

  @Override
  public void start(Stage primaryStage){
    try {
      Scene selectScene = new Scene(integrateBorderPane());
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
