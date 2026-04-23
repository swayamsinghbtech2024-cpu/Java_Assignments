class SavingsAccount extends Account {
    private double interestRate;
    private double minBalance;

    public SavingsAccount(int accNo, double balance, double interestRate, double minBalance) {
        super(accNo, balance, "Savings");
        this.interestRate = interestRate;
        this.minBalance = minBalance;
    }

    // Override withdraw
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Withdrawn from Savings: " + amount);
        } else {
            System.out.println("Cannot withdraw! Minimum balance required.");
        }
    }
}