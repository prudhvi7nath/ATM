package main.java;

/**
 * @author 
 * This class is about the balance enquiry details
 */
public class BalanceInquiryTransaction extends Transaction {

  public BalanceInquiryTransaction(int userAccountNumber, Display atmScreen, UserDetails atmBankDatabase) {
    super(userAccountNumber, atmScreen, atmBankDatabase);
  }

  // performs the transaction when it is balance check transaction
  public void execute() {
    UserDetails bankDatabase = getBankDatabase();
    Display screen = getScreen();
    // returns the available balance
    double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
    // returns the total balance
    double totalBalance = bankDatabase.getAvailableBalance(getAccountNumber());
    // display the balance information on the screen
    screen.displayMessageLine("\nBalance Information:");
    screen.displayMessage("Available balance: ");
    screen.displayAmount(availableBalance);
    screen.displayMessage("\nTotal balance: ");
    screen.displayAmount(totalBalance);
    screen.displayMessageLine("");
  }

}
