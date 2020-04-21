package ooga.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ooga.Model.Stages.StageBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public class SettingsPopUp{

  public static final ResourceBundle buttonStyles = ResourceBundle.getBundle("ooga.Resources.stylesheets.buttonStyle");

  public SettingsPopUp()
  {
    //System.out.println("Yeet");
    Stage settings=new Stage();
    settings.initModality(Modality.APPLICATION_MODAL);
    settings.setTitle("Settings");

    Label settingsText= new Label("Settings");
    Button buttonConfig= new Button("Button Configuration");
    Button uiTheme = new Button("UI THEME");
    Button close = new Button("Close this pop up window");

    close.setOnAction(e -> settings.close());
    buttonConfig.setOnAction(e -> new ButtonsConfigPopUp());

    VBox layout= new VBox(10);
    layout.getChildren().addAll(settingsText, buttonConfig, uiTheme, close);
    layout.setAlignment(Pos.CENTER);

    Scene settingsScene= new Scene(layout, 300, 250);

    settings.setScene(settingsScene);

    settings.showAndWait();
  }
}