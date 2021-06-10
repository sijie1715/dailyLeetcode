package Solutions;

import java.util.Stack;

public class P32_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int longest = 0;
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            if (c == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && chars[stack.peek()] == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }

        if (stack.isEmpty()) {
            longest = s.length();
        } else {
            int a = 0, b = s.length();
            while (!stack.isEmpty()) {
                a = stack.pop();
                longest = Math.max(longest, b - a - 1);
                b = a;
            }
            // in case of valid from index 0
            longest = Math.max(longest, a);
        }
        return longest;
    }
}
