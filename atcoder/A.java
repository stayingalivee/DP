import java.util.Scanner;
import java.util.Arrays;


class Solution{

    /**
 *  * A - Frog 1 /
 * 
 * Time Limit: 2 sec / Memory Limit: 1024 MB
 * Score : 100
 * 
 * There are N stones, numbered 1,2,…,N. For each i (1≤i≤N), the height of Stone i is hi
 * There is a frog who is initially on Stone 1. He will repeat the following action some number of times to reach Stone N:
 *     If the frog is currently on Stone i, jump to Stone i+1 or Stone i+2. Here, a cost of |hi−hj| is incurred, where j is the stone to land on.
 * Find the minimum possible total cost incurred before the frog reaches Stone N
 * ------------------------------------------------------------------------------------------------------------------------------------------------
 * Constraints
 *     All values in input are integers.
 *     2≤N≤105
 *     1≤hi≤104
 * ------------------------------------------------------------------------------------------------------------------------------------------------
 * Input
 * Input is given from Standard Input in the following format:
 * N
 * h1 h2…hN
 * 
 * Output
 * Print the minimum possible total cost incurred.
 * ----------------------------------------------------------------------------
 * Sample Input 1
 * 4
 * 10 30 40 20
 
 * Sample Output 1
 * 30
 * 
 * If we follow the path 1
 * → 2 → 4, the total cost incurred would be |10−30|+|30−20|=30
 * ----------------------------------------------------------------------------
 * Sample Input 2
 * 2
 * 10 10
 
 * Sample Output 2
 * 0
 * 
 * If we follow the path 1
 * → 2, the total cost incurred would be |10−10|=0
 * ----------------------------------------------------------------------------
 * Sample Input 3
 * 6
 * 30 10 60 10 60 50
 * 
 * Sample Output 3
 * 40
 * 
 * If we follow the path 1
 * → 3 → 5 → 6, the total cost incurred would be |30−60|+|60−60|+|60−50|=40.
 * ----------------------------------------------------------------------------
 */

    // recursive solution
    private static int helper(int[] nums, int i){
        if(i==0)
            return 0;
        if(i==1)
            return Math.abs(nums[1] - nums[0]);

        return Math.min(
            Math.abs(nums[i] - nums[i-1]) + helper(nums, i-1),
            Math.abs(nums[i] - nums[i-2]) + helper(nums, i-2));
    }

    // recursive with memoization aka dp but not really accepted in interviews as real dp so go figure.
    private static int helper(int[] nums, int[] cache, int i){

        if(cache[i] != -1)
            return cache[i];
        if(i==0)
            return cache[0] = 0;
        if(i==1)
            return cache[1] = Math.abs(nums[1] - nums[0]);

        cache[i] = Math.min(
            Math.abs(nums[i] - nums[i-1]) + helper(nums, cache, i-1),
            Math.abs(nums[i] - nums[i-2]) + helper(nums, cache, i-2));
        
        return cache[i];
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if(n==0){
            System.out.println(0);
            return;
        }
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = in.nextInt();
        int[] cache = new int[nums.length];
        Arrays.fill(cache, -1);

        System.out.println(helper(nums, cache, nums.length - 1));
    }
}