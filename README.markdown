# Banking System Project

## Overview
This Banking System is a Java-based console application that simulates core banking operations. It allows users to create accounts, log in, perform transactions such as deposits, withdrawals, and money transfers to other users, and generate transaction receipts. The system now uses SQLite for persistent data storage, replacing the previous in-memory ArrayList implementation, ensuring data persists across sessions. The codebase has been optimized for better formatting and efficiency, with streamlined scripts for easy execution on Linux and Windows. This project is ideal for learning database integration, object-oriented programming, and user input handling in Java.

For a detailed walkthrough of how I built this project, including insights into OOP, SQLite integration, and console UI design, check out my article on Hashnode: [Crafting a Java Banking System: Lessons in OOP, SQLite, and Console UI Design](https://rehanshafiq.hashnode.dev/crafting-a-java-banking-system-lessons-in-oop-sqlite-and-console-ui-design).

## Features
- **User Authentication**:
  - Login for existing users with account credentials (full name and password).
  - Option to create a new account with basic user details.
- **Account Management**:
  - Create multiple accounts (savings or current) for a single user.
  - Persistent storage of account details using SQLite.
- **Transactions**:
  - Deposit funds into an account.
  - Withdraw funds from an account (with balance validation).
  - Send money to another user’s account.
  - Generate receipts for deposit, withdrawal, and transfer transactions.
- **Console-Based Interface**:
  - Simple and intuitive menu-driven interface for user interaction.

## Technologies Used
- **Programming Language**: Java
- **Database**: SQLite (via `sqlite-jdbc-3.42.0.0.jar`)
- **Environment**: Console-based application
- **Dependencies**: SQLite JDBC driver (`sqlite-jdbc-3.42.0.0.jar`)
- **Scripts**:
  - `run.sh` for Linux/macOS
  - `run.bat` for Windows

## Installation
### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
- **SQLite**: Included via `sqlite-jdbc-3.42.0.0.jar` in the `Libraries` folder
- **Operating System**: Linux, macOS, or Windows

### Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Rehancodecraft/Banking-System.git
   ```
2. **Navigate to the Project Directory**:
   ```bash
   cd Banking-System
   ```

#### For Linux/macOS Users
3. **Make the Run Script Executable**:
   ```bash
   chmod +x run.sh
   ```
4. **Run the Application**:
   ```bash
   ./run.sh
   ```

#### For Windows Users
3. **Run the Application**:
   - **Option 1**: Double-click `run.bat` or `bat` in File Explorer.
   - **Option 2**: Open a Command Prompt in the project directory and run:
     ```cmd
     run.bat
     ```

#### For IntelliJ IDEA Users (Windows or Other Platforms)
3. **Open the Project**:
   - Open IntelliJ IDEA and select `File → Open`.
   - Choose the `Banking-System` folder and click `OK`.
4. **Add SQLite JAR to Classpath**:
   - Go to `File → Project Structure → Modules`.
   - Select your module (likely named `Banking-System`).
   - Go to the `Dependencies` tab.
   - Click `+` → `JARs or directories...`.
   - Select `Libraries/sqlite-jdbc-3.42.0.0.jar`.
   - Choose `Compile` scope.
   - Click `OK` or `Apply` to save.
5. **Run the Application**:
   - Locate `Main.java` in the `Bank` folder.
   - Right-click and select `Run 'Main.main()'`.

## Usage
1. **Launching the Application**:
   Upon launching the application, the user is presented with two options:
   - **Login**: Enter credentials (full name and password) to access an existing account. If the account does not exist, an error message is displayed.
   - **Create Account**: Proceed to create a new account by providing required details.

      ![Account Not Found](Images/AccountNotFound.png)

2. **Creating a New Account**:
   After selecting the "Create Account" option, the user is prompted to:
   - **Enter Full Name**: Provide their full name for the account.
   - **Enter Password**: Set a password for account security.
   - **Select Account Type**: Choose between a savings account or a current account.
   - **Account Created Successfully**: Upon successful creation, a confirmation message is displayed with the generated account number.

      ![Create Account](Images/CreateAccount.png)

3. **Banking Services**:
   After account creation or login, the user can access the following services:
   - **Deposit Funds**:
     Select option 1 to deposit money. Enter the amount, and a deposit receipt is generated.

     ![Deposit](Images/Deposit.png)
   - **Withdraw Funds**:
     Select option 2 to withdraw money. If the withdrawal amount exceeds the deposited balance or no funds have been deposited, an error is displayed. Otherwise, the amount is withdrawn, and a receipt is generated.

     ![Withdraw Error](Images/WithdrawError.png)

     ![Withdraw](Images/Withdraw.png)
   - **Check Balance**:
       Select option 3 to view account details, including the remaining balance and other account information.

   ![Display](Images/Display.png)
   - **Send Money**:
     Select option 4 to transfer money to another user’s account. Enter the recipient’s account details and amount. If the balance is sufficient and the recipient exists, the transfer is completed, and a receipt is generated.
   ![Display](Images/sendMoney.png)
   
   - **Login to Another Account**:
     Select option 5, enter the full name and password of another account, and log in successfully if credentials are valid.

     ![Login](Images/loginToAnother.png)
4. **Exit the Application**:
   Select option 6 to exit the banking system.

   ![Display](Images/Exit.png)

## Project Structure
```
Banking-System/
├── Bank/
│   ├── BankAccount.java   # Manages account details and transactions
│   ├── Database.java      # Handles SQLite database operations
│   ├── Main.java          # Entry point with main menu and user interaction
│   └── Utility.java       # Contains helper classes for input handling and UI
├── Bank.db                # SQLite database file
├── Images/                # Screenshots for README
│   ├── AccountNotFound.png
│   ├── CreateAccount.png
│   ├── Deposit.png
│   ├── Display.png
│   ├── Exit.png
│   ├── Login.png
│   ├── WithdrawError.png
│   └── Withdraw.png
├── Libraries/
│   └── sqlite-jdbc-3.42.0.0.jar  # SQLite JDBC driver
├── out/                   # Compiled class files
│   └── Bank/
│       ├── BankAccount.class
│       ├── Database.class
│       ├── Main.class
│       ├── Utility$GetInputWithStyles.class
│       ├── Utility$UserInterface.class
│       └── Utility.class
├── README.md              # Project documentation
├── run.bat                # Windows script to run the application
└── run.sh                 # Linux/macOS script to run the application
```

## Limitations
- Basic console interface without a graphical user interface (GUI).
- Limited error handling for invalid inputs.
- No support for advanced banking features like interest calculation or loan management.
- Passwords are stored in plain text in the SQLite database (no encryption).

## Future Improvements
- Add a graphical user interface using JavaFX or Swing.
- Implement advanced features like:
  - Transaction history with timestamps.
  - Loan management.
  - Interest calculation for savings accounts.
- Enhance security with password encryption and input validation.
- Add support for multi-user sessions or concurrent transactions.

## Contributing
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit (`git commit -m "Add feature"`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Author
- Rehan Shafiq
- GitHub: [Rehancodecraft](https://github.com/Rehancodecraft)
