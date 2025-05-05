// AUTHOR: REHAN SHAFIQ
package Bank;

import java.sql.*;
import java.util.*;

import static Bank.Database.*;
import static Bank.Encryption.encryptPassword;
import static Bank.GeneratePDF.*;
import static Bank.Utility.GetInputWithStyles.*;
import static Bank.Utility.UserInterface.*;

public class AccountManager {

  //VARIABLES TO USER'S INFORMATION
  private String accountHolderName;
  private String accountNo;
  private String accountType;
  private double accountBalance;
  private String accountPassword;
  
  // LOGIN AS USER INTO EXISTING ACCOUNT
  public static AccountManager logInAsUser() {
    logInAsUserDisplay();
    //GETTING DETAILS TO LOG IN TO EXISTING ACCOUNT AS USER
    String enteredName = getFullNameInputForLogIn();
    String encryptedPassword = encryptPassword(getPasswordInputForLogIn());
    ResultSet loggedInAccount = logInToAccountFromDatabase(enteredName, encryptedPassword);
    try {
      if (loggedInAccount!=null) {
        
        AccountManager account = new AccountManager();
        account.accountHolderName = loggedInAccount.getString("AccountHolder_Name");
        account.accountNo = loggedInAccount.getString("Account_No");
        account.accountType = loggedInAccount.getString("Account_Type");
        account.accountBalance = loggedInAccount.getInt("Balance");
        loggedInAccount.close();
        return account;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  
  // CREATING NEW ACCOUNT IN BANK
  public static AccountManager createNewAccount() {
    AccountManager account = new AccountManager();

    createNewAccountDisplay();

    // GETTING DETAILS OF USER TO CREAT A NEW ACCOUNT
    account.accountHolderName = getFullNameInputForCreateNewAccount();
    account.accountPassword = Utility.GetInputWithStyles.getPasswordInputForCreateNewAccount();
    account.accountType = getAccountTypeInputForCreateNewAccount();
    account.accountNo = accountNumberGenerator();
    account.accountBalance = 0;
    char confirmation = getProceedConfrimationInput();
    if ( confirmation== 'y'|| confirmation== 'Y') {
          saveNewAccountInDatabase(account.accountNo, account.accountHolderName, account.accountPassword, account.accountType, account.accountBalance);
      return account;
    }
    return null;
  }
  // USING BANK SERVICES
  public static void manageUserAccountServices(AccountManager account) {
    while (true) {
      userAccountServicesDisplay();
      int userServicesOptionChosen = getUserAccountServicesInput();

      switch (userServicesOptionChosen) {
        //OPTION 1: CASE OF DEPOSIT MONEY
        case 1:
          account.depositMoney(account);
          askToExitFromBank();
          break;
        //OPTION 2: CASE OF WITHDRAW MONEY
        case 2:
          withdrawMoney(account);
          askToExitFromBank();
          break;
        //OPTION 4: SEND MONEY TO OTHER ACCOUNTS
        case 3:
          if (sendMoney(account)) {
            askToExitFromBank();
          } else {
            accountNotFoundErrorDisplay();
          }
        
          //OPTION 3: CHECK ACCOUNT BALANCE
        case 4:
          account.accountBalance = checkBalanceOfAccountFromDatabase(account.accountHolderName, account.accountNo);
          accountBalanceDisplay(account.accountHolderName, account.accountNo, account.accountBalance);
          askToExitFromBank();
          break;
        //OPTION 5: PRINT COMPLETE TRANSACTION HISTORY
        case 5:
          printTransactionHistoryDisplay();
          ResultSet transactionHistory = Database.getTransactionHistory(account.accountNo);
          if (transactionHistory != null) {
            printTransactionHistory(transactionHistory, account.accountNo, account.accountHolderName);
            printTransactionHistorySuccessfullyDisplay(account.accountNo);
          }
          break;
        //OPTION 6: LOGIN TO AN OTHER ACCOUNT
        case 6:
          AccountManager loggedInAccount = logInAsUser();
          if (loggedInAccount != null) {
            accountLoggedInSuccessfullyDisplay();
            account = loggedInAccount;
          } else {
            accountLoginFailedErrorDisplay();
          }
          break;
        //OPTION 7: CASE TO EXIT FROM BANK
        case 7:
          exitingDisplay();
          return;
        default:
          enterValidActionErrorDisplay();
          break;
      }
    }
  }

  // Deposit Money in Account
  public void depositMoney(AccountManager account) {
    String transactionType = "Deposit";
    String description = "Money deposited";
    depositMoneyDisplay();
    int depositAmount = getDepositMoneyInput();
    saveDepositToDatabase(depositAmount, account.accountNo, account.accountHolderName);
    saveTransactionHistory(account.accountNo, transactionType, depositAmount, description);
    depositMoneyReceiptDisplay(account.accountHolderName, account.accountNo, depositAmount);
  }
  
  // WITHDRAW MONEY FROM ACCOUNT
  public static void withdrawMoney(AccountManager account) {
    String transactionType = "Withdraw";
    String description = "Money withdrawn";
    withdrawMoneyDisplay();
    int withdrawAmount = getWithdrawAmountInput();
    if (withdrawAmount > checkBalanceOfAccountFromDatabase(account.accountHolderName, account.accountNo)) {
      invalidWithdrawAmountErrorDisplay();
    } else {
      saveWithdrawInDatabase(withdrawAmount, account.accountNo, account.accountHolderName);
      saveTransactionHistory(account.accountNo, transactionType, withdrawAmount, description);
    }
      withdrawMoneyReceiptDisplay(account.accountHolderName, account.accountNo, withdrawAmount);
    }
  //SEND MONEY TO OTHER ACCOUNTS
  public static boolean sendMoney(AccountManager account) {
    String transactionType = "SendMoney";
    String description = "Money sent";
    sendMoneyDisplay();
    String accountNo = Utility.GetInputWithStyles.getAccountNoInput();
    int sendAmount = Utility.GetInputWithStyles.getAmountInputForSendMoney();
    if (sendAmount < Database.checkBalanceOfAccountFromDatabase(account.accountHolderName, account.accountNo)) {
      String receiverName = Database.getNameOfAccountNoFromDatabase(accountNo);
      if (receiverName != null) {
        Utility.UserInterface.sendMoneyReceiptDisplay(receiverName, accountNo, sendAmount);
        char sendMoneyProcess = Utility.GetInputWithStyles.getProceedConfrimationInput();
        if (sendMoneyProcess == 'Y' || sendMoneyProcess == 'y') {
          Database.sendMoneyToDatabaseToOtherAccount(
              sendAmount, accountNo, receiverName, account.accountNo, account.accountHolderName);
          Utility.UserInterface.sentMoneySuccessfullyDisplay();
          Database.saveTransactionHistory(
              account.accountNo, transactionType, sendAmount, accountNo, description);
          return true;
        } else {
          return false;
        }
      }
    } else {
      Utility.UserInterface.invalidWithdrawAmountErrorDisplay();
      return false;
    }
    return false;
  }
  
  
  // ASK IF USER WANT TO EXIT
  public static void askToExitFromBank() {
    Utility.UserInterface.exitBank();
    char exit = Utility.GetInputWithStyles.getInputForExiting();
    if (exit == 'y' || exit == 'Y') {
      Utility.UserInterface.exitingDisplay();
      
      System.exit(0);
    }
  }
  
  //
  private static String accountNumberGenerator() {
    String accountNo = ""; // Initialize to avoid compiler error
    Random rand = new Random();
    accountNo = "ACC" + (100000 + rand.nextInt(900000));
    return accountNo;
  }
}
