package CTCI.LinkedList;

import DataStructures.LinkedListG;
import DataStructures.NodeG;

import java.util.Stack;

public class IsPalindrome {
    public static void main(String[] args) {
        testCase("abcba", true);
        testCase("abcb", true);
        testCase("abcba", false);
        testCase("abcb", false);
    }

    private static void testCase(String word, boolean extraSpace) {
        LinkedListG list = LinkedListUtils.createList(word);
        System.out.println(extraSpace ? "[[extraSpace]]" : "[[noExtraSpace]]" + " List to check");
        list.print();
        System.out.println("isPalindrome: " + (extraSpace ? isPalindromeExtraSpace(list) : isPalindromeNoExtraSpace(list)));
    }

    private static boolean isPalindromeNoExtraSpace(LinkedListG list) {
        int size = getSize(list);
        boolean isOddSize = size % 2 != 0;
        int stepsForward = size / 2;
        if (size == 0) {
            return false;
        }

        if (size == 1) {
            return true;
        }

        NodeG leftHead = list.getHead();
        while (stepsForward > 1) {
            leftHead = leftHead._next;
            stepsForward--;
        }

        NodeG rightHead = isOddSize ? leftHead._next._next : leftHead._next;
        leftHead._next = null; // cut the bridge
        LinkedListG reversedRight = createReverseFromHead(rightHead);
        leftHead = list.getHead();
        rightHead = reversedRight.getHead();
        while (leftHead != null) {
            if (!leftHead.getData().equals(rightHead.getData())) {
                return false;
            }

            leftHead = leftHead._next;
            rightHead = rightHead._next;
        }

        return true;
    }

    private static LinkedListG createReverseFromHead(NodeG head) {
        LinkedListG toReverse = new LinkedListG();
        toReverse.setHead(head);
        LinkedListUtils.reverseList(toReverse);
        return toReverse;
    }

    private static int getSize(LinkedListG list) {
        int size = 0;
        NodeG head = list.getHead();
        while (head != null) {
            size++;
            head = head._next;
        }

        return size;
    }

    // time o(n)
    // space o(n)
    private static boolean isPalindromeExtraSpace(LinkedListG list) {
        Stack stack = new Stack<>();
        NodeG current = list.getHead();
        while (current != null) {
            stack.push(current.getData());
            current = current._next;
        }

        current = list.getHead();
        while (current != null) {
            if (!current.getData().equals(stack.pop())) {
                return false;
            }

            current = current._next;
        }

        return true;
    }
}
