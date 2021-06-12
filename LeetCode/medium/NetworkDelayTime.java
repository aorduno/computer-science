package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.*;

public class NetworkDelayTime {
    public static void main(String[] args) {
//        [[2,1,5],[2,3,1],[3,4,1],[4,5,1]]
//        5
//        2
        testCase(new int[][]{
                {2, 1, 5}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}
        }, 5, 2);

//        [[1,2,1],[2,1,3]]
//        2
//        2

        testCase(new int[][]{
                {1, 2, 1}, {2, 1, 3}
        }, 2, 2);
    }

    private static void testCase(int[][] times, int nodes, int startAt) {
        LogUtils.logMessage("[[NetworkDelayTime]] Computing netWorkDelayTime for given times input.");
        ArrayUtils.printMatrix(times);
        LogUtils.logMessage("Having " + nodes + " nodes and starting at: " + startAt);

        int delayTime = computeNetworkDelayTimeDijkstraHeap(times, nodes, startAt);
        LogUtils.logMessage("DelayTime: " + delayTime);
    }

    private static int computeNetworkDelayTimeDijkstraHeap(int[][] times, int nodes, int startAt) {
        List<Edge>[] graph = buildGraph(nodes, times);

        int[] nodeDistance = new int[nodes + 1];
        Arrays.fill(nodeDistance, Integer.MAX_VALUE);

        PriorityQueue<Edge> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a._distance));

        heap.add(new Edge(startAt, 0));
        while (!heap.isEmpty()) {
            Edge currentEdge = heap.remove();
            int node = currentEdge._node;
            int distance = currentEdge._distance;
            if (nodeDistance[node] != Integer.MAX_VALUE) {
                continue;
            }

            nodeDistance[node] = distance;
            for (Edge edge : graph[node]) {
                if (nodeDistance[edge._node] == Integer.MAX_VALUE) {
                    edge._distance += distance;
                    heap.add(edge);
                }
            }
        }

        return computeMaxDelayTime(nodes, nodeDistance);
    }

    private static int computeNetworkDelayTimeDijkstra(int[][] times, int nodes, int startAt) {
        List<Edge>[] graph = buildGraph(nodes, times);

        int[] nodeDistance = new int[nodes + 1];
        Arrays.fill(nodeDistance, Integer.MAX_VALUE);

        boolean[] visited = new boolean[nodes + 1];

        nodeDistance[startAt] = 0;
        while (true) {
            int candidate = -1;
            int candidateDistance = Integer.MAX_VALUE;
            for (int x = 1; x <= nodes; x++) {
                if (!visited[x] && nodeDistance[x] < candidateDistance) {
                    candidate = x;
                    candidateDistance = nodeDistance[x];
                }
            }

            if (candidate < 1) {
                break;
            }

            visited[candidate] = true;
            for (Edge edge : graph[candidate]) {
                int node = edge._node;
                nodeDistance[node] = Math.min(nodeDistance[node], candidateDistance + edge._distance);
            }
        }

        return computeMaxDelayTime(nodes, nodeDistance);
    }

    private static int computeNetworkDelayTimeDfs(int[][] times, int nodes, int startAt) {
        List<Edge>[] graph = buildGraph(nodes, times);

        int[] nodeDistance = new int[nodes + 1];
        Arrays.fill(nodeDistance, Integer.MAX_VALUE);

        dfs(graph, startAt, 0, nodeDistance);
        return computeMaxDelayTime(nodes, nodeDistance);
    }

    private static void dfs(List<Edge>[] graph, int node, int currentDistance, int[] nodeDistance) {
        if (currentDistance >= nodeDistance[node]) {
            return;
        }

        for (List<Edge> edges : graph) {
            Collections.sort(edges, Comparator.comparingInt(a -> a._distance));
        }

        nodeDistance[node] = currentDistance;
        for (Edge adjacent : graph[node]) {
            int adjacentNode = adjacent._node;
            int adjacentDistance = adjacent._distance;
            dfs(graph, adjacentNode, currentDistance + adjacentDistance, nodeDistance);
        }
    }

    private static int computeNetworkDelayTime(int[][] times, int nodes, int startAt) {
        List<Edge>[] graph = buildGraph(nodes, times);

        int[] nodeDistance = new int[nodes + 1];
        Arrays.fill(nodeDistance, Integer.MAX_VALUE);

        Queue<Edge> toVisit = new ArrayDeque<>();
        toVisit.add(new Edge(startAt, 0));
        while (!toVisit.isEmpty()) {
            Edge edge = toVisit.remove();
            int distance = edge._distance;
            int node = edge._node;
            nodeDistance[node] = Math.min(distance, nodeDistance[node]);
            for (Edge adjacent : graph[node]) {
                if (distance + adjacent._distance < nodeDistance[adjacent._node]) {
                    toVisit.add(new Edge(adjacent._node, (distance + adjacent._distance)));
                }
            }
        }

        return computeMaxDelayTime(nodes, nodeDistance);
    }

    private static int computeMaxDelayTime(int nodes, int[] nodeDistance) {
        int networkDelayTime = Integer.MIN_VALUE;
        for (int x = 1; x <= nodes; x++) {
            if (nodeDistance[x] == Integer.MAX_VALUE) {
                return -1;
            }

            networkDelayTime = Math.max(networkDelayTime, nodeDistance[x]);
        }

        return networkDelayTime;
    }

    private static List<Edge>[] buildGraph(int nodes, int[][] times) {
        List<Edge>[] graph = new List[nodes + 1];
        for (int x = 0; x <= nodes; x++) {
            graph[x] = new ArrayList<>();
        }

        for (int[] requisites : times) {
            graph[requisites[0]].add(new Edge(requisites[1], requisites[2]));
        }

        return graph;
    }


}

class Edge {
    int _node;
    int _distance;

    public Edge(int node, int distance) {
        _node = node;
        _distance = distance;
    }
}