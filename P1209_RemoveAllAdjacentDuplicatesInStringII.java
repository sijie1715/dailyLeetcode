package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class P1209_RemoveAllAdjacentDuplicatesInStringII {
    public String removeDuplicates(String s, int k) {
        Stack<Character> charStk = new Stack<>();
        Deque<Integer> numStk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (charStk.isEmpty() || charStk.peek() != c) {
                charStk.push(c);
                numStk.offerLast(1);
            } else {
                numStk.offerLast(numStk.pollLast() + 1);
            }
            if (!charStk.isEmpty() && numStk.peekLast() == k) {
                charStk.pop();
                numStk.pollLast();
            }
        }
        StringBuilder res = new StringBuilder();
        for (char c : charStk) {
            int num = numStk.pollFirst();
            while (num-- > 0) res.append(c);
        }
        return res.toString();
    }
}
