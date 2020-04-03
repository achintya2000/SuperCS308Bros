package ooga.Model;

import java.util.*;

/**
 * The model external will what retrieves information from the front end to set up the selection screens and
 * player information. Data binding will be extensively used to reduce getters and setters. 
 */
public interface ModelExternal {

    /**
     * 
     */
    public void setStock();

    /**
     * 
     */
    public void setStamina();

    /**
     * 
     */
    public void getImageViews();

    /**
     * 
     */
    public void setCharacter();

    /**
     * 
     */
    public void setStage();

    /**
     * 
     */
    public void resetGame();

}