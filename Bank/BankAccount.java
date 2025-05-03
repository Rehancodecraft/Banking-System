// AUTHOR: REHAN SHAFIQ
package Bank;



import java.sql.*;
import java.util.*;

public class BankAccount {

 

  // Data members of class
  private String name;
  private String account_no;
  private int acct_type;
  private int balance;
  private String password;
  
  
  public static void askForLoginAndSignup() {

    Utility.UserInterface.startUpScreenDisplay();
    
    int loginSignupOption = Utility.GetInputWithStyles.getInputForStartUpScreen();

    switch (loginSignupOption) {
      case 1:
        BankAccount loggedInAccount = loginAccount();
        if (loggedInAccount != null) {
          Utility.UserInterface.accountLoggedInSuccessfullyDisplay();
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
        if (account != null) {
          account.handleAccountServices(account);
        }
        else{
          askForLoginAndSignup();
        }
        break;
    }
  }

  // Method to create New Account
  public static BankAccount create_Account() {
    BankAccount account = new BankAccount();
    
    Utility.UserInterface.createNewAccountDisplay();
    
    // Getting user input for account details
    account.name = Utility.GetInputWithStyles.getFullNameInputForCreateAccount();
    account.password = Utility.GetInputWithStyles.getPasswordInputForCreateAccount();
    account.acct_type = Utility.GetInputWithStyles.getAccountTypeInputForCreateAccount();
    account.account_no = AccountNumberGenerator();
    account.balance = 0;
    
    if (Utility.GetInputWithStyles.getInputForProcessCreateAccount() == 'y') {
      Database.createAccountInDatabase(account.account_no,account.name,account.password,account.acct_type,account.balance);
      return account;
    }
    
    
    return null;
  }
  
  
  
  // Method to generate a random account number
  private static String AccountNumberGenerator() {
    String accountNo = ""; // Initialize to avoid compiler error
    Random rand = new Random();
    accountNo = "ACC" + (100000 + rand.nextInt(900000));
    return accountNo;
  }
  
  
  // Method to login into existing account
  public static BankAccount loginAccount() {
    Utility.UserInterface.loginAccountDisplay();
    String enteredName = Utility.GetInputWithStyles.getFullNameInputForLoginAccount();
    String enteredPassword = Utility.GetInputWithStyles.getPasswordInputForLoginAccount();
    ResultSet rs = Database.loginToAccountFromDatabase(enteredName, enteredPassword);
    try {
      if (rs.next()) {
        
        BankAccount account = new BankAccount();
        account.name = rs.getString("AccountHolder_Name");
        account.account_no = rs.getString("Account_No");
        account.acct_type = rs.getInt("Account_Type");
        account.balance = rs.getInt("Balance");
        rs.close();
        return account;
      }
    }
    catch (SQLException e){
      System.out.println(e.getMessage());
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
          account.depositAmount(account);
          exitFromBank();
          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          break;
          // case of withdraw
        case 2:
          account.withdraw(account);
          exitFromBank();
          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          break;
          // case of display account details
        case 3:
          account.balance = Database.checkBalanceOfAccountFromDatabase(account.name,account.account_no);
          Utility.UserInterface.accountDetailsDisplay(
              account.name, account.account_no, account.balance);
          exitFromBank();
          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          break;
          // case of send money
        case 4:
          if(sendMoney(account)) {
            exitFromBank(); // Optional: display message or pause
            Utility.UserInterface.bankServicesDisplay();
            servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
            break;
          }else {
            handleAccountServices(account);
          }
          break;
        
        // case of login into account
        case 5:
          BankAccount loggedAccount = loginAccount();
          if (loggedAccount != null) {
            Utility.UserInterface.accountLoggedInSuccessfullyDisplay();
            account.handleAccountServices(loggedAccount);
          }
          else {
            Utility.UserInterface.accountLoginFailedDisplay();
            account.handleAccountServices(account);
          }
          break;
          
        default:
          Utility.UserInterface.enterValidActionDisplay();

          Utility.UserInterface.bankServicesDisplay();
          servicesOption = Utility.GetInputWithStyles.getBankServicesInput();
          continue;
      }
    }
    Utility.UserInterface.exiting();
  }

  // Method to deposit amount  in account
  public void depositAmount(BankAccount account) {
    Utility.UserInterface.depositAmountDisplay();
    int depositAmount = Utility.GetInputWithStyles.getDepositAmountInput();
    Database.depositToDatabase(depositAmount,account.account_no,account.name);
    Utility.UserInterface.depositAmountReceiptDisplay(account.name, account.account_no, depositAmount);
  }
  
  // METHOD TO WITHDRAW AMOUNT

  public void withdraw(BankAccount account) {
    Utility.UserInterface.withdrawAmountDisplay();
    int withdrawAmount = Utility.GetInputWithStyles.getWithdrawAmountInput();
    if (withdrawAmount > Database.checkBalanceOfAccountFromDatabase(account.name,account.account_no)) {
      Utility.UserInterface.invalidWithdrawAmountDisplay();
    } else {
      Database.withdrawFromDatabase(withdrawAmount,account.account_no,account.name);
      Utility.UserInterface.withdrawAmountReceiptDisplay(account.name, account.account_no, withdrawAmount);
    }
  }
  public static boolean sendMoney(BankAccount account) {
    Utility.UserInterface.sendMoneyDisplay();
    String accountNo = Utility.GetInputWithStyles.getAccountNoInputForSendMoney();
    int sendAmount = Utility.GetInputWithStyles.getAmountInputForSendMoney();
    if(sendAmount < Database.checkBalanceOfAccountFromDatabase(account.name,account.account_no)) {
      String receiverName = Database.getNameOfReceiverFromDatabase(accountNo);
      if (receiverName != null) {
        Utility.UserInterface.sendMoneyReceiptDisplay(receiverName, accountNo, sendAmount);
        char sendMoneyProcess = Utility.GetInputWithStyles.getInputForProcessSendMoney();
        if (sendMoneyProcess == 'Y' || sendMoneyProcess == 'y') {
          Database.sendMoneyToDatabaseToOtherAccount(sendAmount, accountNo, receiverName, account.account_no, account.name);
          Utility.UserInterface.sentMoneySuccessfullyDisplay();
          return true;
        } else {
          return false;
        }
      } else {
        Utility.UserInterface.accountNotFoundDisplay();
        
      }
    }else{
      Utility.UserInterface.invalidWithdrawAmountDisplay();
      return false;
    }
    return false;
  }
  public static void exitFromBank() {
    Utility.UserInterface.exitBank();
    char exit = Utility.GetInputWithStyles.getInputForExiting();
    if (exit == 'y'||exit == 'Y') {
      Utility.UserInterface.exiting();
      
      System.exit(0);
    }
    
  }
}
