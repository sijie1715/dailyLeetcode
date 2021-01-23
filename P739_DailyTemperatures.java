package Solutions;

import java.util.Stack;

public class P739_DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        // monostack
        Stack<Integer> stk = new Stack<>();
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stk.isEmpty() && T[i] > T[stk.peek()]) {
                res[stk.peek()] = i - stk.pop();
            }
            stk.push(i);
        }
        return res;
    }
}
