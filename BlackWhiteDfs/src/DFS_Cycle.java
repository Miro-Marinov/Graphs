import java.util.HashSet;
import java.util.LinkedList;


public class DFS_Cycle {

  private int[] edgeTo;
  private boolean foundCycle = false;
  private HashSet<Integer> used = new HashSet<Integer>();

  public DFS_Cycle(UnweightedGraph G, int source) {
    dfs_cycle(G, source, -1);
  }

  public void dfs_cycle(UnweightedGraph G, int cur, int prev) {
    if (foundCycle == true)
      return; // IN PREVIOUS RECURSION CALLS
    used.add(cur);
    for (Integer i : G.adj(cur)) {
      if (foundCycle == true)
        return; // IN CURRENT RECURSION CALL LOOP OMFG
      if (!used.contains(i)) {
        edgeTo[i] = cur;
        dfs_cycle(G, i, cur);

      }

      else if (i != prev) {
        foundCycle = true;
        LinkedList<Integer> cycle = new LinkedList<Integer>();
        cycle.push(i);
        for (int tr = cur; tr != i; tr = edgeTo[tr])
          cycle.push(tr);
        cycle.push(i);
        System.out.println(cycle.toString());
      }
    }
  }
}
