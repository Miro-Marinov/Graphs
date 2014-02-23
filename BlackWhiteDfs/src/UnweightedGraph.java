public interface UnweightedGraph {
  public int V();

  public int E();


  public void addEdge(int v, int w);

  public Iterable<Integer> adj(int v);


  public String toString();
}
