package Solutions;

import java.util.HashSet;
import java.util.Set;

public class P3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxL = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (set.contains(c)) {
                set.remove(s.charAt(left++));
            } else {
                set.add(c);
                right++;
                maxL = Math.max(maxL, right - left);
            }
        }
        return maxL;
    }
}
