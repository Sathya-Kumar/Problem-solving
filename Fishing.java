class Fishing {

    /**
     *You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:
     *
     * A land cell if grid[r][c] = 0, or
     * A water cell containing grid[r][c] fish, if grid[r][c] > 0.
     * A fisher can start at any water cell (r, c) and can do the following operations any number of times:
     *
     * Catch all the fish at cell (r, c), or
     * Move to any adjacent water cell.
     * Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.
     *
     * An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
     * Output: 7
     * Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.
     * Example 2:
     *
     *
     * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
     * Output: 1
     * Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish.
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10
     * 0 <= grid[i][j] <= 10
     */

    public int dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if(i<0 || j<0 || i>=m || j>=n || grid[i][j]==0) return 0;
        int temp = grid[i][j];
        grid[i][j] = 0;
        return temp + dfs(grid, i+1, j) + dfs(grid, i-1, j) + dfs(grid, i, j+1) + dfs(grid, i, j-1);
    }

    // public int dfs(int[][] grid, int i, int j, int count) {
    //     // System.out.println(i+" "+j+" "+count);
    //     int m = grid.length, n = grid[0].length;
    //     if(i<0 || j<0 || i>=m || j>=n || grid[i][j]==0) return 0;
    //     count += grid[i][j];
    //     grid[i][j] = 0;
    //     // dfs(grid, i+1, j, count);
    //     // dfs(grid, i-1, j, count);
    //     // dfs(grid, i, j+1, count);
    //     // dfs(grid, i, j-1, count);
    //     // System.out.println("return -> "+count);
    //     return dfs(grid, i+1, j, count) + dfs(grid, i-1, j, count) + dfs(grid, i, j+1, count) + dfs(grid, i, j-1, count);;
    // }

    public int findMaxFish(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] > 0) {
                    // int c = dfs(grid, i, j);
                    // System.out.println(c);
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }
}