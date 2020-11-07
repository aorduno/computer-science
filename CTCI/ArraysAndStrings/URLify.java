package CTCI.ArraysAndStrings;

public class URLify {
    private static String convert(String word, int length) {
        char[] chars = word.toCharArray();
        doConvert(chars, length);
        return String.valueOf(chars);
    }

    // for this we would start from the end of the string and build or string accordingly
    // time o(n)
    // space o(1)
    private static void doConvert(char[] chars, int length) {
        int numberOfSpaces = countSpaces(chars, length, ' ');
        int convertIndex = length - 1 + (numberOfSpaces * 2);

        for (int x = length - 1; x >= 0; x--) {
            char currentChar = chars[x];
            if (currentChar == ' ') { // replace
                chars[convertIndex] = '0';
                chars[convertIndex - 1] = '2';
                chars[convertIndex - 2] = '%';
                convertIndex -= 3;
            } else {
                chars[convertIndex] = chars[x];
                convertIndex--;
            }
        }
    }

    private static int countSpaces(char[] chars, int length, char target) {
        int count = 0;
        for (int x = 0; x < length; x++) {
            if (chars[x] == target) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    private static void testCase1() {
        evaluate("mr john smith    ", 13);
    }

    private static void testCase2() {
        evaluate("a b c d e f g              ", 13);
    }

    private static void testCase3() {
        evaluate("a     b     c                    ", 13);
    }

    private static void evaluate(String word, int length) {
        System.out.println("URLify version of word " + word + " is: " + convert(word, length));
    }
}
