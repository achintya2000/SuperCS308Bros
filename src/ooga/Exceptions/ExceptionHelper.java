package ooga.Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ExceptionHelper {

  public ExceptionHelper(Exception e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(e.getMessage());
    alert.setResizable(true);
    alert.show();
  }

}
