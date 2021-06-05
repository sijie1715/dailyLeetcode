package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P241_DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        // at every operator, recursively calculate left side and right side possibilities
        List<Integer> res = new ArrayList<>();

        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftSide = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rightSide = diffWaysToCompute(expression.substring(i + 1));

                for (int left : leftSide) {
                    for (int right : rightSide) {
                        if (c == '+') {
                            res.add(left + right);
                        } else if (c == '-') {
                            res.add(left - right);
                        } else if (c == '*') {
                            res.add(left * right);
                        }
                    }
                }
            }
        }

        // only 1 number
        if (res.isEmpty()) {
            res.add(Integer.parseInt(expression));
        }
        return res;
    }
    
}
