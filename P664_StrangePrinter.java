package Solutions;

public class P664_StrangePrinter {
    public int strangePrinter(String s) {
        // dp[i][j] stands for the minimal turns we need for string from index i to index j
        // dp[i][i] = 1: we need 1 turn to paint a single character
        // maximum turns for dp[start][start + len] is len + 1, i.e. print one character each time
        // ***divide substring into 2 parts: start -> start+k and start+k+1 -> start+len (0<=k<len)
        // if both parts end with same char, dp-- (think about aba->a,ba)
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                dp[i][i + len] = len + 1;
                for (int k = 0; k < len; k++) {
                    int temp = dp[i][i + k] + dp[i + k + 1][i + len];
                    dp[i][i + len] = Math.min(
                            dp[i][i + len],
                            s.charAt(i + k) == s.charAt(i + len) ? temp - 1 : temp);
                }
            }
        }
        return dp[0][n - 1];
    }
}
