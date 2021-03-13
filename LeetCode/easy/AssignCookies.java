package LeetCode.easy;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3}, new int[]{1, 1});
        testCase(new int[]{1, 2}, new int[]{1, 2, 3});
        testCase(new int[]{3, 2, 3}, new int[]{1});
        testCase(new int[]{1, 2, 3}, new int[]{5, 1});
    }

    private static void testCase(int[] childHunger, int[] cookieSize) {
        LogUtils.logMessage("[[AssignCookies]] Finding out how many content kids we can have with the given inputs.");
        LogUtils.logMessage("Child Hunger");
        ArrayUtils.print(childHunger);
        LogUtils.logMessage("Cookie size");
        ArrayUtils.print(cookieSize);

        int contentNumber = computeBetter(childHunger, cookieSize);
        LogUtils.logMessage("Happy kids: " + contentNumber);
    }

    private static int computeBetter(int[] childHunger, int[] cookieSize) {
        Arrays.sort(childHunger);
        Arrays.sort(cookieSize);
        int k = 0;
        int c = 0;
        int content = 0;
        while (c < cookieSize.length && k < childHunger.length) {
            if (cookieSize[c] >= childHunger[k]) {
                content++;
                k++;
            }

            c++;
        }
        return content;
    }

    private static int computeBrute(int[] childHunger, int[] cookieSize) {
        int content = 0;
        for (int x = 0; x < cookieSize.length; x++) {
            int minDiff = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int y = 0; y < childHunger.length; y++) {
                if (childHunger[y] == -1) {
                    continue;
                }

                int currentDiff = cookieSize[x] - childHunger[y];
                if (currentDiff >= 0 && currentDiff < minDiff) {
                    minDiff = currentDiff;
                    minIndex = y;
                }
            }

            if (minIndex != -1) {
                childHunger[minIndex] = -1;
                content++;
            }
        }

        return content;
    }
}
