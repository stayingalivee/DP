import java.util.*;
import java.io.*;

class Main{

    
    public static void solver(String[] grid, int H, int W){
        
        final long MOD = (long) 1e9+7;
        long[][] dp = new long[H][W];

        int initialVal = 1;
        for(int i = 0; i < H; i++){
            if(grid[i].charAt(0) == '#')
                initialVal = 0;
            dp[i][0] = initialVal;
        }
        initialVal = 1;
        for(int i = 0; i < W; i++){
            if(grid[0].charAt(i) == '#')
                initialVal = 0;
            dp[0][i] = initialVal;
        }

        for(int i = 1; i < H; i++){
            for(int j = 1; j < W; j++){
                long up = grid[i - 1].charAt(j) == '.' ? dp[i - 1][j]: 0;
                long left = grid[i].charAt(j - 1) == '.' ? dp[i][j - 1]: 0;
                dp[i][j] = (up + left) % MOD;
            }
        }
        System.out.println(dp[H - 1][W - 1]);        

    }

    public static void main(String[] args){
        
        Scanner in = new Scanner(System.in);
        int H = in.nextInt();
        int W = in.nextInt();
        in.nextLine();

        String[] grid = new String[H];
        for(int i = 0; i < H; i++){
            grid[i] = in.nextLine();
        }
        solver(grid, H, W); 
    }
}
