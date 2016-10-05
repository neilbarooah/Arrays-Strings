/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 *
 * Important to know if the string is an ASCII string or a Unicode string.
 * Assume that the character set is ASCII for this case.
 * Solution - create an array of 128 values filled with boolean false values
 * and toggle to true when that character occurs.
 */

/**
 * Created by neilbarooah on 12/09/16.
 */
public class IsUnique {

    /* this is O(n), where n is length of the word. The space complexity is
    O(1)
    */
    boolean isUniqueChars(String word) {
        if (word.length() > 128) {
            return false;   // only 128 unique characters exist in ASCII
        }
        boolean[] charSet = new boolean[128];
        for (int i = 0; i < word.length(); i++) {
            int value = word.charAt(i);   // gives you the ASCII code
            if (charSet[value]) {
                return false;
            }
            charSet[value] = true;
        }
        return true;
    }

    /* This reduces the space usage by a factor of 8 by using a bit vector.
       It assumes that we only have lowercase letters.
     */
    boolean isUniqueBitVector(String word) {
        int checker = 0;
        for (int i = 0; i < word.length(); i++) {
            int value = word.charAt(i) - 'a';
            if ((checker & (1 << value)) > 0) {
                return false;
            }
            checker |= (1 << value);
        }
        return true;
    }

    /* Can also do the following:
       1. Compare every character of the string to every other character of the
          string - O(n^2) time and O(1) space
       2. If we're allowed to modify the input string, we could sort the string in
          O(nlog(n)) time and then linearly check the string for neighboring characters
          that are identical. Sorting algorithms take up extra space though.
     */
}
