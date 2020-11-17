public class Manager {
    private static int managerID;

    public Manager(int mID) {
        this.managerID = mID;
    }

    public String addCustomer(int customerID, int accountID, String firstName, String lastName, String address,
            String phoneNumber, String email) {

        return "";
    }

    public String deleteCustomer(int customerID) {

        return "";
    }

    public String addAccount(int accountID, int branchID, String accountType, double balance, double rate,
            String status) {
        return "";
    }

    public String deleteAccount(int accountID) {

        return "";
    }

    // If transaction = null output all transaction
    // If transaction != null, show only transaction with specific transactionID
    public String viewTransaction(int transactionID) {

        return "";
    }
}
