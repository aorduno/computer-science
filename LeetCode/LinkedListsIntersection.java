package LeetCode;

import DataStructures.Node;

import java.util.HashMap;
import java.util.Map;

class IntersectionData {
    Node _tail;
    int _length;

    public IntersectionData(int length, Node tail) {
        _length = length;
        _tail = tail;
    }
}

public class LinkedListsIntersection {
    public Node getIntersectionNode(Node headA, Node headB) {
//        return getIntersectionInitial(headA, headB);
        return getIntersectionABitBetter(headA, headB);
    }

    // another way to solve this would be ...
    // 1) get _length and tail of both
    // 2) we check if both tails are the same (if not there's no intersection)
    // 3) get the diff between both lists' _length, so we can adjust both in order to move forward one by one and find the intersection
    // time o(n)
    // space o(1)
    private Node getIntersectionABitBetter(Node headA, Node headB) {
        IntersectionData aIntersectionData = getIntersectionData(headA);
        IntersectionData bIntersectionData = getIntersectionData(headB);

        if (aIntersectionData._tail != bIntersectionData._tail) {
            return null;
        }

        // move forward the list that needs to... so both are in same terms...
        if (aIntersectionData._length > bIntersectionData._length) {
            headA = moveListForward(headA, aIntersectionData._length - bIntersectionData._length);
        }
        if (aIntersectionData._length < bIntersectionData._length) {
            headB = moveListForward(headB, bIntersectionData._length - aIntersectionData._length);
        }

        while (headA != headB) {
            headA = headA._next;
            headB = headB._next;
        }

        return headA;
    }

    private Node moveListForward(Node node, int moves) {
        while (moves > 0) {
            node = node._next;
            moves--;
        }

        return node;
    }

    private IntersectionData getIntersectionData(Node node) {
        int length = 0;
        Node current = node;
        while (current != null) {
            length++;
            current = current._next;
        }

        return new IntersectionData(length, current);
    }

    // one way to solve this is...
    // 1) collect all nodes from one list in a map
    // 2) iterate the other list and check if map contains one of its nodes
    // time o(n)
    // space o(n), where n = elements in A
    private Node getIntersectionInitial(Node headA, Node headB) {
        Node currentA = headA;
        Map<Node, Node> nodeMap = new HashMap<>();
        while (currentA != null) {
            nodeMap.put(currentA, currentA);
            currentA = currentA._next;
        }

        while (headB != null) {
            if (nodeMap.containsKey(headB)) {
                return headB;
            }

            headB = headB._next;
        }

        // no matches at this point
        return null;
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    private static Node addNext(Node node, int value) {
        Node next = new Node(value);
        node._next = next;
        return next;
    }

    private static Node addNext(Node node, Node next) {
        node._next = next;
        return next;
    }

    private static void doAndPrint(Node a, Node b) {
        LinkedListsIntersection linkedListsIntersection = new LinkedListsIntersection();
        Node intersectionNode = linkedListsIntersection.getIntersectionNode(a, b);
        System.out.print("Intersection node is: " + intersectionNode);
        if (intersectionNode != null) {
            System.out.print(" with value " + intersectionNode.getData());
        }
        System.out.println();
    }

    private static void testCase3() {
        // a
        // 1 -> 10 -> 15 -> 8 -> 9
        Node a = new Node(1);
        Node next = addNext(a, 10);
        next = addNext(next, 15);
        Node intersection = addNext(next, 8);
        addNext(intersection, 9);
        // b
        // 2 -> 3 -> 4 -> 5 -> 8 -> 9
        Node b = new Node(2);
        Node nextb = addNext(b, 3);
        nextb = addNext(nextb, 4);
        nextb = addNext(nextb, 5);
        addNext(nextb, intersection);

        doAndPrint(a, b);
    }

    private static void testCase2() {
        // no intersection
        // a
        // 2 -> 6 -> 4
        Node a = new Node(2);
        Node next = addNext(a, 6);
        // b
        // 1 -> 5
        Node b = new Node(1);
        doAndPrint(a, b);
    }

    private static void testCase1() {
        // a
        // 0 -> 9 -> 1 -> 2 -> 4
        Node a = new Node(0);
        Node next = addNext(a, 9);
        next = addNext(next, 1);
        Node intersection = addNext(next, 2);
        addNext(intersection, 4);
        // b
        // 3 -> 2 -> 4
        Node b = new Node(3);
        Node nextb = addNext(b, intersection);
        addNext(nextb, 4);

        doAndPrint(a, b);
    }
}
