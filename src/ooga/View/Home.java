package ooga.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ooga.Controller.MusicManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Home extends Application {

  private Stage currentStage;
  private BorderPane borderPane;
  private HBox buttonHolder;
  private Button playLocal;
  private Button playOnline;
  private boolean isLocal;

  @Override
  public void start(Stage primaryStage) {
    System.out.println();
    Scene homeScreen = new Scene(setupBorderPane());
    primaryStage.setScene(homeScreen);
    primaryStage.setHeight(800);
    primaryStage.setWidth(1200);
    currentStage = primaryStage;
    primaryStage.show();
    //MusicManager.playMainMenuMusic();

  }

  private BorderPane setupBorderPane() {
    borderPane = new BorderPane();
    BackgroundImage homeScreen = new BackgroundImage(
        new Image("SmashHomeScreen.jpg", 1200, 800, false, true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    borderPane.setBackground(new Background(homeScreen));

    createButtons();
    setButtonActions();

    borderPane.setCenter(buttonHolder);

    return borderPane;
  }

  private void setButtonActions() {
    playLocal.setOnAction(e -> {
      try {
        localPlay();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
    playOnline.setOnAction(e -> {
      try {
        onlinePlay();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  private void createButtons() {
    playLocal = new Button();
    playLocal.setAlignment(Pos.BOTTOM_LEFT);
    playLocal.setGraphic(new ImageView(new Image("LocalSmash.png", 575, 175, false, true)));
    playOnline = new Button();
    playOnline.setAlignment(Pos.BOTTOM_RIGHT);
    playOnline.setGraphic(new ImageView(new Image("OnlineSmash.png", 575, 175, false, true)));

    buttonHolder = new HBox();
    buttonHolder.getChildren().addAll(playLocal,playOnline);
    buttonHolder.setAlignment(Pos.BOTTOM_CENTER);
  }


  private void localPlay() throws IOException {
    currentStage.hide();
    isLocal = true;
    StageSelect stageSelect = new StageSelect(isLocal);
    System.out.println(stageSelect.prop.keySet());
    stageSelect.start(new Stage());
  }

  private void onlinePlay() throws IOException {
    currentStage.hide();
    isLocal = false;
    Properties prop = new Properties();
    prop.load(new FileReader("data/stylesheets/buttonStyle.properties"));
    new OnlinePopUp(prop, isLocal);
  }
}
