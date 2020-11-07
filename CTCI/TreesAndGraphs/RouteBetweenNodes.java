package CTCI.TreesAndGraphs;

import DataStructures.GraphG;
import DataStructures.GraphNodeG;
import DataStructures.QueueG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteBetweenNodes {
    static boolean hasDirectedRoute(GraphNodeG from, GraphNodeG to) {
        Map<GraphNodeG, Boolean> visited = new HashMap<>();
        return hasDirectedRouteHelper(from, to, visited);
    }

    static private boolean hasDirectedRouteHelper(GraphNodeG from, GraphNodeG to, Map<GraphNodeG, Boolean> visited) {
        QueueG<GraphNodeG> toVisit = new QueueG<>();
        toVisit.add(from);
        while (!toVisit.isEmpty()) {
            GraphNodeG current = toVisit.remove();
            if (visited.containsKey(current)) {
                continue;
            }

            visited.put(current, true);
            if (current.equals(to)) {
                return true;
            }

            List<GraphNodeG> adjacencyList = current.getAdjacencyList();
            for (GraphNodeG graphNodeG : adjacencyList) {
                toVisit.add(graphNodeG);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GraphG graphG = new GraphG(10, false);
        graphG.connect(0, 1);
        graphG.connect(0, 2);
        graphG.connect(1, 4);
        graphG.connect(2, 3);
        graphG.connect(3, 9);
        graphG.connect(4, 6);
        graphG.connect(6, 7);
        graphG.connect(8, 0);
        graphG.connect(9, 3);

        testCase(0, 7, graphG);
        testCase(0, 3, graphG);
        testCase(0, 9, graphG);
        testCase(0, 8, graphG);
        testCase(8, 0, graphG);
    }

    private static void testCase(int from, int to, GraphG graphG) {
        GraphUtils.print(graphG);
        System.out.println("[[HasDirectedRoute]] Checking if there's a route connected " + from + " -> " + to);
        if (hasDirectedRoute(graphG.getNodes()[from], graphG.getNodes()[to])) {
            System.out.println("Yes! there's a route");
        } else {
            System.out.println("Sorry! couldn't find a route!");
        }
    }
}
