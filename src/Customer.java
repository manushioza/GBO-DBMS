
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

public class Customer {

    private static int customerID;
    Scanner input = new Scanner(System.in);

    public Customer() {
        System.out.print("\n> Please enter your 5 digit customer id: ");
        int cId = input.nextInt();
        this.customerID = cId;

        Prompts();
    }

    public void Prompts() {
        System.out.print("\nSelect an option from below: \n [1] Send money to another account"
                + "\n [2] Pay a loan"
                + "\n [0] Quit \n");
        System.out.print("> My Choice: ");
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
                System.out.print("\nPlease enter a vaild a choice. \n ");
                Prompts();
        }
    }

    //Need to Fix
    /* Ask user if they want to (1) Deposit money (2) withdraw money (3) transfer money
    for (1) and (2) keey update statement, for (3), you have to decrease money from customer account (using update statement and
    create new transaction) */
    public void transferMoney() {
        boolean b = true;
        while (b) {
            System.out.print("\nTo transfer money");
            System.out.print("\n > Account ID of the recieving Account (5 digits):");
            int aID = input.nextInt();
            System.out.print("\n > Amount (without $):");
            double moneyTransfer = input.nextDouble();
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        "username", "password");
                Statement st = con.createStatement();

                String sql = "UPDATE Accounts"
                        + " SET Balance = Balance + ? "
                        + "WHERE Account_ID = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, aID);
                ps.setDouble(2, moneyTransfer);
                System.out.println("Amount " + moneyTransfer + " successfully added to account # " + aID);
                ps.execute();
                con.close();
                b = false;

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //Goes back to main menu
        Prompts();
    }

    // Need to Fix
    /* (1) Updated loan amount does not show up on DB
       (2) Can you also ouput new loan amount to customer after paying the loan? no select statement required just simple math  */
    public void payLoan() {
        boolean b = true;
        while (b) {
            System.out.print("\nTo pay a pre-existing loan:");
            System.out.print("\n > Loan ID (5 digits):");
            int lID = input.nextInt();
            System.out.print(" > Loan Ammount (without $):");
            double payLoan = input.nextDouble();

            try {
                Class.forName("oracle.jdbc.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                        "username", "password");
                Statement st = con.createStatement();
                String sql = "UPDATE LOANS"
                        + " SET AMOUNT = AMOUNT - ? "
                        + "WHERE LOAN_ID = ?";

                PreparedStatement prepStmt = con.prepareStatement(sql);
                prepStmt.setInt(1, lID);
                prepStmt.setDouble(2, payLoan);
                prepStmt.execute();
                System.out.println("Amount $" + payLoan + " successfully paid towards Loan# " + lID);
                con.close();
                b = false;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Prompts();
    }
}
