import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 1: Precompute matching parentheses indices
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int openIdx = stack.pop();
                pair[openIdx] = i;
                pair[i] = openIdx;
            }
        }

        // Step 2: Traverse string using wormhole logic
        StringBuilder sb = new StringBuilder();
        int curr = 0;
        int dir = 1; // 1 = right, -1 = left

        while (curr < n) {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') {
                curr = pair[curr]; // Teleport to the matching parenthesis
                dir = -dir;        // Reverse traversal direction
            } else {
                sb.append(c);
            }
            curr += dir;
        }

        return sb.toString();
    }
}