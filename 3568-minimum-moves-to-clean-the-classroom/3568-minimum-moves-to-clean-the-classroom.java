import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        List<int[]> litters = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int k = litters.size();
        int fullMask = (1 << k) - 1;
        if (fullMask == 0) return 0;
        
        int[][] litterIdx = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(litterIdx[i], -1);
        for (int i = 0; i < k; i++) {
            litterIdx[litters.get(i)[0]][litters.get(i)[1]] = i;
        }
        
        int[][][] maxEnergy = new int[m][n][1 << k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY, 0, energy});
        maxEnergy[startX][startY][0] = energy;
        
        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1], mask = curr[2], e = curr[3];
                
                if (mask == fullMask) return moves;
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        if (e - 1 < 0) continue;
                        
                        int nextE = e - 1;
                        char cell = classroom[nr].charAt(nc);
                        int nextMask = mask;
                        
                        if (cell == 'R') {
                            nextE = energy;
                        } else if (cell == 'L') {
                            int idx = litterIdx[nr][nc];
                            if (idx != -1) {
                                nextMask |= (1 << idx);
                            }
                        }
                        
                        if (nextE > maxEnergy[nr][nc][nextMask]) {
                            maxEnergy[nr][nc][nextMask] = nextE;
                            q.offer(new int[]{nr, nc, nextMask, nextE});
                        }
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}