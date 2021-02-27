package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Arrays;

public class GasStation {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
        testCase(new int[]{2, 3, 4}, new int[]{3, 4, 3});
        testCase(new int[]{1}, new int[]{5});
        testCase(new int[]{}, new int[]{});
    }

    private static void testCase(int[] gas, int[] cost) {
        LogUtils.logMessage("[[GasStation]] Computing the start gas station to start at in order to travel around the whole circuit once");
        LogUtils.logMessage("Gas:");
        ArrayUtils.print(gas);

        LogUtils.logMessage("Cost:");
        ArrayUtils.print(cost);

        int index = canCompleteGreedy(gas, cost);
        LogUtils.logMessage("Index: " + index);
    }

    private static int canCompleteGreedy(int[] gas, int[] cost) {
        int totalGas = 0;
        int currentGas = 0;
        int index = 0;
        for (int x = 0; x < gas.length; x++) {
            totalGas += gas[x] - cost[x];
            currentGas += gas[x] - cost[x];
            if (currentGas < 0) {
                currentGas = 0;
                index = -1;
            } else if (index == -1) {
                index = x;
            }
        }

        return totalGas >= 0 ? index : -1;
    }

    private static int canCompleteDpWithMemo(int[] gas, int[] cost) {
        int[] travelMemo = new int[gas.length]; // stores bad path
        Arrays.fill(travelMemo, -1);
        for (int x = 0; x < gas.length; x++) {
            if (checkRecursively(gas, cost, x, 0, 0, travelMemo)) {
                return x;
            }
        }
        return -1;
    }

    private static boolean checkRecursively(int[] gas, int[] cost, int currentIndex, int currentGas, int traveled, int[] travelMemo) {
        if (travelMemo[currentIndex] != -1 && currentGas <= travelMemo[currentIndex]) {
            return false;
        }
        if (traveled == gas.length) {
            return true;
        }

        int gasAvail = currentGas + gas[currentIndex] - cost[currentIndex];
        if (gasAvail < 0) {
            travelMemo[currentIndex] = currentGas;
            return false;
        }

        int nextIndex = currentIndex + 1 == gas.length ? 0 : currentIndex + 1;
        boolean isGoodPath = checkRecursively(gas, cost, nextIndex, gasAvail, traveled + 1);
        travelMemo[currentIndex] = !isGoodPath ? currentGas : -1;
        return isGoodPath;
    }

    private static int canCompleteDp(int[] gas, int[] cost) {
        for (int x = 0; x < gas.length; x++) {
            if (checkRecursively(gas, cost, x, 0, 0)) {
                return x;
            }
        }
        return -1;
    }

    private static boolean checkRecursively(int[] gas, int[] cost, int currentIndex, int currentGas, int traveled) {
        if (traveled == gas.length) {
            return true;
        }

        int gasAvail = currentGas + gas[currentIndex] - cost[currentIndex];
        if (gasAvail < 0) {
            return false;
        }

        int nextIndex = currentIndex + 1 == gas.length ? 0 : currentIndex + 1;
        return checkRecursively(gas, cost, nextIndex, gasAvail, traveled + 1);
    }

    private static int canCompleteBrute(int[] gas, int[] cost) {
        int startAt = 0;
        while (startAt < gas.length) {
            int current = startAt;
            int traveled = 0;
            int currentGas = 0;
            while (traveled < gas.length && currentGas >= 0) {
                currentGas += gas[current] - cost[current];
                traveled++;
                current++;
                if (current == gas.length) { // cycle...
                    current = 0;
                }
            }

            if (traveled == gas.length && currentGas >= 0) {
                return startAt;
            }
            startAt++;
        }

        return -1;
    }
}
