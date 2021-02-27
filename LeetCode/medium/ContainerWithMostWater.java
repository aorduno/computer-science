package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

//Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines
// are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with
// the x-axis forms a container, such that the container contains the most water.
//
//        Notice that you may not slant the container.
public class ContainerWithMostWater {

    public static void main(String[] args) {
        testCase(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49);
        testCase(new int[]{1, 1}, 1);
        testCase(new int[]{4, 3, 2, 1, 4}, 16);
        testCase(new int[]{1, 2, 1}, 2);
    }

    private static void testCase(int[] pipes, int expectedValue) {
        LogUtils.logMessage("[[ContainerWithMostWater]] getting the maxArea for given pipes", true);
        ArrayUtils.print(pipes);

        int maxArea = computeMaxAreaOnePass(pipes);
        LogUtils.logMessage("Expected maxArea is: " + expectedValue + " and result is: " + maxArea, true);
    }

    // brute force
    // time o(n^2)
    // space o(1)
    private static int computeMaxArea(int[] pipes) {
        int length = pipes.length;
        int maxArea = 0;
        for (int start = 0; start < length; start++) {
            int end = start + 1;
            while (end < length) {
                int currentArea = computeArea(start, end, pipes);
                if (maxArea < currentArea) {
                    maxArea = currentArea;
                }
                end++;
            }
        }
        return maxArea;
    }

    private static int computeMaxAreaOnePass(int[] pipes) {
        int start = 0;
        int end = pipes.length - 1;
        int maxArea = 0;
        while (start < end) {
            int startHeight = pipes[start];
            int endHeight = pipes[end];
            int minPipe = Math.min(startHeight, endHeight);
            int currentArea = minPipe * (end - start);
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            if (startHeight < endHeight) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

    private static int computeArea(int start, int end, int[] pipes) {
        int startHeight = pipes[start];
        int endHeight = pipes[end];
        int minHeight = Math.min(startHeight, endHeight);
        return minHeight * (end - start);
    }
}
