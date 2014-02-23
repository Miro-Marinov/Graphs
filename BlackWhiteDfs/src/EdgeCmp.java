import java.util.Comparator;


public class EdgeCmp implements Comparator<WeightedEdge> {

  public int compare(WeightedEdge e1, WeightedEdge e2) {
    if (e1.getWeight() > e2.getWeight())
      return 1;
    else if (e1.getWeight() == e2.getWeight())
      return 0;
    else
      return -1;
  }
}
