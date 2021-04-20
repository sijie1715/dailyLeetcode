package Solutions;

import java.util.Stack;

public class P1381_DesignAStackWithIncrementOperation {
    // inc[i] records the val stack[0] to stack[i] is inced
    // when popped, inc[i-1]+=inc[i]
    Stack<Integer> s;
    int n;
    int[] inc;

    public void CustomStack(int maxSize) {
        n = maxSize;
        inc = new int[n];
        s = new Stack<>();
    }

    public void push(int x) {
        if (s.size() < n) {
            s.push(x);
        }
    }

    public int pop() {
        int i = s.size() - 1;
        if (i < 0) {
            return -1;
        }
        if (i > 0) {
            inc[i - 1] += inc[i];
        }
        int res = s.pop() + inc[i];
        inc[i] = 0;
        return res;
    }

    public void increment(int k, int val) {
        int i = Math.min(s.size(), k) - 1;
        if (i >= 0) inc[i] += val;
    }

}
