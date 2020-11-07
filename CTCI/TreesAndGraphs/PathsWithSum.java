package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;

import java.util.HashMap;
import java.util.List;

public class PathsWithSum {
    public static void main(String[] args) {
        TreeNodeG treeNodeG = new TreeNodeG(10);
        treeNodeG._left = new TreeNodeG(5);
        treeNodeG._left._left = new TreeNodeG(3);
        treeNodeG._left._left._left = new TreeNodeG(3);
        treeNodeG._left._left._right = new TreeNodeG(-2);

        treeNodeG._left._right = new TreeNodeG(1);
        treeNodeG._left._right._right = new TreeNodeG(2);

        treeNodeG._right = new TreeNodeG(-3);
        treeNodeG._right._right = new TreeNodeG(11);

        testCase(treeNodeG, 8, false);
        testCase(treeNodeG, 6, false);
        testCase(treeNodeG, 11, false);
        testCase(treeNodeG, 99, false);

        testCase(treeNodeG, 8, true);
        testCase(treeNodeG, 6, true);
        testCase(treeNodeG, 11, true);
        testCase(treeNodeG, 99, true);
    }

    private static void testCase(TreeNodeG binaryTreeNode, int targetSum, boolean optimized) {
        System.out.println("[[PathsWithSum]] Checking how many paths with sum " + targetSum + " the input tree has");
        List<List<TreeNodeG>> listOfDepths = ListOfDepths.getListOfDepths(binaryTreeNode);
        System.out.println("Tree is: ");
        ListOfDepths.print(listOfDepths);

        int totalPaths = optimized ? computePathsWithSumOptimized(binaryTreeNode, targetSum) : computePathsWithSumBruteForce(binaryTreeNode, targetSum);
        System.out.println("Tree has " + totalPaths + " paths that sum to " + targetSum);
    }

    // this is optimized solution
    // we only traverse the tree in order once
    // time complexity o(n) where n is the number of nodes in the tree
    // space complexity o(h) -- where h is the height of the tree
    private static int computePathsWithSumOptimized(TreeNodeG binaryTreeNode, int targetSum) {
        return computePathsWithSumOptimized(binaryTreeNode, targetSum, 0, new HashMap<Integer, Integer>());
    }

    private static int computePathsWithSumOptimized(TreeNodeG binaryTreeNode, int targetSum, int currentSum, HashMap<Integer, Integer> pathCount) {
        if (binaryTreeNode == null) {
            return 0;
        }

        currentSum += binaryTreeNode.getData();
        int sumMinusTarget = currentSum - targetSum;
        int totalPathsCount = pathCount.getOrDefault(sumMinusTarget, 0);
        if (currentSum == targetSum) {
            totalPathsCount++;
        }

        incrementPathsCount(pathCount, currentSum, 1);
        totalPathsCount += computePathsWithSumOptimized(binaryTreeNode.getLeft(), targetSum, currentSum, pathCount);
        totalPathsCount += computePathsWithSumOptimized(binaryTreeNode.getRight(), targetSum, currentSum, pathCount);
        incrementPathsCount(pathCount, currentSum, -1);

        return totalPathsCount;
    }

    private static void incrementPathsCount(HashMap<Integer, Integer> pathCount, int currentPathSum, int increment) {
        int newValue = pathCount.getOrDefault(currentPathSum, 0) + increment;
        if (newValue == 0) {
            pathCount.remove(currentPathSum);
        } else {
            pathCount.put(currentPathSum, newValue);
        }
    }

    // this is brute force approach
    // traverse the tree in order, branch and repeat
    // time complexity o(n log n) where n is the number of nodes in the tree
    // space complexity o(h) where h = is the height of the tree
    private static int computePathsWithSumBruteForce(TreeNodeG root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int rootTotals = getPathsWithSumFromNode(root, targetSum, 0);

        int leftTotals = computePathsWithSumBruteForce(root._left, targetSum);
        int rightTotals = computePathsWithSumBruteForce(root._right, targetSum);
        return rootTotals + leftTotals + rightTotals;
    }

    private static int getPathsWithSumFromNode(TreeNodeG root, int targetSum, int currentSum) {
        if (root == null) {
            return 0;
        }

        currentSum += root.getData();
        int totalPaths = 0;
        if (currentSum == targetSum) {
            totalPaths++;
        }

        totalPaths += getPathsWithSumFromNode(root._left, targetSum, currentSum);
        totalPaths += getPathsWithSumFromNode(root._right, targetSum, currentSum);
        return totalPaths;
    }

}
