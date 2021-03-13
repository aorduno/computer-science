package DataStructures.Graph;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.List;

public class DetectCycle {
    public static void main(String[] args) {
        short test = 32767;
        LogUtils.logMessage("check: " + test + " || " + (test + test), true);

        byte test2 = 120;
        LogUtils.logMessage("check: " + test2 + " || " + (test2 + test2), true);
        GraphG graphG = new GraphG(4, false);
        int[][] edges = new int[][]{
                {0, 1},
                {0, 2},
                {1, 2},
                {2, 0},
                {2, 3},
                {3, 3}
        };

        addEdges(graphG, edges);
        testCase(graphG, edges, 4);
    }

    private static void addEdges(GraphG graphG, int[][] edges) {
        for (int[] edge : edges) {
            graphG.connect(edge[0], edge[1]);
        }
    }

    private static void testCase(GraphG graphG, int[][] edges, int nodes) {
        LogUtils.logMessage("[[GraphDetectCycle]] detecting cycle in given graph with " + nodes + " nodes and the following edges:", true);
        ArrayUtils.printMatrix(edges);

        int cyclesDetected = hasCycle(graphG);
        LogUtils.logMessage("has " + cyclesDetected + " nodes with cycles detected", true);
    }

    private static int hasCycle(GraphG graphG) {
        GraphNodeG[] nodes = graphG.getNodes();
        boolean[] visited = new boolean[nodes.length];
        boolean[] currentNodePath = new boolean[nodes.length];
        int cycles = 0;
        for (GraphNodeG graphNodeG : nodes) {
            if (detectCycle(graphNodeG, visited, currentNodePath)) {
                cycles++;
            }
        }

        return cycles;
    }

    private static boolean detectCycle(GraphNodeG graphNodeG, boolean[] visited, boolean[] currentNodePath) {
        int node = (int) graphNodeG.getData();
        if (!visited[node]) {
            // add to recursive path
            currentNodePath[node] = true;
            visited[node] = true;

            List<GraphNodeG> adjacencyList = graphNodeG.getAdjacencyList();
            for (GraphNodeG nodeG : adjacencyList) {
                int adjacent = (int) nodeG.getData();
                if (!visited[adjacent] && detectCycle(nodeG, visited, currentNodePath)) { // visit
                    return true;
                } else if (currentNodePath[adjacent]) {
                    return true;
                }
            }
        }

        // remove from recursive path
        currentNodePath[node] = false;
        return false;
    }
}
