package CommonProblems;

import java.math.BigInteger;

public class Factorial {

    private static int counter = 1;

    static BigInteger factorial(int n) {
        System.out.println("executed: " + counter);
        counter++;
        BigInteger bigInteger = new BigInteger("1");
        if (n == 1) {
            return bigInteger;
        }
        BigInteger bigIntegerN = new BigInteger(String.valueOf(n));
        return bigIntegerN.multiply(factorial(n - 1));
    }

    public static void main(String[] args) {
        for (int x = 1; x < 10; x++) {
            System.out.println("Calculando factorial de " + x + " || " + factorial(x));
        }
    }
}
