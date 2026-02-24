public class Book {
    public String title;
    public String author;
    public double price;
    public String genre;
    public String ISBN;

    // Parameterized Constructor with Validation
    public Book(String t, String a, double p, String g, String i) throws InvalidPriceException, InvalidTitleException {
        if (p < 0) {
            throw new InvalidPriceException("Invalid price: " + p + " For book: " + t + ". Price cannot be negative.");
        }
        if (t == null || t.trim().isEmpty()) {
            throw new InvalidTitleException("Invalid title for book with ISBN: " + i + ". Title cannot be empty.");
        }
        this.title = t;
        this.author = a;
        this.price = p;
        this.genre = g;
        this.ISBN = i;
    }

    // Default Constructor
    public Book() {
        this.title = "Generic Title";
        this.author = "Unknown Author";
        this.price = 0.0;
        this.genre = "Uncategorized";
        this.ISBN = "000-0000000000";
    }

    // Copy Constructor
    public Book(Book b) {
        this.title = b.title;
        this.author = b.author;
        this.price = b.price;
        this.genre = b.genre;
        this.ISBN = b.ISBN;
    }
}