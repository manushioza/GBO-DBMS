import java.util.Scanner;

public class ManagerController {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("\n **********************************\n");
        System.out.print("*             GBO DBMS!             *\n");
        System.out.print("**********************************\n");
        System.out.print("Please Enter your username:");
        String username = input.nextLine();

        System.out.print("Please Enter your password:");
        String password = input.nextLine();

        System.out.print("Select an option from below: \n [1] Add Customer \n [2] Delete Customer \n [3] Add Account \n [4] Delete Account Customer \n [5] View Transactions Customer \n [0] Quit \n");
    }
}
