package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class RotatedArray {
    public static void main(String[] args) {
        int[] ints = {7, 8, 1, 2, 3, 4, 5, 6};
        for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8}) {
            testCase(ints, i);
        }

        testCase(ints, 1000);
        testCase(ints, 666);
        testCase(ints, -1);

        ints = new int[]{1, 1, 1, 2, 1, 1, 1, 1, 1};
        testCase(ints, 2);
        testCase(ints, 1);
        testCase(ints, 0);

    }

    private static void testCase(int[] ints, int target) {
        LogUtils.logMessage("[[SearchInRotatedArray]] searching " + target + " in rotated array", true);
        ArrayUtils.print(ints);
        int index = search(ints, target);
        LogUtils.logMessage("item found at index: " + index, true);
    }

    private static int search(int[] ints, int target) {
        return doSearch(ints, target, 0, ints.length - 1);
    }

    private static int doSearch(int[] ints, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middle = left + ((right - left) / 2);
        if (ints[middle] == target) {
            return middle;
        }

        if (ints[left] < ints[middle]) { // left is sorted
            if (ints[left] <= target && ints[middle] > target) {
                return doSearch(ints, target, left, middle - 1);
            }

            return doSearch(ints, target, middle + 1, right); // not in left -- go right
        } else if (ints[middle] < ints[right]) { // right is sorted
            if (ints[right] >= target && ints[middle] < target) {
                return doSearch(ints, target, middle + 1, right);
            }
            return doSearch(ints, target, left, middle - 1);
        } else {
            int found = -1;
            if (ints[left] == ints[middle]) { // left to mid full of dupes
                found = doSearch(ints, target, middle + 1, right);
            }
            if (found == -1 && ints[middle] == ints[right]) { // mid to right full of dupes
                found = doSearch(ints, target, left, middle - 1);
            }

            return found;
        }
    }
}
