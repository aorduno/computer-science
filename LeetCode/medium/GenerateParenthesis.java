package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args) {
        testCase(1);
        testCase(2);
        testCase(3);
    }

    private static void testCase(int pairs) {
        LogUtils.logMessage("[[GenerateParenthesis]] Generating parenthesis for " + pairs + " number of pairs", true);
        List<String> combinations = generateParenthesis(pairs);
        LogUtils.logMessage(combinations.size() + " combinations found", true);
        ArrayUtils.print(Collections.singleton(combinations));
    }

    private static List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        makeParenthesis(n, n, "", combinations);
        return combinations;
    }

    private static void makeParenthesis(int open, int close, String current, List<String> combinations) {
        if (open == 0 && close == 0) {
            combinations.add(current);
            return;
        }

        if (open > 0) {
            makeParenthesis(open - 1, close, current + "(", combinations);
        }

        if (close > open) {
            makeParenthesis(open, close - 1, current + ")", combinations);
        }
    }
}
