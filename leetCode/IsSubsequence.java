import java.util.*;


/*
 * 392. Is Subsequence
 * -------------------------------------------------
 * Given a string s and a string t, check if s is subsequence of t.
 * You may assume that there is only lower case English letters in both s and t. 
 * t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * A subsequence of a string is a new string which is formed from the original string by 
 * deleting some (can be none) of the characters without disturbing the relative positions 
 * of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * -------------------------------------------------
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 * 
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 * -------------------------------------------------
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, 
 * and you want to check one by one to see if T has its subsequence. 
 * In this scenario, how would you change your code?
*/

// Binary search approach
class Solution{

    /*
    public boolean isSubsequence(String s, String t) {
        if(t==null || s== null)
            return true;
        if(s.equals("") && t.length() > 0)
            return true;
         if(t.equals("") && s.equals(""))
            return true;
        
        int lookingFor = 0;
        for(int i = 0; i < t.length(); i++){
            if(s.charAt(lookingFor) == t.charAt(i)){
                lookingFor++;
                if(lookingFor >= s.length())
                    return true;
            }
        }
        return false;
    }
    */

    private static int upperBound(List<Integer> arr, int target){  
        int start = 0;
        int end = arr.size() - 1;
        int bound = -1;  

        while (start <= end) {  
            int mid = (start + end) / 2;      
            if (arr.get(mid) < target)  
                start = mid + 1;  
            else {
                end = mid - 1;  
                bound = mid;
            }
        }  
        return bound;  
    }  

    public static boolean isSubsequence(String s, String t){
        if(t==null || s== null)               return true;
        if(s.equals("") && t.length() > 0)    return true;
        if(t.equals("") && s.equals(""))      return true;

        List<Integer>[] chars = new ArrayList[256]; // ascii charset
        for(int i = 0; i < t.length(); i++){
            if(chars[t.charAt(i)] == null)
                chars[t.charAt(i)] = new ArrayList<>();
            chars[t.charAt(i)].add(i); // char to indices mapping
        }
        
        int indexSoFar = -1;
        for(int i = 0; i < s.length(); i++){
            
            if(chars[s.charAt(i)] == null)  
                return false;
            
            int j = upperBound(chars[s.charAt(i)], indexSoFar);
            if(j == -1) return false;
            
            indexSoFar = chars[s.charAt(i)].get(j) + 1;
        }
        return true;
    }


    public static void main(String[] args) {
       
        String s = "bcd";
        String t = "uuuuuuuuuuuubcd";   
        
        System.out.println(s);
        System.out.println(t);
        System.out.println(isSubsequence(s, t));
    }
}