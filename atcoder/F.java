import java.util.*;
import java.io.*;

class Main{
    /**
     * https://atcoder.jp/contests/dp/tasks/dp_f
     * Longest Common Subsequence.
    */
    static ArrayList<Character> answer = new ArrayList<>();
    enum Path{
        I_MINUS_1, // upper cell
        J_MINUS_1, // left-side cell
        I_MINUS_1_J_MINUS_1 // upper left
    }

    private static void solver(String s, String t){
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        Path[][] parent = new Path[n + 1][m + 1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;   // +1 to the length
                    parent[i][j] = Path.I_MINUS_1_J_MINUS_1; // set parent
                } else{ 
                    int l1 = dp[i - 1][j]; // get upper row cell's length
                    int l2 = dp[i][j - 1]; // get left col cell's length
                    dp[i][j] = l1 > l2 ? l1 : l2; // get max length 
                    parent[i][j] = l1 > l2 ? Path.I_MINUS_1 : Path.J_MINUS_1;
                }
            }
        }
        getPath(dp, parent, n, m, s, t);
    }

    private static void getPath(int[][] dp, Path[][] parent, int n, int m, String s, String t){
        
        while(n > 0 && m > 0){
            
            if(s.charAt(n - 1) == t.charAt(m - 1))
                answer.add(s.charAt(n - 1));
            
            if(parent[n][m] == Path.I_MINUS_1_J_MINUS_1){
                n--; m--;
            } else if(parent[n][m] == Path.I_MINUS_1){
                n--;
            } else{
                m--;
            }
        }
        for(int i = answer.size() - 1; i>=0; --i){
            System.out.print(answer.get(i));
        }
    }        

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        String s = in.next();
        String t = in.next();

        if(s == "" || t == ""){
            System.out.println("");
            return;
        }

        solver(s, t);
    }
}