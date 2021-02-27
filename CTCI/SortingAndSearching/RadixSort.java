package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import Helpers.Array;

// this implementation is base 10
public class RadixSort {
    private static int BASE = 10;

    public static void main(String[] args) {
        testCase(new int[]{999, 1000, 4, 809, 9, 44, 66, 15, 35, 1, 3});
    }

    private static void testCase(int[] ints) {
        LogUtils.logMessage("[[RadixSort]] Received array of ints as input", true);
        ArrayUtils.print(ints);
        LogUtils.logMessage("Sorted:", true);
        doRadixSort(ints);
        Array.print(ints);
    }

    private static void doRadixSort(int[] ints) {
        int maxDigit = getMaxNumber(ints);
        for (int exponent = 1; (maxDigit / exponent) > 0; exponent *= 10) {
            doCountSort(ints, exponent);
        }
    }

    private static void doCountSort(int[] ints, int exponent) {
        // find occurrences
        int[] occurrences = new int[BASE];
        int totalNumbers = ints.length;
        for (int x = 0; x < totalNumbers; x++) {
            int current = ints[x];
            occurrences[computeRemainder(exponent, current)]++;
        }

        // build positions
        int[] positions = new int[BASE];
        positions[0] = occurrences[0];
        for (int x = 1; x < occurrences.length; x++) {
            int occurrence = occurrences[x];
            positions[x] = positions[x - 1] + occurrence;
        }

        // sort in temp
        int[] temp = new int[totalNumbers];
        for (int x = totalNumbers - 1; x >= 0; x--) {
            int current = ints[x];
            int remainder = computeRemainder(exponent, current);
            temp[positions[remainder] - 1] = current;
            positions[remainder]--;
        }

        // copy to original
        for (int x = 0; x < temp.length; x++) {
            ints[x] = temp[x];
        }
    }

    private static int computeRemainder(int exponent, int current) {
        return current / exponent % BASE;
    }

    private static int getMaxNumber(int[] ints) {
        int max = 0;
        for (int anInt : ints) {
            if (anInt > max) {
                max = anInt;
            }
        }
        return max;
    }
}
