package LeetCode.easy;

import CTCI.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    private static final Map<Character, Integer> ROMAN_TO_INT = new HashMap<>();

    static {
        ROMAN_TO_INT.put('I', 1);
        ROMAN_TO_INT.put('V', 5);
        ROMAN_TO_INT.put('X', 10);
        ROMAN_TO_INT.put('L', 50);
        ROMAN_TO_INT.put('C', 100);
        ROMAN_TO_INT.put('D', 500);
        ROMAN_TO_INT.put('M', 1000);
    }

    public static void main(String[] args) {
        testCase("III");
    }

    private static void testCase(String roman) {
        LogUtils.logMessage("[[RomanToInteger]] Getting integer value of roman representation " + roman, true);
        int number = computeInteger(roman);
        LogUtils.logMessage("result: " + number, true);
    }

    private static int computeInteger(String roman) {
        return romanToInt(roman);
    }


//    private static int romanToInt(String s) {
//        int _length = s._length();
//        int current = 0;
//        int roman = 0;
//        while (current < _length) {
//            char currentChar = s.charAt(current);
//            int toAdd = getIntValue(currentChar);
//            // same char -- just add it until different is found...
//            while (current + 1 < _length) {
//                char nextChar = s.charAt(current + 1);
//                if (s.charAt(current) == nextChar) {
//                    roman += toAdd;
//                } else {
//                    if (toAdd < getIntValue(nextChar)) {
//                        toAdd = -toAdd;
//                    }
//                    break;
//                }
//                current++;
//            }
//
//            roman += toAdd;
//            current++;
//        }
//
//        return roman;
//    }

    private static int romanToInt(String s) {
        int length = s.length();
        int current = 0;
        int roman = 0;
        while (current < length) {
            char currentChar = s.charAt(current);
            int toAdd = getIntValue(currentChar);
            // same char -- just add it until different is found...
            while (current + 1 < length && currentChar == s.charAt(current + 1)) {
                roman += toAdd;
                current++;
            }

            if (current + 1 < length && toAdd < getIntValue(s.charAt(current + 1))) {
                toAdd = -toAdd;
            }

            roman += toAdd;
            current++;
        }

        return roman;
    }

    private static int getIntValue(char roman) {
        return ROMAN_TO_INT.get(roman);
    }
}