package Solutions;

import java.util.Arrays;

public class P179_LargestNumber {
    public String largestNumber(int[] nums) {
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strNums, (a, b) -> {
            String s1 = a + b;
            String s2 = b + a;
            return s2.compareTo(s1);
        });
        if (strNums[0].equals("0")) return "0";
        String res = new String();
        for (String s : strNums) {
            res += s;
        }
        return res;
    }
}
