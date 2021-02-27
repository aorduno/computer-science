package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class MergeSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        testCase(numbers);
    }

    private static void testCase(int[] numbers) {
        LogUtils.logMessage("[[MergedSort]]Received unsorted array!", true);
        ArrayUtils.print(numbers);
        doSort(numbers);
        LogUtils.logMessage("After sorting!", true);
        ArrayUtils.print(numbers);
    }

    private static void doSort(int[] numbers) {
        int[] helper = new int[numbers.length];
        mergeSort(numbers, helper, 0, numbers.length - 1);
    }

    private static void mergeSort(int[] numbers, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;
        mergeSort(numbers, helper, left, middle);
        mergeSort(numbers, helper, middle + 1, right);
        merge(numbers, helper, left, middle, right);
    }

    private static void merge(int[] numbers, int[] helper, int left, int middle, int right) {
        // copy into helper
        for (int x = left; x <= right; x++) {
            helper[x] = numbers[x];
        }

        int currentLeft = left;
        int currentRight = middle + 1;
        int current = left;
        // compare and swap
        while (currentLeft <= middle && currentRight <= right) {
            if (helper[currentLeft] <= helper[currentRight]) {
                numbers[current] = helper[currentLeft];
                currentLeft++;
            } else {
                numbers[current] = helper[currentRight];
                currentRight++;
            }

            current++;
        }

        // copy pending...
        int pending = middle - currentLeft;
        for (int x = 0; x <= pending; x++) {
            numbers[current + x] = helper[currentLeft + x];
        }
    }
}
