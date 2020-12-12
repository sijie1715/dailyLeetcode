package Solutions;

import java.util.ArrayList;
import java.util.Collections;

public class P1087_BraceExpansion {
    public String[] expand(String S) {
        if (S == null || S.length() == 0) return new String[]{""};
        if (S.length() == 1) return new String[]{S};
        // if firstChar is '{', record i until hits '}'
        // -> thisList=S.substring(1,i).split(','); skip '}', nextList=S.substring(i+1)
        // -> res.add all combination of thisList and nextList
        ArrayList<String> res = new ArrayList<>();
        if (S.charAt(0) == '{') {
            int i = 0;
            while (S.charAt(i) != '}') i++;
            String[] thisList = S.substring(1, i).split(",");
            String[] nextList = expand(S.substring(i + 1));
            for (String thisStr : thisList) {
                for (String nextStr : nextList) {
                    res.add(thisStr + nextStr);
                }
            }
        }
        // else firstChar is letter, nextList=expand(S.substring(1))
        // -> res.add all combination of firstChar and nextList
        else {
            String[] nextList = expand(S.substring(1));
            for (String nextStr : nextList) {
                res.add(S.charAt(0) + nextStr);
            }
        }
        // sort res
        // return res.toArray(new String[0])
        Collections.sort(res);
        return res.toArray(new String[0]);
    }
}
