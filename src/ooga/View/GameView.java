package ooga.View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.Controller.KeyBindingController;
import ooga.Controller.MusicManager;
import ooga.Model.Characters.AbstractCharacter;
import ooga.Model.Player;

import java.util.ArrayList;
import ooga.Model.StageClasses.Platform;
import ooga.networking.Client.MPClient;
import ooga.networking.Server.MPServer;

public class GameView extends Application implements ViewInternal {

  private final double GRAVITY = 1.5;

  private Stage mainStage;
  private ArrayList<Player> playerList;
  private Scene scene;
  private Pane root;

  private BooleanProperty PLAYER_1_LEFT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_1_RIGHT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_1_FALL_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_1_JUMP_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_1_ATTACK_PRESSED = new SimpleBooleanProperty();

  private BooleanProperty PLAYER_2_LEFT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_2_RIGHT_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_2_DOWN_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_2_JUMP_PRESSED = new SimpleBooleanProperty();
  private BooleanProperty PLAYER_2_ATTACK_PRESSED = new SimpleBooleanProperty();

  AbstractCharacter player1;
  AbstractCharacter player2;
  ArrayList<Platform> platforms;

  private boolean joiningMatch;
  private String ipAddress;
  private boolean isLocal;

  @Override
  public void resetGame() {

  }

  @Override
  public void setCharacter() {

  }

  @Override
  public void setStage() {
  }


  public GameView(ArrayList playerlist, Pane root, ooga.Model.StageClasses.Stage chosenStage, boolean joiningMatch, String ipAddress, boolean isLocal) {
    this.isLocal = isLocal;
    this.ipAddress = ipAddress;
    this.joiningMatch = joiningMatch;
    this.playerList = playerlist;
    this.root = root;
    player1 = playerList.get(0).getMyCharacter();
    player1.setCenterX(chosenStage.getSpawnCoordinates().get(0).get(0));
    player1.setCenterY(chosenStage.getSpawnCoordinates().get(0).get(1) - 100);
    player2 = playerList.get(1).getMyCharacter();
    player2.setCenterX(chosenStage.getSpawnCoordinates().get(1).get(0));
    player2.setCenterY(chosenStage.getSpawnCoordinates().get(1).get(1) - 100);
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
    //MusicManager.clearMusic();
    //MusicManager.playBattlefieldMusic();
    new KeyBindingController(this, scene, player1, player2);

    if(!isLocal) {
      if (!joiningMatch) {
        new MPServer(this);
      } else {
        new MPClient(this, ipAddress);
      }
    }


    primaryStage.setTitle("FIGHT!");
    primaryStage.setScene(scene);
    primaryStage.show();

    AnimationTimer animationTimer = new GameViewAnimation(this, playerList, platforms, mainStage);
    animationTimer.start();

  }

  public BooleanProperty getPlayer1LeftProp() {
    return PLAYER_1_LEFT_PRESSED;
  }

  public BooleanProperty getPlayer1RightProp() {
    return PLAYER_1_RIGHT_PRESSED;
  }

  public BooleanProperty getPlayer1JumpProp() {
    return PLAYER_1_JUMP_PRESSED;
  }

  public BooleanProperty getPlayer1FallProp() {
    return PLAYER_1_FALL_PRESSED;
  }

  public BooleanProperty getPlayer1AttackProp() {
    return PLAYER_1_ATTACK_PRESSED;
  }

  public BooleanProperty getPlayer2LeftProp() {
    return PLAYER_2_LEFT_PRESSED;
  }

  public BooleanProperty getPlayer2RightProp() {
    return PLAYER_2_RIGHT_PRESSED;
  }

  public BooleanProperty getPlayer2FallProp() {
    return PLAYER_2_DOWN_PRESSED;
  }

  public BooleanProperty getPlayer2JumpProp() {
    return PLAYER_2_JUMP_PRESSED;
  }

  public BooleanProperty getPlayer2AttackProp() {
    return PLAYER_2_ATTACK_PRESSED;
  }

  public boolean getIsLocal()
  {
    return  isLocal;
  }

}