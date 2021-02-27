package LeetCode.hard;

import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.List;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        testCase(root);

        root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        testCase(root);

        root = new TreeNode(-3);
        testCase(root);
    }

    private static void testCase(TreeNode root) {
        LogUtils.logMessage("[[BinaryTreeMaximumPathSum]] Finding out the maximum path sum for the given tree which pre order traversal is...");
        List<TreeNode> preOrder = root.getPreOrderTraversalWithNulls();
        TreeNode.print(preOrder);

        int maxSum = computeMaxSum(root);
        LogUtils.logMessage("Max Path Sum found: " + maxSum);


    }

    private static int computeMaxSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        computeRecursively2(root, maxSum);
        return maxSum[0];
    }

    private static int computeRecursively(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }

        int leftSum = computeRecursively(node.left, maxSum);
        int rightSum = computeRecursively(node.right, maxSum);
        int currentSum = node.val;
        int currentMaxSum = Math.max(currentSum, // root
                Math.max(currentSum + leftSum, // root + left
                        Math.max(currentSum + rightSum, leftSum + currentSum + rightSum)) // root + right or root + left + right
        );
        maxSum[0] = Math.max(currentMaxSum, maxSum[0]); // update max as we find it
        return Math.max(currentSum, currentSum + Math.max(leftSum, rightSum));
    }

    private static int computeRecursively2(TreeNode node, int[] maxSum) {
        if (node == null) {
            return 0;
        }

        int leftSum = computeRecursively(node.left, maxSum);
        int rightSum = computeRecursively(node.right, maxSum);
        int currentSum = node.val;
        maxSum[0] = Math.max(currentSum + leftSum + rightSum, maxSum[0]); // update max as we find it
        return Math.max(0, currentSum + Math.max(leftSum, rightSum));
    }
}
