# Banking System Project

## Overview
This Banking System is a Java-based console application that simulates basic banking operations. It allows users to create accounts, log in, perform transactions such as deposits and withdrawals, and generate transaction receipts. The system uses ArrayLists for in-memory data storage, making it a lightweight project without a database dependency. This project is ideal for learning fundamental programming concepts like object-oriented programming, data structures, and user input handling.

## Features
- **User Authentication**:
  - Login for existing users with account credentials.
  - Option to create a new account with basic user details.
- **Account Management**:
  - Create multiple accounts for a single user.
  - Store account details using ArrayLists.
- **Transactions**:
  - Deposit funds into an account.
  - Withdraw funds from an account (with balance validation).
  - Generate receipts for deposit and withdrawal transactions.
- **Console-Based Interface**:
  - Simple and intuitive menu-driven interface for user interaction.

## Technologies Used
- **Programming Language**: Java
- **Data Structure**: ArrayList (for in-memory storage of user and account data)
- **Environment**: Console-based application

## Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Rehancodecraft/Banking-System.git
   ```
2. **Navigate to the Project Directory**:
   ```bash
   cd Banking-System
   ```
3. **Compile the Java Files**:
   ```bash
   javac Bank/Main.java
   ```
4. **Run the Application**:
   ```bash
   java Bank/Main
   ```

## Usage
1. Launch the application using the command above.
2. Choose from the main menu options:
   - **Login**: Enter credentials to access an existing account.
   - **Create Account**: Provide details to create a new account.
   - **Exit**: Close the application.
3. After logging in, select from transaction options:
   - Deposit funds.
   - Withdraw funds.
   - View transaction receipts.
   - Create additional accounts.
4. Follow the prompts to complete your actions.

## Project Structure
- `Main.java`: Entry point of the application, contains the main menu and user interaction logic.
- `BankAccount.java`: Class to manage account details and transactions.

## Limitations
- Data is stored in-memory using ArrayLists, so all data is lost when the application closes.
- No persistent database integration.
- Basic console interface without graphical user interface (GUI).
- Limited error handling for invalid inputs.

## Future Improvements
- Integrate a database (e.g., MySQL or SQLite) for persistent storage.
- Add a graphical user interface using JavaFX or Swing.
- Implement advanced features like:
  - Transaction history with timestamps.
  - Loan management.
  - Interest calculation.
- Enhance security with password encryption.

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