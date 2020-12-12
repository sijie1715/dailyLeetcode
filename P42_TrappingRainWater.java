package Solutions;

public class P42_TrappingRainWater {
    public int trap(int[] height) {
        // check argument
        if (height == null || height.length == 0) return 0;

        int size = height.length;
        int[] leftMax = new int[size], rightMax = new int[size];

        // iterate from left side
        leftMax[0] = height[0];
        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        // iterate from right side
        rightMax[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int ans = 0;
        // no need to compare 2 ends
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}
