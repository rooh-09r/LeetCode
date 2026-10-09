class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int open = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If there's an active '(' that only received one ')', complete it with a ')'
                if (open < 0) {
                    res++;
                    open++;
                }
                open++;
            } else { // c == ')'
                // Check if the next character is also ')'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i++; // Skip the second ')'
                } else {
                    res++; // Insert a missing ')'
                }

                // Balance with an open '(' if available
                if (open > 0) {
                    open--;
                } else {
                    res++; // Insert a missing '('
                }
            }
        }

        // Remaining unmatched '(' each need two ')'
        return res + open * 2;
    }
}