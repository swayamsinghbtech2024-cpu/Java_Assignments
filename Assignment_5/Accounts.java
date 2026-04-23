class Account {
    protected int accNo;
    protected double balance;
    protected String accType;

    public Account(int accNo, double balance, String accType) {
        this.accNo = accNo;
        this.balance = balance;
        this.accType = accType;
    }

    // Deposit
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void display() {
        System.out.println("Account No: " + accNo + " | Balance: " + balance + " | Type: " + accType);
    }
}