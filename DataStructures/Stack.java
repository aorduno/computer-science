package DataStructures;

public class Stack {
    Node _top;

    public boolean isEmpty() {
        return _top == null;
    }

    public int peek() {
        return _top._data;
    }

    public int add(int data) {
        Node newNode = new Node(data);
        if (_top == null) {
            _top = newNode;
        } else {
            newNode._next = _top;
            _top = newNode;
        }

        return newNode._data;
    }

    public int pop() {
        int data = _top._data;
        _top = _top._next;
        return data;
    }

    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);

        System.out.println("Stacks!");

        while (!stack.isEmpty()) {
            System.out.println("Peek " + stack.peek());
            System.out.println("Pop! " + stack.pop());
        }
    }
}
