package CTCI.RecursionAndDynamicProgramming;

import CTCI.LogUtils;

import java.util.*;

public class Parens {
    public static void main(String[] args) {
//        int c = 1;
//        while (c <= 5) {
//            testCase(c, false);
//            testCase(c, true);
//            c++;
//        }
        testCase(3, true);
    }

    private static void testCase(int numberOfParens, boolean doOptimized) {
        LogUtils.logMessage("[[Parens" + (doOptimized ? "Optimized" : "") + "]] Generating different valid combinations of " + numberOfParens + " pair of parentheses.");
        Collection<String> combinations;
        if (doOptimized) {
            combinations = generateParens(numberOfParens);
            LogUtils.logMessage("Found " + combinations.size() + " combinations");
        } else {
            combinations = generate(numberOfParens);
            LogUtils.logMessage("Found " + combinations.size() + " combinations");
        }

        print(combinations);

    }

    private static void print(Collection<String> combinations) {
        for (String combination : combinations) {
            LogUtils.logMessage(combination);
        }
        LogUtils.logMessage("");
    }

    private static Set<String> generate(int numberOfParens) {
        return generateRecursively(numberOfParens);
    }

    // this works but is not very efficient -- since due HashSet overhead to avoid dupes
    private static Set<String> generateRecursively(int toProcess) {
        Set<String> combinations = new HashSet<>();
        if (toProcess == 0) {
            combinations.add(""); // base case
            return combinations;
        }

        Set<String> combinationsPrev = generateRecursively(toProcess - 1);
        for (String comb : combinationsPrev) {
            insertParensInCombination(combinations, comb);
            combinations.add("()" + comb);
        }

        return combinations;
    }

    private static void insertParensInCombination(Set<String> combinations, String comb) {
        for (int x = 0; x < comb.length(); x++) {
            // insert new combinations
            if (comb.charAt(x) == '(') {
                combinations.add(insertAfter(comb, x));
            }
        }
    }

    private static String insertAfter(String comb, int x) {
        String leftSide = comb.substring(0, x + 1);
        String rightSide = comb.substring(x + 1);
        return leftSide + "()" + rightSide;
    }

    // optimized solution
    private static List<String> generateParens(int numberOfParents) {
        char[] str = new char[2 * numberOfParents];
        List<String> combinations = new ArrayList<>();
        generateParensRecursively(numberOfParents, numberOfParents, str, 0, combinations);
        return combinations;
    }

    private static void generateParensRecursively(int leftRemaining, int rightRemaining, char[] combination, int index, List<String> combinations) {
        if (leftRemaining < 0 || rightRemaining < leftRemaining) { // no more lefts nor rights -- invalid
            return;
        }

        if (leftRemaining == 0 && rightRemaining == 0) { // done processing
            combinations.add(String.valueOf(combination));
            return;
        }

        // build combination
        // left
        combination[index] = '(';
        generateParensRecursively(leftRemaining - 1, rightRemaining, combination, index + 1, combinations);

        // right
        combination[index] = ')';
        generateParensRecursively(leftRemaining, rightRemaining - 1, combination, index + 1, combinations);
    }
}
