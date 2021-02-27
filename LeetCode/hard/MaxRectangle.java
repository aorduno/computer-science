package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Stack;


public class MaxRectangle {
    public static void main(String[] args) {
        //[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
        testCase(new char[][]{
                {'1', '1'}
        });

        testCase(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'},
        });

        testCase(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '1'},
        });
    }

    private static void testCase(char[][] matrix) {
        LogUtils.logMessage("[[MaxRectangle]] computing maxRectangle's area with all 1's for given matrix");
        ArrayUtils.printMatrix(matrix);

        int maxArea = computeMaxArea(matrix);
        LogUtils.logMessage("Max Area: " + maxArea);
    }

    private static int computeMaxArea(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int[] indexHeight = new int[matrix[0].length];
        for (int x = 0; x < matrix.length; x++) {
            computeHeightsForRow(x, matrix, indexHeight);
            maxArea = Math.max(maxArea, LargestRectangleInHistogram.computeLargestRectangleLinearBetter(indexHeight));
        }

        return maxArea;
    }

    private static void computeHeightsForRow(int row, char[][] matrix, int[] indexHeight) {
        for (int x = 0; x < matrix[row].length; x++) {
            indexHeight[x] = matrix[row][x] == '1' ? indexHeight[x] + 1 : 0;
        }
    }
}
