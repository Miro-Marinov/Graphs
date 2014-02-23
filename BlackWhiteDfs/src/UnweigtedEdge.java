public class UnweigtedEdge {
  private int from;
  private int to;
  private double weight;

  public UnweigtedEdge(int from, int to, double weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  public int getOne() {
    return from;
  }

  public Integer getOther(int v) {
    if (v == from)
      return to;
    else if (v == to)
      return from;
    else
      return null;
  }

  public double getWeight() {
    return weight;
  }

  public String toString() {
    return String.format("%d-%d %.1f", from, to, weight);
  }

  @Override
  public boolean equals(Object e) {
    return this.getWeight() == ((UnweigtedEdge) e).getWeight();
  }
}
