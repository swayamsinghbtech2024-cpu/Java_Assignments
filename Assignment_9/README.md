# Assignment 09: Restaurant Management System - JDBC & MySQL

## Description
This project demonstrates **Database Connectivity** in Java using **JDBC (Java Database Connectivity)**. It involves creating a relational schema in MySQL and performing complex CRUD (Create, Read, Update, Delete) operations using SQL queries executed through Java.

## Database Schema
- **Restaurant Table:** `Id` (PK), `Name`, `Address`.
- **MenuItem Table:** `Id` (PK), `Name`, `Price`, `ResId` (FK linked to Restaurant).

## Key Operations
- **Batch Insertion:** Populates both tables with 10 records each to simulate a production environment.
- **Conditional Selection:** - Filters items based on price thresholds ($\le 100$).
    - Performs a **Join-like operation** to find menu items for a specific restaurant ("Cafe Java").
- **Bulk Updates:** Modifies pricing for specific subsets of data.
- **Targeted Deletion:** Uses String pattern matching (`LIKE 'P%'`) to remove specific records.

## Technical Requirements
- MySQL Server.
- MySQL Connector/J (JDBC Driver).
