package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class BinarySearch {
    public static void main(String[] args) {
        int[] ints = {1, 5, 10, 20, 100, 500, 1000, 9999, 88888};
        testCase(ints, 20, false);
        testCase(ints, 1000, false);
        testCase(ints, 88888, false);
        testCase(ints, 1, false);
        testCase(ints, 666, false);


        testCase(ints, 20, true);
        testCase(ints, 1000, true);
        testCase(ints, 88888, true);
        testCase(ints, 1, true);
        testCase(ints, 666, true);
    }

    private static void testCase(int[] ints, int target, boolean doRecursively) {
        LogUtils.logMessage("[[BinarySearch" + (doRecursively ? "Recursive" : "Iterative") + "]] searching " + target + " in given array of numbers", true);
        ArrayUtils.print(ints);

        int index = doRecursively ? doSearchResurively(ints, target) : doSearch(ints, target);
        if (index == -1) {
            LogUtils.logMessage("item " + target + " not found!", true);
        } else {
            LogUtils.logMessage("item is present at idx: " + index, true);
        }

    }

    private static int doSearchResurively(int[] ints, int target) {
        return doSearch(ints, target, 0, ints.length - 1);
    }

    private static int doSearch(int[] ints, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = left + ((right - left) / 2);
        if (ints[middle] == target) {
            return middle;
        } else if (ints[middle] > target) {
            return doSearch(ints, target, left, middle - 1);
        } else {
            return doSearch(ints, target, middle + 1, right);
        }
    }

    private static int doSearch(int[] ints, int target) {
        int left = 0;
        int right = ints.length - 1;
        int middle;

        while (left <= right) {
            middle = left + ((right - left) / 2);
            if (ints[middle] == target) {
                return middle;
            }

            if (ints[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }
}
