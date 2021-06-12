package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumHeightTrees {
    public static void main(String[] args) {
        testCase(4, new int[][]{
                {1, 0}, {1, 2}, {1, 3}
        });

        testCase(6, new int[][]{
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}
        });

    }

    private static void testCase(int nodes, int[][] edges) {
        LogUtils.logMessage("[[MinimumHeightTrees]] Computing the minimumHeightTrees having " + nodes + " nodes and edges:");
        ArrayUtils.printMatrix(edges);

        List<Integer> result = computeMinimumHeightTreesBetter(nodes, edges);
        print(result);
    }

    private static List<Integer> computeMinimumHeightTreesBetter(int nodes, int[][] edges) {
        int[] nodeChild = new int[nodes];
        List<Integer>[] graph = createGraphAndNodeChild(nodes, edges, nodeChild);

        int removed = 0;
        List<Integer> result = new ArrayList<>();
        while (removed < nodes) {
            List<Integer> toRemove = findLeaves(nodeChild);
            removed += toRemove.size();
            remove(toRemove, nodeChild, graph);
            if (removed == nodes) {
                result = toRemove;
            }
        }

        return result;
    }

    private static void remove(List<Integer> toRemove, int[] nodeChild, List<Integer>[] graph) {
        for (int node : toRemove) {
            nodeChild[node] = -1;
            for (int adjacent : graph[node]) {
                nodeChild[adjacent]--;
            }
        }
    }

    private static List<Integer> findLeaves(int[] nodeChild) {
        List<Integer> leaves = new ArrayList<>();
        for (int x = 0; x < nodeChild.length; x++) {
            if (nodeChild[x] == 0 || nodeChild[x] == 1) {
                leaves.add(x);
            }
        }
        return leaves;
    }

    private static List<Integer>[] createGraphAndNodeChild(int nodes, int[][] edges, int[] nodeChild) {
        List<Integer>[] graph = new List[nodes];
        for (int x = 0; x < nodes; x++) {
            graph[x] = new ArrayList<>();
        }

        for (int x = 0; x < edges.length; x++) {
            int[] edge = edges[x];
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            graph[to].add(from);
            nodeChild[from] = graph[from].size();
            nodeChild[to] = graph[to].size();
        }

        return graph;
    }

    private static void populateNodeValue(int startAt, int nodes, List<Integer>[] graph, int[] nodeValue) {
        boolean[] visited = new boolean[nodes];
        populateRecursively(startAt, visited, graph, nodeValue, 0); // dfs
    }

    private static void populateRecursively(int node, boolean[] visited, List<Integer>[] graph, int[] nodeValue, int currentValue) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        nodeValue[node] += currentValue;
        for (int adjacent : graph[node]) {
            if (!visited[adjacent]) {
                populateRecursively(adjacent, visited, graph, nodeValue, currentValue + 1);
            }
        }
    }

    private static void print(List<Integer> result) {
        LogUtils.logMessage("MinimumHeightTrees:");
        for (Integer mht : result) {
            LogUtils.logMessage(mht + " ", false);
        }
    }

    private static List<Integer> computeMinimumHeightTrees(int nodes, int[][] edges) {
        List<Integer>[] graph = createGraph(nodes, edges);
        int[] height = new int[nodes];
        Arrays.fill(height, -1);
        for (int x = 0; x < nodes; x++) {
            int nodeHeight = computeLongestPath(x, nodes, graph);
            height[x] = nodeHeight;
        }

        int minHeight = computeMinHeight(height);
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < nodes; x++) {
            if (height[x] == minHeight) {
                result.add(x);
            }
        }

        return result;
    }

    private static int computeMinHeight(int[] height) {
        int minHeight = Integer.MAX_VALUE;
        for (int nodeHeight : height) {
            if (nodeHeight == -1) {
                continue;
            }
            minHeight = Math.min(nodeHeight, minHeight);
        }
        return minHeight;
    }

    private static int computeLongestPath(int startAt, int nodes, List<Integer>[] graph) {
        boolean[] visited = new boolean[nodes + 1];
        return computeRecursively(startAt, graph, visited, -1);
    }

    private static int computeRecursively(int node, List<Integer>[] graph, boolean[] visited, int currentHeight) {
        if (visited[node]) {
            return currentHeight;
        }
        visited[node] = true;
        currentHeight++;
        int maxHeight = currentHeight;
        for (int adjacent : graph[node]) {
            if (!visited[adjacent]) {
                int adjacentHeight = computeRecursively(adjacent, graph, visited, currentHeight);
                maxHeight = Math.max(maxHeight, adjacentHeight);
            }
        }

        return maxHeight;
    }

    private static List<Integer>[] createGraph(int nodes, int[][] edges) {
        List[] graph = new List[nodes];
        for (int x = 0; x < nodes; x++) {
            graph[x] = new ArrayList();
        }

        for (int[] edge : edges) {
            // undirected
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        return graph;
    }
}
