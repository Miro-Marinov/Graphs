public class WhiteGrayDFS {
  private int[] color;
  private int[] dfsNum;
  private int num = 0;

  public WhiteGrayDFS(UnweightedGraph G, int source) {
    whiteGrayDFS(G, source, -1);
  }

  public void whiteGrayDFS(UnweightedGraph G, int cur, int prev) {
    color[cur] = 1;
    dfsNum[cur] = num++;
    for (Integer i : G.adj(cur)) {
      if (color[i] == 0) {
        whiteGrayDFS(G, i, cur);
      } else if (color[i] == 1) { /* Black edge */
      } else if (color[i] == 2) { /* Cross edge */
      }
    }
    color[cur] = 2;
  }
}
