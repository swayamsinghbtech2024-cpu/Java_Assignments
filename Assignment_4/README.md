📐 Assignment 04: Mathematical Vector Operations & Exception Handling

📌 Overview

This project implements a Vector class in Java that performs fundamental linear algebra operations in 2D and 3D space. It emphasizes data integrity, dimension validation, and robust exception handling using custom-defined exceptions.

🚀 Features
✅ Dimensional Validation
Supports only 2D and 3D vectors
Throws custom exception for invalid dimensions
➕ Vector Addition
Performs component-wise addition
➖ Vector Subtraction
Performs component-wise subtraction
🔹 Dot Product
Computes scalar product of two vectors
⚠️ Custom Exception Handling
Handles invalid dimensions and mismatched operations
🧾 Robust Input Handling
Prevents non-numeric inputs using exception handling
🧠 Mathematical Concepts
➕ Vector Addition
V1 + V2 = (a1 + b1, a2 + b2, ..., an + bn)
➖ Vector Subtraction
V1 - V2 = (a1 - b1, a2 - b2, ..., an - bn)
🔹 Dot Product
V1 · V2 = Σ(ai * bi),  where i = 1 to n
🛠️ Tech Stack
Language: Java
Concepts Used:
Object-Oriented Programming (OOP)
Exception Handling
Arrays
Input Handling (Scanner)
📂 Project Structure
📁 Vector-Operations
│
├── Vector.java            # Core logic (vector operations & validation)
├── VectorException.java   # Custom exception class
├── VectorMain.java        # Driver class (user interaction)
└── README.md              # Project documentation
⚙️ Class Design
🔸 Vector Class
Stores vector components using an array
Performs all mathematical operations

Attributes:

private double[] components;
private int dimension;

Methods:

add(Vector v)
subtract(Vector v)
dotProduct(Vector v)
checkDimensions(Vector v)
🔸 VectorException Class

Custom exception for handling vector-related errors.

public class VectorException extends Exception {
    public VectorException(String message) {
        super(message);
    }
}
🔸 VectorMain Class
Handles user input
Executes operations
Manages exceptions
⚠️ Exception Handling Flow
1. Initialization Validation
Ensures dimension is either 2 or 3
Otherwise throws VectorException
2. Operation Validation
Ensures both vectors have same dimension
Otherwise throws VectorException
3. Input Validation
Handles invalid inputs using InputMismatchException
▶️ How to Run
Clone the repository:
git clone https://github.com/your-username/vector-operations.git
Compile the files:
javac *.java
Run the program:
java VectorMain
💡 Example

Input:

Vector 1: (1, 2, 3)
Vector 2: (4, 5, 6)

Output:

Addition: (5, 7, 9)
Subtraction: (-3, -3, -3)
Dot Product: 32
🎯 Learning Outcomes
Understanding vector mathematics in programming
Applying OOP principles in Java
Implementing custom exception handling
Writing robust and error-free input handling systems
