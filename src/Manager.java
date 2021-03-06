//Import Statements

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Manager {

    //Change this before running project
    private final String username = "username";
    private final String password = "password";

// Declare Manager ID
    private static int managerID;
    Scanner input = new Scanner(System.in);
    ArrayList<Integer> managerIDs = new ArrayList<Integer>();

    //Manager Constructor
    public Manager() {
        managerIDs.addAll(Arrays.asList(45030, 85130, 45131, 45332, 45433, 12933, 45534, 45635, 45736, 45837));
        boolean b = true;
        while (b) {

// Gets manager ID and stores it into variable
            System.out.print("\n > Please enter your 5 digit manager id: ");
            int mID = input.nextInt();
            this.managerID = mID;

            if (!managerIDs.contains(mID)) {
                System.out.print("\nIncorrect Manager ID. Please try again.\n");
            } else {
                b = false;
                Prompts();
            }

        }
        //Goes to Prompts function to display manager role options

    }

//Displays Manager options
    public void Prompts() {
        System.out.print("\nSelect an option from below: \n [1] Add Customer(please add an account before you do this) \n [2] Delete Customer \n [3] Add Account \n [4] Delete Account (please remove corresponding customer before you perform this action) \n [5] View Transactions Customer \n [0] Quit \n");
        System.out.print(" > My Choice: ");
        int choice = input.nextInt();

        //Switch case to determine which function to go to next
        switch (choice) {
            case 1:
                addCustomer();
            case 2:
                deleteCustomer();
            case 3:
                addAccount();
            case 4:
                deleteAccount();
            case 5:
                viewTransactions();
            case 0:
                System.exit(0);
            default:
                System.out.print("\nIncorrect Customer ID. Please try again.\n");
                Prompts();
        }
    }

    //Add customer to oracle dB given connection params and user input
    public void addCustomer() {
        boolean b = true;
        while (b) {
            System.out.println("\nTo add a customer input the following information (please remember to follow the proper data entry guidelines in brackets):");
            System.out.print(" > Customer ID (5 digits): ");
            int cID = input.nextInt();

            System.out.print(" > Account ID (5 digits): ");
            int aID = input.nextInt();

            System.out.print(" > Customer First Name (max. 50 characters): ");
            input.nextLine();
            String fN = input.nextLine();

            System.out.print(" > Customer Last Name (max. 50 characters): ");
            String lN = input.nextLine();

            System.out.print(" > Customer Address (max. 200 characters): ");
            String a = input.nextLine();

            System.out.print(" > Customer Phone Number (in the format 123-456-7890: ");
            String pN = input.nextLine();

            System.out.print(" > Customer age (in years): ");
            int age = input.nextInt();

            System.out.print(" > Customer E-mail (max. 200 characters): ");
            input.nextLine();
            String email = input.nextLine();

            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        username, password);
                Statement st = con.createStatement();
                String sql = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL) VALUES (?,?,?,?,?,?,?,?)";

                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, cID);
                preparedStmt.setInt(2, aID);
                preparedStmt.setString(3, fN);
                preparedStmt.setString(4, lN);
                preparedStmt.setString(5, a);
                preparedStmt.setString(6, pN);
                preparedStmt.setInt(7, age);
                preparedStmt.setString(8, email);

                preparedStmt.execute();
                System.out.println("**** Customer " + cID + " successfully added. **** ");
                con.close();

                b = false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //Goes back to main menu
        Prompts();
    }

    //Deletes customer from oracle dB given connection params and user input
    public void deleteCustomer() {
        boolean b = true;
        while (b) {
            System.out.print("\nTo remove a Customer, input the Customer ID of the Customer you want to delete. ");
            System.out.print("\n > Customer ID (5 digits): ");
            int cID = input.nextInt();

            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        username, password);
                Statement st = con.createStatement();
                String sql = "DELETE FROM Customer WHERE Customer_ID = ?";

                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, cID);

                preparedStmt.execute();
                System.out.println("\n **** Customer ID " + cID + " successfully deleted. ****");
                con.close();

                b = false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //Goes back to main menu
        Prompts();
    }

    //Adds account to oracle dB given connection params and user input
    public void addAccount() {
        boolean b = true;
        while (b) {
            System.out.println("\nTo add a Account, input the following information:");
            System.out.print(" > Account ID (5 digits): ");
            int aID = input.nextInt();

            System.out.print(" > Branch ID (5 digits): ");
            int bID = input.nextInt();

            System.out.print(" > Account Type: (Credit,Debit or Loan): ");
            input.nextLine();
            String aT = input.nextLine();

            System.out.print(" > Account Balance (without $): ");
            Double bal = input.nextDouble();

            System.out.print(" > Rate (max. 4 digits ex. 0.234): ");
            Double r = input.nextDouble();

            System.out.print(" > Status: ");
            input.nextLine();
            String s = input.nextLine();

            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        username, password);
                Statement st = con.createStatement();
                String sql = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status) VALUES (?,?,?,?,?,?)";

                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, aID);
                preparedStmt.setInt(2, bID);
                preparedStmt.setString(3, aT);
                preparedStmt.setDouble(4, bal);
                preparedStmt.setDouble(5, r);
                preparedStmt.setString(6, s);

                preparedStmt.execute();
                System.out.println(" **** Account ID " + aID + " successfully added. ****");
                con.close();

                b = false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //Goes back to main menu
        Prompts();
    }

    //Deletes account from oracle dB given connection params and user input
    public void deleteAccount() {
        boolean b = true;
        while (b) {
            System.out.print("\n> To add an Account, input the Account ID of the Account you want to delete: ");
            System.out.print("\n > Account ID (5 digits): ");
            int aID = input.nextInt();

            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        username, password);
                Statement st = con.createStatement();
                String sql = "DELETE FROM Accounts WHERE Account_ID = ?";

                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, aID);

                preparedStmt.execute();
                System.out.println("\n **** Account ID " + aID + " successfully deleted. ****");
                con.close();

                b = false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //Goes back to main menu
        Prompts();
    }

    // If transaction = null output all transaction
    // If transaction != null, show only transaction with specific transactionID
    public void viewTransactions() {
        boolean b = true;
        while (b) {
            System.out.print("\nTo view a Transaction enter the Transaction ID or enter 0 to see all Transactions ");
            System.out.print("\n > Transaction ID (5 digits): ");
            int tID = input.nextInt();
            if (tID == 0) {
                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                            username, password);
                    Statement st = con.createStatement();
                    String sql = "SELECT * FROM Transactions ";

                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        System.out.println(rs.getInt("Transaction_ID") + " " + rs.getInt("Account_ID_S") + " " + rs.getInt("Account_ID_R") + " " + rs.getDate("Transaction_TimeStamp")
                                + " " + rs.getInt("Loan_ID") + " " + rs.getString("Transaction_Name") + "" + rs.getInt("Manager_ID") + " " + rs.getString("Credit_Debit") + " " + rs.getDouble("Amount"));
                    }
                    con.close();

                    b = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                            username, password);
                    Statement st = con.createStatement();
                    String sql = "SELECT * FROM Transactions WHERE Transaction_ID = " + tID;

                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        System.out.println(rs.getInt("Transaction_ID") + " " + rs.getInt("Account_ID_S") + " " + rs.getInt("Account_ID_R") + " " + rs.getDate("Transaction_TimeStamp") + " " + rs.getTime("Transaction_TimeStamp")
                                + " " + rs.getInt("Loan_ID") + " " + rs.getString("Transaction_Name") + "" + rs.getInt("Manager_ID") + " " + rs.getString("Credit_Debit") + " " + rs.getFloat("Amount"));
                    }
                    con.close();

                    b = false;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }
        //Goes back to main menu
        Prompts();
    }

}
