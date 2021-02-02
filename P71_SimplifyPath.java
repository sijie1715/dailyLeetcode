package Solutions;

import java.util.Stack;

public class P71_SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] dirs = path.split("/");

        for (String dir : dirs) {
            // continue if empty or '.'
            if (dir.equals(".") || dir.isEmpty()) continue;
                // pop stack if ..
            else if (dir.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            }
            // real directory
            else {
                stack.push(dir);
            }
        }
        StringBuilder res = new StringBuilder();
        for (String d : stack) {
            res.append("/");
            res.append(d);
        }
        return res.length() > 0 ? res.toString() : "/";
    }
}
