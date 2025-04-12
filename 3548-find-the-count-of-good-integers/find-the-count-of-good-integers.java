class Solution {
    public long countGoodIntegers(int n, int k) {
        HashSet<String> set = new HashSet<>();
        int power = (int) Math.pow(10, (n - 1) / 2);
        int skip = n & 1;
        for (int i = power; i < power * 10; i++) {
            String s = Integer.toString(i);
            s += new StringBuilder(s).reverse().substring(skip);
            long palindrome = Long.parseLong(s);
            if (palindrome % k == 0) {
                char[] ch = s.toCharArray();
                Arrays.sort(ch);
                set.add(new String(ch));
            }
        }

        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        long res = 0;
        for (String s : set) {
            int[] cnt = new int[10];
            for (char c : s.toCharArray()) {
                cnt[c - '0']++;
            }
            long total = (n - cnt[0]) * fact[n - 1];
            for (int x : cnt) {
                total /= fact[x];
            }
            res += total;
        }
        return res;
    }
}