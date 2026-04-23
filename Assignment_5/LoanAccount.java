class LoanAccount extends Account {
    private double loanAmount;

    public LoanAccount(int accNo, double loanAmount) {
        super(accNo, 0, "Loan");
        this.loanAmount = loanAmount;
    }

    @Override
    public void deposit(double amount) {
        loanAmount -= amount;
        System.out.println("Loan repaid: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdraw not allowed in Loan Account");
    }

    @Override
    public void display() {
        System.out.println("Loan Account No: " + accNo + " | Remaining Loan: " + loanAmount);
    }
}