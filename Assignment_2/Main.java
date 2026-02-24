public class Main {
    public static void main(String[] args) {
        // Create multiple objects using the Parameterized Constructor
        Vehicle v1 = new Vehicle("Toyota", "Camry", "Matte Black", 4500000, "TYT2024");
        Vehicle v2 = new Vehicle("Honda", "Civic", "Ruby Red", 3500000, "HND2024");
        
        // Use Copy Constructor for a third vehicle
        Vehicle v3 = new Vehicle(v2); 
        
        // Calculate mileage for them
        v1.mileageCalculation(640, 40);
        v2.mileageCalculation(520, 25);
        v3.mileageCalculation(400, 20);

        // Store them in an Array
        Vehicle[] garage = {v1, v2, v3};

        // Print in Tabular Format
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.printf("%-15s | %-15s | %-15s | %-10s %n", "Brand", "Model", "Price (INR)", "Mileage");
        System.out.println("-----------------------------------------------------------------------");

        for (Vehicle v : garage) {
            System.out.printf("%-15s | %-15s | %-15.2f | %-10.2f km/l %n", 
                              v.getBrandName(), v.getModelName(), v.getPrice(), v.getMileage());
        }
        System.out.println("-----------------------------------------------------------------------");
    }
}