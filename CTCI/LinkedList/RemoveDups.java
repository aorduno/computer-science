package CTCI.LinkedList;

import DataStructures.LinkedList;
import DataStructures.Node;

import java.util.HashMap;
import java.util.Map;

public class RemoveDups {
    public static void main(String[] args) {
        testCaseBuffer(new int[]{1, 2, 3, 4, 5, 6});
        testCaseNoBuffer(new int[]{1, 2, 3, 4, 5, 6});
        testCaseBuffer(new int[]{1, 2, 3, 3, 2, 1});
        testCaseNoBuffer(new int[]{1, 2, 3, 3, 2, 1});
        testCaseBuffer(new int[]{0, 0, 0, 1, 2, 9, 5});
        testCaseNoBuffer(new int[]{0, 0, 0, 1, 2, 9, 5});
        testCaseBuffer(new int[]{0, 0, 0, 1, 9, 9, 9, 9, 10});
        testCaseNoBuffer(new int[]{0, 0, 0, 1, 9, 9, 9, 9, 10});
    }

    private static void testCaseBuffer(int[] numbers) {
        LinkedList list = LinkedListUtils.createList(numbers);
        System.out.println("List holding word with dups:");
        list.print();
        doRemove(list);
        System.out.println("List holding word no dups:");
        list.print();

    }

    private static void testCaseNoBuffer(int[] numbers) {
        LinkedList list = LinkedListUtils.createList(numbers);
        System.out.println("[[NO BUFFER]] List holding word with dups:");
        list.print();
        doRemoveNoBuffer(list);
        System.out.println("[[NO BUFFER]] List holding word no dups:");
        list.print();

    }

    // time complexity o(n)
    // space complexity o(x), where x is unique numbers in list
    private static void doRemove(LinkedList list) {
        Map<Integer, Integer> existingMap = new HashMap<>();
        Node current = list.getHead();
        Node previous = null;
        while (current != null) {
            if (existingMap.containsKey(current.getData())) {
                removeFromList(current, previous);
                current = current._next;
                continue;
            }

            existingMap.put(current.getData(), current.getData());
            previous = current;
            current = current._next;
        }
    }

    private static void doRemoveNoBuffer(LinkedList linkedList) {
        Node outer = linkedList.getHead();
        Node inner;
        Node previous;
        while (outer != null) {
            previous = outer;
            inner = outer._next;
            while (inner != null) {
                if (inner.getData() == outer.getData()) {
                    removeFromList(inner, previous);
                    inner = previous._next;
                    continue;
                }
                previous = inner;
                inner = inner._next;
            }
            outer = outer._next;
        }
    }

    private static void removeFromList(Node current, Node previous) {
        previous._next = previous._next._next;
    }
}
