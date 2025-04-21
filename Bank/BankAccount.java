//AUTHOR: REHAN SHAFIQ
package Bank;

import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.System.exit;

public class BankAccount {
	private static ArrayList<BankAccount> accounts = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	
	private String name;
	private String account_no;
	private int acct_type;
	private int balance;
	private String password;
	
	String bold = "\033[1m";      
	String red = "\033[31m";      // Red Text
	String green = "\033[32m";
	String blue = "\033[34m";
	String yellow = "\u001B[93m";   // Yellow Text
	String reset = "\033[0m";
	String cyan = "\033[36m";
	
	
	public static void run_machine() {
		String bold = "\033[1m";      // Bold Text
		String red = "\033[31m";      // Red Text
		String green = "\033[32m";
		String blue = "\033[34m";
		String yellow = "\u001B[93m";   // Yellow Text
		String reset = "\033[0m";
		String cyan = "\033[36m";
		String banner = """
                 __      __         .__                                     ___________         __________                  __
                /  \\    /  \\  ____  |  |    ____    ____    _____    ____   \\__    ___/  ____   \\______   \\_____     ____  |  | __
                \\   \\/\\/   /_/ __ \\ |  |  _/ ___\\  /  _ \\  /     \\ _/ __ \\    |    |    /  _ \\   |    |  _/\\__  \\   /    \\ |  |/ /
                 \\        / \\  ___/ |  |__\\  \\___ (  <_> )|  Y Y  \\\\  ___/    |    |   (  <_> )  |    |   \\ / __ \\_|   |  \\|    <
                  \\__/\\  /   \\___  >|____/ \\___  > \\____/ |__|_|  / \\___  >   |____|    \\____/   |______  /(____  /|___|  /|__|_ \\
                       \\/        \\/            \\/               \\/      \\/                              \\/      \\/      \\/      \\/
                """;
		System.out.println(yellow+banner+reset);
		System.out.println(yellow+"   ***************************************************************************************************************"+reset);
		Scanner input = new Scanner(System.in);
		System.out.println(yellow + "   *"+cyan+"                                       ╔═══════════════════╗"+reset+yellow+"                                                 *"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ║ 1. Login          ║"+reset+yellow+"                                                 *"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ║-------------------║"+reset+yellow+"                                                 *"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ║ 2. Create Account ║"+reset+yellow+"                                                 *"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ╚═══════════════════╝" + reset+yellow+"                                                 *"+reset);
		System.out.println(yellow+"   *"+reset+yellow+"                                                                                                             *"+reset);
		
		System.out.print(yellow+"   *"+reset+green+"                                       🟢 Select Option: "+reset);
		int login_signup = input.nextInt();
		
		switch (login_signup) {
			case 1:
				BankAccount newAccount = login_account();
				if (newAccount != null) {
					newAccount.using_services(newAccount);
					return;
				}
				else{
					System.out.println(yellow + "   *"+red + "                         ╔════════════════════════════════════════════╗"+yellow+"                                      *"+reset);
					System.out.println(yellow + "   *"+red+"                         ║          ❌  Your Account Not Found         ║"+yellow+"                                      *"+reset);
					System.out.println(yellow + "   *"+red+"                         ╚════════════════════════════════════════════╝" + reset+yellow+"                                      *"+reset);
					run_machine();
				}
				break;
			
			case 2:
				BankAccount account = create_Account();
				account.using_services(account);
				break;
		}
	}
//	public static void creating_accounts(){
//		String bold = "\033[1m";      // Bold Text
//		String red = "\033[31m";      // Red Text
//		String green = "\033[32m";
//		String blue = "\033[34m";
//		String yellow = "\033[33m";   // Yellow Text
//		String reset = "\033[0m";
//		String cyan = "\033[36m";
//		BankAccount account = new BankAccount();
//		account.create_Account();
//		account.account_no = account.AccountNumberGenerator();
//		System.out.println(yellow+"   *"+reset);
//		System.out.println(yellow + "   *"+green+"                         ╔════════════════════════════════════════════╗"+reset);
//		System.out.println(yellow + "   *"+green+"                         ║ ✅ Account Created Successfully!            ║"+reset);
//		System.out.println(yellow + "   *"+green+"                         ║🔹 Your Account No: " + bold + account.account_no + reset+ green+ "               ║"+reset);
//		System.out.println(yellow + "   *"+green+"                         ╚════════════════════════════════════════════╝" + reset);
//		account.using_services(account);
//
//	}
//	public static BankAccount login(){
//		String bold = "\033[1m";      // Bold Text
//		String red = "\033[31m";      // Red Text
//		String green = "\033[32m";
//		String blue = "\033[34m";
//		String yellow = "\033[33m";   // Yellow Text
//		String reset = "\033[0m";
//		String cyan = "\033[36m";
//		BankAccount account = login_account();
//		if (account != null) {
//			System.out.println( yellow + "   *"+green+ "                         ╔════════════════════════════════════════════╗");
//			System.out.println(yellow + "   *"+green+"                         ║   ✅  Your Account Logged In Successfully   ║");
//			System.out.println(yellow + "   *"+green+"                         ╚════════════════════════════════════════════╝" + reset);
//			return account;
//		} else {
//			System.out.println(yellow + "   *"+red + "                         ╔════════════════════════════════════════════╗");
//			System.out.println(yellow + "   *"+red+"                         ║❌  Your Account  Not Found, Returning To Main Menu   ║");
//			System.out.println(yellow + "   *"+red+"                         ╚════════════════════════════════════════════╝" + reset);
//
//			run_machine();
//			return null;
//
//		}
//
//	}
	
	
	
	
	public static  BankAccount create_Account() {
		// Set terminal width manually (adjustable)
		String bold = "\033[1m";      // Bold Text
		String red = "\033[31m";      // Red Text
		String green = "\033[32m";
		String blue = "\033[34m";
		String yellow = "\u001B[93m";  // Yellow Text
		String reset = "\033[0m";
		String cyan = "\033[36m";
		Scanner input = new Scanner(System.in);
		BankAccount account = new BankAccount();
		
		// Title
		System.out.println(yellow + "   *"+reset+yellow+"                                                                                                             *"+reset);
		System.out.println(yellow + ("   *                                    ╔═══════════════════════╗"+yellow+"                                                *"));
		System.out.println(("   *                                    ║  CREATE NEW ACCOUNT   ║"+yellow+"                                                *"));
		System.out.println(("   *                                    ╚═══════════════════════╝"+yellow+"                                                *"+reset));
		
		// User Input Fields
		System.out.println(cyan + (yellow+"   *"+cyan+"                         ╔════════════════════════════════════════════╗"+reset+yellow+"                                      *"+reset));
		System.out.print((yellow+"   *"+cyan+"                         ║ ✏️  Full Name    : "+reset));
		account.name = input.nextLine();
		
		System.out.println((yellow+"   *"+cyan+"                         ║--------------------------------------------║"+reset+yellow+"                                      *"+reset));
		
		System.out.print((yellow+"   *"+cyan+"                         ║ 🔑  Password     : "));
		account.password = input.nextLine();
		
		System.out.println((yellow+"   *"+cyan+"                         ║--------------------------------------------║"+reset+yellow+"                                      *"+reset));
		
		// Account Type Selection
		System.out.println((yellow+"   *"+cyan+"                         ║ 🏦  Select Account Type:                   ║"+reset+yellow+"                                      *"+reset));
		System.out.println((yellow+"   *"+cyan+"                         ║    [1] Saving Account                      ║"+reset+yellow+"                                      *"+reset));
		System.out.println((yellow+"   *"+cyan+"                         ║    [2] Current Account                     ║"+reset+yellow+"                                      *"+reset));
		System.out.println((yellow+"   *"+cyan+"                         ╚════════════════════════════════════════════╝"+reset+yellow+"                                      *"+reset));
		System.out.println(yellow+"   *"+reset+yellow+"                                                                                                             *"+reset);
		System.out.print((yellow+"   *"+cyan+"                                       ➡️  Select Option: "));
		
		account.acct_type = input.nextInt();
		account.account_no = AccountNumberGenerator();
		
		accounts.add(account);
		System.out.println(yellow+"   *"+reset+yellow+"                                                                                                             *"+reset);
		System.out.println(yellow + "   *"+green+"                         ╔════════════════════════════════════════════╗"+reset+yellow+"                                      *"+reset);
		System.out.println(yellow + "   *"+green+"                         ║ ✅ Account Created Successfully!            ║"+reset+yellow+"                                      *"+reset);
		System.out.println(yellow + "   *"+green+"                         ║🔹 Your Account No: " + bold + account.account_no + reset+ green+ "               ║"+reset+yellow+"                                      *"+reset);
		System.out.println(yellow + "   *"+green+"                         ╚════════════════════════════════════════════╝" + reset+yellow+"                                      *"+reset);
		return account;
	}
	private static String AccountNumberGenerator() {
		return "ACC" + UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 6);
	}
	public static   BankAccount  login_account() {
		String bold = "\033[1m";      // Bold Text
		String red = "\033[31m";      // Red Text
		String green = "\033[32m";
		String blue = "\033[34m";
		String yellow = "\u001B[93m";   // Yellow Text
		String reset = "\033[0m";
		String cyan = "\033[36m";
		
		System.out.println(yellow + "   *"+reset+yellow+"                                                                                                             *"+reset);
		System.out.println(yellow + "   *                                          ╔═══════════╗"+reset+yellow+"                                                      *"+reset);
		System.out.println(yellow+"   *                                          ║" + bold + "   LOGIN   " + reset + yellow + "║"+yellow+"                                                      *"+reset);
		System.out.println(yellow+"   *                                          ╚═══════════╝" + reset+yellow+"                                                      *"+reset);
		Scanner input = new Scanner(System.in);
		System.out.println(cyan + (yellow+"   *"+cyan+"                         ╔════════════════════════════════════════════╗"+reset+yellow+"                                      *"+reset));
		System.out.print((yellow+"   *"+cyan+"                         ║ ✏️  Full Name    : "+reset));
		String entered_name = input.nextLine();
		System.out.println((yellow+"   *"+cyan+"                         ║--------------------------------------------║"+reset+yellow+"                                      *"+reset));
		
		System.out.print((yellow+"   *"+cyan+"                         ║ 🔑  Password     : "+yellow+reset));
		String entered_password = input.nextLine();
		System.out.println((yellow+"   *"+cyan+"                         ╚════════════════════════════════════════════╝"+reset+yellow+"                                      *"+reset));
		
		
		for (BankAccount account : accounts) {
			if (account.name.equals(entered_name) && account.password.equals(entered_password)) {
				System.out.println( yellow + "   *"+green+ "                         ╔════════════════════════════════════════════╗"+yellow+"                                      *"+reset);
				System.out.println(yellow + "   *"+green+"                         ║   ✅  Your Account Logged In Successfully   ║"+yellow+"                                      *"+reset);
				System.out.println(yellow + "   *"+green+"                         ╚════════════════════════════════════════════╝" + reset+yellow+"                                      *"+reset);
				return account;
			}
			else{
//				System.out.println(yellow + "   *"+red + "                         ╔════════════════════════════════════════════╗"+yellow+"                                      *"+reset);
//				System.out.println(yellow + "   *"+red+"                         ║          ❌  Login Failed, Try Agian..    ║"+yellow+"                                      *"+reset);
//				System.out.println(yellow + "   *"+red+"                         ╚════════════════════════════════════════════╝" + reset+yellow+"                                      *"+reset);
//              login_account();
				return null;
			}
			
		}
//		System.out.println("Invalid Password");
		return null;
	}
	
	public void using_services(BankAccount account) {
		int action = asking_action();
		while (action != 6) {
			switch (action) {
				case 1:
				account.diposite(account);
//				System.out.println();
				exitBank();
//				System.out.println("\n");
				action = asking_action();
				break;
				
				case 2:
					account.balance = account.withdraw(account);
//					System.out.println("balance"+account.balance);
//					System.out.println();
					exitBank();
//					System.out.println("\n");
					action = asking_action();
					break;
				
				case 3:
					account.display(account);
					exitBank();
					action = asking_action();
					break;
					
				case 4:
					BankAccount account2 = account.create_Account();
					account2.using_services(account2);
					break;
				
				case 5:
					BankAccount account1 = login_account();
					if (account1 != null) {
						account1.using_services(account1);
					}
					else {
						System.out.println(yellow + "   *"+red + "                         ╔════════════════════════════════════════════╗"+yellow+"                                      *"+reset);
						System.out.println(yellow + "   *"+red+"                         ║           ❌  Login Failed, Try Again       ║"+yellow+"                                      *"+reset);
						System.out.println(yellow + "   *"+red+"                         ╚════════════════════════════════════════════╝" + reset+yellow+"                                      *"+reset);
						using_services(account);
//						login_account();
					}
					break;
				case 6:
					System.out.println("Exiting from Bank");
					System.exit(0);
				
				default:
					System.out.println("Enter a valid action");
					using_services(account);
			}
		}
	}
	//METHOD TO ASK ACTION FROM USER
	
	public int asking_action() {
		System.out.println(yellow + "   *                                                                                                             *" + reset);
		System.out.println(yellow+"   *"+cyan+"                           ╔══════════════════════════════════════╗"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║            🏦 ACCOUNT MENU           ║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ╠══════════════════════════════════════╣"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [1] 💰 Deposit                      ║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [2] 💸 Withdraw                     ║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [3] 📊 Check Balance                ║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [4] "+green+"➕"+reset+cyan+" Create Another Account        ║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [5] 🔄 Login to Another Account     ║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [6] "+red+"❌"+reset+cyan+" Exit                          ║"+ reset+yellow+"                                          *"+reset);
		System.out.println(yellow+"   *"+cyan+"                           ╚══════════════════════════════════════╝" + reset+yellow+"                                          *"+reset);
		System.out.print(yellow+"   *"+reset+cyan+"                                       ➡️  Enter Action: "+reset);
		
		int action = input.nextInt();
		return action;
	}
	
	//METHOD TO ASK AMOUNT FROM USER
	
	public int asking_ammount() {
		System.out.print("Enter the ammount: ");
		int ammount = input.nextInt();
		return ammount;
	}
	
	//METHOD TO DEPOSIT AMOUNT
	
//	public void diposite( BankAccount account) {
//		String bold = "\033[1m";      // Bold Text
//		String red = "\033[31m";      // Red Text
//		String green = "\033[32m";
//		String blue = "\033[34m";
//		String yellow = "\u001B[93m";   // Yellow Text
//		String reset = "\033[0m";
//		String cyan = "\033[36m";
//		System.out.println(yellow + "   *"+cyan+"                                         ╔═══════════╗"+reset+yellow+"                                                       *"+reset);
//		System.out.println(yellow+"   *"+cyan+"                                         ║" + bold + "  Deposit  " +"║"+yellow+"                                                       *"+reset);
//		System.out.println(yellow+"   *"+cyan+"                                         ╚═══════════╝" + reset+yellow+"                                                       *"+reset);
//
//		System.out.print(green+"                                      Enter Deposit ammount: "+reset);
//		int ammount = input.nextInt();
//
//
//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//		String formattedDateTime = now.format(formatter);
//		account.balance += ammount;
//		int totalwidth = 50;
//		int boxWidth = 25;
//		int namePadding = boxWidth - 8 - Math.min(account.name.length(), boxWidth-8);
//		int accNoPadding = boxWidth - 9 - Math.min(account.account_no.length(), boxWidth-9);
//		int balancePadding = boxWidth - 9 - Math.min(String.valueOf(account.balance).length(), boxWidth-9);
//
//		System.out.println(yellow+"   *"+green + "                         "+"-".repeat(totalwidth)+reset+yellow+"                                  *"+reset);
//		System.out.println(yellow+"   *"+green+"                                          " + yellow + bold + "Deposit Receipt" + reset + green + "                      "+yellow+"                              *"+reset);
//		System.out.println(yellow+"   *"+reset+green+"                         "+"-".repeat(totalwidth)+reset+yellow+"                                  *"+reset);
//		System.out.println(yellow+"   *"+green+"                          " + green + "Account Holder  : " + "          "+reset + account.name +" ".repeat(namePadding)+green+ "                        "+yellow+"              *"+reset);
//		System.out.println(yellow+"   *"+green+"                          " + green + "Account Number  : "+ "          " + reset + account.account_no +" ".repeat(namePadding)+green+ "                    "+yellow+"              *"+reset);
//		System.out.println(yellow+"   *"+green+"                          " + green + "Amount Deposited: " + "          Rs."+ reset + ammount +" ".repeat(namePadding)+green+ "                      "+yellow+"               *"+reset);
//		System.out.println(yellow+"   *"+green+"                          " + green + "Date & Time     : " + "          "+ reset + formattedDateTime +green+ "          "+yellow+"                          *"+reset);
//		System.out.println(yellow+"   *"+green+"                         "+"-".repeat(totalwidth)+reset+yellow+"                                  *"+reset);
//		System.out.println(yellow+"   *"+green+"                          " + green + "Your amount has been successfully deposited!" + reset +green+ "   "+yellow+"                                    *"+reset);
//		System.out.println(yellow+"   *"+green+"                          " +  bold + "        Thank You for Banking with Us!" + reset +green+ "                 "+yellow+"                            *"+reset);
//		System.out.println(yellow+"   *"+reset+green+"                         "+"-".repeat(totalwidth)+reset+yellow+"                                  *"+reset);
//		System.out.println(yellow+"   *                              																		         *"+reset);
//	}
public void diposite(BankAccount account) {
	// ANSI color codes
	String bold = "\033[1m";
	String red = "\033[31m";
	String green = "\033[32m";
	String yellow = "\u001B[93m";
	String reset = "\033[0m";
	String cyan = "\033[36m";
	
	// Deposit header
	System.out.println(yellow + "   *                                                                                                             *" + reset);
	System.out.println(yellow + "   *" + cyan + "                                         ╔═══════════╗" + reset + yellow + "                                                       *" + reset);
	System.out.println(yellow + "   *" + cyan + "                                         ║" + bold + "  Deposit  " + "║" + yellow + "                                                       *" + reset);
	System.out.println(yellow + "   *" + cyan + "                                         ╚═══════════╝" + reset + yellow + "                                                       *" + reset);
	
	// Get deposit amount
	System.out.print(green + "                                      Enter Deposit amount: " + reset);
	int amount = input.nextInt();
	
	// Process deposit
	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	String formattedDateTime = now.format(formatter);
	account.balance += amount;
	
	// Receipt formatting
	final int totalWidth = 50;
	final int leftPadding = 26;
	final int valueWidth = totalWidth - 12; // After "Account Holder  : "
	
	// Format fields with consistent padding
	String formattedName = String.format("%-" + valueWidth + "s",account.name);
	String formattedAccNo = String.format("%-" + valueWidth + "s", account.account_no);
	String formattedAmount = String.format("%-" + (valueWidth-3) + "s", "Rs." + amount);
	String formattedDate = String.format("%-" + valueWidth + "s", formattedDateTime);
	
	// Divider line
	String divider = "-".repeat(totalWidth);
	
	// Print receipt
	System.out.println(yellow + "   *" + green + "                         " + divider + reset + yellow + "                                  *" + reset);
	System.out.println(yellow + "   *" + green + "                                          " + yellow + bold + "Deposit Receipt" + reset + green + "                      " + yellow + "                              *" + reset);
	System.out.println(yellow + "   *" + reset + green + "                         " + divider + reset + yellow + "                                  *" + reset);
	System.out.println(yellow + "   *" + green + "                          Account Holder  : " + reset +"          "+ formattedName + yellow + "                 *" + reset);
	System.out.println(yellow + "   *" + green + "                          Account Number  : " + reset +"          "+ formattedAccNo + yellow + "                 *" + reset);
	System.out.println(yellow + "   *" + green + "                          Amount Deposited: " + reset +"          "+ formattedAmount + yellow + "                    *" + reset);
	System.out.println(yellow + "   *" + green + "                          Date & Time     : " + reset +"          "+ formattedDate + yellow + "                 *" + reset);
	System.out.println(yellow + "   *" + green + "                         " + divider + reset + yellow +"          "+ "                        *" + reset);
	System.out.println(yellow + "   *" + green + "                          Your amount has been successfully deposited!" + reset + yellow + "                                       *" + reset);
	System.out.println(yellow + "   *" + green + "                          " + bold + "Thank You for Banking with Us!" + reset + yellow + "                                                     *" + reset);
	System.out.println(yellow + "   *" + reset + green + "                         " + divider + reset + yellow + "                                  *" + reset);
	System.out.println(yellow + "   *                                                                                                             *" + reset);
}
//	public void withdraw1
// METHOD TO WITHDRAW AMOUNT
	
	public int withdraw(BankAccount account) {
		System.out.println(yellow + "   *                                                                                                             *" + reset);
		
		System.out.println(yellow + "   *"+cyan+"                                         ╔═══════════╗"+reset+yellow+"                                                       *"+reset);
		System.out.println(yellow+"   *"+cyan+"                                         ║" + bold + "  Withdraw " +"║"+yellow+"                                                       *"+reset);
		System.out.println(yellow+"   *"+cyan+"                                         ╚═══════════╝" + reset+yellow+"                                                       *"+reset);
		
		System.out.print(green+"                                      Enter withdraw ammount: "+reset);
		int ammount = input.nextInt();
		if (ammount > account.balance) {
			System.out.println("Your balance is low, Please deposite some ammount, Thank you!");
			
		} else {
			int remaining_balance = account.balance - ammount;
//			System.out.println("balance: " +remaining_balance);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String formattedDateTime = now.format(formatter);
			account.balance += ammount;
			final int totalWidth = 50;
			final int leftPadding = 26;
			final int valueWidth = totalWidth - 12; // After "Account Holder  : "
			
			// Format fields with consistent padding
			String formattedName = String.format("%-" + valueWidth + "s", account.name);
			String formattedAccNo = String.format("%-" + valueWidth + "s", account.account_no);
			String formattedAmount = String.format("%-" + (valueWidth-3) + "s", "Rs." + ammount);
			String formattedDate = String.format("%-" + valueWidth + "s", formattedDateTime);
			
			// Divider line
			String divider = "-".repeat(totalWidth);
			
			// Print receipt
			System.out.println(yellow + "   *" + green + "                         " + divider + reset + yellow + "                                  *" + reset);
			System.out.println(yellow + "   *" + green + "                                          " + yellow + bold + "Withdraw Receipt" + reset + green + "                      " + yellow + "                             *" + reset);
			System.out.println(yellow + "   *" + reset + green + "                         " + divider + reset + yellow + "                                  *" + reset);
			System.out.println(yellow + "   *" + green + "                          Account Holder  : " +"          "+ reset + formattedName + yellow + "                 *" + reset);
			System.out.println(yellow + "   *" + green + "                          Account Number  : " +"          "+ reset + formattedAccNo + yellow + "                 *" + reset);
			System.out.println(yellow + "   *" + green + "                          Amount Withdrawn: " +"          "+ reset + formattedAmount + yellow + "                    *" + reset);
			System.out.println(yellow + "   *" + green + "                          Date & Time     : " +"          "+ reset + formattedDate + yellow + "                 *" + reset);
			System.out.println(yellow + "   *" + green + "                         " + divider + reset + yellow + "                                  *" + reset);
			System.out.println(yellow + "   *" + green + "                          Your amount has been successfully Withdrawn!" + reset + yellow + "                                       *" + reset);
			System.out.println(yellow + "   *" + green + "                          " + bold + "Thank You for Banking with Us!" + reset + yellow + "                                                     *" + reset);
			System.out.println(yellow + "   *" + reset + green + "                         " + divider + reset + yellow + "                                  *" + reset);
			System.out.println(yellow + "   *                                                                                                             *" + reset);
			
			return remaining_balance;
			
			
		}
		return account.balance;
		
	}
	
	//METHOD TO DISPLAY NAME AND BALANCE
	
	
//	public void display(BankAccount account) {
//		System.out.println(yellow + "   *"+cyan+"                                ╔═══════════════════════════╗"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ║ "+bold+yellow+"     Account  Details"+reset+cyan+"     ║"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ╠═══════════════════════════╣"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ║ Name:   "+account.name+"          ║"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ║---------------------------║"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ║ Acc.No:  "+account.account_no+" ║"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ║---------------------------║"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ║ Balance:  "+account.balance+" ║"+reset+yellow+"                                                *"+reset);
//		System.out.println(yellow + "   *"+cyan+"                                ╚═══════════════════════════╝" + reset+yellow+"                                                *"+reset);
//	}
public void display(BankAccount account) {
	// Calculate padding needed for each field
	int boxWidth = 25;
	int namePadding = boxWidth - 8 - Math.min(account.name.length(), boxWidth-8);
	int accNoPadding = boxWidth - 9 - Math.min(account.account_no.length(), boxWidth-9);
	int balancePadding = boxWidth - 9 - Math.min(String.valueOf(account.balance).length(), boxWidth-9);
	System.out.println(yellow + "   *                                                                                                             *" + reset);
	
	
	System.out.println(yellow + "   *"+cyan+"                                ╔═══════════════════════════╗"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ║ "+bold+yellow+"     Account  Details"+reset+cyan+"     ║"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ╠═══════════════════════════╣"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ║ Name:    " + account.name+reset +cyan+ " ".repeat(namePadding) + "║"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ║---------------------------║"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ║ Acc.No:  " + account.account_no+reset +cyan+ " ".repeat(accNoPadding) + " ║"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ║---------------------------║"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ║ Balance: " + account.balance +reset+cyan+ " ".repeat(balancePadding) + " ║"+reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *"+cyan+"                                ╚═══════════════════════════╝" + reset+yellow+"                                                *"+reset);
	System.out.println(yellow + "   *                                                                                                             *" + reset);
	
}
	
	//METHOD TO EXIT FROM PROGRAM
	
	
	public void exitBank() {
		System.out.println(yellow + "   *                                                                                                             *" + reset);
		System.out.print(red+"                                   Do You want to exit? (y/n): ");
		char exit = input.next().charAt(0);
		System.out.print(reset);
		switch (exit) {
			case 'y':
				exiting();
				exit(0);
		}
		
	}
	public static String centerText(String text, int width) {
		int padSize = (width - text.length()) / 2;
		return " ".repeat(Math.max(0, padSize)) + text;
	}
	public void exiting(){
//		System.out.println(yellow+"   *"+red+"                                                  Exiting From Bank"+reset);
		System.out.println(yellow+"   ***************************************************************************************************************"+reset);
	}
}

	

	
	


