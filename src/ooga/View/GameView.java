package ooga.View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ooga.Controller.Controller;
import ooga.Controller.GameMode;
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

  private String gameMode;

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



  public GameView(ArrayList playerlist, Pane root, ooga.Model.StageClasses.Stage chosenStage, boolean joiningMatch, String ipAddress, boolean isLocal, String gameMode) {
    this.isLocal = isLocal;
    this.ipAddress = ipAddress;
    this.joiningMatch = joiningMatch;
    this.playerList = playerlist;
    this.root = root;
    this.gameMode = gameMode;
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


    if(gameMode.equals("LIVES")){
      Text stonkBar1 = new Text(100, 200, String.valueOf(player1.STONKSProperty().get()));
      Text stonkBar2 = new Text(900, 200, String.valueOf(player2.STONKSProperty().get()));
      stonkBar1.setStyle("-fx-font-size: 20");
      stonkBar2.setStyle("-fx-font-size: 20");
      Bindings.bindBidirectional(stonkBar1.textProperty(), player1.STONKSProperty(), new CustomStringConverter());
      Bindings.bindBidirectional(stonkBar2.textProperty(), player2.STONKSProperty(), new CustomStringConverter());
      root.getChildren().add(stonkBar1);
      root.getChildren().add(stonkBar2);
    }
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

    AnimationTimer animationTimer = new GameViewAnimation(this, playerList, platforms, mainStage, gameMode);
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

  class CustomStringConverter extends StringConverter<Number>{

    @Override
    public String toString(Number object) {
      return String.valueOf(object);
    }

    @Override
    public Number fromString(String string) {
      return Integer.valueOf(string);
    }
  }

  public boolean getIsLocal()
  {
    return  isLocal;
  }

}