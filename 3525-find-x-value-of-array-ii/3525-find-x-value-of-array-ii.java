class Solution {
    static class Node {
        int prod;
        int[] cnt; // cnt[r] = number of prefixes in this segment with product mod k = r

        Node(int k) {
            prod = 1 % k;
            cnt = new int[k];
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    private Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        for (int r = 0; r < k; r++) {
            res.cnt[r] = left.cnt[r];
        }
        for (int r = 0; r < k; r++) {
            if (right.cnt[r] > 0) {
                int newRem = (left.prod * r) % k;
                res.cnt[newRem] += right.cnt[r];
            }
        }
        return res;
    }

    private void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            tree[node] = new Node(k);
            int val = nums[start] % k;
            tree[node].prod = val;
            tree[node].cnt[val] = 1;
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid, nums);
        build(2 * node + 1, mid + 1, end, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            int rem = val % k;
            tree[node] = new Node(k);
            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        if (r <= mid) {
            return query(2 * node, start, mid, l, r);
        }
        if (l > mid) {
            return query(2 * node + 1, mid + 1, end, l, r);
        }
        Node left = query(2 * node, start, mid, l, r);
        Node right = query(2 * node + 1, mid + 1, end, l, r);
        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);
            Node resNode = query(1, 0, n - 1, start, n - 1);
            ans[i] = resNode.cnt[x];
        }

        return ans;
    }
}