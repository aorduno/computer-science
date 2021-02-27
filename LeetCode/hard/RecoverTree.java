package LeetCode.hard;

import CTCI.LogUtils;
import CTCI.TreesAndGraphs.TreeUtils;
import LeetCode.datastructures.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RecoverTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(2);
        testCase(treeNode, false);

        treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(2);
        testCase(treeNode, true);

        treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(2);
        testCase(treeNode, false);

        treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(2);
        testCase(treeNode, true);

        treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(1);
        testCase(treeNode, false);

        treeNode = new TreeNode(0);
        treeNode.left = new TreeNode(1);
        testCase(treeNode, true);

        treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(3);
        treeNode.right = new TreeNode(1);
        testCase(treeNode, true);
    }

    private static void testCase(TreeNode treeNode, boolean noSpace) {
        LogUtils.logMessage("[[RecoverBinarySearchTree" + (noSpace ? "NoSpace" : "") + "]] recovering binary search tree given which inOrder traversal is", true);
        TreeUtils.printInOrder(treeNode);
        LogUtils.logMessage("", true);

        if (noSpace) {
            recoverTreeNoSpace(treeNode);
        } else {
            recoverTree(treeNode);
        }

        LogUtils.logMessage("After recovery:", true);
        TreeUtils.printInOrder(treeNode);
        LogUtils.logMessage("", true);
    }

    // similar approach but no extra space required...
    // traverse the tree in order and store a reference to the previous visited node
    // if what we're visiting is lower than the previous then we store to be swap
    // note that we care about the first and last ones we find in violation
    // time o(n)
    // space o(1)
    private static void recoverTreeNoSpace(TreeNode treeNode) {
        TreeNode[] toSwap = new TreeNode[2];
        TreeNode[] previous = new TreeNode[1];
        doRecoverRecursive(treeNode, previous, toSwap);
        if (toSwap[0] == null) {
            return;
        }

        swap(toSwap[0], toSwap[1]);
    }

    private static void doRecoverRecursive(TreeNode treeNode, TreeNode[] previous, TreeNode[] toSwap) {
        if (treeNode == null) {
            return;
        }

        doRecoverRecursive(treeNode.left, previous, toSwap);
        if (previous[0] != null && previous[0].val > treeNode.val) {
            if (toSwap[0] == null) {
                toSwap[0] = previous[0];
            }

            toSwap[1] = treeNode;
        }
        previous[0] = treeNode;

        doRecoverRecursive(treeNode.right, previous, toSwap);
    }

    // simple approach
    // get in order traversal
    // traverse from left to right until we find x > x + 1
    // traverse from right to left until we find x + 1 < x
    // time o(n)
    // space o(n) -- due auxiliar in order traversal list
    private static void recoverTree(TreeNode treeNode) {
        List<TreeNode> inOrderTraversal = computeInOrderTraversal(treeNode);
        TreeNode toSwap = null;
        for (int x = 0; x < inOrderTraversal.size() - 1; x++) {
            if (inOrderTraversal.get(x).val > inOrderTraversal.get(x + 1).val) {
                toSwap = inOrderTraversal.get(x);
                break;
            }
        }

        TreeNode swapWith = null;
        for (int x = inOrderTraversal.size() - 2; x >= 0; x--) {
            if (inOrderTraversal.get(x + 1).val < inOrderTraversal.get(x).val) {
                swapWith = inOrderTraversal.get(x + 1);
                break;
            }
        }

        if (toSwap == null || swapWith == null) { // nothing to swap
            return;
        }

        swap(toSwap, swapWith);
    }

    // this just swaps the value... does not change the structure...
    private static void swap(TreeNode toSwap, TreeNode swapWith) {
        toSwap.val = toSwap.val + swapWith.val;
        swapWith.val = toSwap.val - swapWith.val;
        toSwap.val = toSwap.val - swapWith.val;
    }

    private static List<TreeNode> computeInOrderTraversal(TreeNode treeNode) {
        List<TreeNode> inOrder = new ArrayList<>();
        computeRecursively(treeNode, inOrder);
        return inOrder;
    }

    private static void computeRecursively(TreeNode treeNode, List<TreeNode> inOrder) {
        if (treeNode == null) {
            return;
        }
        computeRecursively(treeNode.left, inOrder);
        inOrder.add(treeNode);
        computeRecursively(treeNode.right, inOrder);
    }

}
