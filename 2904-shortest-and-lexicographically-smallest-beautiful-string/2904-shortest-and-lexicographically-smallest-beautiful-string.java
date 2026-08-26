class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int countOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') countOnes++;
        }
        
        if (countOnes < k) return "";
        
        // Store indices of all '1's
        int[] ones = new int[countOnes];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[idx++] = i;
            }
        }

        String result = "";
        
        // Check every window containing exactly k ones
        for (int i = 0; i <= countOnes - k; i++) {
            int start = ones[i];
            int end = ones[i + k - 1];
            String sub = s.substring(start, end + 1);
            
            if (result.equals("") || sub.length() < result.length()) {
                result = sub;
            } else if (sub.length() == result.length() && sub.compareTo(result) < 0) {
                result = sub;
            }
        }
        
        return result;
    }
}