package CTCI.ArraysAndStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsUnique {
    // initial result
    // time complexity o(n)
    // space complexity o(n)
    private static boolean doCheck(String word) {
        Map<Character, Boolean> charContainedMap = new HashMap<>();
        for (int x = 0; x < word.length(); x++) {
            char c = word.charAt(x);
            if (charContainedMap.containsKey(c)) {
                return false;
            }

            charContainedMap.put(c, true);
        }

        return true;
    }

    // no data structure needed...
    // time complexity o(n log n)
    // space complexity o(1) -- note that it's creating a temp char array so not really an o(1)
    // implementing merge sort to be able to sort a string would make this space o(1) for real!
    private static boolean doCheckTwo(String word) {
        String sorted = sortWord(word.toCharArray());
        char previous = 0;
        for (int x = 0; x < sorted.length(); x++) {
            char current = sorted.charAt(x);
            if (previous != 0 && current == previous) {
                return false;
            }

            previous = current;
        }

        return true;
    }

    // assuming is ASCII
    // time complexity o(n)
    // space complexity o(1)
    private static boolean doCheckThree(String word) {
        boolean[] charSet = new boolean[128];
        for (int x = 0; x < word.length(); x++) {
            char character = word.charAt(x);
            if (charSet[character]) {
                return false;
            }

            charSet[character] = true;
        }

        return true;
    }

    private static boolean doCheckBitVector(String word) {
        int bitsToCheck = 0;
        for (int x = 0; x < word.length(); x++) {
            char character = word.charAt(x);
            // assuming only letters from a - z
            int letterValue = character - 'a';
            if ((bitsToCheck & (1 << letterValue)) > 0) {
                return false;
            }
            bitsToCheck |= (1 << letterValue);
        }

        return true;
    }

    // ideally i would apply merge sort for this
    // so we don't need an extra array of chars... which takes space complexity but meh...
    private static String sortWord(char[] characters) {
        Arrays.sort(characters);
        return String.valueOf(characters);
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
    }

    private static void evaluate(String word) {
        System.out.println("3 - does word " + word + " contains unique characters? --> " + IsUnique.doCheckThree(word));
        System.out.println("BitVector - does word " + word + " contains unique characters? --> " + IsUnique.doCheckBitVector(word));
    }

    private static void testCase5() {
        evaluate("abacd");
    }

    private static void testCase4() {
        evaluate("abcd");
    }

    private static void testCase3() {
        evaluate("aaa");
    }

    private static void testCase2() {
        evaluate("");
    }

    private static void testCase1() {
        evaluate("abcdea");
    }
}
