package DataStructures;

import java.util.EmptyStackException;

public class StackG<T> {
    public StackNode _top;

    public StackNode getTop() {
        return _top;
    }

    public <T> T getTopData() {
        return (T) _top.getData();
    }

    public T pop() {
        checkEmptiness();

        T data = (T) _top.getData();
        _top = _top._next;
        return data;
    }

    public void push(T data) {
        StackNode newNode = new StackNode<>(data);
        if (_top == null) {
            _top = newNode;
        } else {
            newNode._next = _top;
            _top = newNode;
        }
    }

    public T peek() {
        checkEmptiness();
        return (T) _top.getData();
    }

    public int size() {
        StackNode top = _top;
        int size = 0;
        while (top != null) {
            _top = _top._next;
            size++;
        }

        return size;
    }

    private void checkEmptiness() {
        if (_top == null) {
            throw new EmptyStackException();
        }
    }

    public boolean isEmpty() {
        return _top == null;
    }

    public static void main(String[] args) {
        StackG<Integer> integerStack = new StackG<>();

        System.out.println("isEmpty? : " + integerStack.isEmpty());
        integerStack.push(10);
        System.out.println("isEmpty? : " + integerStack.isEmpty());
        System.out.println("peek : " + integerStack.peek());
        integerStack.push(20);
        System.out.println("isEmpty? : " + integerStack.isEmpty());
        System.out.println("peek : " + integerStack.peek());
        integerStack.push(30);
        System.out.println("isEmpty? : " + integerStack.isEmpty());
        System.out.println("peek : " + integerStack.peek());

        System.out.println("pop: " + integerStack.pop());
        System.out.println("pop: " + integerStack.pop());
        System.out.println("pop: " + integerStack.pop());
        //empty at this point
        System.out.println("pop: " + integerStack.pop());

    }
}
