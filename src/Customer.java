
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Customer {

    //Change this before running project
    private final String username = "username";
    private final String password = "password";

    private static int customerID;
    Scanner input = new Scanner(System.in);
    ArrayList<Integer> customerIDs = new ArrayList<Integer>();
    ArrayList<Integer> accountIDs = new ArrayList<Integer>();
    ArrayList<Integer> loanIDs = new ArrayList<Integer>();

    public Customer() {

        customerIDs.addAll(Arrays.asList(82090, 77293, 74742, 93353, 92521, 73751, 52035, 88498, 73101, 8644, 14057, 16157, 4447, 35973, 19287, 81858,
                69084, 24643, 13280, 93795, 90894, 46326, 97679, 7794, 90886, 53809, 76821, 99623, 98292, 79410, 28059, 32981, 3704, 21301, 26016, 90711, 33324,
                47975, 53925, 68043, 48515, 75768, 31778, 43380, 75501, 37980, 11937, 18027, 37694, 36303));

        boolean b = true;
        while (b) {
            System.out.print("\n > Please enter your 5 digit customer id: ");
            int cId = input.nextInt();
            this.customerID = cId;

            if (!customerIDs.contains(cId)) {
                System.out.print("\nIncorrect Customer ID. Please try again.\n");
            } else {
                b = false;
                Prompts();
            }

        }

    }

    public void Prompts() {
        System.out.print("\nSelect an option from below: \n [1] Send money to another account"
                + "\n [2] Pay down a loan"
                + "\n [0] Quit \n");
        System.out.print(" > My Choice: ");
        int choice = input.nextInt();

        //Switch case to determine which function to go to next
        switch (choice) {
            case 1:
                transferMoney();
            case 2:
                payLoan();
            case 0:
                System.exit(0);
            default:
                System.out.print("\n > Please enter a vaild a choice: ");
        }
    }

    public void transferMoney() {

        accountIDs.addAll(Arrays.asList(34929, 24557, 78660, 50741, 70170, 11237, 26256, 36766, 13433, 14436, 37285, 81797, 11935, 17668, 56677, 47203,
                18033, 46774, 93018, 45690, 88899, 10736, 75639, 87672, 62015, 82293, 77787, 16557, 38470, 81556, 84641, 24749,
                59417, 95581, 57656, 89577, 85789, 29993, 91770, 28024, 79577, 18242, 99891, 19501, 67906, 12698, 26855, 71894, 36328, 42979));

        boolean b = true;
        while (b) {
            System.out.print("\nTo transfer money");
            System.out.print(" > Enter the 5 digit account id you want to transfer to");
            int aID = input.nextInt();
            if (!accountIDs.contains(aID)) {
                System.out.print("\nIncorrect Account ID. Please try again.\n");
                Prompts();
            } else {
                b = false;

            }
            System.out.print("\nHow much money do you want to transfer");
            double moneyTransfer = input.nextDouble();

            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        username, password);
                Statement st = con.createStatement();

                String sql = "UPDATE ACCOUNTS"
                        + " SET BALANCE = BALANCE + ? "
                        + "WHERE ACCOUNT_ID = ?";

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDouble(1, moneyTransfer);
                ps.setInt(2, aID);
                System.out.println("Amount " + moneyTransfer + " successfully added to account # " + aID);
                ps.execute();
                con.close();
                b = false;

            } catch (Exception e) {
                System.out.println(e);
            }

        }
        Prompts();
    }

    public void payLoan() {
        loanIDs.addAll(Arrays.asList(37920, 58210, 11532, 54916, 43045, 54212, 62879, 14134, 54010,
                61480, 19629, 39452, 69981, 82853, 73906, 56123, 16348, 49823));

        boolean b = true;
        while (b) {
            System.out.print("\nTo To pay the loan");
            System.out.print(" > Enter the 5 digit loan id you want to pay to");
            int lID = input.nextInt();
            if (!accountIDs.contains(lID)) {
                System.out.print("\nIncorrect Loan ID. Please try again.\n");
                Prompts();
            } else {
                b = false;
            }

            System.out.print("\nHow much money do you want to pay towards the loan");
            double payLoan = input.nextDouble();

            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        username, password);
                Statement st = con.createStatement();
                String sql = "UPDATE LOANS"
                        + " SET AMOUNT = AMOUNT - ? "
                        + "WHERE LOAN_ID = ?";

                PreparedStatement prepStmt = con.prepareStatement(sql);
                prepStmt.setInt(1, lID);
                prepStmt.setDouble(2, payLoan);
                prepStmt.execute();

                System.out.println("Amount " + payLoan + " successfully paid towards Loan# " + lID);
                con.close();

                b = false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Prompts();
    }
}
