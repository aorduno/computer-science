package LeetCode.medium;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        testCase("bcabc");
        testCase("cbacdcbc");
        testCase("mitnlruhznjfyzmtmfnstsxwktxlboxutbic");
        testCase("abacb");
    }

    private static void testCase(String s) {
        LogUtils.logMessage("[[RemoveDuplicateLetters]] Removing dupe letters and computing smallest lexicographical order for given string: " + s);

        String result = computeResultGreedy(s);
        LogUtils.logMessage("Result: " + result);
    }

    private static String computeResultGreedy(String s) {
        // only lower english letters
        int[] charCount = new int[26];
        for (int x = 0; x < s.length(); x++) {
            charCount[s.charAt(x) - 'a']++;
        }

        Stack<Character> stack = new Stack<>();
        int bitVector = 0;
        for (int x = 0; x < s.length(); x++) {
            char currentChar = s.charAt(x);
            charCount[s.charAt(x) - 'a']--;
            if ((bitVector & (1 << (currentChar - 'a'))) > 0) {
                continue;
            }

            if (stack.isEmpty()) {
                bitVector |= 1 << currentChar - 'a';
                stack.push(currentChar);
            } else {
                while (!stack.isEmpty() && currentChar < stack.peek() && charCount[stack.peek() - 'a'] > 0) {
                    char pop = stack.pop();
                    bitVector &= (~0 & ~(1 << pop - 'a'));
                }

                bitVector |= 1 << currentChar - 'a';
                stack.push(currentChar);
            }

        }

        return convertToString(stack);
    }

    private static String convertToString(Stack<Character> result) {
        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.insert(0, result.pop());
        }

        return sb.toString();
    }

    private static boolean isNextLower(int a, List<Character> result) {
        int aVal = result.get(a) - 'a';
        int bVal = result.get(a + 1) - 'a';
        return bVal < aVal;
    }

    private static String computeResultNaive(String s) {
        List<String> results = computeResults(s);
        return findLexicographicallySmallest(results);
    }

    private static String findLexicographicallySmallest(List<String> results) {
        if (results.isEmpty()) {
            return "";
        }

        String min = results.get(0);
        for (int x = 1; x < results.size(); x++) {
            min = computeMin(min, results.get(x));
        }
        return min;
    }

    private static String computeMin(String a, String b) {
        int x = 0;
        while (x < a.length()) {
            int charA = a.charAt(x) - 'a';
            int charB = b.charAt(x) - 'a';
            if (charA > charB) {
                return b;
            }
            if (charA < charB) {
                return a;
            }

            x++;
        }

        return a;
    }

    private static int computeUniqueChars(String s) {
        int bitVector = 0;
        int unique = 0;
        for (int x = 0; x < s.length(); x++) {
            char currentChar = s.charAt(x);
            if (isBitOn(currentChar, bitVector)) {
                continue;
            }

            bitVector |= 1 << currentChar - 'a';
            unique++;
        }

        return unique;
    }

    private static List<String> computeResults(String s) {
        List<String> results = new ArrayList<>();
        int uniqueChars = computeUniqueChars(s);
        computeResultsRecursively(s, 0, 0, "", uniqueChars, results);
        return results;
    }

    private static void computeResultsRecursively(String s, int currentIndex, int dupeVector, String currentResult, int uniqueChars, List<String> results) {
        if (currentResult.length() == uniqueChars) {
            results.add(currentResult);
            return;
        }

        if (currentIndex == s.length()) {
            return;
        }

        // with
        if (!isBitOn(s.charAt(currentIndex), dupeVector)) {
            computeResultsRecursively(s, currentIndex + 1, dupeVector | (1 << s.charAt(currentIndex) - 'a'), currentResult + s.charAt(currentIndex), uniqueChars, results);
        }

        // without
        computeResultsRecursively(s, currentIndex + 1, dupeVector, currentResult, uniqueChars, results);
    }

    private static void computeRecursively(String s, int currentIndex, StringBuilder result) {
        if (currentIndex == s.length()) {
            return;
        }

        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        int lowestReference = currentIndex == -1 ? Integer.MIN_VALUE : s.charAt(currentIndex) - 'a';
        for (int x = currentIndex + 1; x < s.length(); x++) {
            int currentValue = s.charAt(x) - 'a';
            if (currentValue > lowestReference && currentValue < minValue) {
                minValue = currentValue;
                minIndex = x;
            }
        }

        if (minIndex == -1) {
            return; // no more...
        }

        result.append(s.charAt(minIndex));
        computeRecursively(s, minIndex, result);
    }

    // lowercase english letters only so we're fine...
    private static boolean isBitOn(char c, int vector) {
        int val = c - 'a';
        return (vector & (1 << val)) > 0;
    }
}
