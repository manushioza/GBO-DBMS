import java.util.Scanner;
import java.sql.*;

 class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.print("\n **********************************\n");
            System.out.print("*       Welcome to GBO DBMS!     *\n");
            System.out.print("**********************************\n");
            System.out.print("Select an option from below: \n [1] Manager \n [2] Customer \n [0] Quit \n");
            System.out.print("My choice: ");
            int choice = input.nextInt();

            if (choice == 1) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con =  DriverManager.getConnection("jdbc:oracle:thin:@oracle.scs.ryerson.ca:1521:orcl", "", "");
                    Statement st = con.createStatement();
                    String sql = "SELECT * FROM Accounts";
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()) {
                        System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getFloat("Balance")+ " " + rs.getFloat("Rate") + " " + rs.getString(6));
                    }
                    con.close();
                    }catch(Exception e) {
                        System.out.println(e);
                    }
            } else if (choice == 2) {

            } else if (choice == 0) {
                run = false;
            } else {
                System.out.print("Incorrect option, please try again.");
            }
        }
        input.close();
    }
}
