import java.sql.*;

public class RestaurantJDBC {

    // ─── Change these to match your MySQL setup ───
    static final String URL  = "jdbc:mysql://localhost:3306/restaurantdb";
    static final String USER = "root";
    static final String PASS = "your_password";
    // ─────────────────────────────────────────────

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.println("========================================");
            System.out.println("   RESTAURANT JDBC CRUD OPERATIONS");
            System.out.println("========================================\n");

            createTables(conn);
            insertRecords(conn);
            selectMenuItemsUnder100(conn);
            selectMenuItemsByCafeJava(conn);
            updatePricesUnder100(conn);
            deletePItems(conn);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ─── 1. CREATE TABLES ────────────────────────────────────────────────────

    static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("DROP TABLE IF EXISTS MenuItem");
        stmt.executeUpdate("DROP TABLE IF EXISTS Restaurant");

        stmt.executeUpdate("""
            CREATE TABLE Restaurant (
                Id      INT PRIMARY KEY AUTO_INCREMENT,
                Name    VARCHAR(100) NOT NULL,
                Address VARCHAR(200) NOT NULL
            )
        """);

        stmt.executeUpdate("""
            CREATE TABLE MenuItem (
                Id     INT PRIMARY KEY AUTO_INCREMENT,
                Name   VARCHAR(100) NOT NULL,
                Price  DECIMAL(8,2) NOT NULL,
                ResId  INT,
                FOREIGN KEY (ResId) REFERENCES Restaurant(Id)
                    ON DELETE CASCADE
            )
        """);

        System.out.println("Tables created successfully.\n");
        stmt.close();
    }

    // ─── 2. INSERT 10 RECORDS IN EACH TABLE ─────────────────────────────────

    static void insertRecords(Connection conn) throws SQLException {
        System.out.println("----------------------------------------");
        System.out.println("OPERATION: INSERT RECORDS");
        System.out.println("----------------------------------------");

        // Insert 10 restaurants
        String insertRest = "INSERT INTO Restaurant (Name, Address) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertRest)) {
            Object[][] restaurants = {
                {"Cafe Java",       "12 MG Road, Pune"},
                {"Spice Garden",    "34 FC Road, Pune"},
                {"Pizza Palace",    "56 Baner Road, Pune"},
                {"The Noodle Bar",  "78 Koregaon Park, Pune"},
                {"Burger Hut",      "90 Viman Nagar, Pune"},
                {"Saffron Sweets",  "11 Camp Area, Pune"},
                {"Green Leaf",      "22 Aundh, Pune"},
                {"Mumbai Masala",   "33 Kothrud, Pune"},
                {"Pasta Point",     "44 Hadapsar, Pune"},
                {"The Grill House", "55 Wakad, Pune"}
            };

            for (Object[] r : restaurants) {
                ps.setString(1, (String) r[0]);
                ps.setString(2, (String) r[1]);
                ps.executeUpdate();
            }
        }
        System.out.println("10 Restaurant records inserted.");

        // Insert 10 menu items (ResId 1 = Cafe Java, 2 = Spice Garden, etc.)
        String insertItem = "INSERT INTO MenuItem (Name, Price, ResId) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertItem)) {
            Object[][] items = {
                {"Espresso",          80.00,  1},
                {"Pasta Arrabbiata",  250.00, 1},
                {"Paneer Tikka",      180.00, 2},
                {"Pav Bhaji",          90.00, 2},
                {"Pepperoni Pizza",   350.00, 3},
                {"Plain Dosa",         60.00, 3},
                {"Pad Thai",          220.00, 4},
                {"Poha",               50.00, 4},
                {"Veg Burger",         99.00, 5},
                {"Cold Coffee",        75.00, 1}
            };

            for (Object[] item : items) {
                ps.setString(1, (String) item[0]);
                ps.setDouble(2, (Double) item[1]);
                ps.setInt(3,    (Integer) item[2]);
                ps.executeUpdate();
            }
        }
        System.out.println("10 MenuItem records inserted.\n");
    }

    // ─── 3. SELECT: price <= 100 ─────────────────────────────────────────────

    static void selectMenuItemsUnder100(Connection conn) throws SQLException {
        System.out.println("----------------------------------------");
        System.out.println("OPERATION: SELECT MenuItems where Price <= 100");
        System.out.println("----------------------------------------");

        String sql = "SELECT * FROM MenuItem WHERE Price <= 100";
        try (Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            printMenuItemTable(rs);
        }
        System.out.println();
    }

    // ─── 4. SELECT: Items from "Cafe Java" ──────────────────────────────────

    static void selectMenuItemsByCafeJava(Connection conn) throws SQLException {
        System.out.println("----------------------------------------");
        System.out.println("OPERATION: SELECT MenuItems from Restaurant = 'Cafe Java'");
        System.out.println("----------------------------------------");

        String sql = """
            SELECT mi.Id, mi.Name, mi.Price, r.Name AS Restaurant
            FROM MenuItem mi
            JOIN Restaurant r ON mi.ResId = r.Id
            WHERE r.Name = 'Cafe Java'
        """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            System.out.printf("%-5s %-25s %-10s %-20s%n",
                "ID", "ITEM NAME", "PRICE", "RESTAURANT");
            System.out.println("-".repeat(65));

            int count = 0;
            while (rs.next()) {
                System.out.printf("%-5d %-25s %-10.2f %-20s%n",
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getDouble("Price"),
                    rs.getString("Restaurant"));
                count++;
            }
            if (count == 0) System.out.println("No records found.");
        }
        System.out.println();
    }

    // ─── 5. UPDATE: price <= 100  →  price = 200 ────────────────────────────

    static void updatePricesUnder100(Connection conn) throws SQLException {
        System.out.println("----------------------------------------");
        System.out.println("OPERATION: UPDATE Price = 200 WHERE Price <= 100");
        System.out.println("----------------------------------------");

        String update = "UPDATE MenuItem SET Price = 200 WHERE Price <= 100";
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(update);
            System.out.println("Rows updated: " + rows);
        }

        System.out.println("\nAll MenuItem records after update:");
        try (Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery("SELECT * FROM MenuItem")) {
            printMenuItemTable(rs);
        }
        System.out.println();
    }

    // ─── 6. DELETE: name starts with 'P' ────────────────────────────────────

    static void deletePItems(Connection conn) throws SQLException {
        System.out.println("----------------------------------------");
        System.out.println("OPERATION: DELETE MenuItems where Name LIKE 'P%'");
        System.out.println("----------------------------------------");

        String delete = "DELETE FROM MenuItem WHERE Name LIKE 'P%'";
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(delete);
            System.out.println("Rows deleted: " + rows);
        }

        System.out.println("\nRemaining MenuItem records after delete:");
        try (Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery("SELECT * FROM MenuItem")) {
            printMenuItemTable(rs);
        }
        System.out.println();
    }

    // ─── UTILITY: print MenuItem ResultSet as table ──────────────────────────

    static void printMenuItemTable(ResultSet rs) throws SQLException {
        System.out.printf("%-5s %-25s %-10s %-8s%n",
            "ID", "NAME", "PRICE", "RES_ID");
        System.out.println("-".repeat(52));

        int count = 0;
        while (rs.next()) {
            System.out.printf("%-5d %-25s %-10.2f %-8d%n",
                rs.getInt("Id"),
                rs.getString("Name"),
                rs.getDouble("Price"),
                rs.getInt("ResId"));
            count++;
        }
        if (count == 0) System.out.println("No records found.");
    }
}
