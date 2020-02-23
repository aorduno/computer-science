package DataStructures;

import java.util.Arrays;

public class MinHeapNode {
    private int _capacity = 10;
    private int _size = 0;

    private int[] _items = new int[_capacity];

    int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < _size;
    }

    boolean hasRightChild(int index) {
        return getRightChildIndex(index) < _size;
    }

    int parent(int index) {
        return _items[getParentIndex(index)];
    }

    int leftChild(int index) {
        return _items[getLeftChildIndex(index)];
    }

    int rightChild(int index) {
        return _items[getRightChildIndex(index)];
    }

    void swap(int index1, int index2) {
        int temp = _items[index1];
        _items[index1] = _items[index2];
        _items[index2] = temp;
    }

    void ensureExtraCapacity() {
        if (_capacity == _size) {
            int newCapacity = _capacity * 2;
            _items = Arrays.copyOf(_items, newCapacity);
            _capacity = newCapacity;
        }
    }

    int peek() {
        return _items[0];
    }

    int poll() {
        int top = _items[0];
        _size--;
        _items[0] = _items[_size];
        heapifyDown();
        return top;
    }

    boolean isEmpty() {
        return _size == 0;
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (_items[index] < _items[smallerChildIndex]) {
                break;
            }

            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    void insert(int data) {
        ensureExtraCapacity();
        _items[_size] = data;
        _size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = _size - 1;
        while (hasParent(index) && parent(index) > _items[index]) {
            int parentIndex = getParentIndex(index);
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    public static void main(String[] args) {
        MinHeapNode minHeapNode = new MinHeapNode();
        minHeapNode.insert(10);

        System.out.println("Peek " + minHeapNode.peek());

        minHeapNode.insert(20);
        System.out.println("Peek " + minHeapNode.peek());

        minHeapNode.insert(5);
        System.out.println("Peek " + minHeapNode.peek());

        minHeapNode.insert(100);
        System.out.println("Peek " + minHeapNode.peek());

        minHeapNode.insert(1);
        System.out.println("Peek " + minHeapNode.peek());

        while (!minHeapNode.isEmpty()) {
            System.out.println("Poll " + minHeapNode.poll());
            System.out.println("Peek " + minHeapNode.peek());
        }
    }
}
