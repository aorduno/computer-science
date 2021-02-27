package CTCI.SortingAndSearching;

import CTCI.LogUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MissingInt {
    public static void main(String[] args) {
        testCase("/Users/aorduno/dev/test/aordunotest/algorithms/src/main/resources/CTCI/SortingAndSearching/list_of_ints");
    }

    private static void testCase(String fileName) {
        LogUtils.logMessage("[[MissingInt]] finding missing int in file " + fileName, true);
        int missing = 0;
        try {
            missing = computeMissingInt(fileName);
        } catch (FileNotFoundException e) {
            LogUtils.logMessage("[[ERROR]] Something went wrong!", true);
            e.printStackTrace();
        }
        if (missing == -1) {
            LogUtils.logMessage("could not find missing int", true);
        } else {
            LogUtils.logMessage("missing int found: " + missing, true);
        }
    }

    private static int computeMissingInt(String fileName) throws FileNotFoundException {
        int batchSize = (1 << 20); // 2^20 bits = around 1 million

        int[] batches = computeCountPerBatch(fileName, batchSize);

        int batchWithMissingInt = computeBatchWithInt(batches, batchSize);
        if (batchWithMissingInt == -1) {
            return -1;
        }

        byte[] batchBitVector = generateBitVectorForBatch(fileName, batchWithMissingInt, batchSize);

        int missingOffset = computeMissingOffset(batchBitVector);

        return batchWithMissingInt * batchSize + missingOffset;
    }

    private static int computeMissingOffset(byte[] batchBitVector) {
        for (int x = 0; x < batchBitVector.length; x++) {
            byte current = batchBitVector[x];
            if (current != ~0) { // all 1s are good
                int missingIndex = findMissingInt(current);
                return (x * Byte.SIZE) + missingIndex;
            }
        }

        return -1;
    }

    private static int findMissingInt(byte vector) {
        for (int x = 0; x < Byte.SIZE; x++) {
            if (((1 << x) & vector) == 0) {
                return x;
            }
        }

        return -1;
    }

    private static byte[] generateBitVectorForBatch(String fileName, int batchWithMissingInt, int batchSize) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));

        int startAt = batchWithMissingInt * batchSize;
        int endAt = startAt + batchSize;
        byte[] bitVector = new byte[batchSize / Byte.SIZE];
        while (scanner.hasNextInt()) {
            int current = scanner.nextInt();
            if (current >= startAt && current < endAt) {
                int offset = (current - startAt);
                int bitMask = 1 << (offset % Byte.SIZE);
                bitVector[offset / Byte.SIZE] |= bitMask;
            }
        }
        scanner.close();

        return bitVector;
    }

    private static int computeBatchWithInt(int[] batches, int batchSize) {
        for (int x = 0; x < batches.length; x++) {
            if (batches[x] < batchSize) {
                return x;
            }
        }

        return -1;
    }

    private static int[] computeCountPerBatch(String fileName, int batchSize) throws FileNotFoundException {
        int noOfBatches = (Integer.MAX_VALUE / batchSize) + 1;
        int[] batches = new int[noOfBatches];

        Scanner scanner = new Scanner(new FileReader(fileName));
        while (scanner.hasNextInt()) {
            int current = scanner.nextInt();
            batches[current / batchSize]++;
        }
        scanner.close();

        return batches;
    }
}
