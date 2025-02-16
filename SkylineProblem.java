import java.util.*;

public class SkylineProblem {

    /**
     * 218. The Skyline Problem
     *
     * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
     *
     * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
     *
     * lefti is the x coordinate of the left edge of the ith building.
     * righti is the x coordinate of the right edge of the ith building.
     * heighti is the height of the ith building.
     * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
     *
     * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
     *
     * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
     *
     *
     *
     * Example 1:
     *
     *
     * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
     * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
     * Explanation:
     * Figure A shows the buildings of the input.
     * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
     * Example 2:
     *
     * Input: buildings = [[0,2,3],[2,5,3]]
     * Output: [[0,3],[5,0]]
     *
     *
     * Constraints:
     *
     * 1 <= buildings.length <= 104
     * 0 <= lefti < righti <= 231 - 1
     * 1 <= heighti <= 231 - 1
     * buildings is sorted by lefti in non-decreasing order.
     *
     *
     *
     * class Solution {
     *     public List<List<Integer>> getSkyline(int[][] B) {
     *         //think of this array as bars representing borders of buildings
     *         int[][] H = new int[2 * B.length][2];
     *         for (int i = 0; i < B.length; i++) {
     *             H[i * 2] = new int[]{B[i][0], -B[i][2]}; //left, -height
     *             H[i * 2 + 1] = new int[]{B[i][1], B[i][2]}; //right, height
     *         }
     *         //sort the bars based on position, use height as tie-breaker
     *         Arrays.sort(H, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
     *         //reason for not choosing priority queue is removal of given item other than root can be costly O(n)
     *         //reason for not choosing TreeSet is to accommodate duplicates
     *         var map = new TreeMap<Integer, Integer>(Comparator.reverseOrder()); //height is key, count is val
     *         map.put(0, 1); //one building of zero height is needed
     *         List<List<Integer>> res = new ArrayList<>();
     *         int prev = 0;
     *         for (int[] h : H) {
     *             //-ve height for left bar meaning validity of this building is starting, add it
     *             if (h[1] < 0) map.put(-h[1], map.getOrDefault(-h[1], 0) + 1);
     *             else { //+ve height for right bar meaning end of validity, remove it
     *                 map.put(h[1], map.get(h[1]) - 1);
     *                 if (map.get(h[1]) == 0) map.remove(h[1]); //could have been costly for priority queue
     *             }
     *             if (map.firstKey() != prev) { //max height in curr position that is diff from prev, add to skyline
     *                 prev = map.firstKey();
     *                 res.add(List.of(h[0], prev));
     *             }
     *         }
     *         return res;
     *     }
     * }
     */

        public List<List<Integer>> getSkyline(int[][] buildings) {
            int[][] H = new int[2*buildings.length][2];
            for(int i=0; i<buildings.length; i++){
                H[i*2] = new int[]{buildings[i][0], -buildings[i][2]};
                H[i*2+1] = new int[]{buildings[i][1], buildings[i][2]};
            }
            Arrays.sort(H, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

            var map = new TreeMap<Integer, Integer>(Comparator.reverseOrder());
            map.put(0, 1);
            int prev = 0;
            List<List<Integer>> res = new ArrayList<>();
            for(int[] h : H) {
                if(h[1] < 0) {
                    map.put(-h[1], map.getOrDefault(-h[1], 0) + 1);
                }
                else {
                    map.put(h[1], map.get(h[1]) - 1);
                    if(map.get(h[1]) == 0) map.remove(h[1]);
                }
                if(map.firstKey() != prev) {
                    prev = map.firstKey();
                    res.add(List.of(h[0], prev));
                }
            } return res;
        }



}
