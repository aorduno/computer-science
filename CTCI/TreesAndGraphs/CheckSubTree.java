package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;
import DataStructures.TreeNodeParent;

import java.util.List;

public class CheckSubTree {
    public static void main(String[] args) {
        TreeNodeParent t1 = new TreeNodeParent(8);
        TreeUtils.populateTreeNodeWithChilds(new int[]{4, 2, 1, 3, 6, 5, 7, 12, 10, 11}, t1);

        TreeNodeParent t2 = t1.getLeft();
        testCase(t1, t2, false);
        testCase(t1, t2, true);

        TreeNodeParent t3 = new TreeNodeParent(4);
        TreeUtils.populateTreeNodeWithChilds(new int[]{2, 1, 3, 6, 5, 7}, t3);
        testCase(t1, t3, false);
        testCase(t1, t3, true);

        // same numbers... different structure
        TreeNodeParent t4 = new TreeNodeParent(4);
        TreeUtils.populateTreeNodeWithChilds(new int[]{3, 1, 2, 7, 6, 5}, t4);
        testCase(t1, t4, false);
        testCase(t1, t4, true);
    }

    private static void testCase(TreeNodeParent t1, TreeNodeParent t2, boolean doRecursively) {
        System.out.println("[[CheckSubTree" + (doRecursively ? "Recursive" : "") + "]]Verifying if t1 is subtree of t2");
        System.out.println("t1 is:");
        List<List<TreeNodeG>> listOfDepthsT1 = ListOfDepths.getListOfDepths(t1);
        ListOfDepths.print(listOfDepthsT1);
        System.out.println("t2 is:");
        List<List<TreeNodeG>> listOfDepthsT2 = ListOfDepths.getListOfDepths(t2);
        ListOfDepths.print(listOfDepthsT2);

        boolean isSubtree = doRecursively ? isSubtreeRecursive(t1, t2) : isSubtree(t1, t2);
        if (isSubtree) {
            System.out.println("Yes! t1 is subtree of t2");
        } else {
            System.out.println("No! t1 is NOT subtree of t2");
        }
    }

    private static boolean isSubtreeRecursive(TreeNodeParent t1, TreeNodeParent t2) {
        if (t2 == null) {
            return true;
        }
        return subTreeRecursively(t1, t2);
    }

    private static boolean subTreeRecursively(TreeNodeParent t1, TreeNodeParent t2) {
        if (t1 == null) {
            return false;
        } else if (t1.getData() == t2.getData() && match(t1, t2)) {
            return true;
        }

        return subTreeRecursively(t1.getLeft(), t2) || subTreeRecursively(t1.getRight(), t2);
    }

    // check if subtree matches
    private static boolean match(TreeNodeParent t1, TreeNodeParent t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) { // different structure
            return false;
        } else if (t1.getData() != t2.getData()) {
            return false;
        }

        return match(t1.getLeft(), t2.getLeft()) && match(t1.getRight(), t2.getRight());
    }

    private static boolean isSubtree(TreeNodeParent t1, TreeNodeParent t2) {
        // find the given node first
        TreeNodeParent t1SubTree = TreeUtils.find(t1, t2);
        if (t1SubTree == null) {
            return false;
        }
        StringBuilder t1Sb = new StringBuilder();
        StringBuilder t2Sb = new StringBuilder();
        // could also use char array instead and we could compare arr._length first but meh...
        buildPreOrderStringWithBlanks(t1SubTree, t1Sb);
        buildPreOrderStringWithBlanks(t2, t2Sb);

        String t1String = t1Sb.toString();
        String t2String = t2Sb.toString();
        return t1String.equals(t2String);
    }

    private static void buildPreOrderStringWithBlanks(TreeNodeParent nodeParent, StringBuilder stringBuilder) {
        if (nodeParent == null) {
            stringBuilder.append("<blank>").append("|");
            return;
        }

        stringBuilder.append(nodeParent.getData()).append("|");
        buildPreOrderStringWithBlanks(nodeParent.getLeft(), stringBuilder);
        buildPreOrderStringWithBlanks(nodeParent.getRight(), stringBuilder);
    }
}
