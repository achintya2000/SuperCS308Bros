package ooga.View;

import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.Model.Characters.AbstractCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterSelectTest extends Application {

  CharacterSelect myCharacterSelect;

  @Override
  public void start(Stage primaryStage) throws Exception {
      myCharacterSelect = new CharacterSelect(primaryStage);

  }

  @Test
  void initCharacters() {
  }

  @Test
  void p1Ready() {
  }

  @Test
  void p2Ready() {

  }

  @Test
  void createGame() {
  }

  @Test
  void checkAllPlayersChosen() {
  }


}