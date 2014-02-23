import java.util.HashSet;
import java.io.*;

public class UndirectedGraphEdges implements UnweightedGraph {


  private HashSet<UnweigthedEdge> UnweigthedEdges;
  int V;
  private int E;

  public UndirectedGraphEdges(int V) {
    UnweigthedEdges = new HashSet<UnweigthedEdge>();
    E = 0;
    this.V = V;
  }


  public UndirectedGraphEdges() {
    this(0);
    String filename = "graph.txt";
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line = null;
      line = reader.readLine();
      this.V = Integer.parseInt(line);
      while ((line = reader.readLine()) != null) {
        String[] tokens = line.split(" ");
        addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
      }
      reader.close();
    } catch (IOException exception) {
      System.err.println(exception);
    }// catch

  }


  public void addEdge(int v1, int v2) {
    if (v1 < 0 || v1 >= V)
      throw new IndexOutOfBoundsException();
    if (v2 < 0 || v2 >= V)
      throw new IndexOutOfBoundsException();

    for (UnweigthedEdge e : UnweigthedEdges) {
      Integer tmp_v = e.getOther(v1);
      if (tmp_v != null && tmp_v == v2)
        return;
    }
    UnweigthedEdges.add(new UnweigthedEdge(v1, v2));
    E++;
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public Iterable<Integer> adj(int v) {
    if (v < 0 || v >= V)
      throw new IndexOutOfBoundsException();
    HashSet<Integer> vertices = new HashSet<Integer>();
    for (UnweigthedEdge e : UnweigthedEdges) {
      Integer tmp_v = e.getOther(v);
      if (tmp_v != null)
        vertices.add(tmp_v);
    }
    return vertices;
  }

  public String toString() {
    String s = new String();
    String NEWLINE = System.getProperty("line.separator");
    s = s + V + " vertices, " + E + " edges " + NEWLINE;
    for (int v = 0; v < V; v++) {
      s += v + ": ";
      for (Integer w : adj(v)) {
        s += w + " ";
      }
      s += NEWLINE;
    }
    System.out.println("GraphMatrix:");
    System.out.println(s);
    return s;
  }
}
