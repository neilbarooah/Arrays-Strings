/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 * We need to know if the permutation is case sensitive and whether whitespace is
 * significant. We'll assume that the comparison is case sensitive and that whitespace
 * is significant.
 *
 * Created by neilbarooah on 14/09/16.
 */
public class Permutation {

    // Method 1: sort the characters as permutations have the same characters.
    String sort(String word) {
        char[] content = word.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    boolean permutation(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        return sort(word1).equals(sort(word2));
    }

    // Method 2: compare character counts as permutation pairs have same character count
    boolean efficientPermutation(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] letters = new int[128]; // assume char set is ASCII
        char[] word1Array = word1.toCharArray();
        for (char c: word1Array) {
            letters[c]++;
        }

        for (int i = 0; i < word2.length(); i++) {
            int c = (int) word2.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }
        return true;
    }
}
