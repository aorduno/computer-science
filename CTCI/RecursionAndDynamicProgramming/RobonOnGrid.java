package CTCI.RecursionAndDynamicProgramming;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class RobonOnGrid {
    public static void main(String[] args) {
        int[][] grid = new int[5][6];
        addInvalidMoves(grid, new int[][]{
                {0, 4},
                {1, 2},
                {2, 5},
                {3, 4},
        });
        testCase(grid);

        int[][] grid2 = new int[10][10];
        addInvalidMoves(grid2, new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 7},
                {7, 8},
                {8, 9},
        });

        testCase(grid2);

        int[][] grid3 = new int[10][10];
        addInvalidMoves(grid3, new int[][]{
                {0, 5},
                {1, 5},
                {2, 5},
                {3, 5},
                {4, 5},
                {5, 5},
                {6, 5},
                {7, 5},
                {8, 5},
                {9, 5},
        });

        testCase(grid3);
    }

    private static void addInvalidMoves(int[][] grid, int[][] invalid) {
        for (int[] ints : invalid) {
            grid[ints[0]][ints[1]] = 1;
        }
    }

    private static void testCase(int[][] grid) {
        LogUtils.logMessage("[[RobotOnGrid]] finding path to goal (" + (grid[0].length - 1) + ", " + (grid.length - 1) + ")");
        ArrayUtils.printMatrix(grid);
        int targetRow = grid.length;
        int targetColumn = grid[0].length;
        int[][] path = new int[targetRow][targetColumn];
        boolean found = foundPath(grid, path);
        if (found) {
            LogUtils.logMessage("Found Path for Robot!!!");
            ArrayUtils.printMatrix(path);
        } else {
            LogUtils.logMessage("No Path Found for Robot! No way to get to goal!!!");
        }
    }

    private static boolean foundPath(int[][] grid, int[][] path) {
        int targetRow = grid.length;
        int targetColumn = grid[0].length;
        return findPathRecursively(grid, 0, 0, targetRow - 1, targetColumn - 1, path);
    }

    private static boolean findPathRecursively(int[][] grid, int row, int column, int targetRow, int targetColumn, int[][] path) {
        if (row == targetRow && column == targetColumn) {
            path[row][column] = 1;
            return true;
        }

        if (row > targetRow || column > targetColumn) {
            return false;
        }

        if (grid[row][column] == 1) {
            path[row][column] = 0;
            return false;
        }

        if (findPathRecursively(grid, row, column + 1, targetRow, targetColumn, path) || findPathRecursively(grid, row + 1, column, targetRow, targetColumn, path)) {
            path[row][column] = 1;
            return true;
        }

        return false;
    }
}
