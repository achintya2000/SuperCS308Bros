package ooga.View;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.Controller.KeyBindManager;

import java.util.Collection;
import java.util.ResourceBundle;

public class ButtonsConfigPopUp {

  public static final ResourceBundle buttonStyles = ResourceBundle.getBundle("ooga.Resources.stylesheets.buttonStyle");

  public ButtonsConfigPopUp()
  {
    //System.out.println("Yeet");
    Stage buttonConfigsStage=new Stage();
    buttonConfigsStage.initModality(Modality.APPLICATION_MODAL);
    buttonConfigsStage.setTitle("Button Configurations");

    KeyBindManager buttonConfigurer = new KeyBindManager();

    BorderPane borderPane = new BorderPane();
    Label settingsText= new Label("Button Configurations");
    settingsText.setStyle(buttonStyles.getString("characterText"));
    HBox topElements = new HBox();
    topElements.setAlignment(Pos.TOP_CENTER);
    topElements.getChildren().add(settingsText);


    VBox p1Config= new VBox(10);
    Label p1Text = new Label("Player 1");
    p1Text.setStyle(buttonStyles.getString("characterText"));
    TextField leftButton1 = new TextField(buttonConfigurer.getPlayer1LeftKey());
    TextField rightButton1 = new TextField(buttonConfigurer.getPlayer1RightKey());
    TextField jumpButton1 = new TextField(buttonConfigurer.getPlayer1JumpKey());
    TextField fallButton1 = new TextField(buttonConfigurer.getPlayer1FallKey());
    TextField attackButton1 = new TextField(buttonConfigurer.getPlayer1AttackKey());
    TextField specialButton1 = new TextField(buttonConfigurer.getPlayer1SpecialKey());

    VBox p2Config= new VBox(10);
    Label p2Text = new Label("Player 2");
    p2Text.setStyle(buttonStyles.getString("characterText"));
    TextField leftButton2 = new TextField(buttonConfigurer.getPlayer2LeftKey());
    TextField rightButton2 = new TextField(buttonConfigurer.getPlayer2RightKey());
    TextField jumpButton2 = new TextField(buttonConfigurer.getPlayer2JumpKey());
    TextField fallButton2 = new TextField(buttonConfigurer.getPlayer2FallKey());
    TextField attackButton2 = new TextField(buttonConfigurer.getPlayer2AttackKey());
    TextField specialButton2 = new TextField(buttonConfigurer.getPlayer2SpecialKey());

    VBox buttonLabels = new VBox();
    Label buttonsText = new Label("Buttons");
    buttonsText.setStyle(buttonStyles.getString("characterText"));
    Label leftText = new Label("Left");
    Label rightText = new Label("Right");
    Label jumpText = new Label("Jump");
    Label fallText = new Label("Fall");
    Label attackText = new Label("Attack");
    Label specialText = new Label("Special");


    p1Config.getChildren().addAll(p1Text,leftButton1, rightButton1, jumpButton1, fallButton1,attackButton1,specialButton1);
    p2Config.getChildren().addAll(p2Text,leftButton2, rightButton2, jumpButton2, fallButton2,attackButton2,specialButton2);
    buttonLabels.getChildren().addAll(buttonsText,leftText,rightText,jumpText,fallText,attackText,specialText);

    Button configure= new Button("Configure Both Players");
    configure.setOnAction(e -> {
      buttonConfigurer.setPlayer1KeyBinds(leftButton1.getText(),rightButton1.getText(), jumpButton1.getText(), fallButton1.getText(),attackButton1.getText(),specialButton1.getText());
      buttonConfigurer.setPlayer2KeyBinds(leftButton2.getText(),rightButton2.getText(), jumpButton2.getText(), fallButton2.getText(),attackButton2.getText(),specialButton2.getText());
      buttonConfigsStage.close();
    });
    configure.setStyle(buttonStyles.getString("playerText"));
    HBox bottomElements = new HBox();
    bottomElements.setAlignment(Pos.CENTER);
    bottomElements.getChildren().add(configure);

    borderPane.setTop(topElements);
    borderPane.setBottom(bottomElements);
    borderPane.setLeft(p1Config);
    borderPane.setRight(p2Config);
    borderPane.setCenter(buttonLabels);
    Scene settingsScene= new Scene(borderPane, 500, 350);

    buttonConfigsStage.setScene(settingsScene);

    buttonConfigsStage.showAndWait();
  }
}