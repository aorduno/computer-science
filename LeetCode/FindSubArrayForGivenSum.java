package LeetCode;

import Helpers.Array;

public class FindSubArrayForGivenSum {
    // o(n^2)
    private int[] find(int[] numbers, int target) {
        int[] coords = new int[2];
        coords[0] = -1;
        coords[1] = -1;
        int sum = 0;
        for (int x = 0; x < numbers.length; x++) {
            for (int y = x; y < numbers.length; y++) {
                sum += numbers[y];
                if (sum == target) {
                    coords[0] = x;
                    coords[1] = y;
                }

                if (sum > target) {
                    break;
                }
            }

            sum = 0; // reset
        }

        return coords;
    }

    // worst case o(2n) = o(n)
    private int[] findBetter(int[] numbers, int target) {
        int tail = 0;
        int head = 0;
        int sum = 0;
        while (tail < numbers.length && sum < target) {
            sum += numbers[head];
            while (sum > target && tail < numbers.length) { // move tail forward and substract
                sum -= numbers[tail];
                tail++;
            }

            if (sum == target) {
                break;
            }

            head++;
        }

        return new int[]{tail, head};
    }

    static void printAndFind(int[] numbers, int target) {
        FindSubArrayForGivenSum findSubArrayForGivenSum = new FindSubArrayForGivenSum();
        int[] ints = findSubArrayForGivenSum.findBetter(numbers, target);
        System.out.println("Finding in array:");
        Array.print(numbers);
        if (ints[0] == -1) {
            System.out.println("Sum of: " + target + " was not found as a continuous sub array");
        } else {
            System.out.println("Sum of: " + target + " was found! Coords:");
            Array.print(ints);
        }
    }

    public static void main(String[] args) {
        printAndFind(new int[]{1, 4, 20, 3, 10, 5}, 33);
        printAndFind(new int[]{1, 4, 0, 0, 3, 10, 5}, 7);
        printAndFind(new int[]{1, 4}, 0);
        printAndFind(new int[]{15, 2, 4, 8, 9, 5, 10, 23}, 23);
    }
}
