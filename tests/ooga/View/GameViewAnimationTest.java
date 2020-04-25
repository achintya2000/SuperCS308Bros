package ooga.View;

import ooga.Controller.Controller;
import ooga.Model.Characters.Bunny;
import ooga.Model.Player;
import ooga.Model.StageClasses.Platform;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class GameViewAnimationTest {

    Bunny bunny1 = new Bunny("bunny1", 1);
    Bunny bunny2 = new Bunny("bunny2", 2);
    Platform platform = new Platform(200,600,1000,100, false);
    GameView gv = new GameView(bunny1, bunny2, platform);

    @Test
    void testGravity(){
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMyCharacter(bunny1);
        player2.setMyCharacter(bunny2);

        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<Platform> platformList = new ArrayList<>();
        platformList.add(platform);
        playerList.add(player1);
        playerList.add(player2);

        Controller gva = new Controller(gv, playerList, platformList);

        assertTrue(bunny1.getCenterY() >= 0);
    }

    @Test
    void testIntersection(){

        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMyCharacter(bunny1);
        player2.setMyCharacter(bunny2);

        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<Platform> platformList = new ArrayList<>();
        platformList.add(platform);
        playerList.add(player1);
        playerList.add(player2);

        Controller gva = new Controller(gv, playerList, platformList);
        assertFalse(bunny1.getINTERSECTS());
    }

    @Test
    void testDirectionalCollision(){
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMyCharacter(bunny1);
        player2.setMyCharacter(bunny2);

        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<Platform> platformList = new ArrayList<>();
        platformList.add(platform);
        playerList.add(player1);
        playerList.add(player2);

        Controller gva = new Controller(gv, playerList, platformList);
        assertFalse(bunny1.getLEFT_COLLIDE());
        assertFalse(bunny1.getRIGHT_COLLIDE());
        assertFalse(bunny1.getBOTTOM_COLLIDE());
    }
}
