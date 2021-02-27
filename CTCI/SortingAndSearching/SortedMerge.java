package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class SortedMerge {
    public static void main(String[] args) {
        testCase(new int[]{1, 4, 7, 10, 0, 0, 0}, new int[]{3, 8, 9}, false);
        testCase(new int[]{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, new int[]{1, 1, 2, 3, 4}, false);
        testCase(new int[]{5, 10, 100, 1000, 0, 0, 0, 0}, new int[]{1, 2, 3, 4}, false);

        testCase(new int[]{1, 4, 7, 10, 0, 0, 0}, new int[]{3, 8, 9}, true);
        testCase(new int[]{1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}, new int[]{1, 1, 2, 3, 4}, true);
        testCase(new int[]{5, 10, 100, 1000, 0, 0, 0, 0}, new int[]{1, 2, 3, 4}, true);
    }

    private static void testCase(int[] a, int[] b, boolean bookApproach) {
        LogUtils.logMessage("[[SortedMerge" + (bookApproach ? "Book" : "") + "]] Merging two given sorted arrays", true);
        ArrayUtils.print(a);
        ArrayUtils.print(b);
        if (bookApproach) {
            mergeSorted(a, b);
            LogUtils.logMessage("Merged and sorted!", true);
            ArrayUtils.print(a);
        } else {
            int[] sortedMerged = mergedSorted(a, b);
            LogUtils.logMessage("Merged and sorted!", true);
            ArrayUtils.print(sortedMerged);
        }
    }

    // a is supposed to have a buffer in the end  to fit b
    // merges from last to first -- this is brilliant!
    // time complexity o(n)
    // space complexity o(1)
    private static void mergeSorted(int[] a, int[] b) {
        int diff = a.length - b.length;
        int indexA = diff - 1;
        int indexB = b.length - 1;
        int currentIndex = a.length - 1;
        while (indexB >= 0) {
            if (indexA >= 0 && a[indexA] > b[indexB]) {
                a[currentIndex] = a[indexA];
                indexA--;
            } else {
                a[currentIndex] = b[indexB];
                indexB--;
            }
            currentIndex--;
        }
    }

    // a is supposed to have a buffer in the end  to fit b
    // merges using extra space from first to last
    // time o(n)
    // space o(n)
    private static int[] mergedSorted(int[] a, int[] b) {
        int diff = a.length - b.length;
        int pa = 0;
        int pb = 0;
        int[] positions = new int[a.length];
        int idx = 0;
        while (pa < diff && pb < b.length) {
            if (a[pa] <= b[pb]) {
                positions[idx] = pa;
                pa++;
            } else {
                positions[idx] = diff + pb;
                pb++;
            }
            idx++;
        }

        while (pa < diff) { // remaining a
            positions[idx] = pa;
            pa++;
            idx++;
        }

        while (pb < b.length) { // remaining b
            positions[idx] = diff + pb;
            pb++;
            idx++;
        }


        int[] output = new int[positions.length];
        for (int x = 0; x < positions.length; x++) {
            int position = positions[x];
            if (position < diff) {
                output[x] = a[position];
            } else {
                output[x] = b[position - diff];
            }
        }

        return output;
    }
}
