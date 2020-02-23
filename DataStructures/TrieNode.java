package DataStructures;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {
    private static int NUMBER_OF_CHARS = 26;
    TrieNode[] children = new TrieNode[NUMBER_OF_CHARS];
    int _words;
    List<String> wordsList = new ArrayList<>();

    int getCharIndex(char c) {
        return c - 'a';
    }

    private TrieNode getNode(char c) {
        return children[getCharIndex(c)];
    }

    void add(String s) {
        add(s, 0, "");
    }

    private void add(String s, int index, String word) {
        if (index == s.length()) {
            return;
        }

        _words++;
        wordsList.add(word);
        char current = s.charAt(index);
        word += current;
        TrieNode child = getNode(current);
        if (child == null) {
            child = new TrieNode();
            setNode(current, child);
        }

        child.add(s, index + 1, word);
    }

    private void setNode(char c, TrieNode node) {
        children[getCharIndex(c)] = node;
    }

    int findCount(String s) {
        return findCount(s, 0);
    }

    private int findCount(String s, int index) {
        if (s.length() == index) {
            return _words;
        }

        TrieNode child = getNode(s.charAt(index));
        if (child == null) {
            return 0;
        }
        return child.findCount(s, index + 1);
    }

    List<String> findWords(String s) {
        return findWords(s, 0, new ArrayList<>());
    }

    private List<String> findWords(String s, int index, List<String> words) {
        if (s.length() == index) {
            // tricky part starts here...
            return wordsList;
        }

        TrieNode childNode = getNode(s.charAt(index));
        if (childNode == null) {
            return words;
        }

        return childNode.findWords(s, index + 1, words);
    }

    public static void main(String[] args) {
        TrieNode trie = new TrieNode();
        trie.add("gary");
        trie.add("gayle");
        trie.add("galbe");

        trie.add("gabriel");
        trie.add("alan");
        trie.add("andy");
        trie.add("alex");

        String find = "al";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));

        List<String> words = trie.findWords(find);
        for (String word : words) {
            System.out.println("Words found: " + word);
        }


        find = "a";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));

        find = "g";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));

        find = "ga";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));

        find = "gab";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));

        find = "oc";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));

        find = "b";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));

        find = "c";
        System.out.println("Occurrences of string " + find + ": " + trie.findCount(find));
    }
}
