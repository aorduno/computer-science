package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import LeetCode.datastructures.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuilTreeInOrderPostOrder {

    public static void main(String[] args) {
        testCase(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }

    private static void testCase(int[] inOrder, int[] postOrder) {
        LogUtils.logMessage("[[BuildTreeInOrderPostOrder]] Building up tree from given inOrder and postOrder traversal");
        LogUtils.logMessage("inOrder:");
        ArrayUtils.print(inOrder);

        LogUtils.logMessage("postOrder:");
        ArrayUtils.print(postOrder);

        TreeNode tree = buildTree(inOrder, postOrder);
        List<TreeNode> preOrderTraversalWithNulls = tree.getInOrderTraversal();
        TreeNode.print(preOrderTraversalWithNulls);
    }

    private static TreeNode buildTree(int[] inOrder, int[] postOrder) {
        Map<Integer, Integer> inOrderMap = createMap(inOrder);
        int right = postOrder.length - 1;
        return buildTreeRecursively(postOrder, new int[]{right}, inOrderMap, 0, right);
    }

    private static TreeNode buildTreeRecursively(int[] preOrder, int[] currentPosition, Map<Integer, Integer> inOrderMap, int left, int right) {
        if (left > right) {
            return null;
        }

        TreeNode node = new TreeNode(preOrder[currentPosition[0]]);
        currentPosition[0]--; // update for next call

        int middle = inOrderMap.get(node.val);
        node.right = buildTreeRecursively(preOrder, currentPosition, inOrderMap, middle + 1, right);
        node.left = buildTreeRecursively(preOrder, currentPosition, inOrderMap, left, middle - 1);

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
