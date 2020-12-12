package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P224_BasicCalculator_Recursive {
    int i = 0;

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char op = '+';
        while (i < s.length()) {
            // update i here
            char ch = s.charAt(i++);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if (ch == '(') {
                num = calculate(s);
            }
            if (i >= s.length() || ch == '+' || ch == '-' || ch == ')') {
                if (op == '+') stack.push(num);
                else stack.push(-num);
                op = ch;
                num = 0;
            }
            if (ch == ')') break;
        }
        return stack.stream().mapToInt(Integer::intValue).sum();
    }
}
