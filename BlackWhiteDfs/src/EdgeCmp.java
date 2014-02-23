import java.util.Comparator;


public class EdgeCmp implements Comparator<UnweigtedEdge> {

  public int compare(UnweigtedEdge e1, UnweigtedEdge e2) {
    if (e1.getWeight() > e2.getWeight())
      return 1;
    else if (e1.getWeight() == e2.getWeight())
      return 0;
    else
      return -1;
  }
}
