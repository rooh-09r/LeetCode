class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        
        // Binary search bounds
        long minCoin = Long.MAX_VALUE;
        for (int c : coins) {
            minCoin = Math.min(minCoin, c);
        }
        
        long low = 1;
        long high = minCoin * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (count(mid, coins) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;  // Not enough amounts <= mid
            }
        }

        return ans;
    }

    // Counts how many distinct amounts <= target can be formed
    private long count(long target, int[] coins) {
        int n = coins.length;
        long totalCount = 0;

        // Iterate through all non-empty subsets using bitmask
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int bitCount = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    if (currentLcm > target) { // Prune if LCM exceeds target
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            if (bitCount % 2 == 1) {
                totalCount += target / currentLcm;
            } else {
                totalCount -= target / currentLcm;
            }
        }

        return totalCount;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}