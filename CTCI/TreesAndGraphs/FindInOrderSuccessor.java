package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeParent;

public class FindInOrderSuccessor {


    public static void main(String[] args) {
        // top
        TreeNodeParent root = new TreeNodeParent(8);
        TreeUtils.populateTreeNodeWithChilds(new int[]{4, 2, 1, 3, 6, 5, 7, 12, 10, 11}, root);
        testCase(root.getLeft().getLeft());
        testCase(root.getLeft().getRight());
        testCase(root.getLeft().getLeft().getLeft());
        testCase(root.getLeft().getLeft().getRight());

        testCase(root.getRight());
        testCase(root.getRight().getLeft());
        testCase(root.getRight().getLeft().getRight());
        testCase(root);
    }

    private static void testCase(TreeNodeParent toFind) {
        System.out.println("Finding in order successor for : " + toFind.getData());
        TreeNodeParent inOrderSuccesor = findInOrderSuccesor(toFind);
        if (inOrderSuccesor == null) {
            System.out.println("No InOrderSuccessor found");
        } else {
            System.out.println("Succesor is: " + inOrderSuccesor.getData());
        }
    }

    private static TreeNodeParent findInOrderSuccesor(TreeNodeParent treeNodeParent) {
        if (treeNodeParent.getRight() != null) {
            return findMostLeft(treeNodeParent.getRight());
        }

        return findParentInOrderSuccessor(treeNodeParent);
    }

    private static TreeNodeParent findParentInOrderSuccessor(TreeNodeParent treeNodeParent) {
        if (treeNodeParent.getParent() == null) {
            return null;
        }

        if (treeNodeParent.getData() < treeNodeParent.getParent().getData()) {
            return treeNodeParent.getParent();
        }

        return findParentInOrderSuccessor(treeNodeParent.getParent());
    }

    private static TreeNodeParent findMostLeft(TreeNodeParent treeNodeParent) {
        if (treeNodeParent.getLeft() == null) {
            return treeNodeParent;
        }

        return findMostLeft(treeNodeParent.getLeft());
    }


}
