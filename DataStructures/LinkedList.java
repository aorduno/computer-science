package DataStructures;

public class LinkedList {
    Node _head;

    // add at the end
    void append(int data) {
        if (_head == null) {
            _head = new Node(data);
            return;
        }

        Node current = _head;
        while (current._next != null) {
            current = current._next;
        }

        current._next = new Node(data);
    }

    // add at the beginning
    void preprend(int data) {
        if (_head == null) {
            _head = new Node(data);
            return;
        }

        Node newNode = new Node(data);
        newNode._next = _head;
        _head = newNode;
    }

    void delete(int data) {
        if (_head == null) {
            return;
        }

        if (_head._data == data) {
            _head = _head._next;
            return;
        }

        Node current = _head;
        while (current._next != null) {
            if (current._next._data == data) {
                current._next = current._next._next;
                return;
            }

            current = current._next;
        }
    }

    void print() {
        Node current = _head;
        System.out.print("Printing linkedList values: " + current._data);
        while (current._next != null) {
            System.out.print(" " + current._next._data);
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
