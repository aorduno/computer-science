package LeetCode.easy;

import CTCI.LogUtils;

public class ReverseInteger {
    public static void main(String[] args) {
        testCase(321);
        testCase(123);
        testCase(-123);
        testCase(-321);
        testCase(0);
        testCase(120);
        testCase(Integer.MAX_VALUE);
        testCase(Integer.MIN_VALUE);
        testCase(1000000002);
        testCase(-1000000002);
    }

    private static void testCase(int toReverse) {
        LogUtils.logMessage("[[ReverseInteger]] reversing given integer " + toReverse, true);
        int reversed = doReverseBetter(toReverse);
        LogUtils.logMessage("Reversed: " + reversed, true);
    }

    private static int doReverseBetter(int x) {
        int reversed = 0;
        boolean isNegative = x < 0;
        while (x != 0) {
            // pop last digit
            int pop = x % 10;
            if (isGonnaFlow(reversed, pop, isNegative)) {
                return 0;
            }
            // push
            reversed = (reversed * 10) + pop;
            // update
            x /= 10;
        }

        return reversed;
    }

    private static boolean isGonnaFlow(int reversed, int pop, boolean isNegative) {
        int limit = isNegative ? Integer.MIN_VALUE / 10 : Integer.MAX_VALUE / 10;
        int maxToAdd = isNegative ? 8 : 7;
        boolean isOverLimit = isNegative ? reversed < limit : reversed > limit;
        return (isOverLimit || (reversed == limit && pop > maxToAdd));
    }

    private static int doReverse(int x) {
        int exp = 1;
        int xAbs = Math.abs(x);
        while (exp <= (xAbs / 10)) {
            exp *= 10;
        }

        int reversed = 0;
        int reverseExp = 1;
        boolean isNegative = x < 0;
        while (xAbs > 0) {
            int quotient = xAbs / exp;
            xAbs = xAbs % exp;
            if (isGonnaFlow(reverseExp, quotient, reversed)) {
                return 0;
            }
            reversed += quotient * reverseExp;
            exp /= 10;
            reverseExp *= 10;
        }

        return isNegative ? -reversed : reversed;
    }

    private static boolean isGonnaFlow(int reverseExp, int quotient, int reversed) {
        boolean invalidQuotient = quotient > 2;
        boolean atLimit = reversed > 147483647;
        return reverseExp == 1000000000 && (invalidQuotient || atLimit);
    }
}
