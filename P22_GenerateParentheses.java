package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P22_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        genCom(res, "", 0, 0, n);
        return res;
    }

    private void genCom(List<String> res, String temp, int openN, int closeN, int n) {
        if (temp.length() == 2 * n) {
            res.add(new String(temp));
            return;
        }
        if (openN < n) genCom(res, temp + "(", openN + 1, closeN, n);
        if (closeN < openN) genCom(res, temp + ")", openN, closeN + 1, n);
    }
}
