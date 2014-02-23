import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class BFS {
  public BFS(UnweightedGraph G, int source) {
    bfs(G, source);
  }


  public void bfs(UnweightedGraph G, int cur) {
    Queue<Integer> q = new LinkedList<Integer>();
    HashSet<Integer> used = new HashSet<Integer>();

    q.add(cur);
    used.add(cur);

    System.out.println(cur);
    while (!q.isEmpty()) {
      Integer head = q.remove();

      for (Integer i : G.adj(head))
        if (!used.contains(i)) {
          q.add(i);
          used.add(i);
          System.out.print(i);
        }
      System.out.println();
    }
  }
}
