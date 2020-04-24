package ooga.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class OnlinePopUp {
  private Properties prop;
  private Button createServer;
  private Button joinGame;
  private Stage onlineStage;
  private boolean isLocal;

  public OnlinePopUp(Properties prop, boolean isLocal)
  {
    this.isLocal = isLocal;
    this.prop  = prop;
    onlineStage= new Stage();
    onlineStage.initModality(Modality.APPLICATION_MODAL);
    onlineStage.setTitle("Play Online");

    BorderPane bp = new BorderPane();

    createButtons(prop);

    VBox elements = new VBox(10);
    elements.setAlignment(Pos.CENTER);
    elements.getChildren().addAll(createServer,joinGame);
    bp.setCenter(elements);
    Scene settingsScene= new Scene(bp, 500, 200);

    onlineStage.setScene(settingsScene);

    onlineStage.show();
  }

  private void createButtons(Properties prop) {
    createServer = new Button("Create Match");
    createServer.setOnAction(e -> {
      onlineStage.hide();
      StageSelect stageSelect = null;
      try {
        stageSelect = new StageSelect(isLocal);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      System.out.println(stageSelect.prop.keySet());
      stageSelect.start(new Stage());
    });
    createServer.setStyle(prop.getProperty("playerText"));

    joinGame = new Button("Join Match");
    joinGame.setOnAction(e -> {
      onlineStage.hide();
      IPAddress ipAddress = new IPAddress(prop);
    });
    joinGame.setStyle(prop.getProperty("playerText"));
  }
}