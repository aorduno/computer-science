package CTCI.LinkedList;

import DataStructures.LinkedList;
import DataStructures.Node;

public class Partition {
    public static void main(String[] args) {
        testCase(new int[]{3, 6, 1, 3, 9, 10, 3}, 3);
        testCase(new int[]{3, 5, 8, 5, 10, 2, 1}, 5);
    }

    private static void testCase(int[] numbers, int partitionBy) {
        LinkedList list = LinkedListUtils.createList(numbers);
        System.out.println("Partitioning list by: " + partitionBy);
        list.print();

        System.out.println("ONE WAY!");
        LinkedList partitioned = doPartition(list, partitionBy);
        partitioned.print();

        System.out.println("ONE LIST!");
        partitioned = doPartitionCleaner(list, partitionBy);
        partitioned.print();
    }

    private static LinkedList doPartition(LinkedList list, int partitionBy) {
        Node current = list.getHead();
        LinkedList left = new LinkedList();
        LinkedList right = new LinkedList();
        Node currentLeft = null;
        Node currentRight = null;
        while (current != null) {
            int data = current.getData();
            Node newNode = new Node(data);
            if (data < partitionBy) {
                if (left.getHead() == null) {
                    left.setHead(newNode);
                    currentLeft = newNode;
                } else {
                    currentLeft._next = newNode;
                    currentLeft = currentLeft._next;
                }
            } else {
                if (right.getHead() == null) {
                    right.setHead(newNode);
                    currentRight = newNode;
                } else {
                    currentRight._next = newNode;
                    currentRight = currentRight._next;
                }
            }
            current = current._next;
        }

        return mergePartition(left, right);
    }

    private static LinkedList doPartitionCleaner(LinkedList linkedList, int partitionBy) {
        Node current = linkedList.getHead();
        Node tail = new Node(current.getData());
        Node head = tail;

        current = current._next;
        while (current != null) {
            int data = current.getData();
            Node newNode = new Node(data);
            if (data < partitionBy) {
                newNode._next = head;
                head = newNode;
            } else {
                tail._next = newNode;
                tail = newNode;
            }
            current = current._next;
        }

        LinkedList partitioned = new LinkedList();
        partitioned.setHead(head);
        return partitioned;
    }

    private static LinkedList mergePartition(LinkedList left, LinkedList right) {
        if (left.getHead() != null && right.getHead() != null) {
            Node currentLeft = left.getHead();
            while (currentLeft._next != null) {
                currentLeft = currentLeft._next;
            }

            currentLeft._next = right.getHead();
            return left;
        } else if (left.getHead() != null) {
            return left;
        }

        return right;
    }

    private static Node addToPartition(int data, Node node) {
        if (node == null) {
            Node newNode = new Node(data);
            return newNode;
        }

        Node newNode = new Node(data);
        node._next = newNode;
        return newNode;
    }

    private static void addToLeft(int data, Node left) {

    }
}
