import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MSTprimsEager {

  PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>(11, new EdgeCmp());
  Queue<WeightedEdge> mstq = new LinkedList<WeightedEdge>();
  HashSet<Integer> used = new HashSet<Integer>();
  Hashtable<Integer, Double> distTo = new Hashtable<Integer, Double>();
  Hashtable<Integer, WeightedEdge> edgeTo = new Hashtable<Integer, WeightedEdge>();

  double totalweight = 0;

  public MSTprimsEager(WeightedUndirectedGraph G) {
    mstPrims(G, 0);

  }

  private void mstPrims(WeightedUndirectedGraph G, int v) {
    scan(G, v);

    while (mstq.size() < G.V() - 1) { // !pq.isEmpty() - slower
      WeightedEdge e = pq.remove();
      if (used.contains(e.from()) && used.contains(e.to()))
        continue;
      totalweight += e.getWeight();
      System.out.println("Adding to the tree edge: " + e.toString());
      mstq.add(e);

      if (!used.contains(e.from()))
        scan(G, e.from());
      else
        scan(G, e.to());
    }
    System.out.println("Minimal spanning tree:");
    for (WeightedEdge e : mstq) {
      System.out.println(e.toString());
    }
  }


  private void scan(WeightedUndirectedGraph G, int v) {
    assert !used.contains(v);
    used.add(v);
    for (WeightedEdge e : G.adj(v))
      if (!used.contains(e.getOther(v)))
        if (distTo.get(e.getOther(v)) == null || e.getWeight() < distTo.get(e.getOther(v))) // add
                                                                                            // only
                                                                                            // if
                                                                                            // not
                                                                                            // yet
                                                                                            // added
                                                                                            // and
                                                                                            // necessary
        {
          if (distTo.get(e.getOther(v)) != null)
            System.out.println("Replacing: " + edgeTo.get(e.getOther(v)).toString() + " with "
                + e.toString());
          /*
           * We add v to the spanning tree. Then any vertex w not in the tree can get closer to the
           * tree (because of edge v-w)
           */
          distTo.put(e.getOther(v), e.getWeight()); // update the distance
          pq.remove(edgeTo.get(e.getOther(v))); // remove edge from pq
          edgeTo.put(e.getOther(v), e); // using this edge now
          pq.add(e); // add edge to pq
        }
  }

  public static void main(String[] args) {

    WeightedUndirectedGraph G = new WeightedUndirectedGraph();
    MSTprimsEager mst = new MSTprimsEager(G);
  }
}
