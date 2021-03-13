package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        testCase(new int[][]{
                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        });
        testCase(new int[][]{
                {9, 0}, {7, 0}, {1, 9}, {3, 0}, {2, 7}, {5, 3}, {6, 0}, {3, 4}, {6, 2}, {5, 2}
        });
    }

    private static void testCase(int[][] people) {
        LogUtils.logMessage("[[QueueReconstructionByHeight]] Reconstructing queue by height for given people");
        ArrayUtils.printMatrix(people);

        int[][] queue = reconstructBrute(people);
        print(queue);

    }

    private static void print(int[][] queue) {
        LogUtils.logMessage("Queue is: ");
        ArrayUtils.printMatrix(queue);
    }

    // create all permutations...
    // loop through them...
    // validate to find the right one...
    private static int[][] reconstructBrute(int[][] people) {
        List<int[][]> permutations = new ArrayList<>();
        int[][] current = new int[people.length][2];
        Arrays.fill(current, null);
        permute(people, 0, 0, current, new boolean[people.length], permutations);
        return findValid(permutations);
    }

    private static int[][] findValid(List<int[][]> permutations) {
        for (int[][] permutation : permutations) {
            if (isValid(permutation)) {
                return permutation;
            }
        }

        return null;
    }

    private static boolean isValid(int[][] people) {
        for (int x = people.length - 1; x >= 0; x--) {
            int height = people[x][0];
            int peopleToFind = people[x][1];
            int found = 0;
            for (int y = x - 1; y >= 0; y--) {
                if (height <= people[y][0]) {
                    found++;
                }
            }

            if (peopleToFind != found) {
                return false;
            }
        }

        return true;
    }

    private static void permute(int[][] people, int addAt, int added, int[][] currentAdded, boolean[] addedInStack, List<int[][]> permutations) {
        if (added == people.length) {
            permutations.add(currentAdded);
            return;
        }

        for (int x = 0; x < people.length; x++) {
            int[] current = people[x];
            if (addedInStack[x]) {
                continue;
            }

            int[][] clone = clone(currentAdded);
            clone[addAt] = current;
            addedInStack[x] = true;
            permute(people, addAt + 1, added + 1, clone, addedInStack, permutations);
            addedInStack[x] = false;
        }
    }

    private static int[][] clone(int[][] people) {
        int[][] clone = new int[people.length][2];
        System.arraycopy(people, 0, clone, 0, people.length);
        return clone;
    }
}
