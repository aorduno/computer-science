package LeetCode.medium;

import CTCI.LogUtils;

public class MinDeciBinaryPartitions {
    public static void main(String[] args) {
        testCase("0");
        testCase("32");
        testCase("10");
        testCase("2734620983070918234627346209830709182346");
    }

    private static void testCase(String n) {
        LogUtils.logMessage("[[MinDeciBinaryPartitions]] Computing min deci binary partitions for input: " + n);
        int minPartitions = minPartitions(n);
        LogUtils.logMessage("MinPartitions: " + minPartitions);
    }

    static int minPartitions(String n) {
        return computeMinPartition(n);
    }

    static int computeMinPartition(String n) {
        int max = 0;
        for (int x = 0; x < n.length(); x++) {
            max = Math.max(n.charAt(x) - '0', max);
            if (max == 9) {
                break;
            }
        }

        return max;
    }
}
