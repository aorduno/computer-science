package LeetCode.hard;

import CTCI.ArraysAndStrings.ArrayUtils;
import CTCI.LogUtils;
import javafx.util.Pair;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        testCase("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        testCase("hit", "cog", Arrays.asList("hot", "dog", "lot", "log", "dot"));
        testCase("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        testCase("hot", "dog", Arrays.asList("hot", "dog"));
        testCase("qa", "sq", Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye"));
        testCase("qa", "sq", Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye"));
    }

    private static void testCase(String beginWord, String endWord, List<String> wordList) {
        LogUtils.logMessage("[[WordLadder]] Finding the smallest transformation sequence where beginWord is " + beginWord + " and endWord is " + endWord + ". For given wordList: ");
        ArrayUtils.print(Collections.singleton(wordList));
        LogUtils.logMessage("NOTE: in VALID a transformation sequence all neighbors need to differ in one single char");

        int shortest = computeShortestTransformationSequenceBiDirectional(beginWord, endWord, wordList);
        LogUtils.logMessage("Shortest Transformation Sequence: " + shortest);
    }

    // this is bi BFS
    private static int computeShortestTransformationSequenceBiDirectional(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, List<String>> matcherToWordListMap = createMatchers(wordList);
        Queue<Pair<String, Integer>> toVisitBegin = new ArrayDeque<>();
        Queue<Pair<String, Integer>> toVisitEnd = new ArrayDeque<>();
        toVisitBegin.add(new Pair<>(beginWord, 1));
        toVisitEnd.add(new Pair<>(endWord, 1));

        Map<String, Integer> visitedBegin = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedEnd.put(endWord, 1);
        while (!toVisitBegin.isEmpty() && !toVisitEnd.isEmpty()) {
            int shortestSequence = visit(toVisitBegin, visitedBegin, visitedEnd, matcherToWordListMap);
            if (shortestSequence != Integer.MIN_VALUE) {
                return shortestSequence;
            }

            shortestSequence = visit(toVisitEnd, visitedEnd, visitedBegin, matcherToWordListMap);
            if (shortestSequence != Integer.MIN_VALUE) {
                return shortestSequence;
            }
        }
        return 0;
    }

    private static int visit(Queue<Pair<String, Integer>> toVisit, Map<String, Integer> thisSideVisited, Map<String, Integer> otherSideVisited, Map<String, List<String>> matcherToWordListMap) {
        Pair<String, Integer> current = toVisit.remove();
        String word = current.getKey();
        int level = current.getValue();
        List<String> matchers = computeMatchers(word, word.length());
        for (String matcher : matchers) {
            List<String> matches = matcherToWordListMap.get(matcher);
            if (matches == null) {
                continue;
            }

            for (String match : matches) {
                if (otherSideVisited.containsKey(match)) {
                    return otherSideVisited.get(match) + level;
                }

                if (thisSideVisited.containsKey(match)) {
                    continue;
                }

                thisSideVisited.put(match, level + 1);
                toVisit.add(new Pair<>(match, level + 1));
            }
        }
        return Integer.MIN_VALUE;
    }

    // this is BFS...
    private static int computeShortestTransformationSequenceBetter(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, List<String>> matchers = createMatchers(wordList);
        Map<String, Boolean> targetMap = computeTargets(endWord);
        Queue<String> toVisit = new ArrayDeque<>();
        toVisit.add(beginWord);

        int sequence = 1;
        Map<String, Boolean> visited = new HashMap<>();
        while (!toVisit.isEmpty()) {
            int size = toVisit.size();
            while (size > 0) {
                String currentWord = toVisit.remove();
                visited.put(currentWord, true);

                List<String> currentMatchers = computeMatchers(currentWord, currentWord.length());
                List<String> neighbors = new ArrayList<>();
                for (String matcher : currentMatchers) {
                    if (targetMap.containsKey(matcher)) {
                        return sequence + 1;
                    }

                    List<String> strings = matchers.get(matcher);
                    if (strings == null) { // no match
                        continue;
                    }
                    for (String candidate : strings) {
                        if (visited.containsKey(candidate)) {
                            continue;
                        }

                        neighbors.add(candidate);
                    }

                }

                toVisit.addAll(neighbors);
                size--;
            }

            sequence++;
        }
        return 0;
    }

    private static Map<String, Boolean> computeTargets(String endWord) {
        List<String> targets = computeMatchers(endWord, endWord.length());
        Map<String, Boolean> targetMap = new HashMap<>();
        for (String target : targets) {
            targetMap.put(target, true);
        }

        return targetMap;
    }

    private static Map<String, List<String>> createMatchers(List<String> wordList) {
        if (wordList.isEmpty()) {
            return new HashMap<>();
        }

        int length = wordList.get(0).length(); //assuming all words are same length
        Map<String, List<String>> matcherToMatchesMap = new HashMap<>();
        for (String word : wordList) {
            List<String> matchers = computeMatchers(word, length);
            for (String matcher : matchers) {
                List<String> matches = matcherToMatchesMap.getOrDefault(matcher, new ArrayList<>());
                matches.add(word);
                matcherToMatchesMap.put(matcher, matches);
            }
        }

        return matcherToMatchesMap;
    }

    private static List<String> computeMatchers(String word, int length) {
        List<String> matchers = new ArrayList<>();
        for (int x = 0; x < length; x++) {
            matchers.add(word.substring(0, x) + "*" + word.substring(x + 1, length));
        }
        return matchers;
    }

}
