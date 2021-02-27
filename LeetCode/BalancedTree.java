package LeetCode;

import DataStructures.NodeBinaryTree;

class Height {
    int height;
}

class BalancedTree {
    public boolean isBalanced(NodeBinaryTree root) {
        return isNodeBalancedImproved(root, new Height());
    }

    public boolean isNodeBalancedImproved(NodeBinaryTree root, Height height) {
        if (root == null) {
            height.height = 0;
            return true;
        }

        NodeBinaryTree left = root._left;
        NodeBinaryTree right = root._right;

        Height lH = new Height();
        Height rH = new Height();
        boolean leftBalanced = isNodeBalancedImproved(left, lH);
        boolean rightBalanced = isNodeBalancedImproved(right, rH);
        int leftHeight = lH.height;
        int rightHeight = rH.height;
        height.height = Math.max(leftHeight, rightHeight) + 1;

        return leftBalanced && rightBalanced && Math.abs(leftHeight - rightHeight) <= 1;
    }
}
