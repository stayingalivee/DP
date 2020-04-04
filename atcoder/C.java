
class Main{

    /**
     * https://atcoder.jp/contests/dp/tasks/dp_c
     */

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] dp = new int[3];

        for(int i = 0; i < n; i++){

            int[] temp = new int[3];
            int[] h = new int[3];
            for(int j = 0; j < 3; j++){
                h[j] = in.nextInt();
            }

            for(int k = 0; k < 3; k++){
                for(int x = 0; x < 3; x++){
                    if(k!=x)
                        temp[x] = Math.max(temp[x], dp[k] + h[x]);
                }
            }
            dp = temp;
        }
        System.out.println(Math.max(dp[0], Math.max(dp[1], dp[2])));
    }
}