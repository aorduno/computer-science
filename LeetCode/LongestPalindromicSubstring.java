package LeetCode;

public class LongestPalindromicSubstring {
    public String findDP(String str) {
        boolean[][] isPalidromeCoord = new boolean[str.length()][str.length()];
        String longest = "";
        for (int length = 1; length <= str.length(); length++) {
            for (int start = 0; start + length <= str.length(); start++) {
                int end = start + length - 1;
                if (start == end) { // 1 length
                    isPalidromeCoord[start][end] = true;
                } else if (start + 1 == end) { // 2 length
                    isPalidromeCoord[start][end] = str.charAt(start) == str.charAt(end);
                } else {
                    isPalidromeCoord[start][end] = str.charAt(start) == str.charAt(end) && isPalidromeCoord[start + 1][end - 1];
                }

                if (isPalidromeCoord[start][end] && length > longest.length()) {
                    longest = str.substring(start, end + 1);
                }
            }
        }

        return longest;
    }

    public String findOddEven(String s) {
        if (s.length() == 0) {
            return s;
        }

        int maxLength = 1; // if s length >= we know for sure each char is palindrome
        int low; // low index
        int high; // high index
        int start = 0; // longest palindrome start position
        for (int x = 1; x <= s.length(); x++) {
            // odds
            low = x - 1;
            high = x;
            while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
                int currentLength = high - low + 1;
                if (currentLength > maxLength) {
                    start = low;
                    maxLength = currentLength;
                }
                low--;
                high++;
            }

            // evens
            low = x - 1;
            high = x + 1;
            while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
                int currentLength = high - low + 1;
                if (currentLength > maxLength) {
                    start = low;
                    maxLength = currentLength;
                }
                low--;
                high++;
            }
        }

        return s.substring(start, start + maxLength);
    }

    static void doAndPrint(String s) {
        LongestPalindromicSubstring palindromicSubstrings = new LongestPalindromicSubstring();
        System.out.println("Found longest palindrome: " + palindromicSubstrings.findOddEven(s) + " of string: " + s);
    }

    public static void main(String[] args) {
        doAndPrint("aaa");
        doAndPrint("abc");
        doAndPrint("aabaa");
        doAndPrint("cbbd");
        doAndPrint("forgeeksskeegfor");
        doAndPrint("abaaba");
        doAndPrint("abababa");
        doAndPrint("abcbabcbabcba");
    }
}
