package CTCI.BitManipulation;

public class PowerOfTwo {
    public static void main(String[] args) {
        testCase(1);
        testCase(2);
        testCase(4);
        testCase(64);
        testCase(1024);
        testCase(1021);
        testCase(3);
        testCase(15);
    }

    private static void testCase(int num) {
        System.out.println("[[PowerOfTwo]]Checking if num " + num + " is power of 2");
        System.out.println("Result: " + doCheck(num));
    }

    private static boolean doCheck(int num) {
        return (num & (num - 1)) == 0;
    }
}
