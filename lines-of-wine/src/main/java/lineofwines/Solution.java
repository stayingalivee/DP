package main.java.lineofwines;

class Solution{

    /**
     * 
     * There are N wine bottles in a row, Each year you sell either the
     * loftmost or the rightmost bottle. the i'th wine has initial price
     * of p[i] and price of k*p[i] in the k'th year.
     * What is the maximum possible total profit?
     * ---------------------------------------------------------------
     * 
     * Give the following sample input: 2 4 6 2 5
     * the optimal solution for this is 1*2 + 2*5 + 3*2 + 4*4 + 5*6 = 64
     * we sell the left most bottle in the first year 1*2 and we get (4 6 2 5) bottles so far
     * then we sell the right most bottle, 2*5 and we get (4 6 2) bottles left
     * we sell right most bottle again and we get 3*2 and (4 6) bottles
     * we sell the left most then the right most bottle with prices of 4*4 and 5*6 respectively 
     * 
     * ----------------------------------------------------------------
     * Solution I, bottom-up:
     * 
     * We start with the following:
     * 
     *                   +-------------------+
     *                   |        L       R  |
     *                   | Y=2    <----->    |
     *                   |        2 4 6 2 5  |
     *                   | Y=2      <----->  |
     *                   +-------------------+
     * 
     * Notice that in any given state, we have two choices:
     *     1. Sell the leftmost bottle, we get the interval [L+1, R]
     *     2. Sell the rightmost bottle, and we get the interval [L, R-1]
     * And after selling the first wine bottle we get year Y=2.
     * Also, given a state like _ _ _ 2 5 _ _
     * we know that the current year is original_number_of_bottles - current_number_of_bottles + 1
     * Which means we can calculate the year at any given state and we don't need 
     * to store it's value somewhere, Also it's not necessary to be a dimension of dp
     * 
     * In any given state, what is important so far is actually L and R. 
     * and we will have dp[L][R] holds the max achievable price given L and R
     *  
     * In more practical terms, we can either sell rightmost or leftmost bottle
     * in which case dp[L][R] = max(y*p[L] + dp[L+1][R], y*p[R] + dp[L][R-1])
     * 
     * ! THE TRICKY PART HERE IS TO FIGURE OUT WHAT ORDER WE SHOULD GO THROUGHT THE STATES
     * Given the formula above, it's obvious that we need to solve for smaller intervals first!
     * because in order to compute d*p[L] + dp[L+1][R] then we need to know the value of
     * dp[L+1][R] prior to the computation.
     * 
     *  
     */



    public static void main(String[] args) {
        
        System.out.println("solutionnn");
                                
    }
}
