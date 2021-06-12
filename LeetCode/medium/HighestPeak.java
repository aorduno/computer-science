package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class HighestPeak {
    public static void main(String[] args) {
        testCase(new int[][]{
                {0, 1}, {0, 0}
        });

        testCase(new int[][]{
                {0, 1}, {0, 1}
        });
    }

    private static void testCase(int[][] map) {
        LogUtils.logMessage("[[HighestPeak]] Computing highest possible peak for given map of land and water. 0 = land, 1 = water");
        ArrayUtils.printMatrix(map);

        int[][] result = computeHighestPeakBetter(map);
        LogUtils.logMessage("Highest Peak Map");
        ArrayUtils.printMatrix(result);
    }

    private static int[][] computeHighestPeakBetter(int[][] map) {
        int[][] highestPeak = createDefaultResult(map);
        boolean[][] added = new boolean[map.length][map[0].length];
        Queue<Coordinate> toVisit = new ArrayDeque<>();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == 1) {
                    highestPeak[x][y] = 0;
                    added[x][y] = true;
                    toVisit.add(new Coordinate(x, y));
                }
            }
        }

        while (!toVisit.isEmpty()) {
            Coordinate coordinate = toVisit.remove();
            int row = coordinate._row;
            int col = coordinate._col;
            int coordinateValue = highestPeak[row][col];
            if (isVisited(highestPeak, row, col)) {
                continue;
            }

            if (coordinateValue == Integer.MAX_VALUE) {
                highestPeak[row][col] = computeCoordinateValue(row, col, highestPeak);
            }

            toVisit.addAll(computeCoordinateAdjacents(highestPeak, row, col, added));

        }
        return highestPeak;
    }

    private static Queue<Coordinate> computeCoordinateAdjacents(int[][] highestPeak, int row, int col, boolean[][] visited) {
        Queue<Coordinate> toVisit = new ArrayDeque<>();
        if (row - 1 >= 0 && !visited[row - 1][col]) {
            toVisit.add(new Coordinate(row - 1, col));
            visited[row - 1][col] = true;
        }

        if (row + 1 < highestPeak.length && !visited[row + 1][col]) {
            toVisit.add(new Coordinate(row + 1, col));
            visited[row + 1][col] = true;
        }

        if (col - 1 >= 0 && !visited[row][col - 1]) {
            toVisit.add(new Coordinate(row, col - 1));
            visited[row][col - 1] = true;
        }

        if (col + 1 < highestPeak[row].length && !visited[row][col + 1]) {
            toVisit.add(new Coordinate(row, col + 1));
            visited[row][col + 1] = true;
        }

        return toVisit;
    }

    private static int[][] computeHighestPeak(int[][] map) {
        int[][] highestPeak = createDefaultResult(map);
        Queue<Coordinate> toVisit = new ArrayDeque<>();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == 1) {
                    highestPeak[x][y] = 0;
                    toVisit.add(new Coordinate(x, y));
                }
            }
        }

        while (!toVisit.isEmpty()) {
            Coordinate coordinate = toVisit.remove();
            int row = coordinate._row;
            int col = coordinate._col;
            int coordinateValue = highestPeak[row][col];
            if (isVisited(highestPeak, row, col)) {
                continue;
            }

            if (coordinateValue == Integer.MAX_VALUE) {
                highestPeak[row][col] = computeCoordinateValue(row, col, highestPeak);
            }

            toVisit.addAll(computeCoordinateAdjacents(highestPeak, row, col));

        }
        return highestPeak;
    }

    private static boolean isVisited(int[][] highestPeak, int row, int col) {
        return highestPeak[row][col] != 0 && highestPeak[row][col] != Integer.MAX_VALUE;
    }

    private static Queue<Coordinate> computeCoordinateAdjacents(int[][] highestPeak, int row, int col) {
        Queue<Coordinate> toVisit = new ArrayDeque<>();
        if (row - 1 >= 0 && !isVisited(highestPeak, row - 1, col) && !isWater(highestPeak, row - 1, col)) {
            toVisit.add(new Coordinate(row - 1, col));
        }

        if (row + 1 < highestPeak.length && !isVisited(highestPeak, row + 1, col) && !isWater(highestPeak, row + 1, col)) {
            toVisit.add(new Coordinate(row + 1, col));
        }

        if (col - 1 >= 0 && !isVisited(highestPeak, row, col - 1) && !isWater(highestPeak, row, col - 1)) {
            toVisit.add(new Coordinate(row, col - 1));
        }

        if (col + 1 < highestPeak[row].length && !isVisited(highestPeak, row, col + 1) && !isWater(highestPeak, row, col + 1)) {
            toVisit.add(new Coordinate(row, col + 1));
        }

        return toVisit;
    }

    private static boolean isWater(int[][] highestPeak, int row, int col) {
        return highestPeak[row][col] == 0;
    }

    private static int computeCoordinateValue(int row, int col, int[][] map) {
        int min = Integer.MAX_VALUE;
        if (row - 1 >= 0) {
            min = Math.min(min, map[row - 1][col]);
        }

        if (row + 1 < map.length) {
            min = Math.min(min, map[row + 1][col]);
        }

        if (col - 1 >= 0) {
            min = Math.min(min, map[row][col - 1]);
        }

        if (col + 1 < map[row].length) {
            min = Math.min(min, map[row][col + 1]);
        }
        return min + 1;
    }

    private static int[][] createDefaultResult(int[][] map) {
        int[][] result = new int[map.length][map[0].length];
        for (int[] row : result) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return result;
    }
}

class Coordinate {
    int _row;
    int _col;

    Coordinate(int row, int col) {
        _row = row;
        _col = col;
    }
}
