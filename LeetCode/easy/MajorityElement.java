package LeetCode.easy;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        testCase(new int[]{2, 3, 2, 3, 2});
        testCase(new int[]{2, 3, 3, 3, 2});
        testCase(new int[]{1, 1, 3, 4});
    }

    private static void testCase(int[] nums) {
        LogUtils.logMessage("[[MajorityElement]] Computing majority element for given input:");
        ArrayUtils.print(nums);

        int majorityElement = computeDnC(nums);
        LogUtils.logMessage("Result: " + majorityElement);
    }

    private static int computeDnC(int[] nums) {
        return computeDnCRecursive(nums, 0, nums.length - 1);
    }

    private static int computeDnCRecursive(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int middle = left + (right - left) / 2;
        int majorityLeft = computeDnCRecursive(nums, left, middle);
        int majorityRight = computeDnCRecursive(nums, middle + 1, right);
        if (majorityLeft == majorityRight) {
            return majorityLeft;
        }

        // if different see which one has more occurrences...
        int countLeft = 0;
        int countRight = 0;
        for (int x = left; x <= right; x++) {
            if (nums[x] == majorityLeft) {
                countLeft++;
            }

            if (nums[x] == majorityRight) {
                countRight++;
            }
        }

        return countLeft > countRight ? majorityLeft : majorityRight;
    }

    static int computeWithSpace(int[] nums) {
        Map<Integer, Integer> numberCountMap = new HashMap<>();
        int maxCount = Integer.MIN_VALUE;
        int majorityElement = -1;
        for (int x = 0; x < nums.length; x++) {
            int current = nums[x];
            int count = numberCountMap.getOrDefault(current, 0);
            if (count > maxCount) {
                majorityElement = current;
                maxCount = count;
            }
            numberCountMap.put(current, count + 1);
        }

        return majorityElement;
    }
}
