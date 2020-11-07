package CTCI.ArraysAndStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckPermutation {

    // time complexity o(n log n)
    // space complexity o(1) note that it's creating a temp char array for sorting so not really an o(1)
    // implementing merge sort to be able to sort a string would make this space o(1) for real!
    private static boolean doCheck(String word1, String word2) {
        // different lengths means no permutation...
        if (word1.length() != word2.length()) {
            return false;
        }
        String sorted1 = sortWord(word1.toCharArray());
        String sorted2 = sortWord(word2.toCharArray());

        for (int x = 0; x < sorted1.length(); x++) {
            char currentWord1 = sorted1.charAt(x);
            char currentWord2 = sorted2.charAt(x);
            if (currentWord1 != currentWord2) {
                return false;
            }
        }

        return true;
    }

    // time complexity o(n)
    // space complexity o(n)
    private static boolean doCheckTwo(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        Map<Character, Boolean> wordMap = new HashMap<>();
        for (int x = 0; x < word1.length(); x++) {
            wordMap.put(word1.charAt(x), true);
        }

        for (int x = 0; x < word2.length(); x++) {
            if (!wordMap.containsKey(word2.charAt(x))) {
                return false;
            }
        }

        return true;
    }

    // time complexity o(n), where n is the longest string
    // space complexity o(1)
    private static boolean doCheckThree(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        // assuming ASCII
        int[] charSet = new int[128];
        for (int x = 0; x < word1.length(); x++) {
            char current = word1.charAt(x);
            charSet[current]++;
        }

        for (int x = 0; x < word2.length(); x++) {
            char current = word2.charAt(x);
            charSet[current]--;
            if (charSet[current] < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
    }

    private static void testCase1() {
        evaluate("aaa", "aaa");
    }

    private static void testCase2() {
        evaluate("abc", "bca");
    }

    private static void testCase3() {
        evaluate("abafd", "fdaba");
    }

    private static void testCase4() {
        evaluate("abcde", "fedgh");
    }

    private static void testCase5() {
        evaluate("b", "a");
    }

    private static void evaluate(String word1, String word2) {
        System.out.println("1 - Is " + word2 + " a permutation of " + word1 + " : " + doCheck(word1, word2));
        System.out.println("2 - Is " + word2 + " a permutation of " + word1 + " : " + doCheckTwo(word1, word2));
        System.out.println("3 - Is " + word2 + " a permutation of " + word1 + " : " + doCheckThree(word1, word2));
    }

    // ideally i would apply merge sort for this
    // so we don't need an extra array of chars... which takes space complexity but meh...
    private static String sortWord(char[] characters) {
        Arrays.sort(characters);
        return String.valueOf(characters);
    }
}
