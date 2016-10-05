/**
 *
 * Implement a method to perform basic string compression using the counts of
 * repeated characters. For example, the string aabcccccaaa would become
 * a2b1c5a3. If the compressed string would not become smaller than the original
 * string, your method should return the original string. You can assume that
 * the string has only uppercase and lowercase letters (a-z).
 *
 * Created by neilbarooah on 16/09/16.
 */
public class stringCompression {

    /* #1: Iterate through the string, copying characters to a new string and
           counting the repeats. At each iteration, check if the current char
           is the same as the next char. If not, add it's compressed version
           to the result.
           Runtime is O(p + k^2), where p is the size of the original string and k
           is the number of character sequences.

     */
    String compressBad(String word) {
        String compressedString = "";
        int countConsecutive = 0;
        for (int i = 0; i < word.length(); i++) {
            countConsecutive++;
            if (i + 1 >= word.length() || word.charAt(i) != word.charAt(i + 1)) {
                compressedString += "" + word.charAt(i) + countConsecutive;
                countConsecutive = 0;
            }
        }
        return compressedString.length() < word.length() ? compressedString : word;
    }

    String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            // if next character is different than current, append this char to result
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    /* in cases of a lot of repeating characters, we can check whether the size of the compressed
       string will be shorter than the input string before rather than at the end. Downside is
       the need for a second loop through the characters and duplicated code.
    */
    String compressForLessRepeatChars(String str) {
        // check final length and return input string if it would be longer
        int finalLength = countCompression(str);
        if (finalLength >= str.length()) {
            return str;
        }
        StringBuilder compressed = new StringBuilder(finalLength); // initial capacity
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.toString();
    }

    int countCompression(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }
        return compressedLength;
    }
}
