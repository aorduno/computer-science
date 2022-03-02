package LeetCode.medium;
import java.util.HashMap;
import java.util.Map;

public class LongestHappyString {
    public static void main(String[] args) {
        test(1, 1, 7);
        test(2, 3, 8);
        test(4, 42, 7);
        test(15, 10, 20);
        test(25, 88, 98);
    }

    private static void test(int a, int b, int c) {
        System.out.println("[[LongestHappyString]] Generating longest happy string for a = " + a + ", b = " + b + ", c = " + c);
        String result = generateLHSGreedy(a, b, c);
        System.out.println("Result: " + result);
        System.out.println("Length: " + result.length() + " maxLength: " + (a + b + c));
    }

    // time complexity o(n) being n = a + b +c
    // space complexity o(1)
    private static String generateLHSGreedy(int a, int b, int c) {
        StringBuilder longest = new StringBuilder();
        int max = a + b + c;
        while (longest.length() < max) {
            char excluded = computeExcluded(longest); // check for exclusions...
            char highest = pickHighest('a' == excluded ? 0 : a, 'b' == excluded ? 0 : b, 'c' == excluded ? 0 : c);
            // can not add more -- done
            if (highest == 'x') {
                break;
            }
            // append
            longest.append(highest);

            // update
            switch (highest) {
                case 'a':
                    a--;
                    break;
                case 'b':
                    b--;
                    break;
                case 'c':
                    c--;
            }
        }
        return longest.toString();
    }

    private static char pickHighest(int a, int b, int c) {
        if (a == 0 && b == 0 && c == 0) {
            return 'x';
        }
        if (a >= b) {
            return a >= c ? 'a' : 'c';
        }

        return b >= c ? 'b' : 'c';
    }

    private static char computeExcluded(StringBuilder longest) {
        if (longest.length() >= 2 && longest.charAt(longest.length() - 1) == longest.charAt(longest.length() - 2)) {
            return longest.charAt(longest.length() - 1);
        }
        return 'x';
    }

    // bottom up approach with memo -- i have a feeling the memo part can be improved but meh!
    private static String generateLHSWithMemo(int a, int b, int c) {
        Map<String, String> keyToLongestMap = new HashMap<>();
        return generateRecursivelyWithMemo(a, b, c, "", null, keyToLongestMap);
    }

    private static String generateRecursivelyWithMemo(int a, int b, int c, String current, Character toAppend, Map<String, String> keyToLongestMap) {
        if (a == 0 && b == 0 && c == 0) {
            return String.valueOf(toAppend);
        }
        String lastTwoChars = computeLastTwoChars(current, toAppend);
        String key = lastTwoChars + "|" + a + "|" + b + "|" + c;
        if (keyToLongestMap.containsKey(key)) {
            return keyToLongestMap.get(key);
        }

        String longestA = a > 0 && isHappy(lastTwoChars + 'a') ? generateRecursivelyWithMemo(a - 1, b, c, lastTwoChars, 'a', keyToLongestMap) : "";
        String longestB = b > 0 && isHappy(lastTwoChars + 'b') ? generateRecursivelyWithMemo(a, b - 1, c, lastTwoChars, 'b', keyToLongestMap) : "";
        String longestC = c > 0 && isHappy(lastTwoChars + 'c') ? generateRecursivelyWithMemo(a, b, c - 1, lastTwoChars, 'c', keyToLongestMap) : "";
        String longest = pickLongest(longestA, longestB, longestC);
        keyToLongestMap.put(key, longest);
        return (toAppend != null ? toAppend : "") + longest;
    }

    private static String computeLastTwoChars(String current, Character toAppend) {
        String lastTwo = current.length() > 0 ? String.valueOf(current.charAt(current.length() - 1)) : "";
        if (toAppend != null) {
            lastTwo += toAppend;
        }
        return lastTwo;
    }

    private static String buildKey(int a, int b, int c, String current) {
        String key = a + "|" + b + "|" + c;
        if (current.length() >= 2) {
            key = current.charAt(current.length() - 2) + "|" + current.charAt(current.length() - 1) + "|" + key;
        } else if (current.length() == 1) {
            key = current.charAt(0) + "|" + key;
        }
        return key;
    }

    // brute force/top down approach
    // time complexity o(3 ^ n) where n = a * b * c
    // space complexity o(h) where h = a + b + c
    private static String generateLHS(int a, int b, int c) {
        return generateRecursively(a, b, c, "", a + b + c);
    }

    private static String generateRecursively(int a, int b, int c, String current, int maxLength) {
        if ((a == 0 && b == 0 && c == 0)
                || (current.length() == maxLength)
                || !isHappy(current)) {
            return !isHappy(current) ? current.substring(0, current.length() - 1) : current;
        }

        String longestA = a > 0 ? generateRecursively(a - 1, b, c, current + "a", maxLength) : current;
        String longestB = b > 0 ? generateRecursively(a, b - 1, c, current + "b", maxLength) : current;
        String longestC = c > 0 ? generateRecursively(a, b, c - 1, current + "c", maxLength) : current;

        return pickLongest(longestA, longestB, longestC);
    }

    private static boolean isHappy(String current) {
        if (current.length() < 3) {
            return true;
        }

        char lastChar = current.charAt(current.length() - 1);
        return !(lastChar == current.charAt(current.length() - 2) && lastChar == current.charAt(current.length() - 3));
    }

    private static boolean canAdd(String current, char letter, int occurrences) {
        return occurrences - 1 >= 0 && !hasLastCharacterDouble(current, letter);
    }

    private static boolean hasLastCharacterDouble(String current, char letter) {
        if (current.length() < 2) {
            return false;
        }
        return current.charAt(current.length() - 1) == letter && current.charAt(current.length() - 2) == letter;
    }

    private static String pickLongest(String longestA, String longestB, String longestC) {
        if (longestA.length() > longestB.length()) {
            return longestA.length() > longestC.length() ? longestA : longestC;
        }
        return longestB.length() > longestC.length() ? longestB : longestC;
    }
}
