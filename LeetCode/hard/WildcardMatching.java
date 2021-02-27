package LeetCode.hard;

import CTCI.LogUtils;

import java.util.Arrays;

public class WildcardMatching {
    public static void main(String[] args) {
        testCase("aa", "a");
        testCase("aa", "*");
        testCase("cb", "?a");
        testCase("cb", "?b");
        testCase("adceb", "*a*b");
        testCase("acdcb", "a*c?b");
        testCase("", "******");
//        "babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab"
//        "***bba**a*bbba**aab**b"
        testCase("babaaababaabababbbbbbaabaabbabababbaababbaaabbbaaab", "***bba**a*bbba**aab**b");
    }

    private static void testCase(String string, String pattern) {
        LogUtils.logMessage("[[WildcardMatching]] Checking if given string: " + string + " matches pattern: " + pattern);
        boolean isMatch = isMatchMemo(string, pattern);
        LogUtils.logMessage("isMatchNaive: " + isMatch);
    }

    private static boolean isMatchMemo(String string, String pattern) {
        int[][] memo = createMemo(string, pattern);
        String clean = stripStars(pattern);
        return checkIfMatchMemo(string, clean, 0, 0, memo);
    }

    private static String stripStars(String string) {
        int current = 0;
        StringBuilder clean = new StringBuilder();
        int sLength = string.length();
        while (current < sLength) {
            clean.append(string.charAt(current));
            while (string.charAt(current) == '*' && current + 1 < sLength && string.charAt(current + 1) == '*') {
                current++;
            }
            current++;
        }

        return clean.toString();
    }

    private static int[][] createMemo(String string, String pattern) {
        int[][] memo = new int[string.length() + 1][pattern.length() + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return memo;
    }

    private static boolean checkIfMatchMemo(String string, String pattern, int stringIndex, int patternIndex, int[][] memo) {
        if (memo[stringIndex][patternIndex] != -1) {
            return memo[stringIndex][patternIndex] == 1;
        }

        if (stringIndex == string.length() || patternIndex == pattern.length()) {
            while (patternIndex < pattern.length() && pattern.charAt(patternIndex) == '*') {
                patternIndex++;
            }
            return stringIndex == string.length() && patternIndex == pattern.length();
        }


        // branch
        if (pattern.charAt(patternIndex) == '*') {
            boolean isMatch = checkIfMatch(string, pattern, stringIndex, patternIndex + 1) // empty sequence...
                    || checkIfMatch(string, pattern, stringIndex + 1, patternIndex + 1)
                    || checkIfMatch(string, pattern, stringIndex + 1, patternIndex);
            memo[stringIndex][patternIndex] = isMatch ? 1 : 0;
            return isMatch;
        }
        char patternChar = pattern.charAt(patternIndex);
        boolean isValid = patternChar == '?' || string.charAt(stringIndex) == patternChar;
        boolean isMatch = isValid && checkIfMatch(string, pattern, stringIndex + 1, patternIndex + 1);
        memo[stringIndex][patternIndex] = isMatch ? 1 : 0;
        return isMatch;
    }

    private static boolean isMatchNaive(String string, String pattern) {
        return checkIfMatch(string, pattern, 0, 0);
    }

    private static boolean checkIfMatch(String string, String pattern, int stringIndex, int patternIndex) {
        if (stringIndex >= string.length() || patternIndex == pattern.length()) {
            if (patternIndex < pattern.length() && pattern.charAt(patternIndex) == '*') {
                return checkIfMatch(string, pattern, stringIndex, patternIndex + 1);
            }
            return stringIndex >= string.length() && patternIndex == pattern.length();
        }

        char patternChar = pattern.charAt(patternIndex);
        // branch
        if (patternChar == '*') {
            return checkIfMatch(string, pattern, stringIndex, patternIndex + 1) // empty sequence...
                    || checkIfMatch(string, pattern, stringIndex + 1, patternIndex + 1)
                    || checkIfMatch(string, pattern, stringIndex + 1, patternIndex);
        }
        boolean isValid = patternChar == '?' || string.charAt(stringIndex) == patternChar;
        return isValid && checkIfMatch(string, pattern, stringIndex + 1, patternIndex + 1);
    }
}
