import java.util.Scanner;

public class ArithmeticOperations{
    public float num1,num2;

    public float  addNums(float n1, float n2){
        return n1 + n2;
    }

    public float  subNums(float n1, float n2){
        return n1 - n2;
    }

    public float  mulNums(float n1, float n2){
        return n1 * n2;
    }
    
    public float  divNums(float n1, float n2){
        return n1 / n2;
    }

    public float modNums(float n1, float n2){
        return n1 % n2;
    }

    public static void main(String[] args) {
        ArithmeticOperations op  = new ArithmeticOperations();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first number: ");
        op.num1 = sc.nextFloat();
        System.out.println("Enter second number: ");
        op.num2 = sc.nextFloat();
        float sum = op.addNums(op.num1, op.num2);
        float diff = op.subNums(op.num1, op.num2);
        float prod = op.mulNums(op.num1, op.num2);
        float quot = op.divNums(op.num1, op.num2);
        float mod = op.modNums(op.num1, op.num2);

        System.out.println("Addition: " + sum);
        System.out.println("Subtraction: " + diff);
        System.out.println("Multiplication: " + prod);
        System.out.println("Division: " + quot);
        System.out.println("Modulus: " + mod);
    }
}