import java.util.*;

class Customer {
    private int custId;
    private String name;
    private String pan;
    private String aadhar;
    private String address;
    private String phoneNo;

    private List<Account> accounts = new ArrayList<>();

    // Constructor
    public Customer(int custId, String name) {
        this.custId = custId;
        this.name = name;
    }

    // Add account
    public void addAccount(Account acc) {
        accounts.add(acc);
    }

    // View all accounts
    public void viewAccounts() {
        System.out.println("Customer: " + name);
        for (Account acc : accounts) {
            acc.display();
        }
    }

    public int getCustId() {
        return custId;
    }
}