package ooga.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyBindManagerTest {

    KeyBindManager keyBindManager = new KeyBindManager();

    @Test
    void defaultKeyTest() {
        assertEquals(keyBindManager.getPlayer1LeftKey(), "A");
        assertEquals(keyBindManager.getPlayer1RightKey(), "D");
        assertEquals(keyBindManager.getPlayer1JumpKey(), "W");
        assertEquals(keyBindManager.getPlayer1FallKey(), "S");
    }

    @Test
    void defaultPlayer2KeyTest() {
        assertEquals(keyBindManager.getPlayer2LeftKey(), "LEFT");
        assertEquals(keyBindManager.getPlayer2RightKey(), "RIGHT");
        assertEquals(keyBindManager.getPlayer2JumpKey(), "UP");
        assertEquals(keyBindManager.getPlayer2FallKey(), "DOWN");
    }

    @Test
    void setPlayer1KeyBinds() {
        keyBindManager.setPlayer1KeyBinds("Z", "C", "S", "X", "F", "G", false);
        assertEquals(keyBindManager.getPlayer1LeftKey(), "Z");
        assertEquals(keyBindManager.getPlayer1AttackKey(), "F");
    }

    @Test
    void setPlayer2KeyBinds() {
    }
}