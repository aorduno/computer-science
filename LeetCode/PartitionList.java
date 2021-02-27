package LeetCode;

import DataStructures.Node;

public class PartitionList {
    public Node partition(Node head, int x) {
        return partitionImproved(head, x);
    }

    // naive
    public Node partitionNaive(Node head, int x) {
        Node lessThanX = null;
        Node greatherThanEqualToX = null;
        Node headOfGreatersEqual = null;
        Node current = head;
        if (current.getData() < x) {
            lessThanX = new Node(current.getData());
        } else {
            greatherThanEqualToX = new Node(current.getData());
            headOfGreatersEqual = greatherThanEqualToX;
        }
        while (current._next != null) {
            current = current._next;
            int number = current.getData();
            if (number < x) {
                if (lessThanX == null) {
                    lessThanX = new Node(number);
                } else {
                    insert(lessThanX, number);
                }
            } else {
                if (greatherThanEqualToX == null) {
                    greatherThanEqualToX = new Node(number);
                    headOfGreatersEqual = greatherThanEqualToX;
                } else {
                    insert(greatherThanEqualToX, number);
                }
            }
        }

        if (lessThanX == null || greatherThanEqualToX == null) {
            return head;
        }

        // glue
        Node tail = lessThanX;
        while (tail._next != null) {
            tail = tail._next;
        }

        tail._next = headOfGreatersEqual;
        return lessThanX;
    }

    void insert(Node list, int x) {
        while (list._next != null) {
            list = list._next;
        }

        list._next = new Node(x);
    }

    void insert(Node list, Node toInsert) {
        while (list._next != null) {
            list = list._next;
        }

        list._next = toInsert;
    }

    public Node partitionImproved(Node head, int x) {
        Node lessThanX = null;
        Node lastLower = null;
        Node greatherThanEqualToX = null;
        Node lastGreater = null;
        Node headOfGreatersEqual = null;
        Node current = head;
        if (current.getData() < x) {
            lessThanX = new Node(current.getData());
            lastLower = lessThanX;
        } else {
            greatherThanEqualToX = new Node(current.getData());
            lastGreater = greatherThanEqualToX;
            headOfGreatersEqual = greatherThanEqualToX;
        }
        while (current._next != null) {
            current = current._next;
            int number = current.getData();
            Node newNode = new Node(number);
            if (number < x) {
                if (lessThanX == null) {
                    lessThanX = newNode;
                    lastLower = lessThanX;
                } else {
                    lastLower._next = newNode;
                    lastLower = newNode;
                }
            } else {
                if (greatherThanEqualToX == null) {
                    greatherThanEqualToX = newNode;
                    lastGreater = newNode;
                    headOfGreatersEqual = greatherThanEqualToX;
                } else {
                    lastGreater._next = newNode;
                    lastGreater = newNode;
                }
            }
        }

        if (lessThanX == null || greatherThanEqualToX == null) {
            return head;
        }

        // glue
        lastLower._next = headOfGreatersEqual;
        return lessThanX;
    }

    public static void main(String[] args) {
        Node testList = new Node(1);
        testList._next = new Node(4);
        testList._next._next = new Node(3);
        testList._next._next._next = new Node(2);
        testList._next._next._next._next = new Node(5);
        testList._next._next._next._next._next = new Node(2);

        PartitionList partitionList = new PartitionList();
        Node partition = partitionList.partition(testList, 3);
        System.out.println("check it out!");

        testList = new Node(1);
        partition = partitionList.partition(testList, 0);
        System.out.println("check it out!");
    }
}
