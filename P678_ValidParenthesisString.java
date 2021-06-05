package Solutions;

public class P678_ValidParenthesisString {
    public boolean checkValidString(String s) {
        // count possible needed ), min max as cmin, cmax
        // ( --> both++;
        // ) --> cmax--, cmin-- and >= 0
        // * --> cmax++, cmin-- and >= 0
        // anytme cmax<0 return false
        // return cmin==0
        int cmax = 0, cmin = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin = Math.max(cmin - 1, 0);
            } else {
                cmax++;
                cmin = Math.max(cmin - 1, 0);
            }
            if (cmax < 0) return false;
        }
        return cmin == 0;
    }
}
