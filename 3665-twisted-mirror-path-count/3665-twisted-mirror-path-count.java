
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = 1000000007;

        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 0; i < m; i++) {
            int res = 0;

            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int temp = res;
                    res = dp[j + 1];
                    dp[j + 1] = temp;
                } else {
                    int ways = (int)(((long)res + dp[j + 1]) % mod);
                    res = ways;
                    dp[j + 1] = ways;
                }
            }
        }

        return dp[n];
    }
}
