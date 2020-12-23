package Solutions;

public class P159_LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int maxL = 0, count = 0;
        int[] counts = new int[128];
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            counts[c - 'a']++;
            if (counts[c - 'a'] == 1) {
                count++;
            }
            while (count > 2) {
                counts[s.charAt(i) - 'a']--;
                if (counts[s.charAt(i) - 'a'] == 0) {
                    count--;
                }
                i++;
            }
            maxL = Math.max(maxL, j - i + 1);
        }
        return maxL;
    }
}
