import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Singleton: get single instance of InventoryManager
        InventoryManager manager = InventoryManager.getInstance();

        // Add NewProduct items directly
        manager.addProduct(new NewProduct("Wireless Keyboard"));
        manager.addProduct(new NewProduct("USB-C Hub"));

        // Adapter: wrap LegacyItem objects so they work as Products
        manager.addProduct(new ProductAdapter(new LegacyItem(101, "Old CRT Monitor")));
        manager.addProduct(new ProductAdapter(new LegacyItem(102, "Vintage Mechanical Mouse")));

        // Iterator: traverse the inventory
        Iterator<Product> iterator = manager.returnInventory();
        System.out.println("=== Inventory ===");
        while (iterator.hasNext()) {
            Product product = iterator.next();
            product.displayDetails();
        }
    }
}
