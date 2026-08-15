class Solution {
    public int longestSubsequence(int[] nums) {
        int xorSum = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xorSum ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // If the entire array already has non-zero XOR
        if (xorSum != 0) {
            return nums.length;
        }

        // If total XOR is 0, but we have non-zero elements, drop 1 element
        if (hasNonZero) {
            return nums.length - 1;
        }

        // If all elements are 0
        return 0;
    }
}