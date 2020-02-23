package LeetCode;

import Helpers.Array;

public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        int binary = 0;
        for (int num : nums) {
            if ((binary & num) == num) {
                return num;
            }
            binary |= num;
        }

        return 0;
    }

    public static void doFind(int[] nums) {
        Array.print(nums);
        FindDuplicate fd = new FindDuplicate();
        System.out.println("Dupe number for array is: " + fd.findDuplicate(nums));
    }

    public static void main(String[] args) {
        doFind(new int[]{1, 3, 4, 2, 2});
        doFind(new int[]{3, 1, 3, 4, 2});
    }
}
