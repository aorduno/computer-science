import java.util.HashMap;
import java.util.Map;

//
//Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
//In other words, return true if one of s1's permutations is the substring of s2.
public class PermutationInString {
    public static void main(String[] args) {
        test("ab", "eidbaooo");
        test("ab", "eidboaooo");
        test("abc", "asdlkjqwebac");
        test("abc", "abcasjhdjk");
    }

    private static void test(String s1, String s2) {
        System.out.println("[[PermutationInString]] Checking if " + s2 + " contains any permutation of " + s1);
        System.out.println("Result: " + computeInclusionWindowSliding(s1, s2));
    }

    // time complexity o(n * 26) n = length of s2
    // space complexity o(2 * 26) = o(52) = o(1) constant
    private static boolean computeInclusionWindowSliding(String s1, String s2) {
        int s1Length = s1.length();
        if (s1Length > s2.length() || s1Length == 0) {
            return false;
        }

        int[] targetCount = createCharCount(s1);
        int left = 0;
        int right = left + s1Length - 1;
        int[] currentWindowCount = createCharCount(s2.substring(left, right + 1)); // exclusive so need +1
        while (right < s2.length()) {
            if (isPermutation(targetCount, currentWindowCount)) {
                return true;
            }

            // move
            left++;
            right++;
            if (right == s2.length()) { // done
                return false;
            }

            // update window
            currentWindowCount[s2.charAt(left - 1) - 'a']--;
            currentWindowCount[s2.charAt(right) - 'a']++;
        }
        return false;
    }

    private static boolean isPermutation(int[] targetCount, int[] currentWindowCount) {
        for (int x = 0; x < 26; x++) {
            if (targetCount[x] != currentWindowCount[x]) {
                return false;
            }
        }

        return true;
    }

    private static int[] createCharCount(String string) {
        int[] charCount = new int[26];
        for (int x = 0; x < string.length(); x++) {
            int index = string.charAt(x) - 'a';
            charCount[index]++;
        }
        return charCount;
    }

    // time complexity o(nk) n = s2.length, k = s1.length
    // space complexity o(nk) n = s2.length, k = s1.length -- substring has a cost + candidateMap
    private static boolean computeInclusion(String s1, String s2) {
        if (s1.length() > s2.length() || s1.length() == 0) {
            return false;
        }

        Map<Character, Integer> targetMap = createMap(s1);
        int left = 0;
        int right = left + s1.length();
        while (right <= s2.length()) {
            String candidate = s2.substring(left, right);
            if (isPermutation(candidate, targetMap)) {
                return true;
            }

            left++;
            right++;
        }

        return false;
    }

    private static boolean isPermutation(String candidate, Map<Character, Integer> targetMap) {
        Map<Character, Integer> candidateMap = createMap(candidate);
        if (candidateMap.size() != targetMap.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> character : targetMap.entrySet()) {
            char key = character.getKey();
            int value = character.getValue();
            if (!candidateMap.containsKey(key) || candidateMap.get(key) != value) {
                return false;
            }

        }
        return true;
    }

    private static Map<Character, Integer> createMap(String string) {
        Map<Character, Integer> characterOccurrencesMap = new HashMap<>();
        for (int current = 0; current < string.length(); current++) {
            char currentChar = string.charAt(current);
            if (characterOccurrencesMap.containsKey(currentChar)) {
                characterOccurrencesMap.put(currentChar, characterOccurrencesMap.get(currentChar) + 1);
            } else {
                characterOccurrencesMap.put(currentChar, 1);
            }
        }

        return characterOccurrencesMap;
    }
}

