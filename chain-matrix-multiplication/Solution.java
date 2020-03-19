import java.util.*;


class Solution{
        
    // find the minimum number of multiplications required to achieve  
    // A1*A2*A3*A4*A5*A6
    // A possible way of grouping the above expression is
    // (((A1*A2)*A3)*A4)*(A5*A6)
    // note that this parenthesization is arbitrary. A different parenthesization could yield less number of multiplications.
    // The way we parenthesize matrices can have a dramatic impact on the cost of evaluation. Proof:
    // Consider the following matrices;
    // +-------+-------+------+
    // | A1    | A2    | A3   | 
    // |10x100 | 100x5 | 5x50 |
    // +-------+-------+------+
    //
    // generally speaking; A1 with [p,q] dimensions multiplied with A2 with [q,r] dimensions yeilds p*q*r calculations in total.
    // A1*A2 yields 10*100*5 evaluations --> 5000
    // (A1*A2)*A3 --> 7500 evaluations
    // A1*(A2*A3) --> 75000 evaluations
    // the first parenthesization is 10x faster. 
    // Considering the above, find the minimum number of evaluations required to evlauate the whole chain of matrices.
    private static class CostParanthesisPair{
        int[][] dp;
        int [][] parenthesis;
        CostParanthesisPair(int[][] dp, int[][] parenthesis){
            this.dp = dp;
            this.parenthesis = parenthesis;
        }
    }

    public static CostParanthesisPair matrix_chain_min_cost(int[] dims){

        int n = dims.length - 1;
        int[][] dp = new int[n+1][n+1];
        int[][] parenthesis = new int[n+1][n+1];

        // start by checking 2 matrices, once all pairs of matrices are done (len=2).
        // we can use the information calculated in pairs of matrices to evaluate the cost of len=3
        // simmilarly, we can use the cost calculated by len=3 to calculate len=4, and so on.

        // len is the chain length we're examining for the current pass
        for(int len = 2; len<=n; len++){
            
            // the offset at which we're going to start examining our chain from.
            for(int offset = 1; offset <= n - len + 1; offset++){

                // offset --> end forms a sliding window with length = len.
                int end = offset + len - 1;
                dp[offset][end] = Integer.MAX_VALUE;

                // iterate on the window and examine each point for a potential possible parenthesization
                // we assume that k is part of the left side parenthesis.
                // i.e. A1, A2, A3, A4, k==1 --> (A1, A2) (A3, A4)
                for(int k = offset; k <= end - 1; k++){

                    // calculate the total cost of multiplication at the current parenthesization
                    int scalar = dp[offset][k] + dp[k+1][end] + (dims[offset-1] * dims[k] * dims[end]); 

                    // update dp with the minimum parenthesization cost
                    if(scalar < dp[offset][end]){
                        dp[offset][end] = scalar;

                        // cache the minimum parenthesization position to construct the solution.
                        parenthesis[offset][end] = k;
                    }
                }

            }
        }
        return new CostParanthesisPair(dp, parenthesis);
    }

    private static void printOptimalParenthesization(int[][] parenthesis, int i, int j){

        if(i == j){
            System.out.print("A"+i);
        }
        else{
            System.out.print("(");
            printOptimalParenthesization(parenthesis, i, parenthesis[i][j]);
            printOptimalParenthesization(parenthesis, parenthesis[i][j] +1, j);
            System.out.print(")");
        }
    }



    public static void main(String[] args) {
        
        // driver code
        int[][] matrixDims = {
            {30, 35}, // A1
            {35, 15}, // A2
            {15, 5},  // A3
            {5, 10},  // A4 
            {10, 20}, // A5
            {20, 25}  // A6
        };
        
        // consider using this instead of matrixDims.
        // since Ai.columns == Aj.rows we can reduce the dims to one array instead.
        // Given a Matrix Ai, dimensions are at position i-1, and i
        int[] dims = {30, 35, 15, 5, 10, 20, 25};

        CostParanthesisPair pair = matrix_chain_min_cost(dims);
        int[][] cost = pair.dp;
        int[][] parenthesis = pair.parenthesis;

        for(int i=0; i<cost.length; i++){
            for(int j=0 ;j<cost[0].length; j++){
                System.out.print("\t"+cost[i][j]);
            }
            System.out.println();
        }

        printOptimalParenthesization(parenthesis, 1, 6);
    }
}
