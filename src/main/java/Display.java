package main.java;

/**
 * @author 
 *
 */
public class Display {

  // display a message without a carriage return
  public void displayMessage(String message) {
    System.out.print(message);
  }

  // display a message with a carriage return
  public void displayMessageLine(String message) {
    System.out.println(message);
  }

  // displays amount
  public void displayAmount(double amount) {
    System.out.printf("Rs %,.2f", amount);
  }

}
