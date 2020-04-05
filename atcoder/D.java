import java.util.*;

class Main{


    private static int solver(int[] w, int[] v, int n, int max){
        
        int[][] dp = new int[n + 1][max + 1];
        for(int[] row: dp) Arrays.fill(row, 0);


        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= max; j++){

                if(w[i-1] <= j) { 
                    int emptyCapacity = j - w[i-1]; 
                    dp[i][j] = Math.max(dp[i-1][j], v[i-1] + dp[i-1][emptyCapacity]);
                     
                } else { 
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[n][max];
    }  


    // space optimized version, weight is the dimension to dp   
    private static long solverOneDimension(int[] w, int[] v, int n, int max){
        
        long[] dp = new long[max + 1];
        dp[0] = 0;

        for(int i = 1; i <= n ;i++){

            int value = v[i - 1];
            int weight = w[i - 1];

            for(int weight_already = max - weight; weight_already >= 0; --weight_already) {
                
                    dp[weight_already+weight] = Math.max(dp[weight_already+weight], dp[weight_already] + value);
                }
        }

        return dp[max];
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
