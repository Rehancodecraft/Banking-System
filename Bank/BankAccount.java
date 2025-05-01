// AUTHOR: REHAN SHAFIQ
package Bank;

import java.util.*;

public class BankAccount {

  // ArrayList that stores all the accounts
  private static ArrayList<BankAccount> accounts = new ArrayList<>();

  // Data members of class
  private String name;
  private String account_no;
  private int acct_type;
  private int balance;
  private String password;

  // Colors

  // Method which runs at start
  public static void askForLoginAndSignup() {

    Utility.UserInterface.startUpScreenDisplay();
    int loginSignupOption = Utility.GetInputWithStyles.getInputForStartUpScreen();

    switch (loginSignupOption) {
      case 1:
        BankAccount loggedInAccount = loginAccount();
        if (loggedInAccount != null) {
          loggedInAccount.handleAccountServices(loggedInAccount);
          return;
        } else {
          //
          Utility.UserInterface.accountNotFoundDisplay();
          askForLoginAndSignup();
        }
        break;

      case 2:
        BankAccount account = create_Account();
        account.handleAccountServices(account);
        break;
    }
  }

  // Method to create New Account
  public static BankAccount create_Account() {

    BankAccount account = new BankAccount();

    Utility.UserInterface.createNewAccountDisplay();

    account.name = Utility.GetInputWithStyles.getFullNameInputForCreateAccount();
    account.password = Utility.GetInputWithStyles.getPasswordInputForCreateAccount();
    account.acct_type = Utility.GetInputWithStyles.getAccountTypeInputForCreateAccount();
    account.account_no = AccountNumberGenerator();
    accounts.add(account);

    Utility.UserInterface.accountCreatedSuccessfullyDisplay(account.account_no);

    return account;
  }

  // Method to generate a random account number
  private static String AccountNumberGenerator() {
    return "ACC" + UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 6);
  }

  // Method to login into existing account
  public static BankAccount loginAccount() {
    Utility.UserInterface.loginAccountDisplay();
    String enteredName = Utility.GetInputWithStyles.getFullNameInputForLoginAccount();

    String enteredPassword = Utility.GetInputWithStyles.getPasswordInputForLoginAccount();

    // checking throug the accounts if anyone matches
    for (BankAccount account : accounts) {
      if (account.name.equals(enteredName) && account.password.equals(enteredPassword)) {
        Utility.UserInterface.accountLoggedInSuccessfullyDisplay();
        return account;
      }
    }
    return null;
  }

  // Method to start transactions
  public void handleAccountServices(BankAccount account) {
    Utility.UserInterface.bankServicesDisplay();
    int servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
    while (servicesOption != 6) {
      switch (servicesOption) {
          // Case of deposit
        case 1:
          account.depositAmmount(account);
          exitBank();
          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          break;
          // case of withdraw
        case 2:
          account.balance = account.withdraw(account);
          exitBank();
          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          break;
          // case of display account details
        case 3:
          Utility.UserInterface.accountDetailsDisplay(
              account.name, account.account_no, account.balance);
          exitBank();
          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          break;
          // case of creating a new account
        case 4:
          BankAccount account2 = account.create_Account();
          account2.handleAccountServices(account2);
          return;
          // case of login into account
        case 5:
          BankAccount account1 = loginAccount();
          if (account1 != null) {
            account1.handleAccountServices(account1);
          } else {
            Utility.UserInterface.accountLoginFailedDisplay();
            handleAccountServices(account);
          }
          break;
        default:
          Utility.UserInterface.enterValidActionDisplay();

          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          continue;
      }
    }
    exitBank();
  }

  // Method to deposit amount  in account
  public void depositAmmount(BankAccount account) {
    Utility.UserInterface.depositAmountDisplay();
    int depositAmount = Utility.GetInputWithStyles.getDepositAmountInput();

    //                           Enter Deposit amount: " + reset);
    if (depositAmount <= 0) {
      Utility.UserInterface.invalidDepositAmountDisplay();
      account.depositAmmount(account);

    } else {
      account.balance += depositAmount;
      Utility.UserInterface.depositAmountReceiptDisplay(
          account.name, account.account_no, depositAmount);
    }
  }

  // METHOD TO WITHDRAW AMOUNT

  public int withdraw(BankAccount account) {
    Utility.UserInterface.withdrawAmountDisplay();

    int withdrawAmount = Utility.GetInputWithStyles.getWithdrawAmountInput();

    //
    if (withdrawAmount > account.balance || withdrawAmount <= 0) {
      Utility.UserInterface.invalidWithdrawAmountDisplay();

      //

    } else {
      int remaining_balance = account.balance - withdrawAmount;
      //      account.balance += amount;
      Utility.UserInterface.depositAmountReceiptDisplay(
          account.name, account.account_no, withdrawAmount);
      return remaining_balance;
    }
    return account.balance;
  }

  // METHOD TO EXIT FROM PROGRAM
  // asking exit from bank
  public boolean exitBank() {
    Utility.UserInterface.exitBank();
    char exit = Utility.GetInputWithStyles.getInputForExiting();
    if (exit == 'y') {
      Utility.UserInterface.exiting();
      return true;
    }
    return false;
  }
}
