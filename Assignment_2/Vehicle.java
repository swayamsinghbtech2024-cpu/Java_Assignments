class Vehicle {
    private String brandName;
    private String modelName;
    private String color;
    private double price;
    private String mfgCode;
    private double mileage; // Added to store the result of calculation

    // 1. Default Constructor
    public Vehicle() {}

    // 2. Parameterized Constructor
    public Vehicle(String brand, String model, String color, double price, String mfgCode) {
        this.brandName = brand;
        this.modelName = model;
        this.color = color;
        this.price = price;
        this.mfgCode = mfgCode;
    }

    // 3. Copy Constructor
    public Vehicle(Vehicle other) {
        this.brandName = other.brandName;
        this.modelName = other.modelName;
        this.color = other.color;
        this.price = other.price;
        this.mfgCode = other.mfgCode;
    }

    // Getters
    public String getBrandName() { return brandName; }
    public String getModelName() { return modelName; }
    public double getPrice() { return price; }
    public double getMileage() { return mileage; }

    // Mileage Calculation Logic
    public void mileageCalculation(double distanceCovered, double fuelUsed) {
        if (fuelUsed != 0) {
            this.mileage = distanceCovered / fuelUsed;
        } else {
            this.mileage = 0;
        }
    }
}