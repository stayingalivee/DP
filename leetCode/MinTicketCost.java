import java.util.*;


class Solution{

    private static int min(int a, int b, int c){
        return Math.min(a, Math.min(b,c));
    }

    private static int max(int a, int b){
        return Math.max(a,b);
    }

    private static void print(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(" "+arr[i]);
        }
        System.out.println();
    }

    public static int minCostTicket(int[] days, int[] cost){
        
        int[] dp = new int[days[days.length-1] + 1];
        Set<Integer> travelDays = new HashSet<>();
        for(int i = 0; i < days.length; i++) travelDays.add(days[i]);

        for(int i = 1; i < dp.length; i++){

            if(travelDays.contains(i))
                dp[i] = min(
                    dp[i - 1] + cost[0], 
                    dp[max(0, i-7)] + cost[1],
                    dp[max(0, i-30)] + cost[2]);
            else
                dp[i] = dp[i - 1];
        }
        //print(dp);
        return dp[dp.length - 1];
    }

    
    public static void main(String[] args) {
        
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] cost = {2, 7, 15};

        System.out.println(minCostTicket(days, cost));
        System.out.println(test(days, cost));
    }
}