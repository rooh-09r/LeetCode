class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        double sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                if (num.charAt(i) == '?') {
                    qDiff++;
                } else {
                    sumDiff += num.charAt(i) - '0';
                }
            } else {
                if (num.charAt(i) == '?') {
                    qDiff--;
                } else {
                    sumDiff -= num.charAt(i) - '0';
                }
            }
        }

        // If the total number of '?' is odd, Alice can always force a win
        // Otherwise, Bob wins only if the sum difference perfectly balances with the '?' count difference
        return sumDiff + (qDiff / 2.0) * 9 != 0;
    }
}