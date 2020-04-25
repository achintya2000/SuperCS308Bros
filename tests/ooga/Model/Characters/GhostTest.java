package ooga.Model.Characters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GhostTest {
  @Test
  void testJump(){
    assertThrows(NullPointerException.class, () -> {
      Ghost testGhost = new Ghost("TestBunny");
      testGhost.jump();
    });
  }

  @Test
  void testMoveLeft(){
    assertDoesNotThrow(() -> {
      Ghost testGhost = new Ghost("TestBunny");
      testGhost.moveLeft();
    });
  }

  @Test
  void testMoveRight(){
    assertDoesNotThrow(() -> {
      Ghost testGhost = new Ghost("TestBunny");
      testGhost.moveRight();
    });
  }

  @Test
  void testMoveDown(){
    assertDoesNotThrow(() -> {
      Ghost testGhost = new Ghost("TestBunny");
      testGhost.moveDown();
    });
  }


  @Test
  void testAttack(){
    assertDoesNotThrow(() -> {
      Ghost testGhost = new Ghost("TestBunny");
      testGhost.attack();
    });
  }

  @Test
  void testGetCharacterImage(){
    assertNotNull(new Ghost("TestBunny").getCharacterImage());
  }

  @Test
  void testGetCenterY(){
    assertTrue((new Ghost("TestBunny").getCenterY() >= 0));
  }

  @Test
  void testGetHitBox(){
    assertNotNull(new Ghost("TestBunny").getHitBox());
    assertNotEquals(new Ghost("TestGhost").getHitBox().getCenterX(), 0);
  }

  @Test
  void testGetHurtBox(){
    assertNotNull(new Ghost("TestBunny").getHurtBox());
  }

  @Test
  void testImageBoxSync(){
    int OFFSET = 25;
    Ghost testGhost = new Ghost("TestBunny");
    assertTrue((testGhost.getCharacterImage().getX() + OFFSET) == testGhost.getHurtBox().getX());
    assertTrue(testGhost.getCharacterImage().getX() == testGhost.getCharacterImage().getY());
  }

}
