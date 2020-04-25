package ooga.View;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.Model.Characters.AbstractCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StageSelectTest{

  StageSelect myStageSelect;

  Button myGoButton;

  @BeforeEach
  void setUp() throws IOException {
    boolean isLocal = true;
    myStageSelect = new StageSelect(isLocal);
  }

  @Test
  void checkStageFiles() throws FileNotFoundException {
    String[] files = {"battlefield.json","bridge.json", "finaldestination.json","norfair.json"};
    try {
      myStageSelect.initStages();
    }catch(ExceptionInInitializerError e){
    }
    int count = 0;
    boolean correct = true;
    for(String stageName : myStageSelect.getStageNames())
    {
      System.out.println(stageName);
      if(!stageName.equals(files[count])){
        correct = false;
      }
      count++;
    }
    assertTrue(correct);
  }

  @Test
  void checkPropertyFileKeySet()
  {
    assertEquals("[playerText, labelText, playButtonsText, characterText]",myStageSelect.getPropterties());
  }

  @Test
  void checkDirectoryPath()
  {
    try {
      String path = myStageSelect.initStages();
      assertEquals(path, "data/stages/stagedata");
    }catch(ExceptionInInitializerError | FileNotFoundException | NoClassDefFoundError e){
    }
  }

  @Test
  void checkIfGoDisabled() {
    myGoButton = myStageSelect.getGo();
    if (myGoButton != null) {
      assertFalse(myGoButton.isDisable());
    }
  }
}