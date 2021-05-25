package Solutions;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class P1249_MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        // stack to determine what idx to exclude
        // positive idx for ( and negative for ), use 1-index to avoid confusion at 0
        Stack<Integer> st = new Stack<>();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] == '(') st.push(i + 1);
            else if (sChar[i] == ')') {
                if (!st.isEmpty() && st.peek() > 0) st.pop();
                else st.push(-(i + 1));
            }
        }
        Set<Integer> set = new HashSet<>();
        while (!st.isEmpty()) {
            set.add(Math.abs(st.pop()));
        }

        if (!set.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sChar.length; i++) {
                if (!set.contains(i + 1)) {
                    sb.append(sChar[i]);
                }
            }
            return sb.toString();
        }
        return s;
    }
}
