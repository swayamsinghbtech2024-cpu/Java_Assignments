Assignment 03: Library Management System with Collections & Exceptions
Description:
This project implements a robust book management system using the Java Collections Framework. It focuses on dynamic data storage and strictly enforces data integrity through User-Defined Exceptions. The program is designed to intercept invalid data entry (such as negative pricing) at the object construction level to maintain a clean and reliable data set.

Key Features:
Custom Exception Handling: Implementation of InvalidPriceException to prevent the creation of Book objects with invalid financial states.
Dynamic Data Storage: Utilizes an ArrayList<Book> to manage a flexible inventory of books.
Robust Constructor Logic: One of the Book constructors is specifically designed to throw a custom exception if the price is less than zero.
Functional Programming Elements: Demonstrates the use of the forEach() method with lambda expressions to filter and display books of a specific genre (e.g., "Fiction").
Statistical Processing: Iterates through the collection to calculate the average price of all valid book objects.
File Structure:
Book.java: The core entity class containing attributes, constructors, and validation logic.
InvalidPriceException.java: A custom checked exception class.
ArrayListOfBooks.java: The driver class containing the main method, object creation, and list processing logic.
