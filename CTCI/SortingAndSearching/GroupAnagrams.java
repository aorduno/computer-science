package CTCI.SortingAndSearching;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        testCase(new String[]{"omar", "tar", "amor", "rats", "car", "roma", "ramo", "rat", "art"});
    }

    private static void testCase(String[] words) {
        LogUtils.logMessage("[[GroupAnagrams]] grouping anagrams for received input", true);
        ArrayUtils.print(words);
        Map<String, List<String>> grouped = groupAnagrams(words);
        print(grouped);
    }

    private static void print(Map<String, List<String>> grouped) {
        LogUtils.logMessage("Printing grouped anagrams", true);
        for (String key : grouped.keySet()) {
            List<String> anagrams = grouped.get(key);
            LogUtils.logMessage("Anagrams group: " + key + " contains " + anagrams.size() + " different words", true);
            for (String anagram : anagrams) {
                LogUtils.logMessage(anagram, true);
            }
            LogUtils.logMessage("Done!", true);
        }
    }

    private static Map<String, List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String word : words) {
            String sorted = sortWord(word);
            List<String> anagrams = anagramMap.getOrDefault(sorted, new ArrayList<>());
            anagrams.add(word);
            anagramMap.put(sorted, anagrams);
        }

        return anagramMap;
    }

    private static String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
