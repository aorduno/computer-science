package LeetCode.medium;

import CTCI.LogUtils;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        testCase(10, 1);
        testCase(2000, 66);
        testCase(-9999, -3);
        testCase(-1, 1);
        testCase(1, -1);
        testCase(Integer.MIN_VALUE, 1);
        testCase(Integer.MIN_VALUE, -1);
        testCase(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    private static void testCase(int dividend, int divisor) {
        LogUtils.logMessage("Dividing " + dividend + " by " + divisor, true);
        int quotient = divideIntegersBetter(dividend, divisor);
        LogUtils.logMessage("Result is: " + quotient, true);
    }

    private static int divideIntegersBetter(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) { // edge case overflows
            return Integer.MAX_VALUE;
        }

        int dividendAbs = Math.abs(dividend);
        int divisorAbs = Math.abs(divisor);
        boolean isQuotientPositive = isPositive(dividend, divisor);
        int quotient = 0;
        while (dividendAbs - divisorAbs >= 0) {
            int maxDivisor = 0;
            while ((dividendAbs - (divisorAbs << maxDivisor << 1)) >= 0) {
                maxDivisor++;
            }

            int subtract = divisorAbs << maxDivisor;
            dividendAbs -= subtract;
            quotient += (1 << maxDivisor);
        }
        return isQuotientPositive ? quotient : -quotient;
    }

    private static int divideIntegersNaive(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        int quotient = 0;
        boolean shouldGoUp = isPositive(dividend, divisor);
        boolean isDividendPositive = dividend > 0;
        while (shouldKeepGoing(isDividendPositive, dividend)) {
            if (shouldGoUp) {
                if (quotient == Integer.MAX_VALUE) { // overflow
                    return Integer.MAX_VALUE;
                }
                dividend -= divisor;
                quotient++;
            } else {
                dividend += divisor;
                quotient--;
            }
        }

        return quotient;
    }

    private static boolean shouldKeepGoing(boolean isDividendPositive, int dividend) {
        return isDividendPositive ? dividend > 0 : dividend < 0;
    }

    private static boolean isPositive(int dividend, int divisor) {
        return (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
    }
}
