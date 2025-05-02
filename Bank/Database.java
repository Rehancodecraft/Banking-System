package Bank;

import java.sql.*;

public class Database {
	static Connection connection;
	public static  void connectToDatabase(){
		try{
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
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("Error in database Connection");
			e.printStackTrace();
		}
	}
	private static void initializeDatabase() {
		try () {
			String createTableSQL = "CREATE TABLE IF NOT EXISTS Accounts (" +
					"Account_No VARCHAR(20) PRIMARY KEY, " +
					"AccountHolder_Name VARCHAR(50), " +
					"Account_Password VARCHAR(20), " +
					"Account_Type INT, " +
					"Balance INT);";
			PreparedStatement createStmt = connection.prepareStatement(createTableSQL);
			createStmt.executeUpdate();
			connection.commit();
			createStmt.close();
			
		} catch (Exception e) {
			System.out.println("Error initializing database.");
			e.printStackTrace();
		}
	}
	public static void depositToDatabase(int amount,String accountNo,String name){
		try{
			String sql = "UPDATE Accounts SET Balance = Balance + ? WHERE Account_No = ? AND AccountHolder_Name = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setString(2, accountNo);
			stmt.setString(3, name);
			stmt.executeUpdate();
			connection.commit();
			stmt.close();
			
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("Error in database deposit amount");
		}
		
	}
	public static void withdrawFromDatabase(int amount,String accountNo,String name){
		try{
			String sql = "UPDATE Accounts SET Balance = Balance - ? WHERE Account_No = ? AND AccountHolder_Name = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setString(2, accountNo);
			stmt.setString(3, name);
			stmt.executeUpdate();
			
			connection.commit();
			stmt.close();
			
		}
		catch (Exception e){
			System.out.println("Error in database deposit amount");
		}
	}
	
	
	public static void sendMoneyToDatabaseToOtherAccount(int amount,String receiverAccountNo,String receiverName,String senderAccountNo,String senderName){
							try (
									PreparedStatement sendStmt = connection.prepareStatement(
											"UPDATE Accounts SET Balance = Balance - ? WHERE Account_No = ? AND AccountHolder_Name = ?");
									PreparedStatement receiveStmt = connection.prepareStatement(
											"UPDATE Accounts SET Balance = Balance + ? WHERE Account_No = ? AND AccountHolder_Name = ?")
							) {
								sendStmt.setInt(1, amount);
								sendStmt.setString(2, senderAccountNo);
								sendStmt.setString(3, senderName);
								sendStmt.executeUpdate();
								
								receiveStmt.setInt(1, amount);
								receiveStmt.setString(2, receiverAccountNo);
								receiveStmt.setString(3, receiverName);
								receiveStmt.executeUpdate();
								connection.commit();
							} catch (SQLException e) {// Rollback on error
								System.out.println("Transaction failed, rolled back.");
								e.printStackTrace();
							}
							
		}
		
		
	public static String getNameOfReceiverFromDatabase(String receiverAccountNo){
		try {
			String getName = "SELECT AccountHolder_Name FROM Accounts WHERE Account_No = ?";
			PreparedStatement getNameStmt = connection.prepareStatement(getName);
			getNameStmt.setString(1, receiverAccountNo);
			ResultSet getResultOfName = getNameStmt.executeQuery();
			if (getResultOfName.next()) {
				return getResultOfName.getString("AccountHolder_Name");
			}
			else {
				return null;
			}
		}
		catch(SQLException e){
				System.out.println();
			}
		return null;
	}
	
	
	public static int checkBalanceOfAccountFromDatabase(String name,String accountNo){
		int balance = 0;
		try{
			String sql = "SELECT Balance FROM Accounts WHERE Account_No = ? AND AccountHolder_Name = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,accountNo);
			stmt.setString(2,name);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				balance = result.getInt("Balance");
			}
			connection.commit();
		}
		catch (Exception e){
			System.out.println("Error in database check balance");
			System.out.println(e.getMessage());
		}
		return balance;
	}
	public static ResultSet loginToAccountFromDatabase(String name,String password){
		
		try{
			String sql = "SELECT * FROM Accounts WHERE AccountHolder_Name = ? AND Account_Password = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,name);
			stmt.setString(2,password);
			ResultSet rs = stmt.executeQuery();
			connection.commit();
			return rs;
		}
		catch (Exception e){
			System.out.println("Error in database login account");
			
			e.printStackTrace();
		}
		return null;
	}
	public static void createAccountInDatabase(String account_no,String name,String password,int acct_type,int balance){
		try {
			if (connection != null) {
				// Insert the account details into the Accounts table
				String sql = "INSERT INTO Accounts (Account_No, AccountHolder_Name, Account_Password, Account_Type, Balance) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, account_no);
				stmt.setString(2, name);
				stmt.setString(3, password);
				stmt.setInt(4, acct_type);
				stmt.setInt(5, balance);
				
				stmt.executeUpdate();// Execute the query
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
}
