package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class P1106_ParsingABooleanExpression {
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : expression.toCharArray()) {
            if (ch == ')') {
                Set<Character> seen = new HashSet<>();
                while (stack.peek() != '(') {
                    seen.add(stack.pop());
                }
                stack.pop();        // pop '('
                char op = stack.pop();   // pop operator (&/|/!)
                // push to stack depending on what's in seen
                if (op == '&') {
                    stack.push(seen.contains('f') ? 'f' : 't');
                } else if (op == '|') {
                    stack.push(seen.contains('t') ? 't' : 'f');
                } else {
                    stack.push(seen.contains('t') ? 'f' : 't');
                }
            } else if (ch != ',') stack.push(ch);
        }
        return stack.pop() == 't';
    }
}
