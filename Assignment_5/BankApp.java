import java.util.*;

public class BankApp {
    public static void main(String[] args) {

        ArrayList<Customer> customers = new ArrayList<>();

        // Create customers
        Customer c1 = new Customer(1, "Vardhan");
        Customer c2 = new Customer(2, "Rahul");

        // Create accounts
        Account a1 = new SavingsAccount(1001, 50000, 4.5, 1000);
        Account a2 = new LoanAccount(2001, 20000);

        Account a3 = new SavingsAccount(1002, 80000, 5.0, 1500);

        // Link accounts to customers
        c1.addAccount(a1);
        c1.addAccount(a2);

        c2.addAccount(a3);

        // Add customers to list
        customers.add(c1);
        customers.add(c2);

        // Display consolidated info
        for (Customer c : customers) {
            c.viewAccounts();
            System.out.println("---------------------");
        }
    }
}