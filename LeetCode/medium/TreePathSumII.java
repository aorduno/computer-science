package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreePathSumII {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(11);
        treeNode.left.left.left = new TreeNode(7);
        treeNode.left.left.right = new TreeNode(2);

        treeNode.right = new TreeNode(8);
        treeNode.right.left = new TreeNode(13);
        treeNode.right.right = new TreeNode(4);
        treeNode.right.right.left = new TreeNode(5);
        treeNode.right.right.right = new TreeNode(1);
        testCase(treeNode, 22, "Recursive");

        testCase(treeNode, 22, "RecursiveLessSpace");

        testCase(treeNode, 22, "RecursiveLessSpace2");
    }

    private static void testCase(TreeNode treeNode, int targetSum, String approachType) {
        LogUtils.logMessage("[[TreePathSumII" + approachType + "]] Checking if given tree has a targetSum of " + targetSum + " from root to any leaf");
        LogUtils.logMessage("pre order traversal of tree node");
        List<TreeNode> postOrderTraversalWithNulls = treeNode.getPreOrderTraversal();
        TreeNode.print(postOrderTraversalWithNulls);

        List<List<Integer>> nodePaths = new ArrayList<>();
        switch (approachType) {
            case "Recursive":
                nodePaths = doPathSumRecursively(treeNode, targetSum, 0);
                break;
            case "RecursiveLessSpace":
                nodePaths = doPathSumRecursivelyLessSpace(treeNode, targetSum);
                break;
            case "RecursiveLessSpace2":
                nodePaths = doPathSumRecursivelyLessSpace2(treeNode, targetSum);
                break;
        }
        print(nodePaths);
    }

    private static List<List<Integer>> doPathSumRecursivelyLessSpace2(TreeNode treeNode, int targetSum) {
        List<List<Integer>> nodePaths = new ArrayList<>();
        computePathSumRecursively2(treeNode, 0, targetSum, nodePaths);
        return nodePaths;
    }

    private static int computePathSumRecursively2(TreeNode node, int currentSum, int targetSum, List<List<Integer>> nodePaths) {
        if (node == null) {
            return 0;
        }

        currentSum += node.val;
        if (currentSum == targetSum && isLeaf(node)) {
            List<Integer> nodePath = new ArrayList<>();
            nodePath.add(node.val);
            nodePaths.add(nodePath);
            return 1;
        }

        int currentSize = nodePaths.size();
        int leftPaths = computePathSumRecursively(node.left, currentSum, targetSum, nodePaths);
        addNodeToList2(node, currentSize, leftPaths, nodePaths);
        int rightPaths = computePathSumRecursively(node.right, currentSum, targetSum, nodePaths);
        addNodeToList2(node, currentSize + leftPaths, rightPaths, nodePaths);
        return leftPaths + rightPaths;
    }

    private static void addNodeToList2(TreeNode node, int from, int toAdd, List<List<Integer>> nodePaths) {
        int current = 0;
        while (current < toAdd) {
            List<Integer> nodePath = nodePaths.get(from + current);
            nodePath.add(0, node.val);
            current++;
        }
    }

    private static List<List<Integer>> doPathSumRecursivelyLessSpace(TreeNode treeNode, int targetSum) {
        List<List<Integer>> nodePaths = new ArrayList<>();
        computePathSumRecursively(treeNode, 0, targetSum, nodePaths);
        return nodePaths;
    }

    private static int computePathSumRecursively(TreeNode node, int currentSum, int targetSum, List<List<Integer>> nodePaths) {
        if (node == null) {
            return 0;
        }

        currentSum += node.val;
        if (currentSum == targetSum && isLeaf(node)) {
            List<Integer> nodePath = new ArrayList<>();
            nodePath.add(node.val);
            nodePaths.add(nodePath);
            return 1;
        }

        int leftPaths = computePathSumRecursively(node.left, currentSum, targetSum, nodePaths);
        addNodeToList(node, leftPaths, nodePaths);
        int rightPaths = computePathSumRecursively(node.right, currentSum, targetSum, nodePaths);
        addNodeToList(node, rightPaths, nodePaths);
        return leftPaths + rightPaths;
    }

    private static void addNodeToList(TreeNode node, int toAdd, List<List<Integer>> nodePaths) {
        int current = 0;
        int end = nodePaths.size() - 1;
        while (current < toAdd) {
            List<Integer> nodePath = nodePaths.get(end - current);
            nodePath.add(0, node.val);
            current++;
        }
    }

    private static void print(List<List<Integer>> nodePaths) {
        LogUtils.logMessage("Printing " + nodePaths.size() + " node paths found");
        for (List<Integer> nodePath : nodePaths) {
            ArrayUtils.print(nodePath);
        }
        LogUtils.logMessage("");
    }

    static List<List<Integer>> doPathSumRecursively(TreeNode node, int targetSum, int currentSum) {
        if (node == null) {
            return new ArrayList<>();
        }

        currentSum += node.val;
        List<List<Integer>> currentPaths = new ArrayList<>();
        if (targetSum == currentSum && isLeaf(node)) {
            List<Integer> nodePath = new ArrayList<>();
            nodePath.add(node.val);
            currentPaths.add(nodePath);
            return currentPaths;
        }
        List<List<Integer>> leftPaths = doPathSumRecursively(node.left, targetSum, currentSum);
        if (leftPaths.size() > 0) {
            appendToNodePaths(node, leftPaths);
        }
        List<List<Integer>> rightPaths = doPathSumRecursively(node.right, targetSum, currentSum);
        if (rightPaths.size() > 0) {
            appendToNodePaths(node, rightPaths);
        }

        currentPaths.addAll(leftPaths);
        currentPaths.addAll(rightPaths);
        return currentPaths;
    }

    private static void appendToNodePaths(TreeNode node, List<List<Integer>> nodePaths) {
        for (List<Integer> nodePath : nodePaths) {
            nodePath.add(0, node.val);
        }
    }

    static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
