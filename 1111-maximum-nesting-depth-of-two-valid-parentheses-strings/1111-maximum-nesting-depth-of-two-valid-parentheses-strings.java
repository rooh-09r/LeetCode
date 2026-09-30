class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] result = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                depth++;
                result[i] = depth % 2; // Assign based on current depth
            } else { // c == ')'
                result[i] = depth % 2; // Assign based on matching depth
                depth--;
            }
        }

        return result;
    }
}