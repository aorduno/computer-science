package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Arrays;

public class PeaksAndValleys {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, false);
        testCase(new int[]{10, 100, 1000, 5, 55, 99, 10000, 8788, 99293, -1}, false);

        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, true);
        testCase(new int[]{10, 100, 1000, 5, 55, 99, 10000, 8788, 99293, -1}, true);
    }

    private static void testCase(int[] ints, boolean optimal) {
        LogUtils.logMessage("[[PeaksAndValleys" + (optimal ? "Optimal" : "") + "]] Generating peaks and valleys alternating sequence for given array", true);
        ArrayUtils.print(ints);

        if (optimal) {
            generateAlternatingOptimal(ints);
        } else {
            generateAlternating(ints);
        }

        LogUtils.logMessage("alternated:", true);
        ArrayUtils.print(ints);

    }

    // no sort needed -- all we need is process three numbers batches and make sure the biggest one is in the middle (peak)
    // time complexity o(n)
    // space complexity o(1)
    private static void generateAlternatingOptimal(int[] numbers) {
        for (int x = 1; x < numbers.length; x += 2) {
            int biggestIndex = getBiggestIndex(numbers, x - 1, x, x + 1);
            if (biggestIndex != x) {
                swap(numbers, x, biggestIndex);
            }
        }
    }

    private static int getBiggestIndex(int[] numbers, int previous, int current, int next) {
        int maxIndex = numbers.length - 1;
        int previousValue = numbers[previous];
        int currentValue = numbers[current];
        int biggestIndex = currentValue < previousValue ? previous : current;
        if (next <= maxIndex) {
            int nextValue = numbers[next];
            if (numbers[biggestIndex] < nextValue) {
                biggestIndex = next;
            }
        }
        return biggestIndex;
    }

    // sort and swap each pair
    // time complexity o(n log n)
    // space complexity o(n) due sort
    private static void generateAlternating(int[] ints) {
        Arrays.sort(ints);
        for (int x = 1; x <= ints.length - 1; x += 2) {
            swap(ints, x, x - 1);
        }
    }

    private static void swap(int[] ints, int x, int y) {
        int temp = ints[x];
        ints[x] = ints[y];
        ints[y] = temp;
    }
}
