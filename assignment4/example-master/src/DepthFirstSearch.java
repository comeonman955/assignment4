import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private Set<V> marked;
    private Map<V, V> edgeTo;
    private final V source;

    public DepthFirstSearch(MyGraph<V> graph, V source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        dfs(graph, source);
    }

    private void dfs(MyGraph<V> graph, V v) {
        marked.add(v);
        for (Vertex<V> w : graph.getVertex(v).getAdjacentVertices().keySet()) {
            if (!marked.contains(w.getData())) {
                edgeTo.put(w.getData(), v);
                dfs(graph, w.getData());
            }
        }
    }

    @Override
    public boolean hasPathTo(V v) {
        return marked.contains(v);
    }

    @Override
    public List<V> pathTo(V v) {
        if (!hasPathTo(v)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V x = v; !x.equals(source); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(source);
        return path;
    }
}
