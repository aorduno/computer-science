package DataStructures.Graph;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import CTCI.TreesAndGraphs.GraphUtils;

public class DetectCycleUnionFind {
    public static void main(String[] args) {
        testCase(new int[][]{
                {0, 1},
                {0, 2},
                {1, 2},
                {2, 0},
                {2, 3},
                {3, 3}
        }, 4);

        testCase(new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
        }, 6);

        testCase(new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {2, 4},
                {4, 0},
        }, 6);
    }

    private static void testCase(int[][] edges, int nodes) {
        LogUtils.logMessage("[[DetectCycleUnionFind]] Detecting cycle on given edges for " + nodes + " number of nodes: ", true);
        ArrayUtils.printMatrix(edges);

        LogUtils.logMessage("hasCycle: " + GraphUtils.hasCycle(edges, nodes), true);
    }
}
