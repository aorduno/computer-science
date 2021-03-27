package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.*;

public class QueueReconstructionByHeight {
    private static Comparator<int[]> PEOPLE_COMPARATOR = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] == o2[1] ? o2[0] - o1[0] : o1[1] - o2[1];
        }
    };

    private static Comparator<int[]> PEOPLE_COMPARATOR_SIMPLE = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
        }
    };

    public static void main(String[] args) {
        testCase(new int[][]{
                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        });

        testCase(new int[][]{
                {9, 0}, {7, 0}, {1, 9}, {3, 0}, {2, 7}, {5, 3}, {6, 0}, {3, 4}, {6, 2}, {5, 2}
        });

        testCase(new int[][]{
                {2, 4}, {3, 4}, {9, 0}, {0, 6}, {7, 1}, {6, 0}, {7, 3}, {2, 5}, {1, 1}, {8, 0}
        });
    }

    private static void testCase(int[][] people) {
        LogUtils.logMessage("[[QueueReconstructionByHeight]] Reconstructing queue by height for given people");
        ArrayUtils.printMatrix(people);

        int[][] queue = reconstructGreedySimple(people);
        print(queue);

    }

    private static int[][] reconstructGreedySimple(int[][] people) {
        // sort by height DESC and people in front ASC
        Arrays.sort(people, PEOPLE_COMPARATOR_SIMPLE);
        LinkedList<int[]> queue = new LinkedList<>();
        for (int[] person : people) {
            queue.add(person[1], person);
        }

        return convert(queue);
    }

    private static int[][] reconstructGreedy(int[][] people) {
        // sort by people in front
        Arrays.sort(people, PEOPLE_COMPARATOR);
        LinkedList<int[]> queue = new LinkedList<>();
        for (int[] person : people) {
            int addAt = findAddAt(person, queue);
            queue.add(addAt, person);
        }
        return convert(queue);
    }

    private static int[][] convert(LinkedList<int[]> queue) {
        int[][] result = new int[queue.size()][2];
        int x = 0;
        while (!queue.isEmpty()) {
            result[x] = queue.removeFirst();
            x++;
        }

        return result;
    }

    private static int findAddAt(int[] person, LinkedList<int[]> linkedList) {
        if (linkedList.isEmpty()) {
            return 0;
        }

        int addAt = 0;
        int height = person[0];
        int peopleInFront = person[1];
        int foundInFront = 0;
        while (addAt < linkedList.size()) {
            int currentHeight = linkedList.get(addAt)[0];
            if (peopleInFront == 0 && currentHeight >= height) {
                break;
            }

            if (peopleInFront > 0 && peopleInFront == foundInFront) {
                break;
            }

            if (currentHeight >= height) {
                foundInFront++;
            }

            addAt++;
        }
        return addAt;
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
