package Solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class P402_RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if (num.length() == k) return "0";
        Deque<Character> d = new ArrayDeque<>();
        // keep an increasing stack until not enough k
        for (int i = 0; i < num.length(); i++) {
            while (!d.isEmpty() && d.peekLast() > num.charAt(i) && k > 0) {
                d.pollLast();
                k--;
            }
            d.add(num.charAt(i));
        }
        // use remaining k
        while (k > 0) {
            d.pollLast();
            k--;
        }

        // remove leading "0"
        while (!d.isEmpty() && d.peekFirst() == '0') d.pollFirst();
        String res = "";
        while (!d.isEmpty()) res += d.pollFirst();
        return res == "" ? "0" : res;
    }
}
