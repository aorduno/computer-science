package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        testCase("2-1-1");
        testCase("2*3-4*5");
    }

    private static void testCase(String expression) {
        LogUtils.logMessage("[[DifferentWaysToAddParentheses]] Computing results for all possible ways to group operators and numbers for given expression:");
        LogUtils.logMessage(expression);

        List<Integer> results = computeDifferentWaysDP(expression);
        ArrayUtils.print(results);
    }

    private static List<Integer> computeDifferentWaysDP(String expression) {
        List<Integer> result = new ArrayList<>();
        if (expression.length() == 0) {
            return result;
        }


        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (isOperator(currentChar)) {
                String before = expression.substring(0, i);
                String after = expression.substring(i + 1);

                List<Integer> resultBefore = computeDifferentWaysDP(before);
                List<Integer> resultAfter = computeDifferentWaysDP(after);
                for (int num1 : resultBefore) {
                    for (int num2 : resultAfter) {
                        int localResult = num1;
                        switch (currentChar) {
                            case '+':
                                localResult += num2;
                                break;
                            case '-':
                                localResult -= num2;
                                break;
                            case '*':
                                localResult *= num2;
                                break;
                        }

                        result.add(localResult);
                    }
                }
            }
        }

        // no operator
        if (result.size() == 0) {
            result.add(convert(expression));
        }
        return result;
    }

    private static int convert(String number) {
        int result = 0;
        for (int x = 0; x < number.length(); x++) {
            result = (number.charAt(x) - '0') + (result * 10);
        }

        return result;
    }

    private static boolean isOperator(char c) {
        return c == '*' || c == '+' || c == '-';
    }
}
