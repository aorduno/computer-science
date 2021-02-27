package LeetCode.medium;

import CTCI.LogUtils;
import CTCI.TreesAndGraphs.TreeUtils;
import DataStructures.TreeNodeG;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FindNodeNeighborsAtKDistance {
    public static void main(String[] args) {
        TreeNodeG treeNodeG = new TreeNodeG(1);
        treeNodeG._left = new TreeNodeG(2);
        treeNodeG._right = new TreeNodeG(3);

        TreeNodeG left = new TreeNodeG(4);
        treeNodeG._left._left = left;
        treeNodeG._left._right = new TreeNodeG(5);

        treeNodeG._right._left = new TreeNodeG(6);
        treeNodeG._right._right = new TreeNodeG(7);

        TreeNodeG rightLeft = new TreeNodeG(10);
        treeNodeG._right._right._left = rightLeft;

        testCase(treeNodeG, left, 1);
        testCase(treeNodeG, left, 2);
        testCase(treeNodeG, left, 10);

        testCase(treeNodeG, rightLeft, 1);
        testCase(treeNodeG, rightLeft, 2);
        testCase(treeNodeG, rightLeft, 10);
    }

    private static void testCase(TreeNodeG root, TreeNodeG targetNode, int edgesAway) {
        LogUtils.logMessage("[[LeetCode.medium.FindNodeNeighborsAtKDistance]] Finding all neighbors at " + edgesAway + " edges away from the given target node " + targetNode.getData() + ". Tree is:", true);
        TreeUtils.printPreOrder(root);

        List<TreeNodeG> treeNodeGList = computeNeighbors(root, targetNode, edgesAway);
        LogUtils.logMessage("Parents are: ", true);
        if (treeNodeGList.isEmpty()) {
            LogUtils.logMessage("None!", true);
        }
        print(treeNodeGList);
    }

    private static List<TreeNodeG> computeNeighbors(TreeNodeG root, TreeNodeG targetNode, int edgesAway) {
        List<TreeNodeG> nodePath = computeNodePath(root, targetNode);
        List<TreeNodeG> neighbors = new ArrayList<>();
        int currentDistanceAway = 0;
        TreeNodeG previousNode = null;
        while (!nodePath.isEmpty()) {
            TreeNodeG currentNode = nodePath.remove(0);
            findNeighborsDown(currentNode, edgesAway, currentDistanceAway, neighbors, previousNode);
            previousNode = currentNode;
            currentDistanceAway++;
        }
        return neighbors;
    }

    private static void findNeighborsDown(TreeNodeG treeNodeG, int targetDistance, int currentDistanceAway, List<TreeNodeG> neighbors, TreeNodeG previousNode) {
        if (treeNodeG == null || (currentDistanceAway > targetDistance) || previousNode == treeNodeG) { // nothing else to see or went to far...
            return;
        }

        if (targetDistance == currentDistanceAway) {
            neighbors.add(treeNodeG);
            return;
        }

        findNeighborsDown(treeNodeG.getLeft(), targetDistance, currentDistanceAway + 1, neighbors, previousNode);
        findNeighborsDown(treeNodeG.getRight(), targetDistance, currentDistanceAway + 1, neighbors, previousNode);
    }

    private static void print(List<TreeNodeG> treeNodeGList) {
        for (TreeNodeG treeNodeG : treeNodeGList) {
            LogUtils.logMessage(treeNodeG.getData() + "", true);
        }
    }

    // from root to targetnode
    private static List<TreeNodeG> computeNodePath(TreeNodeG treeNodeG, TreeNodeG target) {
        List<TreeNodeG> parents = new Stack<>();
        findParentsRecursively(treeNodeG, target, parents);
        return parents;
    }

    private static boolean findParentsRecursively(TreeNodeG treeNodeG, TreeNodeG target, List<TreeNodeG> parents) {
        if (treeNodeG == null) {
            return false;
        }

        if (treeNodeG == target) {
            parents.add(treeNodeG);
            return true;
        }

        if (findParentsRecursively(treeNodeG.getLeft(), target, parents) || findParentsRecursively(treeNodeG.getRight(), target, parents)) {
            parents.add(treeNodeG);
            return true;
        }

        return false;
    }
}
