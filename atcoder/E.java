import java.util.*;

class Main{

    /**
     * https://atcoder.jp/contests/dp/tasks/dp_e
     * 
     * In this question, W <= 10>9 which is aa huge number to have as dp dimension
     * instead of maximizing value subject to max weight we minimize weight
     * subject to value.
     */
    private static int sum(int[] v){
        int sum = 0;
        for(int i = 0; i < v.length; i++){
            sum += v[i];
        }
        return sum;
    }

    // space optimized version, value is the dimension  
    private static long solverOneDimension(int[] w, int[] v, int n, int max){
        
        int sumValues = sum(v);
        long[] dp = new long[sumValues + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // iterate on items
        for(int i = 1; i <= n ;i++){

            int value = v[i - 1];
            int weight = w[i - 1];

            for(int value_already = sumValues - value; value_already >= 0; --value_already) {
                    dp[value_already + value] = Math.min(dp[value_already + value], dp[value_already] + weight);
            }
        }

        long answer = 0;
        
        for(int i = 0; i <= sumValues; i++) {
            if(dp[i] <= max) {
                answer = Math.max(answer, (long) i);
            }
        }
        return answer;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // number of items
        int max = in.nextInt(); // max weight
        int[] w = new int[n];
        int[] v = new int[n];
        
        for(int i = 0; i < n; i++){
            w[i] = in.nextInt();
            v[i] = in.nextInt();
        }
        System.out.println(solverOneDimension(w, v, n, max));
        
    }
}