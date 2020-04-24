package ooga.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.Controller.KeyBindManager;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class IPAddress {
  private Properties prop;
  private Stage ipAddressStage;
  private Button connect;
  private VBox elements= new VBox(10);
  private TextField ipAddress;
  private BorderPane borderPane= new BorderPane();
  public IPAddress(Properties prop)
  {
    this.prop  = prop;
    ipAddressStage=new Stage();
    ipAddressStage.initModality(Modality.APPLICATION_MODAL);
    ipAddressStage.setTitle("Play Online");

    elements.setAlignment(Pos.CENTER);
    ipAddress = new TextField("Type in your desired IP address");
    connect = new Button("Connect");
    elements.getChildren().addAll(ipAddress,connect);

    createConnectButton(prop);

    borderPane.setCenter(elements);
    Scene settingsScene= new Scene(borderPane, 500, 200);

    ipAddressStage.setScene(settingsScene);

    ipAddressStage.show();
  }

  private void createConnectButton(Properties prop) {
    connect.setOnAction(e -> {
      // FILL IN CONNECTION WITH IP ADDRESS HERE
      System.out.println(ipAddress.getText());

      System.out.println("Going to Select Screen ... ");
      ipAddressStage.hide();
      CharacterSelect characterSelect = null;
      try {
        characterSelect = new CharacterSelect();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      characterSelect.start(new Stage());
    });
    connect.setStyle(prop.getProperty("playerText"));
  }
}