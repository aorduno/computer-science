package LeetCode.medium;

import CTCI.LogUtils;

import java.util.Arrays;

public class RobotPaths2 {
    public static void main(String[] args) {
        testCase(new int[][]{
                {0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        });

        testCase(new int[][]{
                {1, 0},
        });

        testCase(new int[][]{
                {0},
                {1},
        });

        testCase(new int[][]{
                {0, 1},
                {0, 0},
        });
        testCase(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0},
        });

        testCase(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0},
        });

        testCase(new int[][]{
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 0},
        });
    }

    private static void testCase(int[][] obstacleGrid) {
        LogUtils.logMessage("[[RobotPaths2]] Computing all possible robot paths from 0,0 to target (" + obstacleGrid.length + ", " + obstacleGrid[0].length + ")");
        int paths = computePathsDynamically(obstacleGrid);
        LogUtils.logMessage("Paths: " + paths);
    }

    private static int computePathsDynamically(int[][] obstacleGrid) {
        int[][] memo = createDpMemo(obstacleGrid);
        int rowLength = obstacleGrid.length;
        int columnLength = obstacleGrid[0].length;
        for (int row = 1; row < rowLength; row++) {
            for (int column = 1; column < columnLength; column++) {
                if (obstacleGrid[row][column] == 1) {
                    memo[row][column] = 0;
                    continue;
                }

                memo[row][column] = memo[row - 1][column] + memo[row][column - 1];
            }
        }

        return memo[rowLength - 1][columnLength - 1];
    }

    private static int[][] createDpMemo(int[][] obstacleGrid) {
        int[][] memo = new int[obstacleGrid.length][obstacleGrid[0].length];
        int blockedPathIndex = -1;
        for (int row = 0; row < obstacleGrid.length; row++) {
            if (obstacleGrid[row][0] == 1) {
                blockedPathIndex = row;
                break;
            }
        }

        for (int row = 0; row < obstacleGrid.length; row++) {
            memo[row][0] = blockedPathIndex != -1 && row >= blockedPathIndex ? 0 : 1;
        }

        blockedPathIndex = -1;
        for (int column = 0; column < obstacleGrid[0].length; column++) {
            if (obstacleGrid[0][column] == 1) {
                blockedPathIndex = column;
                break;
            }
        }

        for (int column = 0; column < obstacleGrid[0].length; column++) {
            memo[0][column] = blockedPathIndex != -1 && column >= blockedPathIndex ? 0 : 1;
        }
        return memo;
    }

    private static int computePathsWithMemo(int[][] obstacleGrid) {
        int[][] memo = createMemo(obstacleGrid);
        return computeRecursivelyWithMemo(0, 0, obstacleGrid.length - 1, obstacleGrid[0].length - 1, obstacleGrid, memo);
    }

    private static int[][] createMemo(int[][] obstacleGrid) {
        int[][] memo = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return memo;
    }

    private static int computeRecursivelyWithMemo(int row, int column, int targetRow, int targetColumn, int[][] obstacleGrid, int[][] memo) {
        if (row > targetRow || column > targetColumn || obstacleGrid[row][column] == 1) {
            return 0;
        }

        if (row == targetRow && column == targetColumn) {
            return 1;
        }

        if (memo[row][column] != -1) {
            return memo[row][column];
        }

        return computeRecursivelyWithMemo(row + 1, column, targetRow, targetColumn, obstacleGrid, memo) + computeRecursivelyWithMemo(row, column + 1, targetRow, targetColumn, obstacleGrid, memo);
    }

    private static int computePaths(int[][] obstacleGrid) {
        return computeRecursively(0, 0, obstacleGrid.length - 1, obstacleGrid[0].length - 1, obstacleGrid);
    }

    private static int computeRecursively(int row, int column, int targetRow, int targetColumn, int[][] obstacleGrid) {
        if (row > targetRow || column > targetColumn || obstacleGrid[row][column] == 1) {
            return 0;
        }

        if (row == targetRow && column == targetColumn) {
            return 1;
        }

        return computeRecursively(row + 1, column, targetRow, targetColumn, obstacleGrid) + computeRecursively(row, column + 1, targetRow, targetColumn, obstacleGrid);
    }
}
