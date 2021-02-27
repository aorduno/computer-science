package CTCI.RecursionAndDynamicProgramming;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class EightQueens {
    public static void main(String[] args) {
        testCase(8);
//        testCaseBook(4);
    }

    private static void testCaseBook(int numberOfQueens) {
        LogUtils.logMessage("[[NQueensBook]] Placing " + numberOfQueens + " queens in a chessBoard of " + numberOfQueens + " x " + numberOfQueens, true);
        char[][] board = createBoard(numberOfQueens);
        LogUtils.logMessage("Board:", true);
        ArrayUtils.printMatrix(board);

        List<int[]> placed = doQueenPlacementsBook(board, numberOfQueens);
        printAllWays(placed, numberOfQueens);
    }

    private static void printAllWays(List<int[]> placed, int numberOfQueens) {
        LogUtils.logMessage("Found " + placed.size() + " different ways of placing 8 queens...", true);
        for (int[] columns : placed) {
            char[][] board = new char[numberOfQueens][numberOfQueens];
            populateQueens(board, columns);
            ArrayUtils.printMatrix(board);
            LogUtils.logMessage("DONE", true);
        }
    }

    private static void populateQueens(char[][] board, int[] columnsPlaced) {
        int row = 0;
        for (int columnPlaced : columnsPlaced) {
            int column = columnsPlaced[columnPlaced];
            board[row][column] = 'Q';
            row++;
        }
    }

    private static List<int[]> doQueenPlacementsBook(char[][] board, int numberOfQueens) {
        List<int[]> results = new ArrayList<>();
        placeQueens(0, new int[numberOfQueens], results, numberOfQueens);
        return results;
    }

    private static void placeQueens(int row, int[] columns, List<int[]> results, int numberOfQueens) {
        if (row == numberOfQueens) {
            results.add(columns.clone());
            return;
        }

        for (int column = 0; column < numberOfQueens; column++) {
            if (checkValid(columns, row, column)) {
                columns[row] = column;
                placeQueens(row + 1, columns, results, numberOfQueens);
            }
        }
    }

    private static boolean checkValid(int[] columns, int row, int column) {
        for (int compareRow = 0; compareRow < row; compareRow++) {
            int compareColumn = columns[compareRow];
            if (column == compareColumn) {
                return false;
            }

            int columnDistance = Math.abs(compareColumn - column);
            int rowDistance = row - compareRow;
            if (columnDistance == rowDistance) {
                return false;
            }
        }

        return true;
    }

    private static void testCase(int n) {
        LogUtils.logMessage("[[NQueens]] Placing " + n + " queens in a chessBoard of " + n + " x " + n, true);
        char[][] board = createBoard(n);
        ArrayUtils.printMatrix(board);

        boolean placed = doQueenPlacements(board, n);
        if (placed) {
            LogUtils.logMessage("Placed, here's the board!", true);
            ArrayUtils.printMatrix(board);
        } else {
            LogUtils.logMessage("Not possible to place!", true);
        }
    }

    private static boolean doQueenPlacements(char[][] board, int numberOfQueens) {
        return doPlacementsRecursively(0, 0, board, numberOfQueens, 0);
    }

    private static boolean doPlacementsRecursively(int row, int column, char[][] board, int queensToPlace, int placed) {
        if (queensToPlace == 0) {
            return true;
        }

        int currentRow = row;
        while (currentRow < board.length) {
            if (canPlace(board, currentRow, column)) {
                board[currentRow][column] = 'Q';
                if (doPlacementsRecursively(0, column + 1, board, queensToPlace - 1, placed + 1)) {
                    return true;
                } else {
                    board[currentRow][column] = '\u0000';
                }
            }

            currentRow++;
        }

        return false;
    }

    private static boolean canPlace(char[][] board, int row, int column) {
        // no need to check vertically since we do one column at the time...
        return checkHorizontally(board, row) && checkDiagonals(board, row, column);
    }

    // @TODO:aorduno - we're doing extra work here... like we could just do some math and find the diagonals but meh!
    private static boolean checkDiagonals(char[][] board, int row, int column) {
        // top-left direction
        int offset = 1;
        while (((row - offset) >= 0) && (column - offset) >= 0) {
            if (board[row - offset][column - offset] == 'Q') {
                return false;
            }
            offset++;
        }
        // top-right direction
        offset = 1;
        while (((row - offset) >= 0) && (column + offset) < board[row].length) {
            if (board[row - offset][column + offset] == 'Q') {
                return false;
            }
            offset++;
        }

        offset = 1;
        // bottom-left direction
        while (((row + offset) < board.length) && (column - offset) >= 0) {
            if (board[row + offset][column - offset] == 'Q') {
                return false;
            }
            offset++;
        }

        offset = 1;
        // bottom-right
        while (((row + offset) < board.length) && (column + offset) < board[row].length) {
            if (board[row + offset][column + offset] == 'Q') {
                return false;
            }
            offset++;
        }

        return true;
    }

    private static boolean checkHorizontally(char[][] board, int row) {
        for (int x = 0; x < board[row].length; x++) {
            if (board[row][x] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static boolean checkVertically(char[][] board, int column) {
        for (int x = 0; x < board.length; x++) {
            if (board[x][column] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static char[][] createBoard(int n) {
        return new char[n][n];
    }
}
