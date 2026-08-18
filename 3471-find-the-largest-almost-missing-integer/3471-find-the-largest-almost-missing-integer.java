import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: k == n -> Any element is in the single subarray of size n
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }

        // Count frequencies of all elements to check uniqueness
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Case 2: k == 1 -> Largest element with frequency 1
        if (k == 1) {
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }

        // Case 3: 1 < k < n -> Only the endpoints nums[0] and nums[n-1] are candidates
        int maxVal = -1;
        if (freq.get(nums[0]) == 1) {
            maxVal = Math.max(maxVal, nums[0]);
        }
        if (freq.get(nums[n - 1]) == 1) {
            maxVal = Math.max(maxVal, nums[n - 1]);
        }

        return maxVal;
    }
}