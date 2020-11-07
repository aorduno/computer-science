package CTCI.RecursionAndDynamicProgramming;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class MagicIndex {
    public static void main(String[] args) {
        // distinct
        testCase(new int[]{0, 2, 5, 10, 15}, true);
        testCase(new int[]{0, 2, 5, 10, 15}, false);
        testCase(new int[]{-35, -20, -10, -3, -1, 0, 1, 3, 8, 100}, true);
        testCase(new int[]{-35, -20, -10, -3, -1, 0, 1, 3, 8, 100}, false);
        testCase(new int[]{-35, -20, -10, -3, 4, 10, 20, 30, 40}, true);
        testCase(new int[]{-35, -20, -10, -3, 4, 10, 20, 30, 40}, false);

        // non-distinct
        testCase(new int[]{-10, -5, 2, 2, 2, 3, 8, 9, 10, 11, 12}, false);
        testCase(new int[]{-10, -5, 2, 2, 3, 3, 8, 9, 10, 11, 12}, false);
        testCase(new int[]{-1, -1, -1, -1, -1, -1, -1, 7, 7, 7, 7, 7}, false);
        testCase(new int[]{-1, -1, -1, -1, -1, -1, -1, -1}, false);
    }

    private static void testCase(int[] numbers, boolean doDistinct) {
        LogUtils.logMessage("[[MagicIndex" + (doDistinct ? "Distinct" : "NonDistinct") + "]] Finding magic index for given array of integers");
        ArrayUtils.print(numbers);

        int index = doDistinct ? findMagicIndex(numbers) : findMagicIndexNonDistinct(numbers);
        if (index == -1) {
            LogUtils.logMessage("No magic index found!");
        } else {
            LogUtils.logMessage("Magic index found at: " + index);
        }
    }

    private static int findMagicIndexNonDistinct(int[] numbers) {
        return findRecursivelyNonDistinct(numbers, 0, numbers.length - 1);
    }

    private static int findRecursivelyNonDistinct(int[] numbers, int left, int right) {
        if (left > right) {
            return -1;
        }

        int index = left + ((right - left) / 2);
        int currentNumber = numbers[index];
        if (currentNumber == index) {
            return index;
        }

        // skip some spots to the left...
        int leftIndex = Math.min(index - 1, currentNumber);
        int indexAtLeft = findRecursivelyNonDistinct(numbers, left, leftIndex);
        if (indexAtLeft != -1) {
            return indexAtLeft;
        }

        // skip some spots to the right
        int rightIndex = Math.max(index + 1, currentNumber);
        return findRecursivelyNonDistinct(numbers, rightIndex, right);
    }

    private static int findMagicIndex(int[] numbers) {
        return findRecursively(numbers, 0, numbers.length - 1);
    }

    private static int findRecursively(int[] numbers, int left, int right) {
        if (left > right) {
            return -1;
        }
        int index = left + ((right - left) / 2);
        if (numbers[index] == index) {
            return index;
        }

        if (numbers[index] < index) {
            return findRecursively(numbers, index + 1, right);
        }
        return findRecursively(numbers, left, index - 1);
    }
}
