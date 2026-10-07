import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;

        // Step 1: Calculate minimum '(' and ')' to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, left, right, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String s, int index, int balance, int left, int right, StringBuilder current, List<String> result) {
        // Prune: Invalid state where closing brackets exceed opening brackets
        if (balance < 0) return;

        // Base case: Processed full string
        if (index == s.length()) {
            if (left == 0 && right == 0 && balance == 0) {
                result.add(current.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);
        int len = current.length();

        // Branch 1: Skip (Remove) current character if needed
        // Avoid duplicate recursive calls on identical consecutive characters
        if (currentChar == '(' && left > 0) {
            if (index == 0 || s.charAt(index - 1) != '(') {
                // Count consecutive '(' to try removing each possible amount
                int i = index;
                while (i < s.length() && s.charAt(i) == '(') i++;
                int count = i - index;
                for (int k = 1; k <= Math.min(left, count); k++) {
                    backtrack(s, index + k, balance, left - k, right, current, result);
                }
            }
        } else if (currentChar == ')' && right > 0) {
            if (index == 0 || s.charAt(index - 1) != ')') {
                // Count consecutive ')' to try removing each possible amount
                int i = index;
                while (i < s.length() && s.charAt(i) == ')') i++;
                int count = i - index;
                for (int k = 1; k <= Math.min(right, count); k++) {
                    backtrack(s, index + k, balance, left, right - k, current, result);
                }
            }
        }

        // Branch 2: Keep current character (ALWAYS evaluated, not in an else-if)
        current.append(currentChar);
        if (currentChar == '(') {
            backtrack(s, index + 1, balance + 1, left, right, current, result);
        } else if (currentChar == ')') {
            backtrack(s, index + 1, balance - 1, left, right, current, result);
        } else {
            backtrack(s, index + 1, balance, left, right, current, result);
        }
        current.setLength(len); // Backtrack
    }
}