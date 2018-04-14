package main.java;

/**
 * @author 
 *
 */
public class Withdraw extends Transaction {

  private int amount; // amount to withdraw
  private InputKey keypad; // reference to keypad
  private CashWithdraw cashDispenser; // reference to cash dispenser
  // constant corresponding to menu option to cancel
  private final static int CANCELED = 7;

  // Withdrawal constructor
  public Withdraw(int userAccountNumber, Display atmScreen,
                    UserDetails atmBankDatabase, InputKey atmKeypad,
                    CashWithdraw atmCashDispenser) {
    // initialize superclass variables
    super(userAccountNumber, atmScreen, atmBankDatabase);
    // initialize references to keypad and cash dispenser
    keypad = atmKeypad;
    cashDispenser = atmCashDispenser;
  }

  // perform transaction
  public void execute() {
    boolean cashDispensed = false; // cash was not dispensed yet
    double availableBalance; // amount available for withdrawal
    // get references to bank database and screen
    UserDetails bankDatabase = getBankDatabase();
    Display screen = getScreen();
    // loop until cash is dispensed or the user cancels
    do {
      // obtain a chosen withdrawal amount from the user
      amount = displayMenuOfAmounts();
      // check whether user chose a withdrawal amount or canceled
      if (amount != CANCELED) {
        // get available balance of account involved
        availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        // check whether the user has enough money in the account
        if (amount <= availableBalance) {
          // check whether the cash dispenser has enough money
          if (cashDispenser.isSufficientCashAvailable(amount)) {
            // update the account involved to reflect the withdrawal
            bankDatabase.debit(getAccountNumber(), amount);
            cashDispenser.dispenseCash(amount); // dispense cash
            cashDispensed = true; // cash was dispensed
            // instruct user to take cash
            screen.displayMessageLine("Your cash has been dispensed.\n" +
                                      "    Please take your cash now.");
          } else { // cash dispenser does not have enough cash
            screen.displayMessageLine("Insufficient cash available in the ATM.\n" +
                                      "    Please choose a smaller amount.");
          }
        } else { // not enough money available in user's account
          screen.displayMessageLine("Insufficient funds in your account!\n" +
                                    "    Please choose a smaller amount.");
        }
      } else { // user chose cancel menu option
        screen.displayMessageLine("Canceling transaction...");
        return; // return to main menu because user canceled
      }
    } while (!cashDispensed);
  }

  // display a menu of withdrawal amounts and the option to cancel;
  // return the chosen amount or 0 if the user chooses to cancel
  private int displayMenuOfAmounts() {
    int userChoice = 0; // local variable to store return value
    Display screen = getScreen(); // get screen reference
    // array of amounts to correspond to menu numbers
    int amounts[] = {0, 20, 40, 60, 100, 200};
    // loop while no valid choice has been made
    while (userChoice == 0) {
      // display the menu
      screen.displayMessageLine("\n[Withdrawal Menu]");
      screen.displayMessageLine("1 - Rs 20");
      screen.displayMessageLine("2 - Rs 40");
      screen.displayMessageLine("3 - Rs 60");
      screen.displayMessageLine("4 - Rs 100");
      screen.displayMessageLine("5 - Rs 200");
      screen.displayMessageLine("6 - Custom amount upto 10000 Rs");
      screen.displayMessageLine("7 - Cancel transaction");
      screen.displayMessage("Choose a withdrawal amount: ");
      int input = keypad.getInput(); // get user input through keypad
      // determine how to proceed based on the input value
      switch (input) {
        case 1: 
        case 2:
        case 3: 
        case 4:
        case 5:
          userChoice = amounts[input]; // save user's choice
          break;
        case 6:
        	userChoice = customWithdraw(); // save user's choice
            break;
        case CANCELED: // the user chose to cancel
          userChoice = CANCELED; // save user's choice
          break;
        default: // the user did not enter a value from 1-6
          screen.displayMessageLine("Invalid selection. Try again.");
      }
    }
    return userChoice; // return withdrawal amount or CANCELED
  }

/**
 * 
 */
private int customWithdraw() {
	boolean condition = true;
	Display screen = getScreen(); 
	screen.displayMessage("\nEnter the amount which are multiples of 100: ");
	int value = keypad.getInput();
	while (condition) {
		if(value != 0) {
	if(value % 100 != 0) {
		screen.displayMessage("\nEnter the Valid amount which are multiples of 100: ");
		value = keypad.getInput();
	}
	else {
		condition = false;
	}
	
	}else {
		condition = false;
		value = 7;
		screen.displayMessage("\nReturned back ");
	}
		
	}
	return value;
}

}
