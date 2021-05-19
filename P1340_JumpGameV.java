package Solutions;

public class P1340_JumpGameV {
    // dfs O(ND)
    public int maxJumps(int[] arr, int d) {
        int[] dp = new int[arr.length];
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(res, dfs(arr, d, i, dp));
        }
        return res;
    }

    private int dfs(int[] arr, int d, int i, int[] dp) {
        if (dp[i] != 0) return dp[i];
        int res = 1;
        // check left side
        for (int j = i - 1; j >= Math.max(0, i - d) && arr[j] < arr[i]; j--) {
            res = Math.max(res, dfs(arr, d, j, dp) + 1);
        }
        // check right side
        for (int j = i + 1; j <= Math.min(arr.length - 1, i + d) && arr[j] < arr[i]; j++) {
            res = Math.max(res, dfs(arr, d, j, dp) + 1);
        }
        return dp[i] = res;
    }
}
