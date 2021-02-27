package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        testCase(root);

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        testCase(root);
    }

    private static void testCase(TreeNode root) {
        LogUtils.logMessage("[[BinaryTreeLevelOrderTraversal]] traversing given tree nodes by level");
        List<List<Integer>> nodesByLevels = computeNodesByLevels(root);
        print(nodesByLevels);

    }

    private static void print(List<List<Integer>> nodesByLevels) {
        LogUtils.logMessage("Printing levels...");
        for (int x = 0; x < nodesByLevels.size(); x++) {
            LogUtils.logMessage("Level " + x);
            ArrayUtils.print(nodesByLevels.get(x));
        }
    }

    private static List<List<Integer>> computeNodesByLevels(TreeNode root) {
        List<List<Integer>> nodesByLevels = new ArrayList<>();
        computeRecursively(root, 0, nodesByLevels);
        return nodesByLevels;
    }

    private static void computeRecursively(TreeNode node, int level, List<List<Integer>> nodesByLevels) {
        if (node == null) {
            return;
        }

        addToList(node, level, nodesByLevels);
        computeRecursively(node.left, level + 1, nodesByLevels);
        computeRecursively(node.right, level + 1, nodesByLevels);
    }

    private static void addToList(TreeNode node, int level, List<List<Integer>> nodesByLevels) {
        List<Integer> nodes = new ArrayList<>();
        if (nodesByLevels.size() - 1 < level) {
            nodesByLevels.add(nodes);
        } else {
            nodes = nodesByLevels.get(level);
        }

        nodes.add(node.val);
    }
}
