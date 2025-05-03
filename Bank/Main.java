package Bank;

public class Main {
  public static void main(String[] args) {
    // creating object of class BankAccount
    Database.connectToDatabase();
    BankAccount Bank1 = new BankAccount();
    Bank1.askForLoginAndSignup();
  }
}
