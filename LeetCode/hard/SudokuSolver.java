package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class SudokuSolver {
    public static void main(String[] args) {
        LogUtils.logMessage((1 << 1) + "");
        LogUtils.logMessage((1 << 2) + "");
        LogUtils.logMessage((6 & (1 << 0)) + "");
        testCase(new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {
                '.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {
                '4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {
                '.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {
                '.', '.', '.', '.', '8', '.', '.', '7', '9'}});
    }

    private static void testCase(char[][] board) {
        LogUtils.logMessage("[[SudokuSolver]] solving given Sudoku board");
        ArrayUtils.printMatrix(board);

        solve(board);
        LogUtils.logMessage("Solved:");
        ArrayUtils.printMatrix(board);
    }

    private static void solve(char[][] board) {
        solveRecursively(0, board);
    }

    private static boolean solveRecursively(int row, char[][] board) {
        int[] toFix = findNextToFix(row, board);
        int rowToFix = toFix[0];
        int columnToFix = toFix[1];
        if (rowToFix == -1 && columnToFix == -1) {
            return true;
        }

        for (int digit = 1; digit <= 9; digit++) {
            if (isValid(digit, rowToFix, columnToFix, board)) {
                board[rowToFix][columnToFix] = Character.forDigit(digit, 10);
                if (solveRecursively(rowToFix, board)) {
                    return true;
                }
            }
        }

        board[rowToFix][columnToFix] = '.';
        return false;
    }

    private static boolean isValid(int value, int row, int column, char[][] board) {
        return isValidRow(value, row, board) && isValidColumn(value, column, board) && isValidBox(value, row, column, board);
    }

    private static boolean isValidBox(int value, int row, int column, char[][] board) {
        int startRow = (row / 3) * 3;
        int startCol = (column / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                int current = board[r][c] - '0';
                if (current == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidColumn(int value, int column, char[][] board) {
        for (int r = 0; r < board.length; r++) {
            int current = board[r][column] - '0';
            if (current == value) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidRow(int value, int row, char[][] board) {
        for (int c = 0; c < board[row].length; c++) {
            int current = board[row][c] - '0';
            if (current == value) {
                return false;
            }
        }
        return true;
    }

    private static int[] findNextToFix(int fromR, char[][] board) {
        for (int r = fromR; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == '.') {
                    return new int[]{r, c};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
