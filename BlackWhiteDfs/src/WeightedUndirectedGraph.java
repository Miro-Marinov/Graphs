import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;

public class WeightedUndirectedGraph implements WeightedGraph{
  private int V;
  private int E;
  private ArrayList<LinkedList<WeightedEdge>> adj;

  public WeightedUndirectedGraph() {
    if (V < 0)
      throw new IllegalArgumentException("Number of vertices must be nonnegative");
    this.E = 0;

    String fileName = "weightedGraph.txt";
    String line;
    try {

      FileReader freader = new FileReader(fileName);
      BufferedReader breader = new BufferedReader(freader);
      this.V = Integer.parseInt(breader.readLine());
      adj = new ArrayList<LinkedList<WeightedEdge>>();
      for (int v = 0; v < V; v++) {
        adj.add(new LinkedList<WeightedEdge>());
      }
      while ((line = breader.readLine()) != null) {
        String[] edge = line.split(" ");
        addEdge(new WeightedEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]),
            Double.parseDouble(edge[2])));
      }
      breader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
      // Or we could just do this:
      // ex.printStackTrace();
    }

  }



  public int V() {
    return V;
  }

  public int E() {
    return E;
  }


  public void addEdge(WeightedEdge edge) {
    E++;
    System.out.println("Addidng edge beween: " + edge.from() + " and "
        + edge.to() + " with weight: " + edge.getWeight());
    adj.get(edge.from()).add(edge);
  }


  public Iterable<WeightedEdge> adj(int v) {
    if (v < 0 || v >= V)
      throw new IndexOutOfBoundsException();
    return adj.get(v);
  }



  public String toString() {
    StringBuilder s = new StringBuilder();
    String NEWLINE = System.getProperty("line.separator");
    s.append(V + " vertices, " + E + " edges " + NEWLINE);
    for (int v = 0; v < V; v++) {
      s.append(v + ": ");
      for (WeightedEdge e : adj.get(v)) {
        s.append(e.toString() + " ");
      }
      s.append(NEWLINE);
    }
    System.out.println(s);
    return s.toString();
  }


  public static void main(String[] args) {
    WeightedUndirectedGraph G = new WeightedUndirectedGraph();
    G.toString();
  }

}
