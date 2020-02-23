package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    private final int _dimension;
    private int _counter;

    private NQueens(int dimension) {
        _dimension = dimension;
    }

    // so what this needs to do is...
    //   1) Start in the leftmost column
    //   2) If all queens are placed
    //    return true <<< need to pass the placed queens for this...
    //   3) Try all rows in the current column.
    //   Do following for every tried row. <<< iterate...
    //    a) If the queen can be placed safely in this row
    //       then mark this [row, column] as part of the
    //       solution and recursively check if placing
    //       queen here leads to a solution. <<< check isSafe, if it is do for next column...
    //    b) If placing the queen in [row, column] leads to
    //       a solution then return true.
    //    c) If placing queen doesn't lead to a solution then
    //       unmark this [row, column] (Backtrack) and go to
    //       step (a) to try other rows.
    //3) If all rows have been tried and nothing worked,
    //   return false to trigger backtracking.
    void solve() {
        int[][] board = new int[_dimension][_dimension];

        for (int x = 0; x < _dimension; x++) {
            for (int y = 0; y < _dimension; y++) {
                board[x][y] = 0;
            }
        }

        List<Integer[]> placedQueens = new ArrayList<>();
        // start at {0,0}
        if (hasSolution(board, placedQueens, 0)) {
            BackTrackingUtil.printSolution(board, _dimension, "1", "Q");
        } else {
            System.out.println("Board with dimensions: " + _dimension + " has no solution!");
        }
    }

    private boolean hasSolution(int[][] board, List<Integer[]> placedQueens, int column) {
        _counter++;
        if (placedQueens.size() == _dimension) {
            return true;
        }

        // alternative columns
        for (int row = 0; row < _dimension; row++) {
            if (isSafeToPut(board, row, column, placedQueens)) {
                board[row][column] = 1;
                placedQueens.add(getCoordinateArray(row, column));
                if (hasSolution(board, placedQueens, column + 1)) {
                    return true;
                } else {
                    board[row][column] = 0;
                    removeFromPlacedQueens(row, column, placedQueens);
                }
            }
        }

        return false;
    }

    private void removeFromPlacedQueens(int row, int column, List<Integer[]> placedQueens) {
        for (int x = 0; x < placedQueens.size(); x++) {
            Integer[] integers = placedQueens.get(x);
            if (integers[0] == row && integers[1] == column) {
                placedQueens.remove(x);
            }
        }
    }

    private Integer[] getCoordinateArray(int row, int column) {
        Integer[] integers = new Integer[2];
        integers[0] = row;
        integers[1] = column;

        return integers;
    }

    private boolean isSafeToPut(int[][] board, int row, int column, List<Integer[]> placedQueens) {
        return column >= 0 && column < _dimension && !isAttackedByOtherQueen(row, column, board, placedQueens);
    }

    private boolean isAttackedByOtherQueen(int row, int column, int[][] board, List<Integer[]> placedQueens) {
        // just to check faster when empty
        if (placedQueens.isEmpty()) {
            return false;
        }

        return hasQueenInSameRow(row, board) || hasQueenInSameColumn(column, board) || hasQueenInDiagonals(row, column, board);
    }

    private boolean hasQueenInDiagonals(int row, int column, int[][] board) {
        int[] xDiagonalMoves = {-1, -1, 1, 1};
        int[] yDiagonalMoves = {-1, 1, -1, 1};
        // try each diagonal move
        for (int x = 0; x < 4; x++) {
            int xMove = xDiagonalMoves[x];
            int yMove = yDiagonalMoves[x];

            int posX = row + xMove;
            int posY = column + yMove;
            while (isSafeDiagonalMove(posX) && isSafeDiagonalMove(posY)) {
                if (board[posX][posY] == 1) {
                    return true; //found it!
                }

                posX = posX + xMove;
                posY = posY + yMove;
            }
        }

        return false;
    }

    private boolean isSafeDiagonalMove(int move) {
        return move >= 0 && move < _dimension;
    }

    private boolean hasQueenInSameColumn(int column, int[][] board) {
        for (int x = 0; x < _dimension; x++) {
            if (board[x][column] == 1) {
                return true; // found it!
            }
        }

        return false;
    }

    private boolean hasQueenInSameRow(int row, int[][] board) {
        for (int x = 0; x < _dimension; x++) {
            if (board[row][x] == 1) {
                return true; // found it!
            }
        }

        return false;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens(8);
        nQueens.solve();
        System.out.println("times run: " + nQueens.get_counter());
    }

    public int get_dimension() {
        return _dimension;
    }

    public int get_counter() {
        return _counter;
    }
}
