package ooga.Model;

import ooga.Model.Characters.AbstractCharacter;

/**
 *
 */
public class Player {

  private int myStocks;
  private int myStamina;
  private AbstractCharacter myCharacter;
  private Boolean hasChosenChar = false;

  public Player() {
  }

  public void setMyCharacter(AbstractCharacter myCharacter) {
    this.myCharacter = myCharacter;
  }

  public AbstractCharacter getMyCharacter()
  {
    return  myCharacter;
  }

  /**
   * Getter for myStamina
   *
   * @return
   */
  public int getStamina() {
    return myStamina;
  }

  public Boolean getHasChosenChar() {
    return hasChosenChar;
  }

  public void setHasChosenChar(Boolean hasChosenChar) {
    this.hasChosenChar = hasChosenChar;
  }

  /**
   * Setter for myStamina
   *
   * @param newStamina the new amount of stocks
   */
  public void setStamina(int newStamina) {
    myStamina = newStamina;
  }

  /**
   * Getter for myStocks
   *
   * @return
   */
  public int getStocks() {
    return myStocks;
  }

  /**
   * Setter for myStocks
   *
   * @param newStock the new amount of stocks
   */
  public void setStocks(int newStock) {
    myStocks = newStock;
  }
}
