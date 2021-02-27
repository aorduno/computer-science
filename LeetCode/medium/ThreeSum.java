package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        testCase(new int[]{-1, 0, 1, 2, -1, -4});
        testCase(new int[]{0, 0, 0});
        testCase(new int[]{0});
        testCase(new int[]{});
        testCase(new int[]{1, -1, -1, 0});
    }

    private static void testCase(int[] ints) {
        LogUtils.logMessage("[[3Sum]] Finding all unique triplets in the array which gives the sum to zero for given array", true);
        ArrayUtils.print(ints);

        List<List<Integer>> threeSum = computeTripletsOptimal(ints);
        print(threeSum);
    }

    private static void print(List<List<Integer>> threeSum) {
        LogUtils.logMessage("Computed triplets found: " + threeSum.size(), true);
        for (List<Integer> integers : threeSum) {
            ArrayUtils.print(integers);
        }
    }

    // time complexity o(n^3)
    // space complexity o(1)
    private static List<List<Integer>> computeTripletsBruteForce(int[] ints) {
        Set<List<Integer>> triplets = new HashSet<>();
        for (int left = 0; left < ints.length; left++) {
            for (int center = left + 1; center < ints.length; center++) {
                for (int right = center + 1; right < ints.length; right++) {
                    if (ints[left] + ints[center] + ints[right] == 0) {
                        List<Integer> toAdd = Arrays.asList(ints[left], ints[center], ints[right]);
                        Collections.sort(toAdd);
                        triplets.add(toAdd);
                    }
                }
            }
        }


        return new ArrayList<>(triplets);
    }

    // better but not optimal...
    // still to skip dupes
    // time complexity o(n^2)
    private static List<List<Integer>> computeTripletsBetter(int[] ints) {
        Arrays.sort(ints);
        int lastPosition = ints.length;
        int current = 0;
        Set<List<Integer>> triplets = new HashSet<>();
        while (current < lastPosition) { // right takes this
            if (ints[current] > 0) { // stop when this meets the positive numbers
                return new ArrayList<>(triplets);
            }

            int right = lastPosition - 1;
            int left = current + 1;
            while (left < right) {
                int sum = ints[left] + ints[current] + ints[right];
                if (sum == 0) {
                    triplets.add(Arrays.asList(ints[left], ints[current], ints[right]));
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }

            current++;
        }

        return new ArrayList<>(triplets);
    }

    // time complexity is close to o(n log n) due sorting
    private static List<List<Integer>> computeTripletsOptimal(int[] ints) {
        Arrays.sort(ints);
        int lastPosition = ints.length;
        int auxiliar = 0;
        List<List<Integer>> triplets = new ArrayList<>();
        while (auxiliar < lastPosition) { // right takes this
            if (ints[auxiliar] > 0) { // stop when this meets the positive numbers
                return triplets;
            }

            if (auxiliar != 0 && ints[auxiliar] == ints[auxiliar - 1]) { // already processed skip!
                auxiliar++;
                continue;
            }

            int right = lastPosition - 1;
            int left = auxiliar + 1;
            while (left < right) {
                int sum = ints[left] + ints[auxiliar] + ints[right];
                if (sum == 0) {
                    triplets.add(Arrays.asList(ints[auxiliar], ints[left], ints[right]));
                    left++;
                    while (left < right && ints[left - 1] == ints[left]) { // make sure next position is not dupe
                        left++;
                    }

                    right--;
                    while (left < right && ints[right + 1] == ints[right]) { // make sure next position is not dupe
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }

            auxiliar++;
        }

        return triplets;
    }
}
