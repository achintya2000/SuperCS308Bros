package ooga.View;

import java.util.*;

/**
 * This will primarily be working with user facing activities. It will record things such as what character and
 * state the user picks as well as displaying score and resetting the game.
 */
public interface ViewInternal {

    /**
     * 
     */
    public void displayScore();

    /**
     * 
     */
    public void resetGame();

    /**
     *
     */
    public void setCharacter();

    /**
     *
     */
    public void setStage();

}