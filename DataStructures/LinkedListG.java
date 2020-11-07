package DataStructures;

public class LinkedListG<T> {
    private NodeG _head;

    public NodeG getHead() {
        return _head;
    }

    public void setHead(NodeG head) {
        _head = head;
    }

    // add at the end
    public void append(T data) {
        if (_head == null) {
            _head = new NodeG<>(data);
            return;
        }

        NodeG current = _head;
        while (current._next != null) {
            current = current._next;
        }

        current._next = new NodeG<>(data);
    }

    // add at the beginning
    void preprend(T data) {
        if (_head == null) {
            _head = new NodeG<>(data);
            return;
        }

        NodeG newNode = new NodeG<>(data);
        newNode._next = _head;
        _head = newNode;
    }

    void delete(T data) {
        if (_head == null) {
            return;
        }

        if (_head._data.equals(data)) {
            _head = _head._next;
            return;
        }

        NodeG current = _head;
        while (current._next != null) {
            if (current._next._data.equals(data)) {
                current._next = current._next._next;
                return;
            }

            current = current._next;
        }
    }

    public void print() {
        NodeG current = _head;
        System.out.print("Printing linkedList values: " + current._data);
        while (current._next != null) {
            System.out.print(" " + current._next._data);
            current = current._next;
        }

        System.out.println();
    }

    public static void print(NodeG node) {
        NodeG current = node;
        System.out.print("Printing linkedList values: " + current._data);
        while (current._next != null) {
            System.out.print(" -> " + current._next._data);
            current = current._next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.preprend(1);
        linkedList.print();

        linkedList.preprend(2);
        linkedList.print();

        linkedList.preprend(3);
        linkedList.print();

        linkedList.append(10);
        linkedList.print();

        linkedList.append(100);
        linkedList.print();

        linkedList.append(1000);
        linkedList.print();

        linkedList.delete(5000);
        linkedList.print();

        linkedList.delete(10);
        linkedList.print();

        linkedList.delete(1000);
        linkedList.print();

        linkedList.delete(3);
        linkedList.print();
    }
}
