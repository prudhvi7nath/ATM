package main.java;

/**
 * @author 
 *
 */
public class Deposit extends Transaction {

  private double amount; // amount to deposit
  private InputKey keypad; // reference to keypad
  private DepositSlot depositSlot; // reference to deposit slot
  private final static int CANCELED = 0; // constant for cancel option

  // Deposit constructor
  public Deposit(int userAccountNumber, Display atmScreen, UserDetails atmBankDatabase,
                 InputKey atmKeypad, DepositSlot atmDepositSlot) {
    // initialize superclass variables
    super(userAccountNumber, atmScreen, atmBankDatabase);
    // initialize references to keypad and deposit slot
    keypad = atmKeypad;
    depositSlot = atmDepositSlot;
  }

  // perform transaction
  public void execute() {
    UserDetails bankDatabase = getBankDatabase(); // get reference
    Display screen = getScreen(); // get reference
    amount = promptForDepositAmount(); // get deposit amount from user
    // check whether user entered a deposit amount or canceled
    if (amount != CANCELED) {
      // request deposit envelope containing specified amount
      screen.displayMessage("Please insert a deposit envelope containing ");
      screen.displayAmount(amount);
      screen.displayMessageLine(".");
      // receive deposit envelope
      boolean envelopeReceived = depositSlot.isEnvelopeReceived();
      // check whether deposit envelope was received
      if (envelopeReceived) {
        screen.displayMessageLine("Your Cash has been received.\n");
        // credit account to reflect the deposit
        bankDatabase.credit(getAccountNumber(), amount);
      } else { // deposit envelope not received
        screen.displayMessageLine("You did not insert an envelope,\n" +
                                  "    so the ATM has canceled your transaction.");
      }
    } else {
      screen.displayMessageLine("Canceling transaction...");
    }
  }

  private double promptForDepositAmount() {
    Display screen = getScreen(); // get reference to screen
    screen.displayMessage("Please enter a deposit amount in\n" +
                          "    Paise (or 0 to cancel): ");
    int input = keypad.getInput(); // receive input of deposit amount
    // check whether the user canceled or entered a valid amount
    if (input == CANCELED) {
      return CANCELED;
    } else {
      return (double) input /100; // return  amount
    }
  }

}
