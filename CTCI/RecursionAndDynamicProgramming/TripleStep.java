package CTCI.RecursionAndDynamicProgramming;

import CTCI.LogUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TripleStep {
    public static void main(String[] args) {
        int c = 10;
        while (c < 15) {
            testCase(c, "recursive");
            testCase(c, "topDown");
            testCase(c, "TopDownSpace");
            c++;
        }
    }

    private static void testCase(int target, String computeType) {
        LogUtils.logMessage("[[TripleStep" + computeType + "]] Finding number of ways for running up the stairs to " + target + " step", true);
        int ways = 0;
        switch (computeType) {
            case "recursive":
                ways = calculatePossibleWaysRecursive(target);
                break;
            case "topDown":
                ways = calculatePossibleWaysTopDown(target);
                break;
            case "TopDownSpace":
                ways = calculatePossibleWaysTopDownLessSpace(target);
                break;
            default:
                // ignore...
        }

        LogUtils.logMessage("There are " + ways + " possible ways to get there", true);
    }

    private static int calculatePossibleWaysTopDownLessSpace(int target) {
        int[] stepsMemo = new int[target + 3];
        Arrays.fill(stepsMemo, -1);
        return calculateRecursivelyTopDownLessSpace(1, target, 0, stepsMemo) + calculateRecursivelyTopDownLessSpace(2, target, 0, stepsMemo) + calculateRecursivelyTopDownLessSpace(3, target, 0, stepsMemo);
    }

    private static int calculateRecursivelyTopDownLessSpace(int currentStep, int target, int waysCount, int[] stepsMemo) {
        int stepKeyMemo = stepsMemo[currentStep];
        if (stepKeyMemo != -1) { // already computed this...
            return stepKeyMemo;
        }

        if (currentStep > target) {
            stepsMemo[currentStep] = 0;
            return 0;
        }


        if (currentStep == target) {
            stepsMemo[currentStep] = waysCount + 1;
            return waysCount + 1;
        }

        return calculateRecursivelyTopDownLessSpace(currentStep + 1, target, waysCount, stepsMemo) + calculateRecursivelyTopDownLessSpace(currentStep + 2, target, waysCount, stepsMemo) + calculateRecursivelyTopDownLessSpace(currentStep + 3, target, waysCount, stepsMemo);
    }

    // dynamic programming -- top down
    private static int calculatePossibleWaysTopDown(int target) {
        Map<String, Integer> keyCountMap = new HashMap<>();
        return calculateRecursivelyTopDown(1, target, 0, keyCountMap, "1") + calculateRecursivelyTopDown(2, target, 0, keyCountMap, "2") + calculateRecursivelyTopDown(3, target, 0, keyCountMap, "3");
    }

    private static int calculateRecursivelyTopDown(int currentStep, int target, int waysCount, Map<String, Integer> keyCountMap, String stepKey) {
        String sorted = sortStepKey(stepKey);
        Integer stepKeyMemo = keyCountMap.getOrDefault(sorted, -1);
        if (stepKeyMemo != -1) { // already computed this...
            return stepKeyMemo;
        }

        if (currentStep > target) {
            keyCountMap.put(sorted, 0);
            return 0;
        }


        if (currentStep == target) {
            keyCountMap.put(sorted, waysCount + 1);
            return waysCount + 1;
        }

        return calculateRecursivelyTopDown(currentStep + 1, target, waysCount, keyCountMap, stepKey + "1") + calculateRecursivelyTopDown(currentStep + 2, target, waysCount, keyCountMap, stepKey + "2") + calculateRecursivelyTopDown(currentStep + 3, target, waysCount, keyCountMap, stepKey + "3");
    }

    private static String sortStepKey(String stepKey) {
        char[] chars = stepKey.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    // recursive
    private static int calculatePossibleWaysRecursive(int target) {
        return calculateRecursively(1, target, 0) + calculateRecursively(2, target, 0) + calculateRecursively(3, target, 0);
    }

    private static int calculateRecursively(int currentStep, int target, int waysCount) {
        if (currentStep > target) {
            return 0;
        }

        if (currentStep == target) {
            return waysCount + 1;
        }

        return calculateRecursively(currentStep + 1, target, waysCount) + calculateRecursively(currentStep + 2, target, waysCount) + calculateRecursively(currentStep + 3, target, waysCount);
    }
}
