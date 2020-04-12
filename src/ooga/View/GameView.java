package ooga.View;

import javafx.application.Application;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.Model.Player;

import java.util.ArrayList;

public class GameView extends Application implements ViewInternal {

  private ArrayList<Player> playerList;
  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {

  }

  public GameView(ArrayList playerlist)
  {
    this.playerList = playerlist;
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setScene(new Scene(new BorderPane(), 1200, 800));
    primaryStage.show();
  }
}
