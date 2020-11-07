package CTCI.BitManipulation;

public class NextNumber {
    public static void main(String[] args) {
        testCase(0b10111100);
        testCase(0b1010011);
        testCase(0b0110101);
        testCase(0b11011001111100);
    }

    private static void testCase(int num) {
        System.out.println("[[NextPrevNumber]] Getting next and prev number for input " + num + " which binary representation is " + Integer.toBinaryString(num));
        int next = getNextNumber(num);
        System.out.println("Next number is: " + next + " binary: " + Integer.toBinaryString(next));
        int previous = getPreviousNumber(num);
        System.out.println("Previous number is: " + previous + " binary: " + Integer.toBinaryString(previous));
    }

    // for previous number we need to...
    // 1) count the right most 1s (c1)
    // 2) count the right most 0s (c0)
    // 3) c0 + c1 determines the bit we want to flip (from 1 to 0)
    // 4) put (c1 + 1) 1s right after p
    // 5) put c0 - 1 0s starting from 0
    private static int getPreviousNumber(int num) {
        int temp = num;
        int c1 = 0;
        while (((temp & 1) == 1) && temp > 0) {
            c1++;
            temp = temp >> 1;
        }

        int c0 = 0;
        while (((temp & 1) == 0) && temp > 0) {
            c0++;
            temp = temp >> 1;
        }

        int p = c0 + c1;
        int zerosMask = ~((1 << (p + 1)) - 1); // 1s followed by 0s from p to 0
        int onesMask = ((1 << (c1 + 1)) - 1) << (c0 - 1);
        return (num & zerosMask) | onesMask;
    }

    // for getting next number we need to
    // 1) count the rightmost 0s (c0)
    // 2) count the rightmost 1s (c1)
    // 3) c0 + c1 determines the bit we want to flip (from 0 to 1)
    // 4) put 0s from p to position 0
    // 5) put 1s from 0 to c1 - 1 position
    private static int getNextNumber(int num) {
        int temp = num;
        int c0 = 0;
        while (((temp & 1) == 0) && temp > 0) {
            c0++;
            temp = temp >> 1;
        }

        int c1 = 0;
        while ((temp & 1) == 1 && temp > 0) {
            c1++;
            temp = temp >> 1;
        }

        int p = c1 + c0;
        num = num | (1 << p); // flip p
        num = num & ~((1 << p) - 1); // clear bits from p to 0
        return num | (1 << (c1 - 1)) - 1; // insert 1s from 0 to c1 - 1
    }
}
