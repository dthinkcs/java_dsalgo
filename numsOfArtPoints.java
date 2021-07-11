import java.util.*;
import java.io.*;

class Main {

  public static void main(String[] args) {
    // map reduce
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    // assuming Connected graph G
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        graph.add(new ArrayList<>());
    }
    
    for (int i = 0; i < m; i++) {
        int u = sc.nextInt() - 1;
        int v = sc.nextInt() - 1;
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
    
    // 1. isAP []
    boolean[] areAPs = new boolean[n];
    boolean[] visited = new boolean[n];
    int[] lows = new int[n];
    // Arrays.fill(lows, Integer.MAX_VALUE); coz sending level
    int[] times = new int[n];
    dfs(graph, 0, -1, 0, lows, times, areAPs, visited);
    int count = 0;
    for (int i = 0; i < n; i++) {
        if (areAPs[i])
            count += 1;
    }
    System.out.println(count);
  }
    
  static void dfs(ArrayList<ArrayList<Integer>> graph,
    int v,
    int parent,
    int level,
    int[] lows,
    int[] times,
    boolean[] areAPs,
    boolean[] visited) {
        visited[v] = true; // was SILLIEST ERROR In dfs
        lows[v] = level;
        times[v] = level;
        int indiChild = 0;
        for (int u: graph.get(v)) {
            if (u == parent) // 5
                continue;
                
            if (visited[u]) {
                lows[v] = Math.min(lows[v], lows[u]); 
            } else { // unvisited
                dfs(graph, u, v, level + 1, lows, times, areAPs, visited);
                lows[v] = Math.min(lows[v], lows[u]);
                if (lows[u] >= times[v] && parent != -1) // 2. 3.
                    areAPs[v] = true; 
                indiChild++; // 4.
            } 
        }
        if (indiChild > 1)
            areAPs[v] = true;
    }
}
