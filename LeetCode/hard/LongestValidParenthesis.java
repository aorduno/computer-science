package LeetCode.hard;

import CTCI.LogUtils;

public class LongestValidParenthesis {
    public static void main(String[] args) {
        testCase("()(())");
        testCase("((()))())");
        testCase("(()");
        testCase(")()())");
        testCase("");
        testCase(")");
        testCase("(");
    }

    private static void testCase(String parenString) {
        LogUtils.logMessage("[[LongestValidParenthesis]] Finding out the longest valid parenthesis in given string " + parenString);
        int longest = computeLongestLinear(parenString);
        LogUtils.logMessage("Result: " + longest);
    }

    private static int computeLongestLinear(String parenString) {
        int longest = computeParensInDirection(parenString, 0, parenString.length(), true);
        longest = Math.max(longest, computeParensInDirection(parenString, parenString.length() - 1, 0, false));
        return longest;
    }

    private static int computeParensInDirection(String parenString, int from, int to, boolean ascending) {
        int longest = 0;
        int left = 0;
        int right = 0;
        while (shouldKeepGoing(from, to, ascending)) {
            if (parenString.charAt(from) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                longest = Math.max(longest, left + right);
            }

            if (shouldReset(ascending, left, right)) {
                left = 0;
                right = 0;
            }
            from = ascending ? from + 1 : from - 1;
        }

        return longest;
    }

    private static boolean shouldReset(boolean ascending, int left, int right) {
        return ascending && right > left || !ascending && left > right;
    }

    private static boolean shouldKeepGoing(int from, int to, boolean ascending) {
        return (ascending && from < to) || (!ascending && from >= to);
    }

    private static int computeLongestDynamically(String parenString) {
        int stringLength = parenString.length();
        int[] longestRecord = new int[stringLength];
        int longest = 0;
        for (int currentIndex = 1; currentIndex < parenString.length(); currentIndex++) {
            if (parenString.charAt(currentIndex) == ')') {
                if (parenString.charAt(currentIndex - 1) == '(') {
                    int previousRecord = currentIndex >= 2 ? longestRecord[currentIndex - 2] : 0;
                    longestRecord[currentIndex] = previousRecord + 2;
                }
                if (parenString.charAt(currentIndex - 1) == ')'
                        && currentIndex - longestRecord[currentIndex - 1] > 0
                        && parenString.charAt(currentIndex - longestRecord[currentIndex - 1] - 1) == '('
                        ) {
                    int previousRecord = (currentIndex - longestRecord[currentIndex - 1] >= 2 ? longestRecord[currentIndex - longestRecord[currentIndex - 1] - 2] : 0);
                    longestRecord[currentIndex] = longestRecord[currentIndex - 1] +
                            previousRecord + 2;
                }
            }

            longest = Math.max(longest, longestRecord[currentIndex]);
        }

        return longest;
    }

    private static String computeLongest(String parenString) {
        int left = 0;
        int longestLeft = 0;
        int longestRight = 0;
        while (left + 1 < parenString.length()) {
            int right = left + 1;
            while (right < parenString.length()) {
                if (isValid(left, right, parenString) && right - left > longestRight - longestLeft) {
                    longestLeft = left;
                    longestRight = right;
                }
                right += 2;
            }
            left++;
        }

        return longestLeft == longestRight ? "" : parenString.substring(longestLeft, longestRight + 1);
    }

    private static boolean isValid(int left, int right, String parenString) {
        int open = 0;
        int close = 0;
        for (int x = left; x <= right; x++) {
            if (parenString.charAt(x) == '(') {
                open++;
            } else {
                close++;
            }

            if (close > open) {
                return false;
            }
        }

        return open == close;
    }
}
