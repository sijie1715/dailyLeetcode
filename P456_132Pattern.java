package Solutions;

import java.util.Stack;

public class P456_132Pattern {
    public boolean find132pattern(int[] nums) {
        // Stack<int[min,max]>
        // if num<min, push new int[num,num]
        // if min<num<max, return true
        // if max<=num, update max
        // - after update stack empty, push back
        // - after update stack not empty
        // -- while peek.max<num, pop stack (new int[] cover entire range)
        // -- num<peek.max, return true
        Stack<int[]> stack = new Stack<>();     // int[min,max]
        for (int num : nums) {
            if (stack.isEmpty() || num < stack.peek()[0]) {
                stack.push(new int[]{num, num});
            } else if (num > stack.peek()[0]) {
                if (num < stack.peek()[1]) return true;
                else {          // num >= max
                    int[] last = stack.pop();
                    last[1] = num;
                    while (!stack.isEmpty() && stack.peek()[1] <= num) {
                        stack.pop();
                    }
                    // num<peek.max
                    if (!stack.isEmpty() && stack.peek()[0] < num) return true;
                    stack.push(last);
                }
            }
        }
        return false;
    }
}
