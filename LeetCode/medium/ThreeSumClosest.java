package medium;

import java.util.Arrays;

//https://leetcode.com/problems/3sum-closest/
public class ThreeSumClosest {
    public static void main(String[] args) {
//        [-3,-2,-5,3,-4]
//        -1
        test(new int[]{9999, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -3, -2, -5, 3, -4, -9999}, -1);
        test(new int[]{0, 2, 1, -3}, 1);
        test(new int[]{1, 1, -1}, 1);
        test(new int[]{1, 1, 1, 0}, 100);
        test(new int[]{0, 1, 2}, 0);
        test(new int[]{1, 1, 1, 0}, -100);
        test(new int[]{-1, 2, 1, -4}, 1);
        test(new int[]{-1, 2, 1, -4}, 2);
        test(new int[]{-1, 2, 1, -4}, -1);
        test(new int[]{0, 0, 0}, -1);
    }

    private static void test(int[] nums, int k) {
        System.out.println("[[3SumClosest]] Computing the closest sum of 3 numbers in given array: " + print(nums) + " that is closest to target: " + k);
        System.out.println("Closest: " + findClosestBetter(nums, k));
    }

    // time complexity o(n^2) -- this solution is very smart on how to move pointers but
    // space complexity o(n) -- assuming extra space is needed for sorting
    private static int findClosestBetter(int[] nums, int k) {
        Arrays.sort(nums);
        int closestSum = Integer.MAX_VALUE;
        int minDiff = Integer.MAX_VALUE;
        for (int x = 0; x < nums.length - 2; x++) {
            int left = x + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[x] + nums[left] + nums[right];
                int diff = Math.abs(k - sum);
                if (diff == 0) {
                    return nums[x] + nums[left] + nums[right];
                }

                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = sum;
                }

                if (sum > k) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return closestSum;
    }

    // time complexity o(n^3)
    // space complexity o(1) no additional space needed...
    private static int findClosestBrute(int[] nums, int k) {
        int left = 0;
        int closestSum = Integer.MAX_VALUE;
        int closestDiff = Integer.MAX_VALUE;
        while (left < nums.length - 2) {
            int mid = left + 1;
            while (mid < nums.length - 1) {
                int right = mid + 1;
                while (right < nums.length) {
                    int currentSum = nums[left] + nums[mid] + nums[right];
                    int diff = computeDifference(currentSum, k);
                    if (diff == 0) {
                        return currentSum; // done since only one solution
                    }

                    if (diff < closestDiff) {
                        closestDiff = diff;
                        closestSum = currentSum;
                    }

                    right++;
                }
                mid++;
            }
            left++;
        }

        return closestSum;
    }

    private static int computeDifference(int currentSum, int k) {
        if (sameSign(currentSum, k)) {
            return Math.abs(currentSum - k);
        }
        return Math.abs(currentSum) + Math.abs(k);
    }

    private static boolean sameSign(int currentSum, int k) {
        return currentSum == 0 && k == 0 || currentSum > 0 && k > 0 || currentSum < 0 && k < 0;
    }

    private static String print(int[] nums) {
        StringBuilder output = new StringBuilder();
        output.append("[");
        for (int num : nums) {
            output.append(num + ",");
        }

        output.deleteCharAt(output.length() - 1);
        output.append("]");
        return output.toString();
    }
}

