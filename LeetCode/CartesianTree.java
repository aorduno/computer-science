package LeetCode;

import DataStructures.NodeBinaryTree;
import Helpers.ArrayList;

import java.util.Arrays;
import java.util.Set;

public class CartesianTree {
    static NodeBinaryTree doBuild(int[] sequence) {
        if (sequence.length == 0) {
            return null;
        }

        if (sequence.length == 1) {
            return new NodeBinaryTree(sequence[0]);
        }

        int lowest = sequence[0];
        int lowestIndex = 0;
        for (int x = 1; x < sequence.length; x++) {
            int current = sequence[x];
            if (current < lowest) {
                lowest = current;
                lowestIndex = x;
            }
        }

        NodeBinaryTree root = new NodeBinaryTree(lowest);
        root.setLeft(doBuild(Arrays.copyOfRange(sequence, 0, lowestIndex)));
        root.setRight(doBuild(Arrays.copyOfRange(sequence, lowestIndex + 1, sequence.length)));
        return root;
    }

    public static void main(String[] args) {
        int[] sequence = new int[]{3, 2, 6, 1, 9};
        NodeBinaryTree tree = doBuild(sequence);

        Set<Integer> inOrder = tree.getInOrder();
        ArrayList.print(inOrder);
    }
}
