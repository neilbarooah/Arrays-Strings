/**
 *
 * There are 3 types of edits that can performed on strings: insert a character,
 * remove a character, or replace a character. Given 2 strings, write a function
 * to check if they are 1 edit (or 0 edits) away.
 *
 * EXAMPLE:
 * pale, ple -> true
 * pales, pale -> true
 * pale, bale -> true
 * pale, bae -> false
 *
 * Replacement: consider bale and pale. Since you can replace 1 character in bale
 * to make pale, it means that they're different only in 1 place.
 * Insertion: consider apple and aple. They're identical except for a shift at some
 * point in the strings.
 * Removal: consider apple and aple. It's the inverse of insertion.
 *
 * Consider the lengths of the strings to see which one of the 3 is needed
 *
 * Created by neilbarooah on 15/09/16.
 */
public class OneAway {

    // #1: O(N) solution
    boolean oneEditAway(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() == second.length() + 1) {
            return oneEditInsert(second, first);
        }
        return false;
    }

    boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {  // first time it catches a difference, it will automatically skip the if loop
                    return false;
                }
                foundDifference = true;
            }
        }
        return true;
    }

    // check if you can insert a character into s1 to make s2
    boolean oneEditInsert(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    // #2: Same efficiency but merge oneEditReplace and oneEditInsert so we don't repeat code
    // Not necessarily better as the first approach is easier to read, but second is more compact/maintainable
    boolean shorterOneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        // get shorter and longer string
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                // ensure that this is the first difference found
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;

                if (s1.length() == s2.length()) {
                    index1++;  // on replace, move shorter pointer
                }
            } else {
                index1++;  // if matching, move shorter pointer
            }
            index2++; // always move pointer for longer string
        }
        return true;
    }
}
