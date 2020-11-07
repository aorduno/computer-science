package CTCI.StacksAndQueues;

import DataStructures.StackG;

public class StackUtils {
    public static void print(StackG stackG) {
        System.out.println("Printing stack...");
        StackG<Object> tmp = new StackG<>();
        while (!stackG.isEmpty()) {
            Object pop = stackG.pop();
            tmp.push(pop);
            System.out.println("element in stack: " + pop);
        }

        while (!tmp.isEmpty()) {
            stackG.push(tmp.pop());
        }
    }
}
