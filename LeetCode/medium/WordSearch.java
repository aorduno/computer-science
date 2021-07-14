package LeetCode.medium;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.Arrays;

public class WordSearch {
    public static void main(String[] args) {
        testCase(new char[][]{}, "asd");
        char[][] testGrid1 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        testCase(testGrid1, "ABCCED");
        char[][] testGrid2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        testCase(testGrid2, "EEDA");
        char[][] testGrid3 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        testCase(testGrid3, "abc");
    }

    private static void testCase(char[][] board, String word) {
        LogUtils.logMessage("[[WordSearch]] Looking if given word " + word + " is present in given board");
        ArrayUtils.printMatrix(board);

        boolean isPresent = searchWordWithPrune(board, word);
        LogUtils.logMessage("isPresent: " + isPresent);
    }

    private static boolean searchWordWithPrune(char[][] board, String word) {
        int rows = board.length;
        if (rows == 0) {
            return false;
        }

        int cols = board[0].length;
        if (word.length() > rows * cols) {
            return false;
        }

        // prune
        int[] charCount = new int[57 + 1];
        Arrays.fill(charCount, -1);
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                int index = board[row][col] - 'A';
                charCount[index]++;
            }
        }

        for (int x = 0; x < word.length(); x++) {
            if (charCount[word.charAt(x) - 'A'] == -1) {
                return false;
            }
            charCount[word.charAt(x) - 'A']--;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (searchWordRecursively(row, col, 0, word, board, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean searchWord(char[][] board, String word) {
        if (board.length == 0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (searchWordRecursively(row, col, 0, word, board, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean searchWordRecursively(int row, int col, int currentChar, String word, char[][] board, boolean[][] visited) {
        if (currentChar == word.length()) {
            return true;
        }

        if (row >= board.length || row < 0 || col >= board[row].length || col < 0) { // out of bounds..
            return false;
        }

        if (board[row][col] == '*' || visited[row][col]) { // pruned or visited
            return false;
        }

        if (board[row][col] != word.charAt(currentChar)) { // invalid
            visited[row][col] = false;
            return false;
        }

        visited[row][col] = true;
        if (searchWordRecursively(row + 1, col, currentChar + 1, word, board, visited)
                || searchWordRecursively(row - 1, col, currentChar + 1, word, board, visited)
                || searchWordRecursively(row, col + 1, currentChar + 1, word, board, visited)
                || searchWordRecursively(row, col - 1, currentChar + 1, word, board, visited)) {
            return true;
        }

        visited[row][col] = false;
        return false;
    }
}
