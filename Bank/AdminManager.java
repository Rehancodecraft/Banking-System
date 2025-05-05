package Bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Bank.AccountManager.*;
import static Bank.Database.*;
import static Bank.Encryption.encryptPassword;
import static Bank.GeneratePDF.*;
import static Bank.Utility.GetInputWithStyles.*;
import static Bank.Utility.UserInterface.*;

public class AdminManager {
	public static Boolean loginAsAdmin() {
		loginAsAdminDisplay();
		String enteredName = Utility.GetInputWithStyles.getFullNameInputForLogIn();
		String enteredPassword = Utility.GetInputWithStyles.getPasswordInputForLogIn();
		return Database.loginAsAdmin(enteredName, enteredPassword);
	}
	public static void handleAdminServices() {
		while (true) {
			Utility.UserInterface.adminServicesDisplay();
			int servicesOption = Utility.GetInputWithStyles.getAdminServicesInput();
			
			switch (servicesOption) {
				
				//OPTION 1: CHECK TOTAL BANK BALANCE
				case 1:
					double totalBankBalance  = getTotalBankBalance();//check accountBalance
					totalBankBalanceDisplay(totalBankBalance);
					askToExitFromBank();
					break;
				//OPTION 1: PRINT ALL ACCOUNTS
				case 2:
					printAllAccountsDisplay();
					ResultSet allAccounts = getAllAccountsFromDatabase();
					if(allAccounts!= null){
						printAllAccounts(allAccounts);
						printAllAccountsSuccessfullyDisplay();
						askToExitFromBank();
					}else{
						noAccountFoundErrorDisplay();
						askToExitFromBank();
					}
					
					
					break;
				//OPTION 1: PRINT TRANSACTION HISTORY
				case 3:
					Utility.UserInterface.printTransactionHistoryDisplay();
					String account_no = getAccountNoInput();
					ResultSet resultSetOfTransactionHistory = getTransactionHistory(account_no);
					if (resultSetOfTransactionHistory != null) {
						String name = Database.getNameOfAccountNoFromDatabase(account_no);
						printTransactionHistory(resultSetOfTransactionHistory,account_no,name);
						Utility.UserInterface.printTransactionHistorySuccessfullyDisplay(account_no);
						askToExitFromBank();
					}else{
						Utility.UserInterface.accountNotFoundErrorDisplay();
					}
					break;
				//OPTION 1: UPDATE ACCOUNT INFORMATION
				case 4:
					updateAccountInformationDisplay();
					String accountNo = getAccountNoInput();
					if(getNameOfAccountNoFromDatabase(accountNo) != null){
						String updatedName = getFullNameInputForUpdateAccountInformation();
						String updatedEncryptedPassword = encryptPassword(getPasswordInputForUpdateAccountInformation());
						String updatedAccountType = getAccountTypeInputForUpdateAccountInformation();
						Boolean isUpdated = updateAccountInformation(updatedName, updatedEncryptedPassword,updatedAccountType,accountNo);
						if (isUpdated) {
							accountUpdatedSuccessfullyDisplay();
						}
					}else{
						accountNotFoundErrorDisplay();
					}
					
					
					break;
				
				case 5:
					deleteAccountDisplay();// delete account
					String accountNO = getAccountNoInput();
					ResultSet detailsOfAccountToDelete = getInformationForDeleteAccount(accountNO);
					if (detailsOfAccountToDelete != null) {
						try {
							String name = detailsOfAccountToDelete.getString("AccountHolder_Name");
							int balance = detailsOfAccountToDelete.getInt("Balance");
							Utility.UserInterface.deleteAccountReceiptDisplay(name, accountNO, balance);
							char process = Utility.GetInputWithStyles.getProceedConfrimationInput();
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
						accountNotFoundErrorDisplay();
					}
					break;
				
				case 6: // Exit
					exitingDisplay();
					System.exit(0);
					break;
				
				default:
					enterValidActionErrorDisplay();
					break;
			}
		}
	}
}
