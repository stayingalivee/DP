import java.util.*;


class Solution{

    /**
     * 62. Unique Paths
     * --------------------------------------------------------------------
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. 
     * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there? 
     * --------------------------------------------------------------------
     * Example 1:
     * Input: m = 3, n = 2
     * Output: 3
     * Explanation:
     * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down
     * 2. Right -> Down -> Right
     * 3. Down -> Right -> Right
     *  
     * Example 2:
     * Input: m = 7, n = 3
     * Output: 28
     * 
     * Constraints:
     * 1 <= m, n <= 100
     * It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
     * 
     * 
     * 
     * n = 3, m = 7;
     * +---+---+---+---+---+---+---+
     * | S | 1 | 1 | 1 | 1 | 1 | 1 |
     * +---+---+---+---+---+---+---+
     * | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
     * +---+---+---+---+---+---+---+
     * | 1 | 3 | 6 | 10| 15| 21| 28|
     * +---+---+---+---+---+---+---+
     */


    public static int uniquePaths(int m, int n) {
        
        // for a given index [i][j], the number of unqipue paths is [i-1][j] + [i][j-1]
        // we construct the solution with a base case i = j = 1 (there's only one path from i,j to i,j itself)
        // and we populate the values as we progress.
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                
                if(i > 0 && j > 0)
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                else
                    dp[i][j] = 1;

            }
        }
        return dp[n-1][m-1];
    }
    public static void main(String[] args) {
        System.out.println(uniquePaths(7, 3));        
    }
}