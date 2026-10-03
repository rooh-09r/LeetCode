class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        // Stack holds indices of characters
        Deque<Integer> stack = new ArrayDeque<>();
        
        // Push -1 as a base index for boundary length calculations
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop(); // Pop the last matching '(' or boundary
                if (stack.isEmpty()) {
                    // Current ')' is unmatched; push its index as the new base boundary
                    stack.push(i);
                } else {
                    // Length of the current valid substring is (current index - top of stack)
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}