package BackTracking;

// A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0]
// and destination block is lower rightmost block i.e., maze[N-1][N-1]. A rat starts from source and has to reach the
// destination. The rat can move only in two directions: forward and down. In the maze matrix,
// 0 means the block is a dead end and 1 means the block can be used in the path from source to destination.
// Note that this is a simple version of the typical Maze problem. For example, a more complex version can
// be that the rat can move in 4 directions and a more complex version can be with a limited number of moves.

// i.e:
//        {1, 0, 0, 0}
//        {1, 1, 0, 1}
//        {0, 1, 0, 0}
//        {1, 1, 1, 1}
public class RatInMaze {
    private int counter = 0;
    private int _dimension;

    // so we need to...
    // 1) see if we got to the end maze[n-1][n-1];
    // 2) if not
    //  2.1 -- mark current cell as visited (1)
    //  2.2 -- see if moving forward leads to the solution
    //  2.3 -- if not see if moving down leads to the solution
    //  2.4 -- if none, return false (no way out!)
    private void solve(int[][] maze, int dimension, int[] startingPoints) {
        setDimension(dimension);
        int solution[][] = new int[dimension][dimension];


        for (int x = 0; x < _dimension; x++) {
            for (int y = 0; y < _dimension; y++) {
                solution[x][y] = 0;
            }
        }

        // first move
//        solution[0][0] = 1;
        if (hasWayOut(solution, startingPoints[0], startingPoints[1], maze)) {
            printSolution(solution);
        } else {
            System.out.println("THERE IS NO WAY OUT!");
        }
    }

    private void setDimension(int dimension) {
        _dimension = dimension;
    }

    private int getCounter() {
        return counter;
    }

    private boolean hasWayOut(int[][] solution, int x, int y, int[][] maze) {
        this.counter++;
        if (x == _dimension - 1 && y == _dimension - 1) {
            solution[x][y] = 1; // mark it
            return true;
        }

        // mark visited!
        if (isSafeMove(x, y, maze)) {
            solution[x][y] = 1;
            if (isSafeMove(x, y + 1, maze)) {
                if (hasWayOut(solution, x, y + 1, maze)) {
                    return true;
                } else {
                    solution[x][y + 1] = 0;
                }
            }

            if (isSafeMove(x + 1, y, maze)) {
                if (hasWayOut(solution, x + 1, y, maze)) {
                    return true;
                } else {
                    solution[x + 1][y] = 0;
                }
            }
        }

        // no way out!
        return false;
    }

    // check boundaries and not visited
    private boolean isSafeMove(int x, int y, int[][] maze) {
        return x < _dimension && y < _dimension && maze[x][y] == 1;
    }

    private void printSolution(int sol[][]) {
        for (int x = 0; x < _dimension; x++) {
            for (int y = 0; y < _dimension; y++)
                System.out.print(sol[x][y] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maze[][] = {
                {1, 1, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 0, 1}
        };

        RatInMaze ratInMaze = new RatInMaze();
        ratInMaze.solve(maze, 5, new int[]{0, 0});

        System.out.println("run times: " + ratInMaze.getCounter());

    }
}
