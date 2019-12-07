import Math;


class Solution{

    private int[] minimum_coins(int N, int[] nums, int l){
        
        int[] dp = new int[N+1];

        dp[0] = 0;
        for(int i=1;i<=N;i++) dp[i] = 1000; // should be INF, but 1000 is good for now, just don't be snarky and try >1000 values.

        for(int i=1; i<=N; i++){
            for(int j=0; j<l; j++){
                

                if(i-nums[j]>=0) dp[i] = Math.min(dp[i], dp[i-nums[j]]+1);         
            }
        }
        return dp;
    }

    public static void main( String[] args) {

        int N = 4; // classic example N = 4  
        int[] nums ={1,2,4}; // classic example nums = {1,2,3}
        int l = nums.length;
        
        Solution sol = new Solution();
        int[] dp = sol.minimum_coins(N,nums,l);
        sol.print(dp, N+1);
    }

    private void print(int[] arr, int length){
        for (int i=0;i<length;i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }
}