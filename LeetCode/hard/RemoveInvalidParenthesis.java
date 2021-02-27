package LeetCode.hard;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveInvalidParenthesis {
    public static void main(String[] args) {
        testCase("()(abc))()");
        testCase("(a)(b))(c)");
        testCase(")(");
    }

    private static void testCase(String s) {
        LogUtils.logMessage("[[RemoveInvalidParenthesis]] removing minimum number of parenthesis for given string " +
                "and returning all possible results");

        List<String> validResults = computeValidResults(s);
        LogUtils.logMessage("Found " + validResults.size() + " valid results");
        print(validResults);
    }

    private static List<String> computeValidResults(String s) {
        int maxNumberOfParens = findMaxNumberOfParents(s);
        List<String> validResults = new ArrayList<>();
        Map<String, Boolean> visited = new HashMap<>();
        computeValidResults(s, maxNumberOfParens, 0, 0, 0, "", validResults, visited);
        return validResults;
    }

    private static int findMaxNumberOfParents(String s) {
        int open = 0;
        int close = 0;
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == '(') {
                open++;
            } else if (s.charAt(x) == ')') {
                close++;
            }

            if (close > open) {
                close--;
            }
        }

        return Math.min(open, close);
    }

    private static void computeValidResults(String string, int numberOfParens, int open, int close, int currentIndex, String currentString, List<String> validResults, Map<String, Boolean> visited) {
        if (visited.containsKey(currentIndex + "_" + currentString) || close > open || open > numberOfParens) { // no good
            return;
        }

        visited.put(currentIndex + "_" + currentString, true);
        if (currentIndex == string.length()) { // branch might be good
            if (open == numberOfParens && open == close) {
                validResults.add(currentString);
            }
            return;
        }

        char currentChar = string.charAt(currentIndex);
        if (currentChar == '(') {
            computeValidResults(string, numberOfParens, open + 1, close, currentIndex + 1, currentString + currentChar, validResults, visited);
            computeValidResults(string, numberOfParens, open, close, currentIndex + 1, currentString, validResults, visited);
            return;
        } else if (currentChar == ')') {
            computeValidResults(string, numberOfParens, open, close + 1, currentIndex + 1, currentString + currentChar, validResults, visited);
            computeValidResults(string, numberOfParens, open, close, currentIndex + 1, currentString, validResults, visited);
            return;
        }

        computeValidResults(string, numberOfParens, open, close, currentIndex + 1, currentString + currentChar, validResults, visited); // don't care...
    }

    private static void print(List<String> validResults) {
        for (String validResult : validResults) {
            LogUtils.logMessage(validResult);
        }

        LogUtils.logMessage("");
    }
}
