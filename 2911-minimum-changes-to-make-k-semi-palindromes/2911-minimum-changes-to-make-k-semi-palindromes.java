class Solution {
    private static final List<Integer>[] DIVISORS = new List[201];
    static {
        for (int i = 1; i <= 200; i++) {
            DIVISORS[i] = new ArrayList<>();
            for (int d = 1; d < i; d++) {
                if (i % d == 0) {
                    DIVISORS[i].add(d);
                }
            }
        }
    }

    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = Integer.MAX_VALUE;
                
                for (int d : DIVISORS[len]) {
                    int changes = 0;
                    for (int g = 0; g < d; g++) {
                        int left = i + g;
                        int right = j - (d - 1 - g);
                        while (left < right) {
                            if (chars[left] != chars[right]) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    minChanges = Math.min(minChanges, changes);
                }
                cost[i][j] = minChanges;
            }
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 1000000);
        }
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 0; i < n; i++) {
                if (dp[i][parts - 1] == 1000000) continue;
                
                for (int end = i + 2; end <= n - 2 * (k - parts); end++) {
                    dp[end][parts] = Math.min(dp[end][parts], dp[i][parts - 1] + cost[i][end - 1]);
                }
            }
        }

        return dp[n][k];
    }
}