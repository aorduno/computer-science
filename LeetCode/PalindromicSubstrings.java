package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubstrings {

    private int countSubstringsDp(String s) {
        int[][] palindromeData = new int[s.length()][s.length()];
        int count = 0;
        for (int x = 1; x <= s.length(); x++) {
            for (int y = 0; y + x <= s.length(); y++) {
                int end = y + x - 1;
                char current = s.charAt(y);
                char atEnd = s.charAt(end);
                boolean currentIsEnd = current == atEnd;
                if (y == end) {
                    palindromeData[y][end] = 1;
                    count++;
                    continue;
                } else if (y + 1 == end) {
                    palindromeData[y][end] = currentIsEnd ? 1 : 0;
                } else if (currentIsEnd && palindromeData[y + 1][end - 1] == 1) {
                    palindromeData[y][end] = 1;
                } else {
                    palindromeData[y][end] = 0;
                }


                if (palindromeData[y][end] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public int countSubstringsNaive(String s) {
        List<String> palindromes = new ArrayList<>();
        for (int x = 0; x < s.length(); x++) {
            for (int y = 0; y + x < s.length(); y++) {
                String current = x > 0 ? s.substring(y, y + x + 1) : String.valueOf(s.charAt(y));
                if (isPalindrome(current)) {
                    palindromes.add(current);
                }
            }
        }

        return palindromes.size();
    }

    private boolean isPalindrome(String current) {
        StringBuilder backwards = new StringBuilder();
        for (int x = current.length() - 1; x >= 0; x--) {
            backwards.append(current.charAt(x));
        }

        return backwards.toString().equals(current);
    }

    static void doAndPrint(String s) {
        PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
        System.out.println("Found " + palindromicSubstrings.countSubstringsDp(s) + " palindromes in string: " + s);
    }

    public static void main(String[] args) {
        doAndPrint("aaa");
        doAndPrint("abc");
        doAndPrint("aabaa");
    }
}
