package Bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utility {

  static String bold = "\033[1m";
  static String red = "\033[31m";
  static String green = "\033[32m";
  static String blue = "\033[34m";
  static String yellow = "\u001B[93m";
  static String reset = "\033[0m";
  static String cyan = "\033[36m";

  public static class GetInputWithStyles {
    private static final Scanner input = new Scanner(System.in);

    //		Scanner input = new Scanner(System.in);

    public static char getInputForExiting() {
      System.out.print(red + "                                   Do You want to exit? (y/n): ");
      char exit;

      do {
        exit = input.next().charAt(0);
      } while (exit != 'y' && exit != 'n');

      System.out.print(reset);
      return exit;
    }

    public static int getInputForStartUpScreen() {
      System.out.print(
          yellow
              + "   *"
              + reset
              + green
              + "                                       🟢 Select Option: "
              + reset);

      return input.nextInt();
    }

    public static String getFullNameInputForCreateAccount() {

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

      System.out.print((yellow + "   *" + cyan + "                         ║ ✏️  Full Name    : "));

      input.nextLine();
      return input.nextLine();
    }

    public static String getPasswordInputForCreateAccount() {

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
      return input.nextLine();
    }

    public static int getAccountTypeInputForCreateAccount() {
      int accountType;
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
              + "                                                                                  "
              + "                           *"
              + reset);
      do {
        System.out.print(
            (yellow
                + "   *"
                + cyan
                + "                                       ➡️  Select Option: "));

        accountType = input.nextInt();
      } while (accountType != 1 && accountType != 2);
      input.nextLine();

      return accountType;
    }

    public static String getFullNameInputForLoginAccount() {

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

      input.nextLine();
      return input.nextLine();
    }

    public static String getPasswordInputForLoginAccount() {

      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                         ║--------------------------------------------║"
              + reset
              + yellow
              + "                                      *"
              + reset));
      System.out.print(
          (yellow
              + "   *"
              + cyan
              + "                         ║ 🔑  Password     : "
              + yellow
              + reset));
      String password = input.nextLine();

      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                         ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                      *"
              + reset));

      return password;
    }

    public static int getBankServicesInput() {

      System.out.print(
          reset + cyan + "                                           ➡️  Enter Action: " + reset);
      int action = input.nextInt();
      input.nextLine();
      return action;
    }

    public static int getDepositAmountInput() {

      System.out.print(
          green + "                                      Enter Deposit amount: " + reset);
      int amount = input.nextInt();
      input.nextLine();
      return amount;
    }

    public static int getWithdrawAmountInput() {

      System.out.print(
          green + "                                      Enter withdraw ammount: " + reset);

      int amount = input.nextInt();
      input.nextLine();
      return amount;
    }
  }

  public static class UserInterface {

    public static void startUpScreenDisplay() {
      String banner =
          """
								 __      __         .__                                     ___________         __________                  __
								/  \\    /  \\  ____  |  |    ____    ____    _____    ____   \\__    ___/  ____   \\______   \\_____     ____  |  | __
								\\   \\/\\/   /_/ __ \\ |  |  _/ ___\\  /  _ \\  /     \\ _/ __ \\    |    |    /  _ \\   |    |  _/\\__  \\   /    \\ |  |/ /
								 \\        / \\  ___/ |  |__\\  \\___ (  <_> )|  Y Y  \\\\  ___/    |    |   (  <_> )  |    |   \\ / __ \\_|   |  \\|    <
								  \\__/\\  /   \\___  >|____/ \\___  > \\____/ |__|_|  / \\___  >   |____|    \\____/   |______  /(____  /|___|  /|__|_ \\
									   \\/        \\/            \\/               \\/      \\/                              \\/      \\/      \\/      \\/
								""";

      System.out.println(yellow + banner + reset);
      System.out.println(
          yellow
              + "   ***************************************************************************************************************"
              + reset);
      Scanner input = new Scanner(System.in);

      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                       ╔═══════════════════╗"
              + reset
              + yellow
              + "                                                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                       ║ 1. Login          ║"
              + reset
              + yellow
              + "                                                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                       ║-------------------║"
              + reset
              + yellow
              + "                                                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                       ║ 2. Create Account ║"
              + reset
              + yellow
              + "                                                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                       ╚═══════════════════╝"
              + reset
              + yellow
              + "                                                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + yellow
              + "                                                                                  "
              + "                           *"
              + reset);
    }

    public static void accountNotFoundDisplay() {
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ╔════════════════════════════════════════════╗"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ║          ❌  Your Account Not Found        ║"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                      *"
              + reset);
    }

    public static void createNewAccountDisplay() {
      System.out.println(
          yellow
              + "   *"
              + reset
              + yellow
              + "                                                                                  "
              + "                           *"
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
    }

    public static void accountCreatedSuccessfullyDisplay(String accountNo) {
      System.out.println(
          yellow
              + "   *"
              + reset
              + yellow
              + "                                                                                  "
              + "                           *"
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
              + accountNo
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
    }

    public static void loginAccountDisplay() {
      System.out.println(
          yellow
              + "   *"
              + reset
              + yellow
              + "                                                                                  "
              + "                           *"
              + reset);
      System.out.println(
          yellow
              + "   *                                          ╔═══════════╗"
              + reset
              + yellow
              + "                                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *                                          ║"
              + bold
              + "   LOGIN   "
              + reset
              + yellow
              + "║"
              + yellow
              + "                                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *                                          ╚═══════════╝"
              + reset
              + yellow
              + "                                                      *"
              + reset);
    }

    public static void accountLoggedInSuccessfullyDisplay() {
      System.out.println(
          yellow
              + "   *"
              + green
              + "                         ╔════════════════════════════════════════════╗"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                         ║   ✅  Your Account Logged In Successfully  ║"
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
    }

    public static void accountLoginFailedDisplay() {
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ╔════════════════════════════════════════════╗"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ║           ❌  Login Failed, Try Again       ║"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                      *"
              + reset);
    }

    public static void enterValidActionDisplay() {
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ╔════════════════════════════════════════════╗"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ║           Enter a valid Action!            ║"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                         ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                      *"
              + reset);
    }

    public static void bankServicesDisplay() {
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ╔══════════════════════════════════════╗"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║            🏦 ACCOUNT MENU           ║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ╠══════════════════════════════════════╣"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║  [1] 💰 Deposit                      ║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║--------------------------------------║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║  [2] 💸 Withdraw                     ║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║--------------------------------------║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║  [3] 📊 Check Balance                ║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║--------------------------------------║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║  [4] "
              + green
              + "➕"
              + reset
              + cyan
              + " Create Another Account       ║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║--------------------------------------║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║  [5] 🔄 Login to Another Account     ║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║--------------------------------------║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ║  [6] "
              + red
              + "❌"
              + reset
              + cyan
              + " Exit                         ║"
              + reset
              + yellow
              + "                                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                           ╚══════════════════════════════════════╝"
              + reset
              + yellow
              + "                                          *"
              + reset);
    }

    public static void depositAmountDisplay() {
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                         ╔═══════════╗"
              + reset
              + yellow
              + "                                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                         ║"
              + bold
              + "  Deposit  "
              + "║"
              + yellow
              + "                                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                         ╚═══════════╝"
              + reset
              + yellow
              + "                                                       *"
              + reset);
    }

    public static void invalidDepositAmountDisplay() {

      System.out.println(
          yellow
              + ("   *"
                  + red
                  + "                              ╔═══════════════════════════════╗"
                  + yellow
                  + "                                              *"));
      System.out.println(
          (yellow
              + "   *"
              + red
              + "                              ║  Invalid Amount, Try Again!   ║"
              + yellow
              + "                                              *"));
      System.out.println(
          (yellow
              + "   *"
              + red
              + "                              ╚═══════════════════════════════╝"
              + yellow
              + "                                              *"
              + reset));
    }

    public static void depositAmountReceiptDisplay(String Name, String AccountNo, int amount) {
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      String formattedDateTime = now.format(formatter);

      // Receipt formatting
      final int totalWidth = 50;
      final int leftPadding = 26;
      final int valueWidth = totalWidth - 12; // After "Account Holder  : "

      // Format fields with consistent padding
      String formattedName = String.format("%-" + valueWidth + "s", Name);
      String formattedAccNo = String.format("%-" + valueWidth + "s", AccountNo);
      String formattedAmount = String.format("%-" + (valueWidth - 3) + "s", "Rs." + amount);
      String formattedDate = String.format("%-" + valueWidth + "s", formattedDateTime);

      // Divider line
      String divider = "-".repeat(totalWidth);

      // Print receipt
      System.out.println(
          yellow
              + "   *"
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "                                  *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                          "
              + yellow
              + bold
              + "Deposit Receipt"
              + reset
              + green
              + "                      "
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "                                  *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Account Holder  : "
              + reset
              + "          "
              + formattedName
              + yellow
              + "                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Account Number  : "
              + reset
              + "          "
              + formattedAccNo
              + yellow
              + "                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Amount Deposited: "
              + reset
              + "          "
              + formattedAmount
              + yellow
              + "                    *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Date & Time     : "
              + reset
              + "          "
              + formattedDate
              + yellow
              + "                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "          "
              + "                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Your amount has been successfully deposited!"
              + reset
              + yellow
              + "                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          "
              + bold
              + "Thank You for Banking with Us!"
              + reset
              + yellow
              + "                                                     *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "                                  *"
              + reset);
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
    }

    public static void withdrawAmountDisplay() {
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);

      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                         ╔═══════════╗"
              + reset
              + yellow
              + "                                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                         ║"
              + bold
              + "  Withdraw "
              + "║"
              + yellow
              + "                                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                         ╚═══════════╝"
              + reset
              + yellow
              + "                                                       *"
              + reset);
    }

    public static void invalidWithdrawAmountDisplay() {
      System.out.println(
          yellow
              + "   *"
              + red
              + "              "
              + " ╔═══════════════════════════════════════════════════════════════════╗"
              + yellow
              + "                         *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "               ║   Your balance is low, Please deposite some ammount, Thank you!  "
              + " ║"
              + yellow
              + "                         *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "              "
              + " ╚═══════════════════════════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                         *"
              + reset);
    }

    public static void withdrawAmountReceiptDisplay(String Name, String AccountNo, int amount) {
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      String formattedDateTime = now.format(formatter);
      final int totalWidth = 50;
      final int leftPadding = 26;
      final int valueWidth = totalWidth - 12; // After "Account Holder  : "

      // Format fields with consistent padding
      String formattedName = String.format("%-" + valueWidth + "s", Name);
      String formattedAccNo = String.format("%-" + valueWidth + "s", AccountNo);
      String formattedAmount = String.format("%-" + (valueWidth - 3) + "s", "Rs." + amount);
      String formattedDate = String.format("%-" + valueWidth + "s", formattedDateTime);

      // Divider line
      String divider = "-".repeat(totalWidth);

      // Print receipt
      System.out.println(
          yellow
              + "   *"
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "                                  *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                          "
              + yellow
              + bold
              + "Withdraw Receipt"
              + reset
              + green
              + "                      "
              + yellow
              + "                             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "                                  *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Account Holder  : "
              + "          "
              + reset
              + formattedName
              + yellow
              + "                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Account Number  : "
              + "          "
              + reset
              + formattedAccNo
              + yellow
              + "                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Amount Withdrawn: "
              + "          "
              + reset
              + formattedAmount
              + yellow
              + "                    *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Date & Time     : "
              + "          "
              + reset
              + formattedDate
              + yellow
              + "                 *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "                                  *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          Your amount has been successfully Withdrawn!"
              + reset
              + yellow
              + "                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          "
              + bold
              + "          Thank You for Banking with Us!"
              + reset
              + yellow
              + "                                           *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                         "
              + divider
              + reset
              + yellow
              + "                                  *"
              + reset);
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
    }

    public static void accountDetailsDisplay(String Name, String AccountNo, int Balance) {
      int boxWidth = 25;
      int namePadding = boxWidth - 8 - Math.min(Name.length(), boxWidth - 8);
      int accNoPadding = boxWidth - 9 - Math.min(AccountNo.length(), boxWidth - 9);
      int balancePadding = boxWidth - 9 - Math.min(String.valueOf(Balance).length(), boxWidth - 9);
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);

      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ╔═══════════════════════════╗"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ║ "
              + bold
              + yellow
              + "     Account  Details"
              + reset
              + cyan
              + "     ║"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ╠═══════════════════════════╣"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ║ Name:    "
              + Name
              + reset
              + cyan
              + " ".repeat(namePadding)
              + "║"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ║---------------------------║"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ║ Acc.No:  "
              + AccountNo
              + reset
              + cyan
              + " ".repeat(accNoPadding)
              + " ║"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ║---------------------------║"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ║ Balance: "
              + Balance
              + reset
              + cyan
              + " ".repeat(balancePadding)
              + " ║"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                ╚═══════════════════════════╝"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
    }

    public static void exiting() {

      System.out.println(
          yellow
              + "   ***************************************************************************************************************"
              + reset);
    }

    public static void exitBank() {
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
    }
  }
}
