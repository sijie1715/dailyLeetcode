package Solutions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class P752_OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        Set<String> seen = new HashSet<>();
        for (String dead : deadends) {
            deads.add(dead);
        }
        Queue<String> q = new LinkedList<>();
        seen.add("0000");
        q.add("0000");
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (deads.contains(curr)) continue;
                if (curr.equals(target)) return step;
                for (int j = 0; j < 4; j++) {
                    String up = turnUp(curr, j);
                    String down = turnDown(curr, j);
                    if (!seen.contains(up)) {
                        seen.add(up);
                        q.add(up);
                    }
                    if (!seen.contains(down)) {
                        seen.add(down);
                        q.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String turnUp(String curr, int i) {
        char[] chars = curr.toCharArray();
        if (chars[i] == '9') chars[i] = '0';
        else chars[i] += 1;
        return new String(chars);
    }

    private String turnDown(String curr, int i) {
        char[] chars = curr.toCharArray();
        if (chars[i] == '0') chars[i] = '9';
        else chars[i] -= 1;
        return new String(chars);
    }
}
