package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class MinimumInRotatedArray {
    public static void main(String[] args) {
        testCase(new int[]{4, 5, 6, 7, 0, 1, 2});
        testCase(new int[]{3, 4, 5, 1, 2});
        testCase(new int[]{11, 13, 15, 17});
    }

    private static void testCase(int[] numbers) {
        LogUtils.logMessage("[[MinimumInRotatedArray]] Finding minimum in given rotated array");
        ArrayUtils.print(numbers);

        int minimum = findMinimumBinarySearch(numbers);
        LogUtils.logMessage("Minimum is " + minimum);
    }

    private static int findMinimumBinarySearch(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }

        int right = numbers.length - 1;
        // not rotated
        if (numbers[0] < numbers[right]) {
            return numbers[0];
        }

        int index = doFind(numbers, 0, right);
        return index != -1 ? numbers[index] : index;
    }

    private static int doFind(int[] numbers, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = left + (right - left) / 2;
        if (numbers[middle] > numbers[middle + 1]) {
            return middle + 1;
        }

        if (numbers[middle] < numbers[middle - 1]) {
            return middle;
        }

        if (numbers[left] < numbers[middle]) {
            return doFind(numbers, middle + 1, right);
        }

        return doFind(numbers, left, middle - 1);
    }

    private static int findMinimumBrute(int[] numbers) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int x = 0; x < numbers.length; x++) {
            if (numbers[x] < min) {
                minIndex = x;
                min = numbers[x];
            }
        }
        return minIndex;
    }
}
