package ooga.View;


import java.awt.MouseInfo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.sound.midi.SysexMessage;
import ooga.Controller.MusicManager;

import java.io.File;

import static javafx.geometry.Pos.CENTER;

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
    //musicManager.playMainMenuMusic();
  }

  private BorderPane makeGridPane() {
    BorderPane myGP = new BorderPane();
    BackgroundImage homeScreen = new BackgroundImage(
        new Image("SmashHomeScreen.jpg", 1200, 800, false, true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    myGP.setBackground(new Background(homeScreen));
    myGP.setOnMouseClicked(e -> play());

    Button playLocal = new Button("PLAY LOCAL");
    playLocal.setAlignment(Pos.CENTER_LEFT);
    Button playOnline = new Button("PLAY ONLINE");
    playLocal.setAlignment(Pos.CENTER_RIGHT);

    HBox buttonHolder = new HBox();
    buttonHolder.getChildren().addAll(playLocal,playOnline);
    buttonHolder.setAlignment(CENTER);

    myGP.setCenter(buttonHolder);

    return myGP;
  }


  private void play() {
    currentStage.hide();
    new StageSelect().start(new Stage());

  }

  private void help() {

  }

  public void settings()
  {
    new SettingsPopUp();
  }
}
