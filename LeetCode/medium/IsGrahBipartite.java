package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class IsGrahBipartite {
    public static void main(String[] args) {
        testCase(new int[][]{
                {1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}
        });

        testCase(new int[][]{
                {1, 3}, {0, 2}, {1, 3}, {0, 2}
        });

        testCase(new int[][]{
                {}, {2, 4, 6}, {1, 4, 8, 9}, {7, 8}, {1, 2, 8, 9}, {6, 9}, {1, 5, 7, 8, 9}, {3, 6, 9}, {2, 3, 4, 6, 9}, {2, 4, 5, 6, 7, 8}
        });
    }

    private static void testCase(int[][] graph) {
        LogUtils.logMessage("[[IsGrahBipartite]] Checking out if given graph is bipartite");
        ArrayUtils.printMatrix(graph);

        boolean isBipartite = checkIfBipartite(graph);
        LogUtils.logMessage("isBipartite: " + isBipartite);
    }

    private static boolean checkIfBipartite(int[][] graph) {
        char[] group = new char[graph.length];
        Arrays.fill(group, '0');

        for (int vertex = 0; vertex < graph.length; vertex++) {
            if (group[vertex] == '0' && !checkVertex(graph, vertex, group)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkVertex(int[][] graph, int vertex, char[] group) {
        Queue<Integer> toVisit = new ArrayDeque<>();
        toVisit.add(vertex);
        group[vertex] = 'a';
        while (!toVisit.isEmpty()) {
            Integer currentVertex = toVisit.remove();
            for (int adjacent : graph[currentVertex]) {
                if (group[adjacent] == '0') {
                    group[adjacent] = group[currentVertex] == 'a' ? 'b' : 'a';
                    toVisit.add(adjacent);
                } else if (group[adjacent] == group[currentVertex]) {
                    return false;
                }
            }
        }

        return true;
    }

}
