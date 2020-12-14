package Solutions;

import java.util.*;

public class P1096_BraceExpansionII {
    public List<String> braceExpansionII(String expression) {
        List<String> curList = new ArrayList<>();
        curList.add("");
        Stack<List<String>> stk = new Stack<>();
        Stack<Character> opStk = new Stack<>();
        char sign = '*';

        // default operator=="*", attach before each term if nothing before
        // ','='+'
        for (char c : expression.toCharArray()) {
            stk.push(new ArrayList<>(curList));
            curList.clear();
            curList.add("");
            if (c == '{') {
                while (!opStk.isEmpty() && opStk.peek() != ',' && opStk.peek() != '{') {
                    doCal(stk, opStk);
                }
                opStk.push(sign);
                opStk.push('{');
                sign = '*';
            } else if (c == '}') {
                while (opStk.peek() != '{') {   // doCal(letters and +) until meets '{'
                    doCal(stk, opStk);
                }
                opStk.pop();    // pop the '{'
                opStk.push(sign);
                sign = '*';
            } else if (c == ',') {
                while (!opStk.isEmpty() && opStk.peek() != '{') {
                    doCal(stk, opStk);
                }
                opStk.push(sign);
                sign = ',';
            } else {
                while (!opStk.isEmpty() && opStk.peek() != ',' && opStk.peek() != '{') {
                    doCal(stk, opStk);
                }
                curList.set(0, "" + c);
                opStk.push(sign);
                sign = '*';
            }
        }
        stk.push(curList);
        while (!opStk.isEmpty()) {
            doCal(stk, opStk);
        }
        List<String> res = stk.pop();
        Collections.sort(res);
        return res;
    }

    private void doCal(Stack<List<String>> stk, Stack<Character> opStk) {
        List<String> list2 = stk.pop(), list1 = stk.pop();  // order!!
        char op = opStk.pop();
        if (op == ',') stk.push(plus(list1, list2));
        else stk.push(multi(list1, list2));
    }

    private List<String> multi(List<String> list1, List<String> list2) {
        List<String> res = new ArrayList<>();
        for (String s1 : list1) {
            for (String s2 : list2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }

    private List<String> plus(List<String> list1, List<String> list2) {
        Set<String> set = new HashSet<>(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }
}
