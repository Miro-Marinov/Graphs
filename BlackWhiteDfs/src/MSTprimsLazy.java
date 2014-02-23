import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MSTprimsLazy {

  PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>(11, new EdgeCmp());
  Queue<WeightedEdge> mstq = new LinkedList<WeightedEdge>();
  HashSet<Integer> used = new HashSet<Integer>();

  double totalweight = 0;

  public MSTprimsLazy(WeightedUndirectedGraph G) {
    mstPrims(G, 0);
  }

  private void mstPrims(WeightedUndirectedGraph G, int v) {
    scan(G, v);

    while (!pq.isEmpty()) {
      WeightedEdge e = pq.remove();
      if (used.contains(e.from()) && used.contains(e.to()))
        continue;
      totalweight += e.getWeight();
      mstq.add(e);

      if (!used.contains(e.from()))
        scan(G, e.from());
      else
        scan(G, e.to());
    }

    for (WeightedEdge e : mstq) {
      System.out.println(e.toString());
    }
  }


  private void scan(WeightedUndirectedGraph G, int v) {
    assert !used.contains(v);
    used.add(v);
    for (WeightedEdge e : G.adj(v))
      if (!used.contains(e.getOther(v)))
        pq.add(e);
  }

  public static void main(String[] args) {

    WeightedUndirectedGraph G = new WeightedUndirectedGraph();
    MSTprimsLazy mst = new MSTprimsLazy(G);
  }
}
