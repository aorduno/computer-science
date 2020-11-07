package CTCI.LinkedList;

import DataStructures.LinkedList;
import DataStructures.Node;

public class ReturnKthToLast {
    static class KthToLastData {
        private int _position;
        private int _data;
        private boolean _outOfBounds;
        private Node _node;

        public int getPosition() {
            return _position;
        }

        public void set_position(int _position) {
            this._position = _position;
        }

        public int getData() {
            return _data;
        }

        public void set_data(int _data) {
            this._data = _data;
        }

        public boolean is_outOfBounds() {
            return _outOfBounds;
        }

        public void set_outOfBounds(boolean _outOfBounds) {
            this._outOfBounds = _outOfBounds;
        }

        public Node get_node() {
            return _node;
        }

        public void set_node(Node _node) {
            this._node = _node;
        }
    }

    static class IndexData {
        private int _val;

        public int get_val() {
            return _val;
        }

        public void set_val(int _val) {
            this._val = _val;
        }
    }

    public static void main(String[] args) {
//        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
//        testCaseOnePass(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        testCaseRecursive(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    private static void testCase(int[] numbers) {
        LinkedList list = LinkedListUtils.createList(numbers);
        int numbersLength = numbers.length;
        for (int x = 0; x < numbersLength; x++) {
            KthToLastData kthToLastData = getKthToLast(list, x);
            printData(kthToLastData, x, "size");
        }

        KthToLastData kthToLastData = getKthToLast(list, numbersLength + 1);
        printData(kthToLastData, numbersLength + 1, "size");

        kthToLastData = getKthToLast(list, numbersLength + 1000);
        printData(kthToLastData, numbersLength + 1000, "size");
    }

    private static void testCaseOnePass(int[] numbers) {
        LinkedList list = LinkedListUtils.createList(numbers);
        int numbersLength = numbers.length;
        for (int x = 0; x < numbersLength; x++) {
            KthToLastData kthToLastData = getKthToLastOnePass(list, x);
            printData(kthToLastData, x, "iterative");
        }

        KthToLastData kthToLastData = getKthToLast(list, numbersLength + 1);
        printData(kthToLastData, numbersLength + 1, "iterative");

        kthToLastData = getKthToLast(list, numbersLength + 1000);
        printData(kthToLastData, numbersLength + 1000, "iterative");
    }

    private static void testCaseRecursive(int[] numbers) {
        LinkedList list = LinkedListUtils.createList(numbers);
        int numbersLength = numbers.length;
        for (int x = 0; x < numbersLength; x++) {
            KthToLastData kthToLastData = getKthToLastRecursive(list, x);
            printData(kthToLastData, x, "recursive");
        }

        KthToLastData kthToLastData = getKthToLast(list, numbersLength + 1);
        printData(kthToLastData, numbersLength + 1, "recursive");

        kthToLastData = getKthToLast(list, numbersLength + 1000);
        printData(kthToLastData, numbersLength + 1000, "recursive");
    }

    private static void printData(KthToLastData kthToLastData, int k, String type) {
        if (kthToLastData.is_outOfBounds()) {
            System.out.println("[[" + type + "]] The " + k + "th element to last element in the list is= outOfBounds");
        } else {
            if (kthToLastData.get_node() != null) {
                System.out.println("[[" + type + "]] The " + k + "th element to last element in the list is= node: " + kthToLastData.get_node().getData());
            } else {
                System.out.println("[[" + type + "]] The " + k + "th element to last element in the list is= position: " + kthToLastData.getPosition() + " || data: " + kthToLastData.getData());
            }
        }
    }

    private static KthToLastData getKthToLast(LinkedList list, int k) {
        Node current = list.getHead();
        int size = 0;
        while (current != null) {
            size++;
            current = current._next;
        }

        int kthPosition = size - k;
        if (kthPosition < 0) {
            KthToLastData kthToLastData = new KthToLastData();
            kthToLastData.set_outOfBounds(true);
            return kthToLastData;
        }

        current = list.getHead();
        int position = 0;
        while (current != null) {
            position++;
            if (position == kthPosition) {
                break;
            }
            current = current._next;
        }

        KthToLastData kthToLastData = new KthToLastData();
        kthToLastData.set_data(current.getData());
        kthToLastData.set_position(position);
        return kthToLastData;
    }

    private static KthToLastData getKthToLastOnePass(LinkedList linkedList, int k) {
        Node p1 = linkedList.getHead();
        KthToLastData kthToLastData = new KthToLastData();
        while (k >= 0) {
            if (p1 == null) {
                kthToLastData.set_outOfBounds(true);
                return kthToLastData;
            }
            p1 = p1._next;
            k--;
        }

        Node p2 = linkedList.getHead();
        while (p1 != null) {
            p1 = p1._next;
            p2 = p2._next;
        }

        kthToLastData.set_node(p2);
        return kthToLastData;
    }

    private static KthToLastData getKthToLastRecursive(LinkedList linkedList, int k) {
        IndexData indexData = new IndexData();
        Node node = doFind(linkedList.getHead(), k, indexData);
        KthToLastData kthToLastData = new KthToLastData();
        kthToLastData.set_node(node);
        return kthToLastData;
    }

    private static Node doFind(Node node, int k, IndexData indexData) {
        if (node == null) {
            indexData.set_val(0);
            return null;
        }

        Node current = doFind(node._next, k, indexData);
        indexData.set_val(indexData.get_val() + 1);
        if (indexData.get_val() == (k + 1)) {
            return node;
        }
        return current;
    }
}
