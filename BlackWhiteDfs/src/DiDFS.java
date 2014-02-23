import java.util.HashSet;
import java.util.Hashtable;
import java.util.Stack;

public class DiDFS {
	
	HashSet<Integer> used = new HashSet<Integer>();
	Hashtable<Integer, Integer> edgeTo = new Hashtable<Integer, Integer>();
	private final int s;
	public DiDFS(DiGraph G, int v) {
		this.s = v;
		dfs(G, v);
	}
	
	public void dfs(DiGraph G, int v) {
		used.add(v);
		for(Integer adj : G.adj(v)) {
			if(!used.contains(adj)) {
				//save that u go to adj from v
				edgeTo.put(adj, v);
				dfs(G, adj);
			}
		}
	}
	
	
	public boolean hasPathTo(int v) {
	    return used.contains(v);
	}
	
	public Iterable<Integer> pathTo(int t) {
		if(!hasPathTo(t))return null;
		Stack<Integer> path = new Stack<Integer>();
		for(Integer cur = t; cur != s; cur = edgeTo.get(cur)) {
			path.push(cur);
		}
		path.push(s);
		System.out.println(path.toString());
		return path;
	}
	
	public static void main(String[] args) {

        DiGraph G = new DiGraph();
        DiDFS diDfs = new DiDFS(G, 4);
        diDfs.pathTo(6);

    }

}
