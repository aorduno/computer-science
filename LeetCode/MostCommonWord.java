package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        // sanitize
//        String sanitized = paragraph.replaceAll("[^a-zA-Z0-9, ]", "");

        // split by comma and then by space
        String[] split = paragraph.split("[ .?,;!']");
        if (paragraph.isEmpty() || split.length == 0) {
            return null;
        }

        Map<String, String> bannedMap = new HashMap<>();
        for (String aBanned : banned) {
            String bannedLower = aBanned.toLowerCase();
            bannedMap.put(bannedLower, bannedLower);
        }

        Map<String, Integer> wordCount = new HashMap<>();
        String mostCommon = null;
        int maxCount = 0;
        for (String word : split) {
            String wordLower = word.toLowerCase().trim();
            if (bannedMap.containsKey(wordLower) || wordLower.isEmpty()) {
                continue;
            }

            int currentWordCount = wordCount.containsKey(wordLower) ? wordCount.get(wordLower) + 1 : 1;
            if (maxCount < currentWordCount) {
                maxCount = currentWordCount;
                mostCommon = wordLower;
            }

            wordCount.put(wordLower, currentWordCount);
        }

        return mostCommon;
    }

    public static void main(String[] args) {
        runTestCase("", new String[]{""});
        runTestCase(" ", new String[]{""});
        runTestCase("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"});
        runTestCase("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"ball"});
        runTestCase("a, a, a, a, b,b,b,c, c.", new String[]{"ball"});
        runTestCase("a, a, a, a, b,b,b,c, c.", new String[]{"a"});
        runTestCase("j. t? T. z! R, v, F' x! L; l! W. M; S. y? r! n; O. q; I? h; w. t; y; X? y, p. k! k, h, J, r? w! U! V; j' u; R! z. s. T' k. P? M' I' j! y. P, T! e; X. w? M! Y, X; G; d, X? S' F, K? V, r' v, v, D, w, K! S? Q! N. n. V. v. t? t' x! u. j; m; n! F, V' Y! h; c! V, v, X' X' t? n; N' r; x. W' P? W; p' q, S' X, J; R. x; z; z! G, U; m. P; o. P! Y; I, I' l' J? h; Q; s? U, q, x. J, T! o. z, N, L; u, w! u, S. Y! V; S? y' E! O; p' X, w. p' M, h! R; t? K? Y' z? T? w; u. q' R, q, T. R? I. R! t, X, s? u; z. u, Y, n' U; m; p? g' P? y' v, o? K? R. Q? I! c, X, x. r' u! m' y. t. W; x! K? B. v; m, k; k' x; Z! U! p. U? Q, t, u' E' n? S' w. y; W, x? r. p! Y? q, Y. t, Z' V, S. q; W. Z, z? x! k, I. n; x? z; V? s! g, U; E' m! Z? y' x? V! t, F. Z? Y' S! z, Y' T? x? v? o! l; d; G' L. L, Z? q. w' r? U! E, H. C, Q! O? w! s? w' D. R, Y? u. w, N. Z? h. M? o, B, g, Z! t! l, W? z, o? z, q! O? u, N; o' o? V; S! z; q! q. o, t! q! w! Z? Z? w, F? O' N' U' p? r' J' L; S. M; g' V. i, P, v, v, f; W? L, y! i' z; L? w. v, s! P?", new String[]{"m", "q", "e", "l", "c", "i", "z", "j", "g", "t", "w", "v", "h", "p", "d", "b", "a", "r", "x", "n"});
    }

    private static void runTestCase(String paragraph, String[] banned) {
        MostCommonWord mostCommonWord = new MostCommonWord();
        System.out.println("The most common word for paragraph: " + paragraph);
        System.out.println("is: " + mostCommonWord.mostCommonWord(paragraph, banned));


    }
}
