package LeetCode.easy;

import CTCI.LogUtils;

public class PalindromeNumber {
    public static void main(String[] args) {
        testCase(0);
        testCase(-120);
        testCase(252);
        testCase(1234321);
        testCase(-1234321);
        testCase(6667666);
        testCase(6667662);
        testCase(1410110141);
    }

    public int countSubstrings(String s) {
        int[][] palindromeData = new int[s.length()][s.length()];
        int count = 0;
        for (int length = 0; length < s.length(); length++) {
            for (int startAt = 0; startAt + length < s.length(); startAt++) {
                int endAt = startAt + length;
                if (length == 0) {
                    palindromeData[startAt][endAt] = 1;
                    count++;
                } else if (length == 1 && s.charAt(startAt) == s.charAt(endAt)) {
                    palindromeData[startAt][endAt] = 1;
                    count++;
                } else if (s.charAt(startAt) == s.charAt(endAt) && palindromeData[startAt + 1][endAt - 1] == 1) {
                    palindromeData[startAt][endAt] = 1;
                    count++;
                }
            }
        }

        return count;
    }

    private static void testCase(int number) {
        LogUtils.logMessage("[[PalindromeNumber]] Determining if number " + number + " is palindrome", true);
        LogUtils.logMessage("isNumberPalindrome? " + isNumberPalindromeNoSpace(number), true);
    }

    // time complexity o(log n) with base 10
    // space o (1)
    private static boolean isNumberPalindromeNoSpace(int number) {
        if (number < 0) {
            return false;
        }

        int exponent = 1;
        while (exponent * 10 < number && exponent < Integer.MAX_VALUE / 10) {
            exponent *= 10;
        }

        int divisor = 10;
        while (number > 0) {
            int quotient = number / exponent;
            int remainder = number % divisor;
            if (quotient != remainder) {
                return false;
            }

            // update
            number -= quotient * exponent;
            number /= 10;
            exponent /= 100;
        }
        return true;
    }

    // time o(n)
    // space o(n)
    private static boolean isNumberPalindrome(int number) {
        if (number < 0) {
            return false;
        }

        String numberString = convert(number);
        int left = 0;
        int right = numberString.length() - 1;
        while (left < right) {
            if (numberString.charAt(left) != numberString.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    private static String convert(int number) {
        StringBuilder numberBuilder = new StringBuilder();
        while (number > 0) {
            int units = number % 10;
            numberBuilder.append(units);
            number /= 10;
        }

        return numberBuilder.toString();
    }
}
