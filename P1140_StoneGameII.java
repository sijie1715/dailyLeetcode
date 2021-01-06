package Solutions;

public class P1140_StoneGameII {
    public int stoneGameII(int[] piles) {
        int[] presum = piles.clone();
        for (int i = presum.length - 2; i >= 0; i--) {
            presum[i] += presum[i + 1];
        }
        return dfs(presum, new int[piles.length][piles.length], 0, 1);
    }

    // dfs calculates the max stones that can be taken starting piles[start] with m
    private int dfs(int[] presum, int[][] memo, int s, int m) {
        if (s + 2 * m >= presum.length) return presum[s];
        if (memo[s][m] != 0) return memo[s][m];
        int res = 0;
        for (int i = 1; i <= 2 * m; i++) {
            // amount if take next i piles
            int take = presum[s] - presum[s + i];
            res = Math.max(res, take + presum[s + i] - dfs(presum, memo, s + i, Math.max(i, m)));
        }
        memo[s][m] = res;
        return res;
    }
}
