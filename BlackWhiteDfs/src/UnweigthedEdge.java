public class UnweigthedEdge implements Edge {
  // instance variables - replace the example below with your own
  private int first;
  private int second;

  public UnweigthedEdge(int first, int second) {
    this.first = first;
    this.second = second;
  }

  public int getOne() {
    return first;
  }

  public Integer getOther(int v) {
    if (v == first)
      return second;
    else if (v == second)
      return first;
    return null;
  }
}
