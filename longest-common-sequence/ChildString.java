
class ChildString{

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {

        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i=1;i<dp.length;i++){
            for(int k=1; k<dp[0].length; k++){
                
                if(s1.charAt(i-1) == s2.charAt(k-1))
                    dp[i][k] = dp[i-1][k-1]+1;
                else
                    dp[i][k] = Math.max(dp[i-1][k], dp[i][k-1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {

        String s1 = "SHINCHAN";
        String s2 = "NOHARAAA";
        int res = commonChild(s1, s2);
        System.out.println(res);
    }
}
