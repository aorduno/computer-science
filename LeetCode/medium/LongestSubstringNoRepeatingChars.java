package LeetCode.medium;

import CTCI.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringNoRepeatingChars {
    public static void main(String[] args) {
        LogUtils.logMessage(Character.MAX_VALUE + "");
        LogUtils.logMessage(Character.MIN_VALUE + "");
        testCase("abcabcbb");
        testCase("bbbbb");
        testCase("pwwkew");
        testCase("");
    }

    private static void testCase(String s) {
        LogUtils.logMessage("[[LongestSubstringNoRepeatingChars]] Computing longest length with no repeating chars");

        int longest = computeLongestSubstring(s);
        LogUtils.logMessage("Length of longest: " + longest);
    }


    private static int computeLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int longest = 0;
        Map<Character, Boolean> charsMap = new HashMap<>();
        while (right < s.length() && left < s.length()) {
            char current = s.charAt(right);
            boolean exists = charsMap.getOrDefault(current, false);
            if (exists) {
                char leftChar = s.charAt(left);
                while (leftChar != current) {
                    charsMap.put(leftChar, false);
                    left++;
                    leftChar = s.charAt(left);
                }
                charsMap.put(current, false);
                left++;
            } else {
                charsMap.put(current, true);
                right++;
            }

            longest = Math.max(longest, right - left);
        }

        return longest;
    }
}
