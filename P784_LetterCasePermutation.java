package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P784_LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(S.toCharArray(), res, 0);
        return res;
    }

    private void dfs(char[] s, List<String> res, int idx) {
        if (idx == s.length) {
            res.add(new String(s));
            return;
        }
        if (Character.isDigit(s[idx])) {
            dfs(s, res, idx + 1);
            return;
        }
        s[idx] = Character.toLowerCase(s[idx]);
        dfs(s, res, idx + 1);

        s[idx] = Character.toUpperCase(s[idx]);
        dfs(s, res, idx + 1);
    }
}
