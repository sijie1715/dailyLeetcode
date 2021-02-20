package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P320_GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(word, res, "", 0, 0);
        return res;
    }

    private void dfs(String word, List<String> res, String cur, int pos, int count) {
        if (pos == word.length()) {
            if (count > 0) cur += count;
            res.add(cur);
            // return;
        } else {
            // continue count
            dfs(word, res, cur, pos + 1, count + 1);
            // end and append count
            cur = cur + ((count > 0) ? count : "") + word.charAt(pos);
            dfs(word, res, cur, pos + 1, 0);
        }
    }
}
