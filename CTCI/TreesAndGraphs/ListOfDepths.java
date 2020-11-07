package CTCI.TreesAndGraphs;

import CTCI.ArraysAndStrings.ArrayUtils;
import DataStructures.TreeNodeG;

import java.util.ArrayList;
import java.util.List;

public class ListOfDepths {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 4);
        testCase(new int[]{1, 10, 20, 50, 100, 150, 200, 230, 999, 100000, 2500000, 30000000, 40000000, 50000000, 60000000, 70000000}, 5);
    }

    private static void testCase(int[] elements, int expectedHeight) {
        System.out.println("[[ListOfDepths]]Getting list of depth for elements...");
        ArrayUtils.print(elements);
        System.out.println("Expected lists to be: " + expectedHeight);
        TreeNodeG minimalTree = MinimalTree.createMinimalTree(elements);
        System.out.println("Tree's height is: " + minimalTree.getHeight());
        List<List<TreeNodeG>> listOfDepths = getListOfDepths(minimalTree);

        print(listOfDepths);
    }

    static void print(List<List<TreeNodeG>> listOfDepths) {
        int index = 0;
        int size = listOfDepths.size();
        System.out.println("Printing List Of Depths: ");
        for (List<TreeNodeG> listOfDepth : listOfDepths) {
            int tabs = index;
            StringBuilder tabBuilder = new StringBuilder();
            while (tabs < size) {
                tabBuilder.append("\t");
                tabs++;
            }

            System.out.print("depth " + index + " : " + tabBuilder.toString());
            for (TreeNodeG treeNodeG : listOfDepth) {
                System.out.print(treeNodeG.getData() + "\t");
            }
            System.out.println();
            index++;
        }
    }

    // input: gets a binary search tree as input
    // last of depths containing all nodes, i.e: if the tree is 4 levels depth then it will return 4 lists.
    // each list have the levels corresponding to that depth.
    // time complexity o(n)
    // space complexity o(h), where h = height of the tree.
    // NOTE: if this is balanced tree we could also say space is o(log n)
    static List<List<TreeNodeG>> getListOfDepths(TreeNodeG treeNodeG) {
        List<List<TreeNodeG>> listOfDepths = new ArrayList<>();
        buildListsRecursively(treeNodeG, 0, listOfDepths);
        return listOfDepths;
    }

    private static void buildListsRecursively(TreeNodeG treeNodeG, int depth, List<List<TreeNodeG>> listOfDepths) {
        if (treeNodeG == null) {
            return;
        }

        if ((listOfDepths.size() - 1) < depth) {
            List<TreeNodeG> treeNodeGList = new ArrayList<>();
            listOfDepths.add(treeNodeGList);
        }

        listOfDepths.get(depth).add(treeNodeG);
        buildListsRecursively(treeNodeG.getLeft(), depth + 1, listOfDepths);
        buildListsRecursively(treeNodeG.getRight(), depth + 1, listOfDepths);
    }
}
