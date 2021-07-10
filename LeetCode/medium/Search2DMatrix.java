package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        testCase(matrix, 3);
        testCase(matrix, 8);
        testCase(matrix, 0);
        testCase(matrix, 40);
        testCase(matrix, 60);

        int[][] matrix2 = {
                {1}
        };
        testCase(matrix2, 2);
        testCase(new int[][]{}, 2);
    }

    private static void testCase(int[][] matrix, int target) {
        LogUtils.logMessage("[[Search2DMatrix]] Searching for: " + target + " in given matrix");
        ArrayUtils.printMatrix(matrix);
        boolean present = searchMatrix(matrix, target);
        LogUtils.logMessage("IsPresent: " + present);
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        int row = findRow(matrix, target, 0, matrix.length - 1);
        if (row == -1) {
            return false;
        }

        return searchRow(matrix[row], target, 0, matrix[row].length - 1);
    }

    private static boolean searchRow(int[] row, int target, int left, int right) {
        if (left > right) {
            return false;
        }

        int mid = left + (right - left) / 2;
        if (row[mid] == target) {
            return true;
        }

        if (row[mid] > target) {
            return searchRow(row, target, left, mid - 1);
        }

        return searchRow(row, target, mid + 1, right);
    }

    private static int findRow(int[][] matrix, int target, int top, int bottom) {
        if (top > bottom) {
            return -1;
        }

        int mid = top + (bottom - top) / 2;
        if (isGoodRow(matrix, mid, target)) {
            return mid;
        }
        if (matrix[mid][0] > target) {
            return findRow(matrix, target, top, mid - 1);
        }

        return findRow(matrix, target, mid + 1, bottom);
    }

    private static boolean isGoodRow(int[][] matrix, int row, int target) {
        return matrix[row][0] <= target && matrix[row][matrix[row].length - 1] >= target;
    }
}
