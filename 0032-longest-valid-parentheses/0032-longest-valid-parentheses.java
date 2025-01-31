class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 2) == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s.charAt(j - 1) == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.max(ans, f[i]);
            }
        }
        return ans;
    }
}

//////

class Solution_noExtraSpace {
    public int longestValidParentheses(String s) {
        int res = 0, left = 0, right = 0, n = s.length();

        // from left to right, '(()' => will never hit left==right
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') ++left;
            else ++right;

            if (left == right) res = Math.max(res, 2 * right);
            else if (right > left) left = right = 0;
        }

        // from right to left, '())' => will never hit left==right
        left = right = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == '(') ++left;
            else ++right;

            if (left == right) res = Math.max(res, 2 * left);
            else if (left > right) left = right = 0;
        }
        return res;

    }
}

//////

class Solution_stack {
    public int longestValidParentheses(String s) {
        Stack<Integer> sk = new Stack<>();
        int start = 0;
        int result = 0;
        for (int i = 0;i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                sk.push(i);
            } else {
                if (sk.empty()) {
                    start = i + 1;
                } else {
                    sk.pop();
                    result = Math.max(result, sk.isEmpty() ? i - start + 1 : i - sk.peek());
                }
            }
        }
        return result;

    }
}