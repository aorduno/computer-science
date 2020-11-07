package CTCI.StacksAndQueues;

import DataStructures.StackG;
import DataStructures.StackNode;

import java.util.EmptyStackException;

public class StackMin {
    private StackG<Integer> _normal;
    private StackG<Integer> _auxiliar;

    StackMin() {
        _normal = new StackG<>();
        _auxiliar = new StackG<>();
    }

    int getMin() {
        if (_auxiliar.getTop() == null) {
            throw new EmptyStackException();
        }
        return _auxiliar.getTopData();
    }

    void push(int data) {
        StackNode<Integer> topData = _auxiliar.getTop();
        int toInsert = (topData != null && topData.getData() <= data) ? topData.getData() : data;
        _auxiliar.push(toInsert);
        _normal.push(data);
    }

    int pop() {
        int data = _normal.pop();
        _auxiliar.pop();
        return data;
    }

    public static void main(String[] args) {
        StackMin stackMin = new StackMin();

        System.out.println("Push: " + 10);
        stackMin.push(10);
        System.out.println("Min: " + stackMin.getMin());

        System.out.println("Push: " + 20);
        stackMin.push(20);
        System.out.println("Min: " + stackMin.getMin());

        System.out.println("Push: " + 30);
        stackMin.push(30);
        System.out.println("Min: " + stackMin.getMin());

        System.out.println("Pop: " + stackMin.pop());
        System.out.println("Min: " + stackMin.getMin());
        System.out.println("Pop: " + stackMin.pop());
        System.out.println("Min: " + stackMin.getMin());

        System.out.println("Push: " + 5);
        stackMin.push(5);
        System.out.println("Push: " + 8);
        stackMin.push(8);
        System.out.println("Min: " + stackMin.getMin());

        System.out.println("Pop: " + stackMin.pop());
        System.out.println("Min: " + stackMin.getMin());

        System.out.println("Pop: " + stackMin.pop());
        System.out.println("Min: " + stackMin.getMin());
    }

}
