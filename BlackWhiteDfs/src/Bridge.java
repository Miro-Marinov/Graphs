
public class Bridge {
    private int bridges;      // number of bridges
    private int cnt;          // counter
    private int[] dfsVisitedOrder;        // dfsVisitedOrder[cur] = order in which dfs examines cur
    private int[] erliestVisitedReachable;        // erliestVisitedReachable[cur] = lowest preorder of any vertex connected (reachable from) to cur

    public Bridge(Graph G) {
        erliestVisitedReachable = new int[G.V()];
        dfsVisitedOrder = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
        	erliestVisitedReachable[v] = -1;
        	dfsVisitedOrder[v] = -1;
        	}
        

        for (int v = 0; v < G.V(); v++)
            if (dfsVisitedOrder[v] == -1)
                dfs(G, v, v);
    }

    public int components() { return bridges + 1; }

    private void dfs(Graph G, int cur, int prev) {
        System.out.print("Visiting " + cur);
       
    	dfsVisitedOrder[cur] = cnt++;
        erliestVisitedReachable[cur] = dfsVisitedOrder[cur];
        System.out.println(" erliestVisitedReachable: " + erliestVisitedReachable[cur] + " dfsVisitedOrder: " + dfsVisitedOrder[cur]);
        for (int w : G.adj(cur)) {
            System.out.println("Look at Neigh: " + w);
        	if (dfsVisitedOrder[w] == -1) { //not visited
                dfs(G, w, cur);
                
                // Check if the subtree rooted with w has a connection to
                // one of the ancestors of cur (or cur itself)
                erliestVisitedReachable[cur] = Math.min(erliestVisitedReachable[cur], erliestVisitedReachable[w]);
                System.out.println("Changing1:  erliestVisitedReachable: " + erliestVisitedReachable[cur] + " dfsVisitedOrder: " + dfsVisitedOrder[cur]);
                

                // If the lowest vertex reachable from subtree under v is 
                // below u in DFS tree, then u-v is a bridge
                if (dfsVisitedOrder[cur] < erliestVisitedReachable[w]) { //if (erliestVisitedReachable[w] == dfsVisitedOrder[w])
                    System.out.println(cur + "-" + w + " is a BRIDGE!");
                    bridges++;
                }
            }
        		
            // Update erliestVisitedReachable value of u for parent function calls - 
        	// ignore reverse of edge leading to cur
            else if (w != prev){
                erliestVisitedReachable[cur] = Math.min(erliestVisitedReachable[cur], dfsVisitedOrder[w]);
                System.out.println("Changing2:  erliestVisitedReachable: " + erliestVisitedReachable[cur] + " dfsVisitedOrder: " + dfsVisitedOrder[cur]);
            }
        }
        System.out.println("Go back to dfs for " + prev);
    }

    // test client
    public static void main(String[] args) {
        Graph G = new Graph();
        G.toString();

        Bridge bridge = new Bridge(G);
        System.out.println("Edge connected components = " + bridge.components());
    }


}