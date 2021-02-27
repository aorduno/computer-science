package LeetCode.easy;

import CTCI.LogUtils;

import java.util.Arrays;
import java.util.Map;

public class ClimbingStairs {
    public static void main(String[] args) {
        testCase(1);
        testCase(2);
        testCase(3);
        testCase(5);
        testCase(10);
    }

    private static void testCase(int steps) {
        LogUtils.logMessage("[[ClimbingStarts]] computing how many DISTINCTS ways you can climb up " + steps + " steps");
        int ways = computeSteps(steps);
        LogUtils.logMessage("Result: " + ways);
    }

    private static int computeSteps(int steps) {
//        Map<String, Boolean> keyMemoMap = new HashMap<>();
        return computeRecursively(0, steps);
    }

    private static int computeRecursively(int currentStep, int target) {
        if (currentStep == target) {
            return 1;
        }

        if (currentStep > target) {
            return 0;
        }

        return computeRecursively(currentStep + 1, target) +
                computeRecursively(currentStep + 2, target);
    }

    private static void addEntry(String key, Map<String, Boolean> keyMemoMap) {
        char[] keyChars = key.toCharArray();
        Arrays.sort(keyChars);
        keyMemoMap.put(String.valueOf(keyChars), true);
    }

    private static boolean hasProcessed(String key, Map<String, Boolean> keyMemoMap) {
        char[] keyChars = key.toCharArray();
        Arrays.sort(keyChars);
        String sorted = keyChars.toString();
        return keyMemoMap.containsKey(sorted);
    }
}
