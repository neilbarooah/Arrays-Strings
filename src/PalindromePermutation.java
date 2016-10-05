/**
 * Given a string, check if it's a permutation of a palindrome. A palindrome is
 * a word or phrase that is the same forwards and backwards.
 *
 * Example: Tact Coa
 * Output: True ("taco cat","atco cta")
 *
 * Created by neilbarooah on 15/09/16.
 *
 * To be able to write a set of characters the same way forwards and backwards,
 we need to have an even number of almost all characters, so that half can
 be one side and the other half on the other side. At most, one character
 can have an odd count. So, strings with even length (after removing all
 non-letter chars) must have all even counts of chars. Strings of an odd
 length must have exactly one character with an odd count.
 */
public class PalindromePermutation {

    // #1 Use a hash table to count how many times each char appears. O(N) time reqd
    boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    // check that no more than one character has an odd count
    boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) {   // multiple odd numbers of chars found
                    return false;
                }
                foundOdd = true;  // first time odd numbers of char found
            }
        }
        return true;
    }

    // map each char to a number. a -> 0, b -> 1, c -> 2 etc
    int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (val >= a && val <= z) {
            return val - a;
        }
        return -1;
    }

    // count how many times each char appears
    int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    // #2 Instead of checking the numer of odd counts at the end, we can check as
    // as we go along. Then, as we get to the end, we have our answer.

    boolean isPermutationPalindrome(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    /*
    #3 We don't need to know the counts. We just need to know if the count is even
    or off. We use a single integer (as a bit vector). When we see a letter, we map
    it to an integer between 0 and 26. Then we toggle the bit at that value. At the
    end of the iteration, we check that at most one bit in the integer is set to 1.
    We can check that no bits in the integer are 1: just compare the integer to 0.
    This solution is also O(N).
     */

    boolean isPermutationPalindromeBits(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    // create a bit vector. For each letter with value i, toggle the ith bit
    int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    // toggle the ith bit in the integer
    int toggle(int bitVector, int index) {
        if (index < 0) {
            return bitVector;
        }
        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    // check that 1 bit is set by subtracting 1 from the integer and ANDing it with
    // original integer.
    boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }
}
