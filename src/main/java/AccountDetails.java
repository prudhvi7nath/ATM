package main.java;

/**
 * @author 
 *  This class validates the ATM pin and returns the bank transactions details
 */
public class AccountDetails {

  private int accountNumber; // account number
  private int pin; // ATM PIN 
  private double availableBalance; // funds available for withdrawal
  private double totalBalance; // funds available + pending deposits

  // Account constructor initializes attributes
  public AccountDetails(int theAccountNumber, int thePIN,
                 double theAvailableBalance, double theTotalBalance) {
    accountNumber = theAccountNumber;
    pin = thePIN;
    availableBalance = theAvailableBalance;
    totalBalance = theTotalBalance;
  }

  // determines whether a user-specified PIN matches PIN in Account
  public boolean validatePIN(int userPIN) {
    return userPIN == pin ? true : false;
  }

  // returns available balance
  public double getAvailableBalance() {
    return availableBalance;
  }

  // returns the total balance
  public double getTotalBalance() {
    return totalBalance;
  }

  // credits an amount to the account
  public void credit(double amount) {
	  
    totalBalance += amount;
    availableBalance=totalBalance;
  }

  // debits an amount from the account
  public void debit(double amount) {
    availableBalance -= amount;
    totalBalance -= amount;
  }

  // returns account number
  public int getAccountNumber() {
    return accountNumber;
  }

}
