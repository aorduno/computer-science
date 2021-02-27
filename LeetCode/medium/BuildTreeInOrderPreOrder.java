package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTreeInOrderPreOrder {
    public static void main(String[] args) {
        testCase(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        testCase(new int[]{1, 2, 3}, new int[]{3, 2, 1});
    }

    private static void testCase(int[] preOrder, int[] inOrder) {
        LogUtils.logMessage("[[BuildTreeInOrderPreOrder]] Building up tree from given preOrder and inOrder traversal");
        LogUtils.logMessage("preOrder:");
        ArrayUtils.print(preOrder);

        LogUtils.logMessage("inOrder:");
        ArrayUtils.print(inOrder);

        TreeNode tree = buildTree(preOrder, inOrder);
        List<TreeNode> preOrderTraversalWithNulls = tree.getPreOrderTraversalWithNulls();
        TreeNode.print(preOrderTraversalWithNulls);
    }

    private static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        Map<Integer, Integer> inOrderMap = createMap(inOrder);
        return buildTreeRecursively(preOrder, new int[]{0}, inOrderMap, 0, inOrder.length - 1);
    }

    private static TreeNode buildTreeRecursively(int[] preOrder, int[] currentPosition, Map<Integer, Integer> inOrderMap, int left, int right) {
        if (left > right) {
            return null;
        }

        TreeNode node = new TreeNode(preOrder[currentPosition[0]]);
        currentPosition[0]++; // update for next call

        int middle = inOrderMap.get(node.val);
        node.left = buildTreeRecursively(preOrder, currentPosition, inOrderMap, left, middle - 1);
        node.right = buildTreeRecursively(preOrder, currentPosition, inOrderMap, middle + 1, right);

        return node;
    }

    private static Map<Integer, Integer> createMap(int[] traversal) {
        HashMap<Integer, Integer> traversalMap = new HashMap<>();
        for (int x = 0; x < traversal.length; x++) {
            traversalMap.put(traversal[x], x);
        }
        return traversalMap;
    }

}
