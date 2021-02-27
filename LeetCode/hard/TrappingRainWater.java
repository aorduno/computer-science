package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Stack;

public class TrappingRainWater {
    public static void main(String[] args) {
        testCase(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        testCase(new int[]{4, 2, 0, 3, 2, 5});
    }

    private static void testCase(int[] elevationMap) {
        LogUtils.logMessage("[[TrappingRainWater]]Finding out units of water trapped in given elevation map", true);
        ArrayUtils.print(elevationMap);

        int units = computeUnitsTrappedTwoPointers(elevationMap);
        LogUtils.logMessage("Result: " + units, true);
    }

    private static int computeUnitsTrappedTwoPointers(int[] elevationMap) {
        int left = 0;
        int right = elevationMap.length - 1;
        int maxLevelLeft = 0;
        int maxLevelRight = 0;
        int units = 0;
        while (left < right) {
            int levelLeft = elevationMap[left];
            int levelRight = elevationMap[right];
            if (levelLeft < levelRight) {
                if (levelLeft < maxLevelLeft) {
                    units += maxLevelLeft - levelLeft;
                } else {
                    maxLevelLeft = Math.max(maxLevelLeft, levelLeft);
                }

                left++;
            } else {
                if (levelRight < maxLevelRight) {
                    units += maxLevelRight - levelRight;
                } else {
                    maxLevelRight = Math.max(maxLevelRight, levelRight);
                }

                right--;
            }
        }

        return units;
    }

    private static int computeUnitsTrappedStack(int[] elevationMap) {
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        int units = 0;
        while (current < elevationMap.length) {
            int currentHeight = elevationMap[current];
            while (!stack.isEmpty() && currentHeight > elevationMap[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) { // no more walls...
                    break;
                }

                int distance = current - stack.peek() - 1;
                int trappedHeight = Math.min(current, elevationMap[stack.peek()]) - elevationMap[top];
                units += distance * trappedHeight;
            }
            // update
            stack.add(current);
            current++;
        }

        return units;
    }

    // time o(n)
    // space o(n)
    private static int computeUnitsTrappedDynamicProgramming(int[] elevationMap) {
        int[] leftWalls = computeLeftWalls(elevationMap);
        int[] rightWalls = computeRightWalls(elevationMap);
        int units = 0;
        for (int x = 1; x < elevationMap.length; x++) {
            int current = elevationMap[x];
            int tallestLeft = leftWalls[x];
            int tallestRight = rightWalls[x];
            if (tallestLeft > current && tallestRight > current) {
                units += Math.min(tallestLeft, tallestRight) - current;
            }
        }
        return units;
    }

    private static int[] computeRightWalls(int[] elevationMap) {
        int tallestValue = -1;
        int[] rightWalls = new int[elevationMap.length];
        for (int x = elevationMap.length - 1; x >= 0; x--) {
            tallestValue = Math.max(elevationMap[x], tallestValue);
            rightWalls[x] = tallestValue;
        }

        return rightWalls;
    }

    private static int[] computeLeftWalls(int[] elevationMap) {
        int tallestValue = -1;
        int[] leftWalls = new int[elevationMap.length];
        for (int x = 0; x < elevationMap.length; x++) {
            tallestValue = Math.max(elevationMap[x], tallestValue);
            leftWalls[x] = tallestValue;
        }

        return leftWalls;
    }

    // time o(n^2)
    // space o(1)
    private static int computeUnitsTrapped(int[] elevationMap) {
        int units = 0;
        for (int x = 0; x < elevationMap.length; x++) {
            int current = elevationMap[x];
            int tallestLeft = -1;
            int left = x - 1;
            while (left >= 0) {
                tallestLeft = Math.max(elevationMap[left], tallestLeft);
                left--;
            }

            int tallestRight = -1;
            int right = x + 1;
            while (right < elevationMap.length) {
                tallestRight = Math.max(elevationMap[right], tallestRight);
                right++;
            }

            if (tallestLeft > current && tallestRight > current) {
                units += Math.min(tallestLeft, tallestRight) - current;
            }
        }

        return units;
    }
}
