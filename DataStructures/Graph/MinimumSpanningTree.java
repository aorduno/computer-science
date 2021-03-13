package DataStructures.Graph;

import CTCI.LogUtils;
import CTCI.TreesAndGraphs.GraphUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumSpanningTree {
    public static void main(String[] args) {
        List<Edge> edges = createEdges(new int[][]{
                {0, 1, 4},
                {1, 2, 8},
                {2, 3, 7},
                {2, 5, 4},
                {3, 4, 9},
                {3, 5, 14},
                {4, 5, 10},
                {5, 6, 2},
                {6, 7, 1},
                {6, 8, 6},
                {7, 0, 8},
                {7, 8, 7},
                {8, 2, 2},
        });
        testCase(edges, 9);
    }

    private static void testCase(List<Edge> edges, int nodes) {
        LogUtils.logMessage("[[MinimumSpanningTreeKruskal]] Computing minimum spanning tree on given edges for " + nodes + " number of nodes: ", true);
        print(edges);

        List<Edge> mst = computeMinimumSpanningTree(edges, nodes);
        LogUtils.logMessage("MinimumSpanningTree:", true);
        print(mst);

        LogUtils.logMessage("With a total weight of: " + computeMSTWeight(mst), true);
    }

    private static int computeMSTWeight(List<Edge> mst) {
        int weight = 0;
        for (Edge edge : mst) {
            weight += edge.getWeight();
        }

        return weight;
    }

    // kruskal algorithm...
    // 1) sort edges so we can go greedy
    // 2) traverse the edges and on each check if adding that edge forms a cycle
    // 2.1) add it if it doesn't.
    // 2.2) discard if it does form a cycle
    // 3) loop until you have edges count == nodes - 1
    private static List<Edge> computeMinimumSpanningTree(List<Edge> edges, int nodes) {
        edges.sort(Edge::compareTo);

        List<Edge> minimumSpanningTree = new ArrayList<>();
        int[] parent = new int[nodes];
        Arrays.fill(parent, -1);
        while ((minimumSpanningTree.size() < nodes - 1) && !edges.isEmpty()) {
            Edge current = edges.remove(0);
            int parentFrom = GraphUtils.findParent(current.getFrom(), parent);
            int parentTo = GraphUtils.findParent(current.getTo(), parent);
            if (parentFrom != parentTo) { // add if no cycle
                minimumSpanningTree.add(current);
                GraphUtils.doUnion(parent, parentFrom, parentTo);
            }
        }

        return minimumSpanningTree;
    }

    private static void print(List<Edge> edges) {
        LogUtils.logMessage("Edges: ", true);
        for (Edge edge : edges) {
            LogUtils.logMessage(edge.getFrom() + " -> " + edge.getTo() + " || weight: " + edge.getWeight(), true);
        }
        LogUtils.logMessage("", true);
    }

    private static List<Edge> createEdges(int[][] edges) {
        List<Edge> edgeList = new ArrayList<>();
        for (int[] edge : edges) {
            edgeList.add(new Edge(edge[0], edge[1], edge[2]));
        }

        return edgeList;
    }
}
