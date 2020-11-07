package CTCI.RecursionAndDynamicProgramming;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

// calculates all permutation of a string with no dupe chars
public class PermutationNoDups {
    public static void main(String[] args) {
        testCase("abc");
        testCase("abcd");
        testCase("abcde");
        testCase("abcdef");
        testCase("abcdefg");
        testCase("abcdefghijklmnopqrstuvwxyz");
    }

    private static void testCase(String string) {
        LogUtils.logMessage("[[PermutationNoDups]] Calculating all permutations for given string: " + string);
        List<String> perms = computePermutations(string);
        LogUtils.logMessage("Found " + perms.size() + " permutations:");
//        print(perms);
    }

    private static void print(List<String> permutations) {
        for (String permutation : permutations) {
            LogUtils.logMessage(permutation);
        }
        LogUtils.logMessage("");
    }

    private static List<String> computePermutations(String string) {
        ArrayList<String> permutations = new ArrayList<>();
        computeRecursively("", string, permutations);
        return permutations;
    }

    private static void computeRecursively(String current, String remaining, List<String> permutations) {
        if (remaining.isEmpty()) {
            permutations.add(current);
            return;
        }

        for (int x = 0; x < remaining.length(); x++) {
            char currentChar = remaining.charAt(x);
            // current would be += currentChar
            // remaining would -= the current char
            computeRecursively(current + currentChar, remaining.substring(0, x) + remaining.substring(x + 1), permutations);
        }
    }
}
