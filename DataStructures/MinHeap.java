package DataStructures;

import CTCI.LogUtils;

public class MinHeap {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(15);
        minHeap.insert(9999);
        minHeap.insert(4444);
        minHeap.insert(2222);
        minHeap.insert(1111);
        minHeap.insert(666);
        minHeap.insert(333);
        minHeap.insert(111);
        minHeap.insert(50);
        minHeap.insert(10);
        minHeap.insert(999);
        minHeap.insert(3333);

        LogUtils.logMessage("Min element in heap is: " + minHeap.peek(), true);

        while (minHeap.size() > 0) {
            LogUtils.logMessage("Popping min element: " + minHeap.pop(), true);
            LogUtils.logMessage("Now min element is :" + minHeap.peek(), true);
        }

    }

    private int[] _elements; // using list instead of array so it's flexible
    private int _size;

    private MinHeap(int size) {
        _elements = new int[size];
    }

    private void insert(int number) {
        expandIfNeeded();
        _elements[_size] = number;
        heapifyUp();
        _size++;
    }

    private int peek() {
        return _elements[0];
    }

    private int size() {
        return _size;
    }

    private int pop() {
        int min = _elements[0];
        _size--;
        _elements[0] = _elements[_size];
        _elements[_size] = 0;

        heapifyDown();
        return min;
    }

    private void heapifyDown() {
        int currentIndex = 0;
        while (getLeftChildIndex(currentIndex) != -1) {
            int leftChildIndex = getLeftChildIndex(currentIndex);
            int rightChildIndex = getRightChildIndex(currentIndex);
            int lowestIndex = rightChildIndex != -1 && (_elements[rightChildIndex] < _elements[leftChildIndex]) ? rightChildIndex : leftChildIndex;
            if (_elements[currentIndex] < _elements[lowestIndex]) {
                break;
            }

            swap(currentIndex, lowestIndex);
            currentIndex = lowestIndex;
        }
    }

    private int getLeftChildIndex(int currentIndex) {
        int index = (currentIndex * 2) + 1;
        return index <= (_size - 1) ? index : -1;
    }

    private int getRightChildIndex(int currentIndex) {
        int index = (currentIndex * 2) + 2;
        return index <= (_size - 1) ? index : -1;
    }

    private void expandIfNeeded() {
        if (_elements.length == _size) {
            int[] newHeap = new int[_size * 2];
            System.arraycopy(_elements, 0, newHeap, 0, _size);
            _elements = newHeap;
        }
    }

    private void heapifyUp() {
        int lastIndex = _size;
        while (getParentIndex(lastIndex) != -1) {
            int parentIndex = getParentIndex(lastIndex);
            if (_elements[lastIndex] < _elements[parentIndex]) {
                swap(lastIndex, parentIndex);
            }
            lastIndex = parentIndex;
        }
    }

    private void swap(int a, int b) {
        int temp = _elements[a];
        _elements[a] = _elements[b];
        _elements[b] = temp;
    }

    private int getParentIndex(int index) {
        if (index == 0) {
            return -1;
        }

        return (index - 1) / 2;
    }
}
