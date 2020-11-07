package DataStructures;

public class TreeNodeParent extends TreeNodeG {
    private final int _data;
    private TreeNodeParent _parent;
    private TreeNodeParent _left;
    private TreeNodeParent _right;

    public TreeNodeParent getRight() {
        return _right;
    }

    public TreeNodeParent getLeft() {
        return _left;
    }

    public void setLeft(TreeNodeParent left) {
        _left = left;
    }

    public void setRight(TreeNodeParent right) {
        _right = right;
    }

    public TreeNodeParent(int data) {
        super(data);
        _data = data;
    }

    public void add(int data) {
        if (data <= getData()) {
            if (_left == null) {
                _left = new TreeNodeParent(data);
                _left.setParent(this);
            } else {
                _left.add(data);
            }
            return;
        } else if (_right == null) {
            _right = new TreeNodeParent(data);
            _right.setParent(this);
            return;
        }

        _right.add(data);
    }

    private void setParent(TreeNodeParent treeNodeG) {
        _parent = treeNodeG;
    }

    public TreeNodeParent getParent() {
        return _parent;
    }

    public int getData() {
        return _data;
    }
}
