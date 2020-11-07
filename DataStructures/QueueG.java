package DataStructures;

import java.util.NoSuchElementException;

public class QueueG<T> {
    private static class QueueNode<T> {
        private T _data;
        private QueueNode _next;

        public QueueNode(T data) {
            _data = data;
        }
    }

    // remove from head
    private QueueNode _head;
    // add to the tail
    private QueueNode _tail;

    public void add(T data) {
        QueueNode<T> queueNode = new QueueNode<>(data);
        if (_tail == null) {
            _tail = queueNode;
        } else {
            _tail._next = queueNode;
            _tail = queueNode;
        }

        if (_head == null) {
            _head = queueNode;
        }
    }

    private void checkEmptiness() {
        if (_tail == null) {
            throw new NoSuchElementException("Empty Queue");
        }
    }

    public T remove() {
        checkEmptiness();

        T data = (T) _head._data;
        _head = _head._next;
        if (_head == null) {
            _tail = null;
        }

        return data;
    }

    public T peek() {
        checkEmptiness();
        return (T) _head._data;
    }

    public boolean isEmpty() {
        return _tail == null;
    }

    public static void main(String[] args) {
        QueueG<Integer> integerQueue = new QueueG<>();

        System.out.println("isEmpty? : " + integerQueue.isEmpty());
        integerQueue.add(10);
        System.out.println("isEmpty? : " + integerQueue.isEmpty());
        System.out.println("peek : " + integerQueue.peek());
        integerQueue.add(20);
        System.out.println("isEmpty? : " + integerQueue.isEmpty());
        System.out.println("peek : " + integerQueue.peek());
        integerQueue.add(30);
        System.out.println("isEmpty? : " + integerQueue.isEmpty());
        System.out.println("peek : " + integerQueue.peek());

        System.out.println("remove: " + integerQueue.remove());
        System.out.println("remove: " + integerQueue.remove());
        System.out.println("remove: " + integerQueue.remove());
        //empty at this point
        System.out.println("remove: " + integerQueue.remove());

    }
}
