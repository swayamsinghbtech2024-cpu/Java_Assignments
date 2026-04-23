# Assignment 10: JavaFX GUI for Restaurant Database CRUD

## Description
This experiment extends the JDBC functionality of Assignment 09 by adding a **Graphical User Interface (GUI)** using **JavaFX**. It transforms a console-based database tool into a user-friendly, menu-driven desktop application.

## Key Features
- **Interactive UI:** A complete menu-driven interface to perform database operations without writing SQL commands manually.
- **Dynamic Forms:** Input fields for inserting new Restaurant and MenuItem records.
- **Live Result View:** Displays database query results directly within the JavaFX application window.
- **Event-Driven Programming:** Uses JavaFX event handlers to trigger JDBC logic based on button clicks and menu selections.

## Implementation Details
- **Architecture:** Separates the UI logic (JavaFX) from the Data Access logic (JDBC).
- **CRUD Integration:**
    - **Insert:** TextFields capture user data to generate `INSERT` queries.
    - **Select/Search:** ResultSets are parsed and displayed in the UI.
    - **Update/Delete:** Interactive buttons trigger the modification of specific database records.
