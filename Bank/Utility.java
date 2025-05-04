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
          if (action >= 1 && action <= 3) {
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

    public static String getAccountTypeInputForCreateAccount() {
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
          int accountTypeInput = Integer.parseInt(userInput);
          if (accountTypeInput >= 1 && accountTypeInput <= 2) {
            if(accountTypeInput == 1) {
              return "Saving Account";
            }else if(accountTypeInput == 2) {
              return "Current Account";
            }
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

    public static char getInputForProcess() {
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
    public static int getAdminServicesInput() {
      
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
    public static String getAccountNoInput() {
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
    public static String getFullNameInputForUpdateAccountInformation() {
      
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
    
    public static String getPasswordInputForUpdateAccountInformation() {
      
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
    
    public static String getAccountTypeInputForUpdateAccountInformation() {
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
          int accountTypeInput = Integer.parseInt(userInput);
          if (accountTypeInput >= 1 && accountTypeInput <= 2) {
            if(accountTypeInput == 1) {
              return "Saving Account";
            }else if(accountTypeInput == 2) {
              return "Current Account";
            }
          } else {
            Utility.UserInterface.enterValidActionDisplay();
          }
        } else {
          Utility.UserInterface.enterValidActionDisplay();
        }
      }
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
              + "                                            ║ 1. Login As User  ║"
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
                      + "                                            ║ 2. Login As Admin ║"
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
              + "                                            ║ 3. Create Account ║"
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
              + "                               ╔════════════════════════════════════════════╗"
              + yellow
              + "                                *"
              + reset);
      System.out.println(
          yellow
              + "   *"
              + red
              + "                               ║          ❌  Your Account Not Found        ║"
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
              + "                        ║ TransactionHistory/Transaction_History("
              + accountNo
              + ")"
              + ".pdf"
              + "    ║"
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
    public static void loginAsAdminDisplay() {
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
                      + "   *                                            ╔════════════════════╗"
                      + reset
                      + yellow
                      + "                                           *"
                      + reset);
      System.out.println(
              yellow
                      + "   *                                            ║"
                      + bold
                      + "   LOGIN AS ADMIN   "
                      + reset
                      + yellow
                      + "║"
                      + yellow
                      + "                                           *"
                      + reset);
      System.out.println(
              yellow
                      + "   *                                            ╚════════════════════╝"
                      + reset
                      + yellow
                      + "                                           *"
                      + reset);
    }
    public static void adminServicesDisplay() {
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
                      + "                                  ║  [1] 💰 Check Total Balance          ║"
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
                      + "                                  ║  [2] 🔄 Print All Accounts           ║"
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
                      + "                                  ║  [3] 💸 Print Transaction History    ║"
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
                      + "                                  ║  [4] 🔄 Update Account Information   ║"
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
                      + "                                  ║  [5] 📊 Delete Account               ║"
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
                      + "                                  ║  [6] "
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
    public static void totalBankBalanceDisplay( double Balance) {
      int boxWidth = 25;
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
                      + "      CMD Bank Ltd."
                      + reset
                      + cyan
                      + "       ║"
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
    public static void printAllAccountsSuccessfullyDisplay() {
      System.out.println(
              yellow
                      + "   *"
                      + green
                      + "                                    "
                      + "╔════════════════════════════════╗"
                      + yellow
                      + "                                       *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + green
                      + "                                    ║     All Accounts Printed!"
                      + "      ║"
                      + yellow
                      + "                                       *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + green
                      + "                                    ║   Accounts/All-Accounts.pdf"
                      + "    ║"
                      + yellow
                      + "                                       *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + green
                      + "                                    "
                      + "╚════════════════════════════════╝"
                      + reset
                      + yellow
                      + "                                       *"
                      + reset);
    }
    public static void noAccountFoundDisplay() {
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
                      + "                                ║       ❌ There are NO Accounts in Bank     ║"
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
    public static void accountUpdatedSuccessfullyDisplay() {
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
                      + "                                ║    ✅  Your Account Updated Successfully   ║"
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
    public static void updateAccountInformationDisplay() {
      System.out.println(
              yellow
                      + "   *                                                                              "
                      + "                               *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                         ╔═══════════════════════════╗"
                      + reset
                      + yellow
                      + "                                       *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                         ║"
                      + bold
                      + "Update Account Information"
                      + " ║"
                      + yellow
                      + "                                       *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                         ╚═══════════════════════════╝"
                      + reset
                      + yellow
                      + "                                       *"
                      + reset);
    }
    
    public static void deleteAccountDisplay() {
      System.out.println(
              yellow
                      + "   *                                                                              "
                      + "                               *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                              ╔══════════════╗"
                      + reset
                      + yellow
                      + "                                               *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                              ║"
                      + bold
                      + "Delete Account"
                      + "║"
                      + yellow
                      + "                                               *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                              ╚══════════════╝"
                      + reset
                      + yellow
                      + "                                               *"
                      + reset);
    }
    public static void printAllAccountsDisplay() {
      System.out.println(
              yellow
                      + "   *                                                                              "
                      + "                               *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                              ╔══════════════════╗"
                      + reset
                      + yellow
                      + "                                           *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                              ║"
                      + bold
                      + "Print All Accounts"
                      + "║"
                      + yellow
                      + "                                           *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                              ╚══════════════════╝"
                      + reset
                      + yellow
                      + "                                           *"
                      + reset);
    }
    public static void printTransactionHistoryDisplay() {
      System.out.println(
              yellow
                      + "   *                                                                              "
                      + "                               *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                        ╔═════════════════════════╗"
                      + reset
                      + yellow
                      + "                                          *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                        ║"
                      + bold
                      + "Print Transaction History"
                      + "║"
                      + yellow
                      + "                                          *"
                      + reset);
      System.out.println(
              yellow
                      + "   *"
                      + cyan
                      + "                                        ╚═════════════════════════╝"
                      + reset
                      + yellow
                      + "                                          *"
                      + reset);
    }
    public static void deleteAccountReceiptDisplay(String Name, String AccountNo,double balance) {
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      String formattedDateTime = now.format(formatter);
      final int totalWidth = 50;
      final int leftPadding = 26;
      final int valueWidth = totalWidth - 12; // After "Account Holder  : "
      
      // Format fields with consistent padding
      String formattedName = String.format("%-" + valueWidth + "s", Name);
      String formattedAccNo = String.format("%-" + valueWidth + "s", AccountNo);
      String formattedAmount = String.format("%-" + (valueWidth - 3) + "s", "Rs." + balance);
      
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
                      + "                                            "
                      + yellow
                      + bold
                      + "Deleting Account"
                      + reset
                      + green
                      + "                      "
                      + yellow
                      + "                           *"
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
                      + "                              Total Balance  :  "
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
    public static void accountDeletedSuccessfullyDisplay() {
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
                      + "                                ║    ✅  Your Account Deleted Successfully   ║"
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
