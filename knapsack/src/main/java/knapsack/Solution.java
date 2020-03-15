package main.java.knapsack;


/**
 * 
 * This doc is inspired by
 *  https://medium.com/@fabianterh/how-to-solve-the-knapsack-problem-with-dynamic-programming-eb88c706d3cf
 * 
 * 
 * ---------------------------------------------------------------------------
 * --------------------------- Knapsack problem ------------------------------
 * ---------------------------------------------------------------------------
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
 * while keeping your bag intact. 
 * 
 * How'd you solve the problem?
 * 
 * it helps to think about dynamic programming as what is important so far (credits to errichto/youtube)
 * in this case, we care about the total value and the total weight. Hence we have
 * two states for our problem. Already we know that the solution is going to 
 * optimize some value in dp[i][j] states.
 * We also know that in DP, in order to solve for i = n (total number of items),
 * we need to solve for i-1 first, and prior to that i-2 and so on. This is also
 * true for w=10, we need to solve for w-1=9 and prior tp that w-2... to 0.
 * 
 * We can construct a two dimensional array (table) with columns being the current weight
 * and rows being the currently used items in the bag.
 * 
 * 
 * table of values mapped to weights:
 *   n=   1    2    3    4
 * +---+----+----+----+----+
 * | V | 10 | 40 | 30 | 50 |
 * +---+----+----+----+----+
 * | W | 5  | 4  | 6  | 3  |
 * +---+----+----+----+----+
 *
 * 
 *                          <----------------- W ---------------------> 
 *                    +---+---+---+---+---+---+---+---+---+---+---+---+
 *                    |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|
 *                    +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  ^ | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 1 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 2 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  n +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 3 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 4 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  v +---+---+---+---+---+---+---+---+---+---+---+---+
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
 * we cannot put that item in the bag, then starting from j = 5, we can actually 
 * start making our decision, we have 2 options:
 *      1. put the item.
 *      2. don't
 * In this case, it's pretty obvious that putting the item in the bag will maximize
 * the value (10 > 0, DUUH), and we get the following table

 *                          <----------------- W ---------------------> 
 *                    +---+---+---+---+---+---+---+---+---+---+---+---+
 *                    |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|
 *                    +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  ^ | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 1 | 0 | 0 | 0 | 0 | 0 | 10| 10| 10| 10| 10| 10|
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 2 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  n +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 3 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 4 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  v +---+---+---+---+---+---+---+---+---+---+---+---+
 * 
 * ! so far so good
 * 
 * Now we get to n=2, which means we have 2 items to fill, BUT we already SOLVED
 * for n = 1 and we know for sure which is the best decision for that item with the
 * given max weight capacity, so we're gonna use that information to help us solve
 * for n = 2!!. But in more general terms, let's actually not talk about item #2 
 * let's generalize and solve for item #i
 * 
 * 1. To calculate the maximum value for item i, we first need to compare the weight
 * for the item i with maximum weight capacity, if the item i weights more than the
 * maximum capacity we cannot include it. Hence making the calculations doesn't make sense
 * and in that case, the solution for the problem is whatever value we obtain without 
 * chosing the item i, technically, it's the value in the row above with the same column.
 * 
 * 2. However, if the weight for item i is less than the max capacity then we have
 * the option to include that item. if it potentially increases the maximum obtainable
 * value then we can chose it. 
 * ? The maximum optainable value after including item i is:
 * ?     The value of item i itself + the maximum value that can be obtained
 * !     with the remaining capacity of our knapsack.
 * 
 * therefore at row i column j (which represents the maximum value we can obtain there)
 * ! we would pick either the maximum value that we can obtain without item i,
 * ! or the maximum value that we can obtain with item 1, whichever is larger.
 * 
 * In the below table, row 3 column 5 (item 2 with max capacity of 4), we can chose
 * to either include the item 2 (which weighs 4 units) or not. if we choose NOT to 
 * onclude it, the maximum value we can obtain is the same as if we only have item 1
 * to choose from (which is found in the row above). If we want to include item 2
 * (value 40 weight 4), the maximum value we can obtain with item 2 is the value of
 * item 2 (40) + the maximum value we can obtain with the REMAINING capacity
 * 
 * at row 3 column 10 (item 10 capacity 9), again, we can choose to either
 * include item 2 or not. if we choose not to, the maximum value we can obtain is
 * the value in the row above it, (by including only item 1 of value 10). If we
 * choose to include item 2 (value 40, weight 4), then we have remaining capacity 
 * of 9 - 4 (we are at capacity 9) = 5.
 * ! WE ALREADY SOLVED FOR MAX CAPACITY = 5, WE CAN USE THAT VALUE IN OUR CALCULATION
 * (AND THAT IS THE CRUX OF DYNAMIC PROGRAMMING!!!!!!!!!!!!!!!!!!!!!!!!!!!)
 *  We can find out the maximum value that can be obtained with a capacity of 5 by
 * simply looking at the row above were j = 5. Thus, the maximum value we can obtain
 * by including item 2 is 40 (the value of item 2) + 10 = 50
 * now we compare our new value (50) with the older value (10) and pick whichever 
 * is larger. and we have the following table updated
 * 
 *
 *                          <----------------- W ---------------------> 
 *                    +---+---+---+---+---+---+---+---+---+---+---+---+
 *                    |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10|
 *                    +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  ^ | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 1 | 0 | 0 | 0 | 0 | 0 | 10| 10| 10| 10| 10| 10|
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 2 | 0 | 0 | 0 | 0 | 40| 40| 40| 40| 40| 50| 50|
 *                  n +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 3 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  | +---+---+---+---+---+---+---+---+---+---+---+---+
 *                  | | 4 | 0 |   |   |   |   |   |   |   |   |   |   |
 *                  v +---+---+---+---+---+---+---+---+---+---+---+---+
 * 
 *
 * Now go ahead and fill the rest of the table.
 * 
 */


class Solution {


    public static void main(String[] args) {
        
        int max = 10; // max allowed weight
        int n = 4; // total number of items

        
        int[] v = {10, 40, 30, 50};  // values
        int[] w = {5, 4, 6, 3};      // weights

        
        knapsack(v, w, max, n);
    }

    public static void knapsack(int[] v, int[] w, int max, int n){
        
        int[][] dp = new int [n+1][max+1];
        

        for(int i=1; i<n+1; i++){ // iterates on n in the table above

            for(int j=1; j< max+1; j++){ // iterates on w in the table above

                // Solve for the current item i and max capacity j.
                // remember that we dont care about all values of w and v.
                // we only care about the values from v[0] to v[i] so far. (as well as for w)
                
                // j here is the max weight capacity we are solving for IN THIS PASS.
                if(w[i-1] <= j) { // to prevent taking items with weights bigger than the current weight capacity.  
                    
                    
                    int emptyCapacity = j - w[i-1]; // i.e. for item 2 (weight 4) and current j = 9 , 9-4 = 5 is the current empty usable capacity
                    
                    // dp[i-1][j] simulates not taking the current item of index i-1, 
                    // ! v[i-1]+dp[i-1][emptyCapacity] IS THE CRUX OF DYNAMIC PROGRAMMING.
                    // ! we use older computed optimal values to solve current state.
                    // ! given emptyCapacity of 5, we already have computed what is the best value for that and stored it
                    // ! in dp[i-1][5], so we just simply add that value with whatever currently we have in v[i-1]
                    dp[i][j] = Math.max(dp[i-1][j], v[i-1]+dp[i-1][emptyCapacity]);

                } else { // if current item cannot be taken, then we just copy the values of the items from i-1 row
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        printState(dp, n+1, max+1);
    }
   

    private void printState(int[][] dp, int n, int m){
        
        System.out.print("       ");
        for(int col =0; col< m; col ++) System.out.print(col+"  |  ");
        System.out.println("\n-------------------------------------------------------------------------------");


        for(int i=0; i<n; i++){
            System.out.print(i+" | ");
            for(int j=0; j<m; j++){
                System.out.print("   "+dp[i][j]+"  ");
            }
            System.out.println();
        }
    }

    
     /**
     * We don't need to call this function anywhere in our code in java. the intital value for arrays is already 0. 
     * If you're using some other langauge, you might need use this method.
     */
    private void initializeState(int[][] dp, int n, int m){

        for(int i=0; i<n; i++){
            dp[i][0] = 0;
        }
        
        for(int j=0; j<m; j++){
            dp[0][j] = 0;
        }

    }


}


