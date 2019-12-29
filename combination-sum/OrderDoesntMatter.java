

class OrderDoesntMatter{


    // The problem is the same as `OrderMatters.java` (Check that one first), In this case, however, Order doesn't matter.
    // which means 1+2+1 is the same as 1+1+2 and should NOT be counter seperately.
    // surprisingly, this is a much harder problem to solve. 

    // In Dynamic programming, it helps to think "what is important so far?"
    // In the previous problem (OrderMatters), what is importat was the sum of the numbers so far,
    // But this state alone is not enough for the current problem.
    // Assume you want to pay a shop owner in coins, aside from the fact that he might hate your guts for handing him small change,
    // 1 cent + 2 cents still equals to 2 cents + 1 cent, in other terms,
    // 1+2 = 2+1 --> The shop owner wouldn't care about the particular order of how you're handing him the coins, he just wants his money.
    

    // Abstract ---------------------------------------------------------------
    // In abstract terms, given a set of numbers and a target, 
    // how many different "combinations" exist of which you can write the target as a sum of the numbers.
    
    // solution ---------------------------------------------------------------
    // we can sort the combinations, this will only allow on of the combinations, 
    // What makes sense is to ocunt the lexicographically sorted way, this is a unique reprenestation of (1+2+1, 1+1+2, and 2+1+1)
    // Then, in our already chosen elements not only the sum matters but also the last element. And the next element cannot be smaller than that.
    // This time we'll say that dp[sum][last] this is the number of ways to get the sum of s where `last` is the last used coin.
    // 



    public static void main(String[] args) {
        


    }


}
