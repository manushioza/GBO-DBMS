/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Krish DS
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Admin {

    //Change this before running project
    private final String username = "ksutar";
    private final String password = "09119744";

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
            System.out.println(e);
        }
        //Goes back to main menu
        Prompts();
    }

    public void populateTables() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",username, password);
            Statement st = con.createStatement();
            String sql1 = "INSERT INTO BANK(Bank_ID,Bank_Name, Address) VALUES(12930, 'Greater Bank of Ontario (GBO)', '755 Oxford Street, Ottawa, Ontario K1A 0J6')";
            
            String sql2 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number)"+
                    " VALUES(12930, 12930, 'GBO Ottawa','Ottawa','3755 Oxford Street, Ottawa, Ontario K1A 0J6', '613-325-1129')";
            String sql3 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number) "
                    + "VALUES(12931, 12930, 'GBO Toronto','Toronto','438 Conway St Toronto, Ontario(ON), M2N 2G8','416-333-1212')";
            String sql4 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number)"
                    + "VALUES(12932, 12930, 'GBO Barrie','Barrie', '25 Lovely Drive, Barrie, Ontario(ON), L4M 3B1','705-211-7337')";
            String sql5 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number) "
                    + "VALUES(12933, 12930, 'GBO Hamilton','Hamilton','1881 Charlie Ave, Hamilton, Ontario(ON), L8N 3X3','905-887-3443')";
            String sql6 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number) "
                    + "VALUES(12934, 12930, 'GBO Missisagua','Missisaugua','3504 Crely Dr, Missisauga, Ontario(ON), N7T 7T9','905-381-2020')";
            String sql7 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number) "
                    + "VALUES(12935, 12930, 'GBO Milton','Milton','3047 Takeoff Rd, Milton, Ontario(ON), L9T 1P4','905-340-8889')";
            String sql8 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number) "
                    + "VALUES(12936, 12930, 'GBO Kingston','Kingston','4149 Jamison, Kingston, Ontario(ON), P0L 1N0','705-345-2020')";
            String sql9 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number) "
                    + "VALUES(12937, 12930, 'GBO London', 'London','722 Fairway Street, London, Ontario(ON), N0N 0N0','519-823-5968')"; 
            
            String sql10 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(34929, 12932, 'Credit', 67575.63, 0.428, 'Active')";
            String sql11 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(24557, 12937, 'Credit', 40297.60 ,0.412, 'Active')";
            String sql12 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(78660, 12936, 'Credit', 89425.46, 0.101, 'Active')";
            String sql13 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(50741, 12930, 'Credit', 97375.32, 0.584, 'Active')";
            String sql14 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(70170, 12933, 'Credit', 37237.14, 0.495, 'Active')";
            String sql15 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(11237, 12935, 'Credit', 76519.91, 0.18, 'Active')";
            String sql16 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(26256, 12936, 'Credit', 8314.34, 0.261, 'Active')";
            String sql17 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(36766, 12934, 'Credit', 40927.82, 0.371, 'Active')";
            String sql18 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(13433, 12935, 'Credit', 77820.46, 0.468, 'Active')";
            String sql19 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(14436, 12936, 'Credit', 34296.41, 0.681, 'Active')";
            String sql20 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(37285, 12930, 'Credit', 45419.84, 0.139, 'Active')";
            String sql21 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(81797, 12931, 'Credit', 9868.58, 0.751, 'Active')";
            String sql22 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(11935, 12933, 'Credit', 91480.63, 0.963, 'Active')";
            String sql23 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(17668, 12935, 'Debit', 50614.29, 0.401, 'Active')";
            String sql24 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(56677, 12937, 'Debit', 40504.43, 0.555, 'Active')";
            String sql25 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(47203, 12935, 'Debit', 13506.33, 0.355, 'Active')";
            String sql26 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(18033, 12934, 'Debit', 46565.88, 0.51, 'Active')";
            String sql27 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(46774, 12930, 'Debit', 43305.99, 0.519, 'Active')";
            String sql28 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(93018, 12937, 'Debit', 48096.09, 0.398, 'Active')";
            String sql29 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(45690, 12932, 'Debit', 37436.61, 0.664, 'Active')";
            String sql30 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(88899, 12930, 'Debit', 41201.47, 0.606, 'Active')";
            String sql31 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(10736, 12934, 'Debit', 41201.47, 0.599, 'Active')";
            String sql32 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(75639, 12934, 'Debit', 91574.95, 0.365, 'Active')";
            String sql33 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(87672, 12934, 'Debit', 17151.82, 0.353, 'Active')";
            String sql34 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(62015, 12936, 'Debit', 46547.69, 0.076, 'Active')";
            String sql35 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(82293, 12935, 'Debit', 25095.60, 0.694, 'Active')";
            String sql36 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(77787, 12932, 'Debit', 25000.04, 0.561, 'Active')";
            String sql37 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(16557, 12934, 'Debit', 78637.06, 0.703, 'Active')";
            String sql38 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(38470, 12934, 'Debit', 49893.21, 0.356, 'Active')";
            String sql39 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(81556, 12931, 'Debit', 21199.94, 0.639, 'Active')";
            String sql40 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(84641, 12931, 'Debit', 95946.15, 0.482, 'Active')";
            String sql41 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(59417, 12930, 'Loan', 75506.17, 0.699, 'Active')";
            String sql42 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(95581, 12935, 'Loan', 71856.78, 0.081, 'Active')";
            String sql43 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(57656, 12935, 'Loan', 81002.86, 0.385, 'Active')";
            String sql44 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(89577, 12934, 'Loan', 1149.26, 0.123, 'Active')";
            String sql45 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(85789, 12931, 'Loan', 75501.70, 0.454, 'Active')";
            String sql46 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(29993, 12931, 'Loan', 9847.12, 0.166, 'Active')";
            String sql47 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(91770, 12937, 'Loan', 48098.98, 0.5, 'Active')";
            String sql48 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(28024, 12935, 'Loan', 79055.06, 0.591, 'Active')";
            String sql49 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(79577, 12935, 'Loan', 18548.64, 0.662, 'Active')";
            String sql50 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(18242, 12937, 'Loan', 47778.54, 0.738, 'Active')";
            String sql51 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(99891, 12930, 'Loan', 70262.49, 0.753, 'Active')";
            String sql52 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(19501, 12934, 'Loan', 50014.95, 0.357, 'Active')";
            String sql53 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(67906, 12936, 'Loan', 5675.37, 0.534, 'Active')";
            String sql54 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(12698, 12935, 'Loan', 52642.15, 0.531, 'Active')";
            String sql55 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(26855, 12933, 'Loan', 3499.15, 0.878, 'Active')";
            String sql56 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(71894, 12932, 'Loan', 81807.44, 0.092, 'Active')";
            String sql57 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(36328, 12937, 'Loan', 30259.94, 0.492, 'Active')";
            String sql58 = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status)"
                    + "VALUES(42979, 12930, 'Loan', 65170.29, 0.285, 'Active')";
            
            
            String sql59 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85230,76530)";
            String sql60 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85330,14330)";
            String sql61 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85530,85230)";
            String sql62 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85630,66630)";
            String sql63 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85731,09731)";
            String sql64 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85831,23031)";
            String sql65 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85932,20332)";
            String sql66 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86032,59032)";
            String sql67 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86133,05133)";
            String sql68 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86234,40534)";
            String sql69 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86334,92434)";
            String sql70 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86435,56935)";
            String sql71 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86536,06536)";
            String sql72 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86636,90336)";
            String sql73 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86737,15637)";
            
            String sql74 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45030,12330,85230))";
            String sql75 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (85130,34530,85330)";
            String sql76 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45131,84331,85731)";
            String sql77 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45332,67432,85932)";
            String sql78 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45433,83533,86032)";
            String sql79 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (12933,98533,86133)";
            String sql80 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45534,58934,86234)";
            String sql81 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45635,63635,86435)";
            String sql82 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45736,07736,86536)";
            String sql83 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45837,29837,86737)";
            
            String sql84 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Credit','Withdraw','Withdraw Money out of a bank account')";
            String sql85 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Debit','Deposit','Deposit Money into a bank account')";
            String sql86 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Debit','Send','Send money to an account')";
            String sql87 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Credit','Pay Bills','Pay credit card bills')";
            
            
            
            
            
            

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
            
            PreparedStatement preparedStmt11 = con.prepareStatement(sql11);
            PreparedStatement preparedStmt12 = con.prepareStatement(sql12);
            PreparedStatement preparedStmt13 = con.prepareStatement(sql13);
            PreparedStatement preparedStmt14 = con.prepareStatement(sql14);
            PreparedStatement preparedStmt15 = con.prepareStatement(sql15);
            PreparedStatement preparedStmt16 = con.prepareStatement(sql16);
            PreparedStatement preparedStmt17 = con.prepareStatement(sql17);
            PreparedStatement preparedStmt18 = con.prepareStatement(sql18);
            PreparedStatement preparedStmt19 = con.prepareStatement(sql19);
            PreparedStatement preparedStmt20 = con.prepareStatement(sql20);
            
            PreparedStatement preparedStmt21 = con.prepareStatement(sql21);
            PreparedStatement preparedStmt22 = con.prepareStatement(sql22);
            PreparedStatement preparedStmt23 = con.prepareStatement(sql23);
            PreparedStatement preparedStmt24 = con.prepareStatement(sql24);
            PreparedStatement preparedStmt25 = con.prepareStatement(sql25);
            PreparedStatement preparedStmt26 = con.prepareStatement(sql26);
            PreparedStatement preparedStmt27 = con.prepareStatement(sql27);
            PreparedStatement preparedStmt28 = con.prepareStatement(sql28);
            PreparedStatement preparedStmt29 = con.prepareStatement(sql29);
            PreparedStatement preparedStmt30 = con.prepareStatement(sql30);
            
            PreparedStatement preparedStmt31 = con.prepareStatement(sql31);
            PreparedStatement preparedStmt32 = con.prepareStatement(sql32);
            PreparedStatement preparedStmt33 = con.prepareStatement(sql33);
            PreparedStatement preparedStmt34 = con.prepareStatement(sql34);
            PreparedStatement preparedStmt35 = con.prepareStatement(sql35);
            PreparedStatement preparedStmt36 = con.prepareStatement(sql36);
            PreparedStatement preparedStmt37 = con.prepareStatement(sql37);
            PreparedStatement preparedStmt38 = con.prepareStatement(sql38);
            PreparedStatement preparedStmt39 = con.prepareStatement(sql39);
            PreparedStatement preparedStmt40 = con.prepareStatement(sql40);
            
            PreparedStatement preparedStmt41 = con.prepareStatement(sql41);
            PreparedStatement preparedStmt42 = con.prepareStatement(sql42);
            PreparedStatement preparedStmt43 = con.prepareStatement(sql43);
            PreparedStatement preparedStmt44 = con.prepareStatement(sql44);
            PreparedStatement preparedStmt45 = con.prepareStatement(sql45);
            PreparedStatement preparedStmt46 = con.prepareStatement(sql46);
            PreparedStatement preparedStmt47 = con.prepareStatement(sql47);
            PreparedStatement preparedStmt48 = con.prepareStatement(sql48);
            PreparedStatement preparedStmt49 = con.prepareStatement(sql49);
            PreparedStatement preparedStmt50 = con.prepareStatement(sql50);
            
            PreparedStatement preparedStmt51 = con.prepareStatement(sql51);
            PreparedStatement preparedStmt52 = con.prepareStatement(sql52);
            PreparedStatement preparedStmt53 = con.prepareStatement(sql53);
            PreparedStatement preparedStmt54 = con.prepareStatement(sql54);
            PreparedStatement preparedStmt55 = con.prepareStatement(sql55);
            PreparedStatement preparedStmt56 = con.prepareStatement(sql56);
            PreparedStatement preparedStmt57 = con.prepareStatement(sql57);
            PreparedStatement preparedStmt58 = con.prepareStatement(sql58);
            PreparedStatement preparedStmt59 = con.prepareStatement(sql59);
            PreparedStatement preparedStmt60 = con.prepareStatement(sql60);
            
            PreparedStatement preparedStmt61 = con.prepareStatement(sql61);
            PreparedStatement preparedStmt62 = con.prepareStatement(sql62);
            PreparedStatement preparedStmt63 = con.prepareStatement(sql63);
            PreparedStatement preparedStmt64 = con.prepareStatement(sql64);
            PreparedStatement preparedStmt65 = con.prepareStatement(sql65);
            PreparedStatement preparedStmt66 = con.prepareStatement(sql66);
            PreparedStatement preparedStmt67 = con.prepareStatement(sql67);
            PreparedStatement preparedStmt68 = con.prepareStatement(sql68);
            PreparedStatement preparedStmt69 = con.prepareStatement(sql69);
            PreparedStatement preparedStmt70 = con.prepareStatement(sql70);
            
            PreparedStatement preparedStmt71 = con.prepareStatement(sql71);
            PreparedStatement preparedStmt72 = con.prepareStatement(sql72);
            PreparedStatement preparedStmt73 = con.prepareStatement(sql73);
            PreparedStatement preparedStmt74 = con.prepareStatement(sql74);
            PreparedStatement preparedStmt75 = con.prepareStatement(sql75);
            PreparedStatement preparedStmt76 = con.prepareStatement(sql76);
            PreparedStatement preparedStmt77 = con.prepareStatement(sql77);
            PreparedStatement preparedStmt78 = con.prepareStatement(sql78);
            PreparedStatement preparedStmt79 = con.prepareStatement(sql79);
            PreparedStatement preparedStmt80 = con.prepareStatement(sql80);
            
            PreparedStatement preparedStmt81 = con.prepareStatement(sql81);
            PreparedStatement preparedStmt82 = con.prepareStatement(sql82);
            PreparedStatement preparedStmt83 = con.prepareStatement(sql83);
            PreparedStatement preparedStmt84 = con.prepareStatement(sql84);
            PreparedStatement preparedStmt85 = con.prepareStatement(sql85);
            PreparedStatement preparedStmt86 = con.prepareStatement(sql86);
            PreparedStatement preparedStmt87 = con.prepareStatement(sql87);


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
            
            preparedStmt11.execute();
            preparedStmt12.execute();
            preparedStmt13.execute();
            preparedStmt14.execute();
            preparedStmt15.execute();
            preparedStmt16.execute();
            preparedStmt17.execute();
            preparedStmt18.execute();
            preparedStmt19.execute();
            preparedStmt20.execute();
            
            preparedStmt21.execute();
            preparedStmt22.execute();
            preparedStmt23.execute();
            preparedStmt24.execute();
            preparedStmt25.execute();
            preparedStmt26.execute();
            preparedStmt27.execute();
            preparedStmt28.execute();
            preparedStmt29.execute();
            preparedStmt30.execute();
            
            preparedStmt31.execute();
            preparedStmt32.execute();
            preparedStmt33.execute();
            preparedStmt34.execute();
            preparedStmt35.execute();
            preparedStmt36.execute();
            preparedStmt37.execute();
            preparedStmt38.execute();
            preparedStmt39.execute();
            preparedStmt40.execute();
            
            preparedStmt51.execute();
            preparedStmt52.execute();
            preparedStmt53.execute();
            preparedStmt54.execute();
            preparedStmt55.execute();
            preparedStmt56.execute();
            preparedStmt57.execute();
            preparedStmt58.execute();
            preparedStmt59.execute();
            preparedStmt60.execute();
            
            preparedStmt61.execute();
            preparedStmt62.execute();
            preparedStmt63.execute();
            preparedStmt64.execute();
            preparedStmt65.execute();
            preparedStmt66.execute();
            preparedStmt67.execute();
            preparedStmt68.execute();
            preparedStmt69.execute();
            preparedStmt70.execute();
            
            preparedStmt71.execute();
            preparedStmt72.execute();
            preparedStmt73.execute();
            preparedStmt74.execute();
            preparedStmt75.execute();
            preparedStmt76.execute();
            preparedStmt77.execute();
            preparedStmt78.execute();
            preparedStmt79.execute();
            preparedStmt80.execute();
            
            preparedStmt81.execute();
            preparedStmt82.execute();
            preparedStmt83.execute();
            preparedStmt84.execute();
            preparedStmt85.execute();
            preparedStmt86.execute();
            preparedStmt87.execute();

            System.out.println("Tables Successfully Populated ");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //Goes back to main menu
        Prompts();
    }

    public void dropTables() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                    username, password);
            Statement st = con.createStatement();
            String sql = "DROP TABLE Transactions PURGE";
            String sql2 =  "DROP TABLE Loans PURGE";
            String sql3 =  "DROP TABLE TransactionType PURGE";
            String sql4 =  "Drop TABLE BranchManager PURGE";
            String sql5 =  "DROP TABLE BankTeller PURGE";
            String sql6 =  "DROP TABLE Employee PURGE";
            String sql7 =  "DROP TABLE Customer PURGE";
            String sql8 =  "DROP TABLE Accounts PURGE";
            String sql9 =  "DROP TABLE BankBranch PURGE";
            String sql10 =  "DROP TABLE Bank PURGE";

            PreparedStatement preparedStmt1 = con.prepareStatement(sql);
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
            System.out.println(e);
        }
        //Goes back to main menu
        Prompts();
    }
}
