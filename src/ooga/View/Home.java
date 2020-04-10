package ooga.View;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home extends Application {

  @Override
  public void start(Stage primaryStage) {
    try{
      primaryStage.setScene(new Scene(makeGridPane()));
    } catch (IOException e){
      System.out.println(e.getLocalizedMessage());
    }
    primaryStage.show();
  }
  private VBox makeGridPane() throws IOException {
    VBox myGP = new VBox();
    HashMap<String, String> buttonMap = new HashMap<>();
    Properties props = new Properties();
    props.load(Home.class.getResourceAsStream("home_buttons.properties"));
    for(String s : props.stringPropertyNames()){
      buttonMap.put(s, props.getProperty(s));
    }
    Class<?> thisHome = Home.class;
    for(String key : buttonMap.keySet()){
      Button b = new Button(key);
      for(Method m : thisHome.getDeclaredMethods()){
        if(buttonMap.get(key).equals(m.getName())){
          b.setOnAction(e -> {
            try{
              m.setAccessible(true);
              m.invoke(this);
            } catch (IllegalAccessException ex) {
              ex.printStackTrace();
            } catch (InvocationTargetException ex) {
              ex.printStackTrace();
            }
          });
        }
      }
      myGP.getChildren().add(b);
    }
    return myGP;
   }


   private void play() {
    new GameView().start(new Stage());
   }
   private void help(){

   }
   private void settings(){

   }
}
