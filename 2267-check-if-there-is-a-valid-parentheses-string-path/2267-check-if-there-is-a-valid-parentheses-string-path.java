class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Total path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // The maximum possible open count for a valid path is (m + n) / 2
        int maxOpen = (m + n) / 2;
        
        // Starting with ')' is immediately invalid
        if (grid[0][0] == ')') {
            return false;
        }

        memo = new Boolean[m][n][maxOpen + 1];
        return dfs(grid, 0, 0, 0, maxOpen);
    }

    private boolean dfs(char[][] grid, int r, int c, int open, int maxOpen) {
        // Update balance count for current cell
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        // Invalid path if closing brackets exceed opening brackets or open count exceeds half path length
        if (open < 0 || open > maxOpen) {
            return false;
        }

        // Base case: reached bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        // Return memoized result if available
        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean hasPath = false;

        // Move Down
        if (r + 1 < m) {
            hasPath = dfs(grid, r + 1, c, open, maxOpen);
        }

        // Move Right
        if (!hasPath && c + 1 < n) {
            hasPath = dfs(grid, r, c + 1, open, maxOpen);
        }

        return memo[r][c][open] = hasPath;
    }
}