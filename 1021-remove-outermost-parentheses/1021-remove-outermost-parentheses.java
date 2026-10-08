class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int opened = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If opened > 0, it's not an outermost '('
                if (opened > 0) {
                    result.append(c);
                }
                opened++;
            } else {
                opened--;
                // If opened > 0, it's not an outermost ')'
                if (opened > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}