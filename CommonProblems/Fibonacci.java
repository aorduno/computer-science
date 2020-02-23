package CommonProblems;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private static Map<Integer, Integer> stored = new HashMap<>();
    private static int counter = 1;

    // time o(n)
    // space o(n)
    private static int fibonacci(int n) {
        if (stored.containsKey(n)) {
            return stored.get(n);
        }

        counter++;
        if (n <= 1) {
            stored.put(n, n);
            return n;
        }


        int fib = fibonacci(n - 1) + fibonacci(n - 2);
        stored.put(n, fib);
        return fib;
    }

    // time o(n)
    // space o(n)
    static int fiboIterative(int n) {
        int[] fib = new int[n + 1];
        if (n == 0 || n == 1) {
            return n;
        }

        fib[0] = 0;
        fib[1] = 1;
        for (int x = 2; x <= n; x++) {
            fib[x] = fib[x - 1] + fib[x - 2];
        }

        return fib[n];
    }

    // time o(n)
    // space o(1)
    static int fiboIterativeNoSpace(int n) {
        if (n <= 1) {
            return n;
        }

        int previousTwo = 0;
        int previousOne = 1;
        int result = 0;
        for (int x = 2; x <= n; x++) {
            result = previousOne + previousTwo;
            // update previous
            previousTwo = previousOne;
            previousOne = result;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("fibonacci sequence");
        for (int x = 0; x < 8; x++) {
            System.out.println("value: " + x + " || " + fibonacci(x));
        }

        System.out.println("Executed: " + counter);

        System.out.println("fibonacci sequence iterative");
        for (int x = 0; x < 8; x++) {
            System.out.println("value: " + x + " || " + fiboIterative(x));
        }

        System.out.println("fibonacci sequence iterative no space");
        for (int x = 0; x < 8; x++) {
            System.out.println("value: " + x + " || " + fiboIterativeNoSpace(x));
        }
    }
}
