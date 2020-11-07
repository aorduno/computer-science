package CTCI.TreesAndGraphs;

import DataStructures.GraphG;
import DataStructures.GraphNodeG;
import DataStructures.QueueG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphUtils {
    public static void print(GraphG graphG) {
        System.out.println("[[Printing" + (graphG.isUndirected() ? "Undirected" : "Directed") + "Graph]]");
        for (GraphNodeG node : graphG.getNodes()) {
            List<GraphNodeG> adjacencyList = node.getAdjacencyList();
            System.out.print("Printing adjancencyList for " + node.getData());
            for (GraphNodeG graphNodeG : adjacencyList) {
                System.out.print(" -> " + graphNodeG.getData());
            }
            System.out.println();
        }
    }

    public static void printDepthFirstSearch(GraphG graphG) {
        System.out.println("[[TraversingAndPriting" + (graphG.isUndirected() ? "Undirected" : "Directed") + "Graph DFS]]");
        GraphNodeG[] nodes = graphG.getNodes();
        Map<GraphNodeG, Boolean> visitedMap = new HashMap<>();
        printDepthFirstSearchHelper(nodes[0], visitedMap);
    }

    private static void printDepthFirstSearchHelper(GraphNodeG<Integer> node, Map<GraphNodeG, Boolean> visitedMap) {
        System.out.println("visiting node: " + node.getData());
        visitedMap.put(node, true);
        List<GraphNodeG> adjacencyList = node.getAdjacencyList();
        for (GraphNodeG graphNodeG : adjacencyList) {
            if (!visitedMap.containsKey(graphNodeG)) {
                printDepthFirstSearchHelper(graphNodeG, visitedMap);
            }
        }
    }

    public static void printBreadthFirstSearch(GraphG graphG) {
        System.out.println("[[TraversingAndPriting" + (graphG.isUndirected() ? "Undirected" : "Directed") + "Graph BFS]]");
        GraphNodeG[] nodes = graphG.getNodes();
        Map<GraphNodeG, Boolean> visitedMap = new HashMap<>();
        printBreadthFirstSearchHelper(nodes[0], visitedMap);
    }

    private static void printBreadthFirstSearchHelper(GraphNodeG<Integer> node, Map<GraphNodeG, Boolean> visitedMap) {
        QueueG<GraphNodeG> queue = new QueueG<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            GraphNodeG graphNodeG = queue.remove();
            if (visitedMap.containsKey(graphNodeG)) {
                continue;
            }

            System.out.println("visiting node: " + graphNodeG.getData());
            List<GraphNodeG> adjacencyList = graphNodeG.getAdjacencyList();
            for (GraphNodeG nodeG : adjacencyList) {
                queue.add(nodeG);
            }
        }
    }
}
