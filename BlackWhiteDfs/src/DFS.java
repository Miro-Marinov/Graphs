import java.util.HashSet;


public class DFS {

  private HashSet<Integer> used = new HashSet<Integer>();

  public DFS(UnweightedGraph G, int source) {
    dfs(G, source);
  }

  public void dfs(UnweightedGraph G, int root) {
    System.out.println(root);
    used.add(root);
    Iterable<Integer> adj = G.adj(root);
    for (Integer i : adj)
      if (!used.contains(i))
        dfs(G, i);
  }
}
