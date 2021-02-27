package LeetCode.medium;

import CTCI.LogUtils;

import java.util.HashMap;
import java.util.Map;

//Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M
//      Symbol       Value
//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000
//For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII,
// which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
//
//Roman numerals are usually written largest to smallest from left to right.
// However, the numeral for four is not IIII. Instead, the number four is written as IV.
// Because the one is before the five we subtract it making four.
// The same principle applies to the number nine, which is written as IX.
// There are six instances where subtraction is used:
//
//I can be placed before V (5) and X (10) to make 4 and 9.
//X can be placed before L (50) and C (100) to make 40 and 90.
//C can be placed before D (500) and M (1000) to make 400 and 900.
//Given an integer, convert it to a roman numeral.
public class IntegerToRoman {
    private static final Map<Integer, String> NUMBER_TO_ROMAN_MAP = new HashMap<>();

    static {
        NUMBER_TO_ROMAN_MAP.put(1, "I");
        NUMBER_TO_ROMAN_MAP.put(5, "V");
        NUMBER_TO_ROMAN_MAP.put(10, "X");
        NUMBER_TO_ROMAN_MAP.put(50, "L");
        NUMBER_TO_ROMAN_MAP.put(100, "C");
        NUMBER_TO_ROMAN_MAP.put(500, "D");
        NUMBER_TO_ROMAN_MAP.put(1000, "M");

        // subtract cases
        NUMBER_TO_ROMAN_MAP.put(4, "IV");
        NUMBER_TO_ROMAN_MAP.put(9, "IX");
        NUMBER_TO_ROMAN_MAP.put(40, "XL");
        NUMBER_TO_ROMAN_MAP.put(90, "XC");
        NUMBER_TO_ROMAN_MAP.put(400, "CD");
        NUMBER_TO_ROMAN_MAP.put(900, "CM");
    }

    public static void main(String[] args) {
        testCase(3, "III");
        testCase(4, "IV");
        testCase(9, "IX");
        testCase(58, "LVIII");
        testCase(1994, "MCMXCIV");
        testCase(428, "CDXXVIII");
    }

    private static void testCase(int number, String expected) {
        LogUtils.logMessage("[[IntegerToRoma]] Getting Roman representation of " + number, true);
        String roman = computeRomanSimple(number);
        LogUtils.logMessage("Expected roman is: " + expected + " || result: " + roman, true);
    }

    private static String computeRomanSimple(int number) {
        int[] scales = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuilder roman = new StringBuilder();
        for (int x = scales.length - 1; x >= 0; x--) {
            int scale = scales[x];
            while (number >= scale) {
                roman.append(romans[x]);
                number -= scale;
            }
        }

        return roman.toString();
    }

    private static String computeRoman(int number) {
        StringBuilder roman = new StringBuilder();
        int[] scales = getScales();
        int x = 0;
        while (x < scales.length) {
            int scale = scales[x];
            int division = number / scale;
            if (division >= 1) { // fits in this scale
                number -= (division * scale);
                while (division > 0) {
                    roman.append(NUMBER_TO_ROMAN_MAP.get(scale));
                    division--;
                }

                continue;
            } else if (isSubtractionInstance(number, scale)) {
                int toSubtract = getToSubtract(scale);
                number -= toSubtract;
                roman.append(NUMBER_TO_ROMAN_MAP.get(toSubtract));
            }

            x++;
        }

        return roman.toString();
    }

    private static int getToSubtract(int scale) {
        switch (scale) {
            case 1000:
                return 900;
            case 500:
                return 400;
            case 100:
                return 90;
            case 50:
                return 40;
            case 10:
                return 9;
            default:
                return 4;
        }
    }

    private static boolean isSubtractionInstance(int number, int scale) {
        boolean isTens = scale == 1000 || scale == 100 || scale == 10;
        boolean isFives = scale == 500 || scale == 50 || scale == 5;
        double threshold = isTens ? (scale * .9) : (scale * .8);
        return number % scale >= threshold;
    }

    private static int[] getScales() {
        return new int[]{1000, 500, 100, 50, 10, 5, 1};
    }
}
