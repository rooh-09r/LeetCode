class Solution {
public:
    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = static_cast<int>(parent.size());
        vector<vector<int>> adj(n);
        for (int i = 1; i < n; ++i) {
            adj[parent[i]].push_back(i);
        }

        vector<int> newParent = parent;
        vector<int> lastSeen(26, -1);

        auto findParentsDfs = [&](auto& self, int u) -> void {
            int idx = s[u] - 'a';
            int prev = lastSeen[idx];
            if (prev != -1) {
                newParent[u] = prev;
            }
            lastSeen[idx] = u;

            for (int v : adj[u]) {
                self(self, v);
            }

            lastSeen[idx] = prev;
        };

        findParentsDfs(findParentsDfs, 0);

        vector<vector<int>> finalAdj(n);
        for (int i = 1; i < n; ++i) {
            finalAdj[newParent[i]].push_back(i);
        }

        vector<int> ans(n, 0);
        auto countSubtreeDfs = [&](auto& self, int u) -> int {
            int sz = 1;
            for (int v : finalAdj[u]) {
                sz += self(self, v);
            }
            ans[u] = sz;
            return sz;
        };

        countSubtreeDfs(countSubtreeDfs, 0);
        return ans;
    }
};