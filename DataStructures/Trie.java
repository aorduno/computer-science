package DataStructures;

import CTCI.LogUtils;

public class Trie {
    private static int NUMBER_OF_CHARS = 26; // representing case-insensitive US-EN chars
    private Trie[] _children = new Trie[NUMBER_OF_CHARS];
    private boolean _word;


    public static void main(String[] args) {

        Trie trie = new Trie();
        trie.add("suscripciones");
        trie.add("suscribir");
        trie.add("suscripcion");
        String word = "suscriptor";
        LogUtils.logMessage("Does trie contains the word " + word + " : " + trie.contains(word), true);
        word = "suscripciones";
        LogUtils.logMessage("Does trie contains the word " + word + " : " + trie.contains(word), true);
        word = "suscripcion";
        LogUtils.logMessage("Does trie contains the word " + word + " : " + trie.contains(word), true);
        String prefix = "suscripcion";
        LogUtils.logMessage("Does trie contains the prefix " + prefix + " : " + trie.hasPrefix(prefix), true);
    }

    public boolean contains(String word) {
        if (word.isEmpty()) {
            return false;
        }

        Trie currentTrie = this;
        for (int x = 0; x < word.length(); x++) {
            char current = word.charAt(x);
            int index = getCharIndex(current);
            if (currentTrie._children[index] == null) {
                return false;
            }

            currentTrie = currentTrie._children[index];
        }

        return currentTrie.isWord();
    }

    public boolean hasPrefix(String prefix) {
        if (prefix.isEmpty()) {
            return false;
        }

        Trie currentTrie = this;
        for (int x = 0; x < prefix.length(); x++) {
            char current = prefix.charAt(x);
            int index = getCharIndex(current);
            if (currentTrie._children[index] == null) {
                return false;
            }

            currentTrie = currentTrie._children[index];
        }

        return currentTrie != null;
    }

    public void add(String word) {
        if (word.isEmpty()) {
            return;
        }

        Trie currentTrie = this;
        for (int x = 0; x < word.length(); x++) {
            char current = word.charAt(x);
            int index = getCharIndex(current);
            if (currentTrie._children[index] == null) {
                currentTrie._children[index] = new Trie();
            }

            currentTrie = currentTrie._children[index];
        }

        currentTrie.setWord(true);
    }

    private int getCharIndex(char current) {
        return current - 'a';
    }

    public boolean isWord() {
        return _word;
    }

    public void setWord(boolean word) {
        _word = word;
    }
}
