package Bank;

import static Bank.AccountManager.*;
import static Bank.AdminManager.*;
import static Bank.Utility.GetInputWithStyles.*;
import static Bank.Utility.UserInterface.*;

public class Main {
  public static void main(String[] args) {
    Database.connectToDatabase();
    askForLoginOrSignup();
  }
  public static void askForLoginOrSignup() {
    boolean isRunning = true;
    
    while (isRunning) {
      Utility.UserInterface.askForLoginOrSignupDisplay();
      
      int loginSignupOptionChosen = getInputForAskForLoginOrSignup();
      
      switch (loginSignupOptionChosen) {
        //OPTION 1: CASE OF LOGIN AS USER
        case 1:
          AccountManager loggedInAccount = logInAsUser();
          if (loggedInAccount != null) {
            accountLoggedInSuccessfullyDisplay();
            manageUserAccountServices(loggedInAccount);
            isRunning = false;
          } else {
            accountNotFoundErrorDisplay();
          }
          break;
        //OPTION 2: CASE OF LOGIN AS ADMIN
        case 2:
          if (loginAsAdmin()) {
            handleAdminServices();
          }else{
            accountNotFoundErrorDisplay();
          }
          
          break;
        //OPTION 3: CASE OF CREATE NEW ACCOUNT
        case 3:
          AccountManager account = createNewAccount();
          if (account != null) {
            manageUserAccountServices(account);
            isRunning = false;
          } else {
            System.out.println("Account creation failed. Please try again.");
          }
          break;
        
        default:
          enterValidActionErrorDisplay();
          break;
      }
    }
  }
  
}
