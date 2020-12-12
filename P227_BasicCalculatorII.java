package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P227_BasicCalculatorII {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        Character operator = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            // operator is storing operator before num
            if (i == s.length() - 1 || ch == '+'
                    || ch == '-' || ch == '*' || ch == '/') {
                if (operator == '+') {
                    stack.push(num);
                } else if (operator == '-') {
                    stack.push(-num);
                } else if (operator == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
                operator = ch;
                num = 0;
            }
        }
        // iterate through stack to get sum fo all terms
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
