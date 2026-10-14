class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Feasibility check: minimum sum needed is 1 (if num > 0, first digit >= 1), 
        // and maximum sum possible is 9 * num.
        if (sum < 1 || sum > 9L * num) {
            return "";
        }

        StringBuilder sb = new StringBuilder(num);
        
        for (int i = 0; i < num; i++) {
            // We want to greedily place the largest possible digit (0 to 9) at position i,
            // while ensuring that the remaining sum can still be validly formed by the remaining positions.
            // Remaining positions count: num - 1 - i
            int remainingPositions = num - 1 - i;
            
            // The maximum sum the remaining positions can take is 9 * remainingPositions.
            // Therefore, the current digit must be at least: sum - (9 * remainingPositions)
            // Also, the current digit cannot exceed 9, nor can it exceed the current 'sum'.
            // If i == 0 (the first digit), it also cannot be 0 (must be >= 1).
            
            int minDigit = Math.max(0, sum - 9 * remainingPositions);
            if (i == 0) {
                minDigit = Math.max(minDigit, 1);
            }
            
            int maxDigit = Math.min(9, sum);
            
            // To maximize the sum of squares, we should pick the LARGEST possible valid digit.
            // Wait, let's verify if picking the largest possible digit always maximizes the sum of squares!
            // Let's check: (x+1)^2 - x^2 = 2x + 1, which increases with x. 
            // Thus, larger digits contribute exponentially more to the sum of squares. 
            // So we greedily pick maxDigit!
            
            int chosenDigit = maxDigit;
            sb.append(chosenDigit);
            sum -= chosenDigit;
        }
        
        // If sum is not completely exhausted, return ""
        return sum == 0 ? sb.toString() : "";
    }
}