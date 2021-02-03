package Solutions;

import java.util.List;
import java.util.Stack;

public class P636_ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        // stack for task number
        Stack<Integer> stack = new Stack<>();
        int pre = 0;    // ***** pre is start of next interval
        int[] res = new int[n];
        for (String log : logs) {
            String[] l = log.split(":");
            if (l[1].equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += Integer.parseInt(l[2]) - pre;
                }
                stack.push(Integer.parseInt(l[0]));
                pre = Integer.parseInt(l[2]);
            } else {
                res[stack.pop()] += Integer.parseInt(l[2]) - pre + 1;
                pre = Integer.parseInt(l[2]) + 1;
            }
        }
        return res;
    }
}
