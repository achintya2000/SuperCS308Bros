package ooga.View;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import ooga.Controller.MusicManager;

import java.io.File;

public class Home extends Application {

  private Stage currentStage;

  @Override
  public void start(Stage primaryStage) {
    Scene homeScreen = new Scene(makeGridPane());
    primaryStage.setScene(homeScreen);
    primaryStage.setHeight(800);
    primaryStage.setWidth(1200);
    currentStage = primaryStage;
    primaryStage.show();

    MusicManager musicManager = new MusicManager();
    musicManager.playBattlefieldMusic();
  }

  private VBox makeGridPane() {
    VBox myGP = new VBox();
    BackgroundImage homeScreen = new BackgroundImage(
        new Image("SmashHomeScreen.jpg", 1200, 800, false, true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    myGP.setBackground(new Background(homeScreen));
    myGP.setOnMouseClicked(e -> play());
    return myGP;
  }


  private void play() {
    currentStage.hide();
    new StageSelect().start(new Stage());
  }

  private void help() {

  }

  private void settings() {

  }
}
