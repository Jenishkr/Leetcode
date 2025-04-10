class Solution {
    static public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        char[] suffixReq = s.toCharArray();

        long res1 = 0;
        char[] range = Long.toString(finish).toCharArray();
        Long[][] dp = new Long[range.length][2];
        if (isSmaller(range, suffixReq)) res1 = solve(0, 0, range, suffixReq, limit, range.length, dp);

        long res2 = 0;
        range = Long.toString(start - 1).toCharArray();
        dp = new Long[range.length][2];
        if (isSmaller(range, suffixReq)) res2 = solve(0, 0, range, suffixReq, limit, range.length, dp);

        return res1 - res2;
    }

    static long solve(int i, int isStrict, char[] range, char[] suffixReq, int limit, int n, Long[][] dp) {
        if(i == n) return 1;
        if(dp[i][isStrict] != null) return dp[i][isStrict];
        if (i >= n - suffixReq.length) {
            if (isStrict == 1) {
                if (range[i] < suffixReq[i - (n - suffixReq.length)]) return 0;
                else if (range[i] > suffixReq[i - (n - suffixReq.length)]) return 1;
                else return dp[i][isStrict] = solve(i + 1, 1, range, suffixReq, limit, n, dp);
            }
            return 1;
        }
        int till = limit, num = (int) (range[i] - '0');
        if (isStrict == 1 || i == 0) till = Math.min(limit, num);
        long ans = 0;
        for (int j = 0; j <= till; j++) {
            ans += solve(i + 1, (i == 0 ? (j == num ? 1 : 0) : isStrict) & (num == j ? 1 : 0), range, suffixReq, limit, n, dp);
        }
        return dp[i][isStrict] =  ans;
    }

    static boolean isSmaller(char[] range, char[] sufffix) {
        int n1 = range.length, n2 = sufffix.length;
        if (n1 < n2) return false;
        if (n1 > n2) return true;
        for (int i = 0; i < n1; i++) {
            if (range[i] > sufffix[i]) return true;
            else if (range[i] < sufffix[i]) return false;
        }
        return true;
    }
}