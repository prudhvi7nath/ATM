
package main.java;

/**
 * @author pvobbilireddy
 *
 */
public abstract class Transaction {

  private int accountNumber; // indicates account involved
  private Display screen; // ATM's screen
  private UserDetails bankDatabase; // account info database

  // Transaction constructor invoked by subclasses using super()
  public Transaction(int userAccountNumber, Display atmScreen,
                     UserDetails atmBankDatabase) {
    accountNumber = userAccountNumber;
    screen = atmScreen;
    bankDatabase = atmBankDatabase;
  }

  // return account number
  public int getAccountNumber() {
    return accountNumber;
  }

  // return reference to screen
  public Display getScreen() {
    return screen;
  }

  // return reference to bank database
  public UserDetails getBankDatabase() {
    return bankDatabase;
  }

  // perform the transaction (overridden by each subclass)
  abstract public void execute();

}
