
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {

    //Change this before running project
    private final String username = "username";
    private final String password = "password";

    Scanner input = new Scanner(System.in);

    public Admin() {
        boolean b = true;
        while (b) {

            System.out.print("\n > Please enter your username: ");
            String u = input.nextLine();
            System.out.print(" > Please enter your password: ");
            String p = input.nextLine();

            if (u.equals("admin") && p.equals("admin")) {
                Prompts();
                b = false;

            } else {
                System.out.print("\nIncorrect credentials. Please try again. \n");
                b = true;
            }
        }
    }

    public void Prompts() {
        System.out.print("\nSelect an option from below: \n [1] Create Tables \n [2] Populate Tables \n [3] Drop Tables  \n [0] Quit \n");
        System.out.print(" > My Choice: ");
        int choice = input.nextInt();

        //Switch case to determine which function to go to next
        switch (choice) {
            case 1:
                createTables();
            case 2:
                populateTables();
            case 3:
                dropTables();
            case 0:
                System.exit(0);
            default:
                System.out.print("\nPlease enter a vaild a choice.\n");
                Prompts();
        }
    }

    public void createTables() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                    username, password);
            Statement st = con.createStatement();
            String sql1 = "CREATE TABLE BANK(Bank_ID NUMBER (5) PRIMARY KEY,Bank_Name VARCHAR(50),Address VARCHAR(200))";
            String sql2 = "CREATE TABLE BankBranch(Branch_ID NUMBER(5) PRIMARY KEY,Bank_ID NUMBER(5) REFERENCES Bank(Bank_ID),Branch_Name VARCHAR(30),"
                    + "Branch_City VARCHAR(30), Address VARCHAR2(200),Phone_Number VARCHAR(15))";
            String sql3 = "CREATE TABLE Accounts(Account_ID NUMBER(5) PRIMARY KEY,Branch_ID NUMBER(5) References BankBranch(Branch_ID), Account_Type VARCHAR(8),"
                    + "Balance FLOAT(15),Rate FLOAT(4),Status VARCHAR(20))";
            String sql4 = "CREATE TABLE Customer(Customer_ID NUMBER(5) PRIMARY KEY,Account_ID  NUMBER(5) References Accounts(Account_ID), First_Name VARCHAR(50),"
                    + "Last_Name VARCHAR(50),Address  VARCHAR(200), Phone_Number VARCHAR(15), Age NUMBER(3),Email VARCHAR(200))";
            String sql5 = "CREATE TABLE Employee(Employee_ID NUMBER(5) PRIMARY KEY,Branch_ID NUMBER(5) REFERENCES BankBranch(Branch_ID), First_Name VARCHAR2(50),"
                    + "Last_Name VARCHAR2(50),Phone_Number VARCHAR(15),Address VARCHAR2(200),Salary FLOAT(8),Emp_City VARCHAR(20))";
            String sql6 = "CREATE TABLE BankTeller (Teller_ID NUMBER(5) PRIMARY KEY,Employee_ID NUMBER(5) REFERENCES Employee(Employee_ID))";
            String sql7 = "CREATE TABLE BranchManager (Manager_ID NUMBER(5) PRIMARY KEY,Employee_ID NUMBER(5) REFERENCES Employee(Employee_ID),"
                    + " Teller_ID Number(5) REFERENCES BankTeller(Teller_ID))";
            String sql8 = "CREATE TABLE Loans(Loan_ID NUMBER(5) Primary Key, Duration_Yr NUMBER(3),Interest_Rate FLOAT(4), Start_Date DATE,Loan_Type VARCHAR(100),"
                    + "Amount FLOAT(7),Loan_TimeStamp  TIMESTAMP WITH LOCAL TIME ZONE)";
            String sql9 = "CREATE TABLE TransactionType(Transaction_Type VARCHAR(10),Transaction_Name VARCHAR2(25) Primary Key,Transaction_Description  VARCHAR(300))";
            String sql10 = "CREATE TABLE Transactions(Transaction_ID  NUMBER(5),Account_ID_S  NUMBER(5),Account_ID_R  NUMBER(5),Transaction_TimeStamp TIMESTAMP WITH LOCAL TIME ZONE,\n"
                    + "Loan_ID  NUMBER(5),Transaction_Name  CHAR(10),Manager_ID  NUMBER(5),Credit_Debit  CHAR(6),Amount FLOAT(15),PRIMARY KEY (Transaction_ID,  Transaction_Name , Loan_ID)\n"
                    + ")";

            PreparedStatement preparedStmt1 = con.prepareStatement(sql1);
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            PreparedStatement preparedStmt3 = con.prepareStatement(sql3);
            PreparedStatement preparedStmt4 = con.prepareStatement(sql4);
            PreparedStatement preparedStmt5 = con.prepareStatement(sql5);
            PreparedStatement preparedStmt6 = con.prepareStatement(sql6);
            PreparedStatement preparedStmt7 = con.prepareStatement(sql7);
            PreparedStatement preparedStmt8 = con.prepareStatement(sql8);
            PreparedStatement preparedStmt9 = con.prepareStatement(sql9);
            PreparedStatement preparedStmt10 = con.prepareStatement(sql10);

            preparedStmt1.execute();
            preparedStmt2.execute();
            preparedStmt3.execute();
            preparedStmt4.execute();
            preparedStmt5.execute();
            preparedStmt6.execute();
            preparedStmt7.execute();
            preparedStmt8.execute();
            preparedStmt9.execute();
            preparedStmt10.execute();

            System.out.println("Tables Successfully Created ");
            con.close();

        } catch (Exception e) {

        }
        Prompts();
    }

    public void populateTables() {
        Prompts();
    }

    public void dropTables() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                    username, password);
            Statement st = con.createStatement();
            String sql1 = "DROP TABLE Transactions PURGE";
            String sql2 = "DROP TABLE Loans PURGE";
            String sql3 = "DROP TABLE TransactionType PURGE";
            String sql4 = "Drop TABLE BranchManager PURGE";
            String sql5 = "DROP TABLE BankTeller PURGE";
            String sql6 = "DROP TABLE Employee PURGE";
            String sql7 = "DROP TABLE Customer PURGE";
            String sql8 = "DROP TABLE Accounts PURGE";
            String sql9 = "DROP TABLE BankBranch PURGE";
            String sql10 = "DROP TABLE Bank PURGE";

            PreparedStatement preparedStmt1 = con.prepareStatement(sql1);
            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            PreparedStatement preparedStmt3 = con.prepareStatement(sql3);
            PreparedStatement preparedStmt4 = con.prepareStatement(sql4);
            PreparedStatement preparedStmt5 = con.prepareStatement(sql5);
            PreparedStatement preparedStmt6 = con.prepareStatement(sql6);
            PreparedStatement preparedStmt7 = con.prepareStatement(sql7);
            PreparedStatement preparedStmt8 = con.prepareStatement(sql8);
            PreparedStatement preparedStmt9 = con.prepareStatement(sql9);
            PreparedStatement preparedStmt10 = con.prepareStatement(sql10);

            preparedStmt1.execute();
            preparedStmt2.execute();
            preparedStmt3.execute();
            preparedStmt4.execute();
            preparedStmt5.execute();
            preparedStmt6.execute();
            preparedStmt7.execute();
            preparedStmt8.execute();
            preparedStmt9.execute();
            preparedStmt10.execute();

            System.out.println("Tables Successfully Dropped ");
            con.close();

        } catch (Exception e) {
            //System.out.println(e);
        }
        Prompts();
    }

}
