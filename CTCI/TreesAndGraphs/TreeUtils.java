package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;
import DataStructures.TreeNodeParent;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {
    public static void printInOrder(TreeNodeG nodeG) {
        System.out.println("[[InOrder]]Printing tree...");
        printInOrderHelper(nodeG);
        System.out.println();
    }

    static void printInOrderHelper(TreeNodeG nodeG) {
        if (nodeG.getLeft() != null) {
            printInOrderHelper(nodeG.getLeft());
        }

        System.out.print(nodeG.getData() + " ");

        if (nodeG.getRight() != null) {
            printInOrderHelper(nodeG.getRight());
        }
    }

    public static void printPreOrder(TreeNodeG nodeG) {
        System.out.println("[[PreOrder]]Printing tree...");
        printPreOrderHelper(nodeG);
        System.out.println();
    }

    static void printPreOrderHelper(TreeNodeG nodeG) {
        System.out.print(nodeG.getData() + " ");
        if (nodeG.getLeft() != null) {
            printPreOrderHelper(nodeG.getLeft());
        }
        if (nodeG.getRight() != null) {
            printPreOrderHelper(nodeG.getRight());
        }
    }

    public static void printPostOrder(TreeNodeG nodeG) {
        System.out.println("[[PostOrder]]Printing tree...");
        printPostOrderHelper(nodeG);
        System.out.println();
    }

    static void printPostOrderHelper(TreeNodeG nodeG) {
        if (nodeG.getLeft() != null) {
            printPostOrderHelper(nodeG.getLeft());
        }
        if (nodeG.getRight() != null) {
            printPostOrderHelper(nodeG.getRight());
        }
        System.out.print(nodeG.getData() + " ");
    }

    static List<TreeNodeG> getInOrderList(TreeNodeG treeNodeG) {
        List<TreeNodeG> treeNodeGList = new ArrayList<>();
        populateListRecursively(treeNodeG, treeNodeGList);
        return treeNodeGList;
    }

    private static void populateListRecursively(TreeNodeG treeNodeG, List<TreeNodeG> treeNodeGList) {
        if (treeNodeG.getLeft() != null) {
            populateListRecursively(treeNodeG.getLeft(), treeNodeGList);
        }

        treeNodeGList.add(treeNodeG);
        if (treeNodeG.getRight() != null) {
            populateListRecursively(treeNodeG.getRight(), treeNodeGList);
        }
    }

    static void populateTreeNodeWithChilds(int[] childs, TreeNodeParent root) {
        for (int element : childs) {
            root.add(element);
        }
    }

    static boolean contains(TreeNodeParent root, TreeNodeParent toFind) {
        if (root == null) {
            return false;
        }

        if (root == toFind) {
            return true;
        }

        return contains(root.getLeft(), toFind) || contains(root.getRight(), toFind);
    }

    public static TreeNodeParent find(TreeNodeParent root, TreeNodeParent toFind) {
        if (root == null) {
            return null;
        }

        if (root.getData() == toFind.getData()) {
            return root;
        }

        TreeNodeParent foundLeft = find(root.getLeft(), toFind);
        if (foundLeft != null) {
            return foundLeft;
        }
        TreeNodeParent foundRight = find(root.getRight(), toFind);
        if (foundRight != null) {
            return foundRight;
        }

        return null;
    }
}
