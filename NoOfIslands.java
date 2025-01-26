import java.util.*;
public class NoOfIslands {
    /**
     * 200. Number of Islands
     * Solved
     * Medium
     * Topics
     * Companies
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * Output: 1
     * Example 2:
     *
     * Input: grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * Output: 3
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     */
    public static void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if(i<0 || j<0 || i>=m || j>=n || grid[i][j]==0) return;
        grid[i][j] = 0;
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }
    public static int noOfIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) {
                    count++;
                    dfs(grid, i, j);
                }
            }
        } return count;
    }
}

class Main2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt(), m = sc.nextInt();
        int[][] a = new int[l][m];
        for(int i=0; i<l; i++){
            for(int j=0; j<m; j++){
                a[i][j] = sc.nextInt();
            }
        }
        NoOfIslands numOfIslands = new NoOfIslands();
        System.out.println(numOfIslands.noOfIslands(a));
    }
}
