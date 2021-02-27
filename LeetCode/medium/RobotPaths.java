package LeetCode.medium;

import CTCI.LogUtils;

public class RobotPaths {
    public static void main(String[] args) {
        testCase(3, 7);
        testCase(7, 3);
        testCase(3, 3);
        testCase(2, 3);
        testCase(23, 12);
    }

    private static void testCase(int rows, int columns) {
        LogUtils.logMessage("[[RobotPaths]] Computing all possible robot paths from 0,0 to target (" + rows + ", " + columns + ")");
        int paths = computePathsDynamically(rows, columns);
        LogUtils.logMessage("Paths: " + paths);
    }

    private static int computePathsDynamically(int rows, int columns) {
        int[][] memo = createMemo(rows, columns);
        for (int x = 1; x < memo[0].length; x++) {
            memo[0][x] = 1;
        }

        for (int x = 1; x < memo.length; x++) {
            memo[x][0] = 1;
        }

        for (int row = 1; row < rows; row++) {
            for (int column = 1; column < columns; column++) {
                memo[row][column] = memo[row - 1][column] + memo[row][column - 1];
            }
        }

        return memo[rows - 1][columns - 1];
    }

    private static int computePathsWithMemo(int rows, int columns) {
        int[][] memo = createMemo(rows, columns);
        return computeWithMemoRecursively(0, 0, rows - 1, columns - 1, memo);
    }

    private static int computeWithMemoRecursively(int row, int column, int targetRow, int targetColumn, int[][] memo) {
        if (row > targetRow || column > targetColumn) {
            return 0;
        }

        if (row == targetRow && column == targetColumn) {
            return 1;
        }

        if (memo[row][column] != 0) {
            return memo[row][column];
        }

        int paths = computeRecursively(row + 1, column, targetRow, targetColumn) + computeRecursively(row, column + 1, targetRow, targetColumn);
        memo[row][column] = paths;
        return paths;
    }

    private static int[][] createMemo(int rows, int columns) {
        return new int[rows][columns];
    }

    private static int computePaths(int rows, int columns) {
        return computeRecursively(0, 0, rows - 1, columns - 1);
    }

    private static int computeRecursively(int row, int column, int targetRow, int targetColumn) {
        if (row > targetRow || column > targetColumn) {
            return 0;
        }

        if (row == targetRow && column == targetColumn) {
            return 1;
        }

        return computeRecursively(row + 1, column, targetRow, targetColumn) + computeRecursively(row, column + 1, targetRow, targetColumn);
    }
}
