package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P17_LetterCombinationsOfAPhoneNumber {
    private String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;
        StringBuilder temp = new StringBuilder();
        doCombinations(digits, res, temp);
        return res;
    }

    private void doCombinations(String digits, List<String> res, StringBuilder temp) {
        if (temp.length() == digits.length()) {
            res.add(temp.toString());
            return;
        }
        String letters = keys[digits.charAt(temp.length()) - '0'];
        for (char c : letters.toCharArray()) {
            temp.append(c);
            doCombinations(digits, res, temp);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
