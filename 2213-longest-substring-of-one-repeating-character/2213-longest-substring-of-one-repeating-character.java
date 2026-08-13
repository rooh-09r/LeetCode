class Solution {
    class Node {
        int maxLen;
        int prefixLen;
        int suffixLen;
        char leftChar;
        char rightChar;
        int len;

        Node(char c) {
            this.maxLen = 1;
            this.prefixLen = 1;
            this.suffixLen = 1;
            this.leftChar = c;
            this.rightChar = c;
            this.len = 1;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node L, Node R) {
        Node res = new Node();
        res.len = L.len + R.len;
        res.leftChar = L.leftChar;
        res.rightChar = R.rightChar;

        res.maxLen = Math.max(L.maxLen, R.maxLen);
        res.prefixLen = L.prefixLen;
        res.suffixLen = R.suffixLen;

        if (L.rightChar == R.leftChar) {
            res.maxLen = Math.max(res.maxLen, L.suffixLen + R.prefixLen);

            if (L.prefixLen == L.len) {
                res.prefixLen = L.len + R.prefixLen;
            }
            if (R.suffixLen == R.len) {
                res.suffixLen = R.len + L.suffixLen;
            }
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            chars[idx] = c;
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        chars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            result[i] = tree[1].maxLen;
        }

        return result;
    }
}