import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Manager {
    private static int managerID;
    Scanner input = new Scanner(System.in);

    public Manager() {
        System.out.println("Please enter your 5 digit manager id: ");
        int mID = input.nextInt();  
        this.managerID = mID;
           
        Prompts();
    }
    
    public void Prompts(){
        System.out.print("Select an option from below: \n [1] Add Customer(please add an account before you do this) \n [2] Delete Customer \n [3] Add Account \n [4] Delete Account Customer \n [5] View Transactions Customer \n [0] Quit \n");
        int choice = input.nextInt(); 
        
        switch(choice){
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
                System.out.print("\n Please enter a vaild a choice: ");
        }       
    }
    public void addCustomer() {
        boolean b = true;
        while(b){
            System.out.println("To add a customer input the following information:");
            System.out.print("Customer ID (5 digits): ");
            int cID = input.nextInt();
            
            System.out.print("Account ID (5 digits): ");
            int aID = input.nextInt();
            
            System.out.print("Customer First Name (max. 50 characters): ");
            input.nextLine();
            String fN = input.nextLine();
                      
            System.out.print("Customer Last Name (max. 50 characters): ");
            String lN = input.nextLine();  
                                
            System.out.print("Customer Address (max. 200 characters): ");
            String a = input.nextLine();
            
            System.out.print("Customer Phone Number (in the format 123-456-7890: ");
            String pN = input.nextLine();
            
            System.out.print("Customer age (in years): ");
            int age = input.nextInt();
            
            System.out.print("Customer E-mail (max. 200 characters): ");
            input.nextLine();
            String email = input.nextLine();
           
             try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                            "username", "password");
                    Statement st = con.createStatement();
                    String sql = "INSERT INTO CUSTOMER (CUSTOMER_ID, ACCOUNT_ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, AGE, EMAIL) VALUES (?,?,?,?,?,?,?,?)";
                            
                    PreparedStatement preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setInt (1, cID);
                    preparedStmt.setInt (2, aID);
                    preparedStmt.setString (3, fN);
                    preparedStmt.setString (4, lN);
                    preparedStmt.setString (5, a);
                    preparedStmt.setString (6, pN);
                    preparedStmt.setInt (7, age);
                    preparedStmt.setString (8, email);
                    
                    preparedStmt.execute();
                    System.out.println(cID + " successfully added.");
                    con.close();
                    
                    Prompts();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
    }

    public void deleteCustomer() {

    }

    public void addAccount() {
    boolean b = true;
        while(b){
            System.out.println("To add a Account, input the following information:");
            System.out.print("Account ID (5 digits): ");
            int aID = input.nextInt();
            
            System.out.print("Branch ID (5 digits): ");
            int bID = input.nextInt();
            
            System.out.print("Account Type: (Credit or Debit): ");
            input.nextLine();
            String aT = input.nextLine();
                      
            System.out.print("Account Balance (max. 50 characters): ");
            Double bal = input.nextDouble();  
                                
            System.out.print("Rate (max. 4 digits ex. 0.234): ");
            Double r = input.nextDouble();
                     
            System.out.print("Status: ");
            input.nextLine();
            String s = input.nextLine();
            
             try {
                    Class.forName("oracle.jdbc.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl",
                            "username", "password");
                    Statement st = con.createStatement();
                    String sql = "INSERT INTO Accounts(Account_ID, Branch_ID, Account_Type, Balance, Rate, Status) VALUES (?,?,?,?,?,?)";
                            
                    PreparedStatement preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setInt (1, aID);
                    preparedStmt.setInt (2, bID);
                    preparedStmt.setString (3, aT);
                    preparedStmt.setDouble (4, bal);
                    preparedStmt.setDouble (5, r);
                    preparedStmt.setString (6, s);
                              
                    preparedStmt.execute();
                    System.out.println("Account ID " + aID + " successfully added.");
                    con.close();
                    
                    Prompts();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
    }

    public void deleteAccount() {
    }

    // If transaction = null output all transaction
    // If transaction != null, show only transaction with specific transactionID
    public void viewTransactions() {

    }
}
