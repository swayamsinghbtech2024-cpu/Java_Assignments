import java.util.Scanner;

public class Calculator {

    public float num1, num2;
    public int choice;

    public static void main(String[] args){
        Calculator calc = new Calculator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Calculator! Choose the operation (0- Exit, 1-Add, 2-Subtract, 3-Multiply, 4-Divide, 5-Modulus): ");
            calc.choice = sc.nextInt();

            if (calc.choice == 0) {
                System.out.println("Exiting Calculator. Goodbye!");
                sc.close();
                return;
            }

            System.out.println("Enter first number: ");
            calc.num1 = sc.nextFloat();
            System.out.println("Enter second number: ");
            calc.num2 = sc.nextFloat();

            switch(calc.choice){
            //case 0:
                //System.out.println("Exiting Calculator. Goodbye!");
                //System.exit(0);
                case 1:
                    System.out.println("Addition, Sum is: " + (calc.num1 + calc.num2));
                    break;
                case 2:
                    System.out.println("Subtraction, Difference is: " + (calc.num1 - calc.num2));
                    break;
                case 3:
                    System.out.println("Multiplication, Product is: " + (calc.num1 * calc.num2));
                    break;
                case 4:
                    System.out.println("Division, Quotient is: " + (calc.num1 / calc.num2));
                    break;
                case 5:
                    System.out.println("Modulus, Remainder is: " + (calc.num1 % calc.num2));
                    break;
                default:
                    System.out.println("Invalid choice!");
                    System.exit(0);
            }
        }
    }
}