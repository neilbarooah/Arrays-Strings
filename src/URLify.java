/**
 * Write a method to replace all spaces in a string with %20. You may assume that
 * the string has sufficient space at the end to hold the additional characters,
 * and that you're given the "true" length of the string.
 *
 * Input: "Mr John Smith    ", 13
 * Output: "Mr%20John%20Smith"
 *
 * Created by neilbarooah on 14/09/16.
 */
public class URLify {

    /* String manipulation: edit the string starting from the end and working backwards.
       We'd have an extra buffer at the end, which allows us to change characters
       without worrying about overwriting. First, count the number of spaces so we
       can compute how many extra characters we have in the final string. Then, edit
       the string in the reverse order.
       We're using character arrays since Strings are immutable in Java.
     */
    void replaceSpaces(char[] string, int trueLength) {
        int spaceCount = 0;
        for (int i = 0; i < trueLength; i++) {
            if (string[i] == ' ') {
                spaceCount++;
            }
        }
        int index = trueLength + spaceCount * 2;
        if (trueLength < string.length) {
            string[trueLength] = '\0'; // End array
        }
        for (int i = trueLength - 1; i >= 0; i--) {
            if (string[i] == ' ') {
                string[index - 1] = '0';
                string[index - 2] = '2';
                string[index - 3] = '%';
                index = index - 3;
            } else {
                string[index - 1] = string[i];
                index--;
            }
        }
    }
}
