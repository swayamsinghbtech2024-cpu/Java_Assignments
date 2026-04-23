# Assignment 08: Inventory Management - Behavioral & Structural Design Patterns

## Description
This project implements a sophisticated Inventory Management System using three core **Gang of Four (GoF)** design patterns. By decoupling product types and centralizing management logic, the system demonstrates how to write scalable, maintainable, and industry-standard Java code.

## Design Patterns Implemented
1. **Singleton Pattern:** The `InventoryManager` class is implemented as a Singleton, ensuring that only one instance exists throughout the application lifecycle to maintain a consistent global state of the inventory.
2. **Adapter Pattern:** A `ProductAdapter` is used to allow `LegacyItem` (which has a different interface) to work seamlessly with the modern `Product` interface. This demonstrates how to integrate legacy code into new systems.
3. **Iterator Pattern:** The system utilizes the `Iterator` interface to traverse the collection of products without exposing the underlying `ArrayList` structure, promoting better encapsulation.

## Technical Specifications
- **Product Interface:** Defines the contract for all inventory items via the `displayDetails()` method.
- **Legacy Integration:** Adapts `LegacyItem` (using `itemId` and `description`) to the `Product` type.
- **Modern Logic:** `NewProduct` directly implements the interface for current-standard items.

## File Structure
- `Product.java`: The core interface.
- `LegacyItem.java`: The pre-existing class requiring adaptation.
- `ProductAdapter.java`: The bridge between legacy and modern interfaces.
- `NewProduct.java`: Modern implementation of the Product.
- `InventoryManager.java`: The Singleton manager class.
- `DesignPatternMain.java`: The driver class demonstrating pattern execution.

## How to Run
1. Navigate to `Assignment_08`.
2. Compile: `javac *.java`
3. Run: `java DesignPatternMain`
