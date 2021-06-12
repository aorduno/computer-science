package LeetCode.easy;

import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.TreeSet;

public class SecondMinimumNodeInBTree {
    long answer = Long.MAX_VALUE;
    int min;

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(5);

        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(7);
        testCase(treeNode);
    }

    private static void testCase(TreeNode treeNode) {
        LogUtils.logMessage("[[SecondMinimumNodeInBTree]] Finding out the second unique minimum number in the given BTree");
        TreeNode.print(treeNode.getPreOrderTraversalWithNulls());


        int secondMinimum = computeSecondMinimumBetter(treeNode);
        LogUtils.logMessage("Second Minimum: " + secondMinimum);
    }

    private static int computeSecondMinimumBetter(TreeNode treeNode) {
        long[] answer = new long[]{Long.MAX_VALUE};
        computeRecursivelyBetter(treeNode, treeNode.val, answer);
        return (int) answer[0];
    }

    private static void computeRecursivelyBetter(TreeNode treeNode, int minValue, long[] answer) {
        if (treeNode == null) {
            return;
        }

        if (minValue < treeNode.val && treeNode.val < answer[0]) {
            answer[0] = treeNode.val;
        } else if (treeNode.val == minValue) {
            computeRecursivelyBetter(treeNode.left, minValue, answer);
            computeRecursivelyBetter(treeNode.right, minValue, answer);
        }
    }

    private static int computeSecondMinimum(TreeNode treeNode) {
        TreeSet<Integer> treeValues = computeTreeValues(treeNode);
        if (treeValues.size() < 2) {
            return -1;
        }

        treeValues.pollFirst();
        return treeValues.pollFirst();
    }

    private static TreeSet<Integer> computeTreeValues(TreeNode treeNode) {
        TreeSet<Integer> treeValues = new TreeSet<>();
        computeRecursively(treeNode, treeValues);
        return treeValues;
    }

    private static void computeRecursively(TreeNode treeNode, TreeSet<Integer> treeValues) {
        if (treeNode == null) {
            return;
        }

        treeValues.add(treeNode.val);
        computeRecursively(treeNode.left, treeValues);
        computeRecursively(treeNode.right, treeValues);
    }
}
