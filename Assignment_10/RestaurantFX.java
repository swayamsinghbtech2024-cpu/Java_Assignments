import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.*;

/**
 * JavaFX Menu-Driven CRUD Application for Restaurant & MenuItem tables.
 *
 * Requirements:
 *   - JDK 11+ with JavaFX 17+ on the module path
 *   - MySQL Connector/J on the classpath
 *
 * Compile & Run (example):
 *   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml \
 *         -cp mysql-connector-j-8.x.x.jar RestaurantFX.java
 *
 *   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml \
 *        -cp .:mysql-connector-j-8.x.x.jar RestaurantFX
 */
public class RestaurantFX extends Application {

    // ─── DB config ────────────────────────────────────────────────────────────
    private static final String URL  = "jdbc:mysql://localhost:3306/restaurantdb";
    private static final String USER = "root";
    private static final String PASS = "your_password";
    // ─────────────────────────────────────────────────────────────────────────

    private Connection conn;
    private TextArea   logArea;

    // ════════════════════════════════════════════════════════════════════════
    //  APPLICATION START
    // ════════════════════════════════════════════════════════════════════════

    @Override
    public void start(Stage primaryStage) {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "DB Connection Failed", e.getMessage());
            return;
        }

        primaryStage.setTitle("Restaurant CRUD — JavaFX");

        // ── Top menu bar ─────────────────────────────────────────────────────
        MenuBar menuBar = buildMenuBar();

        // ── Content area (switches between panels) ───────────────────────────
        StackPane contentArea = new StackPane();
        contentArea.setPadding(new Insets(16));
        contentArea.setStyle("-fx-background-color: #f8f9fa;");

        // ── Log / result area ────────────────────────────────────────────────
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(180);
        logArea.setFont(Font.font("Monospaced", 13));
        logArea.setStyle("-fx-background-color: #1e1e2e; -fx-text-fill: #cdd6f4;");
        logArea.setText("Ready. Select a menu option above.\n");

        Label logLabel = new Label("Output Log");
        logLabel.setFont(Font.font("System", FontWeight.BOLD, 13));

        VBox logBox = new VBox(4, logLabel, logArea);
        logBox.setPadding(new Insets(8, 16, 8, 16));

        // ── Welcome panel (default) ──────────────────────────────────────────
        Label welcome = new Label("🍽  Restaurant CRUD App");
        welcome.setFont(Font.font("System", FontWeight.BOLD, 26));
        welcome.setTextFill(Color.web("#2d6a4f"));
        Label hint = new Label("Use the Menu Bar above to perform operations.");
        hint.setFont(Font.font(15));
        VBox welcomeBox = new VBox(12, welcome, hint);
        welcomeBox.setAlignment(Pos.CENTER);
        contentArea.getChildren().add(welcomeBox);

        // ── Root layout ──────────────────────────────────────────────────────
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(contentArea);
        root.setBottom(logBox);

        // Wire menu actions
        wireMenuActions(menuBar, contentArea);

        primaryStage.setScene(new Scene(root, 900, 640));
        primaryStage.show();
    }

    @Override
    public void stop() {
        try { if (conn != null && !conn.isClosed()) conn.close(); } catch (SQLException ignored) {}
    }

    // ════════════════════════════════════════════════════════════════════════
    //  MENU BAR
    // ════════════════════════════════════════════════════════════════════════

    private MenuBar buildMenuBar() {
        // ── Restaurant menu ──────────────────────────────────────────────────
        Menu restMenu = new Menu("Restaurant");
        MenuItem rInsert = new MenuItem("Insert Restaurant");
        MenuItem rSelect = new MenuItem("View All Restaurants");
        MenuItem rUpdate = new MenuItem("Update Restaurant");
        MenuItem rDelete = new MenuItem("Delete Restaurant");
        restMenu.getItems().addAll(rInsert, rSelect, rUpdate, rDelete);

        // ── MenuItem menu ────────────────────────────────────────────────────
        Menu itemMenu = new Menu("Menu Item");
        MenuItem miInsert         = new MenuItem("Insert Menu Item");
        MenuItem miSelectAll      = new MenuItem("View All Menu Items");
        MenuItem miSelectCheap    = new MenuItem("Items with Price ≤ 100");
        MenuItem miSelectCafeJava = new MenuItem("Items from Cafe Java");
        MenuItem miUpdate         = new MenuItem("Update Price (≤100 → 200)");
        MenuItem miDelete         = new MenuItem("Delete Items starting with P");
        itemMenu.getItems().addAll(miInsert, miSelectAll, miSelectCheap,
                miSelectCafeJava, miUpdate, miDelete);

        // ── Help menu ────────────────────────────────────────────────────────
        Menu helpMenu = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> showAlert(Alert.AlertType.INFORMATION, "About",
                "Restaurant JDBC + JavaFX CRUD App\nBuilt with MySQL Connector/J"));
        helpMenu.getItems().add(about);

        MenuBar bar = new MenuBar();
        bar.getMenus().addAll(restMenu, itemMenu, helpMenu);
        return bar;
    }

    // ════════════════════════════════════════════════════════════════════════
    //  WIRE MENU ACTIONS
    // ════════════════════════════════════════════════════════════════════════

    private void wireMenuActions(MenuBar bar, StackPane content) {
        Menu restMenu = bar.getMenus().get(0);
        Menu itemMenu = bar.getMenus().get(1);

        // Restaurant actions
        restMenu.getItems().get(0).setOnAction(e -> showInsertRestaurantForm(content));
        restMenu.getItems().get(1).setOnAction(e -> showRestaurantTable(content));
        restMenu.getItems().get(2).setOnAction(e -> showUpdateRestaurantForm(content));
        restMenu.getItems().get(3).setOnAction(e -> showDeleteRestaurantForm(content));

        // MenuItem actions
        itemMenu.getItems().get(0).setOnAction(e -> showInsertMenuItemForm(content));
        itemMenu.getItems().get(1).setOnAction(e -> showMenuItemTable("SELECT * FROM MenuItem", content));
        itemMenu.getItems().get(2).setOnAction(e -> showMenuItemTable("SELECT * FROM MenuItem WHERE Price <= 100", content));
        itemMenu.getItems().get(3).setOnAction(e -> showCafeJavaItems(content));
        itemMenu.getItems().get(4).setOnAction(e -> doUpdatePrice(content));
        itemMenu.getItems().get(5).setOnAction(e -> doDeletePItems(content));
    }

    // ════════════════════════════════════════════════════════════════════════
    //  RESTAURANT PANELS
    // ════════════════════════════════════════════════════════════════════════

    private void showInsertRestaurantForm(StackPane content) {
        TextField nameField    = new TextField(); nameField.setPromptText("Restaurant Name");
        TextField addressField = new TextField(); addressField.setPromptText("Address");
        Button insertBtn = new Button("Insert");
        insertBtn.setStyle("-fx-background-color:#2d6a4f; -fx-text-fill:white; -fx-font-weight:bold;");

        insertBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String addr = addressField.getText().trim();
            if (name.isEmpty() || addr.isEmpty()) {
                log("ERROR: Fields cannot be empty.\n"); return;
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Restaurant (Name, Address) VALUES (?, ?)")) {
                ps.setString(1, name); ps.setString(2, addr);
                int rows = ps.executeUpdate();
                log("Restaurant inserted. Rows affected: " + rows + "\n");
                nameField.clear(); addressField.clear();
            } catch (SQLException ex) { log("ERROR: " + ex.getMessage() + "\n"); }
        });

        VBox form = formBox("Insert Restaurant", nameField, addressField, insertBtn);
        switchContent(content, form);
    }

    private void showRestaurantTable(StackPane content) {
        TableView<ObservableList<String>> table = new TableView<>();
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        String[] cols = {"Id", "Name", "Address"};
        for (int i = 0; i < cols.length; i++) {
            final int idx = i;
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(cols[i]);
            col.setCellValueFactory(p -> new javafx.beans.property.SimpleStringProperty(
                    p.getValue().get(idx)));
            col.setPrefWidth(i == 0 ? 60 : 250);
            table.getColumns().add(col);
        }

        try (Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery("SELECT * FROM Restaurant")) {
            while (rs.next()) {
                data.add(FXCollections.observableArrayList(
                    String.valueOf(rs.getInt("Id")),
                    rs.getString("Name"),
                    rs.getString("Address")));
            }
        } catch (SQLException ex) { log("ERROR: " + ex.getMessage() + "\n"); }

        table.setItems(data);
        log("Loaded " + data.size() + " Restaurant records.\n");

        VBox box = new VBox(8, titleLabel("All Restaurants"), table);
        box.setPadding(new Insets(10));
        switchContent(content, box);
    }

    private void showUpdateRestaurantForm(StackPane content) {
        TextField idField   = new TextField(); idField.setPromptText("Restaurant ID to update");
        TextField nameField = new TextField(); nameField.setPromptText("New Name");
        TextField addrField = new TextField(); addrField.setPromptText("New Address");
        Button btn = new Button("Update");
        btn.setStyle("-fx-background-color:#e9c46a; -fx-text-fill:#1e1e2e; -fx-font-weight:bold;");

        btn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String addr = addrField.getText().trim();
                if (name.isEmpty() || addr.isEmpty()) { log("ERROR: Fill all fields.\n"); return; }

                try (PreparedStatement ps = conn.prepareStatement(
                        "UPDATE Restaurant SET Name=?, Address=? WHERE Id=?")) {
                    ps.setString(1, name); ps.setString(2, addr); ps.setInt(3, id);
                    log("Restaurant updated. Rows affected: " + ps.executeUpdate() + "\n");
                }
            } catch (NumberFormatException nfe) { log("ERROR: Invalid ID.\n");
            } catch (SQLException ex)          { log("ERROR: " + ex.getMessage() + "\n"); }
        });

        switchContent(content, formBox("Update Restaurant", idField, nameField, addrField, btn));
    }

    private void showDeleteRestaurantForm(StackPane content) {
        TextField idField = new TextField(); idField.setPromptText("Restaurant ID to delete");
        Button btn = new Button("Delete");
        btn.setStyle("-fx-background-color:#e63946; -fx-text-fill:white; -fx-font-weight:bold;");

        btn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                try (PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM Restaurant WHERE Id=?")) {
                    ps.setInt(1, id);
                    log("Restaurant deleted. Rows affected: " + ps.executeUpdate() + "\n");
                    idField.clear();
                }
            } catch (NumberFormatException nfe) { log("ERROR: Invalid ID.\n");
            } catch (SQLException ex)           { log("ERROR: " + ex.getMessage() + "\n"); }
        });

        switchContent(content, formBox("Delete Restaurant by ID", idField, btn));
    }

    // ════════════════════════════════════════════════════════════════════════
    //  MENU ITEM PANELS
    // ════════════════════════════════════════════════════════════════════════

    private void showInsertMenuItemForm(StackPane content) {
        TextField nameField  = new TextField(); nameField.setPromptText("Item Name");
        TextField priceField = new TextField(); priceField.setPromptText("Price");
        TextField resIdField = new TextField(); resIdField.setPromptText("Restaurant ID (ResId)");
        Button btn = new Button("Insert");
        btn.setStyle("-fx-background-color:#2d6a4f; -fx-text-fill:white; -fx-font-weight:bold;");

        btn.setOnAction(e -> {
            try {
                String name  = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int    resId = Integer.parseInt(resIdField.getText().trim());
                if (name.isEmpty()) { log("ERROR: Name is empty.\n"); return; }

                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO MenuItem (Name, Price, ResId) VALUES (?, ?, ?)")) {
                    ps.setString(1, name); ps.setDouble(2, price); ps.setInt(3, resId);
                    log("MenuItem inserted. Rows affected: " + ps.executeUpdate() + "\n");
                    nameField.clear(); priceField.clear(); resIdField.clear();
                }
            } catch (NumberFormatException nfe) { log("ERROR: Invalid number format.\n");
            } catch (SQLException ex)           { log("ERROR: " + ex.getMessage() + "\n"); }
        });

        switchContent(content, formBox("Insert Menu Item", nameField, priceField, resIdField, btn));
    }

    private void showMenuItemTable(String sql, StackPane content) {
        TableView<ObservableList<String>> table = new TableView<>();
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        String[] cols = {"Id", "Name", "Price", "ResId"};
        for (int i = 0; i < cols.length; i++) {
            final int idx = i;
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(cols[i]);
            col.setCellValueFactory(p -> new javafx.beans.property.SimpleStringProperty(
                    p.getValue().get(idx)));
            col.setPrefWidth(idx == 0 || idx == 3 ? 70 : 200);
            table.getColumns().add(col);
        }

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                data.add(FXCollections.observableArrayList(
                    String.valueOf(rs.getInt("Id")),
                    rs.getString("Name"),
                    String.format("%.2f", rs.getDouble("Price")),
                    String.valueOf(rs.getInt("ResId"))));
            }
        } catch (SQLException ex) { log("ERROR: " + ex.getMessage() + "\n"); }

        table.setItems(data);
        log("Loaded " + data.size() + " MenuItem records.\n");

        String title = sql.contains("100") ? "Menu Items (Price ≤ 100)" : "All Menu Items";
        VBox box = new VBox(8, titleLabel(title), table);
        box.setPadding(new Insets(10));
        switchContent(content, box);
    }

    private void showCafeJavaItems(StackPane content) {
        String sql = """
            SELECT mi.Id, mi.Name, mi.Price, r.Name AS Restaurant
            FROM MenuItem mi
            JOIN Restaurant r ON mi.ResId = r.Id
            WHERE r.Name = 'Cafe Java'
        """;

        TableView<ObservableList<String>> table = new TableView<>();
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

        String[] cols = {"Id", "Item Name", "Price", "Restaurant"};
        for (int i = 0; i < cols.length; i++) {
            final int idx = i;
            TableColumn<ObservableList<String>, String> col = new TableColumn<>(cols[i]);
            col.setCellValueFactory(p -> new javafx.beans.property.SimpleStringProperty(
                    p.getValue().get(idx)));
            col.setPrefWidth(idx == 0 ? 60 : 200);
            table.getColumns().add(col);
        }

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                data.add(FXCollections.observableArrayList(
                    String.valueOf(rs.getInt("Id")),
                    rs.getString("Name"),
                    String.format("%.2f", rs.getDouble("Price")),
                    rs.getString("Restaurant")));
            }
        } catch (SQLException ex) { log("ERROR: " + ex.getMessage() + "\n"); }

        table.setItems(data);
        log("Loaded " + data.size() + " items from Cafe Java.\n");

        VBox box = new VBox(8, titleLabel("Menu Items from Cafe Java"), table);
        box.setPadding(new Insets(10));
        switchContent(content, box);
    }

    private void doUpdatePrice(StackPane content) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Update all MenuItem prices ≤ 100 to 200?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                try (Statement stmt = conn.createStatement()) {
                    int rows = stmt.executeUpdate("UPDATE MenuItem SET Price = 200 WHERE Price <= 100");
                    log("UPDATE complete. Rows affected: " + rows + "\n");
                    showMenuItemTable("SELECT * FROM MenuItem", content);
                } catch (SQLException ex) { log("ERROR: " + ex.getMessage() + "\n"); }
            }
        });
    }

    private void doDeletePItems(StackPane content) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete all MenuItems whose name starts with 'P'?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                try (Statement stmt = conn.createStatement()) {
                    int rows = stmt.executeUpdate("DELETE FROM MenuItem WHERE Name LIKE 'P%'");
                    log("DELETE complete. Rows deleted: " + rows + "\n");
                    showMenuItemTable("SELECT * FROM MenuItem", content);
                } catch (SQLException ex) { log("ERROR: " + ex.getMessage() + "\n"); }
            }
        });
    }

    // ════════════════════════════════════════════════════════════════════════
    //  UTILITY HELPERS
    // ════════════════════════════════════════════════════════════════════════

    private void switchContent(StackPane content, javafx.scene.Node node) {
        content.getChildren().setAll(node);
    }

    private VBox formBox(String title, javafx.scene.Node... nodes) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));
        box.setMaxWidth(460);
        box.setAlignment(Pos.TOP_LEFT);
        box.getChildren().add(titleLabel(title));
        box.getChildren().addAll(nodes);
        for (javafx.scene.Node n : nodes) {
            if (n instanceof TextField tf) {
                tf.setPrefWidth(380);
                tf.setStyle("-fx-font-size:14;");
            }
        }
        return box;
    }

    private Label titleLabel(String text) {
        Label l = new Label(text);
        l.setFont(Font.font("System", FontWeight.BOLD, 18));
        l.setTextFill(Color.web("#2d6a4f"));
        return l;
    }

    private void log(String msg) {
        logArea.appendText(msg);
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type, msg);
        alert.setTitle(title);
        alert.showAndWait();
    }

    // ════════════════════════════════════════════════════════════════════════
    //  MAIN
    // ════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        launch(args);
    }
}
