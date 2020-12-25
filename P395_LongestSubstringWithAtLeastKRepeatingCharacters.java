package Solutions;

public class P395_LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        // iterate thru s and find badchar (occurance<k)
        // split by badchar and return max length
        int size = s.length();
        if (size == 0 || size < k) return 0;
        if (k == 1) return size;

        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        char badChar = '#';
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) badChar = (char) (i + 'a');
        }

        // no badChar then return size
        if (badChar == '#') return size;
        // System.out.println(badChar);
        String[] strs = s.split(String.valueOf(badChar));
        int res = 0;
        for (String str : strs) res = Math.max(res, longestSubstring(str, k));
        return res;
    }
}
