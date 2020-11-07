package DataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeRandomNode extends TreeNodeParent {
    private int _size;

    public TreeRandomNode(int data) {
        super(data);
        _size++;
    }

    @Override
    public void add(int data) {
        if (data <= getData()) {
            if (getLeft() == null) {
                setLeft(new TreeRandomNode(data));
            } else {
                getLeft().add(data);
            }
        } else {
            if (getRight() == null) {
                setRight(new TreeRandomNode(data));
            } else {
                getRight().add(data);
            }
        }

        _size++;
    }

    public int remove(int data) {
        // @TODO:to be defined int removed = super.remove(data)
        _size--;
        return 0;
    }

    @Override
    public TreeNodeG find(int data) {
        return super.find(data);
    }

    public int getSize() {
        return _size;
    }

    // this gets a random node using Random as auxiliar
    // to get the random node just create an in order traversal list and pick a random idx from it
    // we can do better than that...
    // time complexity o(n)
    // space complexity o(n)
    public TreeRandomNode getRandomNode() {
        List<TreeRandomNode> inOrderList = getInOrderList();
        Random random = new Random();
        int idx = random.nextInt(getSize());
        return inOrderList.get(idx);
    }

    private List<TreeRandomNode> getInOrderList() {
        List<TreeRandomNode> list = new ArrayList<>();
        buildRecursively(this, list);
        return list;
    }

    private void buildRecursively(TreeRandomNode treeRandomNode, List<TreeRandomNode> list) {
        if (treeRandomNode == null) {
            return;
        }

        buildRecursively((TreeRandomNode) treeRandomNode.getLeft(), list);
        list.add(treeRandomNode);
        buildRecursively((TreeRandomNode) treeRandomNode.getRight(), list);
    }

    // this is a bit better since it's all around maintaining the size on each node
    // then we have an easy way to find our target
    // time complexity o(h) where h = depth of tree, i.e: balanced tree = log n vs non-balanced  = n
    // space complexity o(h) since recursion this goes o(h) i.e: balanced tree = log n vs non-balanced  = n
    public TreeRandomNode getRandomNodeOptimized() {
        Random random = new Random();
        int idx = random.nextInt(getSize());
        return getNthNode(idx);
    }

    TreeRandomNode getNthNode(int idx) {
        TreeRandomNode left = (TreeRandomNode) getLeft();
        int leftSize = left == null ? 0 : left.getSize();
        if (idx < leftSize) { // our target is on the left side...
            return left.getNthNode(idx);
        } else if (idx == leftSize) { // current position is our target
            return this;
        } else { // target is on the right, so subtract the left + current node portion and go right
            TreeRandomNode right = (TreeRandomNode) getRight();
            return right.getNthNode(idx - (leftSize + 1));
        }
    }
}
