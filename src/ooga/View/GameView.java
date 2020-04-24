package ooga.View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Controller.KeyBindManager;
import ooga.Controller.KeyBindingController;
import ooga.Exceptions.ExceptionHelper;
import ooga.Model.Characters.AbstractCharacter;
import ooga.Model.Player;

import java.util.ArrayList;
import ooga.Model.Stages.Platform;
import ooga.networking.Client.MPClient;
import ooga.networking.Server.MPServer;

public class GameView extends Application implements ViewInternal {

  private final double GRAVITY = 1.5;

  private Stage mainStage;
  private ArrayList<Player> playerList;
  private Scene scene;
  private Pane root;

  private BooleanProperty A_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty D_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty LEFT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty RIGHT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty S_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty DOWN_PRESSED = new SimpleBooleanProperty();

  public BooleanProperty a_PRESSEDProperty() {
    return A_PRESSED;
  }

  public BooleanProperty d_PRESSEDProperty() {
    return D_PRESSED;
  }

  public BooleanProperty LEFT_PRESSEDProperty() {
    return LEFT_PRESSED;
  }

  public BooleanProperty RIGHT_PRESSEDProperty() {
    return RIGHT_PRESSED;
  }

  public BooleanProperty s_PRESSEDProperty() {
    return S_PRESSED;
  }

  public BooleanProperty DOWN_PRESSEDProperty() {
    return DOWN_PRESSED;
  }


  private BooleanProperty W_PRESSED = new SimpleBooleanProperty();
  //private BooleanProperty UP_PRESSED = new SimpleBooleanProperty();

  public BooleanProperty W_PRESSEDProperty() {
    return W_PRESSED;
  }

  //private BooleanProperty T_PRESSED = new SimpleBooleanProperty();
  //private BooleanProperty L_PRESSED = new SimpleBooleanProperty();

  AbstractCharacter player1;
  AbstractCharacter player2;

  int y;
  int y2;

  ArrayList<Platform> platforms;

  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {
  }


  public GameView(ArrayList playerlist, Pane root, ooga.Model.Stages.Stage chosenStage) {
    this.playerList = playerlist;
    this.root = root;
    player1 = playerList.get(0).getMyCharacter();
    player2 = playerList.get(1).getMyCharacter();
    platforms = chosenStage.getPlatforms();
    BackgroundImage stageBackground = new BackgroundImage(chosenStage.getBackground(),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    root.setBackground(new Background(stageBackground));
    root.getChildren().addAll(platforms);
    Rectangle healthBar1 = new Rectangle(100, 100, 1000, 50);
    Rectangle healthBar2 = new Rectangle(900, 100, 1000, 50);
    healthBar1.setFill(Color.GREEN);
    healthBar2.setFill(Color.GREEN);
    healthBar1.widthProperty().bind(player1.healthProperty());
    healthBar2.widthProperty().bind(player2.healthProperty());
    root.getChildren().add(healthBar1);
    root.getChildren().add(healthBar2);

  }

  @Override
  public void start(Stage primaryStage) {
    mainStage = primaryStage;
    scene = new Scene(root, 1200, 800);

    new KeyBindingController(this, scene, player1, player2);

    boolean server = true;

    if (server) {
      new MPServer(this);
    } else {
      new MPClient(this);
    }

    primaryStage.setTitle("FIGHT!");
    primaryStage.setScene(scene);
    primaryStage.show();

    AnimationTimer animationTimer = new GameViewAnimation(this, playerList, platforms, mainStage);
    animationTimer.start();

  }

}