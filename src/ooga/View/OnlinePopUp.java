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

import java.util.ResourceBundle;

public class OnlinePopUp {

  public static final ResourceBundle buttonStyles = ResourceBundle.getBundle("ooga.Resources.stylesheets.buttonStyle");

  public OnlinePopUp()
  {
    System.out.println("Yeet");
    Stage onlineStage=new Stage();
    onlineStage.initModality(Modality.APPLICATION_MODAL);
    onlineStage.setTitle("Play Online");

    BorderPane bp = new BorderPane();

    VBox elements = new VBox(10);
    elements.setAlignment(Pos.CENTER);
    TextField ipAddress = new TextField("Type in your desired IP address");
    Button connect = new Button("Connect");
    elements.getChildren().addAll(ipAddress,connect);
    connect.setOnAction(e -> {
      // FILL IN CONNECTION WITH IP ADDRESS HERE
      System.out.println(ipAddress.getText());
    });
    connect.setStyle(buttonStyles.getString("playerText"));

    bp.setCenter(elements);
    Scene settingsScene= new Scene(bp, 500, 200);

    onlineStage.setScene(settingsScene);

    onlineStage.showAndWait();
  }
}