package ooga.View;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import ooga.Model.Characters.AbstractCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StageSelectTest {

  StageSelect myStageSelect;

  Button myGoButton;

  @BeforeEach
  void setUp() throws FileNotFoundException {


  }

  @Test
  void initStages() {

  }

  @Test
  void goToSelectScreen() {
  }

  @Test
  void start() {
    myGoButton = myStageSelect.getGo();
    if (myGoButton != null) {
      assertFalse(myGoButton.isDisable());
    }
  }
}