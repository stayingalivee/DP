import java.io.*;
import java.util.*;

class Main{
    /**
     * https://atcoder.jp/contests/dp/tasks/dp_g
     * References: www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/11-Graph/Docs/longest-path-in-dag.pdf
     * 
     */ 

    static int[] dist;
    static int[] parentsCount;
    static LinkedList<Integer>[] adjList;
    static boolean[] visited;

    public static void dfs(int parent){

        visited[parent] = true;
        Iterator<Integer> iter = adjList[parent].iterator();

        while(iter.hasNext()){
            int child = iter.next();
            dist[child] = Math.max(dist[child], dist[parent] + 1);
            parentsCount[child]--;
            if(parentsCount[child] == 0)
                dfs(child);
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        dist = new int[n + 1]; 
        parentsCount = new int[n + 1]; 
        adjList = new LinkedList[n + 1];
        visited = new boolean[n + 1];

        for(int i = 0; i <= n; i++) adjList[i] = new LinkedList<>();
        Arrays.fill(dist, 0);
        Arrays.fill(parentsCount, 0);   // verbose

        for(int i = 0; i < m; i++){
            int from = in.nextInt();
            int to = in.nextInt();
            adjList[from].add(to); // populate adjacency list
            parentsCount[to]++;
        }
        for(int i = 1; i <= n; i++){
            if( !visited[i] && parentsCount[i] == 0)
                dfs(i);
        }

        int ret = 0;
        for(int i = 1; i <= n; i++)
            ret = Math.max(ret, dist[i]);
        
        System.out.println(ret);
    }
}