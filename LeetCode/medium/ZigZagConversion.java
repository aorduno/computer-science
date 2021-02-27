package LeetCode.medium;

import CTCI.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    public static void main(String[] args) {
        testCase("PAYPALISHIRING", 3);
        testCase("PAYPALISHIRING", 4);
        testCase("A", 1);
    }

    private static void testCase(String text, int rows) {
        LogUtils.logMessage("[[ZigZagConversion]] Converting to zig zag format given string: " + text + " for " + rows + " rows", true);
        String converted = doConvertBetter(text, rows);

        LogUtils.logMessage("Converted: " + converted, true);
    }

    private static String doConvertBetter(String string, int rows) {
        if (rows == 1) {
            return string;
        }
        int stringLength = string.length();
        int totalPositions = rows + (rows - 2);
        StringBuilder converted = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (int x = 0; x + row < string.length(); x += totalPositions) {
                converted.append(string.charAt(row + x));
                if (addSecondChar(rows, row, x, totalPositions, stringLength)) {
                    converted.append(string.charAt(x + totalPositions - row));
                }
            }
        }

        return converted.toString();
    }

    private static boolean addSecondChar(int rows, int row, int x, int totalPositions, int stringLength) {
        return row != 0 && row != rows - 1 && ((x + totalPositions - row) < stringLength);
    }

    private static String doConvert(String text, int rows) {
        List<List<Character>> charsByRows = createLists(rows);
        int x = 0;
        while (x < text.length()) {
            List<Character> characters = charsByRows.get(computeListIndex(x, rows));
            characters.add(text.charAt(x));
            x++;
        }

        StringBuilder output = new StringBuilder();
        for (List<Character> charsByRow : charsByRows) {
            for (Character character : charsByRow) {
                output.append(character);
            }
        }

        return output.toString();
    }

    private static List<List<Character>> createLists(int rows) {
        List<List<Character>> charsByRows = new ArrayList<>();
        while (rows > 0) {
            charsByRows.add(new ArrayList<>());
            rows--;
        }

        return charsByRows;
    }

    private static int computeListIndex(int stringIndex, int rows) {
        if (rows <= 2) {
            return stringIndex % rows;
        }
        int midRows = rows - 2;
        int totalPositions = rows + midRows;
        int position = stringIndex % totalPositions;
        if (position <= rows - 1) {
            return position;
        }

        return midRows - (position - rows);
    }
}
