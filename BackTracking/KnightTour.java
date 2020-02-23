package BackTracking;

public class KnightTour {
    private static int DIMENSIONS = 5; // n x n
    private static int[] X_MOVES = {2, 2, 1, 1, -1, -1, -2, -2};
    private static int[] Y_MOVES = {1, -1, 2, -2, 2, -2, 1, -1};
    private int _counter = 0;
    private int[][] _chessBoard;
    private int _dimensions;

    KnightTour(int dimensions) {
        // create board
        int[][] chessBoard = new int[dimensions][dimensions];

        // set default -1
        for (int x = 0; x < dimensions; x++) {
            for (int y = 0; y < dimensions; y++) {
                chessBoard[x][y] = -1;
            }
        }

        _chessBoard = chessBoard;
        _dimensions = dimensions;
    }

    private int getCounter() {
        return _counter;
    }

    private void solve() {
        // first move
        _chessBoard[0][0] = 0;
        if (hasSolution(_chessBoard, 0, 0, 1)) {
            printSolution(_chessBoard);
        } else {
            System.out.println("Solution not found for dimensions: " + _dimensions);
        }
    }

    private void printSolution(int[][] chessBoard) {
        for (int x = 0; x < _dimensions; x++) {
            for (int y = 0; y < _dimensions; y++) {
                System.out.print(chessBoard[x][y] + " ");
            }
            System.out.println();
        }
    }

    private boolean hasSolution(int[][] chessBoard, int x, int y, int moveNumber) {
        this._counter++;
        // solution
        if (moveNumber == (_dimensions * _dimensions)) {
            return true;
        }

        // check alternatives
        // only 8 knight moves avail
        for (int knightMove = 0; knightMove < 8; knightMove++) {
            int nextX = x + X_MOVES[knightMove];
            int nextY = y + Y_MOVES[knightMove];
            if (isSafeMove(nextX, nextY, chessBoard)) {
                chessBoard[nextX][nextY] = moveNumber;
                if (hasSolution(chessBoard, nextX, nextY, (moveNumber + 1))) {
                    return true;
                } else {
                    chessBoard[nextX][nextY] = -1;
                }
            }
        }

        // at this point there's no solutions
        return false;
    }

    // don't want to be out of boundaries + unique moves only
    private static boolean isSafeMove(int nextX, int nextY, int[][] chessBoard) {
        return (nextX >= 0 && nextX < DIMENSIONS) && (nextY >= 0 && nextY < DIMENSIONS) && chessBoard[nextX][nextY] == -1;
    }


    public static void main(String[] args) {
        KnightTour knightTour = new KnightTour(8);
        knightTour.solve();

        System.out.println("Run " + knightTour.getCounter());
//        int dimensions = DIMENSION;
//        System.out.println("Finding BackTracking.KnightTour solution for dimensions: " + dimensions);
//        BackTracking.KnightTour knightTour = new BackTracking.KnightTour(dimensions);
//        System.out.println("blahblahblah");
//
//        knightTour.solveKnightTour();
    }

    // if all squares are visited = return board
    // else
    //    a) Add one of the next moves to solution vector and recursively
    //    check if this move leads to a solution. (A Knight can make maximum
    //    eight moves. We choose one of the 8 moves in this step).
    //    b) If the move chosen in the above step doesn't lead to a solution
    //    then remove this move from the solution vector and try other
    //    alternative moves.
    //    c) If none of the alternatives work then return false (Returning false
    //    will remove the previously added item in recursion and if false is
//    returned by the initial call of recursion then "no solution exists" )
}
