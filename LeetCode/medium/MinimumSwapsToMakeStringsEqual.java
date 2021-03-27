package LeetCode.medium;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MinimumSwapsToMakeStringsEqual {
    public static void main(String[] args) {
        testCase("xy", "yx");
        testCase("yy", "xx");
        testCase("xxx", "yyy");
        testCase("y", "x");
        testCase("y", "y");
        testCase("xxyyxyxyxx", "xyyxyxxxyx");
    }

    private static void testCase(String s1, String s2) {
        LogUtils.logMessage("[[MinimumSwapsToMakeStringsEqual]] Computing minimum swaps required to make given strings equals. S1: " + s1 + " S2: " + s2);

        int swaps = computeMinimumSwapsWorking(s1, s2);
        LogUtils.logMessage("Swaps: " + swaps);
    }

    private static int computeMinimumSwapsWorking(String s1, String s2) {
        int diffX = 0;
        int diffY = 0;
        for (int x = 0; x < s1.length(); x++) {
            int charA = s1.charAt(x);
            int charB = s2.charAt(x);
            if (charA != charB) {
                if (charA == 'x') {
                    diffX++;
                } else {
                    diffY++;
                }
            }
        }

        if ((diffX + diffY) % 2 != 0) {
            return -1;
        }

        return diffX / 2 + diffY / 2 + (diffX % 2 == 0 ? 0 : 2);
    }

    private static int computeMinimumSwaps(String s1, String s2) {
        int[] countsA = new int[2]; // 0 = x, 1 = y
        int[] countsB = new int[2]; // 0 = x, 1 = y
        List<Integer> diffIndex = new ArrayList<>();
        for (int x = 0; x < s1.length(); x++) {
            int charA = s1.charAt(x);
            int charB = s2.charAt(x);
            if (charA != charB) {
                diffIndex.add(x);
            }

            countsA[charA - 'x']++;
            countsB[charB - 'x']++;
        }

        if (!canMakeEqual(diffIndex, countsA, countsB)) {
            return -1; // at this point we know we can't make equal...
        }

        return computeSwapsBrute(s1, s2, diffIndex);
    }

    private static int computeSwapsBrute(String s1, String s2, List<Integer> diffIndex) {
        int swaps = 0;
        while (!diffIndex.isEmpty()) {
            // fix two by two
            int toSwap = diffIndex.remove(0);
            int swapWith = diffIndex.remove(0);
            swaps += computeMinSwapsRecursively("" + s1.charAt(toSwap) + s1.charAt(swapWith), "" + s2.charAt(toSwap) + s2.charAt(swapWith), 0, 1, 0);
        }

        return swaps;
    }

    private static int computeMinSwapsRecursively(String s1, String s2, int toSwap, int swapWith, int currentSwaps) {
        if (s1.equals(s2)) {
            return currentSwaps;
        }

        if (currentSwaps > 2) {
            return -1;
        }

        // strategyA = swap s1 toSwap with s2 toSwap
        // strategyB = swap s1 toSwap with s2 swapWith
        int swapsWithA = computeMinSwapsRecursively(insertAt(s1, toSwap, s2.charAt(toSwap)), insertAt(s2, toSwap, s1.charAt(toSwap)), toSwap, swapWith, currentSwaps + 1);
        int swapsWithB = computeMinSwapsRecursively(insertAt(s1, toSwap, s2.charAt(swapWith)), insertAt(s2, swapWith, s1.charAt(toSwap)), toSwap, swapWith, currentSwaps + 1);
        if (swapsWithA == -1) {
            swapsWithA = Integer.MAX_VALUE;
        }

        if (swapsWithB == -1) {
            swapsWithB = Integer.MAX_VALUE;
        }
        return Math.min(swapsWithA, swapsWithB);
    }

    private static String insertAt(String s1, int insertAt, char toInsert) {
        return s1.substring(0, insertAt) + toInsert + s1.substring(insertAt + 1);
    }

    private static boolean canMakeEqual(List<Integer> diffIndex, int[] countsA, int[] countsB) {
        return diffIndex.size() % 2 == 0 && (countsA[0] + countsB[0]) % 2 == 0 && (countsA[1] + countsB[1]) % 2 == 0;
    }
}
