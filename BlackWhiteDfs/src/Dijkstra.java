import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Dijkstra {
  HashSet<Integer> used = new HashSet<Integer>();
  PriorityQueue<WeightedEdge> pq = new PriorityQueue<WeightedEdge>(11, new EdgeCmp());
  Queue<WeightedEdge> qTree = new LinkedList<WeightedEdge>();
  Hashtable<Integer, Double> distTo = new Hashtable<Integer, Double>();
  Hashtable<Integer, WeightedEdge> edgeTo = new Hashtable<Integer, WeightedEdge>();

  public Dijkstra(WeightedGraph G, Integer source) {
    distTo.put(source, 0.0);
    relax(G, source);
    dijkstra(G, source);
  }

  private void relax(WeightedGraph G, int source) {
    used.add(source);
    for (WeightedEdge e : G.adj(source)) {

      if (distTo.get(e.to()) == null || distTo.get(e.to()) > distTo.get(e.from()) + e.getWeight()) {
        distTo.put(e.to(), distTo.get(e.from()) + e.getWeight());
        
        pq.remove(edgeTo.get(e.to()));
        pq.add(e);
        edgeTo.put(e.to(), e);
      }
    }
  }

  public void dijkstra(WeightedGraph G, int source) {
    while (!pq.isEmpty()) {
      WeightedEdge min = pq.remove();
      qTree.add(min);
      relax(G, min.to());
    }
  }
}
