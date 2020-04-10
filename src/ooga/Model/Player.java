package ooga.Model;

public class Player {
  private int myStocks;
  private int myStamina;

  /**
   * Getter for myStamina
   * @return
   */
  public int getStamina() {
    return myStamina;
  }

  /**
   * Setter for myStamina
   * @param newStamina the new amount of stocks
   */
  public void setStamina(int newStamina) {
    myStamina = newStamina;
  }

  /**
   * Getter for myStocks
   * @return
   */
  public int getStocks() {
    return myStocks;
  }

  /**
   * Setter for myStocks
   * @param newStock the new amount of stocks
   */
  public void setStocks(int newStock) {
    myStocks = newStock;
  }
}
