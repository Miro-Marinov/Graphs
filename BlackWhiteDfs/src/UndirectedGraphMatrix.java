import java.util.Iterator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.io.*;

public class UndirectedGraphMatrix implements UnweightedGraph {
  // instance variables - replace the example below with your own
  private boolean[][] adjMat;
  private int V;
  private int E;

  public UndirectedGraphMatrix(int V) {
    this.V = V;
    adjMat = new boolean[V][V];
  }

  public UndirectedGraphMatrix() {
    String filename = "graph.txt";
    E = 0;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line = null;
      line = reader.readLine();
      this.V = Integer.parseInt(line);
      adjMat = new boolean[V][V];


      while ((line = reader.readLine()) != null) {
        String[] tokens = line.split(" ");
        addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
      }
      reader.close();
    } catch (IOException exception) {
      System.err.println(exception);
    }// catch

  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(int v1, int v2) {
    if (v1 < 0 || v1 >= V)
      throw new IndexOutOfBoundsException();
    if (v2 < 0 || v2 >= V)
      throw new IndexOutOfBoundsException();
    if (!adjMat[v1][v2])
      E++;
    adjMat[v1][v2] = true;
    adjMat[v2][v1] = true;
  }

  // support iteration over graph vertices
  private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
    int v, w = 0;

    AdjIterator(int v) {
      this.v = v;
    }

    public Iterator<Integer> iterator() {
      return this;
    }

    public boolean hasNext() {
      while (w < V) {
        if (adjMat[v][w])
          return true;
        w++;
      }
      return false;
    }

    public Integer next() {
      if (hasNext()) {
        return w++;
      } else {
        throw new NoSuchElementException();
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }


  public Iterable<Integer> adj(int v) {
    return new AdjIterator(v);
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
