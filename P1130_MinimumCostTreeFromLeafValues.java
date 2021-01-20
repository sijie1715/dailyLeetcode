package Solutions;

import java.util.Stack;

public class P1130_MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        // Given an array A, choose two neighbors in the array a and b,
        // we can remove the smaller one min(a,b) and the cost is a * b.
        // What is the minimum cost to remove the whole array until only one left?
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        int res = 0;
        for (int num : arr) {
            while (stack.peek() <= num) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), num);
            }
            stack.push(num);
        }
        // when size==2 there're only MAX and 1 num left
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}
