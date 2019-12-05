package solution;


import java.lang.Math;
// Solving rod cutting problem.
// First approach: recursion.
// Second apptoach: dynamic programming.

// This solution is meant to be a base case study for the fundamental difference between recursion and dynamic programming.

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

	// Dynamic progrmming approach, no recursion, the solution is built up from a base case.
	public int bottom_up_cut_road(int[] p, int n)
	{
		
		int[] r = new int[n+1];
		r[0] = 0;
		for(int i=1; i<=n; i++){

			int q=-1;
			for(int j=1; j<=i; j++){
				q = Math.max(q, p[j-1] + r[i-j]);
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
