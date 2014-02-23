import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MSTprimsLazy {

  PriorityQueue<UnweigtedEdge> pq = new PriorityQueue<UnweigtedEdge>(11, new EdgeCmp());
  Queue<UnweigtedEdge> mstq = new LinkedList<UnweigtedEdge>();
  HashSet<Integer> used = new HashSet<Integer>();

  double totalweight = 0;

  public MSTprimsLazy(WeightedGraph G) {
    mstPrims(G, 0);
  }

  private void mstPrims(WeightedGraph G, int v) {
    scan(G, v);

    while (!pq.isEmpty()) {
      UnweigtedEdge e = pq.remove();
      if (used.contains(e.getOne()) && used.contains(e.getOther(e.getOne())))
        continue;
      totalweight += e.getWeight();
      mstq.add(e);

      if (!used.contains(e.getOne()))
        scan(G, e.getOne());
      else
        scan(G, e.getOther(e.getOne()));
    }

    for (UnweigtedEdge e : mstq) {
      System.out.println(e.toString());
    }
  }


  private void scan(WeightedGraph G, int v) {
    assert !used.contains(v);
    used.add(v);
    for (UnweigtedEdge e : G.adj(v))
      if (!used.contains(e.getOther(v)))
        pq.add(e);
  }

  public static void main(String[] args) {

    WeightedGraph G = new WeightedGraph();
    MSTprimsLazy mst = new MSTprimsLazy(G);
  }
}
