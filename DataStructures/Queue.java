package DataStructures;

public class Queue {
    Node _tail; // add here
    Node _head; // remove from here

    boolean isEmpty() {
        return _head == null;
    }

    int peek() {
        return _head._data;
    }

    int add(int data) {
        Node newNode = new Node(data);
        if (_tail != null) {
            _tail._next = newNode;
        }

        _tail = newNode;
        if (_head == null) {
            _head = newNode;
        }

        return newNode._data;
    }

    int remove() {
        int data = _head._data;
        _head = _head._next;
        return data;
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("Queues!");

        while (!queue.isEmpty()) {
            System.out.println("Peek " + queue.peek());
            System.out.println("Remove " + queue.remove());
        }
    }
}
