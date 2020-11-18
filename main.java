import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.print("\n **********************************\n");
            System.out.print("*        Welcome to GBO DBMS!     *\n");
            System.out.print("**********************************\n");
            System.out.print("Select an option from below: \n [1] Manager \n [2] Customer \n [0] Quit \n");
            System.out.print("My choice: ");
            int choice = input.nextInt();

            if (choice == 1) {

            } else if (choice == 2) {

            } else if (choice == 0) {

                run = false;
            } else {
                System.out.print("Incorrect option, please try again.");
            }
        }
    }
}