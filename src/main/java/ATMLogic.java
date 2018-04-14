package main.java;

/**
 * @author 
 * This class loads the initials user details and executes the transactions based on the user request 
 */
public class ATMLogic {

  private boolean userAuthenticated; // whether user is authenticated
  private int currentAccountNumber; // current user's account number
  private Display screen; // ATM's Display
  private InputKey keypad; // ATM's Input keys
  private CashWithdraw cashDispenser; // ATM's cash withdraw
  private DepositSlot depositSlot; // ATM's deposit slot
  private UserDetails bankDatabase; // user details

  private static final int BALANCE_INQUIRY = 1;
  private static final int WITHDRAWAL = 2;
  private static final int DEPOSIT = 3;
  private static final int EXIT = 4;
private static final int SAVINGS = 1;
private static final int CURRENT = 2;

  public ATMLogic() {
    userAuthenticated = false; // user is not authenticated to start
    currentAccountNumber = 0; // no current account number to start
    screen = new Display(); // create screen
    keypad = new InputKey(); // create keypad
    cashDispenser = new CashWithdraw(); // create cash dispenser
    depositSlot = new DepositSlot(); // create deposit slot
    bankDatabase = new UserDetails("Savings"); // create user details from the user details class
  }

  public void run() {
    while (!userAuthenticated) {
      screen.displayMessageLine("\nWelcome to Test Bank ATM!");
      authenticateUser();
    }
    performTransactions(); 
    userAuthenticated = false; // reset before next ATM session
    currentAccountNumber = 0; // reset before next ATM session
    screen.displayMessageLine("\nThank you for using Test Bank!");

  }

  // Authentication based on the user details
  private void authenticateUser() {
    screen.displayMessage("\nPlease enter your account number: ");
    int accountNumber = keypad.getInput(); // input account number
    screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
    int pin = keypad.getInput(); // input PIN

    // return boolean value for the validation of account and pin
    userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
    if (userAuthenticated) {
      currentAccountNumber = accountNumber;
    } else {
      screen.displayMessageLine("Invalid Credentials. Please try again.");
    }
  }

  // display the main menu and perform transactions
  private void performTransactions() {

    // local variable to store transaction currently being processed
	  Transaction currentTransaction;
    
    boolean accountType = false;
    boolean userExited = false; // user has not chosen to exit
    
    while (!accountType) {
    	//Select the type of account
    int accountTypeSelection = displayAccountType();
	
	switch (accountTypeSelection) {
    case SAVINGS:
    	bankDatabase = new UserDetails("SAVINGS");
    	userExited = false;
    	break;
    case CURRENT:
    	bankDatabase = new UserDetails("CURRENT");
    	  userExited = false;
    	  break;
    default: // user did not enter an integer 1 or 2
      screen.displayMessageLine("\nYou did not enter a valid selection! Please try again.");
      break;
  }	

    // loop while user has not chosen option to exit system
    while (!userExited) {
    	
      // show main menu and get user selection
      int mainMenuSelection = displayMainMenu();
      // decide how to proceed based on user's menu selection
      switch (mainMenuSelection) {
        // user choose to perform one of three transaction types
        case BALANCE_INQUIRY:
        case WITHDRAWAL:
        case DEPOSIT: // initialize as new object of chosen type
          currentTransaction = createTransaction(mainMenuSelection);
          currentTransaction.execute(); //executes now
          break;
        case EXIT: // user choose to terminate session
          screen.displayMessageLine("\nExiting the system..!");
          userExited = true; // this ATM session should end - confirmation for while loop
          accountType=true; // this ATM session should end - confirmation for while loop
          break;
        default: //input did not match
          screen.displayMessageLine("\nYou did not enter a valid input! Please try again.");
          break;
      }
    }
    }
  }

//display the type of accounts
private int displayAccountType() {
		screen.displayMessageLine("\n[Account menu]");
		screen.displayMessageLine("1 - Savings Account");
	    screen.displayMessageLine("2 - Current Account\n");
	    screen.displayMessage("Enter a choice: ");
	    return keypad.getInput(); // return user's selection
	  
}

// display the main transactions
  private int displayMainMenu() {
    screen.displayMessageLine("\n[Transaction menu]");
    screen.displayMessageLine("1 - View my balance");
    screen.displayMessageLine("2 - Withdraw cash");
    screen.displayMessageLine("3 - Deposit funds");
    screen.displayMessageLine("4 - Exit\n");
    screen.displayMessage("Enter a choice: ");
    return keypad.getInput(); // return user's selection
  }

  private Transaction createTransaction(int type) {
    Transaction temp = null;
    // based on the type of request below will be executed
    switch (type) {
      case BALANCE_INQUIRY: // create new BalanceInquiry transaction
        temp = new BalanceInquiryTransaction(currentAccountNumber, screen, bankDatabase);
        break;
      case WITHDRAWAL: // create new Withdrawal transaction
        temp = new Withdraw(currentAccountNumber, screen,
                              bankDatabase, keypad, cashDispenser);
        break;
      case DEPOSIT: // create new Deposit transaction
        temp = new Deposit(currentAccountNumber, screen,
                           bankDatabase, keypad, depositSlot);
        break;
    }
    return temp; // returns the temporary stored result
  }

}

