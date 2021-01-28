package Solutions;

import java.util.Stack;

public class P946_ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // pop popped whenever possibleto test
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        for (int push : pushed) {
            stack.push(push);
            while (!stack.isEmpty() && stack.peek() == popped[idx]) {
                stack.pop();
                idx++;
            }
        }
        return stack.isEmpty();
    }
}
