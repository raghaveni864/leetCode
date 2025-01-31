class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        dfs(i + 1, s);
        t.add(candidates[i]);
        dfs(i, s - candidates[i]);
        t.remove(t.size() - 1);
    }
}
class Solution_dp {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // for each-target (from 1 to target), its dp[i][j] => so 3-D array dp[][][]
        List<List<List<Integer>>> dp = new ArrayList<>();
        Arrays.sort(candidates);

        for (int i = 1; i <= target; ++i) {
            List<List<Integer>> cur = new ArrayList<>();
            for (int j = 0; j < candidates.length; ++j) {
                if (candidates[j] > i) break;
                if (candidates[j] == i) {
                    ArrayList<Integer> one = new ArrayList<Integer>();
                    one.add(candidates[j]);
                    cur.add(one); // @note: one with proper <Integer>, or else unsupoorted operation error
                    break;
                }
                for (List<Integer> a : dp.get(i - candidates[j] - 1)) {
                    if (candidates[j] > a.get(0)) {
                        continue;
                    }

                    ArrayList<Integer> deepCopied = new ArrayList<>(a); // @note: must have
                    deepCopied.add(0, candidates[j]); // @note: largest at index=0 for the array
                    cur.add(deepCopied);
                }
            }
            dp.add(cur);
        }

        return dp.get(dp.size() - 1);
    }

}