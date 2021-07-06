package LeetCode.medium;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/combinations/
public class Combinations {
    public static void main(String[] args) {
        testCase(4, 2);
        testCase(10, 1);
        testCase(5, 3);
        testCase(10, 0);
    }

    private static void testCase(int n, int k) {
        LogUtils.logMessage("[[Combinations]] Computing all possible combinations of " + k + " numbers out of range [1, " + n + "]");

        List<List<Integer>> combinations = computeAllCombinationsBetter(n, k);
        LogUtils.logMessage("Found " + combinations.size());
        for (List<Integer> combination : combinations) {
            print(combination);
        }
        LogUtils.logMessage("done!");
    }

    // DP
    private static List<List<Integer>> computeAllCombinationsBetter(int n, int k) {
        if (k == 0) {
            return new ArrayList<>();
        }
        return computeAllCombinationsDP(1, n, k);
    }

    private static List<List<Integer>> computeAllCombinationsDP(int index, int n, int k) {
        List<List<Integer>> combinations = new ArrayList<>();
        if (k == 1) {
            for (int x = index; x <= n; x++) {
                List<Integer> combination = new ArrayList<>();
                combination.add(x);
                combinations.add(combination);
            }

            return combinations;
        }

        for (int x = index; x <= n - k + 1; x++) {
            List<List<Integer>> childCombinations = computeAllCombinationsDP(x + 1, n, k - 1);
            for (List<Integer> childCombination : childCombinations) {
                List<Integer> combination = new ArrayList<>(childCombination);
                combination.add(0, x);
                combinations.add(combination);
            }
        }

        return combinations;
    }


    // this works but it's kinda slow cause we create a copy of the current list in recursion
    // time complexity something like o(n! * k) where n is the range of numbers and k = total numbers on each combination ?
    // space complexity o(n!) since we're copying arrays...
    private static List<List<Integer>> computeAllCombinations(int n, int k) {
        if (k == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int x = 1; x <= n; x++) {
            computeRecursively(x, n, k, new ArrayList<>(), result);
        }
        return result;
    }

    private static void computeRecursively(int currentNumber, int n, int k, List<Integer> currentList, List<List<Integer>> result) {
        currentList.add(currentNumber);
        if (currentList.size() == k) {
            addToListAndRemove(result, currentList);
            return;
        }

        for (int x = currentNumber + 1; x <= n; x++) {
            if (currentList.size() - 1 == k) {
                currentList.add(x);
                addToListAndRemove(result, currentList);
                continue;
            }

            computeRecursively(x, n, k, currentList, result);
        }

        currentList.remove(currentList.size() - 1);
    }

    private static void addToListAndRemove(List<List<Integer>> result, List<Integer> currentList) {
        add(result, currentList);
        currentList.remove(currentList.size() - 1);
    }

    private static void add(List<List<Integer>> result, List<Integer> currentList) {
        result.add(new ArrayList<>(currentList));
    }

    static void print(List<Integer> combination) {
        LogUtils.logMessage("[", false);
        for (int i : combination) {
            LogUtils.logMessage(i + " ", false);
        }
        LogUtils.logMessage("]", false);
    }
}
