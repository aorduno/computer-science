package LeetCode;

import Helpers.Array;

public class FindPeakElement {
    public int findPeak(int[] nums) {
        int length = nums.length;
        if (length <= 1) { // one element
            return 0;
        } else if (length == 2) { // two elements
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }
        // rest
        int idx = find(nums, 0, length - 1);
        return nums[idx];
    }

    int find(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        if (isIndexPeak(left, nums)) {
            return left;
        }

        if (isIndexPeak(mid, nums)) {
            return mid;
        }

        if (isIndexPeak(right, nums)) {
            return right;
        }

        if (nums[mid - 1] > nums[mid]) {
            return find(nums, left, mid - 1);
        }

        return find(nums, mid + 1, right);
    }

    private boolean isIndexPeak(int idx, int[] nums) {
        if ((idx - 1 < 0) || (idx + 1 > nums.length - 1)) {
            return false;
        }

        int num = nums[idx];
        return nums[idx - 1] <= num && nums[idx + 1] <= num;
    }

    static void findAndPrint(int[] nums) {
        System.out.println("Finding peak in...");
        Array.print(nums);
        FindPeakElement findPeakElement = new FindPeakElement();
        System.out.println("Found: " + findPeakElement.findPeak(nums));
    }

    public static void main(String[] args) {
        findAndPrint(new int[]{1, 2, 3, 1});
        findAndPrint(new int[]{1, 2, 1, 3, 5, 6, 4});
        findAndPrint(new int[]{1});
        findAndPrint(new int[]{2, 1});
        findAndPrint(new int[]{3, 2, 1});
    }
}
