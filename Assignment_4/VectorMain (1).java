import java.util.InputMismatchException;
import java.util.Scanner;

// Main Class
public class VectorMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter vector dimension (2 or 3): ");
            int dim = sc.nextInt();

            Vector v1 = new Vector(dim);
            Vector v2 = new Vector(dim);

            System.out.println("Enter elements of Vector 1:");
            for (int i = 0; i < dim; i++) {
                v1.setValue(i, sc.nextDouble());
            }

            System.out.println("Enter elements of Vector 2:");
            for (int i = 0; i < dim; i++) {
                v2.setValue(i, sc.nextDouble());
            }

            System.out.println("\nVector 1:");
            v1.display();

            System.out.println("Vector 2:");
            v2.display();

            Vector add = v1.add(v2);
            System.out.println("\nAddition Result:");
            add.display();

            Vector sub = v1.subtract(v2);
            System.out.println("Subtraction Result:");
            sub.display();

            double dot = v1.dotProduct(v2);
            System.out.println("Dot Product: " + dot);
        }
        catch (InputMismatchException e) {
            System.out.println("Input Error: Please enter numeric values only.");
        }
        catch (VectorException e) {
            System.out.println("Vector Error: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Unexpected Error: " + e);
        }

        sc.close();
    }
}