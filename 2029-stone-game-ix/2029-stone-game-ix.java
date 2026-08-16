class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }
        
        // If 0-remainder stones count is even:
        // Alice wins if there is at least one 1 or 2 stone AND c1 != c2.
        if (cnt[0] % 2 == 0) {
            return cnt[1] >= 1 && cnt[2] >= 1;
        }
        
        // If 0-remainder stones count is odd:
        // Alice wins if the difference between c1 and c2 is greater than 2.
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}