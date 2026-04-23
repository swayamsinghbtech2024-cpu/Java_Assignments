# Assignment 06: Employee Payroll System - Abstraction & Multi-level Inheritance

## Description
This project implements a comprehensive Employee Management and Payroll system to demonstrate advanced **Object-Oriented Programming (OOP)** concepts in Java. It utilizes an **Abstract Base Class** to define a common interface for all employee types while enforcing specific salary calculation logic through **Method Overriding**. The architecture highlights how different employment models (Full-Time, Contract, and Managerial) can be managed under a unified hierarchy.

## Key Features
- **Abstraction:** The `Employee` class is declared `abstract`, containing abstract methods like `calcCTC()` and `calcBenefits()`. This ensures that every specific employee type *must* provide its own calculation logic.
- **Multi-level Inheritance:** - `Employee` (Root) → `FullTimeEmployee` (Child) → `Manager` (Grandchild).
    - Demonstrates how specialized roles can inherit and extend the attributes of more general roles.
- **Polymorphism & Super:** The `Manager` class utilizes the `super` keyword to reuse the benefit calculations of a `FullTimeEmployee` while adding manager-specific perks.
- **Encapsulation:** Sensitive data members like `empID` and `pan` are kept `private` and accessed through standard **Getter and Setter** methods.

## Technical Specifications
### 1. Calculation Logic
- **Full-Time Employee:** $CTC = Base Salary + Performance Bonus + Benefits (Insurance + PF)$.
- **Contract Employee:** $CTC = (No. of Hours \times Hourly Rate) + Minimal Benefits$.
- **Manager:** $CTC = FullTimeBase + TA + Education Allowance + Managerial Benefits$.

### 2. Data Modeling
- **Payroll Class:** Acts as a data container for all financial parameters, allowing the calculation methods to remain clean and focused on logic rather than data storage.

## File Structure
- `Employee.java`: The abstract root class defining core attributes and abstract methods.
- `FullTimeEmployee.java`: Child class representing permanent staff.
- `ContractEmployee.java`: Child class representing hourly-wage staff.
- `Manager.java`: Grandchild class extending full-time capabilities with additional allowances.
- `Payroll.java`: A helper class containing salary and benefit constants.
- `MainForEmployee.java`: The driver class that simulates payroll processing for different roles.
