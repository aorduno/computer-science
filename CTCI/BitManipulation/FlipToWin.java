package CTCI.BitManipulation;

public class FlipToWin {
    public static void main(String[] args) {
        testCase(0b11011101111);
        testCase(0b111111101010010011110111);
    }

    private static void testCase(int num) {
        System.out.println("[[FlipToWin]]Finding the longest sequence of 1s that could be created flipping one bit in " + Integer.toBinaryString(num));
        System.out.println("Result: " + getLongestSequence(num));
    }

    // time complexity o(n) where n is the sequence of bits in the num
    // space complexity o(1) no extra space needed
    private static int getLongestSequence(int num) {
        int lastBit = getMostSignificativeBit(num);
        int outer = 0;
        int longestSequence = 0;
        while (outer < lastBit) {
            int inner = outer;
            int currentSequence = 0;
            int flips = 1;
            int lastBitOff = 0;
            while (inner < lastBit) {
                if (isBitOn(num, inner)) {
                    currentSequence++;
                } else if (flips > 0) {
                    lastBitOff = inner;
                    flips--;
                    currentSequence++;
                } else {
                    // move outer until this point to keep computing
                    outer = lastBitOff;
                    break;
                }
                inner++;
            }
            // record...
            if (currentSequence > longestSequence) {
                longestSequence = currentSequence;
            }

            outer++;
        }
        return longestSequence;
    }

    private static boolean isBitOn(int num, int index) {
        int bit = 1 << index;
        return (num & bit) > 0;
    }

    private static int getMostSignificativeBit(int num) {
        int bits = 0;
        while (num > 0) {
            bits++;
            num = num >> 1;
        }

        return bits;
    }
}
