package CTCI.RecursionAndDynamicProgramming;

import CTCI.LogUtils;

public class RecursiveMultiply {
    public static void main(String[] args) {
        testCase(5, 4, false);
        testCase(12, 20, false);
        testCase(8345, 123456, false);
        testCase(87123, 9999, false);

        // book approach
        testCase(5, 4, true);
        testCase(12, 20, true);
        testCase(8345, 123456, true);
        testCase(87123, 9999, true);
    }

    private static void testCase(int num1, int num2, boolean useBookApproach) {
        LogUtils.logMessage("[[RecursiveMultiply" + (useBookApproach ? "Book" : "Custom") + "]] Multiplying " + num1 + " and " + num2 + " without * operator");
        LogUtils.logMessage("Result: " + (useBookApproach ? multiply(num1, num2) : doMultiply(num1, num2)));
    }

    // time complexity o(b) where b = number of bits in binary representation of the smallest number of the two given as input
    // we can also say this is log n, where n is the smaller input number
    // space complexity o(b) due recursion where b = number of bits in binary representation of the smallest number of the two given as input
    // we can also say this is log n, where n is the smaller input number
    // we could save some space doing this iteratively
    private static int doMultiply(int num1, int num2) {
        int bigOne = num1 > num2 ? num1 : num2;
        int smallOne = num1 > num2 ? num2 : num1;
        return multiplyRecursively(bigOne, smallOne, 0, 0);
    }

    private static int multiplyRecursively(int big, int small, int index, int result) {
        if (small == 0) {
            return result;
        }

        if ((small & 1) > 0) {
            result += big << index;
        }

        small = small >> 1;
        return multiplyRecursively(big, small, index + 1, result);
    }

    // book approach
    private static int multiply(int num1, int num2) {
        int bigger = num1 > num2 ? num1 : num2;
        int smaller = num1 > num2 ? num2 : num1;
        return doRecursively(bigger, smaller);
    }

    private static int doRecursively(int bigger, int smaller) {
        if (smaller == 1) {
            return bigger;
        } else if (smaller == 0) {
            return 0;
        }

        int half = smaller >> 1;
        int result = doRecursively(bigger, half);
        if (smaller % 2 == 0) {
            return result + result;
        }

        return result + result + bigger;
    }
}
