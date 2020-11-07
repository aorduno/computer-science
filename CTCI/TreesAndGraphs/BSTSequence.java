package CTCI.TreesAndGraphs;

import DataStructures.TreeNodeG;
import DataStructures.TreeNodeParent;

import java.util.ArrayList;
import java.util.List;

public class BSTSequence {
    public static void main(String[] args) {
        TreeNodeParent root = new TreeNodeParent(8);
        TreeUtils.populateTreeNodeWithChilds(new int[]{4, 6, 2, 3, 1, 5, 7}, root);
        testCase(root);
    }

    private static void testCase(TreeNodeParent root) {
        List<List<Integer>> sequences = getNodeSequences(root);
        System.out.println("[[BSTSequence]] Finding out possible element insertion sequences for given tree.");
        System.out.println("Tree is: ");
        List<List<TreeNodeG>> listOfDepths = ListOfDepths.getListOfDepths(root);
        ListOfDepths.print(listOfDepths);
        System.out.println("Found " + sequences.size() + " sequences");
        printSequences(sequences);
    }

    private static void printSequences(List<List<Integer>> sequences) {
        for (List<Integer> sequence : sequences) {
            System.out.println("Sequence: ");
            for (Integer integer : sequence) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    // 1) recurses through the tree
    // 2) "weaves" nodes sequences at each level
    private static List<List<Integer>> getNodeSequences(TreeNodeParent root) {
        List<List<Integer>> sequences = new ArrayList<>();
        if (root == null) {
            sequences.add(new ArrayList<>()); // add an empty one so it starts the process up
            return sequences;
        }

        List<Integer> prefix = new ArrayList<>();
        prefix.add(root.getData());

        List<List<Integer>> leftSequences = getNodeSequences(root.getLeft());
        List<List<Integer>> rightSequences = getNodeSequences(root.getRight());
        for (List<Integer> leftSequence : leftSequences) {
            for (List<Integer> rightSequence : rightSequences) {
                List<List<Integer>> weavedLists = new ArrayList<>();
                weaveLists(leftSequence, rightSequence, weavedLists, prefix);
                sequences.addAll(weavedLists);
            }
        }
        return sequences;
    }

    private static void weaveLists(List<Integer> leftSequence, List<Integer> rightSequence, List<List<Integer>> weavedLists, List<Integer> prefix) {
        if (leftSequence.size() == 0 || rightSequence.size() == 0) {
            List<Integer> clone = new ArrayList<>(prefix);
            clone.addAll(leftSequence);
            clone.addAll(rightSequence);
            weavedLists.add(clone);
            return;
        }

        Integer leftFirst = leftSequence.remove(0);
        prefix.add(leftFirst);
        weaveLists(leftSequence, rightSequence, weavedLists, prefix);
        prefix.remove(prefix.size() - 1);
        leftSequence.add(0, leftFirst);

        Integer rightFirst = rightSequence.remove(0);
        prefix.add(rightFirst);
        weaveLists(leftSequence, rightSequence, weavedLists, prefix);
        prefix.remove(prefix.size() - 1);
        rightSequence.add(0, rightFirst);
    }

}
