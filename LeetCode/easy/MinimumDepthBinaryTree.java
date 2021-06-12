package LeetCode.easy;

import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class MinimumDepthBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        testCase(root);
        testCase(new TreeNode(10));
        testCase(null);
    }

    private static void testCase(TreeNode root) {
        LogUtils.logMessage("[[MinimumDepthBinaryTree]] Computing minimumDepth for given BinaryTree which preOrderTraversal is...");
        if (root == null) {
            LogUtils.logMessage("[null]");
        } else {
            TreeNode.print(root.getPreOrderTraversalWithNulls());
        }
        int minDepth = computeMinDepthFaster(root);
        LogUtils.logMessage("MinDepth: " + minDepth);
    }

    private static int computeMinDepthFaster(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int toProcess = queue.size();
            for (int x = 0; x < toProcess; x++) {
                TreeNode currentNode = queue.remove();
                if (isLeaf(currentNode)) {
                    return depth;
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return depth;
    }

    private static int computeMinDepth(TreeNode node) {
        int minDepth = computeMinDepthRecursively(node, 0);
        return minDepth == Integer.MAX_VALUE ? -1 : minDepth + 1;
    }

    private static int computeMinDepthRecursively(TreeNode node, int currentDepth) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        if (isLeaf(node)) {
            return currentDepth;
        }

        int minDepthLeft = computeMinDepthRecursively(node.left, currentDepth + 1);
        int minDepthRight = computeMinDepthRecursively(node.right, currentDepth + 1);
        return Math.min(minDepthLeft, minDepthRight);
    }

    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
