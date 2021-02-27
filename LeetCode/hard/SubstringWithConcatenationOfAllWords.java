package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import DataStructures.Trie;
import Helpers.Array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//You are given a string s and an array of strings words of the same _length.
// Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once,
// in any order, and without any intervening characters.
//
//        You can return the answer in any order.
public class SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        testCase("barfoothefoobarman", new String[]{"foo", "bar"});
        testCase("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"});
        testCase("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"});
    }

    private static void testCase(String text, String[] strings) {
        LogUtils.logMessage("[[SubstringWithConcatenationOfAllWords]] Finding indexes where the concatenations of all given words are find in string: " + text, true);
        ArrayUtils.print(strings);

        int[] indexes = computeSubstringIndexes(text, strings);
        LogUtils.logMessage("Result:", true);
        Array.print(indexes);
    }

    private static int[] computeSubstringIndexes(String s, String[] words) {
        Trie wordsTrie = buildTrie(words);
        Map<Integer, List<String>> indexWordsMap = generateIndexWordsMap(s, words);
        return new int[]{};
    }

    private static Trie buildTrie(String[] words) {
        Trie trie = new Trie();
        return trie;
//        for (String word : words) {
//            trie.a
//        }
    }

    private static Map<Integer, List<String>> generateIndexWordsMap(String s, String[] words) {
        for (int x = 0; x < s.length(); x++) {
            char c = s.charAt(x);
        }
        return new HashMap<>();
    }
}
