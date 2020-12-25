package Solutions;

public class P340_LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int res = 0;
        int i = 0, count = 0;
        int[] map = new int[128];
        for (int j = 0; j < s.length(); j++) {
            map[s.charAt(j)]++;
            if (map[s.charAt(j)] == 1) count++;
            while (count > k) {
                map[s.charAt(i)]--;
                if (map[s.charAt(i)] == 0) count--;
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
