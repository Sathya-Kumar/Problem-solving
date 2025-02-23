public class ProfitableSchemes {
    /**
     *879. Profitable Schemes
     *
     * link --> https://leetcode.com/problems/profitable-schemes/description/
     *
     * There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.
     *
     * Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.
     *
     * Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
     * Output: 2
     * Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
     * In total, there are 2 schemes.
     * Example 2:
     *
     * Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
     * Output: 7
     * Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
     * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
     *
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 0 <= minProfit <= 100
     * 1 <= group.length <= 100
     * 1 <= group[i] <= 100
     * profit.length == group.length
     * 0 <= profit[i] <= 100
     *
     *
     *
     *
     * solution discussion:
     *
     * Approach
     * This problem is very similar to the classic knapsack problem. The difference between the two is that the classic knapsack problem has only one capacity constraint, but this problem has two constraints: the upper limit of the number of membersn, and the lower limit of the profit generatedminProfit
     *
     * Through the practice of the classic knapsack problem, we know that the classic knapsack problem can be solved using two-dimensional dynamic programming: the two dimensions represent the constraints of items and capacity respectively. For the above two limitations of this question, we can think of using 3D dynamic programming to solve it.
     *
     * The three dimensions of the solution to this problem are: the currentlyavailable crime, the number ofselected members, and the lower limit of thecurrent profit
     *
     * According to the above analysis, we can define a three-dimensional arraydpas the state of dynamic programming, wheredp[i][j][k]means thatjemployees are selected in the firsticrime, and The total number of profitable plans where the working profit is at leastk. Assuming that the length of thegrouparray islen, then without considering the modulo operation, the final answer is:
     *
     * ∑
     * i=0
     * n
     * ​
     *  dp[len][i][minProfit]
     *
     * So we can create a new three-dimensional arraydp[len+1][n+1][minProfit+1]and initializedp[0][0][0]=1. Next, we analyze the state transition equation. For each crimei, we have two cases of beingable to workandunable to workaccording to the upper limit of the current number of workersj:
     *
     * If the current crimeicannot be done, then obviously:
     * dp[i][j][k]=dp[i−1][j][k]
     *
     * If the current crimeican be done, assume the current group number isgroup[i], and the work profit isprofit[i], then without considering the modulo operation, there are:
     * dp[i][j][k]=dp[i−1][j][k]+dp[i−1][j−group[i]][max(0,k−profit[i])]
     *
     * Since the third dimension we defined is that the working profit is at leastkrather than the working profit is exactlyk, the third dimension on the right side of the above state transition equation ismax(0,k−profit[i])and not k−profit[i]
     *
     * Complexity
     * Time complexity:
     * O(len×n×minProfit), where len is the length of the array group.
     * The total number of states that need to be calculated by dynamic programming isO(len×n×minProfit), and the time of each state needsO(1)time to calculate.
     *
     * Space complexity:
     * O(n×minProfit)
     * To implement space optimization, we need to create a two-dimensional arraydpwithn+1rows andminProfit+1columns.
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // revise knapsack problem to understand this program
        int MOD = (int)1e9 + 7;
        int len = group.length;
        int[][][] dp = new int[len+1][n+1][minProfit+1];
        dp[0][0][0] = 1;
        for(int i=1; i<=len; i++) {
            int members = group[i-1], earn = profit[i-1];
            for(int j=0; j<=n; j++) {
                for(int k=0; k<=minProfit; k++) {
                    if(j < members) {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                    else {
                        dp[i][j][k] = (dp[i-1][j][k] + dp[i-1][j-members][Math.max(0, k-earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for(int j=0; j<=n; j++){
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;
    }
}
