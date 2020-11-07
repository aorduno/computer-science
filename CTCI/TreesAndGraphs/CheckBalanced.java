package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;

public class CheckBalanced {
    public static void main(String[] args) {
        TreeNodeG minimalTree = MinimalTree.createMinimalTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        testCase(minimalTree);

        TreeNodeG treeNodeG = new TreeNodeG(1);
        treeNodeG.add(2);
        treeNodeG.add(3);
        treeNodeG.add(4);
        treeNodeG.add(5);
        testCase(treeNodeG);

        treeNodeG = new TreeNodeG(5);
        treeNodeG.add(2);
        treeNodeG.add(1);
        treeNodeG.add(4);

        treeNodeG.add(40);
        treeNodeG.add(10);
        treeNodeG.add(30);
        treeNodeG.add(9);
        treeNodeG.add(8);
        treeNodeG.add(7);
        treeNodeG.add(6);
        testCase(treeNodeG);
    }

    private static void testCase(TreeNodeG treeNodeG) {
        System.out.println("[[CheckBalanced]] Here's the in order representation of input tree");
        TreeUtils.printInOrder(treeNodeG);
        System.out.println("isBalanced: " + isBalanced(treeNodeG));
    }

    private static boolean isBalanced(TreeNodeG treeNodeG) {
        int height = checkBalancedRecursively(treeNodeG);
        return height != Integer.MIN_VALUE;
    }

    // returns three things...
    // Integer.MIN_VALUE when found not-balanced children
    // -1 null tree
    // 0 for leafs when checking childs
    // else -- any valid height
    private static int checkBalancedRecursively(TreeNodeG treeNodeG) {
        if (treeNodeG == null) {
            return -1;
        }

        int leftRecursiveHeight = checkBalancedRecursively(treeNodeG.getLeft());
        if (leftRecursiveHeight == Integer.MIN_VALUE) { // catch and stop recursion
            return Integer.MIN_VALUE;
        }
        int leftHeight = leftRecursiveHeight + 1;
        int rightRecursiveHeight = checkBalancedRecursively(treeNodeG.getRight());
        if (rightRecursiveHeight == Integer.MIN_VALUE) { // catch and stop recursion
            return Integer.MIN_VALUE;
        }
        int rightHeight = rightRecursiveHeight + 1;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return Integer.MIN_VALUE;
        }

        return Math.max(leftHeight, rightHeight);
    }
}
