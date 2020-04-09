import java.util.*;


/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * 
 * Example 1:
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * 
 * Example 2:
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */


 class Solution{

    /**
     * --------------------------------------------------------------------------------------------------
     * /////////////////////////////////////// dictionary \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
     *          1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
     *          A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
     * --------------------------------------------------------------------------------------------------
     * Let's take the String "206" into consideration, the possible decodings are [20, 6]
     * note that we cannot decode 0 on it's own as in [2, 0, 6] because there's no mapping from 0 to 
     * any letter. Whenever we have a 0 it has to be decoded with a number previous to it. Also we cannot
     * start with a 0, the string "043" for isntance, cannot be decoded to a meaningful representation.
     * another constraints is we cannot couple [2, 7] to get [27], as well as [28] and [29].
     * 
     * But let's first discuss the general case without having to think about those constraints, given 
     * the string "226", we can have a "Growing window" that growing window will consider the characters 
     * so far in the string. In out initial case we can assign the window.length = 1, a trivial case. 
     * And we can right away tell that we only have one  possible decoding that is, "2" -> B. 
     * Now the window grows and next up we consider 2 characters from the String, that is "22"
     * Now we do have 2 possible decodings, that is, [2, 2] and [22]. When we do the same for length = 3, 
     * we get the following decodings: [2, 2, 6], [2, 26], [22, 6]
     * 
     * The important thing to observe is that when we increase the window size, for each decoding 
     * possibility [a,b] where a<10 and b<10, those possiblities are multiplied by 2.
     * the reason is because when we are adding a third number c, we can either couple it with b 
     * [a,b] --> [a,bc] or just add to the the possbility set [a,b] --> [a,b,c]
     * but when we already have a coupled numbers as in [a, bc] and when we attempt to add a third one d,
     * we can only add it without coupling, [a,bc] --> [a, bc, d].
     * To summarize:
     *      1. for a given posibility [a,b] we can get either [a,b,c] or [a,bc]
     *      2. for a given posiblitiy [a,bc] we can only get [a,bc,d]
     * Now we can add our constraints to the problem:
     * when we enconter a 0 in the input string, as in 102. we can only generate [10,2]. Another 
     * constraint is we cannot generate 27, 28 or 29. we cannot decode multiples of 10 starting from 30. 
     * i.e. the string 140 should be non-decodable (is that even a word?)
     * Since we already know the basic case, we can build up our solution using the aforementioned
     * observations, but this will generates all possible decodings which is an overkill for this problem.
     * 
     * However, we don't need to generate all the possible decodings to figure out the number of decodings.
     * 
     * 
     *  
     * 
     * 
    */

    public static int numDecodings(String s) {

        if(s == null || s.equals("") || s.charAt(0) == '0') return 0;


        int[] dp = new int[s.length()];
        dp[0] = 1;
        dp[1] = 2;

        for(int i=2; i<dp.length; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        
        String s = "20678";

        System.out.println(numDecodings(s));

    }
 }