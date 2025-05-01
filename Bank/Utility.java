package Bank;


import java.util.Scanner;

import static java.lang.System.exit;

public class UserInterface {
	static String bold = "\033[1m";
	static String red = "\033[31m";
	static String green = "\033[32m";
	static String blue = "\033[34m";
	static String yellow = "\u001B[93m";
	static String reset = "\033[0m";
	static String cyan = "\033[36m";
	public static void ShowLoginScreen() {
	
	}
	public static void exiting() {
		
		System.out.println(
				yellow
						+ "   ***************************************************************************************************************"
						+ reset);
	}
	public static BankAccount create_Account() {
		
		Scanner input = new Scanner(System.in);
		
		BankAccount account = new BankAccount();
		
		System.out.println(
				yellow
						+ "   *"
						+ reset
						+ yellow
						+ "                                                                                    "
						+ "                         *"
						+ reset);
		System.out.println(
				yellow
						+ ("   *                                    ╔═══════════════════════╗"
						+ yellow
						+ "                                                *"));
		System.out.println(
				("   *                                    ║  CREATE NEW ACCOUNT   ║"
						+ yellow
						+ "                                                *"));
		System.out.println(
				("   *                                    ╚═══════════════════════╝"
						+ yellow
						+ "                                                *"
						+ reset));
		
		// User Input Fields
		System.out.println(
				cyan
						+ (yellow
						+ "   *"
						+ cyan
						+ "                         ╔════════════════════════════════════════════╗"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset));
		System.out.print(
				(yellow + "   *" + cyan + "                         ║ ✏️  Full Name    : " + reset));
		account.name = input.nextLine();
		System.out.println(
				(yellow
						+ "   *"
						+ cyan
						+ "                         ║--------------------------------------------║"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset));
		System.out.print((yellow + "   *" + cyan + "                         ║ 🔑  Password     : "));
		account.password = input.nextLine();
		System.out.println(
				(yellow
						+ "   *"
						+ cyan
						+ "                         ║--------------------------------------------║"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset));
		System.out.println(
				(yellow
						+ "   *"
						+ cyan
						+ "                         ║ 🏦  Select Account Type:                   ║"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset));
		System.out.println(
				(yellow
						+ "   *"
						+ cyan
						+ "                         ║    [1] Saving Account                      ║"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset));
		System.out.println(
				(yellow
						+ "   *"
						+ cyan
						+ "                         ║    [2] Current Account                     ║"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset));
		System.out.println(
				(yellow
						+ "   *"
						+ cyan
						+ "                         ╚════════════════════════════════════════════╝"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset));
		System.out.println(
				yellow
						+ "   *"
						+ reset
						+ yellow
						+ "                                                                                    "
						+ "                         *"
						+ reset);
		
		System.out.print(
				(yellow + "   *" + cyan + "                                       ➡️  Select Option: "));
		
		account.acct_type = input.nextInt();
		account.account_no = AccountNumberGenerator();
		
		accounts.add(account);
		
		System.out.println(
				yellow
						+ "   *"
						+ reset
						+ yellow
						+ "                                                                                    "
						+ "                         *"
						+ reset);
		System.out.println(
				yellow
						+ "   *"
						+ green
						+ "                         ╔════════════════════════════════════════════╗"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset);
		System.out.println(
				yellow
						+ "   *"
						+ green
						+ "                         ║✅ Account Created Successfully!            ║"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset);
		System.out.println(
				yellow
						+ "   *"
						+ green
						+ "                         ║🔹 Your Account No: "
						+ bold
						+ account.account_no
						+ reset
						+ green
						+ "               ║"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset);
		System.out.println(
				yellow
						+ "   *"
						+ green
						+ "                         ╚════════════════════════════════════════════╝"
						+ reset
						+ yellow
						+ "                                      *"
						+ reset);
		
		return account;
	}
	
	
}
