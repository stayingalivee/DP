import java.util.*;


class Solution{
        
    // find the minimum number of multiplications required to achieve  
    // A1*A2*A3*A4*A5*A6
    // A possible way of grouping the above expression is
    // (((A1*A2)*A3)*A4)*(A5*A6)
    // note that this parenthesization is arbitrary. A different parenthesization could yield less number of multiplications.
    // The wat we parenthesize matrices can have a dramatic impact on the cost of evaluation. Proof:
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
    // Considering the above, find the minimum number of evaluations required to evlauate the whole chain of matrecis.

    public static int solution(int[][] matrixDims){

        int minCost = 0;



        

        
        return minCost;
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
    }
}
