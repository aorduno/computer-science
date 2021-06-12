package LeetCode.easy;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

public class FindTownJudge {
    public static void main(String[] args) {
        testCase(2, new int[][]{{1, 2}});
        testCase(3, new int[][]{{1, 3}, {2, 3}});
        testCase(3, new int[][]{{1, 3}, {2, 3}, {3, 1}});
        testCase(3, new int[][]{{1, 2}, {2, 3}});
        testCase(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}});
    }

    private static void testCase(int n, int[][] trust) {
        LogUtils.logMessage("[[FindJudge]] Looking for judge in given trust data");
        ArrayUtils.printMatrix(trust);

        int judge = findJudge(n, trust);
        LogUtils.logMessage("Judge is: " + judge);
    }

    private static int findJudge(int n, int[][] trust) {
        int[] trustInData = new int[n];
        int[] trustedData = new int[n];
        for (int[] trustPair : trust) {
            trustInData[trustPair[0] - 1]++;
            trustedData[trustPair[1] - 1]++;
        }

        for (int x = 0; x < trustInData.length; x++) {
            if (isJudge(trustInData[x], trustedData[x], n)) {
                return x + 1;
            }
        }

        return -1;
    }

    private static boolean isJudge(int trustInDatum, int trustedData, int total) {
        return trustInDatum == 0 && (trustedData == (total - 1));
    }
}
