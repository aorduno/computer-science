package CTCI.BitManipulation;

public class PairwiseSwap {
    public static void main(String[] args) {
        testCase(0b101010101010101010);
        testCase(0b010101010101010101);
        testCase(0b1111001010010100100);
    }

    private static void testCase(int num) {
        System.out.println("[[PairwiseSwap]] Swapping ood and even bits for given integer: " + num);
        String originalBinary = Integer.toBinaryString(num);
        int originalLength = originalBinary.length();
        String swappedBinary = Integer.toBinaryString(getSwappedPairs(num));
        int swappedLength = swappedBinary.length();
        int diff = originalLength - swappedLength;
        System.out.println("BinaryR: " + ((diff < 0) ? repeat("0", Math.abs(diff)) : "") + originalBinary);
        System.out.println("Swapped: " + ((diff > 0) ? repeat("0", diff) : "") + swappedBinary);
    }

    private static String repeat(String toRepeat, int times) {
        if (times == 0) {
            return "";
        }

        StringBuilder repeated = new StringBuilder(toRepeat);
        times -= 1;
        while (times != 0) {
            repeated.append(toRepeat);
            times--;
        }

        return repeated.toString();
    }

    // this is limited to 32 bits...
    private static int getSwappedPairs(int num) {
        int oddMask = 0b10101010101010101010101010101010;
        int eveMask = 0b01010101010101010101010101010101;
        return (num >> 1 & eveMask) | (num << 1 & oddMask);
    }
}
