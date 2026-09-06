class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // Early exit if t is longer than s
        if (n > m) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: 1 way to match empty string t

        for (int i = 1; i <= m; i++) {
            char charS = s.charAt(i - 1);
            // Traverse backwards to avoid overwriting values needed for the current iteration
            for (int j = n; j >= 1; j--) {
                if (charS == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}