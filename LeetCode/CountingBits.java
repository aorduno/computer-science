package LeetCode;

import Helpers.Array;

import java.util.HashMap;
import java.util.Map;

public class CountingBits {
    // time complexity - o(n) || 2 loops
    // space complexity - o(n) || 2 data structures
    private int[] countBits(int num) {
        Map<Integer, Integer> mainBitsMap = new HashMap<>();
        int index = 0;
        int bit = 0;
        // identify the main bits
        while (bit <= num) {
            bit = (int) Math.pow(2, index);
            mainBitsMap.put(bit, 1);
            index++;
        }

        int[] bitsCount = new int[num + 1];
        bitsCount[0] = 0;
        int lastMainBitIdx = 0;
        for (int x = 1; x <= num; x++) {
            if (mainBitsMap.containsKey(x)) {
                bitsCount[x] = 1;
                lastMainBitIdx = x;
                continue;
            }

            int diff = x - lastMainBitIdx;
            bitsCount[x] = bitsCount[lastMainBitIdx] + bitsCount[diff];
        }
        return bitsCount;
    }

    // time complexity - o(n) || 1 loop
    // space complexity - o(n) || 1 data structure
    private int[] countBitsImproved(int num) {
        int[] bitsCount = new int[num + 1];
        bitsCount[0] = 0;
        int mainBitIndex = 0;
        int currentMainBit = (int) Math.pow(2, mainBitIndex);
        for (int x = 1; x <= num; x++) {
            if ((x - currentMainBit) >= currentMainBit) {
                mainBitIndex++;
                currentMainBit = (int) Math.pow(2, mainBitIndex);
            }

            if (currentMainBit == x) {
                bitsCount[x] = 1;
                continue;
            }

            int diff = x - currentMainBit;
            bitsCount[x] = bitsCount[currentMainBit] + bitsCount[diff];
        }

        return bitsCount;
    }

    public static void main(String[] args) {
        CountingBits countingBits = new CountingBits();
        int[] ints = countingBits.countBits(15);
        Array.print(ints);

        ints = countingBits.countBits(5);
        Array.print(ints);

        ints = countingBits.countBits(1);
        Array.print(ints);

        ints = countingBits.countBits(0);
        Array.print(ints);

        ints = countingBits.countBits(100);
        Array.print(ints);

        // improved
        ints = countingBits.countBitsImproved(15);
        Array.print(ints);

        ints = countingBits.countBitsImproved(5);
        Array.print(ints);

        ints = countingBits.countBitsImproved(1);
        Array.print(ints);

        ints = countingBits.countBitsImproved(0);
        Array.print(ints);

        ints = countingBits.countBitsImproved(100);
        Array.print(ints);
    }

}
