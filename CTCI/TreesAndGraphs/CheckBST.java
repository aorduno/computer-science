package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;

import java.util.List;

// for the purposes of this exercise we consider a BST to be
// a binary tree where every left child node is lower or equal than it's parent
// and every right child node is greater than it's parent
public class CheckBST {

    public static void main(String[] args) {
        TreeNodeG treeNodeG = new TreeNodeG(8);
        treeNodeG.setLeft(new TreeNodeG(4));
        treeNodeG.getLeft().setLeft(new TreeNodeG(2));
        treeNodeG.getLeft().setRight(new TreeNodeG(6));

        treeNodeG.setRight(new TreeNodeG(10));
        treeNodeG.getRight().setRight(new TreeNodeG(20));
        testCase(treeNodeG, false);
        testCase(treeNodeG, true);

        treeNodeG = new TreeNodeG(8);
        treeNodeG.setLeft(new TreeNodeG(4));
        treeNodeG.getLeft().setLeft(new TreeNodeG(2));
        treeNodeG.getLeft().setRight(new TreeNodeG(12));

        treeNodeG.setRight(new TreeNodeG(10));
        treeNodeG.getRight().setRight(new TreeNodeG(20));
        testCase(treeNodeG, false);
        testCase(treeNodeG, true);

        TreeNodeG minimalTree = MinimalTree.createMinimalTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        testCase(minimalTree, false);
        testCase(minimalTree, true);
    }

    private static void testCase(TreeNodeG treeNodeG, boolean noSpace) {
        System.out.println("[[CheckBST" + (noSpace ? "NoExtraSpace" : "") + "]]Checking if followin  tree isValidBSTUnique");
        ListOfDepths.print(ListOfDepths.getListOfDepths(treeNodeG));
        System.out.println("isValidBSTUnique: " + (noSpace ? isValidBSTNoSpace(treeNodeG) : isValidBSTUnique(treeNodeG)));
    }


    // simplest way to validate is (ASSUMING NO DUPES ARE ALLOWED)
    // 1) traverse in order
    // 2) populate a list as we traverse the tree (it should be sorted in ASC order.
    // 3) traverse the list and compare current with next element, if next is < than current, then is not valid BST
    // time complexity o(n) -- traverse every node once
    // space complexity o(n) -- extra list is being used
    static boolean isValidBSTUnique(TreeNodeG treeNodeG) {
        List<TreeNodeG> treeNodeGList = TreeUtils.getInOrderList(treeNodeG);
        TreeNodeG prev = null;
        for (TreeNodeG nodeG : treeNodeGList) {
            if (prev != null && prev.getData() > nodeG.getData()) {
                return false;
            }
            prev = nodeG;
        }

        return true;
    }

    static boolean isValidBSTNoSpace(TreeNodeG treeNodeG) {
        return isValidBSTNoSpaceRecursive(treeNodeG, null, null);
    }

    private static boolean isValidBSTNoSpaceRecursive(TreeNodeG treeNodeG, Integer min, Integer max) {
        if (treeNodeG == null) {
            return true;
        }

        if ((min != null && treeNodeG.getData() <= min) || (max != null && treeNodeG.getData() > max)) {
            return false;
        }

        if (!isValidBSTNoSpaceRecursive(treeNodeG.getLeft(), min, treeNodeG.getData()) || !isValidBSTNoSpaceRecursive(treeNodeG.getRight(), treeNodeG.getData(), max)) {
            return false;
        }

        return true;
    }
}
