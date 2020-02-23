package DataStructures;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class NodeBinaryTree {
    int _data;
    private NodeBinaryTree _left;
    private NodeBinaryTree _right;

    public NodeBinaryTree getLeft() {
        return _left;
    }

    public NodeBinaryTree getRight() {
        return _right;
    }

    public void setLeft(NodeBinaryTree nodeBinaryTree) {
        _left = nodeBinaryTree;
    }

    public void setRight(NodeBinaryTree nodeBinaryTree) {
        _right = nodeBinaryTree;
    }

    Map<NodeBinaryTree, Integer> nodeHeightMap = new HashMap<>();

    public NodeBinaryTree(int data) {
        _data = data;
    }

    public void insert(int data) {
        if (data <= _data) {
            if (_left == null) {
                _left = new NodeBinaryTree(data);
            } else {
                _left.insert(data);
            }

            return;
        }

        if (_right == null) {
            _right = new NodeBinaryTree(data);
            return;
        }

        _right.insert(data);
    }

    boolean contains(int data) {
        if (_data == data) {
            return true;
        }

        if (data < _data) {
            if (_left == null) {
                return false;
            }

            return _left.contains(data);
        }

        if (_right == null) {
            return false;
        }

        return _right.contains(data);
    }


    public Set<Integer> getInOrder() {
        Set<Integer> values = new LinkedHashSet<>();
        if (_left != null) {
            values.addAll(_left.getInOrder());
        }

        values.add(_data);

        if (_right != null) {
            values.addAll(_right.getInOrder());
        }

        return values;
    }

    Set<Integer> getPreOrder() {
        Set<Integer> values = new LinkedHashSet<>();
        values.add(_data);

        if (_left != null) {
            values.addAll(_left.getInOrder());
        }

        if (_right != null) {
            values.addAll(_right.getInOrder());
        }

        return values;
    }

    Set<Integer> getPostOrder() {
        Set<Integer> values = new LinkedHashSet<>();
        if (_left != null) {
            values.addAll(_left.getInOrder());
        }

        if (_right != null) {
            values.addAll(_right.getInOrder());
        }

        values.add(_data);
        return values;
    }

    int getNodeHeight() {
        return getHeight(this) - 1;
    }

    int getNodeHeight(NodeBinaryTree nodeBinaryTree) {
        int height = getHeight(nodeBinaryTree);
        return height == 0 ? height : height - 1;
    }

    int getHeight(NodeBinaryTree nodeBinaryTree) {
        if (nodeBinaryTree == null) {
            return 0;
        }

        if (nodeHeightMap.containsKey(nodeBinaryTree)) {
            return nodeHeightMap.get(nodeBinaryTree);
        }

        System.out.println("Getting height for " + nodeBinaryTree._data);

        NodeBinaryTree left = nodeBinaryTree._left;
        int leftHeight = getHeight(left);
        if (leftHeight > 0) {
            addHeight(left, leftHeight);
        }
        NodeBinaryTree right = nodeBinaryTree._right;
        int rightHeight = getHeight(right);
        if (rightHeight > 0) {
            addHeight(right, rightHeight);
        }

        int height = Math.max(leftHeight, rightHeight) + 1;
        addHeight(nodeBinaryTree, height);
        return height; // sum up every time this function gets call, since it represents one level down
    }

    private void addHeight(NodeBinaryTree nodeBinaryTree, int height) {
        int data = nodeBinaryTree._data;
        System.out.println("Adding Height for node: " + data + " || height: " + height);
        nodeHeightMap.put(nodeBinaryTree, height);
    }

    boolean isNodeBalanced(NodeBinaryTree nodeBinaryTree) {
        return isBalanced(nodeBinaryTree);
    }

    private boolean isBalanced(NodeBinaryTree nodeBinaryTree) {
        if (nodeBinaryTree == null) {
            return true;
        }

        NodeBinaryTree left = nodeBinaryTree._left;
        boolean isLeftBalanced = isBalanced(left);
        NodeBinaryTree right = nodeBinaryTree._right;
        boolean isRightBalanced = isBalanced(right);
        if (!isLeftBalanced || !isRightBalanced) {
            return false;
        }

        int leftHeight = getNodeHeight(left);
        int rightHeight = getNodeHeight(right);
        if (leftHeight > rightHeight) {
            return (leftHeight - rightHeight) <= 1;
        }

        return (leftHeight - rightHeight) <= 1;
    }

    public static void main(String[] args) {
        NodeBinaryTree tree4 = new NodeBinaryTree(10);
        tree4.insert(5);
        tree4.insert(3);
        tree4.insert(8);
        tree4.insert(9);
        tree4.insert(2);
        tree4.insert(1);

        tree4.insert(20);
        tree4.insert(15);

//        System.out.println("Tree height: " + tree4.getHeight(tree4));
//        System.out.println("Node Tree height: " + tree4.getNodeHeight(tree4));
        System.out.println("Node Tree isBalanced: " + tree4.isNodeBalanced(tree4));

        String abc = "";
    }

    public int getData() {
        return _data;
    }
}
