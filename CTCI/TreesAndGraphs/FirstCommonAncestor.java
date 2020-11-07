package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;
import DataStructures.TreeNodeParent;

import java.util.List;

public class FirstCommonAncestor {
    public static void main(String[] args) {
        TreeNodeParent root = new TreeNodeParent(8);
        TreeUtils.populateTreeNodeWithChilds(new int[]{4, 2, 1, 3, 6, 5, 7, 12, 10, 11}, root);
        testCase(root, 1, 5, "ParentDepth");
        testCase(root, 1, 8, "ParentDepth");
        testCase(root, 2, 4, "ParentDepth");
        testCase(root, 5, 11, "ParentDepth");
        testCase(root, 8, 0, "ParentDepth");

        testCase(root, 1, 5, "ParentOnly");
        testCase(root, 1, 8, "ParentOnly");
        testCase(root, 2, 4, "ParentOnly");
        testCase(root, 5, 11, "ParentOnly");
        testCase(root, 8, 0, "ParentOnly");

        testCase(root, 1, 5, "Branching");
        testCase(root, 1, 8, "Branching");
        testCase(root, 2, 4, "Branching");
        testCase(root, 5, 11, "Branching");
        testCase(root, 8, 0, "Branching");

        testCase(root, 1, 5, "BranchingOptimized");
        testCase(root, 1, 8, "BranchingOptimized");
        testCase(root, 2, 4, "BranchingOptimized");
        testCase(root, 5, 11, "BranchingOptimized");
        testCase(root, 8, 0, "BranchingOptimized");
    }

    private static void testCase(TreeNodeParent root, int a, int b, String approachType) {
        TreeNodeParent treeNodeA = (TreeNodeParent) root.find(a);
        TreeNodeParent treeNodeB = (TreeNodeParent) root.find(b);
        testCase(root, treeNodeA, treeNodeB, approachType);
    }

    private static void testCase(TreeNodeParent minimalTree, TreeNodeParent nodeA, TreeNodeParent nodeB, String approachType) {
        int aData = nodeA != null ? nodeA.getData() : Integer.MIN_VALUE;
        int bData = nodeB != null ? nodeB.getData() : Integer.MIN_VALUE;
        System.out.println("[[FirstCommonAncestor" + approachType + "]] Finding first common ancestor for " + aData + " and " + bData);
        System.out.println("Tree is: ");
        List<List<TreeNodeG>> listOfDepths = ListOfDepths.getListOfDepths(minimalTree);
        ListOfDepths.print(listOfDepths);
        TreeNodeParent ancestor = null;
        switch (approachType) {
            case "ParentDepth":
                ancestor = findFirstCommonAncestorDepth(nodeA, nodeB);
                break;
            case "ParentOnly":
                ancestor = findFirstCommonAncestorParent(minimalTree, nodeA, nodeB);
                break;
            case "Branching":
                ancestor = findFirstCommonAncestorBranching(minimalTree, nodeA, nodeB);
                break;
            case "BranchingOptimized":
                ancestor = findFirstCommonAncestorBranchingOptimized(minimalTree, nodeA, nodeB);
                break;
        }
        if (ancestor == null) {
            System.out.println("Error: no common ancestor found for " + aData + " and " + bData);
        } else {
            System.out.println("First Common Ancestor is: " + ancestor.getData());
        }
    }

    // no parent reference
    // time complexity o(n) where n is total number of nodes in the tree -- similar to the branching strategy but
    // we only find nodes once and bubble them up
    // space complexity o(h) where h is the height of the tree -- if this is a balanced tree if not it can be o(n)
    private static TreeNodeParent findFirstCommonAncestorBranchingOptimized(TreeNodeParent root, TreeNodeParent nodeA, TreeNodeParent nodeB) {
        if (!TreeUtils.contains(root, nodeA) || !TreeUtils.contains(root, nodeB)) {
            return null;
        }

        return firstCommonAncestorBranchingOptimizedHelper(root, nodeA, nodeB);
    }

    private static TreeNodeParent firstCommonAncestorBranchingOptimizedHelper(TreeNodeParent root, TreeNodeParent nodeA, TreeNodeParent nodeB) {
        if (root == null) {
            return null;
        }

        if (root == nodeA && root == nodeB) { // searching for the same value
            return root;
        }

        TreeNodeParent left = firstCommonAncestorBranchingOptimizedHelper(root.getLeft(), nodeA, nodeB);
        if (left != null && left != nodeA && left != nodeB) { // only bubble up when is not the target
            return left;
        }

        TreeNodeParent right = firstCommonAncestorBranchingOptimizedHelper(root.getRight(), nodeA, nodeB);
        if (right != null && right != nodeA && right != nodeB) { // only bubble up when is not the target
            return right;
        }

        if (left != null && right != null) {
            return root; // ancestor
        } else if (root == nodeA || root == nodeB) {
            return root;
        } else {
            return left == null ? right : left;
        }
    }

    // no parent reference
    // time complexity o(n) where n is total number of nodes in the tree -- even though every time we walk down the tree
    // we branch and that takes our nodes to search down to half
    // space complexity o(h) where h is the height of the tree -- if this is a balanced tree if not it can be o(n)
    private static TreeNodeParent findFirstCommonAncestorBranching(TreeNodeParent root, TreeNodeParent nodeA, TreeNodeParent nodeB) {
        if (!TreeUtils.contains(root, nodeA) || !TreeUtils.contains(root, nodeB)) { // missing in tree
            return null;
        }
        return branchingApproachHelper(root, nodeA, nodeB);


    }

    private static TreeNodeParent branchingApproachHelper(TreeNodeParent root, TreeNodeParent nodeA, TreeNodeParent nodeB) {
        // check if both are in the same side...
        boolean isAOnLeft = TreeUtils.contains(root.getLeft(), nodeA);
        boolean isBOnLeft = TreeUtils.contains(root.getLeft(), nodeB);
        if (isAOnLeft != isBOnLeft) {
            return root;
        }

        return isAOnLeft ? findFirstCommonAncestorBranching(root.getLeft(), nodeA, nodeB) : findFirstCommonAncestorBranching(root.getRight(), nodeA, nodeB);
    }

    static boolean contains(TreeNodeParent root, TreeNodeParent toFind) {
        if (root == null) {
            return false;
        }

        if (root == toFind) {
            return true;
        }

        return TreeUtils.contains(root.getLeft(), toFind) || TreeUtils.contains(root.getRight(), toFind);
    }

    // having parent reference make it easy to find...
    // time complexity o(n) where n is total number of nodes in the tree
    // space complexity o(h) where h is the height of the tree -- if this is a balanced tree if not it can be o(n)
    private static TreeNodeParent findFirstCommonAncestorParent(TreeNodeParent root, TreeNodeParent nodeA, TreeNodeParent nodeB) {
        if (!TreeUtils.contains(root, nodeA) || !TreeUtils.contains(root, nodeB)) { // either a or b are not in the tree
            return null;
        }

        if (TreeUtils.contains(nodeA, nodeB)) { // a is ancestor of b
            return nodeA;
        }

        if (TreeUtils.contains(nodeB, nodeA)) { // b is ancestor of a
            return nodeB;
        }

        // at this point they're in different branches so traverse them until we find them
        TreeNodeParent parent = nodeA.getParent();
        TreeNodeParent sibling = getSibling(nodeA);
        while (!TreeUtils.contains(sibling, nodeB)) {
            sibling = getSibling(parent);
            parent = parent.getParent();
        }
        return parent;
    }

    private static TreeNodeParent getSibling(TreeNodeParent treeNodeParent) {
        if (treeNodeParent == null || treeNodeParent.getParent() == null) {
            return null;
        }

        return treeNodeParent.getParent().getLeft() == treeNodeParent ? treeNodeParent.getParent().getRight() : treeNodeParent.getLeft();
    }

    // having parent reference make it easy to find...
    // time complexity o(h) where h is the height of the tree -- if this is a balanced tree if not it can be o(n)
    // space complexity o(h) where h is the height of the tree -- if this is a balanced tree if not it can be o(n)
    private static TreeNodeParent findFirstCommonAncestorDepth(TreeNodeParent nodeA, TreeNodeParent nodeB) {
        if (nodeA == null || nodeB == null) {
            return null;
        }

        if (nodeA == nodeB) {
            return nodeA;
        }
        int aDepth = getDepth(nodeA);
        int bDepth = getDepth(nodeB);
        int depthDiff = aDepth - bDepth;
        // put both at the same level...
        TreeNodeParent toMove = depthDiff > 0 ? nodeA : nodeB;
        int moves = Math.abs(depthDiff);
        while (moves > 0) {
            moves--;
            toMove = toMove.getParent();
        }

        if (depthDiff > 0) {
            nodeA = toMove;
        } else {
            nodeB = toMove;
        }

        // compare until they're the same
        while (nodeA != null && nodeB != null && nodeA != nodeB) {
            nodeA = nodeA.getParent();
            nodeB = nodeB.getParent();
        }

        return nodeA == nodeB ? nodeA : null;
    }

    private static int getDepth(TreeNodeParent nodeA) {
        int depth = 0;
        TreeNodeParent parent = nodeA.getParent();
        while (parent != null) {
            depth++;
            parent = parent.getParent();
        }

        return depth;
    }
}
