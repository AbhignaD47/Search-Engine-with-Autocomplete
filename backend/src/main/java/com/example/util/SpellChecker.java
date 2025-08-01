package com.example.util;
import java.util.List;
import java.util.stream.Collectors;

public class SpellChecker {
    private List<String> dictionary;

    public SpellChecker(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public String suggestCorrection(String word) {
        return dictionary.stream()
            .min((w1, w2) -> Integer.compare(levenshteinDistance(word, w1), levenshteinDistance(word, w2)))
            .orElse(word);
    }

    private int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1),
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
