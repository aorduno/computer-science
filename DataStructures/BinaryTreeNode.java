package DataStructures;

// this is binary tree -- not binary search tree
public class BinaryTreeNode {
    private int _data;
    public BinaryTreeNode _left;
    public BinaryTreeNode _right;

    public BinaryTreeNode(int data) {
        _data = data;
    }

    public int get_data() {
        return _data;
    }

    public void set_data(int _data) {
        this._data = _data;
    }

    public BinaryTreeNode get_left() {
        return _left;
    }

    public void set_left(BinaryTreeNode _left) {
        this._left = _left;
    }

    public BinaryTreeNode get_right() {
        return _right;
    }

    public void set_right(BinaryTreeNode _right) {
        this._right = _right;
    }
}
