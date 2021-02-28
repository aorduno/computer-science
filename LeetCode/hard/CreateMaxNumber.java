package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class CreateMaxNumber {
    public static void main(String[] args) {
        testCase(new int[]{7, 6, 1, 9, 3, 2, 3, 1, 1}, new int[]{4, 0, 9, 9, 0, 5, 5, 4, 7}, 9);
        testCase(new int[]{2, 2, 0, 6, 8, 9, 6}, new int[]{5, 2, 4, 5, 3, 6, 2}, 7);
        testCase(new int[]{1, 2}, new int[]{}, 2);
        testCase(new int[]{}, new int[]{1}, 1);
        testCase(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5);
        testCase(new int[]{6, 7}, new int[]{6, 0, 4}, 5);
        testCase(new int[]{3, 9}, new int[]{8, 9}, 3);
        testCase(new int[]{3, 9}, new int[]{8, 9}, 4);
    }

    private static void testCase(int[] m, int[] n, int k) {
        LogUtils.logMessage("[[CreateMaxNumber]] Creating maximum number for give inputs...");
        LogUtils.logMessage("m:");
        ArrayUtils.print(m);
        LogUtils.logMessage("n:");
        ArrayUtils.print(n);
        LogUtils.logMessage("k:" + k);

        int[] max = computeMaxNumberNaive(m, n, k);
        LogUtils.logMessage("Result:");
        ArrayUtils.print(max);
    }

    private static int[] computeMaxNumberNaive(int[] m, int[] n, int k) {
        int maxNumber = computeMaxRecursively(m, 0, n, 0, k, 0);
        return convert(maxNumber);
    }

    private static int[] convert(int maxNumber) {
        int length = getLength(maxNumber) - 1;
        int[] num = new int[length + 1];
        int exponent = 10;
        while (maxNumber > 0) {
            num[length] = maxNumber % exponent;

            maxNumber /= exponent;
            length--;
        }
        return num;
    }

    private static int computeMaxRecursively(int[] m, int indexM, int[] n, int indexN, int k, int currentValue) {
        if (k == 0) {
            return currentValue;
        }

        int maxWithM = currentValue;
        int maxWithoutM = currentValue;
        if (indexM < m.length) {
            maxWithM = computeMaxRecursively(m, indexM + 1, n, indexN, k - 1, add(currentValue, m, indexM));
            maxWithoutM = computeMaxRecursively(m, indexM + 1, n, indexN, k, currentValue);
        }

        int maxWithN = currentValue;
        int maxWithoutN = currentValue;
        if (indexN < n.length) {
            maxWithN = computeMaxRecursively(m, indexM, n, indexN + 1, k - 1, add(currentValue, n, indexN));
            maxWithoutN = computeMaxRecursively(m, indexM, n, indexN + 1, k, currentValue);
        }

        return Math.max(Math.max(maxWithM, maxWithN), Math.max(maxWithoutM, maxWithoutN));
    }

    private static int add(int currentValue, int[] nums, int index) {
        return (currentValue * 10) + nums[index];
    }

    private static int getLength(int number) {
        int length = 0;
        while (number > 0) {
            number /= 10;
            length++;
        }

        return length;
    }

    private static int computeMaxIndex(int[] nums) {
        int maxIndex = -1;
        int maxValue = -1;
        for (int x = 0; x < nums.length; x++) {
            maxValue = Math.max(nums[x], maxValue);
            if (maxIndex == -1 || nums[x] > nums[maxIndex]) {
                maxIndex = x;
            }
        }

        return maxIndex == -1 ? nums.length : maxIndex;
    }
}
