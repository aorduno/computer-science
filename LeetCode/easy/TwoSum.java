package LeetCode.easy;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        testCase(new int[]{2, 7, 11, 15}, 9);
        testCase(new int[]{3, 2, 4}, 6);
        testCase(new int[]{3, 3}, 6);
        testCase(new int[]{3, 2, 3}, 6);
    }

    private static void testCase(int[] ints, int target) {
        LogUtils.logMessage("[[TwoSum]] Finding out which indexes in given array sum up to " + target, true);
        ArrayUtils.print(ints);

        int[] indexes = findIndexes(ints, target);
        LogUtils.logMessage("Indexes are : " + indexes[0] + " , " + indexes[1], true);
    }

    private static int[] findIndexes(int[] nums, int target) {
        int sum = 0;
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        for (int x = 0; x < nums.length; x++) {
            sum += nums[x];
            if (sum == target) {
                return new int[]{x - 1, x};
            }

            int diff = sum - target;
            Integer index = sumIndexMap.getOrDefault(diff, -1);
            if (index != -1) {
                return new int[]{index - 1, x};
            }

            sumIndexMap.put(nums[x], x);
            sumIndexMap.put(diff, x);
        }

        return new int[]{};
    }
}
