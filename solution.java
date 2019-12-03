import Math;



class Solution{

    /**
    * Given a grid, find the path from the top-left corner to 
    * the bottom-right corner that minimuzes the sum of numbers
    * along the path.
    * 
    * you can only move right or down.
    * 
    * 
    * Solution:
    *      suppose you have the following input dp[3][4]
    * 
    *      3 2 1 3
    *      1 9 2 3
    *      9 1 5 4
    *      
    *      the goal is to find a path with the lowest possible cost
    *      we can define the cost as the aggregation of the numbers
    *      we visit along the path.
    *      In this case, the most optimal solution is the following path:
    *          - dp[0][0] -> dp[0][1] -> dp[0][2] -> dp[1][2] -> dp[1][3] -> dp[2][3]
    *
    *      Now let's take dp[1][2] = 2 in consideration:
    *          What is important so far? 
    *          it doesn't matter what exactly is the path 
    *          other than the score/cost so far (i.e. the sum of values along the path)
    *          In this case, the sum would be 
    *          dp[0][0]+dp[0][1]+dp[0][2] = 3+2+1 + current element(which is 2)
    *          
    *          BUT! to any given node we have two possible paths,
    *          one from the above cell, the other from the left cell.
    *          Solution? take the minimum of those two aggregate sum! ezpz.
    * 
    *      A possible solution to this problem is the following aggregated values represented in a two dimensional array
    *      
    *      aggregated_sum[i][j] = min(dp[i-1][j], dp[i][j-1]) + dp[i][j]     
    *      
    *       3   5  6  9
    *       4  13  7  10
    *      13  14  12 14
    *          
    *      after we get this result, we can 'backtrack' from the bottom-right corner to the top-left corner
    *       constructing the path by choosing the minimum value in the upper and left-side cell
    *
    *       pseudo-code
    *       path=[]
    *       path.append(min(aggregated_sum[i-1][j], aggregaated_sum[i][j-1]))
    *
    *       
    *       
    *
    */

    public static void main(String[] args) {
        
    }
}