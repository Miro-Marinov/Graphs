import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class Dijkstra {
  HashSet<Integer> visited = new HashSet<Integer>();
  Queue<WeightedEdge> qTree = new LinkedList<WeightedEdge>();
  Hashtable<Integer, Double> distTo = new Hashtable<Integer, Double>();

  public Dijkstra(WeightedGraph G, Integer source) {
	
	distTo.put(source, 0.0);
    relax(G, source);
    System.out.println("Distances to:");
    System.out.println(distTo.toString());
  }

  private void relax(WeightedGraph G, int source) {
    visited.add(source);
    
    // add new Edges and change the respective shortest path values
    for (WeightedEdge e : G.adj(source)) {
    	
    	if (distTo.get(e.to()) == null 
    		  || distTo.get(e.to()) > distTo.get(e.from()) + e.getWeight()) {
        
    	distTo.put(e.to(), distTo.get(e.from()) + e.getWeight());

      }
    }
    Map.Entry<Integer, Double> getMin = getMin();
    if(getMin != null) relax(G, getMin.getKey());
  }


  // min distTo Entry (Priority Queue will be a good idea here but need to 
  // make a class Vertex and comparator for vertices according to distTo[]
  public Map.Entry<Integer, Double> getMin(){
	  Double min = Double.MAX_VALUE;
	  Map.Entry<Integer, Double> minEntry = null;
	  for(Map.Entry<Integer, Double> entry : distTo.entrySet()) {
		  if(entry.getValue().compareTo(min) < 0 && !visited.contains(entry.getKey())) {
			  min = entry.getValue();
			  minEntry = entry;
		  }
	  }
	  return minEntry;
  }
  

  public static void main(String[] args) {

    WeightedDirectedGraph G = new WeightedDirectedGraph("weightedDirectedGraph.txt");
    G.toString();
    Dijkstra dijkstra = new Dijkstra(G, 0);
  }
  
  
}
