package LeetCode.hard;

import CTCI.LogUtils;

public class RegexMatch {
    public static void main(String[] args) {
//        testCase("abcdefgh", "abcdefgh");
//        testCase("abcdefgh", "abcdefgo");
//        testCase("abcdefgh", "a...efgh");
//        testCase("abcdefgh", ".b.d.f.h");
        testCase("aa", "a");
        testCase("aa", "a*");
        testCase("ab", ".*");
        testCase("aab", "c*a*b");
        testCase("mississippi", "mis*is*p*.");
        testCase("asdfzxcv", ".*.");
        testCase("asdfqwer", ".*c");
        testCase("asdfqwer", ".*r");
        testCase("aaa", "a*a");
    }

    private static void testCase(String string, String pattern) {
        LogUtils.logMessage("[[RegexMatch]] Testing if given string " + string + " matches regex pattern " + pattern);
        boolean isMatch = isMatchRecursiveIndexWithMemo(string, pattern);
        LogUtils.logMessage("isMatch: " + isMatch);
    }

    private static boolean isMatchRecursiveIndexWithMemo(String string, String pattern) {
        Boolean[][] memo = new Boolean[string.length()][pattern.length()];
        return computeMatchRecursiveIndexWithMemo(string, pattern, 0, 0, memo);
    }

    private static boolean computeMatchRecursiveIndexWithMemo(String string, String pattern, int stringIndex, int patternIndex, Boolean[][] memo) {
        if (memo[stringIndex][patternIndex] != null) {
            return memo[stringIndex][patternIndex];
        }

        if (patternIndex == pattern.length()) {
            memo[stringIndex][patternIndex] = stringIndex == string.length();
            return memo[stringIndex][patternIndex];
        }

        boolean currentMatch = stringIndex < string.length() && //
                (string.charAt(stringIndex) == pattern.charAt(patternIndex) || pattern.charAt(patternIndex) == '.');
        boolean isMatch;
        if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') { // star
            isMatch = computeMatchRecursiveIndexWithMemo(string, pattern, stringIndex, patternIndex + 2, memo) ||
                    currentMatch && computeMatchRecursiveIndexWithMemo(string, pattern, stringIndex + 1, patternIndex, memo);
        } else {
            isMatch = currentMatch && //
                    computeMatchRecursiveIndexWithMemo(string, pattern, stringIndex + 1, patternIndex + 1, memo);
        }
        memo[stringIndex][patternIndex] = isMatch;
        return isMatch;
    }

    private static boolean isMatchRecursiveIndex(String string, String pattern) {
        return computeMatchRecursiveIndex(string, pattern, 0, 0);
    }

    private static boolean computeMatchRecursiveIndex(String string, String pattern, int stringIndex, int patternIndex) {
        if (patternIndex == pattern.length()) {
            return stringIndex >= string.length();
        }

        boolean firstMatches = stringIndex < string.length() &&
                (string.charAt(stringIndex) == pattern.charAt(patternIndex) || pattern.charAt(patternIndex) == '.');
        if (patternIndex + 1 < pattern.length() && pattern.charAt(patternIndex + 1) == '*') {
            return computeMatchRecursiveIndex(string, pattern, stringIndex, patternIndex + 2) //
                    || firstMatches && computeMatchRecursiveIndex(string, pattern, stringIndex + 1, patternIndex);
        }

        return firstMatches && computeMatchRecursiveIndex(string, pattern, stringIndex + 1, patternIndex + 1);
    }

    private static boolean isMatchRecursive(String string, String pattern) {
        if (pattern.isEmpty()) {
            return string.isEmpty(); // done
        }
        boolean isFirstMatch = !string.isEmpty() && (string.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return isMatchRecursive(string, pattern.substring(2)) //
                    || (isFirstMatch && isMatchRecursive(string.substring(1), pattern));
        }
        return isFirstMatch && isMatchRecursive(string.substring(1), pattern.substring(1));
    }

    private static boolean isMatch(String string, String pattern) {
        int stringPointer = 0;
        int patternPointer = 0;
        while (stringPointer < string.length() && patternPointer < pattern.length()) {
            char currentPatternChar = pattern.charAt(patternPointer);
            if (isJackpot(currentPatternChar, pattern, patternPointer)) {
                int charAfterJackpot = patternPointer + 2;
                if (charAfterJackpot >= pattern.length() || pattern.charAt(charAfterJackpot) == '.') { // nothing else to process...
                    return true;
                }

                char toFind = pattern.charAt(charAfterJackpot);
                while (stringPointer < string.length() && string.charAt(stringPointer) != toFind) {
                    stringPointer++;
                }

                patternPointer += 2;
                continue;
            }

            // star check
            if (isNextCharStar(pattern, patternPointer)) {
                while (stringPointer < string.length() && string.charAt(stringPointer) == currentPatternChar) {
                    stringPointer++;
                }

                patternPointer += 2;
                continue;
            }

            // strict check
            if (currentPatternChar != '.' && currentPatternChar != string.charAt(stringPointer)) {
                return false;
            }

            // advance
            stringPointer++;
            patternPointer++;
        }

        return stringPointer == string.length() && patternPointer == pattern.length();
    }

    private static boolean isJackpot(char currentPatternChar, String pattern, int patternPointer) {
        return currentPatternChar == '.' && isNextCharStar(pattern, patternPointer);
    }

    private static boolean isNextCharStar(String pattern, int patternPointer) {
        return patternPointer + 1 < pattern.length() && pattern.charAt(patternPointer + 1) == '*';
    }
}
