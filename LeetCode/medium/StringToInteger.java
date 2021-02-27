package LeetCode.medium;

import CTCI.LogUtils;

public class StringToInteger {
    public static void main(String[] args) {
        testCase("9");
        testCase("91");
        testCase("910");
        testCase("6666");
        testCase("-91283472332");
        testCase("91283472332");
        testCase("-2147483648");
        testCase("2147483648");
        testCase("2147483647");
    }

    private static void testCase(String string) {
        LogUtils.logMessage("[[StringToInteger]] Converting given string " + string + " to integer", true);
        int converted = convert(string);
        LogUtils.logMessage("Result: " + converted, true);
    }

    private static int convert(String string) {
        if (string.isEmpty()) { // input validation
            return 0;
        }
        int index = 0;
        while (index < string.length() && string.charAt(index) == ' ') { // input validation + whitespaces
            index++;
        }

        boolean isNegative = false;
        if (index < string.length() && (string.charAt(index) == '+' || string.charAt(index) == '-')) { // input validation + sign
            isNegative = string.charAt(index) == '-';
            index++;
        }

        int result = 0;
        if (index >= string.length() || string.charAt(index) - '0' < 0 || string.charAt(index) - '0' > 9) { // input validation
            return result;
        }

        while (index < string.length() && string.charAt(index) - '0' >= 0 && string.charAt(index) - '0' <= 9) { // we're good
            int current = string.charAt(index) - '0';
            if (willFlow(current, result, isNegative)) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            // update
            index++;
            result = result * 10 + current;
        }

        return isNegative ? -result : result;
    }

    private static boolean willFlow(int current, int result, boolean isNegative) {
        int maxValueQuotient = Integer.MAX_VALUE / 10;
        int maxValueMod = Integer.MAX_VALUE % 10;
        if (isNegative) {
            maxValueMod++;
        }
        return (result + current) > (maxValueQuotient + maxValueMod);
    }
}
