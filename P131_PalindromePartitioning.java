package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P131_PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        bt(res, new ArrayList<>(), 0, s.toCharArray());
        return res;
    }

    private void bt(List<List<String>> res, List<String> temp, int start, char[] s) {
        if (start == s.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < s.length; i++) {
            if (isPal(s, start, i)) {
                temp.add(new String(s, start, i - start + 1));
                bt(res, temp, i + 1, s);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isPal(char[] s, int l, int r) {
        while (l < r) {
            if (s[l] != s[r]) return false;
            l++;
            r--;
        }
        return true;
    }
}
