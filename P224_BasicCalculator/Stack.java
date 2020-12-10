class Solution {
    public int calculate(String s) {
        // stack to store temp res and sign
        Stack<Integer> stack = new Stack<>();
        // num to record current number
        // res to record res in ()
        int num = 0;
        int res = 0;
        int sign = 1;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = 10*num + (c - '0');
            }
            else if (c == '+') {
                res += sign * num;
                num = 0;
                sign = 1;
            }
            else if (c == '-') {
                res += sign * num;
                num = 0;
                sign = -1;
            }
            else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                // reset sign and res to calculate res in ()
                sign = 1;
                res = 0;
            }
            else if (c == ')') {
                res += sign * num;
                res *= stack.pop();
                res += stack.pop();
                num = 0;
            }
        }
        if (num != 0) res += sign * num;
        return res;
    }
}