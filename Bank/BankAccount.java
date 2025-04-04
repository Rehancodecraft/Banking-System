//AUTHOR: REHAN SHAFIQ
package Bank;

import java.sql.SQLOutput;
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
		String yellow = "\033[33m";   // Yellow Text
		String reset = "\033[0m";
		String cyan = "\033[36m";
		System.out.println(yellow + "   *                             ╔════════════════════════════════════╗                                *");
		System.out.println("   ******************************║" + bold + "         WELCOME TO BANK          " + reset + yellow + "  ║*********************************");
		System.out.println("   *                             ╚════════════════════════════════════╝                                *" + reset);
		Scanner input = new Scanner(System.in);
		System.out.println(cyan + "                                         ╔═══════════════════╗");
		System.out.println("                                         ║ 1. Login          ║");
		System.out.println("                                         ║-------------------║");
		System.out.println("                                         ║ 2. Create Account ║");
		System.out.println("                                         ╚═══════════════════╝" + reset);
		
		System.out.print(green+ "   🟢 Select Option: "+reset);
		int login_signup = input.nextInt();
		
		switch (login_signup) {
			case 1:
				login();
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
		System.out.println(green + "\n╔════════════════════════════════════╗");
		System.out.println("║ ✅ Account Created Successfully!    ║");
		System.out.println("║ 🔹 Your Account No: " + bold + account.account_no + reset + green + "      ║");
		System.out.println("╚════════════════════════════════════╝" + reset);
		account.using_services(account);
		
	}
	public static void login(){
		BankAccount account = login_account();
		if (account != null){
			System.out.println("Your account Logged in Succefully");
			account.using_services(account);
		}
		else{
			System.out.println("Your account is Not Found");
			run_machine();
		}
	}
	
	
	
	
	public BankAccount create_Account() {
		int width = 60; // Set terminal width manually (adjustable)
		
		Scanner input = new Scanner(System.in);
		BankAccount account = new BankAccount();
		
		// Title
		System.out.println(yellow + centerText("╔═══════════════════════╗", width));
		System.out.println(centerText("║  CREATE NEW ACCOUNT   ║", width));
		System.out.println(centerText("╚═══════════════════════╝", width));
		
		// User Input Fields
		System.out.println(cyan + centerText("╔════════════════════════════════════════════╗", width));
		System.out.print(centerText("║ ✏️  Full Name    : ", width));
		account.name = input.nextLine();
		
		System.out.println(centerText("║--------------------------------------------║", width));
		
		System.out.print(centerText("║ 🔑  Password     : ", width));
		account.password = input.nextLine();
		
		System.out.println(centerText("║--------------------------------------------║", width));
		
		// Account Type Selection
		System.out.println(centerText("║ 🏦  Select Account Type:                   ║", width));
		System.out.println(centerText("║    [1] Saving Account                      ║", width));
		System.out.println(centerText("║    [2] Current Account                     ║", width));
		System.out.println(centerText("╚════════════════════════════════════════════╝", width));
		System.out.print(centerText("➡️  Select Option: ", width));
		
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
		System.out.println(yellow + "                                              ╔═══════════╗");
		System.out.println("                                              ║" + bold + "   LOGIN   " + reset + yellow + "║");
		System.out.println("                                              ╚═══════════╝" + reset);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter your Full Name: ");
		String entered_name = input.nextLine();
		System.out.print("Enter your Password: ");
		String entered_password = input.nextLine();
		
		for (BankAccount account : accounts) {
			if (account.name.equals(entered_name) && account.password.equals(entered_password)) {
				return account;
			}
			
		}
		System.out.println("Invalid Password");
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
					login();
					break;
					
				default:
					System.out.println("Enter a valid action");
			}
		}
	}
	//METHOD TO ASK ACTION FROM USER
	
	public int asking_action() {
		System.out.println("1.Deposite\n2.Withdraw\n3.Checkbalance\n4.Create Another Account \n5.Login Another Account\n5.Exit");
		System.out.print("Enter Action: ");
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
		balance += ammount;
		System.out.println("Your ammount has been deposited, Thank you!");
	}
	
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
	

	
	


