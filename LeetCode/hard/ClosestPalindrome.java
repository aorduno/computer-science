package LeetCode.hard;

import CTCI.LogUtils;

public class ClosestPalindrome {
    public static void main(String[] args) {
        testCase("11911");
        testCase("99");
        testCase("88");
        testCase("2");
        testCase("1");
        testCase("10");
        testCase("11");
        testCase("123");
        testCase("116");
        testCase("33");
        testCase("886");
        testCase("1234");
        testCase("57183");
        testCase("547890");
    }

    private static void testCase(String s) {
        LogUtils.logMessage("[[ClosestPalindrome]] Finding closest palindrome for given input: " + s);
        String closest = findClosestBetter(s);
        LogUtils.logMessage("Closest is: " + closest);
    }

    private static String findClosestBetter(String s) {
        if (s.equals("1")) {
            return "0";
        }

        // first half mirrored in second half
        String first = createPalindrome(s);
        Long diff1 = Math.abs(parse(first) - parse(s));
        if (diff1 == 0) { // palindrome was given...
            diff1 = Long.MAX_VALUE;
        }

        // low down mid number by 1...
        String second = createLowerPalindrome(s);
        Long diff2 = Math.abs(parse(second) - parse(s));

        // add up 1 to mid number... 9 becomes 0...
        String third = createHigherPalindrome(s);
        Long diff3 = Math.abs(parse(third) - parse(s));

        if (diff2 <= diff1 && diff2 <= diff3) {
            return second;
        }

        if (diff1 < diff2 && diff1 <= diff3) {
            return first;
        }

        return third;
    }

    private static String createHigherPalindrome(String s) {
        StringBuilder palindrome = new StringBuilder(s);
        int current = (s.length() - 1) / 2;
        while (current >= 0 && s.charAt(current) == '9') {
            palindrome.replace(current, current + 1, "0");
            current--;
        }

        if (current < 0) {
            palindrome.insert(0, '1');
        } else {
            palindrome.replace(current, current + 1, (char) (s.charAt(current) - '0' + 49) + "");
        }

        return createPalindrome(palindrome.toString());
    }

    private static String createLowerPalindrome(String s) {
        StringBuilder palindrome = new StringBuilder(s);
        int current = (s.length() - 1) / 2;
        while (current >= 0 && s.charAt(current) == '0') {
            palindrome.replace(current, current + 1, "9");
            current--;
        }

        if (current == 0 && s.charAt(current) == '1') {
            palindrome.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            palindrome.replace(mid, mid + 1, "9");
        } else {
            palindrome.replace(current, current + 1, (char) (s.charAt(current) - '0' + 47) + "");
        }

        return createPalindrome(palindrome.toString());
    }

    private static String createPalindrome(String s) {
        int middle = (s.length() / 2) - 1;
        StringBuilder palindrome = new StringBuilder();
        int left = 0;
        while (left <= middle) {
            palindrome.append(s.charAt(left));
            left++;
        }

        if (s.length() % 2 == 1) { // odds
            palindrome.append(s.charAt(s.length() / 2));
        }

        // reverse
        left--;
        while (left >= 0) {
            palindrome.append(s.charAt(left));
            left--;
        }

        return palindrome.toString();
    }

    private static boolean isEdgeCase(String s) {
        int number = s.charAt(0) * 10 + s.charAt(1);
        return number <= 11;
    }

    private static String convertToString(char[] closestPalindrome) {
        StringBuilder answer = new StringBuilder();
        for (char c : closestPalindrome) {
            answer.append(c);
        }

        return answer.toString();
    }

    private static String findClosest(String s) {
        Long number = parse(s);
        int down = 1;
        int up = 1;
        while (number - down >= 0 || number + up <= Long.MAX_VALUE) {
            boolean isPreviousPalindrome = number - down >= 0 && isPalindrome(number - down);
            boolean isNextPalindrome = number + up <= Long.MAX_VALUE && isPalindrome(number + up);
            if (isPreviousPalindrome || isNextPalindrome) {
                return isPreviousPalindrome ? convert(number - down) : convert(number + up);
            }

            up++;
            down++;
        }

        return "";
    }

    private static boolean isPalindrome(long l) {
        return isPalindrome(convert(l));
    }

    private static String convert(long l) {
        if (l == 0) {
            return "0";
        }

        StringBuilder number = new StringBuilder();
        while (l > 0) {
            number.append(l % 10);
            l /= 10;
        }
        return number.toString();
    }

    private static boolean isPalindrome(String s) {
        int length = s.length();
        int left = (length - 1) / 2;
        int right = length % 2 == 0 ? left + 1 : left;
        while (left >= 0 && right < length) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left--;
            right++;
        }

        return true;
    }

    private static Long parse(String s) {
        Long parsed = 0L;
        for (int x = 0; x < s.length(); x++) {
            char currentChar = s.charAt(x);
            parsed = (parsed * 10) + (currentChar - '0');
        }

        return parsed;
    }
}
