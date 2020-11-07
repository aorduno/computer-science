package CTCI.StacksAndQueues;

import DataStructures.StackG;

public class SortStack {

    public static void main(String[] args) {
        StackG<Integer> integerStackG = new StackG<>();

        integerStackG.push(5);
        integerStackG.push(50);
        integerStackG.push(25);
        integerStackG.push(3);
        integerStackG.push(100);
        integerStackG.push(60);

        testCase(integerStackG);
    }

    private static void testCase(StackG<Integer> integerStackG) {
        StackUtils.print(integerStackG);
        sort(integerStackG);
        System.out.println("After sorting");
        StackUtils.print(integerStackG);
    }

    private static void sort(StackG<Integer> originalStack) {
        StackG<Integer> aux = new StackG<>();
        // pop the original stack
        // compare with the sorted one
        // reverse and return the original one
        while (!originalStack.isEmpty()) {
            Integer popped = originalStack.pop();
            if (aux.isEmpty()) {
                aux.push(popped);
                continue;
            }

            originalStack.push(popped);
            while (!aux.isEmpty() && aux.peek() > popped) {
                Integer auxPopped = aux.pop();
                originalStack.push(auxPopped);
            }

            aux.push(popped);
            while (!popped.equals(originalStack.peek())) {
                Integer pop = originalStack.pop();
                aux.push(pop);
            }

            originalStack.pop(); // pop
        }

        while (!aux.isEmpty()) {
            originalStack.push(aux.pop());
        }

        String abc = "";
    }
}
