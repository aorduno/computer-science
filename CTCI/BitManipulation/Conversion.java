package CTCI.BitManipulation;

public class Conversion {
    public static void main(String[] args) {
        testCase(29, 15);
        testCase(15, 16);
        testCase(7, 3);
    }

    private static void testCase(int num1, int num2) {
        System.out.println("[[Conversion]] Determining how many bits would be flipped to convert " + num1 + " to " + num2);
        System.out.println("Result: " + getBitsToConvert(num1, num2));
    }

    private static int getBitsToConvert(int num1, int num2) {
        int xor = num1 ^ num2;
        int bits = 0;
        while (xor != 0) {
            bits += xor & 1;
            xor >>= 1;
        }

        return bits;
    }
}
