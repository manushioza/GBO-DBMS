import java.sql.Timestamp;

public class Customer {

    private static int customerID;

    public Customer(int cID) {
        this.customerID = cID;
    }

    public String newTransaction(int transactionID, int accountIDS, int accountIDR, int loanID, String transaction,
            int managerID, String creditDebit) {
        // For Transaction_Timestamp attribute in SQL
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return "";
    }

    public String viewTransaction(int transactionID) {

        return "";
    }
}
