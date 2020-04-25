package ooga.Model.Characters;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class BunnyTest {
  @Test
  void testJump(){
    assertThrows(NullPointerException.class, () -> {
      Bunny testBunny = new Bunny("TestBunny");
      testBunny.jump();
    });
  }

  @Test
  void testMoveLeft(){
    assertDoesNotThrow(() -> {
      Bunny testBunny= new Bunny("TestBunny");
      testBunny.moveLeft();
    });
  }

  @Test
  void testMoveRight(){
    assertDoesNotThrow(() -> {
      Bunny testBunny = new Bunny("TestBunny");
      testBunny.moveRight();
    });
  }

  @Test
  void testMoveDown(){
    assertDoesNotThrow(() -> {
      Bunny testBunny = new Bunny("TestBunny");
      testBunny.moveDown();
    });
  }


  @Test
  void testAttack(){
    assertDoesNotThrow(() -> {
      AbstractCharacter testBunny = new Bunny("TestBunny");
      testBunny.attack();
    });
  }

  @Test
  void testGetCharacterImage(){
    assertNotNull(new Bunny("TestBunny").getCharacterImage());
  }

  @Test
  void testGetCenterY(){
    assertTrue((new Bunny("TestBunny").getCenterY() >= 0));
  }

  @Test
  void testGetHitBox(){
    assertNotNull(new Bunny("TestBunny").getHitBox());
  }

  @Test
  void testGetHurtBox(){
    assertNotNull(new Bunny("TestBunny").getHurtBox());
  }

  @Test
  void testImageBoxSync(){
    int OFFSET = 25;
    Bunny testBunny = new Bunny("TestBunny");
    assertTrue((testBunny.getCharacterImage().getX() + OFFSET) == testBunny.getHurtBox().getX());
    assertTrue(testBunny.getCharacterImage().getX() == testBunny.getCharacterImage().getY());
  }

}
