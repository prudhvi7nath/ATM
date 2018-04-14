package main.java;

/**
 * @author 
 * This is the main class to run the ATM machine
 */
public class ATMMain {

  // main method creates and runs the ATM
  public static void main(String[] args) {
    ATMLogic mobileATM = new ATMLogic(); // Loads the initials required details
    mobileATM.run(); // starts the ATM machine
  }

}
