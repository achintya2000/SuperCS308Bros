package ooga.View;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
