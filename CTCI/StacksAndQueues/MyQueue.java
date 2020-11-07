package CTCI.StacksAndQueues;

import DataStructures.StackG;

import java.util.EmptyStackException;

public class MyQueue<T> {
    private StackG<T> _stackA = new StackG<>();
    private StackG<T> _stackB = new StackG<>();

    void push(T data) {
        _stackA.push(data);
    }

    T remove() {
        populateStackB();
        checkStackBEmptiness();
        return _stackB.pop();
    }

    private void populateStackB() {
        if (_stackB.isEmpty() && !_stackA.isEmpty()) {
            while (!_stackA.isEmpty()) {
                T data = _stackA.pop();
                _stackB.push(data);
            }
        }
    }

    T peek() {
        populateStackB();
        checkStackBEmptiness();
        return _stackB.peek();
    }

    boolean isEmpty() {
        return _stackB.isEmpty();
    }

    private void checkStackBEmptiness() {
        if (_stackB.isEmpty()) {
            throw new EmptyStackException();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> integerMyQueue = new MyQueue<>();
        System.out.println("isEmpty: " + integerMyQueue.isEmpty());

        integerMyQueue.push(10);
        System.out.println("isEmpty: " + integerMyQueue.isEmpty());
        System.out.println("peek: " + integerMyQueue.peek());

        integerMyQueue.push(20);
        System.out.println("isEmpty: " + integerMyQueue.isEmpty());
        System.out.println("peek: " + integerMyQueue.peek());

        integerMyQueue.push(30);
        System.out.println("isEmpty: " + integerMyQueue.isEmpty());
        System.out.println("peek: " + integerMyQueue.peek());

        System.out.println("Remove: " + integerMyQueue.remove());
        System.out.println("isEmpty: " + integerMyQueue.isEmpty());
        System.out.println("peek: " + integerMyQueue.peek());

        System.out.println("Remove: " + integerMyQueue.remove());
        System.out.println("isEmpty: " + integerMyQueue.isEmpty());
        System.out.println("peek: " + integerMyQueue.peek());

        System.out.println("Remove: " + integerMyQueue.remove());
        System.out.println("isEmpty: " + integerMyQueue.isEmpty());
        System.out.println("peek: " + integerMyQueue.peek());
    }
}
