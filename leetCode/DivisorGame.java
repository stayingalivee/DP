import java.util.*;


/**
 * 1025. Divisor Game 
 * -------------------------------------------------------------------------------------
 * Alice and Bob take turns playing a game, with Alice starting first.
 * Initially, there is a number N on the chalkboard.  
 * On each player's turn, that player makes a move consisting of:
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 * --------------------------------------------------------------------------------------
 * Example 1:
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * 
 * Example 2:
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 * --------------------------------------------------------------------------------------
 */

class Solution {

    public static boolean divisorGame(int n) {

        boolean aliceWins = helper(n, 1);
        return aliceWins;
    }

    /**
     * Recursive solution.
     * player = 1 for Alice
     * player = 0 for Bob
     */
    private static boolean helper(int n, int player){

        // if the player is bob and n == 1 he loses --> alice wins
        if(player == 0 && n == 1)
            return true;
        
        for(int i = 1; i < n; i++){
            if(n % i == 0){
                return helper(n - i, (player + 1) % 2);
            }
        }

        return false;
    }


  

    public static void main(String[] args) {
        
        // driver code.
        int n = 50;
        System.out.println(divisorGame(n));
        

        // or you can just return true. alice always wins.        
    }
}
