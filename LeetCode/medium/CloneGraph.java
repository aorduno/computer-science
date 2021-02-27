package LeetCode.medium;

import CTCI.LogUtils;

import java.util.*;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
public class CloneGraph {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));
        testCase(node1);
    }

    private static void testCase(Node node) {
        LogUtils.logMessage("[[CloneGraph]] Cloning given graph");
        Node clone = cloneGraph(node);
        LogUtils.logMessage("Cloned!");
    }

    public static Node cloneGraph(Node node) {
        return doClone(node);
    }

    private static Node doClone(Node node) {
        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Node> cloneMap = new HashMap<>();
        Queue<Node> toVisit = new LinkedList<>();
        toVisit.add(node);
        while (!toVisit.isEmpty()) {
            Node currentNode = toVisit.remove();
            if (visited.containsKey(currentNode)) {
                continue;
            }

            visited.put(currentNode, true);
            Node clone = createClone(currentNode, cloneMap);
            for (Node neighbor : currentNode.neighbors) {
                Node neighborClone = createClone(neighbor, cloneMap);
                clone.neighbors.add(neighborClone);
                toVisit.add(neighbor);
            }
        }

        return cloneMap.get(node);
    }

    private static Node createClone(Node node, Map<Node, Node> cloneMap) {
        if (cloneMap.containsKey(node)) {
            return cloneMap.get(node);
        }

        Node clone = new Node(node.val);
        cloneMap.put(node, clone);
        return clone;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
