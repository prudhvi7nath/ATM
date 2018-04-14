package main.java;

public class UserDetails {

  private AccountDetails accounts[]; // array of Accounts

  public UserDetails(String accountType) {
	    accounts = new AccountDetails[2]; // just 2 accounts for testing
	    if (accountType.equalsIgnoreCase("Savings")) {
	    accounts[0] = new AccountDetails(12345, 4321, 1000.0, 1000.0);
	    accounts[1] = new AccountDetails(56789, 9876, 2000.0, 2000.0);
	  }else {
		  accounts[0] = new AccountDetails(12345, 4321, 3000.0, 3000.0);
		  accounts[1] = new AccountDetails(56789, 9876, 4000.0, 4000.0);
	  }
  }
  // retrieve Account object containing specified account number
  private AccountDetails getAccount(int accountNumber) {
    // loop through accounts searching for matching account number
    for (AccountDetails currentAccount : accounts) {
      // return current account if match found
      if (currentAccount.getAccountNumber() == accountNumber) {
        return currentAccount;
      }
    }
    return null; // if no matching account was found
  }

  // determine whether user-specified account number and PIN match
  // those of an account in the database
  public boolean authenticateUser(int userAccountNumber, int userPIN) {
    // attempt to retrieve the account with the account number
    AccountDetails userAccount = getAccount(userAccountNumber);
    // if account exists, return result of Account method validatePIN
    return userAccount != null ? userAccount.validatePIN(userPIN) : false;
  }

  // return available balance of Account with specified account number
  public double getAvailableBalance(int userAccountNumber) {
    return getAccount(userAccountNumber).getAvailableBalance();
  }

  // credit an amount to Account with specified account number
  public void credit(int userAccountNumber, double amount) {
    getAccount(userAccountNumber).credit(amount);
  }

  // debit an amount from of Account with specified account number
  public void debit(int userAccountNumber, double amount) {
    getAccount(userAccountNumber).debit(amount);
  }

}
