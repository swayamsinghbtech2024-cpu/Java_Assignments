import java.util.ArrayList;

public class ArrayListOfBooks {

    public static void main(String[] args) {
        ArrayList<Book> library = new ArrayList<>();

        //EXCEPTIONS (invalid entries)
        try {
            // Fails due to empty title
            library.add(new Book(" ", "Harper Lee", 7.99, "Classic", "978-001"));
            System.out.println("\nSuccessfully added valid book.");
        } catch (Exception e) {
            System.out.println("CAUGHT: " + e.getMessage());
        }

        try {
            // Fails due to negative price
            library.add(new Book("Dark Matter", "Blake Crouch", -12.50, "Sci-Fi", "978-002"));
            System.out.println("\nSuccessfully added valid book.");
        } catch (Exception e) {
            System.out.println("CAUGHT: " + e.getMessage());
        }

        //VALID ENTRIES
        try {
            library.add(new Book("1984", "George Orwell", 9.99, "Dystopian", "978-045"));
            System.out.println("\nSuccessfully added valid book.");
            library.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.50, "Classic", "978-074"));
            System.out.println("\nSuccessfully added valid book.");
            library.add(new Book("The Alchemist", "Paulo Coelho", 14.25, "Adventure", "978-006"));
            System.out.println("\nSuccessfully added valid book.");
            library.add(new Book("Atomic Habits", "James Clear", 18.00, "Self-Help", "978-159"));
            System.out.println("\nSuccessfully added valid book.");
            
            
        } catch (Exception e) {
            System.out.println("UNEXPECTED ERROR: " + e.getMessage());
        }

        
        
        System.out.println("\n--- PRINTING VIA FOREACH METHOD ---");
        library.forEach(b -> {
            System.out.println("Book: " + b.title + " | Written by: " + b.author);
        });

        System.out.println();

        System.out.println("\n--- PRINTING VIA FOR LOOP ---");
        System.out.println("\n--- FINAL LIBRARY INVENTORY ---");
        System.out.printf("%-20s | %-20s | %-10s%n", "TITLE", "AUTHOR", "PRICE");
        System.out.println("------------------------------------------------------------");
        for (Book b : library) {
            System.out.printf("%-20s | %-20s | $%.2f%n", b.title, b.author, b.price);
        }

        double avg = averagePrice(library);
        System.out.printf("\nAverage Price of all %d valid books: $%.2f%n", library.size(), avg);
    }

    public static double averagePrice(ArrayList<Book> books) {
        if (books == null || books.isEmpty()) return 0.0;
        double sum = 0.0;
        for (Book b : books) {
            sum += b.price;
        }
        return sum / books.size();
    }
}