import java.util.*;
public class MaximumProfitInJobScheduling {
    /**
     * 1235. Maximum Profit in Job Scheduling
     * Solved
     * Hard
     * Topics
     * Companies
     * Hint
     * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
     *
     * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
     *
     * If you choose a job that ends at time X you will be able to start another job that starts at time X.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
     * Output: 120
     * Explanation: The subset chosen is the first and fourth job.
     * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
     * Example 2:
     *
     *
     *
     * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
     * Output: 150
     * Explanation: The subset chosen is the first, fourth and fifth job.
     * Profit obtained 150 = 20 + 70 + 60.
     * Example 3:
     *
     *
     *
     * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
     * Output: 6
     *
     *
     * Constraints:
     *
     * 1 <= startTime.length == endTime.length == profit.length <= 5 * 104
     * 1 <= startTime[i] < endTime[i] <= 109
     * 1 <= profit[i] <= 104
     */
    class Job {
        int start;
        int end;
        int profit;
        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    class Solution {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            Job[] jobSchedule = new Job[startTime.length];
            for(int i=0; i<startTime.length; i++){
                jobSchedule[i] = new Job(startTime[i], endTime[i], profit[i]);
            }
            Arrays.sort(jobSchedule, (a, b) -> Integer.compare(a.end, b.end));
            return fetchMaxProfit(jobSchedule);
        }

        public int fetchMaxProfit(Job[] jobSchedule){
            int[] dp = new int[jobSchedule.length];
            dp[0] = jobSchedule[0].profit;
            int max = 0;
            for(int i=1; i<jobSchedule.length; i++) {
                dp[i] = Math.max(dp[i-1], jobSchedule[i].profit);
                for(int j=i-1; j>=0; j--) {
                    if(jobSchedule[j].end <= jobSchedule[i].start) {
                        dp[i] = Math.max(dp[i], jobSchedule[i].profit+dp[j]);
                        break;
                    }
                }
            }
            return dp[jobSchedule.length-1];
        }
    }

}
