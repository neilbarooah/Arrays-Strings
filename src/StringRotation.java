/**
 * Assume you have a method isSubstring which checks if one word is a substring of another.
 * Given 2 strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one
 * call to isSubstring.
 *
 * we need to check if there's a way to split s1 into x and y such that xy = s1 and yx = s2
 * Regardless of where the division between x and y is, yx will always be a substring of xyxy
 * That is, s2 will always be a substring of s1s1.
 * Created by neilbarooah on 17/09/16.
 */
public class StringRotation {

    // assuming isSubstring runs in O(A+B) time, then the runtime is O(N).
    boolean isRotation(String s1, String s2) {
        int len = s1.length();
        if (len == s2.length() && len > 0) {
            String s1s1 = s1 + s1;
            return isSubstring(s1s1, s2);
        }
        return false;
    }

    boolean isSubstring(String s1, String s2) {
        return s1.contains(s2);
    }
}
