package Solutions;

public class P45_JumpGameII {
    public int jump(int[] nums) {
        int res = 0, curEnd = 0, nextEnd = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curEnd >= nums.length - 1) {
                return res;
            }
            nextEnd = Math.max(nextEnd, i + nums[i]);
            if (i == curEnd) {
                res++;
                curEnd = nextEnd;
            }
        }
        return res;
    }
}
