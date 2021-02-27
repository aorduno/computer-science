package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Arrays;

public class JumpGame {
    public static void main(String[] args) {
        testCase(new int[]{2, 3, 1, 1, 4});
        testCase(new int[]{3, 2, 1, 0, 4});
        testCase(new int[]{0});
        testCase(new int[]{1});
        testCase(new int[]{0, 1});
    }

    private static void testCase(int[] nums) {
        LogUtils.logMessage("[[JumpGame]] Computing if with given jumps we can reach last position");
        ArrayUtils.print(nums);

        boolean canJump = checkJumpsGreedy(nums);
        LogUtils.logMessage("CanJump: " + canJump);
    }

    private static boolean checkJumpsGreedy(int[] nums) {
        int lastGood = nums.length - 1;
        for (int x = nums.length - 1; x >= 0; x--) {
            if (x + nums[x] >= lastGood) {
                lastGood = x;
            }
        }
        return lastGood == 0;
    }

    private static boolean checkJumpsMemoBottomUp(int[] nums) {
        int[] memo = new int[nums.length];

        memo[nums.length - 1] = 1;
        for (int x = nums.length - 2; x >= 0; x--) {
            int nextJumps = Math.min(nums.length - 1, x + nums[x]);
            for (int y = x + 1; y <= nextJumps; y++) {
                if (memo[y] == 1) {
                    memo[x] = 1;
                    break;
                }
            }
        }
        return memo[0] == 1;
    }

    private static boolean checkJumpsMemoTopDown(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return checkRecursivelyWithMemo(nums, 0, nums.length - 1, memo);
    }

    private static boolean checkRecursivelyWithMemo(int[] nums, int currentIndex, int targetIndex, int[] memo) {
        if (memo[currentIndex] != -1) {
            return memo[currentIndex] == 1;
        }

        if (currentIndex == targetIndex) {
            memo[currentIndex] = 1;
            return true;
        }

        int currentJump = 1;
        while (currentJump <= nums[currentIndex]) {
            int nextJump = Math.min(currentIndex + currentJump, targetIndex);
            boolean isSolution = checkRecursively(nums, nextJump, targetIndex);
            memo[nextJump] = isSolution ? 1 : 0;
            if (isSolution) {
                return true;
            }
            currentJump++;
        }

        memo[currentIndex] = 0;
        return false;
    }

    private static boolean checkJumps(int[] nums) {
        return checkRecursively(nums, 0, nums.length - 1);
    }

    private static boolean checkRecursively(int[] nums, int currentIndex, int targetIndex) {
        if (currentIndex >= targetIndex) {
            return true;
        }

        int jumps = nums[currentIndex];
        while (jumps > 0) {
            if (checkRecursively(nums, currentIndex + jumps, targetIndex)) {
                return true;
            }
            jumps--;
        }

        return false;
    }
}
