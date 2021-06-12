package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Arrays;

public class KthLastElementInArray {
    public static void main(String[] args) {
        testCase(new int[]{3, 2, 1, 5, 6, 4}, 2);
        testCase(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        testCase(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 1);
    }

    private static void testCase(int[] nums, int k) {
        LogUtils.logMessage("[[KthLastElementInArray]] Finding out the " + k + "th last element in given array.");
        ArrayUtils.print(nums);

        int last = computeQuickSelect(nums, k);
        LogUtils.logMessage("Last Kth: " + last);
    }

    private static int computeQuickSelect(int[] nums, int k) {
        return doQuickSelect(nums, 0, nums.length - 1, k);
    }

    private static int doQuickSelect(int[] nums, int left, int right, int k) {
        int partitionIndex = partition(nums, left, right);

        if (partitionIndex == k - 1) {
            return nums[partitionIndex];
        }

        if (partitionIndex < k - 1) {
            return doQuickSelect(nums, partitionIndex + 1, right, k);
        }

        return doQuickSelect(nums, left, partitionIndex - 1, k);
    }

    private static int partition(int[] nums, int left, int right) {
        int partitionIndex = left - 1;

        int pivot = nums[right];
        for (int x = left; x < right; x++) {
            if (nums[x] > pivot) {
                partitionIndex++;
                swap(nums, x, partitionIndex);
            }
        }

        swap(nums, partitionIndex + 1, right);
        return partitionIndex + 1;
    }

    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    private static int computeLast(int[] nums, int k) {
        Arrays.sort(nums);
        int x = nums.length - 1;
        while (k > 1) {
            x--;
            k--;
        }
        return nums[x];
    }
}
