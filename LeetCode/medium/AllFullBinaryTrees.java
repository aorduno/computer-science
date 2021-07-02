package LeetCode.medium;

import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class AllFullBinaryTrees {
    public static void main(String[] args) {
        for (int x = 0; x < 10; x++) {
            testCase(x);
        }
    }

    private static void testCase(int n) {
        LogUtils.logMessage("[[AllFullBinaryTrees]] Computing all possible full binary tree for given number of nodes: " + n);

        List<TreeNode> nodeList = allPossibleFBTMemo(n);
        LogUtils.logMessage("Found " + nodeList.size());
        for (TreeNode treeNode : nodeList) {
            List<TreeNode> preOrderTraversalWithNulls = treeNode.getPreOrderTraversalWithNulls();
            LogUtils.logMessage("Printing...");
            TreeNode.print(preOrderTraversalWithNulls);
        }
    }

    private static List<TreeNode> allPossibleFBTMemo(int n) {
        List<TreeNode>[] memo = new ArrayList[n + 1];
        return computeAllPossibleFBTRecursiveMemo(n, memo);
    }

    private static List<TreeNode> computeAllPossibleFBTRecursiveMemo(int n, List<TreeNode>[] memo) {
        List<TreeNode> allPossibleFBT = new ArrayList<>();
        if (memo[n] != null) {
            return memo[n];
        }

        if (n % 2 == 0) {
            memo[n] = allPossibleFBT;
            return allPossibleFBT;
        }

        if (n == 1) {
            allPossibleFBT.add(new TreeNode(0));
            memo[n] = allPossibleFBT;
            return allPossibleFBT;
        }

        for (int left = 0; left < n; left++) {
            int right = n - 1 - left;
            List<TreeNode> leftNodes = computeAllPossibleFBTRecursiveMemo(left, memo);
            List<TreeNode> rightNodes = computeAllPossibleFBTRecursiveMemo(right, memo);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode parent = new TreeNode(0);
                    parent.left = leftNode;
                    parent.right = rightNode;
                    allPossibleFBT.add(parent);
                }
            }
        }
        memo[n] = allPossibleFBT;
        return memo[n];
    }

    private static List<TreeNode> computeAllPossibleFBTRecursive(int n) {
        List<TreeNode> allPossibleFBT = new ArrayList<>();
        if (n % 2 == 0) {
            return allPossibleFBT;
        }

        if (n == 1) {
            allPossibleFBT.add(new TreeNode(0));
            return allPossibleFBT;
        }

        for (int left = 0; left < n; left++) {
            int right = n - 1 - left;
            List<TreeNode> leftNodes = computeAllPossibleFBTRecursive(left);
            List<TreeNode> rightNodes = computeAllPossibleFBTRecursive(right);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode parent = new TreeNode(0);
                    parent.left = leftNode;
                    parent.right = rightNode;
                    allPossibleFBT.add(parent);
                }
            }
        }
        return allPossibleFBT;
    }
}
