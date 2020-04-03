package ooga.Controller;

import java.util.*;

/**
 * The controller will take data from the front and back ends and contains
 * methods that deal with basic game logic, like whether the player
 * is out of bound and whether the game is over.
 */
public interface ControllerInternal {

    /**
     * 
     */
    public void checkWithinBoundries();

    /**
     * 
     */
    public void isGameOver();

}