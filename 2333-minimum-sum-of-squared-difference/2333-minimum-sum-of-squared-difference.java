class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        // Find the maximum difference to size our frequency array
        int maxDiff = 0;
        int[] count = new int[100001];
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        
        // Greedily reduce the largest differences from top to bottom
        for (int i = maxDiff; i > 0 && totalK > 0; i--) {
            if (count[i] == 0) continue;
            
            // Number of operations we can afford at this difference level
            long opsToUse = Math.min(totalK, (long) count[i]);
            
            count[i] -= opsToUse;
            count[i - 1] += (int) opsToUse;
            totalK -= opsToUse;
        }
        
        // Calculate the final minimum sum of squared differences
        long minSum = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                minSum += (long) count[i] * i * i;
            }
        }
        
        return minSum;
    }
}