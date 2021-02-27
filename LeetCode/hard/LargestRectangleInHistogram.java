package LeetCode.hard;

import CTCI.LogUtils;

import java.util.Stack;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
//        testCase(new int[]{999, 999, 999, 999});
//        testCase(new int[]{0, 9});
//        testCase(new int[]{2, 4});
//        testCase(new int[]{2, 1, 5, 6, 2, 3, 1});
        testCase(new int[]{2, 1, 5, 6, 2, 3});
        testCase(new int[]{});
        testCase(new int[]{1, 0});
    }

    private static void testCase(int[] histogram) {
        LogUtils.logMessage("[[LargestRectangleInHistogram]] Finding out largest rectangle in histogram");
        int largestRectangle = computeLargestRectangleLinearBetter(histogram);
        LogUtils.logMessage("Largest: " + largestRectangle);
    }

    public static int computeLargestRectangleLinearBetter(int[] histogram) {
        Stack<Integer> bars = new Stack<>();
        int maxArea = 0;
        int[] areaByIndex = new int[histogram.length];
        for (int x = 0; x < histogram.length; x++) {
            int current = histogram[x];
            while (!bars.isEmpty() && histogram[bars.peek()] >= current) {
                bars.pop();
            }

            int leftLimit = bars.isEmpty() ? 0 : bars.peek() + 1;
            int currentArea = (x - leftLimit + 1) * current;
            areaByIndex[x] = currentArea;
            maxArea = Math.max(maxArea, areaByIndex[x]);
            bars.push(x);
        }

        bars.clear();
        for (int x = histogram.length - 1; x >= 0; x--) {
            int current = histogram[x];
            while (!bars.isEmpty() && histogram[bars.peek()] >= current) {
                bars.pop();
            }

            int rightLimit = bars.isEmpty() ? histogram.length - 1 : bars.peek() - 1;
            int currentArea = (rightLimit - x) * current;
            areaByIndex[x] += currentArea;
            maxArea = Math.max(maxArea, areaByIndex[x]);
            bars.push(x);
        }

        return maxArea;
    }

    private static int computeLargestRectangleLinear(int[] histogram) {
        int[] leftLimits = computeLeftLimits(histogram);
        int[] rightLimits = computeRightLimits(histogram);
        int largest = 0;
        for (int x = 0; x < histogram.length; x++) {
            int left = leftLimits[x];
            int right = rightLimits[x];
            largest = Math.max(largest, (right - left + 1) * histogram[x]);
        }

        return largest;
    }

    private static int[] computeRightLimits(int[] histogram) {
        Stack<Integer> lowerBars = new Stack<>();
        int[] rightLimits = new int[histogram.length];
        for (int x = histogram.length - 1; x >= 0; x--) {
            if (lowerBars.isEmpty()) {
                lowerBars.push(x);
                rightLimits[x] = histogram.length - 1;
                continue;
            }

            while (!lowerBars.isEmpty() && histogram[lowerBars.peek()] >= histogram[x]) {
                lowerBars.pop();
            }

            rightLimits[x] = lowerBars.isEmpty() ? histogram.length - 1 : lowerBars.peek() - 1;
            lowerBars.push(x);
        }

        return rightLimits;
    }

    private static int[] computeLeftLimits(int[] histogram) {
        Stack<Integer> lowerBars = new Stack<>();
        int[] leftLimits = new int[histogram.length];
        for (int x = 0; x < histogram.length; x++) {
            if (lowerBars.isEmpty()) {
                lowerBars.push(x);
                leftLimits[x] = 0;
                continue;
            }

            while (!lowerBars.isEmpty() && histogram[lowerBars.peek()] >= histogram[x]) {
                lowerBars.pop();
            }

            leftLimits[x] = lowerBars.isEmpty() ? 0 : lowerBars.peek() + 1;
            lowerBars.push(x);
        }

        return leftLimits;
    }

    private static int computeLargestRectangleBrute(int[] histogram) {
        int[] rectangleData = new int[histogram.length];
        int largest = 0;
        for (int x = 0; x < histogram.length; x++) {
            rectangleData[x] = histogram[x];
            int current = x + 1;
            while (current < histogram.length) {
                if (histogram[current] < histogram[x]) {
                    break;
                }

                rectangleData[x] += histogram[x];
                largest = Math.max(rectangleData[x], largest);
                current++;
            }
        }


        for (int x = histogram.length - 1; x >= 0; x--) {
            int current = x - 1;
            while (current >= 0) {
                if (histogram[current] < histogram[x]) {
                    break;
                }
                rectangleData[x] += histogram[x];
                largest = Math.max(rectangleData[x], largest);
                current--;
            }
        }

        return largest;
    }
}
