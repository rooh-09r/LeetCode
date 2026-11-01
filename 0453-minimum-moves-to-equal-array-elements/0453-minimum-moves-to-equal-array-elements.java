class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            sum += num;
        }

        return (int) (sum - (long) nums.length * min);
    }
}