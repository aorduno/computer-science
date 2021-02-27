package LeetCode.hard;

import CTCI.LogUtils;

import java.util.LinkedList;

public class ShortestSubArrayWithSumAtLeastK {
    public static void main(String[] args) {
        testCase(new int[]{17, 85, 93, -45, -21}, 150);
        testCase(new int[]{1, 1, 1, 2, 3}, 89);
        testCase(new int[]{-28, 81, -20, 28, -29}, 89);
        testCase(new int[]{56, -21, 56, 35, -9}, 61);
        testCase(new int[]{2, -1, 2}, 3);
        testCase(new int[]{2, -1, 2}, 4);
        testCase(new int[]{1}, 1);
        testCase(new int[]{1, 2}, 5);
        testCase(new int[]{10, -5, 8, -1, 3, 2, 5}, 5);
    }

    private static void testCase(int[] ints, int k) {
        int shortest = findShortestBetter(ints, k);
        LogUtils.logMessage("Shortest: " + shortest);
    }

    static int findShortestBetter(int[] A, int K) {
        int shortest = Integer.MAX_VALUE;
        long[] sum = new long[A.length + 1];
        for (int x = 0; x < A.length; x++) {
            sum[x + 1] = sum[x] + A[x];
        }

        LinkedList<Integer> leftIndex = new LinkedList<>();
        for (int x = 0; x < sum.length; x++) {
            while (!leftIndex.isEmpty() && sum[x] <= sum[leftIndex.getLast()]) {
                leftIndex.removeLast();
            }

            while (!leftIndex.isEmpty() && sum[x] >= sum[leftIndex.getFirst()] + K) {
                shortest = Math.min(shortest, x - leftIndex.removeFirst());
            }

            leftIndex.addLast(x);
        }


        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }
}
