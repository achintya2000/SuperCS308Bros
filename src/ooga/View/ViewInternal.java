package ooga.View;

import java.util.*;

/**
 * This will primarily be working getting user facing activities. It will record things such as what character and
 * state the user picks as well as displaying score and resetting the game and settings bindings so the controller may
 * pass data to the backend.
 */
public interface ViewInternal {

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