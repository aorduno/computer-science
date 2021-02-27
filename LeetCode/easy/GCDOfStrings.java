package LeetCode.easy;

import CTCI.LogUtils;

public class GCDOfStrings {
    public static void main(String[] args) {
        testCase("ABCABC", "ABC");
        testCase("ABABAB", "ABAB");
        testCase("LEET", "CODE");
        testCase("ABCDEF", "ABC");
    }

    private static void testCase(String string1, String string2) {
        LogUtils.logMessage("[[GCDOfString]] computing greatest common divisor for given strings : " + string1 + " || " + string2);
        String gcd = computeGcd(string1, string2);
        LogUtils.logMessage("Result: " + gcd);
    }

    private static String computeGcd(String str1, String str2) {
        String smaller = str1.length() > str2.length() ? str2 : str1;
        String bigger = str1.length() > str2.length() ? str1 : str2;
        for (int x = smaller.length(); x > 0; x--) {
            if (doesDivide(smaller, smaller, x) && doesDivide(smaller, bigger, x)) {
                return smaller.substring(0, x);
            }
        }

        return "";
    }

    private static boolean doesDivide(String smaller, String bigger, int size) {
        if (bigger.length() % size != 0) {
            return false;
        }
        for (int x = 0; x < bigger.length(); x++) {
            if (smaller.charAt(x % size) != bigger.charAt(x)) {
                return false;
            }
        }
        return true;
    }
}
