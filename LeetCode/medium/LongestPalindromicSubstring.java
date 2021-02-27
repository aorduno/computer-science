package LeetCode.medium;

import CTCI.LogUtils;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        testCase("abaaa");
        testCase("abb");
        testCase("babad");
        testCase("cbbd");
        testCase("a");
        testCase("az");
        testCase("acbaza");
        testCase("");
    }

    private static void testCase(String string) {
        LogUtils.logMessage("[[LongestPalindromicSubstring]] Finding the longest palindromic substring for the given string : " + string);
        String longest = computeLongestExpand(string);
        LogUtils.logMessage("Result: " + longest);
    }

    private static String computeLongestExpand(String string) {
        if (string.length() < 1) {
            return string;
        }

        int start = 0;
        int end = 0;
        for (int x = 0; x < string.length(); x++) {
            int evenLength = expand(string, x, x);
            int oddLength = expand(string, x, x + 1);
            int maxLength = Math.max(evenLength, oddLength);
            if (maxLength > end - start) {
                start = x - ((maxLength - 1) / 2);
                end = x + (maxLength / 2);
            }
        }

        return string.substring(start, end + 1);
    }

    private static int expand(String string, int left, int right) {
        while (left >= 0 && right < string.length() && string.charAt(left) == string.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    private static String computeLongestSpace(String string) {
        if (string.length() == 0) {
            return string;
        }

        boolean[][] substringRanges = new boolean[string.length()][string.length()];
        int maxStartAt = 0;
        int maxEndAt = 0;
        for (int currentLength = 0; currentLength < string.length(); currentLength++) {
            int startAt = 0;
            while (startAt + currentLength < string.length()) {
                int endAt = startAt + currentLength;
                if (startAt == endAt) {
                    substringRanges[startAt][endAt] = true;
                } else if (currentLength == 1) {
                    substringRanges[startAt][endAt] = string.charAt(startAt) == string.charAt(endAt);
                } else if (substringRanges[startAt + 1][endAt - 1] && string.charAt(startAt) == string.charAt(endAt)) {
                    substringRanges[startAt][endAt] = true;
                } else {
                    substringRanges[startAt][endAt] = false;
                }

                if (substringRanges[startAt][endAt]) {
                    maxStartAt = startAt;
                    maxEndAt = endAt;
                }
                startAt++;
            }
        }
        return string.substring(maxStartAt, maxEndAt + 1);
    }

    // time o(n^3)
    // space o(1)
    private static String computeLongest(String string) {
        if (string.length() == 0) {
            return string;
        }

        for (int currentLength = string.length() - 1; currentLength > 0; currentLength--) {
            int startAt = 0;
            boolean isOdd = (currentLength + 1) % 2 == 0; // add 1 to ammend the 0-index thing
            while (startAt + currentLength < string.length()) {
                int left = startAt;
                int right = startAt + currentLength;
                while (string.charAt(left) == string.charAt(right) && left < right) {
                    left++;
                    right--;

                    if ((isOdd && left > right) || (!isOdd && left == right)) { // found our longest one...
                        return string.substring(startAt, startAt + currentLength + 1);
                    }
                }
                startAt++;
            }
        }
        return string.charAt(0) + "";
    }

}
