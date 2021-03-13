package LeetCode.easy;

import CTCI.LogUtils;

import java.util.Arrays;

public class MaxProfit {
    public static void main(String[] args) {
        testCase(new int[]{7, 1, 5, 3, 6, 4});
        testCase(new int[]{1, 2, 3, 4, 5});
        testCase(new int[]{5, 4, 3, 2, 1});
        testCase(new int[]{});
        testCase(new int[]{0});
        testCase(new int[]{7, 1});
        testCase(new int[]{1, 1});
    }

    private static void testCase(int[] nums) {
        int maxProfit = computeSimple(nums);
        LogUtils.logMessage("result: " + maxProfit);
    }

    private static int computeSimple(int[] nums) {
        int profit = 0;
        for (int x = 1; x < nums.length; x++) {
            if (nums[x] > nums[x - 1]) {
                profit += nums[x] - nums[x - 1];
            }
        }

        return profit;
    }

    private static int computePeakValleys(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int index = 0;
        int valley;
        int peak;
        int profit = 0;
        while (index < nums.length - 1) {
            while (index < nums.length - 1 && nums[index] >= nums[index + 1]) {
                index++;
            }

            valley = nums[index];
            while (index < nums.length - 1 && nums[index] <= nums[index + 1]) {
                index++;
            }
            peak = nums[index];
            profit += peak - valley;
            index++;
        }

        return profit;
    }

    // top down
    // time o(n^2)
    // space o(n)
    private static int computeWithMemo(int[] prices) {
        int[] memo = new int[prices.length];
        Arrays.fill(memo, -1);
        return computeNaiveRecursivelyWithMemo(prices, 0, memo);
    }

    private static int computeNaiveRecursivelyWithMemo(int[] prices, int currentIndex, int[] memo) {
        if (currentIndex == prices.length) {
            return 0;
        }

        int max = 0;
        for (int x = currentIndex; x < prices.length; x++) {
            for (int y = x + 1; y < prices.length; y++) {
                if (prices[x] < prices[y]) {
                    int nextIndexMax = y + 1 < prices.length ? memo[y + 1] : 0;
                    if (nextIndexMax == -1) {
                        nextIndexMax = computeNaiveRecursivelyWithMemo(prices, y + 1, memo);
                    }
                    int profit = prices[y] - prices[x] + nextIndexMax;
                    max = Math.max(profit, max);
                }
            }
        }

        memo[currentIndex] = max;
        return max;
    }

    // naive
    // time complexity o(n^n)
    // space complexity o(n)
    static int computeNaive(int[] prices) {
        return computeNaiveRecursively(prices, 0);
    }

    static int computeNaiveRecursively(int[] prices, int currentIndex) {
        if (currentIndex == prices.length) {
            return 0;
        }

        int max = 0;
        for (int x = currentIndex; x < prices.length; x++) {
            for (int y = x + 1; y < prices.length; y++) {
                if (prices[x] < prices[y]) {
                    int profit = prices[y] - prices[x] + computeNaiveRecursively(prices, y + 1);
                    max = Math.max(profit, max);
                }
            }
        }

        return max;
    }
}
