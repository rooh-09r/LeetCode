class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasEven = false;
        boolean hasOdd = false;

        for (int num : nums1) {
            if (num < minVal) {
                minVal = num;
            }
            if (num % 2 == 0) {
                hasEven = true;
            } else {
                hasOdd = true;
            }
        }

        // If array already has uniform parity
        if (!hasEven || !hasOdd) {
            return true;
        }

        // If mixed parity, it's only possible if the smallest element is odd
        return minVal % 2 != 0;
    }
}