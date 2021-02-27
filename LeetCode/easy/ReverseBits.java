package LeetCode.easy;

import CTCI.LogUtils;

public class ReverseBits {
    public static void main(String[] args) {
        testCase(0b00000010100101000001111010011100);
    }

    private static void testCase(int number) {
        LogUtils.logMessage("[[ReverseBits]] reversing bits of given number " + Integer.toBinaryString(number), true);
        int reversed = doReverseNoLoop(number);
        LogUtils.logMessage("Reversed: " + Integer.toBinaryString(reversed), true);
    }

    private static int doReverse(int number) {
        int reversed = 0;
        int bit = 1;
        while (number != 0) {
            if ((number & 1) > 0) {
                reversed |= 1 << 32 - bit;
            }
            bit++;
            number >>>= 1;
        }

        return reversed;
    }

    private static int doReverseNoLoop(int number) {
        number = (number & 0xaaaaaaaa) >>> 1 | (number & 0x55555555) << 1;
        number = (number & 0xcccccccc) >>> 2 | (number & 0x33333333) << 2;
        number = (number & 0xf0f0f0f0) >>> 4 | (number & 0x0f0f0f0f) << 4;
        number = (number & 0xff00ff00) >>> 8 | (number & 0x00ff00ff) << 8;
        number = (number >>> 16) | (number << 16);
        return number;
    }
}
