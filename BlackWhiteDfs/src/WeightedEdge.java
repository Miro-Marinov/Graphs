public class WeightedEdge {
  private int from;
  private int to;
  private double weight;

  public WeightedEdge(int from, int to, double weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  public int from() {
    return from;
  }

  public Integer to() {
    return to;
  }

  public double getWeight() {
    return weight;
  }
  
  public Integer getOther(int v) {
    if (v == from)
      return to;
    else if (v == to)
      return from;
    return null;
  }

  public String toString() {
    return String.format("%d-%d %.1f", from, to, weight);
  }

  @Override
  public boolean equals(Object e) {
    return this.getWeight() == ((WeightedEdge) e).getWeight();
  }
}
