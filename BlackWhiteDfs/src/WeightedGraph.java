public interface WeightedGraph {

  public int V();

  public int E();

  public void addEdge(WeightedEdge edge);

  public Iterable<WeightedEdge> adj(int v);

  public String toString();

}
