package CTCI.BitManipulation;

public class Insertion {
    public static void main(String[] args) {
        int n = 0b10000000000;
        int m = 0b10011;
        testCase(n, m, 6, 2);

        n = 0b1000110100;
        m = 0b001011;
        testCase(n, m, 5, 0);
        testCase(n, m, 10, 5);
    }

    private static void testCase(int n, int m, int startAt, int endAt) {
        System.out.println("[[Insertion]] inserting " + Integer.toBinaryString(m) + " into " + Integer.toBinaryString(n) + " starting at " + endAt + " most significant bit and ending up at " + startAt);
        int insertResult = doInsert(n, m, startAt, endAt);
        System.out.println("Insertion result is: " + Integer.toBinaryString(insertResult));
    }

    // inserts m in n, starting at startAt and ends at endAt.
    // the way we do this is...
    // clear all bits that overlap from n
    // create the m version that fits
    // merge
    // time complexity o(1)
    // space complexity o(1)
    private static int doInsert(int n, int m, int startAt, int endAt) {
        if (startAt < endAt || startAt > 32 || endAt < 0) {
            return 0; // invalid
        }

        int toClearMask = getClearMask(startAt, endAt);
        n = n & toClearMask; // clear overlapping 0s...
        int mNewBits = getOrBits(m, endAt); // put m in the right place...
        return n | mNewBits;
    }

    // creates the mask used to clear the bits that m will take
    private static int getClearMask(int startAt, int endAt) {
        int ones = ~0;
        int left = (ones << (startAt + 1));
        int right = (1 << endAt) - 1;
        return left | right;
    }

    private static int getOrBits(int m, int endAt) {
        return m << endAt;
    }
}
