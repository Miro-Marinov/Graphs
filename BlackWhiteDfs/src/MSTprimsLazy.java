import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MSTprimsLazy {

  PriorityQueue<Edge> pq = new PriorityQueue<Edge>(11, new EdgeCmp());
  Queue<Edge> mstq = new LinkedList<Edge>();
  HashSet<Integer> used = new HashSet<Integer>();

  double totalweight = 0;

  public MSTprimsLazy(WeightedGraph G) {
    mstPrims(G, 1);
  }

  private void mstPrims(WeightedGraph G, int v) {
    scan(G, v);

    while (!pq.isEmpty()) {
      Edge e = pq.remove();
      if(used.contains(e.getOne()) && used.contains(e.getOther(e.getOne()))) continue;
      totalweight += e.getWeight();
      mstq.add(e);

      if (!used.contains(e.getOne()))
        scan(G, e.getOne());
      else
        scan(G, e.getOther(e.getOne()));
    }

    for (Edge e : mstq) {
      System.out.println(e.toString());
    }
  }


  private void scan(WeightedGraph G, int v) {
    assert !used.contains(v);
    used.add(v);
    for (Edge e : G.adj(v))
      if (!used.contains(e.getOther(v)))
        pq.add(e);
  }

  public static void main(String[] args) {

    WeightedGraph G = new WeightedGraph();
    MSTprimsLazy mst = new MSTprimsLazy(G);
  }
}
