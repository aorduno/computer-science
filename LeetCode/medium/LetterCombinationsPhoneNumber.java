package LeetCode.medium;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumber {
    private static final Map<Character, char[]> CHAR_TO_NUMBERS_MAP = new HashMap<>();

    static {
        CHAR_TO_NUMBERS_MAP.put('2', new char[]{'a', 'b', 'c'});
        CHAR_TO_NUMBERS_MAP.put('3', new char[]{'d', 'e', 'f'});
        CHAR_TO_NUMBERS_MAP.put('4', new char[]{'g', 'h', 'i'});
        CHAR_TO_NUMBERS_MAP.put('5', new char[]{'j', 'k', 'l'});
        CHAR_TO_NUMBERS_MAP.put('6', new char[]{'m', 'n', 'o'});
        CHAR_TO_NUMBERS_MAP.put('7', new char[]{'p', 'q', 'r', 's'});
        CHAR_TO_NUMBERS_MAP.put('8', new char[]{'t', 'u', 'v'});
        CHAR_TO_NUMBERS_MAP.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    public static void main(String[] args) {
        testCase("2");
        testCase("23");
        testCase("2345");
        testCase("69");
        testCase("");
    }

    private static void testCase(String numbers) {
        LogUtils.logMessage("[[LetterPhoneCombinations]] Getting all letter combinations for given phone numbers: " + numbers, true);
        List<String> combinations = computeCombinationsRecursively(numbers);

        LogUtils.logMessage("Found " + combinations.size() + " combinations", true);
        print(combinations);
    }

    private static List<String> computeCombinationsRecursively(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        computeRecursively("", digits, combinations);
        return combinations;
    }

    private static void computeRecursively(String currentCombination, String remainingDigits, List<String> combinations) {
        if (remainingDigits.isEmpty()) {
            combinations.add(currentCombination);
            return;
        }

        char[] lettersFromNumber = getLettersFromNumber(remainingDigits.charAt(0));
        for (char aLettersFromNumber : lettersFromNumber) {
            computeRecursively(currentCombination + String.valueOf(aLettersFromNumber), remainingDigits.length() > 1 ? remainingDigits.substring(1) : "", combinations);
        }
    }

    private static void print(List<String> combinations) {
        for (String combination : combinations) {
            LogUtils.logMessage(combination, true);
        }
    }

    // we know digits _length is >= 0 and <= 4 so...
    private static List<String> computeCombinationsBruteForce(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }

        char[] firstLetterNumbers = getLettersFromNumber(digits.charAt(0));
        List<String> combinations = new ArrayList<>();
        for (int w = 0; w < firstLetterNumbers.length; w++) {
            if (digits.length() > 1) {
                char[] secondLetterNumbers = getLettersFromNumber(digits.charAt(1));
                for (int x = 0; x < secondLetterNumbers.length; x++) {
                    if (digits.length() > 2) {
                        char[] thirdLetterNumbers = getLettersFromNumber(digits.charAt(2));
                        for (int y = 0; y < thirdLetterNumbers.length; y++) {
                            if (digits.length() > 3) {
                                char[] fourthLetterNumbers = getLettersFromNumber(digits.charAt(3));
                                for (int z = 0; z < fourthLetterNumbers.length; z++) {
                                    combinations.add(String.valueOf(firstLetterNumbers[w] + "" + secondLetterNumbers[x] + "" + thirdLetterNumbers[y] + "" + fourthLetterNumbers[z]));
                                }
                            } else {
                                combinations.add(String.valueOf(firstLetterNumbers[w] + "" + secondLetterNumbers[x] + "" + thirdLetterNumbers[y]));
                            }
                        }
                    } else {
                        combinations.add(String.valueOf(firstLetterNumbers[w] + "" + secondLetterNumbers[x]));
                    }
                }
            } else {
                combinations.add(String.valueOf(firstLetterNumbers[w]));
            }
        }

        return combinations;
    }

    private static char[] getLettersFromNumber(char letter) {
        return CHAR_TO_NUMBERS_MAP.get(letter);
    }
}
