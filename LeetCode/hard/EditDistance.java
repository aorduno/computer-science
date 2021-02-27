package LeetCode.hard;

import CTCI.LogUtils;

import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {
        testCase("dinitrophenylhydrazine", "acetylphenylhydrazine");
        testCase("dinitrophenylhydrazine", "benzalphenylhydrazone");
        testCase("sea", "eat");
        testCase("", "a");
        testCase("horse", "ros");
        testCase("intention", "execution");
    }

    private static void testCase(String word1, String word2) {
        LogUtils.logMessage("[[EditDistance]] Finding out numbers of moves to convert word " + word1 + " into " + word2);

        int moves = computeMovesToConvertRecursiveBottomUp(word1, word2);
        LogUtils.logMessage("Moves: " + moves);
    }

    private static int computeMovesToConvertRecursiveBottomUp(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return doRecursivelyWithMemoBottomUp(word1, word2, 0, 0, memo);
    }

    private static int doRecursivelyWithMemoBottomUp(String word1, String word2, int word1Index, int word2Index, int[][] memo) {
        if (word1Index == word1.length() || word2Index == word2.length()) {
            return (word1.length() - word1Index) + (word2.length() - word2Index);
        }

        if (word1.charAt(word1Index) == word2.charAt(word2Index)) {
            return doRecursivelyWithMemoBottomUp(word1, word2, word1Index + 1, word2Index + 1, memo);
        }

        if (memo[word1Index][word2Index] != -1) {
            return memo[word1Index][word2Index];
        }
        int movesWithAdd = 1 + doRecursivelyWithMemoBottomUp(word1, word2, word1Index, word2Index + 1, memo);
        int movesWithDelete = 1 + doRecursivelyWithMemoBottomUp(word1, word2, word1Index + 1, word2Index, memo);
        int movesWithReplace = 1 + doRecursivelyWithMemoBottomUp(word1, word2, word1Index + 1, word2Index + 1, memo);
        int min = Math.min(movesWithAdd, Math.min(movesWithDelete, movesWithReplace));
        memo[word1Index][word2Index] = min;
        return min;
    }

    private static int doRecursivelyNoMemo(String word1, String word2, int indexW1, int indexW2) {
        if (indexW1 == 0 || indexW2 == 0) {
            // base case -- either remove  all that's left in word1 OR add all left from word2
            return Math.max(indexW1, indexW2);
        }

        if (word1.charAt(indexW1 - 1) == word2.charAt(indexW2 - 1)) {
            // next is same so no move needed...
            return doRecursivelyNoMemo(word1, word2, indexW1 - 1, indexW2 - 1);
        }

        int movesWithAdd = 1 + doRecursivelyNoMemo(word1, word2, indexW1, indexW2 - 1);
        int movesWithRemove = 1 + doRecursivelyNoMemo(word1, word2, indexW1 - 1, indexW2);
        int movesWithReplace = 1 + doRecursivelyNoMemo(word1, word2, indexW1 - 1, indexW2 - 1);

        return Math.min(movesWithAdd, Math.min(movesWithRemove, movesWithReplace));
    }

    private static int computeMovesToConvertRecursive(String word1, String word2) {
//        return doRecursivelyNoMemo(word1, word2, word1._length(), word2._length());
        int[][] memo = createMemo(word1, word2);
        return doRecursivelyWithMemo(word1, word2, word1.length(), word2.length(), memo);
    }

    private static int[][] createMemo(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        for (int x = 0; x < memo.length; x++) {
            Arrays.fill(memo[x], -1);
        }
        return memo;
    }

    private static int doRecursivelyWithMemo(String word1, String word2, int indexW1, int indexW2, int[][] memo) {
        if (indexW1 == 0 || indexW2 == 0) {
            // base case -- either remove  all that's left in word1 OR add all left from word2
            return Math.max(indexW1, indexW2);
        }

        if (word1.charAt(indexW1 - 1) == word2.charAt(indexW2 - 1)) {
            // next is same so no move needed...
            return doRecursivelyWithMemo(word1, word2, indexW1 - 1, indexW2 - 1, memo);
        }

        if (memo[indexW1][indexW2 - 1] == -1) {
            memo[indexW1][indexW2 - 1] = 1 + doRecursivelyWithMemo(word1, word2, indexW1, indexW2 - 1, memo);
        }

        if (memo[indexW1 - 1][indexW2] == -1) {
            memo[indexW1 - 1][indexW2] = 1 + doRecursivelyWithMemo(word1, word2, indexW1 - 1, indexW2, memo);
        }

        if (memo[indexW1 - 1][indexW2 - 1] == -1) {
            memo[indexW1 - 1][indexW2 - 1] = 1 + doRecursivelyWithMemo(word1, word2, indexW1 - 1, indexW2 - 1, memo);
        }
        return Math.min(memo[indexW1][indexW2 - 1], Math.min(memo[indexW1 - 1][indexW2], memo[indexW1 - 1][indexW2 - 1]));
    }

    private static int computeMovesToConvert(String word1, String word2) {
        return doComputeMovesToConvert(word1, word2, 0);
    }

    private static int doComputeMovesToConvert(String word1, String word2, int currentMoves) {
        if (word1.equals(word2)) {
            return currentMoves;
        }

        int diffIndex = computeDiffIndex(word1, word2);
        int withReplace = Integer.MAX_VALUE;
        if (diffIndex != -1 && diffIndex != -2) {
            withReplace = doComputeMovesToConvert(computeNewWordForReplace(diffIndex, word1, word2), word2, currentMoves + 1);
        }

        int withRemove = Integer.MAX_VALUE;
        if (diffIndex == -2) {
            withRemove = doComputeMovesToConvert(word1.substring(0, word1.length() - 1), word2, currentMoves + 1);
        } else if (diffIndex != -1) {
            int indexInWord1 = findInWord(word1, word2.charAt(diffIndex), diffIndex);
            if (indexInWord1 != -1) {
                withRemove = doComputeMovesToConvert(computeNewWordForRemoval(diffIndex, word1, word2, indexInWord1), word2, currentMoves + (indexInWord1 - diffIndex));
            }
        }

        int withAdd = Integer.MAX_VALUE;
        if (diffIndex == -1) {
            char toAdd = word2.charAt(word1.length());
            withAdd = doComputeMovesToConvert(word1 + toAdd, word2, currentMoves + 1);
        } else if (diffIndex != -2) {
            withAdd = doComputeMovesToConvert(computeNewWordForAdd(diffIndex, word1, word2), word2, currentMoves + 1);
        }

        return Math.min(Math.min(withReplace, withRemove), withAdd);
    }

    private static int findInWord(String word1, char c, int startAt) {
        while (startAt < word1.length()) {
            if (word1.charAt(startAt) == c) {
                return startAt;
            }
            startAt++;
        }

        return -1;
    }

    private static String computeNewWordForAdd(int diffIndex, String word1, String word2) {
        char toAmend = word2.charAt(diffIndex);
        return word1.substring(0, diffIndex) + toAmend + word1.substring(diffIndex);
    }

    private static String computeNewWordForRemoval(int diffIndex, String word1, String word2, int indexInWord1) {
        char toAmend = word2.charAt(diffIndex);
        return word1.substring(0, diffIndex) + toAmend + word1.substring(indexInWord1 + 1);
    }

    private static String computeNewWordForReplace(int diffIndex, String word1, String word2) {
        return word1.substring(0, diffIndex) + word2.charAt(diffIndex) + word1.substring(diffIndex + 1);
    }

    private static int computeDiffIndex(String word1, String word2) {
        int x = 0;
        int word1Length = word1.length();
        int word2Length = word2.length();
        while (x < word1Length && x < word2Length && word1.charAt(x) == word2.charAt(x)) {
            x++;
        }

        if (word1Length != word2Length && (x == word1Length || x == word2Length)) {
            // prefixes are the same... so
            // -1 means add at the end of w1
            // -2 means remove from last of w2
            return word1Length < word2Length ? -1 : -2;
        }

        return x;
    }
}
