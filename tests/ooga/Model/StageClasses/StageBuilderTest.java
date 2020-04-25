package ooga.Model.StageClasses;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class StageBuilderTest {

  private StageBuilder myStageBuilder;

  @Test
  void testBattleField() throws FileNotFoundException {
    myStageBuilder = new StageBuilder("data/stages/stagedata/battlefield.json");
    assertEquals(4, myStageBuilder.getPlatforms().size());
    assertEquals(2, myStageBuilder.getSpawnCoordinates().size());
    boolean[] hollow = new boolean[] {false, true, true, true};
    int[] xPos = new int[] {225, 318, 514, 709};
    int[] yPos = new int[] {325, 209, 94, 209};
    int[] width = new int[] {750, 175, 175, 175};
    int[] height = new int[] {200, 25, 25, 25};

    int i = 0;
    for (Platform p : myStageBuilder.getPlatforms()) {
      assertEquals(p.getHollow(), hollow[i]);
      assertEquals(p.getX(), xPos[i]);
      assertEquals(p.getY(), yPos[i]);
      assertEquals(p.getHeight(), height[i]);
      assertEquals(p.getWidth(), width[i]);

      i++;
    }

    List<List<Integer>> coor = myStageBuilder.getSpawnCoordinates();
    int[][] expectedCoors = new int[][] {{275, 325}, {900, 325}};
    for (int j = 0; j < coor.size(); j++) {
      assertEquals(expectedCoors[j][0], coor.get(j).get(0));
      assertEquals(expectedCoors[j][1], coor.get(j).get(1));
    }

  }

  @Test
  void testBridge() throws FileNotFoundException {
    myStageBuilder = new StageBuilder("data/stages/stagedata/bridge.json");
    assertEquals(1, myStageBuilder.getPlatforms().size());
    assertEquals(2, myStageBuilder.getSpawnCoordinates().size());
  }

  @Test
  void testFinal() throws FileNotFoundException {
    myStageBuilder = new StageBuilder("data/stages/stagedata/finaldestination.json");
    assertEquals(1, myStageBuilder.getPlatforms().size());
    assertEquals(2, myStageBuilder.getSpawnCoordinates().size());
  }

  @Test
  void testNorfair() throws FileNotFoundException {
    myStageBuilder = new StageBuilder("data/stages/stagedata/norfair.json");
    assertEquals(5, myStageBuilder.getPlatforms().size());
    assertEquals(2, myStageBuilder.getSpawnCoordinates().size());
  }

  @Test
  void checkBadPath() throws FileNotFoundException {
    assertThrows(ExceptionInInitializerError.class, () -> new StageBuilder("data/stages/stagedata/battlfield.json"));
  }

}