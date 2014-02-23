import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.*;

public class DiGraph {
  private int V;
  private int E;
  private ArrayList<LinkedList<Integer>> adj;

  public DiGraph() {
    if (V < 0)
      throw new IllegalArgumentException("Number of vertices must be nonnegative");
    this.E = 0;

    String fileName = "graph.txt";
    String line;
    try {

      FileReader freader = new FileReader(fileName);
      BufferedReader breader = new BufferedReader(freader);
      this.V = Integer.parseInt(breader.readLine());
      adj = new ArrayList<LinkedList<Integer>>();
      for (int v = 0; v < V; v++) {
        adj.add(new LinkedList<Integer>());
      }
      while ((line = breader.readLine()) != null) {
        String[] edge = line.split(" ");
        addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
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


  /**
   * Returns the number of vertices in the graph.
   * 
   * @return the number of vertices in the graph
   */
  public int V() {
    return V;
  }

  public int E() {
    return E;
  }


  public void addEdge(int v, int w) {
    if (v < 0 || v >= V)
      throw new IndexOutOfBoundsException();
    if (w < 0 || w >= V)
      throw new IndexOutOfBoundsException();
    E++;
    System.out.println("Addidng edge from: " + v + " to " + w);
    adj.get(v).add(w);
  }


  public Iterable<Integer> adj(int v) {
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
      for (int w : adj.get(v)) {
        s.append(w + " ");
      }
      s.append(NEWLINE);
    }
    System.out.println(s);
    return s.toString();
  }


  /**
   * Unit tests the <tt>Graph</tt> data type.
   */
  public static void main(String[] args) {
    DiGraph G = new DiGraph();
    G.toString();
  }

}
