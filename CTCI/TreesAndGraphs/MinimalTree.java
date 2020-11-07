package CTCI.TreesAndGraphs;

import CTCI.ArraysAndStrings.ArrayUtils;
import DataStructures.LinkedListG;
import DataStructures.NodeG;
import DataStructures.TreeNodeG;

import java.util.List;

public class MinimalTree {
    public static void main(String[] arg) {
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 4, false);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, 5, false);

        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 4, true);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, 5, true);
    }

    private static void testCase(int[] elements, int height, boolean optimize) {
        System.out.println("[[MinimalTree" + (optimize ? "Optimized" : "") + "]]The minimal tree representation for the given elements");
        ArrayUtils.print(elements);
        TreeNodeG minimalTree = optimize ? createMinimalTreeOptimized(elements) : createMinimalTree(elements);
        TreeUtils.printPreOrder(minimalTree); // pre order
        System.out.println("Expecting a height of: " + height + " having: " + minimalTree.getHeight());
        System.out.println("Here's another view of the minimalHeightTree created");
        List<List<TreeNodeG>> listOfDepths = ListOfDepths.getListOfDepths(minimalTree);
        ListOfDepths.print(listOfDepths);

    }

    // time complexity o(n), need to visit each node
    // space complexity o(h), where h = height of the tree.
    // NOTE: if this is balanced tree we could also say space is o(log n)
    static TreeNodeG createMinimalTreeOptimized(int[] elements) {
        return createMinimalRecursively(elements, 0, elements.length - 1);
    }

    private static TreeNodeG createMinimalRecursively(int[] elements, int left, int right) {
        if (right < left) {
            return null;
        }

        int middle = left + ((right - left) / 2);
        TreeNodeG treeNodeG = new TreeNodeG(elements[middle]);
        treeNodeG.setLeft(createMinimalRecursively(elements, left, middle - 1));
        treeNodeG.setRight(createMinimalRecursively(elements, middle + 1, right));
        return treeNodeG;
    }

    // input: given a sorted (ASC order) array with unique integer elements
    // output: creates a binary search tree with minimal height
    // time complexity o(n log n) visit each node + insert
    // space complexity o(n) since a linkedList is created as helper
    static TreeNodeG createMinimalTree(int[] elements) {
        LinkedListG<Integer> minimalHeightList = getMinimalHeightList(elements);
        NodeG head = minimalHeightList.getHead();
        TreeNodeG minimalHeightTree = null;
        while (head != null) {
            int data = (int) head.getData();
            if (minimalHeightTree == null) {
                minimalHeightTree = new TreeNodeG(data);
            } else {
                minimalHeightTree.add(data);
            }
            head = head._next;
        }

        return minimalHeightTree;
    }

    private static LinkedListG<Integer> getMinimalHeightList(int[] elements) {
        LinkedListG<Integer> minimalHeightList = new LinkedListG<>();
        getMinimalRecursively(elements, 0, elements.length - 1, minimalHeightList);
        return minimalHeightList;
    }

    private static void getMinimalRecursively(int[] elements, int left, int right, LinkedListG<Integer> minimalHeightList) {
        if (right < left) {
            return;
        }
        int middle = left + ((right - left) / 2);
        minimalHeightList.append(elements[middle]);
        getMinimalRecursively(elements, left, middle - 1, minimalHeightList);
        getMinimalRecursively(elements, middle + 1, right, minimalHeightList);
    }
}
