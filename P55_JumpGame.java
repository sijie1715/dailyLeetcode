package Solutions;

public class P55_JumpGame {
    public boolean canJump(int[] nums) {
        int reach = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (reach < i) return false;
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= nums.length - 1;
    }
}
