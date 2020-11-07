package CTCI;

//        There is an N by M matrix of zeroes. Given N and M, write a function to count the number of ways of starting at the top-left corner and getting to the bottom-right corner. You can only move right or down.
//        For example, given a 2 by 2 matrix, you should return 2, since there are two ways to get to the bottom-right:
//        Right, then down
//        Down, then right
//        Given a 5 by 5 matrix, there are 70 ways to get to the bottom-right.
public class MatrixMovesBottomRight {

    // time complexity o(n^2)
    // space complexity o(1)
    int count(int n, int m) {
        if (n < 2 || m < 2) {
            return 1;
        }

        // this is not needed but meh!
        int[][] matrix = new int[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                matrix[x][y] = 0;
            }
        }

        // figure out first row
        for (int x = 0; x < m; x++) {
            matrix[0][x] = 1;
        }

        // figure out first column
        for (int x = 0; x < n; x++) {
            matrix[x][0] = 1;
        }


        // figure out the rest
        for (int x = 1; x < n; x++) {
            for (int y = 1; y < m; y++) {
                matrix[x][y] = matrix[x - 1][y] + matrix[x][y - 1];
            }
        }

        return matrix[n - 1][m - 1];
    }

    static void createAndCountMoves(int n, int m) {
        MatrixMovesBottomRight matrixMovesBottomRight = new MatrixMovesBottomRight();
        System.out.println("There are " + matrixMovesBottomRight.count(n, m) + " ways to get to the bottom right in a matrix of sizes " + n + " x " + m);
    }

    public static void main(String[] args) {
        createAndCountMoves(5, 5);
        createAndCountMoves(2, 2);
        createAndCountMoves(10, 8);
    }
}
