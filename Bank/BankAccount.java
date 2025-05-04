// AUTHOR: REHAN SHAFIQ
package Bank;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankAccount {

  // Data members of class
  private String name;
  private String account_no;
  private String acct_type;
  private int balance;
  private String password;

  public static void askForLoginAndSignup() {
    boolean isRunning = true;

    while (isRunning) {
      Utility.UserInterface.startUpScreenDisplay();

      int loginSignupOption = Utility.GetInputWithStyles.getInputForStartUpScreen();

      switch (loginSignupOption) {
        case 1:
          BankAccount loggedInAccount = loginAccount();
          if (loggedInAccount != null) {
            Utility.UserInterface.accountLoggedInSuccessfullyDisplay();
            handleAccountServices(loggedInAccount);
            isRunning = false; // Exit after success
          } else {
            Utility.UserInterface.accountNotFoundDisplay();
          }
          break;

        case 2:
          if (loginAsAdmin()) {
            handleAdminServices();
          }else{
            Utility.UserInterface.accountNotFoundDisplay();
          }
          
          break;
          
         case 3:
           BankAccount newAccount = create_Account();
           if (newAccount != null) {
             handleAccountServices(newAccount);
             isRunning = false; // Exit after success
           } else {
             System.out.println("Account creation failed. Please try again.");
           }
         break;

        default:
          Utility.UserInterface.enterValidActionDisplay();
          break;
      }
    }
  }

  // Method to create New Account
  public static BankAccount create_Account() {
    BankAccount account = new BankAccount();

    Utility.UserInterface.createNewAccountDisplay();

    // Getting user input for account details
    account.name = Utility.GetInputWithStyles.getFullNameInputForCreateAccount();
    account.password = Encryption.encryptPassword(Utility.GetInputWithStyles.getPasswordInputForCreateAccount());
    account.acct_type = Utility.GetInputWithStyles.getAccountTypeInputForCreateAccount();
    account.account_no = AccountNumberGenerator();
    account.balance = 0;
    char process = Utility.GetInputWithStyles.getInputForProcess();
    if ( process== 'y'|| process== 'Y') {
      Database.createAccountInDatabase(
          account.account_no, account.name, account.password, account.acct_type, account.balance);
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
    String password = Encryption.encryptPassword(Utility.GetInputWithStyles.getPasswordInputForLoginAccount());
    ResultSet rs = Database.loginToAccountFromDatabase(enteredName, password);
    try {
      if (rs.next()) {

        BankAccount account = new BankAccount();
        account.name = rs.getString("AccountHolder_Name");
        account.account_no = rs.getString("Account_No");
        account.acct_type = rs.getString("Account_Type");
        account.balance = rs.getInt("Balance");
        rs.close();
        return account;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
  public static Boolean loginAsAdmin() {
    Utility.UserInterface.loginAsAdminDisplay();
    String enteredName = Utility.GetInputWithStyles.getFullNameInputForLoginAccount();
    String enteredPassword = Utility.GetInputWithStyles.getPasswordInputForLoginAccount();
    Boolean loginAdmin = Database.loginAsAdmin(enteredName, enteredPassword);
    if (loginAdmin) {
      return true;
    }else{
      return false;
    }
  }

  // Method to start transactions
  public static void handleAccountServices(BankAccount account) {
    while (true) {
      Utility.UserInterface.bankServicesDisplay();
      int servicesOption = Utility.GetInputWithStyles.getBankServicesInput();

      switch (servicesOption) {
        case 1: // Deposit
          account.depositAmount(account);
          exitFromBank();
          break;

        case 2: // Withdraw
          account.withdraw(account);
          exitFromBank();
          break;

        case 3: // Account Details
          account.balance =
              Database.checkBalanceOfAccountFromDatabase(account.name, account.account_no);
          Utility.UserInterface.accountDetailsDisplay(
              account.name, account.account_no, account.balance);
          exitFromBank();
          break;

        case 4: // Send Money
          if (sendMoney(account)) {
            exitFromBank();
          } else {
            Utility.UserInterface.accountNotFoundDisplay();
          }
          break;

        case 5: // Print Transaction History
          Utility.UserInterface.printTransactionHistoryDisplay();
          ResultSet resultSet = Database.getTransactionHistory(account.account_no);
          if (resultSet != null) {
            GeneratePDF.printTransactionHistory(resultSet, account.account_no, account.name);
            Utility.UserInterface.printTransactionHistorySuccessfullyDisplay(account.account_no);
          }
          break;

        case 6: // Log into another account
          BankAccount loggedAccount = loginAccount();
          if (loggedAccount != null) {
            Utility.UserInterface.accountLoggedInSuccessfullyDisplay();
            account = loggedAccount; // update current session
          } else {
            Utility.UserInterface.accountLoginFailedDisplay();
          }
          break;

        case 7: // Exit
          Utility.UserInterface.exiting();
          return;

        default:
          Utility.UserInterface.enterValidActionDisplay();
          break;
      }
    }
  }

  // Method to deposit amount  in account
  public void depositAmount(BankAccount account) {
    String transactionType = "Deposit";
    String description = "Money deposited";
    Utility.UserInterface.depositAmountDisplay();
    int depositAmount = Utility.GetInputWithStyles.getDepositAmountInput();
    Database.depositToDatabase(depositAmount, account.account_no, account.name);
    Utility.UserInterface.depositAmountReceiptDisplay(
        account.name, account.account_no, depositAmount);
    Database.saveTransactionHistory(
        account.account_no, transactionType, depositAmount, description);
  }

  // METHOD TO WITHDRAW AMOUNT

  public void withdraw(BankAccount account) {
    String transactionType = "Withdraw";
    String description = "Money withdrawn";
    Utility.UserInterface.withdrawAmountDisplay();
    int withdrawAmount = Utility.GetInputWithStyles.getWithdrawAmountInput();
    if (withdrawAmount
        > Database.checkBalanceOfAccountFromDatabase(account.name, account.account_no)) {
      Utility.UserInterface.invalidWithdrawAmountDisplay();
    } else {
      Database.withdrawFromDatabase(withdrawAmount, account.account_no, account.name);
      Utility.UserInterface.withdrawAmountReceiptDisplay(
          account.name, account.account_no, withdrawAmount);
    }
    Database.saveTransactionHistory(
        account.account_no, transactionType, withdrawAmount, description);
  }

  public static boolean sendMoney(BankAccount account) {
    String transactionType = "SendMoney";
    String description = "Money sent";
    Utility.UserInterface.sendMoneyDisplay();
    String accountNo = Utility.GetInputWithStyles.getAccountNoInput();
    int sendAmount = Utility.GetInputWithStyles.getAmountInputForSendMoney();
    if (sendAmount < Database.checkBalanceOfAccountFromDatabase(account.name, account.account_no)) {
      String receiverName = Database.getNameOfAccountNoFromDatabase(accountNo);
      if (receiverName != null) {
        Utility.UserInterface.sendMoneyReceiptDisplay(receiverName, accountNo, sendAmount);
        char sendMoneyProcess = Utility.GetInputWithStyles.getInputForProcess();
        if (sendMoneyProcess == 'Y' || sendMoneyProcess == 'y') {
          Database.sendMoneyToDatabaseToOtherAccount(
              sendAmount, accountNo, receiverName, account.account_no, account.name);
          Utility.UserInterface.sentMoneySuccessfullyDisplay();
          Database.saveTransactionHistory(
              account.account_no, transactionType, sendAmount, accountNo, description);
          return true;
        } else {
          return false;
        }
      }
    } else {
      Utility.UserInterface.invalidWithdrawAmountDisplay();
      return false;
    }
    return false;
  }
  public static void handleAdminServices() {
    while (true) {
      Utility.UserInterface.adminServicesDisplay();
      int servicesOption = Utility.GetInputWithStyles.getAdminServicesInput();
      
      switch (servicesOption) {
        case 1:
          double totalBalance  = Database.getTotalBankBalance();//check balance
          Utility.UserInterface.totalBankBalanceDisplay(totalBalance);
          exitFromBank();
          break;
        
        case 2:
          Utility.UserInterface.printAllAccountsDisplay();
          ResultSet rs = Database.getAllAccountsFromDatabase();
          if(rs!= null){
            GeneratePDF.printAllAccounts(rs);
            Utility.UserInterface.printAllAccountsSuccessfullyDisplay();
            exitFromBank();
          }else{
            Utility.UserInterface.noAccountFoundDisplay();
            exitFromBank();
          }
          
          
          break;
        
        case 3:
          Utility.UserInterface.printTransactionHistoryDisplay();
          String account_no = Utility.GetInputWithStyles.getAccountNoInput();// print transaction history
          ResultSet resultSetOfTransactionHistory = Database.getTransactionHistory(account_no);
          if (resultSetOfTransactionHistory != null) {
            String name = Database.getNameOfAccountNoFromDatabase(account_no);
            GeneratePDF.printTransactionHistory(resultSetOfTransactionHistory,account_no,name);
            Utility.UserInterface.printTransactionHistorySuccessfullyDisplay(account_no);
            exitFromBank();
          }else{
            Utility.UserInterface.accountNotFoundDisplay();
          }
          break;
        
        case 4: // update account information
          Utility.UserInterface.updateAccountInformationDisplay();
          String accountNo = Utility.GetInputWithStyles.getAccountNoInput();
          Database.getNameOfAccountNoFromDatabase(accountNo);
          if(Database.getNameOfAccountNoFromDatabase(accountNo) != null){
            String name = Utility.GetInputWithStyles.getFullNameInputForUpdateAccountInformation();
            String password = Encryption.encryptPassword(Utility.GetInputWithStyles.getPasswordInputForUpdateAccountInformation());
            String accountType = Utility.GetInputWithStyles.getAccountTypeInputForUpdateAccountInformation();
            Boolean update = Database.updateAccountInformation(name, password,accountType,accountNo);
            if (update) {
              Utility.UserInterface.accountUpdatedSuccessfullyDisplay();
            }
          }else{
            Utility.UserInterface.accountNotFoundDisplay();
          }
          
          
          break;
        
        case 5:
          Utility.UserInterface.deleteAccountDisplay();// delete account
          String accountNO = Utility.GetInputWithStyles.getAccountNoInput();
          ResultSet resultSet = Database.getInformationForDeleteAccount(accountNO);
          if (resultSet != null) {
            try {
              String name = resultSet.getString("AccountHolder_Name");
              int balance = resultSet.getInt("Balance");
              Utility.UserInterface.deleteAccountReceiptDisplay(name, accountNO, balance);
              char process = Utility.GetInputWithStyles.getInputForProcess();
              if(process == 'Y' ||process == 'y'){
                Database.deleteAccountFromDatabase(accountNO);
                Utility.UserInterface.accountDeletedSuccessfullyDisplay();
              }else{
                break;
              }
            }catch (SQLException e){
              System.out.println(e.getMessage());
            }
          }else{
            Utility.UserInterface.accountNotFoundDisplay();
          }
          break;
        
        case 6: // Exit
          Utility.UserInterface.exiting();
          System.exit(0);
          break;
        
        default:
          Utility.UserInterface.enterValidActionDisplay();
          break;
      }
    }
  }

  public static void exitFromBank() {
    Utility.UserInterface.exitBank();
    char exit = Utility.GetInputWithStyles.getInputForExiting();
    if (exit == 'y' || exit == 'Y') {
      Utility.UserInterface.exiting();

      System.exit(0);
    }
  }
}
