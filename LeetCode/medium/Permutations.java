package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations/
public class Permutations {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3, 4});
        testCase(new int[]{5});
        testCase(new int[]{1, 2, 3});
    }

    private static void testCase(int[] nums) {
        LogUtils.logMessage("[[Permutations]] Generating all permutations for given array of nums: ");
        ArrayUtils.print(nums);

        List<List<Integer>> permutations = computePermutationsSimple(nums);
        LogUtils.logMessage("Found: " + permutations.size());
        for (List<Integer> permutation : permutations) {
            Combinations.print(permutation);
        }
    }

    // taken from solutions -- this is smart!
    private static List<List<Integer>> computePermutationsSimple(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        doPermute(nums, new ArrayList<>(), new boolean[nums.length], permutations);
        return permutations;
    }

    private static void doPermute(int[] nums, List<Integer> permutation, boolean[] stack, List<List<Integer>> permutations) {
        if (permutation.size() == nums.length) {
            List<Integer> copy = new ArrayList<>(permutation);
            permutations.add(copy);
            return;
        }

        for (int x = 0; x < nums.length; x++) {
            if (stack[x]) {
                continue;
            }

            stack[x] = true;
            permutation.add(nums[x]);
            doPermute(nums, permutation, stack, permutations);
            stack[x] = false;
            permutation.remove(permutation.size() - 1);
        }
    }

    // original approach -- this one builds permutations from base case. It is kinda slow compared to backtrack approach (above)
    private static List<List<Integer>> computePermutations(int[] nums) {
        boolean[] stack = new boolean[nums.length];
        List<List<Integer>> permutations = new ArrayList<>();
        for (int x = 0; x < nums.length; x++) {
            permutations.addAll(computeRecursively(x, nums, stack, nums.length));
        }
        return permutations;
    }

    private static List<List<Integer>> computeRecursively(int index, int[] nums, boolean[] stack, int length) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (length == 1) {
            List<Integer> permutation = new ArrayList<>();
            permutation.add(nums[index]);
            permutations.add(permutation);
            stack[index] = false;
            return permutations;
        }

        stack[index] = true;
        for (int x = 0; x < nums.length; x++) {
            if (stack[x]) {
                continue;
            }

            List<List<Integer>> currentPermutations = computeRecursively(x, nums, stack, length - 1);
            for (List<Integer> currentPermutation : currentPermutations) {
                List<Integer> permutation = new ArrayList<>(currentPermutation);
                permutation.add(0, nums[index]);
                permutations.add(permutation);
            }
        }
        stack[index] = false;
        return permutations;
    }
}
