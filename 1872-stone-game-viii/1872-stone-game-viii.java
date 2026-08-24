class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Compute prefix sums in-place to save memory
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Base case: picking all stones leaves prefixSum[n - 1] score
        int maxDiff = stones[n - 1];
        
        // Iterate backward from n - 2 down to 1
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, stones[i] - maxDiff);
        }
        
        return maxDiff;
    }
}