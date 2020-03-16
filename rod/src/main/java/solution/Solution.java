package solution;

import java.lang.Math;
/**
 * Solving rod cutting problem.
 * First approach: recursion.
 * Second apptoach: dynamic programming.
 * This solution is meant to be a base-case study for the fundamental difference between recursion and dynamic programming.
 * -------------------------------------------------------------------------------------------
 * Solution
 * -------------------------------------------------------------------------------------------
 * given a metal rod with length n and a list of prices p[k] for each k where k in [1,n]
 * determain how to cut the rod to maximize profit.
 * 
 * n = 5
 * 
 * <----- 5 ------>
 * +--+--+--+--+--+
 * |              |
 * +--+--+--+--+--+
 * 
 * list of prices:
 * 
 * +-----------------------+
 * | k | 1 | 2 | 3 | 4 | 5 |
 * +-----------------------+
 * | P | 2 | 3 | 5 | 7 | 8 |
 * +-----------------------+
 * 
 * it's obvious that we can make the cut 1 1 1 1 1 and sell each piece 
 * for 2$ resulting in 10$ total is the best we can do to maximize profit
 * 
 * 
 */


class Solution{

	// recursive top-down implementation
	public int cut_rod(int[] p, int n){

		if(n==0) {
			return 0;
		}
		int q = -1;
		for (int i=1;i<=n;i++){
			q = Math.max(q, p[i-1]+ cut_rod(p, n-i));
		}
		
		return q;
	}

	// Dynamic programming approach, store the value and return it if exist.
	public int memoized_cut_rod(int[]p, int n, int []r){
	
		if(r[n]>=0)
			return r[n];
		if (n==0)
			return 0;

		else{
			int q=-1;
			for(int i=1;i<=n;i++){
				q = Math.max(q, p[i-1]+memoized_cut_rod(p, n-i, r));
			}
			r[n] = q;
			return q;
		}
	}

	// Dynamic progrmming table approach, no recursion, the solution is built up from a base case.
	public int bottom_up_cut_road(int[] p, int n)
	{
		
		int[] r = new int[n+1];
		r[0] = 0;
		for(int i=1; i<=n; i++){

			int q=-1;
            
            // for each element, iterate on all previous elements that sums up to the current element 
            // and take the max sum among them.
            // as we solve for a given element i, we can use it's value to solve an element i+k
            // since we know that the price for i is the most optimal solution
            // we can reuse precomputed values to solve the current i+k
			for(int j=1; j<=i; j++){
				q = Math.max(q, p[j-1] + r[i-j]); // j-1 because r has additional element for 0 length
			}			
			r[i] = q;
		}
		return r[n];
	}

	public static void main(String[] args) {
		
		int[] p = {1,5,8,9,10,17,17,20,24,30};
		//int[] r = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}; // uncomment when memoized_cut_rod is called

		int q = new Solution().bottom_up_cut_road(p, 10);
		System.out.println(q);

	}

}
