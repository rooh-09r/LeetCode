class Solution {
public:
    int maxSelectedElements(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        
        // Since nums[i] <= 10^6, the maximum possible modified value is 10^6 + 1.
        // Using a flat vector instead of an unordered_map avoids hashing overhead.
        int max_val = nums.back() + 2;
        vector<int> dp(max_val, 0);
        
        int ans = 1;
        for (int num : nums) {
            // Update (num + 1) first so the current element isn't used twice
            dp[num + 1] = dp[num] + 1;
            dp[num] = dp[num - 1] + 1;
            
            ans = max({ans, dp[num], dp[num + 1]});
        }
        
        return ans;
    }
};