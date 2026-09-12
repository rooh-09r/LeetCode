import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // Store interval info: {l, r, weight, original_index}
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        
        // Sort indices by right endpoint (r)
        Arrays.sort(order, (a, b) -> Integer.compare(intervals.get(a).get(1), intervals.get(b).get(1)));
        
        int[] rights = new int[n];
        for (int i = 0; i < n; i++) {
            rights[i] = intervals.get(order[i]).get(1);
        }
        
        // dp[k][i] stores {weight, list of original indices}
        // State representation: best choice considering first `i` intervals with up to `k` picks
        long[] prevWeights = new long[n + 1];
        List<Integer>[] prevIndices = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            prevIndices[i] = new ArrayList<>();
        }
        
        for (int k = 1; k <= 4; k++) {
            long[] curWeights = new long[n + 1];
            List<Integer>[] curIndices = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                curIndices[i] = new ArrayList<>();
            }
            
            for (int p = 1; p <= n; p++) {
                int origIdx = order[p - 1];
                int l = intervals.get(origIdx).get(0);
                long w = intervals.get(origIdx).get(2);
                
                // Option 1: Exclude interval p
                long bestWeight = curWeights[p - 1];
                List<Integer> bestIndices = curIndices[p - 1];
                
                // Option 2: Include interval p
                // Find last interval ending strictly before current start `l`
                int j = bisectLeft(rights, l);
                
                long candidateWeight = prevWeights[j] + w;
                List<Integer> candidateIndices = new ArrayList<>(prevIndices[j]);
                candidateIndices.add(origIdx);
                Collections.sort(candidateIndices);
                
                // Compare candidate vs best excluded option
                if (candidateWeight > bestWeight) {
                    curWeights[p] = candidateWeight;
                    curIndices[p] = candidateIndices;
                } else if (candidateWeight == bestWeight) {
                    if (compareLexicographically(candidateIndices, bestIndices) < 0) {
                        curWeights[p] = candidateWeight;
                        curIndices[p] = candidateIndices;
                    } else {
                        curWeights[p] = bestWeight;
                        curIndices[p] = bestIndices;
                    }
                } else {
                    curWeights[p] = bestWeight;
                    curIndices[p] = bestIndices;
                }
            }
            
            prevWeights = curWeights;
            prevIndices = curIndices;
        }
        
        List<Integer> resultList = prevIndices[n];
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    
    // Binary search for first index where rights[idx] >= target
    private int bisectLeft(int[] rights, int target) {
        int low = 0, high = rights.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (rights[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
    private int compareLexicographically(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.size(), b.size());
    }
}