import Math;


/**
 * 
 */


class OrderMatters{



/**
 * Combination sum,
 * Given the target value N and an array of numbers, count the ways
 * to write N as a sum of those numbers.
 *
 * Example:
 * N = 4 -> target value/sum
 * allowed_numbers={1,2,3}
 * there is 7 different ways to denote N as a sum of numbers using allowed_numbers
 * 1+1+1+1, 1+1+2, 1+2+1, 1+3, 2+1+1, 2+2, 3+1
 * as shown above, repition is allowed and the order also matters.
 * ---------------------------------------------------------------------
 * Solution:
 * It helps to think about DP as 'What is important so far?'
 * i.e. what we need to know when we are at some position?
 * So let's take            1+2+1 into further consideration.
 * lets assume we are here      ^ (last number of the aggregated numbers)
 * it isn't important how many numbers are there before that number.
 * what matters is the sum we have calculated so far, in this case it is 1+2 = 3
 * One possible representation for the problem is the following:
 *  
 * i = the sum, 
 * int dp[i] = number of ways to get to that sum
 * In this example int dp[4] equals to 7
 * int dp[sum] = number_of_ways
 * we can initialize dp[0] = 1 and build up our case with transitions,
 * so what transitions can we make? if we have sum = 10 (so far) and the allowed_numbers are 1,2,and 3
 * then we can get sum 11, 12, or 13.
 * ---------------------------------------------------------------------
 * Implementation:
 * int dp[0] = 1
 *   for i in 1..N:
 *       for x in nums:
 *           dp[i] += dp[i-x] (Check for negative indices in your code!!!!!!)
 *       *   -----------------------------------------------
 * As shown above, we calculate the number of ways for each k such that 1 < k < N
 * we use that previously calculated number of ways for the sum k's, that is, k-nums[index] to calculate current number of ways for the current k.
 * THIS IS DYNAMIC PROGRAMMING, BITCH.
 * 
 * nums = {1,2,3}
 * N = 4
 * 
 * we expect the answer to be:
 * dp[0] = 1
 * dp[1] = 1
 * dp[2] = 2
 * dp[3] = 4
 * dp[4] = 7
 *
 *
 * --------------------------------------------------------------------------------
 * 
 * To help netter visualize the problem:
 * 
 * N=5;
 * nums = {1,2,4}
 * 
 * 
 * | N | 0 | 1 | 2 | 3 | 4 | 5  |
 * +---+---+---+---+---+---+----+
 * |sum| 1 | 1 | 2 | 3 | 6 | 10 |   
 * 
 * 
 * Supposed we at at index i = 3,
 * So far we know for a fact that for an N = 3 hwe have 3 diffrent ways to denote the sum
 * 1+1+1, 1+2, 2+1
 * But what is exactly happening in dp[i] += dp[i-nums[j]]
 * we know that to get to N = 3 we must be coming from some_previous_number + a NUMBER 
 * THAT EXISTS IN `int[] nums`.
 * this is very important,
 * we can actually say that some_previous_number has m ways + all possible numbers from 
 * nums that if we add previous number to we get current i.
 * for i = 3, we can say the following:
 *      dp[3] += dp[3-nums[0]] => dp[3-1] => dp[2]
 *      dp[3] += dp[3-nums[1]] => dp[3-2] => dp[1]
 * this is basically equivalent to saying that you get to i=3 from 
 * i=1 + 2 jumps becuase the number 2 exists in int[] nums
 * 
 * which means we can count the ways in i=1 BECAUSE WE HAVE WHATEVER WAY WE GOT TO i=1+jump
 * in this case jump = 2 and i =1, we can get 1+2 as a way to denote i=3 
 */

    private int[] NumofWays(int N, int[] nums, int l){
        
        int[] dp = new int[N+1];
        dp[0] = 1;

        for (int i=1; i<=N; i++){
            for( int j=0; j<l; j++){

                if(i-nums[j]>=0) dp[i] += dp[i-nums[j]];         
            }
        }
        return dp;
    }

    // another method to count, we push values into future indices that we haven't visited yet.
    private int[] NumofWays_pushForward(int N, int[] nums, int l){

        int[] dp = new int[N+1];
        dp[0] = 1;

        for(int i=0; i<N; i++){
            for(int j=0; j<l; j++){
                
                if(i+nums[j]< N+1) dp[i+nums[j]]+= dp[i];
            }
        }
        return dp;
    }    

    public static void main( String[] args) {

        int N = 5;   classic example N = 5  
        int[] nums ={1,2,4};   classic example nums = {1,2,4}
        int l = nums.length;
        
        OrderMatters sol = new OrderMatters();
        int[] dp = sol.NumofWays(N, nums, l);
        sol.print(dp, N+1);

        dp = sol.NumofWays_pushForward(N, nums, l);
        sol.print(dp, N+1);
    }

    private void print(int[] arr, int length){
        for (int i=0;i<length;i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }
}