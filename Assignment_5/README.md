# Assignment 05: Banking System - Inheritance & Polymorphism

## Description
This project simulates a Banking Application to demonstrate the power of **Object-Oriented Inheritance** and **Dynamic Method Dispatch**. By utilizing a base `Account` class and specialized subclasses like `SavingsAccount` and `LoanAccount`, the system enforces different financial rules (like minimum balance or restricted withdrawals) through **Method Overriding**. It also showcases **Association**, where a `Customer` can own and manage multiple account types.

## Key Features
- **Inheritance Hierarchy:** A base `Account` class provides common attributes (`accNo`, `balance`), while specialized classes extend and refine its behavior.
- **Method Overriding (Polymorphism):** - **SavingsAccount:** Overrides `withdraw()` to enforce a minimum balance requirement.
    - **LoanAccount:** Replaces standard `deposit()` with loan repayment logic and restricts withdrawals entirely.
- **Object Association:** The `Customer` class maintains a `List<Account>`, demonstrating a "Has-A" relationship between users and their financial products.
- **Consolidated Reporting:** Iterates through a collection of customers to display all linked accounts in a single view.

## Technical Specifications
### 1. Class Hierarchy
- **Account (Base):** Provides the foundational structure for all banking products.
- **SavingsAccount (Child):** Adds `interestRate` and `minBalance` constraints.
- **LoanAccount (Child):** Tracks `loanAmount` instead of a traditional balance and modifies repayment logic.

### 2. Implementation Concepts
- **Constructor Chaining:** Uses the `super()` keyword to initialize base class members from child constructors.
- **Access Modifiers:** Utilizes `protected` members to allow child classes direct access to critical data like `balance` while keeping them hidden from external classes.
- **Dynamic Binding:** The `BankApp` calls `acc.display()`, and Java automatically determines whether to run the Savings or Loan version of the method at runtime.

## File Structure
- `Account.java`: The parent class defining general account behavior.
- `SavingsAccount.java`: Specialized class for savings logic.
- `LoanAccount.java`: Specialized class for loan management.
- `Customer.java`: Manages customer data and their associated accounts.
- `BankApp.java`: The entry point that builds the customer database and executes transactions.
