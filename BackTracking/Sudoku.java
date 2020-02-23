package BackTracking;


public class Sudoku {
    private final int[][] _board;
    private int _counter = 0;

    Sudoku(int[][] board) {
        _board = board;
    }

    private void solve() {
        // start from first grid
        if (hasSolution()) {
            System.out.println("Board has solution and here it is!...");
            printSolution();
            return;
        }

        System.out.println("Board has NO solution");
    }

    private void printSolution() {
        for (int x = 0; x < _board.length; x++) {
            for (int y = 0; y < _board.length; y++)
                System.out.print(_board[x][y] + " ");
            System.out.println();
        }
    }

    private int[] getNextEmptyPosition() {
        int[] position = new int[2];
        for (int x = 0; x < 9; x++) {

            for (int y = 0; y < 9; y++) {
                if (_board[x][y] == 0) {
                    position[0] = x;
                    position[1] = y;
                    break;
                }
            }
        }

        return position;
    }

    // to get bounds we need to...
    // take the grid number we're talking about and...
    // @TODO:aorduno -- refine this, there's definitely a math expression for bounds...
    private int[] getGridBounds(int grid) {
        int[] bounds = new int[4];
        switch (grid) {
            case 1:
                bounds[0] = 0;
                bounds[1] = 2;
                bounds[2] = 0;
                bounds[3] = 2;
                break;
            case 2:
                bounds[0] = 3;
                bounds[1] = 5;
                bounds[2] = 0;
                bounds[3] = 2;
                break;
            case 3:
                bounds[0] = 6;
                bounds[1] = 8;
                bounds[2] = 0;
                bounds[3] = 2;
                break;
            case 4:
                bounds[0] = 0;
                bounds[1] = 2;
                bounds[2] = 3;
                bounds[3] = 5;
                break;
            case 5:
                bounds[0] = 3;
                bounds[1] = 5;
                bounds[2] = 3;
                bounds[3] = 5;
                break;
            case 6:
                bounds[0] = 6;
                bounds[1] = 8;
                bounds[2] = 3;
                bounds[3] = 5;
                break;
            case 7:
                bounds[0] = 0;
                bounds[1] = 2;
                bounds[2] = 6;
                bounds[3] = 8;
                break;
            case 8:
                bounds[0] = 3;
                bounds[1] = 5;
                bounds[2] = 6;
                bounds[3] = 8;
                break;
            case 9:
                bounds[0] = 6;
                bounds[1] = 8;
                bounds[2] = 6;
                bounds[3] = 8;
                break;

        }
        return bounds;
    }

    private boolean hasSolution() {
        this._counter++;
        if (isSolved(_board)) {
            return true;
        }

        // do alternatives here...
        int[] emptyPosition = getNextEmptyPosition();
        int x = emptyPosition[0];
        int y = emptyPosition[1];
        for (int number = 1; number <= 9; number++) {
            if (isSafe(number, x, y)) {
                _board[x][y] = number;
                if (hasSolution()) {
                    return true;
                } else {
                    _board[x][y] = 0;
                }
            }
        }

        return false;
    }

    private boolean isSafe(int number, int x, int y) {
        int grid = getGridFromCoordinates(x, y);
        return isRowSafe(number, x) && isColumnSafe(number, y) && isGridSafe(number, grid);
    }

    private int getGridFromCoordinates(int x, int y) {
        int grid = 0;
        // @TODO:aorduno -- please refine this!
        if (x >= 0 && x <= 2) {
            if (y >= 0 && y <= 2) {
                grid = 1;
            }

            if (y >= 3 && y <= 5) {
                grid = 4;
            }

            if (y >= 6 && y <= 8) {
                grid = 7;
            }
        } else if (x >= 3 && x <= 5) {
            if (y >= 0 && y <= 2) {
                grid = 2;
            }

            if (y >= 3 && y <= 5) {
                grid = 5;
            }

            if (y >= 6 && y <= 8) {
                grid = 8;
            }
        } else {
            if (y >= 0 && y <= 2) {
                grid = 3;
            }

            if (y >= 3 && y <= 5) {
                grid = 6;
            }

            if (y >= 6 && y <= 8) {
                grid = 9;
            }
        }
        return grid;
    }

    // is number already in grid
    private boolean isGridSafe(int number, int grid) {
        int[] gridBounds = getGridBounds(grid);
        for (int x = gridBounds[0]; x <= gridBounds[1]; x++) {
            for (int y = gridBounds[2]; y <= gridBounds[3]; y++) {
                if (_board[x][y] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    // is number already in column
    private boolean isColumnSafe(int number, int y) {
        for (int x = 0; x < _board.length; x++) {
            if (_board[x][y] == number) {
                return false;
            }
        }

        return true;
    }

    // is number already in row
    private boolean isRowSafe(int number, int x) {
        for (int y = 0; y < _board.length; y++) {
            if (_board[x][y] == number) {
                return false;
            }
        }

        return true;
    }

    private boolean isSolved(int[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]
                {
                        {3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}
                };

        Sudoku sudoku = new Sudoku(board);
        sudoku.solve();

        System.out.println("And it only took " + sudoku.getCounter() + " iterations");
    }

    public int getCounter() {
        return _counter;
    }
}
