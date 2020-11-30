
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
            String sql1 = "CREATE TABLE BANK("
                    + "Bank_ID NUMBER (5) PRIMARY KEY,"
                    + "Bank_Name VARCHAR(50),"
                    + "Address VARCHAR(200))";
            String sql2 = "CREATE TABLE BankBranch("
                    + "Branch_ID NUMBER(5) PRIMARY KEY,"
                    + "Bank_ID NUMBER(5) REFERENCES Bank(Bank_ID),"
                    + "Branch_Name VARCHAR(30),"
                    + "Branch_City VARCHAR(30), "
                    + "Address VARCHAR2(200),"
                    + "Phone_Number VARCHAR(15))";
            String sql3 = "CREATE TABLE Accounts("
                    + "Account_ID NUMBER(5) PRIMARY KEY,"
                    + "Branch_ID NUMBER(5) References BankBranch(Branch_ID), "
                    + "Account_Type VARCHAR(8),"
                    + "Balance FLOAT(15),"
                    + "Rate FLOAT(4),"
                    + "Status VARCHAR(20))";
            String sql4 = "CREATE TABLE Customer("
                    + "Customer_ID NUMBER(5) PRIMARY KEY,"
                    + "Account_ID  NUMBER(5) References Accounts(Account_ID),"
                    + "First_Name VARCHAR(50),"
                    + "Last_Name VARCHAR(50),"
                    + "Address  VARCHAR(200),"
                    + "Phone_Number VARCHAR(15), "
                    + "Age NUMBER(3),"
                    + "Email VARCHAR(200))";
            String sql5 = "CREATE TABLE Employee("
                    + "Employee_ID NUMBER(5) PRIMARY KEY,"
                    + "Branch_ID NUMBER(5) REFERENCES BankBranch(Branch_ID), "
                    + "First_Name VARCHAR2(50),"
                    + "Last_Name VARCHAR2(50),"
                    + "Phone_Number VARCHAR(15),"
                    + "Address VARCHAR2(200),"
                    + "Salary FLOAT(8),"
                    + "Emp_City VARCHAR(20))";
            String sql6 = "CREATE TABLE BankTeller ("
                    + "Teller_ID NUMBER(5) PRIMARY KEY,"
                    + "Employee_ID NUMBER(5) REFERENCES Employee(Employee_ID))";
            String sql7 = "CREATE TABLE BranchManager ("
                    + "Manager_ID NUMBER(5) PRIMARY KEY,"
                    + "Employee_ID NUMBER(5) REFERENCES Employee(Employee_ID),"
                    + "Teller_ID Number(5) REFERENCES BankTeller(Teller_ID))";
            String sql8 = "CREATE TABLE Loans(Loan_ID NUMBER(5) Primary Key, Duration_Yr NUMBER(3),Interest_Rate FLOAT(4), Start_Date DATE,Loan_Type VARCHAR(100),"
                    + "Amount FLOAT(7),Loan_TimeStamp  TIMESTAMP WITH LOCAL TIME ZONE)";
            String sql9 = "CREATE TABLE TransactionType("
                    + "Transaction_Type VARCHAR(10),"
                    + "Transaction_Name VARCHAR2(25) Primary Key,"
                    + "Transaction_Description  VARCHAR(300))";
            String sql10 = "CREATE TABLE Transactions("
                    + "Transaction_ID  NUMBER(5),"
                    + "Account_ID_S  NUMBER(5),"
                    + "Account_ID_R  NUMBER(5),"
                    + "Transaction_TimeStamp TIMESTAMP WITH LOCAL TIME ZONE,"
                    + "Loan_ID  NUMBER(5),"
                    + "Transaction_Name  CHAR(10),"
                    + "Manager_ID  NUMBER(5),"
                    + "Credit_Debit  CHAR(6),"
                    + "Amount FLOAT(15),"
                    + "PRIMARY KEY (Transaction_ID,  Transaction_Name ,"
                    + "Loan_ID)"
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

            System.out.println("\n  **** Tables Successfully Created  **** ");
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
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl", username, password);
            Statement st = con.createStatement();
            //Bank -> 1
            String sql1 = "INSERT INTO BANK(Bank_ID,Bank_Name, Address) VALUES(12930, 'Greater Bank of Ontario (GBO)', '755 Oxford Street, Ottawa, Ontario K1A 0J6')";
            PreparedStatement preparedStmt1 = con.prepareStatement(sql1);
            preparedStmt1.execute();

            //BankBranch -> 2 -9
            String sql2 = "INSERT INTO BankBranch(Branch_ID,Bank_ID,Branch_Name, Branch_City, Address,Phone_Number)"
                    + " VALUES(12930, 12930, 'GBO Ottawa','Ottawa','3755 Oxford Street, Ottawa, Ontario K1A 0J6', '613-325-1129')";
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

            PreparedStatement preparedStmt2 = con.prepareStatement(sql2);
            PreparedStatement preparedStmt3 = con.prepareStatement(sql3);
            PreparedStatement preparedStmt4 = con.prepareStatement(sql4);
            PreparedStatement preparedStmt5 = con.prepareStatement(sql5);
            PreparedStatement preparedStmt6 = con.prepareStatement(sql6);
            PreparedStatement preparedStmt7 = con.prepareStatement(sql7);
            PreparedStatement preparedStmt8 = con.prepareStatement(sql8);
            PreparedStatement preparedStmt9 = con.prepareStatement(sql9);

            preparedStmt2.execute();
            preparedStmt3.execute();
            preparedStmt4.execute();
            preparedStmt5.execute();
            preparedStmt6.execute();
            preparedStmt7.execute();
            preparedStmt8.execute();
            preparedStmt9.execute();

            //ACCOUNTS -> 10 - 58
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
            preparedStmt41.execute();
            preparedStmt42.execute();
            preparedStmt43.execute();
            preparedStmt44.execute();
            preparedStmt45.execute();
            preparedStmt46.execute();
            preparedStmt47.execute();
            preparedStmt48.execute();
            preparedStmt49.execute();
            preparedStmt50.execute();
            preparedStmt51.execute();
            preparedStmt52.execute();
            preparedStmt53.execute();
            preparedStmt54.execute();
            preparedStmt55.execute();
            preparedStmt56.execute();
            preparedStmt57.execute();
            preparedStmt58.execute();

            // CUSTOMER -> 59 - 107
            String sql59 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (82090,17668,'Ellis','Whitaker','1795 Wellington Street, Toronto, Ontario(ON), M9C 3J5','416-394-4244',17,'ewhitaker20@gmail.com')";
            String sql60 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (77293,56677,'Mitchel','Herman','4038 Dundas St Toronto, Ontario(ON), M2N 2G8','416-733-6146',49,'mitchh88@yahoo.com')";
            String sql61 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (74742,34929,'Ollie','Ray','4187 Van Horne Ave, Milton, Ontario(ON), P8N 2A7','807-221-5781',40,'rayollie@outlook.com')";
            String sql62 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (93353,13433,'Rashad','Stephen','4655 Lock Drive, Barrie, Ontario(ON), L4M 3B1','705-721-7567',23,'rsteph123@gmail.com')";
            String sql63 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (92521,59417,'Leo','Reynolds','458 Yonge Street, Toronto, Ontario(ON), M4W 1J7','416-934-1223',25,'oelrey@outlook.com')";
            String sql64 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (73751,14436,'Miranda','Mercer','2926 Victoria Park Ave, Toronto, Ontario(ON), M2J 3T7','416-753-7276',48,'mm22020@gmail.com')";
            String sql65 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (52035,47203,'Lanny','Henderson','4149 James Street, Kingston, Ontario(ON), P0L 1N0','705-362-2119',55,'lannyhen@hello.com')";
            String sql66 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (88498,95581,'Melvin','Carlson','3047 Bridgeport Rd, Milton, Ontario(ON), L9T 1P4','905-299-8084',59,'mellymel@hotmail.com')";
            String sql67 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (73101,37285,'Alfonzo','Carpenter','2678 Fallon Drive, Parkhill, Ontario(ON), N0M 2K0','519-294-6283',61,'alfonzozo@gmail.com')";
            String sql68 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (08644,18033,'Paula','Neal','656 Baker Street, London, Ontario(ON), N0N 0N0','519-931-2877',54,'pneal@yahoo.com')";
            String sql69 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (14057,46774,'Lewis','Mcpherson','3778 Carling Avenue, Ottawa, Ontario(ON), K1Z 7B5','613-715-0737',29,'lewis@outlook.com')";
            String sql70 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (16157,57656,'Benita','Livingston','2306 Fallon Drive, London, Ontario(ON), N0M 1B0','519-828-0740',31,'benita2123@gmail.com')";
            String sql71 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (04447,89577,'Angelo','Frazier','710 Barrydowne Road, Barrie, Ontario(ON), P3E 5K3','705-673-8053',46,'angelangelo@gmail.com')";
            String sql72 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (35973,24557,'Maude','Nichols','4169 York St, London, Ontario(ON), N5W 2S9','519-639-1270',42,'nicholsmaude@hello.com')";
            String sql73 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (19287,93018,'Terrence','Archer','3504 Scotchmere Dr, Missisauga, Ontario(ON), N7T 7T9','519-381-2015',45,'terryt@hotmail.com')";
            String sql74 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (81858,85789,'Gerard','Ellison','3253 Lynden Road, Kingston, Ontario(ON), L0G 1N0','905-859-2334',52,'gerrardelli@outlook.com')";
            String sql75 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (69084,81797,'Normand','Daniel','1825 Ross Street, Kingston, Ontario(ON), K7L 4V4','613-532-7396',44,'normyd@hello.com')";
            String sql76 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (24643,45690,'Nanette','Gomez','4530 Barton Street,  London,  Ontario(ON), L8G 2V1','905-662-5079',43,'ngomz@gmail.com')";
            String sql77 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (13280,88899,'Salvatore','Levine','1881 Charleton Ave, Hamilton, Ontario(ON), L8N 3X3','905-865-3681',68,'sallev@gmail.com')";
            String sql78 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (93795,29993,'Leona','Gallegos','4219 Water Street, Hamilton , Ontario(ON), N2H 5A5','519-505-5510',54,'ggleona@yahoo.com')";
            String sql79 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (90894,78660,'Orval','Nicholson','2311 MacLaren Street, Ottawa, Ontario(ON), K1P 5M7','613-787-1548',37,'orvalnich99@yahoo.com')";
            String sql80 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (46326,91770,'Augustus','Ritter','752 Carling Avenue, Ottawa, Ontario(ON), K1Z 7B5','613-558-3497',70,'gusgus212@gmail.com')";
            String sql81 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (97679,28024,'Ronald','Powell','2140 Heatherleigh, Missisuaga , Ontario(ON), L5A 1V9','905-615-1886',62,'ronaldduck323@outlook.com')";
            String sql82 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (07794,79577,'Roscoe','Singleton','2180 Princess St, Kingston, Ontario(ON), K7L 1C2','613-650-6474',58,'rossyros@gmail.com')";
            String sql83 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (90886,18242,'Todd','Rojas','3021 River Street, Ottawa, Ontario(ON), N0N 1G0','519-862-2601',33,'toddrojas@hello.com')";
            String sql84 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (53809,50741,'Stephan','Holder','1612 Fallon Drive, Missisuaga Ontario(ON), N0P 2B0','519-677-4331',30,'stephanh@hotmail.com')";
            String sql85 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (76821,10736,'Kristina','Leblanc','4474 Charing Cross Rd, Ottawa, Ontario(ON), N7M 2G9','519-351-9206',69,'leblanckk@gmail.com')";
            String sql86 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (99623,75639,'Verna','Hurst','2917 MacLaren Street, Ottawa, Ontario(ON), K1P 5M7','613-940-7429',45,'vernahurst@yahoo.com')";
            String sql87 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (98292,87672,'Foster','Horton','4618 Dufferin Street, Toronto, Ontario(ON), M6H 4B6','416-588-3379',43,'hortonfoster3232@outlook.com')";
            String sql88 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (79410,62015,'Joann','Benton','800 Halsey Avenue, Toronto, Ontario(ON), M3B 2W6','416-386-6639',17,'jojo22@gmail.com')";
            String sql89 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (28059,99891,'Alba','Lynch','4410 Bayfield St,  Barrie, Ontario(ON), L4M 4S8','705-718-2962',52,'alblynch764@gmail.com')";
            String sql90 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (32981,11935,'Chong','Beck','49 Bridgeport Rd, Milton, Ontario(ON), L9T 2Y2','905-699-1628',70,'beckchong577@hello.com')";
            String sql91 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (03704,82293,'Ellen','Bentley','1593 Princess St, Kingston, Ontario(ON), K7L 1C2','613-887-5415',51,'ellen7872@hotmail.com')";
            String sql92 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (21301,77787,'Lucas','Norman','4987 Merivale Road, Ottawa, Ontario(ON), K2G 3K2','613-228-1963',42,'lucasnorman@outlook.com')";
            String sql93 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (26016,19501,'King','Wu','4219 Tycos Dr, Toronto, Ontario(ON), M5T 1T4','416-623-3539',16,'wukiinggss32355@hello.com')";
            String sql94 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (90711,67906,'Truman','Vazquez','3773 Adelaide St, Toronto, Ontario(ON), M5H 1P6','416-216-6219',34,'truman322@gmail.com')";
            String sql95 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (33324,12698,'Alfredo','Combs','110 Barton Street, Hamilton, Ontario(ON), L8P 1P8','905-745-3675',41,'combsal98@yahoo.com')";
            String sql96 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (47975,26855,'Steve','Stark','4136 Parkside Road , Mississauga, Ontario(ON), L5C 4G8','289-360-2116',65,'steves93@outlook.com')";
            String sql97 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (53925,71894,'Liza','Bradford','3520 Baker Street, London, Ontario(ON), N0N 0N0','519-432-1056',64,'lizab@gmail.com')";
            String sql98 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (68043,70170,'Kelly','Huber','4532 Merton Street, Toronto, Ontario(ON), M1L 3K7','416-884-4950',63,'kellyhuber@gmail.com')";
            String sql99 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (48515,16557,'Don','Schultz','629 Hyde Park Road, London, Ontario(ON), N6H 3S2','519-657-5727',24,'dons45@gmail.com')";
            String sql100 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (75768,38470,'Jonas','Rivas','3746 Carling Avenue, Ottawa, Ontario(ON), K1Z 7B5','613-729-0034',36,'jonasriv92@hello.com')";
            String sql101 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (31778,81556,'Christoper','Dodson','1716 Weir Crescent, Toronto, Ontario(ON), M1E 3T8','416-797-8316',29,'chrisd32@outlook.com')";
            String sql102 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (43380,11237,'Kurt','Greg','3317 Islington Ave, Toronto, Ontario(ON), M8V 3B6','647-223-9943',55,'gkurt@hotmail.com')";
            String sql103 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (75501,84641,'Shawna','Morris','2990 Duke Street, Mississauga, Ontario(ON), L5L 5S1','905-302-8812',31,'shawnam538@gmail.com')";
            String sql104 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (37980,26256,'Benny','Howell','1837 Runnymede Rd, Toronto, Ontario(ON), M6S 2Z7','416-761-3848',21,'benny@hello.com')";
            String sql105 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (11937,36328,'Angelica','Leon','3924 Central Pkwy, Mississauga, Ontario(ON), L5L 5S1','905-301-7457',47,'aleon@gmail.com')";
            String sql106 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (18027,36766,'Orlando','Sandoval','455 Orfus Rd, Milton, Ontario(ON), L9T 1P4','905-864-7799',56,'notbloom@info.com')";
            String sql107 = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL)"
                    + "VALUES (37694,42979,'Kimberley','Goodwin','4651 Smith Avenue, Hamilton, Ontario(ON), L9H 1E6','905-515-4951',46,'kimberly.g@aol.com')";

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
            PreparedStatement preparedStmt88 = con.prepareStatement(sql88);
            PreparedStatement preparedStmt89 = con.prepareStatement(sql89);
            PreparedStatement preparedStmt90 = con.prepareStatement(sql90);
            PreparedStatement preparedStmt91 = con.prepareStatement(sql91);
            PreparedStatement preparedStmt92 = con.prepareStatement(sql92);
            PreparedStatement preparedStmt93 = con.prepareStatement(sql93);
            PreparedStatement preparedStmt94 = con.prepareStatement(sql94);
            PreparedStatement preparedStmt95 = con.prepareStatement(sql95);
            PreparedStatement preparedStmt96 = con.prepareStatement(sql96);
            PreparedStatement preparedStmt97 = con.prepareStatement(sql97);
            PreparedStatement preparedStmt98 = con.prepareStatement(sql98);
            PreparedStatement preparedStmt99 = con.prepareStatement(sql99);
            PreparedStatement preparedStmt100 = con.prepareStatement(sql100);
            PreparedStatement preparedStmt101 = con.prepareStatement(sql101);
            PreparedStatement preparedStmt102 = con.prepareStatement(sql102);
            PreparedStatement preparedStmt103 = con.prepareStatement(sql103);
            PreparedStatement preparedStmt104 = con.prepareStatement(sql104);
            PreparedStatement preparedStmt105 = con.prepareStatement(sql105);
            PreparedStatement preparedStmt106 = con.prepareStatement(sql106);
            PreparedStatement preparedStmt107 = con.prepareStatement(sql107);

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
            preparedStmt88.execute();
            preparedStmt89.execute();
            preparedStmt90.execute();
            preparedStmt91.execute();
            preparedStmt92.execute();
            preparedStmt93.execute();
            preparedStmt94.execute();
            preparedStmt95.execute();
            preparedStmt96.execute();
            preparedStmt97.execute();
            preparedStmt98.execute();
            preparedStmt99.execute();
            preparedStmt100.execute();
            preparedStmt101.execute();
            preparedStmt102.execute();
            preparedStmt103.execute();
            preparedStmt104.execute();
            preparedStmt105.execute();
            preparedStmt106.execute();
            preparedStmt107.execute();

            //EMPLOYEE -> 109 - 134
            String sql109 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (12330,12930,'Artemisios','Andreasson','613-409-5429','397 McDonald Street, Ottawa, Ontario(ON), K1R 3X7',80000,'Ottawa')";
            String sql110 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (14330,12930,'Heike','Jennings','613-409-5430','397 McDonald Street, Ottawa, Ontario(ON), K1R 3X7',50000,'Ottawa')";
            String sql111 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (34530,12930,'Alekto','Okafor','613-409-5431','397 McDonald Street, Ottawa, Ontario(ON), K1R 3X7',90000,'Ottawa')";
            String sql112 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (66630,12930,'Amy','Foster','613-409-5432','397 McDonald Street, Ottawa, Ontario(ON), K1R 3X7',54000,'Ottawa')";
            String sql113 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (76530,12930,'Arunas','MacRae','613-409-5433','397 McDonald Street, Ottawa, Ontario(ON), K1R 3X7',50100,'Ottawa')";
            String sql114 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (85230,12930,'Damocles','Nielson','613-409-5434','397 McDonald Street, Ottawa, Ontario(ON), K1R 3X7',58950,'Ottawa')";
            String sql115 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (23031,12931,'Eimhear','Roosevelt','416-388-6699','8790 Haley Avenue, Toronto, Ontario(ON), M1Q 2X7',52000,'Toronto')";
            String sql116 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (49531,12931,'Jagannatha','Heinz','416-388-6700','8790 Haley Avenue, Toronto, Ontario(ON), M1Q 2X7',70000,'Toronto')";
            String sql117 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (84331,12931,'Valens','Horn','416-388-6701','8790 Haley Avenue, Toronto, Ontario(ON), M1Q 2X7',87000,'Toronto')";
            String sql118 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (9731,12931,'Eliott','Jewel','416-388-6702','8790 Haley Avenue, Toronto, Ontario(ON), M1Q 2X7',51300,'Toronto')";
            String sql119 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (20332,12932,'Yechezkel','Ovesen','705-780-2919','66 Bayflor Drive,  Barrie, Ontario(ON), L8S 9C0',40000,'Barrie')";
            String sql120 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (59032,12932,'Finlay','Ford','705-780-2920','66 Bayflor Drive,  Barrie, Ontario(ON), L8S 9C0',67000,'Barrie')";
            String sql121 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (67432,12932,'Gemma','Samson','705-780-2921','66 Bayflor Drive,  Barrie, Ontario(ON), L8S 9C0',76000,'Barrie')";
            String sql122 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (83533,12933,'Sari','Robert','905-555-5151','441 Smithson Avenue, Hamilton, Ontario(ON), L7N 1E6',85000,'Hamilton')";
            String sql123 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (98533,12933,'Mikey','Garfield','905-555-5152','441 Smithson Avenue, Hamilton, Ontario(ON), L7N 1E6',83000,'Hamilton')";
            String sql124 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (5133,12933,'Christiana','Farnham','905-555-5153','441 Smithson Avenue, Hamilton, Ontario(ON), L7N 1E6',60000,'Hamilton')";
            String sql125 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (40534,12934,'Gavril','Alfero','905-311-7777','384 Eastside Road, Mississauga, Ontario(ON), L9A 2X9',45500,'Mississauga')";
            String sql126 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (58934,12934,'Chrissie','Lockwood','905-311-7778','384 Eastside Road, Mississauga, Ontario(ON), L9A 2X9',93000,'Mississauga')";
            String sql127 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (92434,12934,'Thurstan','Mayes','905-311-7779','384 Eastside Road, Mississauga, Ontario(ON), L9A 2X9',57800,'Mississauga')";
            String sql128 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (56935,12935,'Lizzy','Ongaro','905-844-7909','932 Orange Rd, Milton, Ontario(ON), L0E 2V8',45700,'Milton')";
            String sql129 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (63635,12935,'Thornton','Ness','905-844-7910','932 Orange Rd, Milton, Ontario(ON), L0E 2V8',88000,'Milton')";
            String sql130 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (90336,12936,'Visnja','Simmon','613-888-5415','123 Prince St, Kingston, Ontario(ON), K9H 3Y4',67400,'Kingston')";
            String sql131 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (6536,12936,'Aubrie','Dyson','613-888-5416','123 Prince St, Kingston, Ontario(ON), K9H 3Y4',53500,'Kingston')";
            String sql132 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (7736,12936,'Abbey','Cooks','613-888-5417','123 Prince St, Kingston, Ontario(ON), K9H 3Y4',74000,'Kingston')";
            String sql133 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (15637,12937,'John','Kaspersen','905-632-5097','230 Baxter Street,  London,  Ontario(ON), L2X 2Q1',45000,'London')";
            String sql134 = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, BRANCH_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ADDRESS, SALARY, EMP_CITY)"
                    + "VALUES (29837,12937,'Seyfullah','Lis','905-620-5180','459 Dolly Street,  London,  Ontario(ON), L8H 2V2',86000,'London')";

            PreparedStatement preparedStmt109 = con.prepareStatement(sql109);
            PreparedStatement preparedStmt110 = con.prepareStatement(sql110);
            PreparedStatement preparedStmt111 = con.prepareStatement(sql111);
            PreparedStatement preparedStmt112 = con.prepareStatement(sql112);
            PreparedStatement preparedStmt113 = con.prepareStatement(sql113);
            PreparedStatement preparedStmt114 = con.prepareStatement(sql114);
            PreparedStatement preparedStmt115 = con.prepareStatement(sql115);
            PreparedStatement preparedStmt116 = con.prepareStatement(sql116);
            PreparedStatement preparedStmt117 = con.prepareStatement(sql117);
            PreparedStatement preparedStmt118 = con.prepareStatement(sql118);
            PreparedStatement preparedStmt119 = con.prepareStatement(sql119);
            PreparedStatement preparedStmt120 = con.prepareStatement(sql120);
            PreparedStatement preparedStmt121 = con.prepareStatement(sql121);
            PreparedStatement preparedStmt122 = con.prepareStatement(sql122);
            PreparedStatement preparedStmt123 = con.prepareStatement(sql123);
            PreparedStatement preparedStmt124 = con.prepareStatement(sql124);
            PreparedStatement preparedStmt125 = con.prepareStatement(sql125);
            PreparedStatement preparedStmt126 = con.prepareStatement(sql126);
            PreparedStatement preparedStmt127 = con.prepareStatement(sql127);
            PreparedStatement preparedStmt128 = con.prepareStatement(sql128);
            PreparedStatement preparedStmt129 = con.prepareStatement(sql129);
            PreparedStatement preparedStmt130 = con.prepareStatement(sql130);
            PreparedStatement preparedStmt131 = con.prepareStatement(sql131);
            PreparedStatement preparedStmt132 = con.prepareStatement(sql132);
            PreparedStatement preparedStmt133 = con.prepareStatement(sql133);
            PreparedStatement preparedStmt134 = con.prepareStatement(sql134);

            preparedStmt109.execute();
            preparedStmt110.execute();
            preparedStmt111.execute();
            preparedStmt112.execute();
            preparedStmt113.execute();
            preparedStmt114.execute();
            preparedStmt115.execute();
            preparedStmt116.execute();
            preparedStmt117.execute();
            preparedStmt118.execute();
            preparedStmt119.execute();
            preparedStmt120.execute();
            preparedStmt121.execute();
            preparedStmt122.execute();
            preparedStmt123.execute();
            preparedStmt124.execute();
            preparedStmt125.execute();
            preparedStmt126.execute();
            preparedStmt127.execute();
            preparedStmt128.execute();
            preparedStmt129.execute();
            preparedStmt130.execute();
            preparedStmt131.execute();
            preparedStmt132.execute();
            preparedStmt133.execute();
            preparedStmt134.execute();

            //BANKTELLER -> 135 - 149
            String sql135 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85230,76530)";
            String sql136 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85330,14330)";
            String sql137 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85530,85230)";
            String sql138 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85630,66630)";
            String sql139 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85731,09731)";
            String sql140 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85831,23031)";
            String sql141 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (85932,20332)";
            String sql142 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86032,59032)";
            String sql143 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86133,05133)";
            String sql144 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86234,40534)";
            String sql145 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86334,92434)";
            String sql146 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86435,56935)";
            String sql147 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86536,06536)";
            String sql148 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86636,90336)";
            String sql149 = "INSERT INTO BANKTELLER (TELLER_ID, EMPLOYEE_ID) VALUES (86737,15637)";

            PreparedStatement preparedStmt135 = con.prepareStatement(sql135);
            PreparedStatement preparedStmt136 = con.prepareStatement(sql136);
            PreparedStatement preparedStmt137 = con.prepareStatement(sql137);
            PreparedStatement preparedStmt138 = con.prepareStatement(sql138);
            PreparedStatement preparedStmt139 = con.prepareStatement(sql139);
            PreparedStatement preparedStmt140 = con.prepareStatement(sql140);
            PreparedStatement preparedStmt141 = con.prepareStatement(sql141);
            PreparedStatement preparedStmt142 = con.prepareStatement(sql142);
            PreparedStatement preparedStmt143 = con.prepareStatement(sql143);
            PreparedStatement preparedStmt144 = con.prepareStatement(sql144);
            PreparedStatement preparedStmt145 = con.prepareStatement(sql145);
            PreparedStatement preparedStmt146 = con.prepareStatement(sql146);
            PreparedStatement preparedStmt147 = con.prepareStatement(sql147);
            PreparedStatement preparedStmt148 = con.prepareStatement(sql148);
            PreparedStatement preparedStmt149 = con.prepareStatement(sql149);

            preparedStmt135.execute();
            preparedStmt136.execute();
            preparedStmt137.execute();
            preparedStmt138.execute();
            preparedStmt139.execute();
            preparedStmt140.execute();
            preparedStmt141.execute();
            preparedStmt142.execute();
            preparedStmt143.execute();
            preparedStmt144.execute();
            preparedStmt145.execute();
            preparedStmt146.execute();
            preparedStmt147.execute();
            preparedStmt148.execute();
            preparedStmt149.execute();

            //BRANCHMANAGER -> 150 -159
            String sql150 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45030,12330,85230)";
            String sql151 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (85130,34530,85330)";
            String sql152 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45131,84331,85731)";
            String sql153 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45332,67432,85932)";
            String sql154 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45433,83533,86032)";
            String sql155 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (12933,98533,86133)";
            String sql156 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45534,58934,86234)";
            String sql157 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45635,63635,86435)";
            String sql158 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45736,07736,86536)";
            String sql159 = "INSERT INTO BRANCHMANAGER (MANAGER_ID, EMPLOYEE_ID, TELLER_ID) VALUES (45837,29837,86737)";

            PreparedStatement preparedStmt150 = con.prepareStatement(sql150);
            PreparedStatement preparedStmt151 = con.prepareStatement(sql151);
            PreparedStatement preparedStmt152 = con.prepareStatement(sql152);
            PreparedStatement preparedStmt153 = con.prepareStatement(sql153);
            PreparedStatement preparedStmt154 = con.prepareStatement(sql154);
            PreparedStatement preparedStmt155 = con.prepareStatement(sql155);
            PreparedStatement preparedStmt156 = con.prepareStatement(sql156);
            PreparedStatement preparedStmt157 = con.prepareStatement(sql157);
            PreparedStatement preparedStmt158 = con.prepareStatement(sql158);
            PreparedStatement preparedStmt159 = con.prepareStatement(sql159);

            preparedStmt150.execute();
            preparedStmt151.execute();
            preparedStmt152.execute();
            preparedStmt153.execute();
            preparedStmt154.execute();
            preparedStmt155.execute();
            preparedStmt156.execute();
            preparedStmt157.execute();
            preparedStmt158.execute();
            preparedStmt159.execute();

            //LOANs -> 160 - 177
            String sql160 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (37920,15,02.13,to_date('2020-02-20', 'YYYY-MM-DD'),'Personal Loan',19000.00 ,to_timestamp('2020-02-20 15:16:08', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql161 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (58210,16,09.25,to_date('2015-08-28', 'YYYY-MM-DD'),'Credit-card Loan',02300.00 ,to_timestamp('2015-08-28 11:39:30', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql162 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (11532,07,06.75,to_date('2013-08-24', 'YYYY-MM-DD'),'Credit-card Loan',22000.00 ,to_timestamp('2013-08-24 15:59:08', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql163 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (54916,05,11.75,to_date('2016-12-26', 'YYYY-MM-DD'),'Personal Loan',16000.00 ,to_timestamp('2016-12-26 18:52:34', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql164 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (43045,11,06.89,to_date('2008-10-24', 'YYYY-MM-DD'),'Home-Equity Loans',05000.00 ,to_timestamp('2008-10-24 11:53:27', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql165 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (54212,21,09.19,to_date('2019-04-28', 'YYYY-MM-DD'),'Personal line of credit',22000.00 ,to_timestamp('2019-04-28 13:13:46', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql166 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (62879,09,11.48,to_date('2011-02-13', 'YYYY-MM-DD'),'Credit-card Loan',10000.00 ,to_timestamp('2011-02-13 09:06:45', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql167 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (14134,27,16.37,to_date('2006-04-23', 'YYYY-MM-DD'),'Home-Equity Loans',08000.00 ,to_timestamp('2006-04-23 12:24:23', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql168 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (54010,11,09.62,to_date('2009-03-26', 'YYYY-MM-DD'),'Personal Loan',21000.00 ,to_timestamp('2009-03-26 17:00:24', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql169 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (61480,05,12.46,to_date('2017-12-07', 'YYYY-MM-DD'),'Personal Loan',09000.00 ,to_timestamp('2017-12-07 14:53:50', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql170 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (19629,21,03.70,to_date('2002-02-27', 'YYYY-MM-DD'),'Home-Equity Loans',05000.00 ,to_timestamp('2002-02-27 13:28:07', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql171 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (39452,08,08.47,to_date('2003-05-28', 'YYYY-MM-DD'),'Personal Loan',20000.00 ,to_timestamp('2003-05-28 09:06:08', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql172 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (69981,26,12.41,to_date('2007-11-02', 'YYYY-MM-DD'),'Home-Equity Loans',07000.00 ,to_timestamp('2007-11-02 12:19:41', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql173 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (82853,02,00.11,to_date('2017-10-06', 'YYYY-MM-DD'),'Credit-card Loan',16000.00 ,to_timestamp('2017-10-06 11:58:40', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql174 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (73906,30,07.23,to_date('2001-02-12', 'YYYY-MM-DD'),'Personal line of credit',02000.00 ,to_timestamp('2001-02-12 09:23:33', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql175 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (56123,18,08.27,to_date('2003-09-04', 'YYYY-MM-DD'),'Home-Equity Loans',00200.00 ,to_timestamp('2003-09-04 14:47:00', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql176 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (16348,14,08.76,to_date('2006-07-13', 'YYYY-MM-DD'),'Personal Loan',01500.00 ,to_timestamp('2006-07-13 14:49:39', 'YYYY-MM-DD HH24:MI:SS'))";
            String sql177 = "INSERT INTO LOANS (LOAN_ID, DURATION_YR, INTEREST_RATE, START_DATE, LOAN_TYPE, AMOUNT, LOAN_TIMESTAMP)"
                    + "VALUES (49823,21,05.00,to_date('2020-08-28', 'YYYY-MM-DD'),'Home-Equity Loans',16000.00 ,to_timestamp('2020-08-28 17:33:22', 'YYYY-MM-DD HH24:MI:SS'))";

            PreparedStatement preparedStmt160 = con.prepareStatement(sql160);
            PreparedStatement preparedStmt161 = con.prepareStatement(sql161);
            PreparedStatement preparedStmt162 = con.prepareStatement(sql162);
            PreparedStatement preparedStmt163 = con.prepareStatement(sql163);
            PreparedStatement preparedStmt164 = con.prepareStatement(sql164);
            PreparedStatement preparedStmt165 = con.prepareStatement(sql165);
            PreparedStatement preparedStmt166 = con.prepareStatement(sql166);
            PreparedStatement preparedStmt167 = con.prepareStatement(sql167);
            PreparedStatement preparedStmt168 = con.prepareStatement(sql168);
            PreparedStatement preparedStmt169 = con.prepareStatement(sql169);
            PreparedStatement preparedStmt170 = con.prepareStatement(sql170);
            PreparedStatement preparedStmt171 = con.prepareStatement(sql171);
            PreparedStatement preparedStmt172 = con.prepareStatement(sql172);
            PreparedStatement preparedStmt173 = con.prepareStatement(sql173);
            PreparedStatement preparedStmt174 = con.prepareStatement(sql174);
            PreparedStatement preparedStmt175 = con.prepareStatement(sql175);
            PreparedStatement preparedStmt176 = con.prepareStatement(sql176);
            PreparedStatement preparedStmt177 = con.prepareStatement(sql177);

            preparedStmt160.execute();
            preparedStmt161.execute();
            preparedStmt162.execute();
            preparedStmt163.execute();
            preparedStmt164.execute();
            preparedStmt165.execute();
            preparedStmt166.execute();
            preparedStmt167.execute();
            preparedStmt168.execute();
            preparedStmt169.execute();
            preparedStmt170.execute();
            preparedStmt171.execute();
            preparedStmt172.execute();
            preparedStmt173.execute();
            preparedStmt174.execute();
            preparedStmt175.execute();
            preparedStmt176.execute();
            preparedStmt177.execute();

            //TRANSACTIONTYPE
            String sql178 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Credit','Withdraw','Withdraw Money out of a bank account')";
            String sql179 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Debit','Deposit','Deposit Money into a bank account')";
            String sql180 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Debit','Send','Send money to an account')";
            String sql181 = "INSERT INTO TRANSACTIONTYPE (TRANSACTION_TYPE, TRANSACTION_NAME, TRANSACTION_DESCRIPTION)"
                    + "VALUES ('Credit','Pay Bills','Pay credit card bills')";

            PreparedStatement preparedStmt178 = con.prepareStatement(sql178);
            PreparedStatement preparedStmt179 = con.prepareStatement(sql179);
            PreparedStatement preparedStmt180 = con.prepareStatement(sql180);
            PreparedStatement preparedStmt181 = con.prepareStatement(sql181);

            preparedStmt178.execute();
            preparedStmt179.execute();
            preparedStmt180.execute();
            preparedStmt181.execute();

            //TRANSACTIONS -> 182 - 191
            String sql182 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10001, 88899, 37285, TO_TIMESTAMP('2020-09-29 10:16:00', 'YYYY-MM-DD HH24:MI:SS'), 37920, 'Credit', 45030, 'Credit', 100 )";
            String sql183 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10002, 88899, 50741, TO_TIMESTAMP('2020-08-28 11:39:00', 'YYYY-MM-DD HH24:MI:SS'), 58210, 'Debit', 85130, 'Debit', 200 )";
            String sql184 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10003, 78660, 45690, TO_TIMESTAMP('2020-08-24 03:00:00', 'YYYY-MM-DD HH24:MI:SS'), 11532, 'Credit', 45231, 'Credit', 300 )";
            String sql185 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10004, 11935, 26855, TO_TIMESTAMP('2020-10-01 06:52:00', 'YYYY-MM-DD HH24:MI:SS'), 54916, 'Debit', 45332, 'Debit', 1000 )";
            String sql186 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10005, 37285, 88899, TO_TIMESTAMP('2020-07-24 11:53:00', 'YYYY-MM-DD HH24:MI:SS'), 43045, 'Credit', 45433, 'Credit', 1234 )";
            String sql187 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10006, 11237, 81797, TO_TIMESTAMP('2020-09-04 10:16:00', 'YYYY-MM-DD HH24:MI:SS'), 54212, 'Debit', 12933, 'Debit', 4321 )";
            String sql188 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10007, 82293, 59417, TO_TIMESTAMP('2020-08-28 11:38:00', 'YYYY-MM-DD HH24:MI:SS'), 62879, 'Credit', 45534, 'Credit', 400.50 )";
            String sql189 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10008, 59417, 50741, TO_TIMESTAMP('2020-08-24 23:00:00', 'YYYY-MM-DD HH24:MI:SS'), 14134, 'Debit', 45635, 'Debit', 400 )";
            String sql190 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10009, 56677, 70170, TO_TIMESTAMP('2020-06-02 08:52:00', 'YYYY-MM-DD HH24:MI:SS'), 54010, 'Credit', 45736, 'Credit', 30 )";
            String sql191 = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, ACCOUNT_ID_S, ACCOUNT_ID_R, TRANSACTION_TIMESTAMP, LOAN_ID, TRANSACTION_NAME, MANAGER_ID, CREDIT_DEBIT, AMOUNT)"
                    + "VALUES(10010, 17668, 14436, TO_TIMESTAMP('2020-07-24 10:50:00', 'YYYY-MM-DD HH24:MI:SS'), 61480, 'Debit', 45837, 'Debit', 23 )";

            PreparedStatement preparedStmt182 = con.prepareStatement(sql182);
            PreparedStatement preparedStmt183 = con.prepareStatement(sql183);
            PreparedStatement preparedStmt184 = con.prepareStatement(sql184);
            PreparedStatement preparedStmt185 = con.prepareStatement(sql185);
            PreparedStatement preparedStmt186 = con.prepareStatement(sql186);
            PreparedStatement preparedStmt187 = con.prepareStatement(sql187);
            PreparedStatement preparedStmt188 = con.prepareStatement(sql188);
            PreparedStatement preparedStmt189 = con.prepareStatement(sql189);
            PreparedStatement preparedStmt190 = con.prepareStatement(sql190);
            PreparedStatement preparedStmt191 = con.prepareStatement(sql191);

            preparedStmt182.execute();
            preparedStmt183.execute();
            preparedStmt184.execute();
            preparedStmt185.execute();
            preparedStmt186.execute();
            preparedStmt187.execute();
            preparedStmt188.execute();
            preparedStmt189.execute();

            preparedStmt190.execute();
            preparedStmt191.execute();
            System.out.println("\n  **** Tables Successfully Populated  **** ");
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
            String sql2 = "DROP TABLE Loans PURGE";
            String sql3 = "DROP TABLE TransactionType PURGE";
            String sql4 = "Drop TABLE BranchManager PURGE";
            String sql5 = "DROP TABLE BankTeller PURGE";
            String sql6 = "DROP TABLE Employee PURGE";
            String sql7 = "DROP TABLE Customer PURGE";
            String sql8 = "DROP TABLE Accounts PURGE";
            String sql9 = "DROP TABLE BankBranch PURGE";
            String sql10 = "DROP TABLE Bank PURGE";

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

            System.out.println("\n  **** Tables Successfully Dropped  **** ");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        //Goes back to main menu
        Prompts();
    }
}
