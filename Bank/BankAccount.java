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
	String yellow = "\033[33m";   // Yellow Text
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
		System.out.println(yellow + "   *"+cyan+"                                       ╔═══════════════════╗"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ║ 1. Login          ║"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ║-------------------║"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ║ 2. Create Account ║"+reset);
		System.out.println(yellow + "   *"+cyan+"                                       ╚═══════════════════╝" + reset);
		System.out.println(yellow+"   *"+reset);
		
		System.out.print(yellow+ "   *"+green+"                                       🟢 Select Option: "+reset);
		int login_signup = input.nextInt();
		
		switch (login_signup) {
			case 1:
				BankAccount newAccount = login_account();
				if (newAccount != null) {
					newAccount.using_services(newAccount);
					return;
				}
				else{
					System.out.println(yellow + "   *"+red + "                         ╔════════════════════════════════════════════╗");
					System.out.println(yellow + "   *"+red+"                         ║          ❌  Your Account  Not Found        ║");
					System.out.println(yellow + "   *"+red+"                         ╚════════════════════════════════════════════╝" + reset);
					run_machine();
				}
				break;
			
			case 2:
				creating_accounts();
				break;
		}
	}
	public static void creating_accounts(){
		String bold = "\033[1m";      // Bold Text
		String red = "\033[31m";      // Red Text
		String green = "\033[32m";
		String blue = "\033[34m";
		String yellow = "\033[33m";   // Yellow Text
		String reset = "\033[0m";
		String cyan = "\033[36m";
		BankAccount account = new BankAccount();
		account.create_Account();
		account.account_no = account.AccountNumberGenerator();
		System.out.println(yellow+"   *"+reset);
		System.out.println(yellow + "   *"+green+"                         ╔════════════════════════════════════════════╗"+reset);
		System.out.println(yellow + "   *"+green+"                         ║ ✅ Account Created Successfully!            ║"+reset);
		System.out.println(yellow + "   *"+green+"                         ║🔹 Your Account No: " + bold + account.account_no + reset+ green+ "               ║"+reset);
		System.out.println(yellow + "   *"+green+"                         ╚════════════════════════════════════════════╝" + reset);
		account.using_services(account);
		
	}
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
	
	
	
	
	public BankAccount create_Account() {
		// Set terminal width manually (adjustable)
		
		Scanner input = new Scanner(System.in);
		BankAccount account = new BankAccount();
		
		// Title
		System.out.println(yellow + "   *"+reset);
		System.out.println(yellow + ("   *                                    ╔═══════════════════════╗"));
		System.out.println(("   *                                    ║  CREATE NEW ACCOUNT   ║"));
		System.out.println(("   *                                    ╚═══════════════════════╝"));
		
		// User Input Fields
		System.out.println(cyan + (yellow+"   *"+cyan+"                         ╔════════════════════════════════════════════╗"+reset));
		System.out.print((yellow+"   *"+cyan+"                         ║ ✏️  Full Name    : "+reset));
		account.name = input.nextLine();
		
		System.out.println((yellow+"   *"+cyan+"                         ║--------------------------------------------║"+reset));
		
		System.out.print((yellow+"   *"+cyan+"                         ║ 🔑  Password     : "));
		account.password = input.nextLine();
		
		System.out.println((yellow+"   *"+cyan+"                         ║--------------------------------------------║"+reset));
		
		// Account Type Selection
		System.out.println((yellow+"   *"+cyan+"                         ║ 🏦  Select Account Type:                   ║"+reset));
		System.out.println((yellow+"   *"+cyan+"                         ║    [1] Saving Account                      ║"+reset));
		System.out.println((yellow+"   *"+cyan+"                         ║    [2] Current Account                     ║"+reset));
		System.out.println((yellow+"   *"+cyan+"                         ╚════════════════════════════════════════════╝"+reset));
		System.out.println(yellow+"   *"+reset);
		System.out.print((yellow+"   *"+cyan+"                                       ➡️  Select Option: "));
		
		account.acct_type = input.nextInt();
		
		accounts.add(account);
		return account;
	}
	private String AccountNumberGenerator() {
		return "ACC" + UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 6);
	}
	public static   BankAccount  login_account() {
		String bold = "\033[1m";      // Bold Text
		String red = "\033[31m";      // Red Text
		String green = "\033[32m";
		String blue = "\033[34m";
		String yellow = "\033[33m";   // Yellow Text
		String reset = "\033[0m";
		String cyan = "\033[36m";
		System.out.println(yellow + "   *"+reset);
		System.out.println(yellow + "   *                                          ╔═══════════╗");
		System.out.println("   *                                          ║" + bold + "   LOGIN   " + reset + yellow + "║");
		System.out.println("   *                                          ╚═══════════╝" + reset);
		Scanner input = new Scanner(System.in);
		System.out.println(cyan + (yellow+"   *"+cyan+"                         ╔════════════════════════════════════════════╗"+reset));
		System.out.print((yellow+"   *"+cyan+"                         ║ ✏️  Full Name    : "+reset));
		String entered_name = input.nextLine();
		System.out.println((yellow+"   *"+cyan+"                         ║--------------------------------------------║"+reset));
		
		System.out.print((yellow+"   *"+cyan+"                         ║ 🔑  Password     : "));
		String entered_password = input.nextLine();
		System.out.println((yellow+"   *"+cyan+"                         ╚════════════════════════════════════════════╝"+reset));
		
		
		for (BankAccount account : accounts) {
			if (account.name.equals(entered_name) && account.password.equals(entered_password)) {
				System.out.println( yellow + "   *"+green+ "                         ╔════════════════════════════════════════════╗");
				System.out.println(yellow + "   *"+green+"                         ║   ✅  Your Account Logged In Successfully   ║");
				System.out.println(yellow + "   *"+green+"                         ╚════════════════════════════════════════════╝" + reset);
				return account;
			}
			else{
				System.out.println(yellow + "   *"+red + "                         ╔════════════════════════════════════════════╗");
				System.out.println(yellow + "   *"+red+"                         ║❌  Login Failed, Try Agian..loginaccount║");
				System.out.println(yellow + "   *"+red+"                         ╚════════════════════════════════════════════╝" + reset);
				login_account();
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
				int ammount = asking_ammount();
				account.diposite(ammount);
				System.out.println();
				exitBank();
				System.out.println("\n");
				action = asking_action();
				break;
				
				case 2:
					ammount = asking_ammount();
					account.withdraw(ammount);
					System.out.println();
					exitBank();
					System.out.println("\n");
					action = asking_action();
					break;
				
				case 3:
					account.display();
					exitBank();
					action = asking_action();
					break;
					
				case 4:
					account.creating_accounts();
					break;
				
				case 5:
					BankAccount account1 = login_account();
					if (account1 != null) {
						account1.using_services(account1);
					}
					else {
						System.out.println(yellow + "   *"+red + "                         ╔════════════════════════════════════════════╗");
						System.out.println(yellow + "   *"+red+"                         ║❌  Login Failed, Try Again, using services                 ║");
						System.out.println(yellow + "   *"+red+"                         ╚════════════════════════════════════════════╝" + reset);
						login_account();
					}
					break;
					
				default:
					System.out.println("Enter a valid action");
			}
		}
	}
	//METHOD TO ASK ACTION FROM USER
	
	public int asking_action() {
		System.out.println(yellow+"   *"+cyan+"                           ╔══════════════════════════════════════╗"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║            🏦 ACCOUNT MENU           ║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ╠══════════════════════════════════════╣"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [1] 💰 Deposit                      ║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [2] 💸 Withdraw                     ║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [3] 📊 Check Balance                ║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [4] "+green+"➕"+reset+cyan+" Create Another Account        ║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [5] 🔄 Login to Another Account     ║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║--------------------------------------║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ║  [6] "+red+"❌"+reset+cyan+" Exit                          ║"+ reset);
		System.out.println(yellow+"   *"+cyan+"                           ╚══════════════════════════════════════╝" + reset);
		System.out.print(yellow+"   *"+reset+cyan+"                                       ➡️  Enter Action: ");
		
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
	
	public void diposite(int ammount) {
		String bold = "\033[1m";      // Bold Text
		String red = "\033[31m";      // Red Text
		String green = "\033[32m";
		String blue = "\033[34m";
		String yellow = "\033[33m";   // Yellow Text
		String reset = "\033[0m";
		String cyan = "\033[36m";
		
		BankAccount account = create_Account();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		balance += ammount;
		System.out.println(cyan + "\n*********************************************");
		System.out.println("*           " + yellow + bold + "Deposit Receipt" + reset + cyan + "            *");
		System.out.println("*********************************************");
		System.out.println("* " + green + "Account Holder  : " + reset + this.name + "     *");
		System.out.println("* " + green + "Account Number  : " + reset + account.account_no + "     *");
		System.out.println("* " + green + "Amount Deposited: Rs. " + reset + ammount + "     *");
		System.out.println("* " + green + "Date & Time     : " + reset + formattedDateTime + "   *");
		System.out.println("*********************************************");
		System.out.println("* " + cyan + "Your amount has been successfully deposited!" + reset + " *");
		System.out.println("* " + yellow + bold + "Thank You for Banking with Us!" + reset + " *");
		System.out.println("*********************************************\n");
	}
//	public void withdraw1
	//METHOD TO WITHDRAW AMOUNT
	
	public void withdraw(int ammount) {
		if (ammount > balance) {
			System.out.println("Your balance is low, Please deposite some ammount, Thank you!");
		} else {
			balance -= ammount;
			System.out.println("You has received your ammount, Thank you!");
		}
	}
	
	//METHOD TO DISPLAY NAME AND BALANCE
	
	
	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Total balance: " + balance);
	}
	
	//METHOD TO EXIT FROM PROGRAM
	
	
	public void exitBank() {
		System.out.print("Do You want to exit? (y/n): ");
		char exit = input.next().charAt(0);
		switch (exit) {
			case 'y' -> exit(0);
		}
		
	}
	public static String centerText(String text, int width) {
		int padSize = (width - text.length()) / 2;
		return " ".repeat(Math.max(0, padSize)) + text;
	}
}

	

	
	


