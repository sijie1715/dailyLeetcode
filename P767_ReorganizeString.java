package Solutions;

public class P767_ReorganizeString {
    public String reorganizeString(String S) {
        // put most frequent letter at even index (0,2,4...)
        // if occurence>(length+1)/2 then return ""
        // put other letters at odd index
        int[] count = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            count[c - 'a']++;
        }

        int maxCount = 0, maxLetter = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxLetter = i;
                maxCount = count[i];
            }
        }

        if (maxCount > (S.length() + 1) / 2) {
            return "";
        }

        char[] res = new char[S.length()];
        int idx = 0;
        while (count[maxLetter] > 0) {
            res[idx] = (char) (maxLetter + 'a');
            count[maxLetter]--;
            idx += 2;
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                // fill the rest of the even index and then switch to odd
                if (idx >= S.length()) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                count[i]--;
                idx += 2;
            }
        }
        return String.valueOf(res);
    }
}
