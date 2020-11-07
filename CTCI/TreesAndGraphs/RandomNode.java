package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;
import DataStructures.TreeRandomNode;

import java.util.List;

public class RandomNode {
    public static void main(String[] args) {
        TreeRandomNode root = new TreeRandomNode(8);
        TreeUtils.populateTreeNodeWithChilds(new int[]{4, 2, 1, 3, 6, 5, 7, 12, 10, 11}, root);

        testCase(root, 15, false);
        testCase(root, 15, true);
    }

    private static void testCase(TreeRandomNode root, int iterations, boolean optimize) {
        System.out.println("[[FindingRandomNodex" + (optimize ? "Optimized" : "") + "]] Finding randomNode for given tree " + iterations + " number of times");
        System.out.println("Tree is");
        List<List<TreeNodeG>> listOfDepthsT1 = ListOfDepths.getListOfDepths(root);
        ListOfDepths.print(listOfDepthsT1);
        int idx = 0;
        while (idx < iterations) {
            TreeRandomNode treeRandomNode = optimize ? root.getRandomNodeOptimized() : root.getRandomNode();
            System.out.println("Iteration #" + idx + ": Random node found => " + treeRandomNode + " || data in node is: " + treeRandomNode.getData());
            idx++;
        }
    }
}
