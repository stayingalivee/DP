package main.java.knapsack;

import javax.xml.crypto.dsig.keyinfo.KeyName;

/**
 * 
 * knapsack problem
 * 
 * Suppose you are a theif that broke into a house. the house has exactly 4 items. 
 * Being the cheap theif you are, you only have a shitty bag that can 
 * hold 10 units of weight, otherwise it'll be ripped from the extra weight.
 * 
 * The items you have found has different weights and values like below.
 * +---+----+----+----+----+
 * | V | 10 | 40 | 30 | 50 |
 * +---+----+----+----+----+
 * | W | 5  | 4  | 6  | 3  |
 * +---+----+----+----+----+
 * 
 * What is the best combination that maximizes value 
 * while keeping your shitty bag from being ripped. 
 * 
 * How'd you solve the problem?
 * 
 * it helps to think about dynamic programming as what is important so far
 * in this case, we care about the total value and the total weight. Hence we have
 * two states for our problem. Already we know that the solution is going to 
 * optimize some value in dp[i][j] states.
 * We also know that in DP, in order to solve for i = n (total number of items),
 * we need to solve for i-1 first, and proir to that i-2 and so on. This is also
 * true for w=10, we need to solve for w-1 -> 9 and prior tp that w-2... to 0
 * 
 * We can construct a two dimensional array (table) with columns being the current weight
 * and rows being the number of used items in the bag.
 * 
 * 
 * table of values mapped to weights:
 *   n =  1    2   3     4
 * +---+----+----+----+----+
 * | V | 10 | 40 | 30 | 50 |
 * +---+----+----+----+----+
 * | W | 5  | 4  | 6  | 3  |
 * +---+----+----+----+----+
 *
 * 
 *         <----------------- W ---------------------> 
 *   +---+---+---+---+---+---+---+---+---+---+---+---+
 *   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|
 *   +---+---+---+---+---+---+---+---+---+---+---+---+
 * ^ | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
 * | +---+---+---+---+---+---+---+---+---+---+---+---+
 * | | 1 | 0 |   |   |   |   |   |   |   |   |   |   |
 * | +---+---+---+---+---+---+---+---+---+---+---+---+
 * | | 2 | 0 |   |   |   |   |   |   |   |   |   |   |
 * n +---+---+---+---+---+---+---+---+---+---+---+---+
 * | | 3 | 0 |   |   |   |   |   |   |   |   |   |   |
 * | +---+---+---+---+---+---+---+---+---+---+---+---+
 * | | 4 | 0 |   |   |   |   |   |   |   |   |   |   |
 * v +---+---+---+---+---+---+---+---+---+---+---+---+
 * 
 *
 * At first glance we can build our base case where n = 0 and w = 0, for those cases
 * the total value is plain 0, hence we filled those rows and columns with only 0s.
 * 
 * Now we get to the hardest part of the problem.
 * given a number of items to pick i, and a total max weight j,
 * we can either pick that item i or not. if we pick that item we need to make sure
 * that picking said item will maximize the value given the constant weight j,
 * otherwise we are better off not picking it and we continue with our old values.
 * 
 * Now for n = 1 we are solving when we only have 1 item to pick, that is 
 * the item V = 10 with W = 5. The item's weight = 5 so for j = 0->4
 * we cannot put that item in the bag, 
 * 
 * 
 * 
 */


public class Solution {


    public static void main(String[] args) {
        
        int max = 10; // max allowed weight
        int n = 4; // total number of items

        int[] v = {10, 40, 30, 50};
        int[] w = {5, 4, 6, 3};


        new Solution().knapsack(v, w, max, n);

    }

    private void knapsack(int[] v, int[] w, int max, int n){
        
        

    }

}


