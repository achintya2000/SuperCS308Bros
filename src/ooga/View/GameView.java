package ooga.View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Preloader.StateChangeNotification;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.Model.Characters.Character2;
import ooga.Model.Player;

import java.util.ArrayList;

public class GameView extends Application implements ViewInternal {

  private ArrayList<Player> playerList;
  private Scene scene;
  private Group root;
  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {
  }


  public void setKeyBinds() {
    // Main game loop
    AnimationTimer animationTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        //Update and render
        scene.setOnKeyPressed(e -> {
          if (e.getCode() == KeyCode.D) {
            playerList.get(0).getMyCharacter().moveRight();
          }
          if (e.getCode() == KeyCode.T) {
            playerList.get(0).getMyCharacter().attack();
          }
          if (e.getCode() == KeyCode.W) {
            playerList.get(0).getMyCharacter().jump();
          }
          if (e.getCode() == KeyCode.A) {
            playerList.get(0).getMyCharacter().moveLeft();
          }

          if (e.getCode() == KeyCode.RIGHT) {
            playerList.get(1).getMyCharacter().moveRight();
          }
          if (e.getCode() == KeyCode.L) {
            playerList.get(1).getMyCharacter().attack();
          }
          if (e.getCode() == KeyCode.UP) {
            playerList.get(1).getMyCharacter().jump();
          }
          if (e.getCode() == KeyCode.LEFT) {
            playerList.get(1).getMyCharacter().moveLeft();
          }
        });

        scene.setOnKeyReleased(e -> {
          if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.A) {
            playerList.get(0).getMyCharacter().idle();
          }
          if (e.getCode() == KeyCode.T){
            //if (spriteAnimation.)
          }

          if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.LEFT) {
            playerList.get(1).getMyCharacter().idle();
          }
          if (e.getCode() == KeyCode.L){
            //if (spriteAnimation.)
          }
        });

      }
    };
    animationTimer.start();
  }

  public GameView(ArrayList playerlist)
  {
    this.playerList = playerlist;
  }

  @Override
  public void start(Stage primaryStage) {
    root = new Group();
    for(Player player: playerList)
    {
      root.getChildren().add(player.getMyCharacter().getCharacterImage());
    }
    scene = new Scene(root, 1200, 800);
    primaryStage.setTitle("FIGHT!");
    primaryStage.setScene(scene);
    primaryStage.show();
    setKeyBinds();
  }
}
