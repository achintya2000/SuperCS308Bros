package ooga.View;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Home extends Application {

  @Override
  public void start(Stage primaryStage) {
    try{
      Scene homeScreen = new Scene(makeGridPane());
      primaryStage.setScene(homeScreen);
      primaryStage.setHeight(800);
      primaryStage.setWidth(1200);
    } catch (IOException e){
      System.out.println(e.getLocalizedMessage());
    }
    primaryStage.show();
  }

  private VBox makeGridPane() throws IOException {
    VBox myGP = new VBox();
    BackgroundImage homeScreen = new BackgroundImage(new Image("SmashHomeScreen.jpg",1200,800,false,true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    myGP.setBackground(new Background(homeScreen));
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
