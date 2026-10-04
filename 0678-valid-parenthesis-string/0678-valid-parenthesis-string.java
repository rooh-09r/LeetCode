class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else if (c == '*') {
                minOpen--; // Treat as ')'
                maxOpen++; // Treat as '('
            }

            // More ')' than '(' and '*' combined
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative (we treat excess '*' as empty string "")
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        return minOpen == 0;
    }
}