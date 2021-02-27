package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class MedianOfTwoSortedArray {
    public static void main(String[] args) {
        testCase(new int[]{1, 3}, new int[]{2});
        testCase(new int[]{1, 2, 3}, new int[]{10, 15});
        testCase(new int[]{1, 2, 3}, new int[]{1, 2});
        testCase(new int[]{1, 2}, new int[]{3, 4, 6});
    }

    private static void testCase(int[] a, int[] b) {
        LogUtils.logMessage("[[MedianOfTwoSortedArray]] Determining median of two sorted arrays given", true);
        ArrayUtils.print(a);
        ArrayUtils.print(b);

        double median = computeMedianNoSpace(a, b);
        LogUtils.logMessage("Median: " + median, true);
    }

//    private static double computeMedianNoSpace(int[] A, int[] B) {
//        int m = A._length;
//        int n = B._length;
//        if (m > n) { // to ensure m<=n
//            int[] temp = A; A = B; B = temp;
//            int tmp = m; m = n; n = tmp;
//        }
//        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
//        while (iMin <= iMax) {
//            int i = (iMin + iMax) / 2;
//            int j = halfLen - i;
//            if (i < iMax && B[j-1] > A[i]){
//                iMin = i + 1; // i is too small
//            }
//            else if (i > iMin && A[i-1] > B[j]) {
//                iMax = i - 1; // i is too big
//            }
//            else { // i is perfect
//                int maxLeft = 0;
//                if (i == 0) { maxLeft = B[j-1]; }
//                else if (j == 0) { maxLeft = A[i-1]; }
//                else { maxLeft = Math.max(A[i-1], B[j-1]); }
//                if ( (m + n) % 2 == 1 ) { return maxLeft; }
//
//                int minRight = 0;
//                if (i == m) { minRight = B[j]; }
//                else if (j == n) { minRight = A[i]; }
//                else { minRight = Math.min(B[j], A[i]); }
//
//                return (maxLeft + minRight) / 2.0;
//            }
//        }
//        return 0.0;
//    }


    private static double computeMedianNoSpace(int[] a, int[] b) {
        if (a.length > b.length) {
            int[] temp = b;
            b = a;
            a = temp;
        }
        int aLength = a.length;
        int medianArrayLength = (aLength + b.length + 1) / 2;
        int minA = 0;
        int maxA = aLength;
        while (minA <= maxA) {
            int x = minA + (maxA - minA) / 2;
            int y = medianArrayLength - x;
            if (x < maxA && b[y - 1] > a[x]) {
                minA = x + 1;
            } else if (x > minA && a[x - 1] > b[y]) {
                maxA = x - 1;
            } else {
                int maxLeft;
                if (x == 0) {
                    maxLeft = b[y - 1];
                } else if (y == 0) {
                    maxLeft = a[x - 1];
                } else {
                    maxLeft = Math.max(a[x - 1], b[y - 1]);
                }

                if ((aLength + b.length) % 2 == 1) {
                    return maxLeft;
                }


                int minRight;
                if (x == aLength) {
                    minRight = b[y];
                } else if (y == b.length) {
                    minRight = a[x];
                } else {
                    minRight = Math.min(a[x], b[y]);
                }

                return (maxLeft + minRight) / 2;
            }
        }

        return Integer.MIN_VALUE;
    }

    private static double computeMedian(int[] a, int[] b) {
        int[] merged = mergeSortedArrays(a, b);
        return merged.length % 2 == 0 ? calculateForEven(merged) : merged[(merged.length) / 2];
    }

    private static double calculateForEven(int[] merged) {
        int mergedLength = merged.length;
        return ((double) merged[mergedLength / 2] + (double) merged[(mergedLength / 2) - 1]) / 2;
    }

    private static int[] mergeSortedArrays(int[] a, int[] b) {
        int indexA = 0;
        int indexB = 0;
        int[] merged = new int[a.length + b.length];
        int mergedIdx = 0;
        while (indexB < b.length) {
            if (indexA < a.length && a[indexA] < b[indexB]) {
                merged[mergedIdx] = a[indexA];
                indexA++;
            } else {
                merged[mergedIdx] = b[indexB];
                indexB++;
            }

            mergedIdx++;
        }

        while (indexA < a.length) {
            merged[mergedIdx] = a[indexA];
            mergedIdx++;
            indexA++;
        }

        return merged;
    }
}
