import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> p1 = new ArrayList<>();
        List<int[]> p2 = new ArrayList<>();

        // Step 1: Collect coordinates of 1s in both images
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) p1.add(new int[]{r, c});
                if (img2[r][c] == 1) p2.add(new int[]{r, c});
            }
        }

        // Step 2 & 3: Count shift vectors using a 2D array offset
        // Offset is added to avoid negative indices (shifts range from -(n-1) to (n-1))
        int[][] count = new int[2 * n][2 * n];
        int maxOverlap = 0;

        for (int[] a : p1) {
            for (int[] b : p2) {
                int dr = b[0] - a[0] + n;
                int dc = b[1] - a[1] + n;
                count[dr][dc]++;
                maxOverlap = Math.max(maxOverlap, count[dr][dc]);
            }
        }

        return maxOverlap;
    }
}