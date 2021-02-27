package CTCI.ArraysAndStrings;

// this checks if a string is permutation of a palindrome
// for this we're assuming...
// 1) a palidrome is a string that is it read the same from left to right and viceversa
// 2) a permutation is a string that has the same letters but in different order
// 3) thus, we can say a string is a permutation of palindrome...
// 3.1) if string _length is even then all letter count should be even
// 3.2) if string _length is odd then we could have at most one odd letter count
// 4) this is cover by the simple rule of... there's at most one odd letter count
// 5) this assumes only letters from english vocabulary...
public class PalindromePermutation {
    private static boolean doCheck(String word) {
        int[] letterCount = calculateLetterCount(word);
        boolean foundOdd = false;
        for (int count : letterCount) {
            if (count == 0) {
                continue;
            }
            if (count % 2 > 0) {
                if (foundOdd) { // not a palindrome!
                    return false;
                }

                foundOdd = true;
            }
        }

        return true;
    }

    // we need to...
    private static boolean doCheckBitVector(String word) {
        // get bit vector
        int evenOddBitVector = calculateBitVector(word);
        // check if only one bit is on -- bits on are for odds...
        return checkOnlyOneBitOn(evenOddBitVector);
    }

    private static boolean checkOnlyOneBitOn(int evenOddBitVector) {
        return ((evenOddBitVector - 1) & evenOddBitVector) == 0;
    }

    // calculates a bit vector where evens are 0 and odds are 1
    private static int calculateBitVector(String word) {
        int bitVector = 0;
        for (int x = 0; x < word.length(); x++) {
            int charNumber = getCharNumber(word, x);
            if (charNumber < 0) { // ignore
                continue;
            }

            int charBit = (1 << charNumber);
            bitVector ^= charBit;
        }

        return bitVector;
    }

    private static int[] calculateLetterCount(String word) {
        int[] letterCount = new int['z' - 'a'];
        for (int x = 0; x < word.length(); x++) {
            int charNumber = getCharNumber(word, x);
            if (charNumber < 0) { // ignore this
                continue;
            }

            letterCount[charNumber]++;
        }

        return letterCount;
    }

    private static int getCharNumber(String word, int x) {
        char currentLetter = word.charAt(x);
        return currentLetter - 'a';
    }

    public static void main(String[] args) {
        testCase1();
    }

    private static void testCase1() {
        evaluate("aaa");
        evaluate("aabbaac");
        evaluate("tacocat");
        evaluate("coattac");
        evaluate("abcabc");
        evaluate("aabcdabcdxxx");
        evaluate("abcdabcdxxx");
        evaluate("ab");
    }

    private static void evaluate(String word) {
        System.out.println("1 - Is word " + word + " a permutation of palindrome?  " + doCheck(word));
        System.out.println("BitVector - Is word " + word + " a permutation of palindrome?  " + doCheckBitVector(word));
    }
}
