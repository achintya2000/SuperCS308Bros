package ooga.Model;

import java.util.*;

/**
 * The model internal class will deal with much of the back end data. It will hand doing actions the backend when it
 * detects users pressing certain keys. The result of these actions will update certain values that are data bound
 * to the front end.
 */
public interface ModelInternal {

    /**
     * 
     */
    public void getStocks();

    /**
     * 
     */
    public void getStamina();

    /**
     * 
     */
    public void jump();

    /**
     * 
     */
    public void upTilt();

    /**
     * 
     */
    public void downTilt();

    /**
     * 
     */
    public void forwardTilt();

    /**
     * 
     */
    public void down();

    /**
     * 
     */
    public void left();

    /**
     * 
     */
    public void right();

    /**
     * 
     */
    public void setBackground();

}