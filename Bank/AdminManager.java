package Bank;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Bank.AccountManager.askToExitFromBank;
import static Bank.Encryption.encryptPassword;
import static Bank.GeneratePDF.printTransactionHistory;

public class AdminManager {
	public static Boolean loginAsAdmin() {
		Utility.UserInterface.loginAsAdminDisplay();
		String enteredName = Utility.GetInputWithStyles.getFullNameInputForLogIn();
		String enteredPassword = Utility.GetInputWithStyles.getPasswordInputForLogIn();
		return Database.loginAsAdmin(enteredName, enteredPassword);
	}
	public static void handleAdminServices() {
		while (true) {
			Utility.UserInterface.adminServicesDisplay();
			int servicesOption = Utility.GetInputWithStyles.getAdminServicesInput();
			
			switch (servicesOption) {
				case 1:
					double totalBalance  = Database.getTotalBankBalance();//check accountBalance
					Utility.UserInterface.totalBankBalanceDisplay(totalBalance);
					askToExitFromBank();
					break;
				
				case 2:
					Utility.UserInterface.printAllAccountsDisplay();
					ResultSet rs = Database.getAllAccountsFromDatabase();
					if(rs!= null){
						GeneratePDF.printAllAccounts(rs);
						Utility.UserInterface.printAllAccountsSuccessfullyDisplay();
						askToExitFromBank();
					}else{
						Utility.UserInterface.noAccountFoundDisplay();
						askToExitFromBank();
					}
					
					
					break;
				
				case 3:
					Utility.UserInterface.printTransactionHistoryDisplay();
					String account_no = Utility.GetInputWithStyles.getAccountNoInput();// print transaction history
					ResultSet resultSetOfTransactionHistory = Database.getTransactionHistory(account_no);
					if (resultSetOfTransactionHistory != null) {
						String name = Database.getNameOfAccountNoFromDatabase(account_no);
						printTransactionHistory(resultSetOfTransactionHistory,account_no,name);
						Utility.UserInterface.printTransactionHistorySuccessfullyDisplay(account_no);
						askToExitFromBank();
					}else{
						Utility.UserInterface.accountNotFoundErrorDisplay();
					}
					break;
				
				case 4: // update account information
					Utility.UserInterface.updateAccountInformationDisplay();
					String accountNo = Utility.GetInputWithStyles.getAccountNoInput();
					Database.getNameOfAccountNoFromDatabase(accountNo);
					if(Database.getNameOfAccountNoFromDatabase(accountNo) != null){
						String name = Utility.GetInputWithStyles.getFullNameInputForUpdateAccountInformation();
						String password = encryptPassword(Utility.GetInputWithStyles.getPasswordInputForUpdateAccountInformation());
						String accountType = Utility.GetInputWithStyles.getAccountTypeInputForUpdateAccountInformation();
						Boolean update = Database.updateAccountInformation(name, password,accountType,accountNo);
						if (update) {
							Utility.UserInterface.accountUpdatedSuccessfullyDisplay();
						}
					}else{
						Utility.UserInterface.accountNotFoundErrorDisplay();
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
						Utility.UserInterface.accountNotFoundErrorDisplay();
					}
					break;
				
				case 6: // Exit
					Utility.UserInterface.exitingDisplay();
					System.exit(0);
					break;
				
				default:
					Utility.UserInterface.enterValidActionErrorDisplay();
					break;
			}
		}
	}
}
