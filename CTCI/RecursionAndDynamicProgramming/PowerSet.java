package CTCI.RecursionAndDynamicProgramming;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSet {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3, 4});
    }

    private static void testCase(int[] numbers) {
        LogUtils.logMessage("[[PowerSet]] Calculating all subsets for given set");
        ArrayUtils.print(numbers);
        List<int[]> powerSet = computePowerSet(numbers);
        LogUtils.logMessage("Here's your power set:");
        print(powerSet);

    }

    private static void print(List<int[]> powerSet) {
        for (int[] ints : powerSet) {
            ArrayUtils.print(ints);
        }
    }

    private static List<int[]> computePowerSet(int[] numbers) {
        List<int[]> powerSet = new ArrayList<>();
        computeRecursively(new int[]{}, numbers, powerSet);
        return powerSet;
    }

    private static void computeRecursively(int[] current, int[] remaining, List<int[]> powerSet) {
        powerSet.add(current);

        if (remaining.length == 0) { // done
            return;
        }

        for (int x = 0; x < remaining.length; x++) {
            int number = remaining[x];
            int[] newCurrent = addToCurrent(current, number);
            int[] newRemaining = removeFromRemaining(remaining, x);
            computeRecursively(newCurrent, newRemaining, powerSet);

        }
    }

    private static int[] removeFromRemaining(int[] remaining, int x) {
        int[] newRemaining = new int[remaining.length - x - 1];
        System.arraycopy(remaining, x + 1, newRemaining, 0, remaining.length - x - 1);
        return newRemaining;
    }

    private static int[] addToCurrent(int[] current, int number) {
        // generate new array with number
        int[] newCurrent = Arrays.copyOf(current, current.length + 1);
        newCurrent[current.length] = number;
        return newCurrent;
    }
}
