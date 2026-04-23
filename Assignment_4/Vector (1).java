// Vector Class
class Vector {

    private int dimension;
    private double[] data;

    // Constructor (Only 2D or 3D allowed)
    public Vector(int dimension) throws VectorException {
        if (dimension != 2 && dimension != 3) {
            throw new VectorException("Vector dimension must be 2D or 3D only.");
        }
        this.dimension = dimension;
        data = new double[dimension];
    }

    // Set value
    public void setValue(int index, double value) {
        data[index] = value;
    }

    // Private helper method to check dimensions
    private void checkDimensions(Vector other) throws VectorException {
        if (this.dimension != other.dimension) {
            throw new VectorException("Both vectors must have the same dimension.");
        }
    }

    // Addition
    public Vector add(Vector other) throws VectorException {
        checkDimensions(other);

        Vector result = new Vector(dimension);
        for (int i = 0; i < dimension; i++) {
            result.data[i] = this.data[i] + other.data[i];
        }
        return result;
    }

    // Subtraction
    public Vector subtract(Vector other) throws VectorException {
        checkDimensions(other);

        Vector result = new Vector(dimension);
        for (int i = 0; i < dimension; i++) {
            result.data[i] = this.data[i] - other.data[i];
        }
        return result;
    }

    // Dot Product
    public double dotProduct(Vector other) throws VectorException {
        checkDimensions(other);

        double sum = 0;
        for (int i = 0; i < dimension; i++) {
            sum += this.data[i] * other.data[i];
        }
        return sum;
    }

    // Display
    public void display() {
        System.out.print("(");
        for (int i = 0; i < dimension; i++) {
            System.out.print(data[i]);
            if (i < dimension - 1) System.out.print(", ");
        }
        System.out.println(")");
    }
}
