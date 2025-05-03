package Bank;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.*;

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

      char exit;

      while (true) {
        System.out.print(
            red + "                                              Do You want to exit? (y/n): ");
        System.out.print(reset);
        exit = input.next().charAt(0);
        input.nextLine();
        if (exit == 'y' || exit == 'Y' || exit == 'n' || exit == 'N') {
          return exit;
        } else {
          Utility.UserInterface.enterValidActionDisplay();
        }
      }
    }

    public static int getInputForStartUpScreen() {
      while (true) {
        System.out.print(
            yellow
                + "   *"
                + reset
                + green
                + "                                             🟢 Select Option: "
                + reset);
        String userInput = input.nextLine().trim();
        if (userInput.matches("\\d+")) {
          int action = Integer.parseInt(userInput);
          if (action >= 1 && action <= 2) {
            return action;
          } else {
            Utility.UserInterface.enterValidActionDisplay();
          }
        } else {
          Utility.UserInterface.enterValidActionDisplay();
        }
      }
    }

    public static String getFullNameInputForCreateAccount() {

      System.out.println(
          cyan
              + (yellow
                  + "   *"
                  + cyan
                  + "                               ╔════════════════════════════════════════════╗"
                  + reset
                  + yellow
                  + "                                *"
                  + reset));

      System.out.print(
          (yellow + "   *" + cyan + "                               ║ ✏️  Full Name    : "));

      //      input.nextLine();
      return input.nextLine();
    }

    public static String getPasswordInputForCreateAccount() {

      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                               ║--------------------------------------------║"
              + reset
              + yellow
              + "                                *"
              + reset));
      System.out.print(
          (yellow + "   *" + cyan + "                               ║ 🔑  Password     : "));
      return input.nextLine();
    }

    public static int getAccountTypeInputForCreateAccount() {
      int accountType;
      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                               ║--------------------------------------------║"
              + reset
              + yellow
              + "                                *"
              + reset));
      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                               ║ 🏦  Select Account Type:                   ║"
              + reset
              + yellow
              + "                                *"
              + reset));
      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                               ║    [1] Saving Account                      ║"
              + reset
              + yellow
              + "                                *"
              + reset));
      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                               ║    [2] Current Account                     ║"
              + reset
              + yellow
              + "                                *"
              + reset));
      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                               ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                *"
              + reset));
      System.out.println(
          yellow
              + "   *"
              + reset
              + yellow
              + "                                                                                  "
              + "                           *"
              + reset);
      while (true) {
        System.out.print(
            (yellow
                + "   *"
                + cyan
                + "                                          ➡️  Select Option: "));

        String userInput = input.nextLine().trim();
        if (userInput.matches("\\d+")) {
          accountType = Integer.parseInt(userInput);
          if (accountType >= 1 && accountType <= 2) {
            return accountType;
          } else {
            Utility.UserInterface.enterValidActionDisplay();
          }
        } else {
          Utility.UserInterface.enterValidActionDisplay();
        }
      }
    }

    public static String getFullNameInputForLoginAccount() {

      System.out.println(
          cyan
              + (yellow
                  + "   *"
                  + cyan
                  + "                                ╔════════════════════════════════════════════╗"
                  + reset
                  + yellow
                  + "                               *"
                  + reset));
      System.out.print(
          (yellow
              + "   *"
              + cyan
              + "                                ║ ✏️  Full Name    : "
              + reset));

      //      input.nextLine();
      return input.nextLine();
    }

    public static String getPasswordInputForLoginAccount() {

      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                                ║--------------------------------------------║"
              + reset
              + yellow
              + "                               *"
              + reset));
      System.out.print(
          (yellow
              + "   *"
              + cyan
              + "                                ║ 🔑  Password     : "
              + yellow
              + reset));
      String password = input.nextLine();

      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                                ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                               *"
              + reset));

      return password;
    }

    public static int getBankServicesInput() {

      while (true) {
        System.out.print(
            reset
                + cyan
                + "                                                 ➡️  Enter Action: "
                + reset);

        String userInput = input.nextLine().trim();

        // Check if input is all digits (i.e., a valid positive integer)
        if (userInput.matches("\\d+")) {
          int action = Integer.parseInt(userInput);
          if (action > 0 && action <= 7) {
            return action;
          } else {
            UserInterface.enterValidActionDisplay();
          }
        } else {
          UserInterface.enterValidActionDisplay();
        }
      }
    }

    public static int getDepositAmountInput() {
      while (true) {
        System.out.print(
            green + "                                              Enter Deposit amount: " + reset);
        String userInput = input.nextLine().trim();
        if (userInput.matches("\\d+")) {
          int amount = Integer.parseInt(userInput);
          if (amount > 0) {
            return amount;
          } else {
            UserInterface.invalidDepositAmountDisplay();
          }
        } else {
          UserInterface.invalidDepositAmountDisplay();
        }
      }
    }

    public static int getWithdrawAmountInput() {

      while (true) {
        System.out.print(
            green
                + "                                             Enter withdraw ammount: "
                + reset);

        String userInput = input.nextLine().trim();
        if (userInput.matches("\\d+")) {
          int amount = Integer.parseInt(userInput);
          if (amount > 0) {
            return amount;
          } else {
            UserInterface.invalidDepositAmountDisplay();
          }
        } else {
          UserInterface.invalidDepositAmountDisplay();
        }
      }
    }

    public static String getAccountNoInputForSendMoney() {
      System.out.println(
          cyan
              + (yellow
                  + "   *"
                  + cyan
                  + "                               ╔════════════════════════════════════════════╗"
                  + reset
                  + yellow
                  + "                                *"
                  + reset));
      System.out.print(
          (yellow
              + "   *"
              + cyan
              + "                               ║ ✏️  Account No    : "
              + reset));

      String accountNo = input.nextLine();
      System.out.println(
          (yellow
              + "   *"
              + cyan
              + "                               ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                *"
              + reset));
      System.out.println(
          yellow
              + "   *"
              + reset
              + yellow
              + "                                                                                  "
              + "                           *"
              + reset);
      return accountNo;
    }

    public static int getAmountInputForSendMoney() {
      while (true) {

        System.out.println(
            yellow
                + "   *                                                                            "
                + "                                 *"
                + reset);
        System.out.print(
            green + "                                                 Enter Send Amount: " + reset);
        String userInput = input.nextLine().trim();
        System.out.println(
            yellow
                + "   *                                                                            "
                + "                                 *"
                + reset);
        if (userInput.matches("\\d+")) {
          int amount = Integer.parseInt(userInput);
          if (amount > 0) {
            return amount;
          } else {
            Utility.UserInterface.invalidDepositAmountDisplay();
          }
        } else {
          Utility.UserInterface.invalidDepositAmountDisplay();
        }
      }
    }

    public static char getInputForProcessSendMoney() {
      char process;
      do {
        System.out.print(
            green + "                                          Do You want to Process? (y/n): ");

        process = input.next().charAt(0);
        input.nextLine();
      } while ((process != 'y' && process != 'Y') && (process != 'n' && process != 'N'));

      System.out.print(reset);
      return process;
    }

    public static char getInputForProcessCreateAccount() {
      char process;
      do {
        System.out.print(
            green + "                                          Do You want to Process? (y/n): ");

        process = input.next().charAt(0);
        input.nextLine();
      } while (process != 'y' && process != 'n');

      System.out.print(reset);
      return process;
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
              + "                                            ╔═══════════════════╗"
              + reset
              + yellow
              + "                                            *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                            ║ 1. Login          ║"
              + reset
              + yellow
              + "                                            *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                            ║-------------------║"
              + reset
              + yellow
              + "                                            *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                            ║ 2. Create Account ║"
              + reset
              + yellow
              + "                                            *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                            ╚═══════════════════╝"
              + reset
              + yellow
              + "                                            *"
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
              + "                                ╔════════════════════════════════════════════╗"
              + yellow
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                                ║          ❌  Your Account Not Found        ║"
              + yellow
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                                ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                               *"
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
              + ("   *                                          ╔═══════════════════════╗"
                  + yellow
                  + "                                          *"));
      System.out.println(
          ("   *                                          ║  CREATE NEW ACCOUNT   ║"
              + yellow
              + "                                          *"));
      System.out.println(
          ("   *                                          ╚═══════════════════════╝"
              + yellow
              + "                                          *"
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
              + "                               ╔════════════════════════════════════════════╗"
              + reset
              + yellow
              + "                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                               ║     ✅ Account Created Successfully!       ║"
              + reset
              + yellow
              + "                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                               ║     🔹 Your Account No: "
              + bold
              + accountNo
              + reset
              + green
              + "          ║"
              + reset
              + yellow
              + "                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                               ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                *"
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
              + "   *                                                ╔═══════════╗"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *                                                ║"
              + bold
              + "   LOGIN   "
              + reset
              + yellow
              + "║"
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *                                                ╚═══════════╝"
              + reset
              + yellow
              + "                                                *"
              + reset);
    }

    public static void accountLoggedInSuccessfullyDisplay() {
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                ╔════════════════════════════════════════════╗"
              + yellow
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                ║   ✅  Your Account Logged In Successfully  ║"
              + yellow
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                               *"
              + reset);
    }

    public static void accountLoginFailedDisplay() {
      System.out.println(
          yellow
              + "   *"
              + red
              + "                                ╔════════════════════════════════════════════╗"
              + yellow
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                                ║           ❌  Login Failed, Try Again      ║"
              + yellow
              + "                               *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                                ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                               *"
              + reset);
    }

    public static void enterValidActionDisplay() {
      System.out.println(
          yellow
              + "   *"
              + red
              + "                               ╔════════════════════════════════════════════╗"
              + yellow
              + "                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                               ║           Enter a valid Action!            ║"
              + yellow
              + "                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                               ╚════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                                *"
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
              + "                                  ╔══════════════════════════════════════╗"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║            🏦 ACCOUNT MENU           ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ╠══════════════════════════════════════╣"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║  [1] 💰 Deposit                      ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║--------------------------------------║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║  [2] 💸 Withdraw                     ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║--------------------------------------║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║  [3] 📊 Check Balance                ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║--------------------------------------║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║  [4] "
              + green
              + "🔄"
              + reset
              + cyan
              + "Send Money                    ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║--------------------------------------║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║  [5] 🔄 Print Transaction History    ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║--------------------------------------║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║  [6] 🔄 Login to Another Account     ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║--------------------------------------║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ║  [7] "
              + red
              + "❌"
              + reset
              + cyan
              + " Exit                         ║"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                  ╚══════════════════════════════════════╝"
              + reset
              + yellow
              + "                                   *"
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
              + "                                                ╔═══════════╗"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                                ║"
              + bold
              + "  Deposit  "
              + "║"
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                                ╚═══════════╝"
              + reset
              + yellow
              + "                                                *"
              + reset);
    }

    public static void invalidDepositAmountDisplay() {

      System.out.println(
          yellow
              + ("   *"
                  + red
                  + "                                     ╔═══════════════════════════════╗"
                  + yellow
                  + "                                       *"));
      System.out.println(
          (yellow
              + "   *"
              + red
              + "                                     ║  Invalid Amount, Try Again!   ║"
              + yellow
              + "                                       *"));
      System.out.println(
          (yellow
              + "   *"
              + red
              + "                                     ╚═══════════════════════════════╝"
              + yellow
              + "                                       *"
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
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                              "
              + yellow
              + bold
              + "Deposit Receipt"
              + reset
              + green
              + "                      "
              + yellow
              + "                          *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Account Holder  : "
              + reset
              + "          "
              + formattedName
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Account Number  : "
              + reset
              + "          "
              + formattedAccNo
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Amount Deposited: "
              + reset
              + "          "
              + formattedAmount
              + yellow
              + "                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Date & Time     : "
              + reset
              + "          "
              + formattedDate
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "          "
              + "                    *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Your amount has been successfully deposited!"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          "
              + bold
              + "              Thank You for Banking with Us!"
              + reset
              + yellow
              + "                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
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
              + "                                                ╔═══════════╗"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                                ║"
              + bold
              + "  Withdraw "
              + "║"
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                                ╚═══════════╝"
              + reset
              + yellow
              + "                                                *"
              + reset);
    }

    public static void invalidWithdrawAmountDisplay() {
      System.out.println(
          yellow
              + "   *"
              + red
              + "              "
              + "      ╔═══════════════════════════════════════════════════════════════════╗"
              + yellow
              + "                    *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                    ║    Your balance is low, Please deposit some amount, Thank"
              + " you!    ║"
              + yellow
              + "                    *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "              "
              + "      ╚═══════════════════════════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                    *"
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
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                              "
              + yellow
              + bold
              + "Withdraw Receipt"
              + reset
              + green
              + "                      "
              + yellow
              + "                         *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Account Holder  : "
              + "          "
              + reset
              + formattedName
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Account Number  : "
              + "          "
              + reset
              + formattedAccNo
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Amount Withdrawn: "
              + "          "
              + reset
              + formattedAmount
              + yellow
              + "                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Date & Time     : "
              + "          "
              + reset
              + formattedDate
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Your amount has been successfully Withdrawn!"
              + reset
              + yellow
              + "                                   *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                          "
              + bold
              + "              Thank You for Banking with Us!"
              + reset
              + yellow
              + "                                       *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
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
              + "                                        ╔═══════════════════════════╗"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ║ "
              + bold
              + yellow
              + "     Account  Details"
              + reset
              + cyan
              + "     ║"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ╠═══════════════════════════╣"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ║ Name:    "
              + Name
              + reset
              + cyan
              + " ".repeat(namePadding)
              + "║"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ║---------------------------║"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ║ Acc.No:  "
              + AccountNo
              + reset
              + cyan
              + " ".repeat(accNoPadding)
              + " ║"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ║---------------------------║"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ║ Balance: "
              + Balance
              + reset
              + cyan
              + " ".repeat(balancePadding)
              + " ║"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                        ╚═══════════════════════════╝"
              + reset
              + yellow
              + "                                        *"
              + reset);
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
    }

    public static void sendMoneyDisplay() {
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);

      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                               ╔════════════╗"
              + reset
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                               ║"
              + bold
              + " Send Money "
              + "║"
              + yellow
              + "                                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + cyan
              + "                                               ╚════════════╝"
              + reset
              + yellow
              + "                                                *"
              + reset);
    }

    public static void sendMoneyReceiptDisplay(String Name, String AccountNo, int amount) {
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
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                              "
              + yellow
              + bold
              + "Sending money"
              + reset
              + green
              + "                      "
              + yellow
              + "                            *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + reset
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Account Holder  : "
              + "          "
              + reset
              + formattedName
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Account Number  : "
              + "          "
              + reset
              + formattedAccNo
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Sending Amount  : "
              + "          "
              + reset
              + formattedAmount
              + yellow
              + "                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                              Date & Time     : "
              + "          "
              + reset
              + formattedDate
              + yellow
              + "             *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                             "
              + divider
              + reset
              + yellow
              + "                              *"
              + reset);
      System.out.println(
          yellow
              + "   *                                                                              "
              + "                               *"
              + reset);
    }

    public static void sentMoneySuccessfullyDisplay() {
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                    ╔═════════════════════════════════╗"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                    ║   ✅ Money Sent Successfully    ║"
              + yellow
              + "                                      *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                                    ╚═════════════════════════════════╝"
              + reset
              + yellow
              + "                                      *"
              + reset);
    }

    public static void printTransactionHistory(
        ResultSet resultSet, String accountNo, String accountHolderName) {
      try {
        String folderName = "Transaction History";
        File folder = new File(folderName);
        if (!folder.exists()) {
          folder.mkdir(); // Create folder if it doesn't exist
        }

        // Define the file path inside the folder
        String filePath =
            folderName + File.separator + "Transaction-History(" + accountNo + ").pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        Font titleFont = new Font(Font.HELVETICA, 25, Font.BOLD);
        Paragraph heading = new Paragraph("Transaction History\n", titleFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);

        Font infoHeadingFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font infoFont = new Font(Font.HELVETICA, 16);
        Paragraph info =
            new Paragraph(
                "Account Holder: "
                    + accountHolderName
                    + "               Account No: "
                    + accountNo
                    + "\n\n",
                infoHeadingFont);
        info.setAlignment(Element.ALIGN_CENTER);
        document.add(info);

        PdfPTable table = new PdfPTable(6); // 6 columns
        table.setWidthPercentage(110);
        table.setWidths(new float[] {5, 5, 5, 5, 5, 5});

        Font headerFont = new Font(Font.HELVETICA, 10, Font.BOLD);

        String[] headers = {
          "Transaction-Type", "Amount", "Receiver-Account",
          "Receiver-Name", "Description", "Date & Time"
        };

        for (String title : headers) {
          PdfPCell headerCell = new PdfPCell(new Phrase(title, headerFont));
          headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
          headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(headerCell);
        }

        try {
          Font rowFont = new Font(Font.HELVETICA, 10);
          while (resultSet.next()) {
            String type = resultSet.getString("Transaction_Type");
            double amount = resultSet.getDouble("Amount");
            String receiverAccountNo = resultSet.getString("Receiver_Account_No");
            String receiverName = resultSet.getString("Receiver_Name"); // might be null
            String description = resultSet.getString("Description");
            String time = resultSet.getString("Time");

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(type, rowFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(amount), rowFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell =
                new PdfPCell(
                    new Phrase(receiverAccountNo != null ? receiverAccountNo : "Null", rowFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(receiverName != null ? receiverName : "Null", rowFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(description, rowFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(time, rowFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
          }
          document.add(table);
          document.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
      }
    }

    public static void printTransactionHistorySuccessfullyDisplay(String accountNo) {
      System.out.println(
          yellow
              + "   *"
              + green
              + "              "
              + "          ╔══════════════════════════════════════════════════════════╗"
              + yellow
              + "                         *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                        ║               Transaction History Printed!"
              + "               ║"
              + yellow
              + "                         *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "                        ║   TransactionHistory/Transaction_History("
              + accountNo
              + ")"
              + ".pdf"
              + "  ║"
              + yellow
              + "                         *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + green
              + "              "
              + "          ╚══════════════════════════════════════════════════════════╝"
              + reset
              + yellow
              + "                         *"
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
