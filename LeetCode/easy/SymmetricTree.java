package LeetCode.easy;

import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        testCase(root, "Iterative");

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        testCase(root, "Iterative");
    }

    private static void testCase(TreeNode root, String type) {
        LogUtils.logMessage("[[SymmetricTree" + type + "]] Validating if given tree is symmetric", true);
        printPostOrder(root);

        boolean isSymmetric = false;
        switch (type) {
            case "Naive":
                isSymmetric = isSymmetricTree(root);
                break;
            case "Recursive":
                isSymmetric = isSymmetricTreeRecursive(root);
                break;
            case "Iterative":
                isSymmetric = isSymmetricTreeIterative(root);
                break;
        }
        LogUtils.logMessage("isSymmetric: " + isSymmetric);
    }

    private static boolean isSymmetricTreeIterative(TreeNode root) {
        Queue<TreeNode> toProcess = new LinkedList<>();
        if (root == null) {
            return true;
        }
        toProcess.add(root.left);
        toProcess.add(root.right);
        while (!toProcess.isEmpty()) {
            TreeNode a = toProcess.remove();
            TreeNode b = toProcess.remove();
            if (a == null || b == null) {
                if (a != b) {
                    return false;
                }
                continue;
            }

            if (a.val != b.val) {
                return false;
            }

            toProcess.add(a.left);
            toProcess.add(b.right);

            toProcess.add(a.right);
            toProcess.add(b.left);
        }

        return true;
    }


    // time complexity o(n)
    // space complexity o(log n) if tree is balanced, o(n) if is not
    private static boolean isSymmetricTreeRecursive(TreeNode root) {
        return checkSymmetryRecursively(root.left, root.right);
    }

    private static boolean checkSymmetryRecursively(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return a.val == b.val && checkSymmetryRecursively(a.left, b.right) && checkSymmetryRecursively(a.right, b.left);
    }

    private static boolean isSymmetricTree(TreeNode root) {
        List<List<TreeNode>> nodesByLevels = getNodesByLevels(root);
        for (int x = 1; x < nodesByLevels.size(); x++) { // do not care about root level
            List<TreeNode> treeNodes = nodesByLevels.get(x);
            if (treeNodes.size() % 2 != 0) { // node count should be even to be symmetric
                return false;
            }

            if (!checkSymmetry(treeNodes)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkSymmetry(List<TreeNode> treeNodes) {
        int left = 0;
        int right = treeNodes.size() - 1;
        while (left < right) {
            TreeNode nodeLeft = treeNodes.get(left);
            TreeNode nodeRight = treeNodes.get(right);
            if ((nodeLeft == null || nodeRight == null)) {
                if (nodeLeft != nodeRight) {
                    return false;
                }
                left++;
                right--;
                continue;
            }

            if (nodeLeft.val != nodeRight.val) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static List<List<TreeNode>> getNodesByLevels(TreeNode root) {
        List<List<TreeNode>> nodesByLevels = new ArrayList<>();
        computeRecursively(root, 0, nodesByLevels);
        return nodesByLevels;
    }

    private static void computeRecursively(TreeNode node, int level, List<List<TreeNode>> nodesByLevels) {
        if (node == null) {
            addToList(node, level, nodesByLevels);
            return;
        }

        addToList(node, level, nodesByLevels);
        computeRecursively(node.left, level + 1, nodesByLevels);
        computeRecursively(node.right, level + 1, nodesByLevels);
    }

    private static void addToList(TreeNode node, int level, List<List<TreeNode>> nodesByLevels) {
        List<TreeNode> nodes = new ArrayList<>();
        if (nodesByLevels.size() - 1 < level) {
            nodesByLevels.add(nodes);
        } else {
            nodes = nodesByLevels.get(level);
        }

        nodes.add(node);
    }

    private static void printPostOrder(TreeNode root) {
        List<TreeNode> postOrder = root.getPreOrderTraversal();
        LogUtils.logMessage("PostOrder:", true);
        for (TreeNode treeNode : postOrder) {
            LogUtils.logMessage(treeNode.val + " ", false);
        }

        LogUtils.logMessage("", true);
    }
}
