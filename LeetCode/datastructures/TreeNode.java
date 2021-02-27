package LeetCode.datastructures;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public List<TreeNode> getInOrderTraversal() {
        List<TreeNode> treeNodes = new ArrayList<>();
        computeInOrderRecursively(this, treeNodes, false);
        return treeNodes;
    }

    public List<TreeNode> getInOrderTraversalWithNulls() {
        List<TreeNode> treeNodes = new ArrayList<>();
        computeInOrderRecursively(this, treeNodes, true);
        return treeNodes;
    }

    private void computeInOrderRecursively(TreeNode node, List<TreeNode> treeNodes, boolean addNulls) {
        if (node == null) {
            if (addNulls) {
                treeNodes.add(null);
            }
            return;
        }

        computeInOrderRecursively(node.left, treeNodes, addNulls);
        treeNodes.add(node);
        computeInOrderRecursively(node.right, treeNodes, addNulls);
    }

    public List<TreeNode> getPostOrderTraversal() {
        List<TreeNode> treeNodes = new ArrayList<>();
        computePostOrderRecursively(this, treeNodes, false);
        return treeNodes;
    }

    private void computePostOrderRecursively(TreeNode node, List<TreeNode> treeNodes, boolean addNulls) {
        if (node == null) {
            if (addNulls) {
                treeNodes.add(node);
            }
            return;
        }

        computePostOrderRecursively(node.left, treeNodes, addNulls);
        computePostOrderRecursively(node.right, treeNodes, addNulls);
        treeNodes.add(node);
    }

    public List<TreeNode> getPreOrderTraversal() {
        List<TreeNode> treeNodes = new ArrayList<>();
        computePreOrderRecursively(this, treeNodes, false);
        return treeNodes;
    }

    public List<TreeNode> getPreOrderTraversalWithNulls() {
        List<TreeNode> treeNodes = new ArrayList<>();
        computePreOrderRecursively(this, treeNodes, true);
        return treeNodes;
    }

    private void computePreOrderRecursively(TreeNode node, List<TreeNode> treeNodes, boolean addNulls) {
        if (node == null) {
            if (addNulls) {
                treeNodes.add(node);
            }
            return;
        }

        treeNodes.add(node);
        computePreOrderRecursively(node.left, treeNodes, addNulls);
        computePreOrderRecursively(node.right, treeNodes, addNulls);
    }

    public static void print(List<TreeNode> treeNodes) {
        for (TreeNode treeNode : treeNodes) {
            LogUtils.logMessage((treeNode == null ? "null" : treeNode.val) + " ", false);
        }
        LogUtils.logMessage("");
    }
}