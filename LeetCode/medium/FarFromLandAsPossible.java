package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayDeque;
import java.util.Queue;

public class FarFromLandAsPossible {
    public static void main(String[] args) {
        testCase(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
//        [[1,0,0],[0,0,0],[0,0,0]]
        testCase(new int[][]{{1,0,0},{0,0,0},{0,0,0}});
    }

    private static void testCase(int[][] matrix) {
        LogUtils.logMessage("[[AsFarFromLandAsPossible]] Finding out a water cell such that its distance to the nearest land cell is maximized, and return the distance -- for given matrix");
        ArrayUtils.printMatrix(matrix);

        int maxDistance = computeMaxDistance(matrix);
        LogUtils.logMessage("MaxManhattanDistance: " + maxDistance);
    }

    private static int computeMaxDistance(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                if (matrix[x][y] == 1) { // land
                    visited[x][y] = true;
                    queue.add(new int[]{x, y});
                }
            }
        }

        int count = -1; // init as - 1 in case there's no land
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            count++;
            while (currentSize > 0) {
                int[] coordinates = queue.poll();
                int x = coordinates[0];
                int y = coordinates[1];
                if (x - 1 >= 0 && !visited[x - 1][y]) {
                    add(queue, x - 1, y, visited);
                }

                if (x + 1 < matrix.length && !visited[x + 1][y]) {
                    add(queue, x + 1, y, visited);
                }

                if (y - 1 >= 0 && !visited[x][y - 1]) {
                    add(queue, x, y - 1, visited);
                }

                if (y + 1 < matrix[x].length && !visited[x][y + 1]) {
                    add(queue, x, y + 1, visited);
                }

                currentSize--;
            }


        }

        // still can have only lands here.. and no water...
        return count == 0 ? -1 : count;
    }

    private static void add(Queue<int[]> queue, int x, int y, boolean[][] visited) {
        queue.add(new int[]{x, y});
        visited[x][y] = true;
    }
}
