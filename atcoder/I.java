import java.util.*;
import java.io.*;

class Main{

     /*
     * https://atcoder.jp/contests/dp/tasks/dp_i
     */ 
 
    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        double[] dp = new double[n + 1];
        dp[0] = 1.0;
        for(int i = 0; i < n; i++){ // read coin probabilities
            double probabilityHead = in.nextDouble();
            for(int j = i + 1; j >= 0; j--){
                /**
                 * the probability taht we have j heads is the probaility that 
                 * we got a head * the probability of getting j-1 heads or the
                 * probability of not getting a head (1 - prob_head), which means
                 * we still have j heads to pick, hence dp[j].
                 */
                dp[j] = (j == 0 ? 0: dp[j - 1] * probabilityHead) + 
                dp[j] * (1 - probabilityHead);
            }

        }
        double ret = 0;
        for(int index = (n / 2) + 1; index <= n; index++) ret += dp[index];
        System.out.println(ret);
    }
}
