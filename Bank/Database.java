package Bank;


import java.io.File;
import java.sql.*;

public class Database {
  static Connection connection;

  public static void connectToDatabase() {
    try {
      final String DB_FILE = "Bank.db";
      final String URL = "jdbc:sqlite:" + DB_FILE;

      File dbFile = new File(DB_FILE);
      boolean isNew = !dbFile.exists(); // Check if database file exists

      connection = DriverManager.getConnection(URL);
      connection.setAutoCommit(false);
      if (isNew) {
        initializeDatabase(); // Create tables if needed
      }
      //        System.out.println("Connected to database");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Error in database Connection");
      e.printStackTrace();
    }
  }

  private static void initializeDatabase() {
    try {
      String createAccountsTableSQL =
          "CREATE TABLE IF NOT EXISTS Accounts ("
              + "Account_No VARCHAR(20) PRIMARY KEY, "
              + "AccountHolder_Name VARCHAR(50), "
              + "Account_Password VARCHAR(20), "
              + "Account_Type VARCHAR(50), "
              + "Balance INT,"
              + "CreationDate DATE DEFAULT (DATE('now')));";
      PreparedStatement createStmt = connection.prepareStatement(createAccountsTableSQL);
      createStmt.executeUpdate();
      connection.commit();
      createStmt.close();
      String createTransactionTable =
          "CREATE TABLE IF NOT EXISTS Transactions ("
              + "transactionID INTEGER PRIMARY KEY AUTOINCREMENT,"
              + "Account_No VARCHAR(20) NOT NULL,"
              + "Transaction_Type TEXT NOT NULL,"
              + "Amount REAL NOT NULL,\n"
              + "Receiver_Account_No VARCHAR(20),"
              + "Description TEXT,"
              + "Time DATETIME DEFAULT CURRENT_TIMESTAMP,"
              + "FOREIGN KEY (account_no) REFERENCES accounts(account_no),"
              + "FOREIGN KEY (receiver_account_no) REFERENCES accounts(account_no)"
              + ");";
      createStmt = connection.prepareStatement(createTransactionTable);
      createStmt.executeUpdate();
      connection.commit();
      createStmt.close();
      
      String createAdminTable =
              "CREATE TABLE IF NOT EXISTS admin (" +
                      "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                      "adminName VARCHAR(100), " +
                      "adminPassword VARCHAR(255));";
      createStmt = connection.prepareStatement(createAdminTable);
      createStmt.executeUpdate();
      connection.commit();
      createStmt.close();
      String insertAdmin = "INSERT OR IGNORE INTO admin (id, adminName, adminPassword) VALUES (1, 'admin', 'admin');";
      createStmt = connection.prepareStatement(insertAdmin);
      createStmt.executeUpdate();
      connection.commit();
      createStmt.close();
    } catch (Exception e) {
      System.out.println("Error initializing database.");
      e.printStackTrace();
    }
  }

  public static void saveDepositToDatabase(int amount, String accountNo, String name) {
    try {
      String sql =
          "UPDATE Accounts SET Balance = Balance + ? WHERE Account_No = ? AND AccountHolder_Name ="
              + " ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, amount);
      stmt.setString(2, accountNo);
      stmt.setString(3, name);
      stmt.executeUpdate();
      connection.commit();
      stmt.close();

    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Error in database deposit amount");
    }
  }

  public static void saveWithdrawInDatabase(int amount, String accountNo, String name) {
    try {
      String sql =
          "UPDATE Accounts SET Balance = Balance - ? WHERE Account_No = ? AND AccountHolder_Name ="
              + " ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, amount);
      stmt.setString(2, accountNo);
      stmt.setString(3, name);
      stmt.executeUpdate();

      connection.commit();
      stmt.close();

    } catch (Exception e) {
      System.out.println("Error in database deposit amount");
    }
  }

  public static void sendMoneyToDatabaseToOtherAccount(
      int amount,
      String receiverAccountNo,
      String receiverName,
      String senderAccountNo,
      String senderName) {
    try (PreparedStatement sendStmt =
            connection.prepareStatement(
                "UPDATE Accounts SET Balance = Balance - ? WHERE Account_No = ? AND"
                    + " AccountHolder_Name = ?");
        PreparedStatement receiveStmt =
            connection.prepareStatement(
                "UPDATE Accounts SET Balance = Balance + ? WHERE Account_No = ? AND"
                    + " AccountHolder_Name = ?")) {
      sendStmt.setInt(1, amount);
      sendStmt.setString(2, senderAccountNo);
      sendStmt.setString(3, senderName);
      sendStmt.executeUpdate();

      receiveStmt.setInt(1, amount);
      receiveStmt.setString(2, receiverAccountNo);
      receiveStmt.setString(3, receiverName);
      receiveStmt.executeUpdate();
      connection.commit();
    } catch (SQLException e) { // Rollback on error
      System.out.println("Transaction failed, rolled back.");
      e.printStackTrace();
    }
  }

  public static String getNameOfAccountNoFromDatabase(String receiverAccountNo) {
    try {
      String getName = "SELECT AccountHolder_Name FROM Accounts WHERE Account_No = ?";
      PreparedStatement getNameStmt = connection.prepareStatement(getName);
      getNameStmt.setString(1, receiverAccountNo);
      ResultSet getResultOfName = getNameStmt.executeQuery();
      if (getResultOfName.next()) {
        return getResultOfName.getString("AccountHolder_Name");
      } else {
        return null;
      }
    } catch (SQLException e) {
      System.out.println();
    }
    return null;
  }

  public static int checkBalanceOfAccountFromDatabase(String name, String accountNo) {
    int balance = 0;
    try {
      String sql = "SELECT Balance FROM Accounts WHERE Account_No = ? AND AccountHolder_Name = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, accountNo);
      stmt.setString(2, name);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        balance = result.getInt("Balance");
      }
      connection.commit();
    } catch (Exception e) {
      System.out.println("Error in database check balance");
      System.out.println(e.getMessage());
    }
    return balance;
  }

  public static ResultSet logInToAccountFromDatabase(String name, String password) {

    try {
      String sql = "SELECT * FROM Accounts WHERE AccountHolder_Name = ? AND Account_Password = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, name);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();
      connection.commit();
      return rs;
    } catch (Exception e) {
      System.out.println("Error in database login account");

      e.printStackTrace();
    }
    return null;
  }

  public static void saveNewAccountInDatabase(
      String account_no, String name, String password, String acct_type, double balance) {
    try {
      if (connection != null) {
        // Insert the account details into the Accounts table
        String sql =
            "INSERT INTO Accounts (Account_No, AccountHolder_Name, Account_Password, Account_Type,"
                + " Balance) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, account_no);
        stmt.setString(2, name);
        stmt.setString(3, password);
        stmt.setString(4, acct_type);
        stmt.setDouble(5, balance);

        stmt.executeUpdate(); // Execute the query
        stmt.close();
        connection.commit();
        Utility.UserInterface.accountCreatedSuccessfullyDisplay(account_no);
      } else {
        System.out.println("Error: Database connection failed!");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      System.out.println("Error in database while creating account");
      e.printStackTrace();
    }
  }

  public static void saveTransactionHistory(
      String accountNo,
      String transactionType,
      int amount,
      String receiverAccountNo,
      String description) {
    try {
      String sql =
          "INSERT INTO Transactions (Account_No, Transaction_Type, Amount, Receiver_Account_No,"
              + " Description) VALUES (?, ?, ?, ?,?);";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, accountNo);
      stmt.setString(2, transactionType);
      stmt.setDouble(3, amount);
      stmt.setString(4, receiverAccountNo);
      stmt.setString(5, description);
      stmt.executeUpdate();
      connection.commit();
      stmt.close();
    } catch (SQLException e) {
      System.out.println("Error in database while saving transaction");
      System.out.println(e.getMessage());
    }
  }

  public static void saveTransactionHistory(
      String accountNo, String transactionType, int amount, String description) {
    try {
      String sql =
          "INSERT INTO Transactions (Account_No, Transaction_Type, Amount, Receiver_Account_No,"
              + " Description) VALUES (?, ?, ?, ?,?);";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, accountNo);
      stmt.setString(2, transactionType);
      stmt.setInt(3, amount);
      stmt.setNull(4, java.sql.Types.INTEGER);
      stmt.setString(5, description);
      stmt.executeUpdate();
      connection.commit();
      stmt.close();
    } catch (SQLException e) {
      System.out.println("Error in database while saving transaction");
      System.out.println(e.getMessage());
    }
  }

  public static ResultSet getTransactionHistory(String accountNo) {
    ResultSet resultSet = null;
    try {
      String sql =
          "SELECT a.AccountHolder_Name, "
              + "t.Transaction_Type, t.Amount, t.Receiver_Account_No, "
              + "ra.AccountHolder_Name AS Receiver_Name, t.Description, t.Time "
              + "FROM Transactions t "
              + "JOIN Accounts a ON t.Account_No = a.Account_No "
              + "LEFT JOIN Accounts ra ON t.Receiver_Account_No = ra.Account_No "
              + "WHERE t.Account_No = ? "
              + "ORDER BY t.Time DESC";

      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, accountNo);
      resultSet = stmt.executeQuery();
      connection.commit();
      if (!resultSet.next()) {
        resultSet.close();
        return null;
      }else{
        return resultSet;
      }
      
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }
  public static boolean loginAsAdmin(String name, String password) {
    try {
      String sql = "SELECT * FROM admin WHERE adminName = ? AND adminPassword = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, name);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();
      connection.commit();
      if (rs.next()) {
        return true;
      }
      else {
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error in database login account");
      e.printStackTrace();
      return false;
    }
    
  }
  public static double getTotalBankBalance(){
    double total_balance = 0;
    try{
      String sql = "SELECT SUM(Balance) AS Total_Balance FROM Accounts";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      connection.commit();
      if (rs.next()) {
        total_balance =  rs.getInt("Total_Balance");
        return total_balance;
      }else{
        return total_balance;
      }
      
    }catch (SQLException e){
      System.out.println(e.getMessage());
      System.out.println("Error in database getTotalBankBalance");
      return total_balance;
    }
  }
  public static ResultSet getAllAccountsFromDatabase() {
    try {
      String sql = "SELECT * FROM Accounts";
      PreparedStatement stmt = connection.prepareStatement(sql);
      return stmt.executeQuery(); // Don't close stmt yet
    } catch (SQLException e) {
      System.out.println("Error fetching data: " + e.getMessage());
      return null;
    }
  }
  
  public static Boolean updateAccountInformation(String name,String password,String accountType,String accountNo){
    try {
      String sql = "UPDATE Accounts SET AccountHolder_Name = ?, Account_Password = ?, Account_Type = ? WHERE Account_No = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, name);
      stmt.setString(2, password);
      stmt.setString(3, accountType);
      stmt.setString(4, accountNo);
      stmt.executeUpdate();
      connection.commit();
      stmt.close();
      return true;
    }catch (SQLException e){
      System.out.println(e.getMessage());
      return false;
    }
  }
  public static ResultSet getInformationForDeleteAccount(String accountNo){
    try {
      String sql = "SELECT AccountHolder_Name,Balance FROM Accounts WHERE Account_No = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1,accountNo);
      ResultSet rs = stmt.executeQuery();
      connection.commit();
      if(rs.next()){
        return rs;
      }else{
        return null;
      }
    }catch (SQLException e){
      System.out.println(e.getMessage());
      return null;
    }
    
  }
  public static void deleteAccountFromDatabase(String accountNo){
    try {
      String sql = "DELETE FROM Accounts WHERE Account_No = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, accountNo);
      stmt.executeUpdate();
      connection.commit();
    }catch (SQLException e){
      System.out.println(e.getMessage());
    }
  }
}
