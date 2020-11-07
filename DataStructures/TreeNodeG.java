package DataStructures;

import CTCI.TreesAndGraphs.TreeUtils;

public class TreeNodeG {
    final int _data;
    public TreeNodeG _left;
    public TreeNodeG _right;

    public void setLeft(TreeNodeG treeNodeG) {
        _left = treeNodeG;
    }

    public void setRight(TreeNodeG treeNodeG) {
        _right = treeNodeG;
    }

    public TreeNodeG getLeft() {
        return _left;
    }

    public TreeNodeG getRight() {
        return _right;
    }

    public int getData() {
        return _data;
    }


    public TreeNodeG(int data) {
        _data = data;
    }

    public void add(int data) {
        if (data <= _data) {
            if (_left == null) {
                _left = new TreeNodeG(data);
            } else {
                _left.add(data);
            }
            return;
        } else if (_right == null) {
            _right = new TreeNodeG(data);
            return;
        }

        _right.add(data);
    }

    public TreeNodeG find(int data) {
        if (data == getData()) {
            return this;
        }

        if (data <= getData()) {
            if (getLeft() == null) {
                return null;
            }

            return getLeft().find(data);
        }

        if (getRight() == null) {
            return null;
        }

        return getRight().find(data);
    }

    public int getHeight() {
        return getHeightInternal(this);
    }

    private int getHeightInternal(TreeNodeG nodeG) {
        if (nodeG == null) {
            return 0;
        }

        int leftHeight = getHeightInternal(nodeG.getLeft()) + 1;
        int rightHeight = getHeightInternal(nodeG.getRight()) + 1;
        return Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        TreeNodeG treeNodeG = new TreeNodeG(10);
        treeNodeG.add(5);
        treeNodeG.add(3);
        treeNodeG.add(8);
        treeNodeG.add(1);
        treeNodeG.add(7);
        treeNodeG.add(9);
        treeNodeG.add(40);
        treeNodeG.add(30);
        treeNodeG.add(60);

        TreeUtils.printInOrder(treeNodeG);
        TreeUtils.printPreOrder(treeNodeG);
        TreeUtils.printPostOrder(treeNodeG);
    }
}
