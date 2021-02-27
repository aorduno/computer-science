package LeetCode;

import DataStructures.Node;

public class PalindromeLinkedList {
    private boolean isPalindrome(Node head) {
        return aBitBetter(head);
    }

    // time o(n)
    // space o(n)
    private boolean naiveApproach(Node head) {
        Node current = head;
        int length = 0;
        while (current != null) {
            current = current._next;
            length++;
        }

        if (length == 0 || length == 1) {
            return length == 1;
        }

        Node[] aux = new Node[length];
        current = head;
        int pos = 0;
        while (current != null) {
            aux[pos] = current;
            current = current._next;
            pos++;
        }

        if (length % 2 == 0) { // odd
            return doEven(aux);
        } else { // even
            return doOdds(aux);
        }
    }

    // what we do here is...
    // 1) get the _length of the list
    // 2) check _length to build two lists, one list will be from mid to start (reversed) and the other one
    // will be from mid to end (normal)
    // 3) iterate both lists and compare values, should be the same! otherwise is not palindrome
    private boolean aBitBetter(Node head) {
        Node current = head;
        int length = 0;
        while (current != null) {
            current = current._next;
            length++;
        }

        if (length <= 1) {
            return true;
        }

        int mid = length / 2;
        Node halfRightNode = getRightHalf(head, mid);
        Node reversed = getReservedList(head, mid);
        return length % 2 == 0 ? doEven(reversed, halfRightNode) : doOdds(reversed, halfRightNode);
    }

    // for odds we don't care about the mid point, only side halves
    private boolean doOdds(Node reversed, Node halfRightNode) {
        Node currentHalfRight = halfRightNode._next;
        return doPalindromeCheck(reversed, currentHalfRight);
    }

    private boolean doPalindromeCheck(Node node1, Node node2) {
        while (node1 != null && node2 != null) {
            if (node1.getData() != node2.getData()) {
                return false;
            }

            node1 = node1._next;
            node2 = node2._next;
        }

        return true;
    }

    private boolean doEven(Node reversed, Node halfRightNode) {
        return doPalindromeCheck(reversed, halfRightNode);
    }

    private Node getRightHalf(Node head, int target) {
        Node current = head;
        int pos = 0;
        while (pos < target) {
            current = current._next;
            pos++;
        }

        return current;
    }

    // reversed until target
    private Node getReservedList(Node head, int target) {
        int pos = 0;
        Node current = head;
        Node previous = null;
        while (pos < target) {
            Node next = current._next;
            current._next = previous;
            previous = current;
            current = next;
            pos++;
        }

        return previous;
    }

    private boolean doEven(Node[] aux) {
        int length = aux.length;
        int x = length / 2; // this goes from mid to end
        int y = x - 1; // this goes from mid  to 0
        return doPalindromeCheck(aux, length, x, y);
    }

    private boolean doPalindromeCheck(Node[] aux, int length, int x, int y) {
        int auxItems = length - 1;
        while (x <= auxItems && y >= 0) { // 0 based index so need to remove 1
            if (aux[x].getData() != aux[y].getData()) {
                return false;
            }
            x++;
            y--;
        }
        return true;
    }

    private boolean doOdds(Node[] aux) {
        int length = aux.length;
        int x = (length / 2) + 1; // this goes from mid to end
        int y = x - 2; // this goes from mid  to 0
        return doPalindromeCheck(aux, length, x, y);
    }

    private static void checkAndPrint(Node head) {
        PalindromeLinkedList palindromeLinkedList = new PalindromeLinkedList();
        System.out.println("Evaluating isPalindrome for....");
        DataStructures.LinkedList.print(head);
        System.out.println("IsPalindrome: " + palindromeLinkedList.isPalindrome(head));
    }

    public static void main(String[] args) {
//        testCases1();
//        testCases2();
        testCases3();
    }

    private static void testCases1() {
        // 1 -> 2 -> 2 -> 1
        Node node = new Node(1);
        checkAndPrint(node); // expected true

        node._next = new Node(2);
        checkAndPrint(node); // expected false

        node._next._next = new Node(2);
        checkAndPrint(node); // expected false

        node._next._next._next = new Node(1);
        checkAndPrint(node); // expected true

        // 1 -> 2 -> 3 -> 2 -> 1
        Node node2 = new Node(1);
        checkAndPrint(node2); // expected true

        node2._next = new Node(2);
        checkAndPrint(node2); // expected false

        node2._next._next = new Node(3);
        checkAndPrint(node2); // expected false

        node2._next._next._next = new Node(2);
        checkAndPrint(node2); // expected false

        node2._next._next._next._next = new Node(1);
        checkAndPrint(node2); // expected true
    }

    private static void testCases2() {
        // 1 -> 2 -> 2 -> 1
        Node node = new Node(1);
        checkAndPrint(node); // expected true

        Node node2 = new Node(1);
        node2._next = new Node(2);
        checkAndPrint(node2); // expected false

        Node node3 = new Node(1);
        node3._next = new Node(2);
        node3._next._next = new Node(2);
        checkAndPrint(node3); // expected false

        Node node4 = new Node(1);
        node4._next = new Node(2);
        node4._next._next = new Node(2);
        node4._next._next._next = new Node(1);
        checkAndPrint(node4); // expected true

        // 1 -> 2 -> 3 -> 2 -> 1
        Node node5 = new Node(1);
        checkAndPrint(node5); // expected true

        Node node6 = new Node(1);
        node6._next = new Node(2);
        checkAndPrint(node6); // expected false

        Node node7 = new Node(1);
        node7._next = new Node(2);
        node7._next._next = new Node(3);
        node7._next._next._next = new Node(2);
        checkAndPrint(node7); // expected false

        Node node8 = new Node(1);
        node8._next = new Node(2);
        node8._next._next = new Node(3);
        node8._next._next._next = new Node(2);
        node8._next._next._next._next = new Node(1);
        checkAndPrint(node8); // expected false
    }

    private static void testCases3() {
        Node node = new Node(0);
        node._next = new Node(2);
        node._next._next = new Node(2);
        node._next._next._next = new Node(1);
        checkAndPrint(node);
    }
}
