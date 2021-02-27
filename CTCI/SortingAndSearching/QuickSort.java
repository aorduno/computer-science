package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class QuickSort {
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        testCase(numbers);
    }

    private static void testCase(int[] numbers) {
        LogUtils.logMessage("[[QuickSort]]Received unsorted array!", true);
        ArrayUtils.print(numbers);
        doSort(numbers);
        LogUtils.logMessage("After sorting!", true);
        ArrayUtils.print(numbers);
    }

    private static void doSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private static void quickSort(int[] numbers, int left, int right) {
        int partition = partitionAndSwap(numbers, left, right);
        if (partition > -1) {
            if (left < partition - 1) {
                quickSort(numbers, left, partition - 1);
            }

            if (right > partition) {
                quickSort(numbers, partition, right);
            }
        }
    }

    private static int partitionAndSwap(int[] numbers, int left, int right) {
        int pivot = numbers[left + (right - left) / 2];
        while (left <= right) {
            while (numbers[left] < pivot) {
                left++;
            }

            while (numbers[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(numbers, left, right);
                left++;
                right--;
            }
        }


        return left;
    }

    private static void swap(int[] numbers, int left, int right) {
        int temp = numbers[left];
        numbers[left] = numbers[right];
        numbers[right] = temp;
    }
}
